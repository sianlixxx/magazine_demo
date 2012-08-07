/**
 * 
 */
package weimin.magazine.back.snsApi.sinaWeibo.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weimin.magazine.back.dao.pojo.TUser;

/**
 * 用户接口，包括：
 * 1.读取用户信息；
 * 
 * @author tianhao
 *
 */
public class ReadUser {
	
    private static final Log log = LogFactory.getLog(ReadUser.class);
    
	private Weibo weibo;
	
	private Users users;
	
	private TUser tUser;

	/**
	 * 
	 */
	public ReadUser(String accessToke) {
		// TODO Auto-generated constructor stub
		this.weibo = new Weibo();
		this.users = new Users();
		this.tUser = new TUser();
		weibo.setToken(accessToke);
		
	}
	
	/**
	 * 1.读取用户信息；
	 * 2.转化为系统定义的用户对象
	 * @param uid
	 * @return
	 * @throws WeiboException
	 */
	public TUser showUserById(long uid) {
		try {
			User user = users.showUserById(Long.toString(uid));
			tUser = Transform2pojo(user);
			log.debug("成功获取新浪微博用户信息，用户微博id为："+uid);
			return tUser;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			log.error("",e);
		}
		return null;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	protected TUser Transform2pojo(User user) {
		tUser.setUid(Long.parseLong(user.getId()));
		tUser.setScreenName(user.getScreenName());
		tUser.setName(user.getName());
		tUser.setProvince(user.getProvince());
		tUser.setCity(user.getCity());
		tUser.setLocation(user.getLocation());
		tUser.setDescription(user.getDescription());
		tUser.setBlogUrl(user.getUrl());//设置用户博客地址
		tUser.setProfileImageUrl(user.getProfileImageUrl());
		tUser.setWeihao(user.getWeihao());
		tUser.setGender(user.getGender());
		tUser.setFollowersCount(user.getFollowersCount());
		tUser.setFriendsCount(user.getFriendsCount());
		tUser.setStatusesCount(user.getStatusesCount());
		tUser.setFavouritesCount(user.getFavouritesCount());
		tUser.setCreatedAt(user.getCreatedAt());//微博创建时间
		tUser.setAllowAllActMsg(user.isallowAllActMsg());
		//tUser.setGeo_enabled(user.is) //TODO 是否带地理坐标，api中未获得
		tUser.setVerified(user.isVerified());
		tUser.setVerifiedType(user.getverifiedType());
		tUser.setAllowAllComment(user.isallowAllComment());
		tUser.setAvatarLarge(user.getavatarLarge());
		tUser.setVerifiedReason(user.getVerifiedReason());
		tUser.setFollowMe(user.isfollowMe());
		tUser.setOnlineStatus(user.getonlineStatus());
		tUser.setBiFollowersCount(user.getbiFollowersCount());
		//tUser.setUserDomain(user.getUserDomain());//TODO设置微博用户个性化URL 
		return tUser;
	}
	
	
	
	

}
