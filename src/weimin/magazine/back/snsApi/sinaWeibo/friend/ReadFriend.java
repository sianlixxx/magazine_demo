/**
 * 
 */
package weimin.magazine.back.snsApi.sinaWeibo.friend;


import weibo4j.Friendships;
import weibo4j.Weibo;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

/**
 * @author tianhao
 *
 */
public class ReadFriend {
    
    private Weibo weibo;
    
    private Friendships friendships;

    /**
     * 
     */
    public ReadFriend(String accessToke) {
        this.weibo = new Weibo();
        this.friendships = new Friendships();
        weibo.setToken(accessToke);
    }
    
    /**
     * <br>获取用户的关注列表
     * @param uid
     * @return
     * @throws WeiboException
     */
    public UserWapper showFriendsByID(long uid) throws WeiboException{
        return friendships.getFriendsByID(String.valueOf(uid));
    }
    
    /**
     * <br>获取用户的粉丝列表
     * @param uid
     * @return
     * @throws WeiboException
     */
    public UserWapper showFollowersById(long uid) throws WeiboException{
        return friendships.getFollowersById(String.valueOf(uid));
    }

}
