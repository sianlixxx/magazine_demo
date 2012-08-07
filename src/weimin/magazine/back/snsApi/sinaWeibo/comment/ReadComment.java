/**
 * 
 */
package weimin.magazine.back.snsApi.sinaWeibo.comment;

import weibo4j.Comments;
import weibo4j.Weibo;
import weibo4j.model.CommentWapper;
import weibo4j.model.WeiboException;

/**
 * @author tianhao
 *
 */
public class ReadComment {
    
    private Weibo weibo;
    
    private Comments comments;

    /**
     * 
     */
    public ReadComment(String accessToke) {
        this.weibo = new Weibo();
        this.comments = new Comments();
        weibo.setToken(accessToke);
    }
    
    /**
     * <br>获取当前登录用户的最新评论包括接收到的与发出的
     * @return
     * @throws WeiboException 
     */
    public CommentWapper showCommentTimeline() throws WeiboException{
        return comments.getCommentTimeline();
    }

}
