/**
 * 
 */
package weimin.magazine.back.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import weimin.magazine.back.cache.queue.CollectUserInfo;
import weimin.magazine.back.cache.queue.PublishWeiboQueue;


/**
 * @author tianhao
 *
 */
public class QueueService  extends BackService implements InitializingBean{
    
    private static final Log log = LogFactory.getLog(QueueService.class);

    public QueueService() {
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("回调方法启动消息队列！");
        PublishWeiboQueue weiboQueue = new PublishWeiboQueue();
        CollectUserInfo collectUserInfo = new CollectUserInfo();
        
        new Thread(weiboQueue).start();
        new Thread(collectUserInfo).start();
        
        log.debug(" 回调方法结束！");
    }
    
}
