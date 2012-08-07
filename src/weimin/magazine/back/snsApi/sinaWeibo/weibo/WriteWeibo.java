/**
 * 
 */
package weimin.magazine.back.snsApi.sinaWeibo.weibo;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.http.ImageItem;
import weibo4j.model.Emotion;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import weimin.magazine.util.Tools;

/**
 * @author tianhao
 *
 */
public class WriteWeibo {
    
    private static final Log log = LogFactory.getLog(WriteWeibo.class);
	
	private Weibo weibo;
	
	private Timeline timeline;

	/**
	 * 
	 */
	public WriteWeibo(String accessToke) {
		this.weibo = new Weibo();
		this.timeline = new Timeline();
		weibo.setToken(accessToke);
	}
	
	/**
	 * 发布一条新微博
	 * 
	 * @param text
	   *            要发布的微博文本内容，内容不超过140个汉字
	 * @return Status
	 */
	public Status UpdateStatus(String text) throws UnsupportedEncodingException, WeiboException{
	    Status status = timeline.UpdateStatus(text);
		log.debug("成功发布一条微博，微博id为："+status.getId()+" 微博内容为："+text);
		return status;
	}
	
	/**
	 * 发布一条新微博
	 * 
	 * @param text
	 *            要发布的微博文本内容，内容不超过140个汉字
	 * @param lat
	 *            纬度，有效范围：-90.0到+90.0，+表示北纬，默认为0.0。
	 * @param long 经度，有效范围：-180.0到+180.0，+表示东经，默认为0.0。
	 * @param annotations
	 *            元数据，主要是为了方便第三方应用记录一些适合于自己使用的信息，每条微博可以包含一个或者多个元数据，
	 *            必须以json字串的形式提交，字串长度不超过512个字符，具体内容可以自定
	 * @return Status
	 */
	public Status UpdateStatus(String text, Float lat, Float longs,
			String annotations) throws UnsupportedEncodingException, WeiboException{
		Status status =  timeline.UpdateStatus(text,  lat,  longs, annotations);
		log.debug("成功发布一条微博，微博id为："+status.getId()+" 微博内容为："+text+"  annotations:"+annotations);
		return status;
	}
	
	/**
	 * 长文本转图片并发布一条新微博
	 * 	仅支持JPEG、GIF、PNG格式，图片大小小于5M。
	 * @param text
	 *            要发布的微博文本内容，内容不超过140个汉字
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 */
	public Status UploadLongStatus(String text) throws WeiboException, IOException{
		//TODO将string转换为本地图片
		String picAddr = Tools.String2Pic(text);
		//TODO 提取部分内容作为文本
		text = Tools.summary(text);
		//读取本地图片，
		byte[] pic= Tools.readFileImage(picAddr);
		ImageItem item=new ImageItem("pic",pic);
		Status status = timeline.UploadStatus(text, item);
		log.debug("成功发布一条长微博，微博id为："+status.getId()+" 微博内容为："+text+"  图片微博地址为：" + status.getOriginalPic());
        return status;
	}

	/**
	 * 长文本转图片并发布一条新微博
	 * 
	 * @param status
	 *            要发布的微博文本内容，必须做URLencode，内容不超过140个汉字
	 * @param lat
	 *            纬度，有效范围：-90.0到+90.0，+表示北纬，默认为0.0。
	 * @param long 经度，有效范围：-180.0到+180.0，+表示东经，默认为0.0。
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 */
	public Status UploadLongStatus(String text, Float lat, Float longs,
			String annotations) throws IOException, WeiboException {
		// TODO 将string转换为本地图片
		String picAddr = Tools.String2Pic(text);
		// TODO 提取部分内容作为文本
		text = Tools.summary(text);
		// 读取本地图片，
		byte[] pic = Tools.readFileImage(picAddr);
		ImageItem item = new ImageItem("pic", pic);
		Status status = timeline.UploadStatus(text, item, lat, longs,annotations);
		log.debug("成功发布一条长微博，微博id为："+status.getId()+" 微博内容为："+text+"  图片微博地址为："+status.getOriginalPic()+"  annotations:"+annotations);
        return status;
	}
	
