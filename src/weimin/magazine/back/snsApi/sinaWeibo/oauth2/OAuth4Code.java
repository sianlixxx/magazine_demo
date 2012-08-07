package weimin.magazine.back.snsApi.sinaWeibo.oauth2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weimin.magazine.back.dao.pojo.TAccessToken;


/**
 * oauth2认证，步骤：
 * 1.引导用户到授权页；
 * 2.用户授权后获得授权code；
 * 3.根据code获取授权令牌accesstoken
 * @author tianhao
 *
 */
public class OAuth4Code {
	
    private static final Log log = LogFactory.getLog(OAuth4Code.class);
    
	private Oauth oauth;
	
	private AccessToken accessToken;//微博返回的accesstoken，仅有uid；
	
	private TAccessToken tAccessToken;//系统保存的accesstoken，带有userid和uid；
	
	public OAuth4Code(){
		this.oauth = new Oauth();
		this.tAccessToken = new TAccessToken();
		
	}
	
	/**
	 * 引导需要授权的用户到应用地址
	 */
	public String  openURL(){
		//TODO 默认错误页面
		String url = "";
		try {
			url = oauth.authorize("code");
			
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	
	/**
	 * Oauth接口-
	 * 1.通过code获取AccessToken；
	 * 2.转化为应用定义的令牌对象；
	 * 
	 * @return AccessToken
	 */
	public TAccessToken getAccessTokenByCode(String code){
		try{
			accessToken = oauth.getAccessTokenByCode(code);
			log.debug("成功获取用户授权信息！");
			tAccessToken = Transform2pojo(accessToken);
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				log.error("Unable to get the access token.");
			}else{
				log.error("", e);
			}
		}
		return tAccessToken;
	}

	/**
	 * 转换后的tAccessToken中userid为空；
	 * @param accessToken
	 * @return
	 */
	protected TAccessToken Transform2pojo(AccessToken accessToken) {
		tAccessToken.setAccessToken(accessToken.getAccessToken());
		tAccessToken.setExpireIn(accessToken.getExpireIn());
		tAccessToken.setRefreshToken(accessToken.getRefreshToken());
		tAccessToken.setUid(Long.parseLong(accessToken.getUid()));
		log.debug("成功转换用户授权信息！");
		return tAccessToken;
	}
}
