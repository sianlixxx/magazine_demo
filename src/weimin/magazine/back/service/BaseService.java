/**
 * 
 */
package weimin.magazine.back.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.model.Emotion;
import weibo4j.model.WeiboException;
import weimin.magazine.back.cache.Redis4Cache;
import weimin.magazine.back.dao.impl.TAccessTokenDaoImpl;
import weimin.magazine.back.dao.impl.TDepartmentCommentDaoImpl;
import weimin.magazine.back.dao.impl.TDepartmentDaoImpl;
import weimin.magazine.back.dao.impl.TDepartmentEditorDaoImpl;
import weimin.magazine.back.dao.impl.TLabelDaoImpl;
import weimin.magazine.back.dao.impl.TMagazineFinalDaoImpl;
import weimin.magazine.back.dao.impl.TMagazineThemeDaoImpl;
import weimin.magazine.back.dao.impl.TUserContributeDaoImpl;
import weimin.magazine.back.dao.impl.TUserDaoImpl;
import weimin.magazine.back.dao.impl.TUserSubscribeDaoImpl;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TLabel;
import weimin.magazine.back.dao.pojo.TUser;
import weimin.magazine.back.snsApi.sinaWeibo.tags.WriteTags;
import weimin.magazine.util.SpringContextTool;




/**
 * @author tianhao
 *
 */
public class BaseService {
	
	private static final Log log = LogFactory.getLog(BaseService.class);
	
	protected Redis4Cache redis = new Redis4Cache();// 
	
	protected WeiboService weiboService = new WeiboService();//
	
	protected TAccessTokenDaoImpl tAccessTokenDaoImpl;// 通过spring注入；
	
	protected TUserDaoImpl tUserDaoImpl;// 通过spring注入；

	protected TLabelDaoImpl tLabelDaoImpl;// 通过spring注入；

	protected TDepartmentDaoImpl tDepartmentDaoImpl;// 通过spring注入；
	
	protected TDepartmentEditorDaoImpl tDepartmentEditorDaoImpl;// 通过spring注入；
	
	protected TUserSubscribeDaoImpl tUserSubscribeDaoImpl;// 通过spring注入；
	
	protected TMagazineFinalDaoImpl tMagazineFinalDaoImpl;// 通过spring注入；
	
	protected TDepartmentCommentDaoImpl  tDepartmentCommentDaoImpl;// 通过spring注入；
	
	protected TUserContributeDaoImpl tUserContributeDaoImpl;// 通过spring注入；
	
	protected TMagazineThemeDaoImpl tMagazineThemeDaoImpl;// 通过spring注入；

	/**
	 * 获取系统用的微博授权信息
	 * @param userId
	 * @return
	 */
	public TAccessToken find4TAccessToken(Long userId) {
	    TAccessToken tAccessToken = new TAccessToken();
	    long uid = this.transUserId2Uid(userId);
	    String accessToken = redis.queryAccessTokenByUid(uid);
	    if(accessToken != null){
	        tAccessToken.setAccessToken(accessToken);
	        tAccessToken.setUid(uid);
	        tAccessToken.setUserId(userId);
	    }else{
	        log.debug("缓存中不存在此用户的授权信息！");
	        tAccessToken = tAccessTokenDaoImpl.queryByUid(uid);
	    }
        return tAccessToken;
    }
	
	/**
	 * <br>
	 * @param departmentId
	 * @return
	 */
	public TDepartment find4TDepartment(long departmentId) {
	    TDepartment department = redis.queryDepartment(departmentId);
	    if(department == null){
	        log.debug("缓存中杂志社信息丢失！");
	        department = (TDepartment) tDepartmentDaoImpl.queryById(departmentId);
	    }
        return department;
    }
	