	/**
	 * <br>上传本地图片，发布微博
	 * 要发布的微博文本内容，必须做URLencode，内容不超过140个汉字
	 * @param text
	 * @param locPicUrl
	 * @return
	 * @throws IOException
	 * @throws WeiboException
	 */
    public Status UploadLocPicStatus(String text, String locPicUrl) throws IOException, WeiboException {
     // 读取本地图片，
        byte[] pic = Tools.readFileImage(locPicUrl);
        ImageItem item=new ImageItem("pic",pic);
        text = java.net.URLEncoder.encode( text,"utf-8");
        Status status = timeline.UploadStatus(text, item);
        log.debug("成功发布一条服务端图片微博，微博id为："+status.getId()+" 微博内容为："+text +"  图片微博地址为：" + status.getOriginalPic());
        return status;
    }
	 
    /**
     * 上传本地图片，发布微博
     * @param text
     * @param locPicUrl
     * @param lat
            *            纬度，有效范围：-90.0到+90.0，+表示北纬，默认为0.0。
     * @param long 
            *            经度，有效范围：-180.0到+180.0，+表示东经，默认为0.0。
     * @param annotations
     * @return
     * @throws IOException 
     * @throws WeiboException 
     */
    public Status UploadLocPicStatus(String text, String locPicUrl,Float lat, Float longs,
            String annotations) throws IOException, WeiboException {
     // 读取本地图片，
        byte[] pic = Tools.readFileImage(locPicUrl);
        ImageItem item=new ImageItem("pic",pic);
        Status status = timeline.UploadStatus(text, item, lat, longs,annotations);
        log.debug("成功发布一条服务端图片微博，微博id为："+status.getId()+" 微博内容为："+text+"  图片微博地址为："+status.getOriginalPic()+"  annotations:"+annotations);
        return status;
    }
    
    /**
     * 
     * @param text
     * @param pic
     * @param lat
     * @param longs
     * @param annotations
     * @return
     * @throws WeiboException
     */
    public Status UploadPicStatus(String text, byte[] pic, Float lat,
            Float longs, String annotations) throws WeiboException {
        ImageItem item = new ImageItem("pic",pic);
        Status status = timeline.UploadStatus(text, item, lat, longs,annotations);
        log.debug("成功发布一条客户端图片微博，微博id为："+status.getId()+" 微博内容为："+text+"  图片微博地址为："+status.getOriginalPic()+"  annotations:"+annotations);
        return status;
    }

    /**
     * 
     * @param text
     * @param pic
     * @return
     * @throws WeiboException
     */
    public Status UploadPicStatus(String text, byte[] pic) throws WeiboException {
        ImageItem item=new ImageItem("pic",pic);
        Status status = timeline.UploadStatus(text, item);
        log.debug("成功发布一条客户端图片微博，微博id为："+status.getId()+" 微博内容为："+text +"  图片微博地址为：" + status.getOriginalPic());
        return status;
    }
    
    /**
     * <br>删除一条微博</br>
     * @param id 微博id或者mid
     * @throws WeiboException
     */
    public void destroy(String id) throws WeiboException{
        timeline.Destroy(id);
        log.debug("成功删除一条微博，微博id为："+id);
    }
    
    /**
     * <br> 转发一条新微博
     * @param id 要转发的微博ID
     * @throws WeiboException
     */
    public void repost(String id) throws WeiboException{
        timeline.Repost(id);
        log.debug("成功转发一条微博，微博id为："+id);
    }
	
    /**
     * <br>获取微博表情
     * @return
     * @throws WeiboException
     */
    public  List<Emotion> showEmotions() throws WeiboException{
        List<Emotion> emotions = timeline.getEmotions();
        log.debug("成功获取微博表情！");
        return emotions;
    }

}
