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
 * @author tianhao
 *
 */
public class CalculateTask {
    
    private static final Log log = LogFactory.getLog(CalculateTask.class);
    
    protected Redis4Cache redis = new Redis4Cache();//
    
    private BaseService baseService;// 通过spring注入；
    
    private BackService backService;// 通过spring注入；
    

    /**
     * 
     */
    public CalculateTask() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    

    /**
     *<br> 为用户计算合适的杂志进行推荐，重置保存在db和缓存中的推荐数据；</br>
     * TODO 当用户量大时 ，可考虑过滤部分用户或进行mapreduce
     */
   public void calculateRecomments(){
       log.info("定时任务开始为用户计算合适的杂志进行推荐！");
       Set<String> userIds = redis.queryAllUserId();
       Iterator<String> iterator =  userIds.iterator();
       while (iterator.hasNext()){
           long userId = Long.valueOf(iterator.next());
           // TODO 当用户量大时 ，可考虑过滤部分用户
           //重置推荐
           backService.replaceRecomments(userId);
       }
       log.info("定时任务已完成，为用户计算合适的杂志进行推荐！");
   }
   
   /**
    * <br>定时任务开始为主编计算合适的编辑进行推荐
    */
   public void calculateRecommentEditors(){
       log.info("定时任务开始为主编计算合适的编辑进行推荐！");
    //   Set<String> userIds = redis.queryAllUserId();
     //  long userId = 1;
       // 判断用户是否为杂志创建者，
//       TUser creater ;
//       if(creater.isCreateEnabled()){
//           log.debug("此用户未建立过杂志社，不需要为此用户推荐编辑！");
//       }else{
//           //仅为杂志创建者推荐编辑
//       }
   }
   
   /**
    * <br>定时任务，按期计算总排行榜、分类排行榜；</br>
    * TODO 可考虑仅对缓存中的杂志进行排行计算，
    */
   public void calculateTop(){
       log.info("定时任务开始计算排行榜！");
       backService.calculateTop();
       log.info("定时任务已完成，计算排行榜！");
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