	/**
	 * <br>
	 * @param userId
	 * @return
	 */
	public TUser find4TUser(long userId){
	    TUser tUser = redis.queryTUser(userId);
        if(tUser == null){
            log.debug("缓存中杂志社信息丢失！");
            tUser = (TUser) tUserDaoImpl.queryById(userId);
        }
        return tUser;
	}
	
	/**
	 * 将uid转换为userid
	 * 先查询redis，后查询
	 * 
	 * @param parseLong
	 * @return
	 */
	public long transUid2UserId(long uid) {
	    long userId = 0;
	    // 缓存里读取userId
	    userId = redis.queryUserIdByUid(uid);
	    // 从db中读取
	    if(userId == 0 ){
	        TAccessToken tempTAccessToken = (TAccessToken) tAccessTokenDaoImpl
	                .queryByUid(uid);
            if (tempTAccessToken != null) {
                userId = tempTAccessToken.getUserId();
                //增加uid map userId
                redis.addUserId4Uid(uid, userId);
            }
	    }
        return userId;
    }
	
	/**
	 * 将userid 转换为uid
     * 先查询redis，后查询
	 * @param userId
	 * @return
	 */
	public long transUserId2Uid(long userId) {
        long uid = 0;
        // 缓存里读取userId
        uid = redis.queryUidByUserId(userId);
        // 从db中读取
        if(uid == 0 ){
            TAccessToken tempTAccessToken = (TAccessToken) tAccessTokenDaoImpl.queryById(userId);
            if (userId != 0) {
                uid = tempTAccessToken.getUid();
                redis.addUid4UserId(userId, uid);
            }
        }
        return uid;
    }
	
	/**
	 * <br>获取用户信息</br>
	 * TODO 用户信息是否需要放入缓存？
	 * @param userId
	 * @return
	 */
	public TUser showUserInfo(long userId){
	    Object o = tUserDaoImpl.queryById(userId);
	    if(o != null){
	        TUser user = (TUser) o;
	        log.debug("成功获取用户信息，用户id为："+user.getUserId());
	        return user;
	    }
	    log.debug("系统不存在此用户！用户id为："+userId);
        return null;
	}
	
	
	/**
	 * <br>取出系统推荐标签列表，列表中的标签按照优先级排列，
	 * @return
	 */
	public List<String> showLabels(){
	    log.debug("获取系统推荐标签！");
		ArrayList<String> labels = (ArrayList<String>) redis.querySuggestionsLabels();
		//如果缓存丢失则取db
		if(labels.size()==0){
		    log.debug("缓存丢失系统推荐标签！");
		    BackService backService = (BackService) SpringContextTool.getApplicationContext().getBean("backService");
		    labels = (ArrayList<String>) backService.replaceLabels();
		}
		return labels;
	}
	
	   /**
     * 获取用户已有标签，新用户注册时用户标签已经写入缓存
     * @param userId
     * @return
     */
    public Set<String> showUserLabels(long userId) {
        Set<String> labels = new LinkedHashSet<String>();
        log.debug("开始获取用户标签，用户id为："+userId);
        labels = redis.queryUserLabels(userId);
        // 如果缓存丢失则取db
        if (labels.size() == 0) {
            log.debug("缓存丢失用户标签，用户id为："+userId);
            // 通过dao层从mysql中获取用户标签 labels
            List<TLabel> tLabel = tLabelDaoImpl.queryLabelsByUserId(userId);
            for (TLabel l : tLabel) {
                String label = l.getName();
                labels.add(label);
                //将用户标签写入redis
                redis.addLabel2User(userId, label);
            }
        }
        log.debug("成功获取用户标签，用户id为："+userId+" 用户标签个数为："+labels.size());
        return labels;
    }
    
    /**
     * <br>显示系统总排行榜
     * @return
     */
    public Map<Integer, TDepartment>  showTop(){
        log.debug("查询系统总排行榜！");
        Map<Integer, TDepartment> top4Department = new HashMap<Integer, TDepartment>();
        Map<String, String> top = redis.queryTop();
        log.debug("系统总排行榜大小为："+top.size());
        for(int i = 0 ; i< top.size() ; i++){
            long departmentId = Long.valueOf(top.get(String.valueOf(i)));
            TDepartment department = this.find4TDepartment(departmentId);
            top4Department.put(i, department);
        }
        return top4Department;
    }
	
