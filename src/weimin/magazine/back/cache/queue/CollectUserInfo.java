/**
 * 
 */
package weimin.magazine.back.cache.queue;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import weimin.magazine.back.cache.JedisQueue;
import weimin.magazine.back.cache.message.MSGCollectUserInfo;
import weimin.magazine.back.service.BackService;
import weimin.magazine.back.service.QueueService;
import weimin.magazine.util.*;



/**
 * @author tianhao
 *
 */
public class CollectUserInfo extends QueueService implements  Runnable{
    
    private static final Log log = LogFactory.getLog(CollectUserInfo.class);

    private   BackService backService ;
    
    private JedisQueue<MSGCollectUserInfo> collectUserInfoQueue;
    
   
    
    private boolean flag = true ;

    @Override
    public void run() {
        log.info("启动收集用户信息队列！");
        init();
        while (flag) {
            try {
                log.debug("开始检索收集用户信息队列！");
                MSGCollectUserInfo msg = collectUserInfoQueue.take();
                log.debug("从收集用户信息队列中获得一条微博消息！");
                switch (msg.getType()) {
                //为主编收集微博稿件
                case Constants.QUEUE_COLLECT_USERINFO_TYPE:
                    //延迟加载
                    if(backService == null){
                        ApplicationContext context = SpringContextTool.getApplicationContext();
                        backService = (BackService) context.getBean("backService");
                    }
                    backService.collectContributeInWeibo(msg.getUserId());
                break;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("", e);
            } 
        }
    }

    /**
     * <br> 队列初始化
     */
    private void init() {
        collectUserInfoQueue = JedisQueue.newQ(MSGCollectUserInfo.class);
    }
}
