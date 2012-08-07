/**
 * 
 */
package weimin.magazine.back.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TDepartmentComment;
import weimin.magazine.back.dao.pojo.TMagazineFinal;
import weimin.magazine.back.dao.pojo.TUserSubscribe;
import weimin.magazine.back.vo.SubscribeRelation;
import weimin.magazine.util.SystemConfig;
import weimin.magazine.util.Tools;

/**
 * @author tianhao
 *
 */
public class ReadService extends BaseService{
    
    private static final Log log = LogFactory.getLog(ReadService.class);
    
    private RecommendService recommendService;// 通过spring注入；

    /**
     * 
     */
    public ReadService() {
    }
    
    /**
     * <br>预览当前杂志社的最新期刊
     * TODO 可考虑把当前期刊放入缓存，以提高读取效率
     * @param tDepartment
     * @return
     */
    public String previewCurrent(long departmentId){
        TMagazineFinal tMagazineFinal = tMagazineFinalDaoImpl.queryCurrent(departmentId);
        String magazineUrl = tMagazineFinal.getMagazineUrl();
        return magazineUrl;
    }
    
    /**
     * <br>批量订阅杂志；
     * <br>步骤：
     * 1.依次订阅杂志，若订阅成功则记录状态为1，否则为0
     * 2.发送宣传微博到队列中；
     * 3.返回订阅结果。
     * 
     * @param userId
     * @param departmentIds
     * @return HashMap<key,value> 
     *          key为杂志id； value为订阅状态：  0未订阅1 已订阅
     */
    public HashMap<String, String> batchSubscribe(long userId,
            List<String> departmentIds) {
        HashMap<String, String> maps = new HashMap<String, String>();
        Iterator<String> iterator = departmentIds.iterator();
        while (iterator.hasNext()) {
            String departmentId = iterator.next();
            boolean tag = subscribe(userId, Long.parseLong(departmentId));
            if (tag) {
                maps.put(departmentId, "1");
            } else {
                maps.put(departmentId, "0");
            }
        }
        // TODO 发送宣传微博到队列中；无论订阅多少杂志，仅发送一条微博。
        return maps;
    }
    
   
    
    
    
    
    /**
     * <>取消订阅
     * 1.取消订阅关系
     * 2.杂志订阅数减1
     * 3.用户订阅数减1
     * @param userId
     * @param departmentId
     * @return
     */
    public boolean cancelSubscribe(Long userId, Long departmentId){
        try {
            Object o = tUserSubscribeDaoImpl.findSubscribe(userId, departmentId);
            if(o != null){
                TUserSubscribe subscribe = (TUserSubscribe) o;
                if(subscribe.getStatus() == 0){
                    //status = 0 表明已取消
                    log.info("用户：" + userId + "  取消订阅杂志：" + departmentId + "  取消订阅失败！： 此订阅关系已被取消！");
                    return true;
                }
                subscribe.setCancelAt(Tools.getDate());
                subscribe.setStatus(0);
                tUserSubscribeDaoImpl.update(subscribe);
                 //2.杂志社订阅数增-1
                tDepartmentDaoImpl.addSubscribeCount(departmentId, -1);
                //3.用户订阅数增-1
                tUserDaoImpl.addSubscribeCount(userId,-1);
                log.info("用户：" + userId + "  取消订阅杂志：" + departmentId + "  取消订阅成功！");
            }else{
                log.info("用户：" + userId + "  取消订阅杂志：" + departmentId + "  取消订阅失败！： 用户未订阅此杂志！");
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("用户：" + userId + "   取消订阅杂志：" + departmentId + "   取消订阅失败！：" + e);
        }
        return false;
    }
    
    
    
    

    /**
     * <br>用户对杂志社发表评论</br>
     * <br> 步骤：
     * 1.保存评论，更新评论id；
     * 2.发送一条微博
     * @param tDepartmentComment
     * @return
     */
    public TDepartmentComment comment(TDepartmentComment tDepartmentComment) {
        tDepartmentComment.setCreatedAt(Tools.getDate());
        long commentId = (Long) tDepartmentCommentDaoImpl
                .insert(tDepartmentComment);
        log.info("用户：" + tDepartmentComment.getUserId() + "    对杂志社："
                + tDepartmentComment.getDepartmentId() + "   发表评论！评论内容为："
                + tDepartmentComment.getContent());
        tDepartmentComment.setCommentId(commentId);
        // 2.发送一条微博 ,放入消息队列中
        return tDepartmentComment;
    }
    
    /**
     * <br>显示用户已订阅内容</br>
     * 1.获取用户订阅信息
     * 2.用户订阅数据太少，选择系统推荐进行显示；
     * @param userId
     * @return
     */
    public Set<SubscribeRelation> showSubAndRec(long userId){
        log.debug("开始检索用户订阅杂志！");
        Set<SubscribeRelation> relateions = new LinkedHashSet<SubscribeRelation>();
        List<Object> subscribes = tUserSubscribeDaoImpl.querySubscribeByUserId(userId);
        int size = subscribes.size();
        if(size > 0){
            log.debug("开始检索已订阅杂志信息！");
            Iterator<Object> iterator =  subscribes.iterator();
            while (iterator.hasNext()){
                
                TUserSubscribe subscribe = (TUserSubscribe) iterator.next();
                long departmentId = subscribe.getDepartmentId();
                TDepartment department = this.find4TDepartment(departmentId);
                SubscribeRelation relation = new SubscribeRelation();
                relation.setDepartment(department);
                relation.setFlag(1);//0未订阅，1已订阅
                relation.setUserId(userId);
                relateions.add(relation);
            }
        }
        int num = Integer.valueOf(SystemConfig.getValue("sys.subscribe.max.number"));
        if(num > size ){
            log.debug("用户订阅的杂志太少，从推荐中获取部分进行填补");
            // 检索redis获得为用户推荐的杂志，
            Set<TDepartment> recomments = new LinkedHashSet<TDepartment>();
            recomments = recommendService.queryRecomments(userId);
            Iterator<TDepartment> iterator =  recomments.iterator();
            for(int i = 0 ; i<(num-size); i++){
                while (iterator.hasNext()){
                    TDepartment department = iterator.next();
                    SubscribeRelation relation = new SubscribeRelation();
                    relation.setDepartment(department);
                    relation.setFlag(0);//0未订阅，1已订阅
                    relation.setUserId(userId);
                    relateions.add(relation);
                }
            }
        }
        return relateions;
    }
    
    /**
     * <br>检索用户所发表的所有评论
     * @param userId
     * @return
     */
    public List<TDepartmentComment> showComments(long userId){
        List<TDepartmentComment> comments = tDepartmentCommentDaoImpl.queryCommentsByUserId(userId);
        if(comments != null ){
            log.debug("显示用户对杂志社的评论，用户id为："+userId);
            return comments;
        }else{
            log.debug("用户未发表任何评论，用户id为："+userId);
            return null;
        }
    }
    
    /**
     * <br>用户订阅某杂志</br>
     * <br>步骤：</br>
     * <br>1.增加订阅关系：</br>
     * <br>2.杂志社订阅数增1</br>
     * <br>3.用户订阅数增1</br>
     * <br>4. 在推荐中移除已订阅</br>
     * @param userId
     * @param departmentId
     * @return 
     */
    protected boolean subscribe(Long userId, Long departmentId) {
        try {
            //1.增加订阅关系
            long subscribeId = addSubscribe(userId, departmentId);
            log.info("用户：" + userId + "  订阅杂志：" + departmentId
                    + "  订阅已成功！订阅编号为：" + subscribeId);
            //2.杂志社订阅数增1
            tDepartmentDaoImpl.addSubscribeCount(departmentId, 1);
            //3.用户订阅数增1
            tUserDaoImpl.addSubscribeCount(userId,1);
            //4.在推荐中移除已订阅
            redis.deleteSubscribe(userId, departmentId);
            return true;
        } catch (Exception e) {
            log.error("用户：" + userId + "  订阅杂志：" + departmentId + "  订阅失败！：");
        }
        return false;
    }
    
    /**
     * 增加订阅关系</br>
     *  步骤：
     *  1.查询此关系是否存在；
     *  2.若存在则判断是否已订阅，若不是则修改；若是则更新订阅时间
     * 3.若不存在则增加订阅关系
     * @param userId
     * @param departmentId
     * @return
     */
    private long addSubscribe(Long userId, Long departmentId) {
        log.debug("准备为用户："+userId +"  订阅杂志"+departmentId);
        TUserSubscribe userSubscribe = new TUserSubscribe();
        userSubscribe.setUserId(userId);
        userSubscribe.setDepartmentId(departmentId);
        //检查此关系是否存在
        Object o =  tUserSubscribeDaoImpl.findSubscribe(userId ,departmentId);
        if(o != null){
            //检查到此关系已存在
            log.debug("用户曾经订阅过此杂志！");
            userSubscribe = (TUserSubscribe) o;
            int status = userSubscribe.getStatus();
            long id = userSubscribe.getSubscribeId();
            if(status == 0){
                log.debug("用户曾经订阅过此杂志！现在的状态为：已取消订阅！");
                //状态为0表明未订阅，修改为 1，已订阅，更新订阅时间，并保存
                userSubscribe.setCreatedAt(Tools.getDate());
                userSubscribe.setStatus(1);
                log.debug("用户重新订阅此杂志！");
                tUserSubscribeDaoImpl.update(userSubscribe);
            }
            //否则表明已订阅，直接返回订阅关系id
            return id;
        }else{
            //此关系不存在，直接添加订阅关系
            userSubscribe.setStatus(1);//1为已订阅成功
            userSubscribe.setCreatedAt(Tools.getDate());
            long id = (Long) tUserSubscribeDaoImpl.insert(userSubscribe);
            return id;
        }
    }

    public RecommendService getRecommendService() {
        return recommendService;
    }

    public void setRecommendService(RecommendService recommendService) {
        this.recommendService = recommendService;
    }
    
    

}
