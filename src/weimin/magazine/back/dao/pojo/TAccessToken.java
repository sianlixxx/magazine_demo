/**
 * 
 */
package weimin.magazine.back.dao.pojo;

/**
 * @author tianhao
 *
 */
public class TAccessToken {
	
	private  Long userId;//系统userid
	
	private String accessToken;//用于调用access_token，接口获取授权后的access token
	
	private String expireIn;//access_token的生命周期。
	
	private String refreshToken;//
	
	private Long uid;//当前授权用户的UID

	/**
	 * 
	 */
	public TAccessToken() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(String expireIn) {
		this.expireIn = expireIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

	
	
	

}
