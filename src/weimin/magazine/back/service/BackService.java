/**
 * 
 */
package weimin.magazine.back.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.model.Emotion;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weimin.magazine.back.analysis.Calculate;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TLabel;
import weimin.magazine.back.dao.pojo.TMagazineTheme;
import weimin.magazine.back.dao.pojo.TUser;
import weimin.magazine.back.dao.pojo.TUserContribute;
import weimin.magazine.back.snsApi.sinaWeibo.tags.ReadTags;
import weimin.magazine.back.snsApi.sinaWeibo.user.ReadUser;
import weimin.magazine.back.snsApi.sinaWeibo.weibo.WriteWeibo;
import weimin.magazine.util.SystemConfig;



/**
 * @author tianhao
 *
 */
public class BackService extends BaseService {

    private static final Log log = LogFactory.getLog(BackService.class);
    
    private Calculate  calculate = new Calculate();//
    
    private RecommendService recommendService;// 通过spring注入；
    
    /**
     * 
     */
    public BackService() {
    }
    
    /**
     * <br>计算杂志的综合排名</br>
     *<br> 步骤：</br>
     *<br>1.检索所有杂志；</br>
     *<br>2，分别计算各个杂志的综合积分；</br>
     *<br>3，更新系统总排行榜</br>
     *<br>4，更新系统分类排行榜</br>
     * <br>TODO 当数据量大时，需考虑检索所有杂志所带来的性能影响，</br>
     * <br>TODO 当数据量大时，应对此工作进行拆分或使用mapreduce进行。</br>
     */
    public void calculateTop(){
        List<Object> list = tDepartmentDaoImpl.queryAll();
        log.debug("开始计算所有杂志的综合积分！");
        Iterator<Object> iterator =  list.iterator();
        while (iterator.hasNext()){
            Object o = iterator.next();
            TDepartment tDepartment = (TDepartment) o;
            //计算杂志综合积分
            int departmentScore = calculate.calculateDepartmentScore(tDepartment);
            //更新杂志综合积分
            tDepartment.setDepartmentScore(departmentScore);
            tDepartmentDaoImpl.update(tDepartment);
        }
        log.debug("已完成计算所有杂志的综合积分！");
        //重置总排行榜
        this.replaceTop();
        ///重置所有分类排行榜
        List<Integer> types = tMagazineThemeDaoImpl.queryThemeTypes();
        Iterator<Integer> typeIterator =  types.iterator();
        while (typeIterator.hasNext()){
            int type = typeIterator.next();
            this.replaceThemeTop(type);
        }
    }
    
    /**
     * <br>为用户创建标签，保存在db中，每个label对应db中的一条记录；同时写入缓存；
     * 
     * @param labels
     * @param userId
     * @throws WeiboException 
     */
    public void collectUserLabels(long userId)  {
        log.debug("开始收集系统用户："+ userId +" 的微博标签。");
        long uid = this.transUserId2Uid(userId);
        TAccessToken tAccessToken = this.find4TAccessToken(userId);
        // 获取用户标签,保存在db中，同时写入缓存；
        ReadTags readTags = new ReadTags(tAccessToken.getAccessToken());
        Set<String> labels;
        try {
            labels = readTags.showUserTags(uid);
         // 同步微博标签到系统
            this.modifyUserLabels(userId, labels);
        } catch (WeiboException e) {
           log.error("收集系统用户："+ userId +"   异常",e);
        }
    }
    
    /**
     * <br>更新用户微博信息。
     * @param userId
     */
    public void collectUserInfo(long userId){
        log.debug("开始收集系统用户："+ userId +" 的用户信息。");
        TAccessToken tAccessToken = this.find4TAccessToken(userId);
        ReadUser readUser = new ReadUser(tAccessToken.getAccessToken());
        TUser user = readUser.showUserById(tAccessToken.getUid());
        if(user != null ){
            log.debug("开始更新用户微博信息，用户id为："+userId);
            TUser tUser = (TUser) tUserDaoImpl.queryById(userId);
            {
                //写入系统特有信息
                user.setUserId(userId); //系统用户id ****
                user.setUserSource(tUser.getUserSource()); //用户评分
                user.setCreateEnabled(tUser.isCreateEnabled());//是否允许创建编辑部 :0为未创建，1为已创建
                user.setParticipateCount(tUser.getParticipateCount());//参与编辑数  ****** 
                user.setSubscribeCount(tUser.getSubscribeCount());  //订阅数  ****** 
                user.setSubmissionCount(tUser.getSubmissionCount()); //提交数  ******
            }
          //更新用户信息
            tUserDaoImpl.update(user);
        }
    }
    
