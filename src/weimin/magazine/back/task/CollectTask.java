/**
 * 
 */
package weimin.magazine.back.task;

import java.util.Iterator;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weimin.magazine.back.cache.Redis4Cache;
import weimin.magazine.back.service.BackService;
import weimin.magazine.back.service.BaseService;


/**
 * <br>定时任务，收集信息</br>
 * @author tianhao
 *
 */
public class CollectTask {
    
    private static final Log log = LogFactory.getLog(CalculateTask.class);
    
    protected Redis4Cache redis = new Redis4Cache();//
    
    private BaseService baseService;// 通过spring注入；
    
    private BackService backService;// 通过spring注入；

   
    /**
     * <br>定时任务，系统收集信息
     * <br>步骤：
     * 1.收集用户微博信息，并同步更新
     * 2.收集用户微博标签，并同步更新；
     * 3.收集微博表情，并同步更新；
     */
    public void collectInfo() {
        log.info("定时任务，系统开始收集信息！");
        Set<String> userIds = redis.queryAllUserId();
        Iterator<String> iterator = userIds.iterator();
        while (iterator.hasNext()) {
            long userId = Long.parseLong(iterator.next());
            // 收集用户信息
            backService.collectUserInfo(userId);
            // 收集用户标签 TODO 可将此任务周期延长。
            backService.collectUserLabels(userId);
        }
        // 重置微博表情
        backService.replaceEmotions();
        log.info("完成定时任务，系统开始收集信息！");
    }

    public BaseService getBaseService() {
        return baseService;
    }

    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }

    public BackService getBackService() {
        return backService;
    }

    public void setBackService(BackService backService) {
        this.backService = backService;
    }

    

    
    
}
