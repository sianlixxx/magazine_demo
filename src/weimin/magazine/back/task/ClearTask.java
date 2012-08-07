/**
 * 
 */
package weimin.magazine.back.task;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weimin.magazine.back.cache.Redis4Cache;
import weimin.magazine.back.dao.pojo.TUser;
import weimin.magazine.back.service.BackService;
import weimin.magazine.util.Tools;


/**
 * @author tianhao
 *
 */
public class ClearTask {
    
    private static final Log log = LogFactory.getLog(ClearTask.class);
    
    protected Redis4Cache redis = new Redis4Cache();//
    
    private BackService backService;// 通过spring注入； 
    
    

    /**
     * <br>清理缓存
     */
    public void clearRedisCache(){
        log.info("开始清理redis缓存任务！");
        //清理用户
        this.clearTUser();
        //清理杂志社
        this.clearTDepartment();
        log.info("完成清理redis缓存任务！");
    }

   

    /**
     * <br>清理redis中用户信息
     */
    private void clearTUser() {
        log.info("开始清理redis中用户信息！");
        //获取清理参数
        int num = Integer.valueOf(System.getProperties().getProperty("sys.clear.tUser.day.num"));
        Date date = Tools.getEarlyDate(num);
        // 
        Set<String> userIds = redis.queryAllUserId();
        Iterator<String> iterator = userIds.iterator();
        while (iterator.hasNext()) {
            long userId = Long.valueOf(iterator.next());
            TUser tUser = redis.queryTUser(userId);
            Date last = tUser.getLastLoginTime();
            //
            if(last.compareTo(date) < 0 ){
                redis.deleteTUser(userId);
            }
        }
        log.info("完成清理redis中用户信息！");
    }
    
    /**
     * <br>清理redis中杂志社信息！
     * TODO 
     */
    private void clearTDepartment() {
        log.info("开始清理redis中杂志社信息！");
        Set<String> departmentIds = redis.queryAllDepartmentId();
        Iterator<String> iterator = departmentIds.iterator();
        while (iterator.hasNext()) {
           // String departmentId = iterator.next();
            // 收集用户信息
           
        }
        log.info("完成清理redis中杂志社信息！" ); 
    }

    public BackService getBackService() {
        return backService;
    }

    public void setBackService(BackService backService) {
        this.backService = backService;
    }

}
