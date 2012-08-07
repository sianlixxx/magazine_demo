/**
 * 
 */
package weimin.magazine.back.dao.pojo;

import java.util.Date;






/**
 * 
 * @author tianhao
 *
 */
public class TUser {
	
	private Long userId;					//系统用户id ****
	
	private Long uid;                      //新浪用户UID
	
	private String screenName;            //微博昵称
	
	private String name;                  //友好显示名称，如Bill Gates,名称中间的空格正常显示(此特性暂不支持)
	
	private int province;                 //省份编码（参考省份编码表）
	
	private int city;                     //城市编码（参考城市编码表）
	
	private String location;              //地址
	
	private String description;           //个人描述
	
	private String blogUrl;                   //用户博客地址
	
	private String profileImageUrl;       //自定义图像
	
	private String domain;            //用户个性化URL
	
	private String weihao;				  //微號
	
	private String gender;                //性别,m--男，f--女,n--未知
	
	private int followersCount;           //粉丝数
	
	private int friendsCount;             //关注数
	
	private int statusesCount;            //微博数
	
	private int favouritesCount;          //收藏数
	
	private Date createdAt;               //创建时间
	
	private boolean verified;             //加V标示，是否微博认证用户
	
	private int verifiedType;             //认证类型
	
	private boolean allowAllActMsg;       //是否允许所有人给我发私信
	
	private boolean allowAllComment;      //是否允许所有人对我的微博进行评论
	
	private String avatarLarge;           //大头像地址
	
	private boolean followMe;             //此用户是否关注我
	
	private int onlineStatus;             //用户在线状态
	
	
	
	private int biFollowersCount;         //互粉数
	
	private String verifiedReason; 		//认证原因
	
	private boolean geoEnabled;			//是否带地理坐标
	
	private int submissionCount;			//提交数  ******
	
	private int subscribeCount;			//订阅数  ****** 
	
	private int participateCount;			//参与编辑数  ****** 
	
	private boolean createEnabled;			//是否允许创建编辑部 :0为未创建，1为已创建  ******
	
	private String userDomain;				//微博用户地址
	
	private int  userSource;				//用户评分  ****** 
	
	private Date lastLoginTime ;			//最后登录时间   ******

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getWeihao() {
		return weihao;
	}

	public void setWeihao(String weihao) {
		this.weihao = weihao;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}

	public int getStatusesCount() {
		return statusesCount;
	}

	public void setStatusesCount(int statusesCount) {
		this.statusesCount = statusesCount;
	}

	public int getFavouritesCount() {
		return favouritesCount;
	}

	public void setFavouritesCount(int favouritesCount) {
		this.favouritesCount = favouritesCount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public int getVerifiedType() {
		return verifiedType;
	}

	public void setVerifiedType(int verifiedType) {
		this.verifiedType = verifiedType;
	}

	public boolean isAllowAllActMsg() {
		return allowAllActMsg;
	}

	public void setAllowAllActMsg(boolean allowAllActMsg) {
		this.allowAllActMsg = allowAllActMsg;
	}

	public boolean isAllowAllComment() {
		return allowAllComment;
	}

	public void setAllowAllComment(boolean allowAllComment) {
		this.allowAllComment = allowAllComment;
	}

	public String getAvatarLarge() {
		return avatarLarge;
	}

	public void setAvatarLarge(String avatarLarge) {
		this.avatarLarge = avatarLarge;
	}

	public boolean isFollowMe() {
		return followMe;
	}

	public void setFollowMe(boolean followMe) {
		this.followMe = followMe;
	}

	public int getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public int getBiFollowersCount() {
		return biFollowersCount;
	}

	public void setBiFollowersCount(int biFollowersCount) {
		this.biFollowersCount = biFollowersCount;
	}

	public String getVerifiedReason() {
		return verifiedReason;
	}

	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}

	public boolean isGeoEnabled() {
		return geoEnabled;
	}

	public void setGeoEnabled(boolean geoEnabled) {
		this.geoEnabled = geoEnabled;
	}

	public int getSubmissionCount() {
		return submissionCount;
	}

	public void setSubmissionCount(int submissionCount) {
		this.submissionCount = submissionCount;
	}

	public int getSubscribeCount() {
		return subscribeCount;
	}

	public void setSubscribeCount(int subscribeCount) {
		this.subscribeCount = subscribeCount;
	}

	public int getParticipateCount() {
		return participateCount;
	}

	public void setParticipateCount(int participateCount) {
		this.participateCount = participateCount;
	}

	public boolean isCreateEnabled() {
		return createEnabled;
	}

	public void setCreateEnabled(boolean createEnabled) {
		this.createEnabled = createEnabled;
	}

	public String getUserDomain() {
		return userDomain;
	}

	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}

	public int getUserSource() {
		return userSource;
	}

	public void setUserSource(int userSource) {
		this.userSource = userSource;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
	
	

	
}