    /**
     * <br>显示系统总排行榜
     * @return
     */
    public Map<Integer, TDepartment>  showThemeTop(int type){
        log.debug("查询系统分类排行榜！类别为："+type);
        Map<Integer, TDepartment> top4Department = new HashMap<Integer, TDepartment>();
        Map<String, String> top = redis.queryThemeTop(type);
        log.debug("系统统分类排行榜：" +type+"   大小为："+top.size());
        for(int i = 0 ; i< top.size() ; i++){
            long departmentId = Long.valueOf(top.get(String.valueOf(i)));
            TDepartment department = this.find4TDepartment(departmentId);
            top4Department.put(i, department);
        }
        return top4Department;
    }
	
    /**
     * <br> 获取系统表情
     * <br> 步骤：
     * 1.从缓存中取；
     * 2.若缓存丢失，则重置缓存中系统表情。
     * @return
     */
    public List<Emotion> showEmotions(){
        List<Emotion> emotions = redis.queryEmotions();
        if(emotions == null){
            log.debug("缓存中系统表情丢失！");
            BackService backService = (BackService) SpringContextTool.getApplicationContext().getBean("backService");
            emotions = backService.replaceEmotions();
        }
        return emotions;
    }
	

	

	/**
	 * 步骤</br>
	 * 1.将新增标签添加到db和缓存中；</br>
	 * 2.将不存在的标签从db和缓存中删除；</br>
	 * 算法：</br>
	 * 1.将new插入old，若插入成功，则为新标签，否则为老标签，此时将新标签添加到db和缓存中；</br>
	 * 2.之后old为新标签和老标签的合集；</br>
	 * 3.将old插入new，若插入成功，则为老标签，此时将标签从db和缓存中删除</br>
	 * @param userId 用户id
	 * @param labels 修改后的标签列表
	 */
	public  void modifyUserLabels(long userId ,Set<String> labels){
	    try{
	        log.debug("开始更新用户标签，用户id为："+userId);
	        Set<String> old = this.showUserLabels(userId);
	        for(String l:labels){
	            //如果能插入old，表明此标签为新标签
	            boolean fag  = old.add(l);
	            if(fag){
	                tLabelDaoImpl.addUserLabel(userId,l);
	                // 用户标签加入缓存
	                redis.addLabel2User(userId, l);
	                //更新微博用户标签
	                this.createLabel(userId,l);
	            }
	        }
	        //此时old 中包括old+new的所有标签
	        for(String o:old){
	            //如果能插入new ，表明此标签已经被删除。
	            boolean tag = labels.add(o);
	            if(tag){
	                tLabelDaoImpl.deleteLabel(userId,o);
	                // 删除缓存中用户标签
	                redis.delUserLabel(userId,o);
	                //删除微博用户标签
	                this.destroyLabel(userId,o);
	            };
	        }
	    }catch(WeiboException e){
	        log.error("更新用户标签异常！",e);
	    }
	    log.info("更新用户标签，用户系统id为："+userId);
	}
	
	  
	
    


    
   
    
  
	
	/**
	 * 更新微博用户标签
	 * @param userId
	 * @param l
	 * @throws WeiboException 
	 */
	 private void createLabel(long userId, String label) throws WeiboException {
	     long uid = this.transUserId2Uid(userId);
	     String accessToke = redis.queryAccessTokenByUid(uid);
	     WriteTags writeTags = new WriteTags(accessToke);
	     writeTags.create(label);
	    }
	
