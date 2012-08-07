/**
 * 
 */
package weimin.magazine.back.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import weibo4j.model.CommentWapper;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.exception.SinaWeiboExpiredTokenException;
import weimin.magazine.back.snsApi.sinaWeibo.comment.ReadComment;
import weimin.magazine.back.snsApi.sinaWeibo.comment.WriteComment;
import weimin.magazine.back.snsApi.sinaWeibo.friend.ReadFriend;
import weimin.magazine.back.snsApi.sinaWeibo.weibo.ReadWeibo;
import weimin.magazine.back.snsApi.sinaWeibo.weibo.WriteWeibo;
import weimin.magazine.back.vo.Weibo;
import weimin.magazine.util.ErrorCodeConstant;

/**
 * @author tianhao
 *
 */
public class WeiboService {

    private static final Log log = LogFactory.getLog(WeiboService.class);
	
	/**
	 * <br> 获取用户最新发表的微博列表
	 * @param tAccessToken
	 * @return StatusWapper 
	 * 			list of the user_timeline:用户微博（statuses）的list，
	 */
	public StatusWapper showUserTimeline(TAccessToken tAccessToken)  {
		ReadWeibo readWeibo = new ReadWeibo(tAccessToken.getAccessToken());
		StatusWapper statusWapper = null;
		try {
			statusWapper = readWeibo.showUserTimeline();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusWapper;
	}
	
	/**
	 * <br>根据微博ID获取单条微博内容
	 * @param id 
	 * 		需要获取的微博ID。
	 * @param tAccessToken
	 * @return Status
	 */
	public Status showWeibo(TAccessToken tAccessToken,String id){
		ReadWeibo readWeibo = new ReadWeibo(tAccessToken.getAccessToken());
		Status status = new Status();
		try {
			status = readWeibo.showStatus(id);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * <br>获取当前登录用户及其所关注用户的最新微博消息。<br/>
	 * <br>和用户登录 http://weibo.com 后在“我的首页”中看到的内容相同。
	 * @param tAccessToken
	 * @return StatusWapper 用户微博（statuses）的list，
	 * 
	 */
	public StatusWapper showFriendsTimeline(TAccessToken tAccessToken) {
		ReadWeibo readWeibo = new ReadWeibo(tAccessToken.getAccessToken());
		StatusWapper statusWapper = null;
		try {
			statusWapper = readWeibo.getFriendsTimeline();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusWapper;
	}
	
	/**
	 * <b>获取最新的提到登录用户的微博列表，即@我的微博</br>
	 * @param tAccessToken
	 * @return
	 */
	   public StatusWapper showMentions(TAccessToken tAccessToken){
	        ReadWeibo readWeibo = new ReadWeibo(tAccessToken.getAccessToken());
	        try {
                return readWeibo.showMentions();
            } catch (WeiboException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
	    }

	/**
	 *  <br>发布一条新微博<br/>
	 * @param tAccessToken
	 * @param status
	 * 			annotations中记录系统规则，
	 * 			lat，longs为地理坐标
	 * 			text为微博内容
	 * @return
	 * @throws SinaWeiboExpiredTokenException 
	 */
	public Status publishWeibo(Weibo weibo) throws SinaWeiboExpiredTokenException {
		WriteWeibo writeWeibo = new WriteWeibo(weibo.gettAccessToken().getAccessToken());
		Status status = new Status();
		String text = weibo.getText();
		float lat =  weibo.getLat();
		float longs = weibo.getLongs();
		String locPicUrl = weibo.getLocPicUrl();
		byte[] pic = weibo.getPic();
		String annotations = weibo.getAnnotations();
		try {
			//长微博转图片
			if(text.length()>=140){
				if(StringUtils.isNotEmpty(annotations) ||( lat > 0 && longs > 0 )){
					status = writeWeibo.UploadLongStatus(text,lat,longs,annotations);
				}else{
					status = writeWeibo.UploadLongStatus(text);
				}
				return status;
			}
			//上传本地图片，发图片微博
			if(StringUtils.isNotEmpty(locPicUrl)){
			    if(StringUtils.isNotEmpty(annotations) ||( lat > 0 && longs > 0 )){
                    status = writeWeibo.UploadLocPicStatus(text,locPicUrl,lat,longs,annotations);
                }else{
                    status = writeWeibo.UploadLocPicStatus(text,locPicUrl);
                }
			    return status;
			}
			//客户端上传图片，发图片微博
			if(pic != null ){
			    if(StringUtils.isNotEmpty(annotations) ||( lat > 0 && longs > 0 )){
                    status = writeWeibo.UploadPicStatus(text,pic,lat,longs,annotations);
                }else{
                    status = writeWeibo.UploadPicStatus(text,pic);
                }
                return status;
			}
			
			//发普通微博
			{
			    if(StringUtils.isNotEmpty(annotations)||( lat > 0 && longs > 0 )){
	                status = writeWeibo.UpdateStatus(text,lat,longs,annotations);
	            }else{
	                status = writeWeibo.UpdateStatus(text);
	            }
	            return status ;
	            
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WeiboException e) {
		    int errorCode = ErrorCodeConstant.trancErrorCode(e.getErrorCode());
		    if(errorCode == ErrorCodeConstant.SINA_WEIBO_EXPIRED_TOKEN){
		        throw new SinaWeiboExpiredTokenException("需要新浪微博用户授权！");
		    }else{
		        log.error(e.getMessage(), e);
		    }
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status ;
	}
	
	/**
	 * <br>转发微博
	 * @param tAccessToken
	 * @param id
	 * @throws WeiboException
	 */
	public void repostWeibo(TAccessToken tAccessToken,String id) {
	    WriteWeibo writeWeibo = new WriteWeibo(tAccessToken.getAccessToken());
	    try {
            writeWeibo.destroy(id);
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	/**
	 * <br>删除微博
	 * @param tAccessToken
	 * @param id
	 * @throws WeiboException
	 */
	public void destoryWeibo(TAccessToken tAccessToken,String id) {
	    WriteWeibo writeWeibo = new WriteWeibo(tAccessToken.getAccessToken());
	    try {
            writeWeibo.destroy(id);
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	/**
	 * <br>获取当前登录用户的最新评论包括接收到的与发出的
	 * @param tAccessToken
	 * @return
	 */
    public CommentWapper showCommentTimeline(TAccessToken tAccessToken) {
        ReadComment readComment = new ReadComment(tAccessToken.getAccessToken());
        try {
            return readComment.showCommentTimeline();
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * <br>获取用户的关注列表
     * @param tAccessToken
     * @return
     */
    public UserWapper showFriendsByID(TAccessToken tAccessToken){
        ReadFriend readFriend = new ReadFriend(tAccessToken.getAccessToken());
        try {
            return readFriend.showFriendsByID(tAccessToken.getUid());
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
	
    /**
     * <br>获取用户的粉丝列表
     * @param tAccessToken
     * @return
     */
    public UserWapper showFollowersById(TAccessToken tAccessToken){
        ReadFriend readFriend = new ReadFriend(tAccessToken.getAccessToken());
        try {
            return readFriend.showFollowersById(tAccessToken.getUid());
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     *  /**
     * <br>对一条微博进行评论
     * @param tAccessToken
     * @param text   评论内容，必须做URLencode，内容不超过140个汉字
     * @param mid 需要评论的微博ID
     */
    public void createComment(TAccessToken tAccessToken,String text ,String mid){
        WriteComment writeComment = new WriteComment(tAccessToken.getAccessToken());
        try {
            writeComment.createComment(text, mid);
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