    /**
     * <br>从用户微博中过滤出投稿
     * <br>步骤：
     * 1.获取@用户的微博；
     * 2.检索微博内容，过滤出投稿微博；
     * 3.判断投稿记录是否存在，若不存在则生成一条投稿记录，并记录投稿信息；
     * 4.返回稿件
     * @param userId
     * @return
     */
    public void collectContributeInWeibo(long userId){
        log.debug("开始检索用户微博中的投稿微博，用户id为：" +userId);
        TDepartment department = tDepartmentDaoImpl.queryByCreater(userId);
        if(department != null){
            long departmentId = department.getDepartmentId();
            //1.获取@用户的微博；
            StatusWapper statusWapper = weiboService.showMentions(this.find4TAccessToken(userId));
            List<Status> status = statusWapper.getStatuses();
            //2.检索微博内容，过滤出投稿微博；
            Iterator<Status> iterator =  status.iterator();
            while (iterator.hasNext()){
                Status s = iterator.next();
                 String text = s.getText();
                if(this.validateContribution(text)){
                    log.debug("验证通过，获取一条投稿微博！");
                    //构造投稿关系
                    TUserContribute tUserContribute = this.createContributeByStatus(userId,departmentId,s);
                    //判断此投稿记录是否存在
                    Object o = tUserContributeDaoImpl.queryContribute(tUserContribute);
                    if(o != null){
                        log.debug("此投稿记录已存在");
                    }else{
                        log.debug("增加一条投稿记录");
                        long contributeId = (Long) tUserContributeDaoImpl.insert(tUserContribute);
                        //更新投稿id
                        tUserContribute.setContributeId(contributeId);
                    }
                }
            }
        }else{
            log.debug("此用户不是主编，不需要收集微博投稿，用户id为：" +userId);
        }
        log.debug("完成检索用户微博中的投稿微博，用户id为：" +userId);
    }
    
    /**
     * <br>从用户收听的好友中获取杂志社创建者，用于用户投稿
     * @param userId
     * @return 
     */
    public List<Long> collectDepartmentByWeiboFriends(long userId){
        
        return null;
    }

    /**
     * <br>重置系统总排行榜
     * <br>步骤：
     * 1.获取后台杂志社排名；
     * 2.根据后台设置，获取一定数量的杂志社；
     * 3.按照名次更新排行榜
     * 4.更新缓存中杂志社信息
     */
    public void replaceTop(){
        log.debug("开始重置系统总排行榜！");
        // 排行榜hashMap<String, String> key为名次，value编辑部id
        Map<String, String> hash   = new HashMap<String, String>();
        List<Object> departments = tDepartmentDaoImpl.getTop();
        int num = departments.size();
        int maxNmu = Integer.valueOf(SystemConfig.getValue("top.department.max.number"));
        if(maxNmu < num) {num = maxNmu;}//取最小值
        for(int i= 0 ;i < num ;i++){
            TDepartment department = (TDepartment) departments.get(i);
            long departmentId = department.getDepartmentId();
            hash.put(String.valueOf(i), String.valueOf(departmentId));
            //重置缓存中杂志社信息；
            redis.addDepartment(department);
        }
        //更新缓存
        redis.addTop(hash);
    }
    
    /**
     * <br>重置系统总排行榜
     * <br>步骤：
     * 1.获取后台杂志社排名；
     * 2.根据后台设置，获取一定数量的杂志社；
     * 3.按照名次更新排行榜
     * 4.更新缓存中杂志社信息
     */
    public void replaceThemeTop(int type){
        log.debug("开始重置系统分类排行榜！类别为："+type);
        // 排行榜hashMap<String, String> key为名次，value编辑部id
        Map<String, String> hash   = new HashMap<String, String>();
        List<Object> departments = tDepartmentDaoImpl.getTopByTheme(type);
        int num = departments.size();
        int maxNmu = Integer.valueOf(SystemConfig.getValue("top.department.max.number.theme"));
        if(maxNmu < num) {num = maxNmu;}//取最小值
        for(int i= 0 ;i < num ;i++){
            TDepartment department = (TDepartment) departments.get(i);
            long departmentId = department.getDepartmentId();
            hash.put(String.valueOf(i), String.valueOf(departmentId));
            //重置缓存中杂志社信息；
            redis.addDepartment(department);
        }
        //更新缓存
        redis.addThemeTop(hash,type);
    }
    
    /**
     * <br>重置系统推荐标签</br>
     * <br>步骤：
     * 1.获取后台系统标签，按照使用次数排序；
     * 2.获取系统推荐杂志的参数；
     * 3.清除缓存中历史数据；
     * 4.更新缓存中系统推荐标签列表
     *  <--系统后台服务-->
     */
    public List<String> replaceLabels(){
        ArrayList<String> labels = new ArrayList<String>();
        {
            log.debug("开始重置系统推荐标签！");
            //检索系统推荐标签
            List<TLabel> TLabels = tLabelDaoImpl.queryRecommendLabels();
            int size = TLabels.size();
            //系统默认推荐标签个数，
            int maxNUm = Integer.valueOf(SystemConfig.getValue("sys.suggestions.label.max.number"));
            log.debug("系统标签个数为："+size + "    默认显示推荐标签个数为："+maxNUm);
            if(size > maxNUm) {size = maxNUm;}
            //生成推荐标签
            for(int i = 0 ; i < size ;i++){
                labels.add(TLabels.get(i).getName());
            }
            //先清除历史推荐
            redis.deleteLabels();
            //存入缓存
            redis.addSuggestionsLabels(labels); 
            return labels;
        }
    }
    
