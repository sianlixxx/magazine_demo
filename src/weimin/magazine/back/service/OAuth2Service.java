package weimin.magazine.back.service;



import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.model.WeiboException;
import weimin.magazine.back.cache.JedisQueue;
import weimin.magazine.back.cache.message.MSGCollectUserInfo;
import weimin.magazine.back.cache.message.MSGWeibo;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.dao.pojo.TUser;
import weimin.magazine.back.snsApi.sinaWeibo.oauth2.OAuth4Code;
import weimin.magazine.back.snsApi.sinaWeibo.tags.ReadTags;
import weimin.magazine.back.snsApi.sinaWeibo.user.ReadUser;
import weimin.magazine.back.vo.Weibo;
import weimin.magazine.util.Constants;
import weimin.magazine.util.Tools;


public class OAuth2Service extends BaseService{
	
	private static final Log log = LogFactory.getLog(OAuth2Service.class);

	private OAuth4Code oAuth4Code = new OAuth4Code();

	private TUser tUser = new TUser();

	private ReadUser readUser;

	private ReadTags readTags;

	private TAccessToken tAccessToken;

	private JedisQueue<MSGCollectUserInfo> collectUserInfoQueue = JedisQueue.newQ(MSGCollectUserInfo.class);
	
	private JedisQueue<MSGWeibo> publishWeiboQueue = JedisQueue.newQ(MSGWeibo.class);

	

	/**
	 * 引导需要授权的用户到应用地址
	 * 
	 * 
	 */
	public String  openURL() {
		return oAuth4Code.openURL();
	}

	/**
	 * TODO 拆分注册
	 * 1.通过code获得用户授权accesstoken； 
	 * 2.判断用户是否为新用户，先查询redis，若不存在则查询db？
	 * 3.1若是新用户则注册用户，
	 *  3.2否则更新用户taccesstoken TODO?写入缓存，未用队列？
	 * 
	 * @param code
	 * @throws WeiboException 
	 */
	public TAccessToken oAuthByCode(String code)  {
        try {
            // 通过code获得AccessToken，
            // 转换AccessToken为taccesstoken，注意转换后的tAccessToken中userid为空；
            tAccessToken = oAuth4Code.getAccessTokenByCode(code);
            log.info("新浪用户" + tAccessToken.getUid() + "授权成功！");
            long uid = tAccessToken.getUid();
            // 判断用户是否为新用户，
            long userId = this.transUid2UserId(uid);
            if (userId != 0) {
                log.debug("新浪用户" + tAccessToken.getUid() + "再次登陆系统！");
                this.updateTuser(userId);
                // 设置tAccessToken中系统用户id，并更新tAccessToken
                tAccessToken.setUserId(userId);
                this.updateTaccessToken(tAccessToken);
                // 发送系统消息，开始分析用户，
                MSGCollectUserInfo msg = new MSGCollectUserInfo();
                msg.setUserId(userId);
                collectUserInfoQueue.push(msg);
                return tAccessToken;
            } 
            return tAccessToken;
        }catch(Exception e){
            log.error(" 授权认证失败！"+e);
	    }
        return tAccessToken;
	}
	
	public TAccessToken createSYSUser(TAccessToken tAccessToken){
        try {
         // 注册为系统新用户
            log.debug("新浪用户" + tAccessToken.getUid() + "首次登陆系统！");
            // 新用户，获取微博用户信息，并生成系统用户，
            long  userId = this.createUser(tAccessToken);
            // 设置taccesstoken的userid，将taccesstoken存入db，并写入缓存
            tAccessToken.setUserId(userId);
            // 设置taccesstoken的userid，将taccesstoken存入db，并写入缓存
            this.createTAccessToken(tAccessToken);
            // 为用户创建标签，保存在db中，同时写入缓存；
            this.createUserLabels(userId, tAccessToken.getUid());
            // 发送广播消息，
            Weibo weibo = new Weibo();
            weibo.settAccessToken(tAccessToken);
            weibo.setText(Tools.createText(weibo, Constants.BROADCAST_TYPE_REGEDIT_USER));
            MSGWeibo msg = new MSGWeibo(weibo);
            publishWeiboQueue.push(msg);
        } catch (WeiboException e) {
            log.error("注册系统用户失败！ "+e);
        }
        return tAccessToken;
	}
	
	
	
	
	

	
	
	

	/**
	 * 
	 * @param uid
	 */
	private void updateTuser(long userId) {
		// 更新老用户最近登录时间
		tUser = this.find4TUser(userId);
		tUser.setLastLoginTime(Tools.getDate());
		//
		tUserDaoImpl.update(tUser);
		redis.addTuser(tUser);
		log.debug("更新系统用户："+userId+" 用户信息。");
	}


	
	
	/**
	 * 更新tAccessToken，获取老tAccessToken中的userid
	 * @param oldtAccessToken
	 */
	private void updateTaccessToken(TAccessToken accessToken) {
		log.debug("更新系统用户："+accessToken.getUserId()+" 授权信息。");
		// 更新taccesstoken
		tAccessTokenDaoImpl.update(tAccessToken);
		// 更新redis缓存
		redis.addAccessTokenByUid(tAccessToken.getUid(), tAccessToken.getAccessToken());
	}

	/**
     * 新用户，获取微博用户信息，并生成系统用户，
     * 
     * @param tAccessToken2
     * @return 系统用户userId
     */
    private long createUser(TAccessToken tAccessToken) {
        log.debug("开始注册新浪用户"+tAccessToken.getUid() +" 为系统用户");
        readUser = new ReadUser(tAccessToken.getAccessToken());
        tUser = readUser.showUserById(tAccessToken.getUid());
        long userId = (Long) tUserDaoImpl.insert(tUser);
        log.info("注册 新浪用户"+tAccessToken.getUid() +" 为系统用户已成功！" + "系统用户id为："+ userId);
        //
        redis.addTuser(tUser);
        // 缓存中记录映射关系
        redis.addUserId4Uid(tAccessToken.getUid(),tAccessToken.getUserId());
        redis.addUid4UserId(tAccessToken.getUserId(), tAccessToken.getUid());
        return userId;
    }

    /**
     * 
     * @param userId
     * @param tAccessToken
     */
    private TAccessToken createTAccessToken( TAccessToken tAccessToken) {
        log.debug("开始新建系统用户："+tAccessToken.getUserId()+" 授权信息。");
        // 新建taccesstoken
        tAccessTokenDaoImpl.insert(tAccessToken);
        // 将taccesstoken写入redis缓存
        redis.addAccessTokenByUid(tAccessToken.getUid(),
                tAccessToken.getAccessToken());
        return tAccessToken;
    }
    
	/**
	 * 为用户创建标签，保存在db中，每个label对应db中的一条记录；同时写入缓存；
	 * 
	 * @param labels
	 * @param userId
	 * @throws WeiboException 
	 */
	private void createUserLabels(long userId, long uid) throws WeiboException {
		log.debug("开始收集系统用户："+ userId +" 的微博标签。");
		// 获取用户标签,保存在db中，同时写入缓存；
		readTags = new ReadTags(tAccessToken.getAccessToken());
		Set<String> labels = readTags.showUserTags(uid);
		// 为用户创建标签，保存在db中，写入redis缓存
		for (String label : labels) {
			tLabelDaoImpl.addUserLabel(userId, label);
			redis.addLabel2User(userId, label);
		}
	}

}
