package weimin.magazine.back.snsApi.sinaWeibo.comment;

import weibo4j.Comments;
import weibo4j.Weibo;
import weibo4j.model.WeiboException;

public class WriteComment {
    
private Weibo weibo;
    
    private Comments comments;

    public WriteComment(String accessToke) {
        this.weibo = new Weibo();
        this.comments = new Comments();
        weibo.setToken(accessToke);
    }
    
    /**
     * <br>对一条微博进行评论
     * @param text   评论内容，必须做URLencode，内容不超过140个汉字
     * @param mid 需要评论的微博ID
     * @throws WeiboException
     */
    public void createComment(String text ,String mid) throws WeiboException{
        comments.createComment(text, mid);
    }

}
