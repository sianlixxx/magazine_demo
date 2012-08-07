package weimin.magazine.back.snsApi.sinaWeibo.tags;

import java.util.Iterator;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import weibo4j.Tags;
import weibo4j.Weibo;
import weibo4j.model.WeiboException;
import weimin.magazine.back.cache.Redis4Cache;


public class WriteTags {
    
    private static final Log log = LogFactory.getLog(WriteTags.class);
    
    private Weibo weibo;
    
    private Tags tags;
    
    private Redis4Cache redis;

    public WriteTags(String accessToke) {
        this.weibo = new Weibo();
        this.tags = new Tags();
        this.redis = new Redis4Cache();
        weibo.setToken(accessToke);
    }
    
    /**
     * 添加用户标签，
     * 1.添加用户标签；
     * 2.存储标签的微博id
     * @param tag
     * @throws WeiboException
     */
    public void create(String label) throws WeiboException{
        log.debug("添加用户标签："+label);
        String tagJSON = tags.createTags(label).toString();
        log.debug("新添加的用户标签为："+tagJSON);
        Gson gson = new Gson();
        String str = tagJSON.substring(1, (tagJSON.length()-1));
        TagRes tag = gson.fromJson(str, new TypeToken<TagRes>(){}.getType());
        //存储标签的微博id
        redis.addWeiboTagTid(label,tag.getTagid());
        
       
    }
    
    /**
     * 删除标签
     * @param tid 标签在新浪微博中的id
     * @throws WeiboException
     */
    public void destroy(long tid) throws WeiboException{
        tags.destoryTag(tid);
        log.debug("删除用户标签："+tid);
    }
    
    
    /**
     * 
     * @param tids
     * @throws WeiboException
     */
    public void batchDestroy(Set<Long> tids) throws WeiboException{
        String ids = "";
        Iterator<Long> iterator =  tids.iterator();
        while (iterator.hasNext()){
            String tid = String.valueOf(iterator.next());
            ids = ids+tid + ",";
        }
        ids = ids.substring(0, (ids.length()-1));
        tags.destroyTagsBatch(ids);
        log.debug("批量删除用户标签，批大小为：" + tids.size());
    }
    
    
    class TagRes{
        String tagid ;
        
        public String getTagid() {
            return tagid;
        }
        public void setTagid(String tagid) {
            this.tagid = tagid;
        }
    }
    
    
}
