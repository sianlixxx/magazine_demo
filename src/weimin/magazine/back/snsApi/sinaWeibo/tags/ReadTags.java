/**
 * 
 */
package weimin.magazine.back.snsApi.sinaWeibo.tags;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.Tags;
import weibo4j.Weibo;
import weibo4j.model.Tag;
import weibo4j.model.WeiboException;
import weimin.magazine.back.cache.Redis4Cache;


/**
 * 标签读取接口，包括：
 * 1.读取用户标签
 * @author tianhao
 *
 */
public class ReadTags {
    
    private static final Log log = LogFactory.getLog(ReadTags.class);

	private Weibo weibo;
	
	private Tags tags;
	
	private Redis4Cache redis;
	
	private Set<String> labels = new LinkedHashSet<String>();//用户标签，使用set避免重复；
	
	/**
	 * 
	 */
	public ReadTags(String accessToke) {
		this.weibo = new Weibo();
		this.tags = new Tags();
		this.redis = new Redis4Cache();
		weibo.setToken(accessToke);
	}
	
	/**
	 * 1.读取用户标签，
	 * 2.转化为系统定义的标签列表
	 * 3.存储标签名和微博标签id；
	 * @param uid
	 * @return
	 * @throws WeiboException 
	 */
	public Set<String> showUserTags(long uid) throws WeiboException {
		List<Tag> tagList = getTags(uid);
		for (Tag tag : tagList) {
			String tagName = tag.getValue();
			labels.add(tagName);
			//存储标签名和微博标签id；
			redis.addWeiboTagTid(tagName,tag.getId());
		}
		log.debug("成功获取用户："+uid+" 的微博标签，标签个数为："+labels.size());
		return labels;
	}
	
	/**
	 * 
	 * @param uid
	 * @return
	 * @throws WeiboException 
	 */
	protected List<Tag> getTags(long uid) throws WeiboException{
			List<Tag> tagList =  tags.getTags(Long.toString(uid));
			return tagList;
	}
	
	

}
