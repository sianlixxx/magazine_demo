package weimin.magazine.back.snsApi.sinaWeibo.weibo;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

/**
 * 微博接口，包括：
 * 1.获取某个用户最新发表的微博列表
 * 2.根据微博ID获取单条微博内容
 * 3.获取当前登录用户及其所关注用户的最新微博消息；
 * 
 * @author tianhao
 *
 */
public class ReadWeibo {
	
	private Weibo weibo;
	
	private Timeline timeline;
	
	public ReadWeibo(String accessToke){
		this.weibo = new Weibo();
		this.timeline = new Timeline();
		weibo.setToken(accessToke);
	}
	
	
	/**
	 *  获取某个用户最新发表的微博列表
	 * @return StatusWapper 
	 * 			list of the user_timeline:用户微博（statuses）的list，
	 * @throws WeiboException
	 */
	public StatusWapper showUserTimeline() throws WeiboException {
		return timeline.getUserTimeline();
	}
	
	/**
	 * 根据微博ID获取单条微博内容
	 * @param id 
	 * 		需要获取的微博ID。
	 * @return Status
	 * @throws WeiboException
	 */
	public Status showStatus(String id)throws WeiboException{
		return timeline.showStatus(id);
	}
	
	/**
	 * 获取当前登录用户及其所关注用户的最新微博消息。<br/>
	 * 和用户登录 http://weibo.com 后在“我的首页”中看到的内容相同。
	 * @return StatusWapper 用户微博（statuses）的list，
	 * @throws WeiboException
	 */
	public StatusWapper getFriendsTimeline() throws WeiboException {
		return timeline.getFriendsTimeline();
	}
	
	/**
	 *  获取当前登录用户及其所关注用户的最新微博
	 * @param baseAPP 是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0。
	 * @param feature 过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
	 * @param paging  相关分页参数 ,返回结果的页码，默认为1。
	 * @return
	 * @throws WeiboException
	 */
	public StatusWapper getFriendsTimeline(Integer baseAPP, Integer feature,
			Paging paging) throws WeiboException {
		return timeline.getFriendsTimeline( baseAPP,  feature, paging);
	}


	/**
	 * <br>获取最新的提到登录用户的微博列表，即@我的微博</br>
	 * @return
	 * @throws WeiboException
	 */
    public StatusWapper showMentions() throws WeiboException {
        return timeline.getMentions();
    }
	
	

}
