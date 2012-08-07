/**
 * 
 */
package weimin.magazine.back.cache.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weimin.magazine.back.cache.JedisQueue;
import weimin.magazine.back.cache.message.MSGWeibo;
import weimin.magazine.back.exception.SinaWeiboExpiredTokenException;
import weimin.magazine.back.service.WeiboService;




/**
 * @author tianhao
 *
 */
public class PublishWeiboQueue implements  Runnable {
    
    private static final Log log = LogFactory.getLog(PublishWeiboQueue.class);
    
    private JedisQueue<MSGWeibo> sendWeiboQueue ;
    
    private WeiboService weiboService ;
    
    private boolean flag = true ;

    /**
     * 
     * 
     */
    public PublishWeiboQueue() {
    }

    @Override
    public void run() {
        log.info("启动微博队列！");
        init();
        while (flag) {
            try {
                log.debug("开始检索微博队列！");
                MSGWeibo msgWeibo = sendWeiboQueue.take();
                log.debug("从微博队列中获得一条微博消息！");
                //发布此微博
                weiboService.publishWeibo(msgWeibo.getWeibo());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("", e);
            } catch (SinaWeiboExpiredTokenException e) {
                log.error("", e);
            }
        }
    }

    private void init() {
        weiboService = new WeiboService();
        sendWeiboQueue = JedisQueue.newQ(MSGWeibo.class);
    }

}