    /**
     * <br>重置系统推荐主题</br>
     * <br>步骤：
     * 1.获取后台系统主题，按照使用次数排序；
     * 2.获取系统推荐杂志的参数；
     * 3.清除缓存中历史数据；
     * 4.更新缓存中系统推荐主题列表
     * @return
     */
    public ArrayList<String> replaceThemes() {
        ArrayList<String> themes = new ArrayList<String>();
        {
            log.debug("开始重置系统推荐标签！");
            //检索系统推荐标签
            List<TMagazineTheme> tMagazineThemes = tMagazineThemeDaoImpl.queryRecommendThemes();
            int size = tMagazineThemes.size();
            //系统默认推荐标签个数，
            int maxNUm = Integer.valueOf(SystemConfig.getValue("sys.suggestions.theme.max.number"));
            log.debug("系统标签个数为："+size + "    默认显示推荐标签个数为："+maxNUm);
            if(size > maxNUm) {size = maxNUm;}
            //生成推荐标签
            for(int i = 0 ; i < size ;i++){
                themes.add(tMagazineThemes.get(i).getMagazineThemeContent());
            }
            //先清除历史推荐
            redis.deleteThemes();
            //存入缓存
            redis.addSuggestionsThemes(themes); 
            return themes;
        }
    
    }

    /**
     * <br>重置微博表情
     * @throws WeiboException
     */
    public List<Emotion> replaceEmotions()  {
        long uid = Long.valueOf(SystemConfig.getValue("sys.collect.user.uid"));
        String accessToken = redis.queryAccessTokenByUid(uid);
        WriteWeibo writeWeibo = new WriteWeibo(accessToken);
        try {
            List<Emotion> emotions =  writeWeibo.showEmotions();
            redis.addEmotions(emotions);
            return emotions;
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     *<br> 重置为用户推荐的杂志</br>
     *  <--系统后台服务-->
     * 
     * @param userId
     * @return  编辑部列表
     */
    public Set<TDepartment> replaceRecomments(long userId) {
        Set<TDepartment> recomments = recommendService.calculateRecomments(userId);
        // 删除已有推荐
        redis.deleteRecomments(userId);
        // 更新推荐
        redis.addRecomments(userId, recomments);
        log.info("重置推荐给用户的杂志，用户id为：" + userId+" 杂志数目为："+recomments.size());
        return recomments;
    }
    
    
    /**
     * <br>重置 -为用户创建的杂志社推荐编辑;
     * 
     * @param userId
     */
    public void replaceRecommentEditors(long userId){
    //    Set<TUser> editors = new LinkedHashSet<TUser>();
      //查找用户所创建的杂志社
    //    TDepartment department = tDepartmentDaoImpl.queryByCreater(userId);
        //为杂志社匹配主编
   //     editors = recommendService.calculateEditors(department);
    }
    
    /**
     * <br>根据useid和微博Status，构造投稿关系
     * <br>步骤：
     * 1过滤投稿标识
     * 2获取杂志社id
     * 3投稿用户id
     * 4其它微博信息
     * @param userId
     * @param s
     * @return
     */
    private TUserContribute createContributeByStatus(long userId,long departmentId ,Status s) {
        TUserContribute tUserContribute = new TUserContribute();
        //3.1过滤投稿标识
        String content = this.filterContributeFlag(s.getText());
        tUserContribute.setContent(content);
        //3.2获取杂志社id
        tUserContribute.setDepartmentId(departmentId);
        //3.3投稿用户id
        String  contributeruid = s.getUser().getId();
        long contributerId = this.transUid2UserId(Long.valueOf(contributeruid));
        tUserContribute.setUserId(contributerId);
        //3.4其它微博信息
        tUserContribute.setWeiboId(Long.valueOf(s.getId())); //获取微博id
        tUserContribute.setContributeComment(s.getCommentsCount());//评论数
        tUserContribute.setContributeForward(s.getRepostsCount());//转发数
        tUserContribute.setContentPic(s.getOriginalPic()); //原始图片
        return tUserContribute;
    }
    
    /**
     * <br>验证微博是否为投稿微博 
     * @param text
     * @return 是投稿返回true，否则返回false
     */
    private  boolean validateContribution(String text) {
        String flag = SystemConfig.getValue("weibo.contribute.flag");
        if(text.indexOf(flag) >= 0){
            return true;  
        }
        return false;
    }

    /**
     * <br>过滤投稿标识，获取投稿内容。
     * @param text
     * @return
     */
    private  String filterContributeFlag(String text) {
        int size = text.length();
        String flag = SystemConfig.getValue("weibo.contribute.flag");
        int beginIndex = text.indexOf(flag)+flag.length();
        return text.substring(beginIndex, size);
    }

    public RecommendService getRecommendService() {
        return recommendService;
    }

    public void setRecommendService(RecommendService recommendService) {
        this.recommendService = recommendService;
    }
    
    
}