	 /**
	  * 删除微博用户标签
	  * @param userId 
	  * @param o
	 * @throws WeiboException 
	  */
	 private void destroyLabel(long userId, String tagName) throws WeiboException {
	     long uid = this.transUserId2Uid(userId);
         String accessToke = redis.queryAccessTokenByUid(uid);
         WriteTags writeTags = new WriteTags(accessToke);
         String str = redis.queryWeiboTagTid(tagName);
         if(str == null){
            log.info("缓存中不存在此标签，标签为："+tagName);
         }else{
             long tid = Long.valueOf(str);
             writeTags.destroy(tid); 
         }
         
	    }
	 
	 
	 
	 
	 
	 

	public TUserDaoImpl gettUserDaoImpl() {
		return tUserDaoImpl;
	}

	public void settUserDaoImpl(TUserDaoImpl tUserDaoImpl) {
		this.tUserDaoImpl = tUserDaoImpl;
	}

	public TLabelDaoImpl gettLabelDaoImpl() {
		return tLabelDaoImpl;
	}

	public void settLabelDaoImpl(TLabelDaoImpl tLabelDaoImpl) {
		this.tLabelDaoImpl = tLabelDaoImpl;
	}

	public TDepartmentDaoImpl gettDepartmentDaoImpl() {
		return tDepartmentDaoImpl;
	}

	public void settDepartmentDaoImpl(TDepartmentDaoImpl tDepartmentDaoImpl) {
		this.tDepartmentDaoImpl = tDepartmentDaoImpl;
	}

	public TDepartmentEditorDaoImpl gettDepartmentEditorDaoImpl() {
		return tDepartmentEditorDaoImpl;
	}

	public void settDepartmentEditorDaoImpl(
			TDepartmentEditorDaoImpl tDepartmentEditorDaoImpl) {
		this.tDepartmentEditorDaoImpl = tDepartmentEditorDaoImpl;
	}

	public TAccessTokenDaoImpl gettAccessTokenDaoImpl() {
		return tAccessTokenDaoImpl;
	}

	public void settAccessTokenDaoImpl(TAccessTokenDaoImpl tAccessTokenDaoImpl) {
		this.tAccessTokenDaoImpl = tAccessTokenDaoImpl;
	}

	public TUserSubscribeDaoImpl gettUserSubscribeDaoImpl() {
		return tUserSubscribeDaoImpl;
	}

	public void settUserSubscribeDaoImpl(TUserSubscribeDaoImpl tUserSubscribeDaoImpl) {
		this.tUserSubscribeDaoImpl = tUserSubscribeDaoImpl;
	}

    public TMagazineFinalDaoImpl gettMagazineFinalDaoImpl() {
        return tMagazineFinalDaoImpl;
    }

    public void settMagazineFinalDaoImpl(TMagazineFinalDaoImpl tMagazineFinalDaoImpl) {
        this.tMagazineFinalDaoImpl = tMagazineFinalDaoImpl;
    }


    public TDepartmentCommentDaoImpl gettDepartmentCommentDaoImpl() {
        return tDepartmentCommentDaoImpl;
    }


    public void settDepartmentCommentDaoImpl(
            TDepartmentCommentDaoImpl tDepartmentCommentDaoImpl) {
        this.tDepartmentCommentDaoImpl = tDepartmentCommentDaoImpl;
    }


    public TUserContributeDaoImpl gettUserContributeDaoImpl() {
        return tUserContributeDaoImpl;
    }


    public void settUserContributeDaoImpl(
            TUserContributeDaoImpl tUserContributeDaoImpl) {
        this.tUserContributeDaoImpl = tUserContributeDaoImpl;
    }

    public TMagazineThemeDaoImpl gettMagazineThemeDaoImpl() {
        return tMagazineThemeDaoImpl;
    }

    public void settMagazineThemeDaoImpl(TMagazineThemeDaoImpl tMagazineThemeDaoImpl) {
        this.tMagazineThemeDaoImpl = tMagazineThemeDaoImpl;
    }




    
	
	
	

}
