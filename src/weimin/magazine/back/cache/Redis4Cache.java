package weimin.magazine.back.cache;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import redis.clients.jedis.Jedis;
import weibo4j.model.Emotion;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TUser;


public class Redis4Cache {
	
    private static final Log log = LogFactory.getLog(Redis4Cache.class);

    private JedisPoolManager instance;
    
    private Gson JsonUtils = new Gson();
    

	 /**
	  * 
	  */
	public Redis4Cache() {
		instance = JedisPoolManager.getInstance();
	}
	
	/**
     * <br>从缓存中取用户系统id,
     * map<key ,vaule>
     * map<uid ,userId>
     * @param uid
     * @return
     */
    public long queryUserIdByUid(long uid) {
        Jedis jedis=null; 
        long userId = 0;
        try{
            jedis =instance.getResource();
            //key 即db中文件名，field为用户uid，value为accesstoken
            String key  = "uid_map_userId" ;
            String field = String.valueOf(uid);
            String value = jedis.hget(key,field);
            if (value != null){
                userId = Long.parseLong(value);
                log.debug("从缓存中取用户系统id，用户微博id为：" + uid+"    用户id为：" + userId);
            }else{
                log.debug("从缓存中取用户系统id失败！缓存中不存在此系统id，用户微博id为：" + uid ); 
            }
        }catch (Exception e) {  
            log.error("从缓存中取用户系统id失败！用户微博id为：" + uid+"    用户id为：" + userId ,e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
        return userId; 
    }
    
    /**
     * <br>维护更新缓存中取用户系统id
     * map<key ,vaule>
     * map<uid ,userId>
     * @param uid
     * @param userId
     */
    public void addUserId4Uid(long uid ,long userId) {

        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            //key 即db中文件名，field为用户uid，value为accesstoken
            String key  = "uid_map_userId" ;
            String field = String.valueOf(uid);
            String value = String.valueOf(userId);
            jedis.hset(key, field, value);
            log.debug("向缓存中更新用户系统id，用户微博id为：" + uid+"    用户id为：" + userId);
        }catch (Exception e) {  
            log.error("向缓存中更新用户系统id失败！用户微博id为：" + uid+"    用户id为：" + userId,e); 
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
    }
    
    /**
     * <br>从缓存中取用户微博id
     *  map<key ,vaule>
     * map<userId ,uid>
     * @param userId
     * @return
     */
    public long queryUidByUserId(long userId) {
        Jedis jedis=null; 
        long uid = 0;
        try{
            jedis =instance.getResource();
            //key 即db中文件名，field为用户uid，value为accesstoken
            String key  = "userId_map_uid" ;
            String field = String.valueOf(userId);
            String value = jedis.hget(key,field);
            uid = Long.parseLong(value);
            log.debug("从缓存中取用户微博id,用户id为：" + userId+"   用户微博id为："+uid);
        }catch (Exception e) {  
            log.error("从缓存中取用户微博id失败,用户id为：" + userId+"   用户微博id为："+uid ,e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
        return uid; 
    }

    /**
     * <br>更新缓存中取用户微博id
     * map<key ,vaule>
     * map<userId ,uid>
     * @param userId
     * @param uid
     */
    public void addUid4UserId(long userId ,long uid) {

        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            //key 即db中文件名，field为用户uid，value为accesstoken
            String key  = "userId_map_uid" ;
            String field = String.valueOf(userId);
            String value = String.valueOf(uid);
            jedis.hset(key, field, value);
            log.debug("向缓存中更新用户系统微博id，用户id为：" + uid+"   用户微博id为："+userId);
        }catch (Exception e) {  
            log.error("向缓存中更新用户系统微博id失败！用户id为：" + uid+"   用户微博id为："+userId,e); 
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
    }
	
	/**
	 *<br> 获取某个类别的排行榜
	 * @param theme
	 * @return Map<String, String> key为名次，value编辑部id
	 */
	public Map<String, String> queryTopByTheme(String theme){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "topMagazine" + "_" + theme;
			Map<String, String> top = jedis.hgetAll(key);
			if(top != null){
			    log.debug("从缓存中获取排行榜，排行榜类别为："+ theme);
	            return top;
			}else{
			    log.debug("缓存中不存在此类别的排行榜，查询类别为："+ theme);
			}
			
		}catch (Exception e) {  
            log.error("获取排行榜失败！"+e)  ;
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
		return null; 
	}
	
	/**
	 *<br> 增加/更新某个类别的排行榜
	 * @param theme
	 * @param hashMap<String, String> key为名次，value编辑部id
	 */
	public void addTopByTheme(String theme ,Map<String, String>  hash){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "topMagazine" + "_" + theme;
			jedis.hmset(key, hash);
			log.debug("向缓存中更新排行榜！排行榜类别为："+ theme);
		}catch (Exception e) {  
            log.error("向缓存中更新排行榜失败！排行榜类别为："+ theme, e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }  
	}
	
	/**
	 * <br>获取系统总排行榜
	 * @return Map<String, String> key为名次，value编辑部id
	 */
	public Map<String, String> queryTop(){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "topMagazine" ;
			log.debug("从缓存中获取系统总排行榜！");
			return jedis.hgetAll(key);
		}catch (Exception e) {  
            log.error("从缓存中获取系统总排行榜失败！", e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
		return null; 
	}
	
	/**
	 *  <br>增加/更新系统总排行榜
	 * @param hashMap<String, String> key为名次，value编辑部id
	 */
	public void addTop(Map<String, String>  hash){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "topMagazine" ;
			jedis.hmset(key, hash);
			log.debug("向缓存中更新总排行榜!");
		}catch (Exception e) {  
            log.error("向缓存中更新总排行榜失败！", e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
	}
	
	/**
	 * <br>查询用户标签，
	 * @param userId
	 * @return Set<String> 用户的标签集合
	 */
	public  Set<String> queryUserLabels(long userId){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "userLabels_" +  String.valueOf(userId);
			Set<String> labels =  jedis.smembers(key);
			if(labels != null){
			    log.debug("向缓存中取用户标签，用户id为：" + userId);
			    return labels;
			}else{
			    log.debug("向缓存不存在此用户标签，用户id为：" + userId);
			}
		}catch (Exception e) {  
			log.error("向缓存中取用户标签失败！用户id为：" + userId); 
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
		return null; 
	}
	
	/**
	 *<br> 插入用户标签  Set<String>
	 * @param userId
	 * @param label 
	 */
	public void addLabel2User(long userId,String label){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "userLabels_" +  String.valueOf(userId);
			jedis.sadd(key, label);
			log.debug("向缓存中更新用户标签，用户id为：" + userId);
		}catch (Exception e) {  
			log.error("向缓存中更新用户标签失败！用户id为：" + userId); 
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
	}
	
	/**
	 *<br> 删除用户某个标签
	 * @param userId
	 * @param label
	 */
	public void delUserLabel(long userId, String label) {
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "userLabels_" +  String.valueOf(userId);
			jedis.srem(key, label);
			log.debug("清除缓存中用户标签，用户id为：" + userId);
		}catch (Exception e) {  
			log.error("向缓存中清除用户标签失败！用户id为：" + userId); 
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
		
	}
	
	/**
	 *<br> 查询杂志社所有标签， Set<String>
	 * @param departmentId
	 * @return 杂志社的标签集合
	 */
	public Set<String> queryDepartmentLabels(long departmentId){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "departmenLabels_" +  String.valueOf(departmentId);
			log.debug("从缓存中取杂志社标签，杂志社id为：" + departmentId);
			Set<String> labels = jedis.smembers(key);
			if(labels != null){
                log.debug("向缓存中取杂志标签，杂志id为：" + departmentId);
                return labels;
            }else{
                log.debug("向缓存不存在此杂志标签，杂志id为：" + departmentId);
            }    
		}catch (Exception e) {  
			log.error("从缓存中取杂志社标签失败！杂志社id为：" + departmentId,e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
		return null; 
	}
	
	/**
	 * <br>插入杂志社标签
	 * @param departmentId
	 * @param label
	 */
	public void addLabel2Department(long departmentId,String label){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，
			String key = "departmenLabels_" +  String.valueOf(departmentId);
			jedis.sadd(key, label);
			log.debug("向缓存中更新杂志社标签，杂志社id为：" + departmentId);
		}catch (Exception e) {  
			log.error("向缓存中更新杂志社标签失败！杂志社id为：" + departmentId,e); 
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
	}
	
	
	
	/**
	 * <br>根据微博uid查询用户授权令牌accesstoken
	 * hash<key ,vulue >
	 * hash<uid ,String >
	 * @param uid  
	 * @return accesstoken 
	 */
	public String queryAccessTokenByUid(long uid ){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，field为用户uid，value为accesstoken
			String key  = "accessToken_uid" ;
			String field = String.valueOf(uid);
			String accesstoken = jedis.hget(key,field);
			if(accesstoken != null ){
			    log.debug("从缓存中取用户授权信息，用户微博id为：" + uid);
			    return accesstoken;
			}else{
			    log.debug("从缓存中不存在此用户授权信息，用户微博id为：" + uid);
			}
			
		}catch (Exception e) {  
			log.error("从缓存中取用户授权信息失败！用户微博id为：" + uid ,e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
		return null; 
	} 
	
	
	/**
	 *<br> 根据微博uid增加用户授权令牌accesstoken
	 * hash<key ,vulue >
     * hash<uid ,String >
	 * @param uid
	 * @param accessToken
	 */
	public void addAccessTokenByUid(long uid ,String accessToken){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//key 即db中文件名，field为用户uid，value为accesstoken
			String key  = "accessToken_uid" ;
			String field = String.valueOf(uid);
			String value = accessToken;
			jedis.hset(key, field, value);
			log.debug("向缓存中更新用户授权信息，用户微博id为：" + uid);
			
		}catch (Exception e) {  
			log.error("向缓存中更新用户授权信息失败！用户微博id为：" + uid,e); 
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
	}
	
	/**
	 *<br> 取出系统推荐的标签列表  
	 * @return 标签列表 List<String>
	 */
	public List<String> querySuggestionsLabels(){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			String key = "sys_suggestions_label";
			List<String> labels = jedis.lrange(key, 0, -1);
			if(labels != null){
			    log.debug("从缓存中获取系统推荐标签！");
			    return  labels; 
			}else{
			    log.debug("缓存中不存在任何系统推荐标签！");
			}
		}catch (Exception e) {  
            log.error("从缓存中获取系统推荐标签失败！"+e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
		return null;
	}
	
	/**
	 *<br> 生成标签列表供用户选择； List<String>
	 * <br>使用倒排序列保证列表中的标签按照优先级排列，
	 * @param labels 
	 */
	public void addSuggestionsLabels(List<String> labels){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			String key = "sys_suggestions_label";
			//使用倒排序列保证列表中的标签按照优先级排列，
			for(int i = labels.size(); i>0; i--){
				jedis.lpush(key, labels.get(i-1));
			}
			log.debug("向缓存中更新系统推荐标签！");
		}catch (Exception e) {  
			 log.error("从缓存中更新系统推荐标签失败！"+e);   
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
	}
	
	/**
	 * <br>缓存中清空系统推荐标签  List<String>
	 */
	public void deleteLabels() {
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//清空列表
			jedis.ltrim("sys_suggestions_label", -1, 0);
			log.debug("清空缓存中系统推荐标签!");
		}catch (Exception e) {  
            log.error("清空缓存中系统推荐标签失败！", e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
	}
	
	/**
	 * <br>在缓存里存储为用户推荐的杂志</br>
	 * <br>步骤：</br>
	 * 	1.在杂志社列表里增加杂志社id</br>
	 * 	2.根据杂志社列表更新所有杂志社的详细信息；</br>
	 * 
	 * @param userId
	 * @param recomments
	 */
	public void addRecomments(long userId, Set<TDepartment> recomments) {
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			String id = Long.toString(userId);
			//key 为"magazine_recomments_" + userid ；
			String key = "magazine_recomments_" + id ;
			for(TDepartment t:recomments){
				 String departmentId = Long.toString(t.getDepartmentId());
				 //为用户增加一个所推荐杂志的id；
				 //key 为 "magazine_recomments_"+useId；	value 为departmentId
				 jedis.sadd(key, departmentId);
				 //在缓存中更新此department
				 this.addDepartment(t);
			}
			log.debug("向缓存中增加推荐给用户的杂志，用户id为：" + userId);
		}catch (Exception e) {  
			log.error("向缓存中增加推荐给用户的杂志失败！用户id为：" + userId,e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
	}
	
	/**
	 *<br> 在redis中检索为用户推荐的杂志</br>
	 *<br> 步骤：</br>
	 * 1.根据用户id检索杂志社id列表；</br>
	 * @param userId
	 * @return 包括杂志社详细信息的杂志社列表
	 */
	public Set<String> queryRecomments(long userId)  {
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			//获取用户推荐集，集合中保存的是杂志社id
			String id = Long.toString(userId);
			String key = "magazine_recomments_" + id ;
			//TODO 取出的是逆序？未找到原因。System.out.println(jedis.smembers(key));
			Set<String> departmentIdSet = jedis.smembers(key);
			return departmentIdSet;
		}catch (Exception e) {  
            log.error("从缓存中获取推荐给用户的在杂志失败！用户id为：" + userId, e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
		return null; 
	}
	
	/**
	 *<br> 删除系统推荐给用户的所有杂志id的set集合
	 * @param userId
	 */
	public void deleteRecomments(long userId) {
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			String id = Long.toString(userId);
			String key = "magazine_recomments_" + id ;
			jedis.del(key);
			log.debug("清空缓存中推荐给用户的在杂志，用户id为：" + userId);
		}catch (Exception e) {  
			log.error("清空缓存中推荐给用户的在杂志失败！用户id为：" + userId, e);   
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
	}
	
	/**
	 * <br> 删除用户已订阅杂志id，</br>
	 * <br> 删除系统推荐给用户的杂志id的set集合用户已订阅杂志，
	 * @param userId
	 * @param departmentId
	 */
	 public void deleteSubscribe(Long userId, Long departmentId) {
	        Jedis jedis=null; 
	        try{
	            jedis =instance.getResource();
	            String id = Long.toString(userId);
	            String key = "magazine_recomments_" + id ;
	            jedis.srem(key, Long.toString(departmentId));
	            log.debug("删除缓存中用户已订阅杂志，用户id为：" + userId);
	        }catch (Exception e) {  
	            log.error("清空缓存用户已订阅失败！用户id为：" + userId, e);   
	        }  
	        finally{  
	            if(jedis!=null)  
	            instance.retrunResource(jedis);
	        } 
	    
	        
	    }
	
	
	


	/**
	 * <br>
	 * @param departmentId
	 * @param TDepartment
	 */
	public void addDepartment( TDepartment t) {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String id = String.valueOf(t.getDepartmentId());
            String key = "tDepartment";
            String json = JsonUtils.toJson(t);
            jedis.hset(key, id ,json);
            log.debug("向缓存中更新杂志社信息，杂志社id为：" +id);
        }catch (Exception e) {  
            log.error("向缓存中更新杂志社信息失败！杂志社id为：" +t.getDepartmentId(),e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
	}
	
	 /**
     * <br>从缓存中取所有杂志社Id！
     * @return
     */
    public Set<String> queryAllDepartmentId() {
        Jedis jedis=null;
        try{
            jedis =instance.getResource();
            //key 即db中文件名，
            String key  = "tDepartment" ;
            Set<String> uids = jedis.hkeys(key);
            if(uids != null){
                log.debug("从缓存中取所有杂志社Id！" );
                return uids;
            }else{
                log.debug("缓存中不存在任何杂志社Id！" );
            }
            
        }catch (Exception e) {  
            log.error("从缓存中取所有杂志社Id失败！",e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
        return null; 
    }

	/**
	 * <br>在缓存里查询杂志社的详细信息
	 * @param departmentId
	 * @return 杂志社详细信息
	 *  
	 */
	public TDepartment queryDepartment(long departmentId)  {

        Jedis jedis = null;
        try {
            jedis = instance.getResource();
            String id = String.valueOf(departmentId);
            // key 即db中文件名，
            String key = "tDepartment";
            if (jedis.hexists(key,id)) {
                String json = jedis.hget(key,id);
                TDepartment department = JsonUtils.fromJson(json, new TypeToken<TDepartment>(){}.getType());
                log.debug("向缓存中获取杂志社信息，杂志社id为：" + departmentId);
                return department ;
            } else {
                log.debug("向缓存中不存在此杂志社户信息，杂志社id为：" + departmentId);
            }
        } catch (Exception e) {
            log.error("向缓存中获取杂志社信息失败！杂志社id为：" + departmentId, e);
        } finally {
            if (jedis != null)
                instance.retrunResource(jedis);
        }
        return null;
    
	}
	
	/**
	 * <br>删除缓存里杂志社的详细信息
	 * @param departmentId
	 */
	public void deleteDepartment(long departmentId){
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String id = String.valueOf(departmentId);
             //key 即db中文件名，
            String key = "tDepartment";
            if(jedis.hexists(key,id)) {
                jedis.hdel(key,id);
                 log.debug("删除缓存中杂志社信息成功，杂志社id为："+departmentId);
            }else{
                log.debug("向缓存中不存在此杂志社信息，杂志社id为：" +departmentId);
            }
            
        }catch (Exception e) {  
             log.error("向缓存中获取杂志社信息失败！杂志社id为：" +departmentId,e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
	}
	
	/**
     * <br>存储标签的微博id
     * 
     * @param tagName 标签名
     * @param id 标签的微博id
     */
    public void addWeiboTagTid(String tagName, String id) {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String key = "sina_weibo_tags_id";
            jedis.hset(key, tagName, id);
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
    }

    /**
     *<br> 获取标签的微博id
     * @param tagName 标签名
     * @return
     */
    public String queryWeiboTagTid(String tagName){
        Jedis jedis=null; 
        String tid = "";
        try{
            jedis =instance.getResource();
            String key = "sina_weibo_tags_id";
            tid = jedis.hget(key,tagName);
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
        return tid; 
    }
    
    /**
     * <br>向缓存中检索系统推荐表情
     * @return
     */
    public List<Emotion> queryEmotions() {
        List<Emotion> emotions = new ArrayList<Emotion>();
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String key = "sys_suggestions_emotion";
            List<String> labels = jedis.lrange(key, 0, -1);
            if(labels != null){
                for(String l :labels){
                    Gson gson = new Gson();
                    Emotion e = gson.fromJson(l, new TypeToken<Emotion>(){}.getType());
                    log.debug("从缓存中获取系统推荐表情！表情为："+e.getPhrase());
                    emotions.add(e);
                }
                return  emotions; 
            }else{
                log.debug("缓存中不存在任何系统推荐表情！");
            }
        }catch (Exception e) {  
            log.error("从缓存中获取系统推荐表情失败！"+e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
        return null;
    }

    /**
     * <br>向缓存中更新系统推荐表情
     * @param emotions
     */
    public void addEmotions(List<Emotion> emotions) {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String key = "sys_suggestions_emotion";
            //使用倒排序列保证列表中的标签按照优先级排列，
            for(Emotion e:emotions){
                Gson gson = new Gson();
                String json = gson.toJson(e, new TypeToken<Emotion>(){}.getType());
                jedis.lpush(key, json);
                log.debug("向缓存中更新系统推荐表情！表情为："+e.getPhrase());
            }
        }catch (Exception e) {  
             log.error("从缓存中更新系统推荐表情失败！"+e);   
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
    }
    
    /**
     * <br>删除杂志主题
     */
    public void deleteThemes() {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            //清空列表
            jedis.ltrim("sys_suggestions_theme", -1, 0);
            log.debug("清空缓存中系统推荐主题!");
        }catch (Exception e) {  
            log.error("清空缓存中系统推荐主题失败！", e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
    }

    /**
     * <br> 更新杂志主题
     * @param themes
     */
    public void addSuggestionsThemes(ArrayList<String> themes) {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String key = "sys_suggestions_theme";
            //使用倒排序列保证列表中的标签按照优先级排列，
            for(int i = themes.size(); i>0; i--){
                jedis.lpush(key, themes.get(i-1));
            }
            log.debug("向缓存中更新系统推荐主题！");
        }catch (Exception e) {  
             log.error("从缓存中更新系统推荐主题失败！"+e);   
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
    
    }

    /**
     * <br>查询杂志主题，
     * @return
     */
    public List<String> querySuggestionsThemes() {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String key = "sys_suggestions_theme";
            List<String> themes = jedis.lrange(key, 0, -1);
            if(themes != null){
                log.debug("从缓存中获取系统推荐主题！");
                return  themes; 
            }else{
                log.debug("缓存中不存在任何系统推荐主题！");
            }
        }catch (Exception e) {  
            log.error("从缓存中获取系统推荐主题失败！"+e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
        return null;
    
    }
    
    /**
     * <br>获取系统总排行榜
     * @param type 类别
     * @return Map<String, String> key为名次，value编辑部id
     */
    public Map<String, String> queryThemeTop(int type) {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            //key 即db中文件名，
            String key = "topMagazine_theme_"+String.valueOf(type);
            log.debug("从缓存中获取系统分类排行榜！   类别为："+type);
            return jedis.hgetAll(key);
        }catch (Exception e) {  
            log.error("从缓存中获取系统分类排行榜失败！   类别为："+type, e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
        return null; 
    }

    /**
     * <br>增加/更新系统分类排行榜
     * @param hashMap<String, String> key为名次，value编辑部id
     * @param type 类别
     */
    public void addThemeTop(Map<String, String> hash,int type) {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            //key 即db中文件名，
            String key = "topMagazine_theme_"+String.valueOf(type);
            jedis.hmset(key, hash);
            log.debug("向缓存中更新分类排行榜!     类别为："+type);
        }catch (Exception e) {  
            log.error("向缓存中更新分类排行榜失败！     类别为："+type, e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
    }
	
	/**
	 * <br>
	 */
	public void flushDb(){
		Jedis jedis=null; 
		try{
			jedis =instance.getResource();
			jedis.flushDB();
		}catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
	}

    /**
     * <br>
     * @param userId
     * @return
     */
    public Set<TUser> queryRecommentEditors(long userId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * <br>检索缓存中投稿对象集合
     * @param userId
     * @return departmentId 集合
     */
    public Set<Long> queryEditor4contribute(long userId) {
        Jedis jedis=null; 
        Set<Long> departmentIds = new LinkedHashSet<Long>();
        try{
            jedis =instance.getResource();
            //获取用户推荐集，集合中保存的是杂志社id
            String id = Long.toString(userId);
            String key = "set_4_contribute_" + id ;
            Set<String> set = jedis.smembers(key);
            Iterator<String> iterator =  set.iterator();
            while (iterator.hasNext()){
                long departmentId = Long.valueOf(iterator.next());
                departmentIds.add(departmentId);
            }
            log.debug("从缓存中获取投稿对象集合！用户id为：" + userId+"  集合大小为："+departmentIds.size());     
            return departmentIds;
        }catch (Exception e) {  
            log.error("从缓存中获取！用户id为：" + userId, e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
        return null; 
    }
    
    /**
     * <br>
     * @param userId
     * @param departmentId
     */
    public void addEditor4Contribute(Long userId, Long departmentId) {
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String id = Long.toString(userId);
            String key = "set_4_contribute_" + id ;
            jedis.sadd(key, String.valueOf(departmentId));
            log.debug("向缓存中增加推荐给用户的杂志，用户id为：" + userId);
        }catch (Exception e) {  
            log.error("向缓存中增加推荐给用户的杂志失败！用户id为：" + userId,e);  
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        } 
    }
    
    /**
     * <br>从缓存中取所有用户userId Set<String>
     * @return 用户微博uid集合
     */
     public Set<String> queryAllUserId() {
            Jedis jedis=null;
            try{
                jedis =instance.getResource();
                //key 即db中文件名，
                String key  = "tUser" ;
                Set<String> uids = jedis.hkeys(key);
                if(uids != null){
                    log.debug("从缓存中取所有用户userId！" );
                    return uids;
                }else{
                    log.debug("缓存中不存在任何用户userId！" );
                }
                
            }catch (Exception e) {  
                log.error("从缓存中取所有用户userId失败！",e);
            }  
            finally{  
                if(jedis!=null)  
                instance.retrunResource(jedis);
            }
            return null; 
        }

    /**
     * <br>从缓存中取用户信息
     * @param userId
     * @return
     */
    public TUser queryTUser(long userId) {
        Jedis jedis = null;
        try {
            jedis = instance.getResource();
            String id = String.valueOf(userId);
            // key 即db中文件名，
            String key = "tUser";
            if (jedis.hexists(key,id)) {
                String json = jedis.hget(key,id);
                TUser user = JsonUtils.fromJson(json, new TypeToken<TUser>(){}.getType());
                log.debug("向缓存中获取用户信息，用户id为：" + userId);
                return user ;
            } else {
                log.debug("向缓存中不存在此用户信息，用户id为：" + userId);
            }
        } catch (Exception e) {
            log.error("向缓存中获取杂志社信息失败！用户id为：" + userId, e);
        } finally {
            if (jedis != null)
                instance.retrunResource(jedis);
        }
        return null;
    }
    
    /**
     * <br>用户信息
     * <br>缓存存储最近登录的用户信息，若有需要可根据最后登录时间进行清理
     * @param t
     */
    public void addTuser(TUser t){
        Jedis jedis=null; 
        try{
            jedis =instance.getResource();
            String id = String.valueOf(t.getUserId());
            String key = "tUser";
            String json = JsonUtils.toJson(t);
            jedis.hset(key, id ,json);
            log.debug("向缓存中更新用户信息，用户id为：" +id);
        }catch (Exception e) {  
            log.error("向缓存中更新用户信息失败！用户id为：" +t.getUserId(),e);
        }  
        finally{  
            if(jedis!=null)  
            instance.retrunResource(jedis);
        }
    }

    /**
     * <br>向缓存中删除用户信息
     * @param userId
     * @return
     */
    public void deleteTUser(long userId) {
        Jedis jedis = null;
        try {
            jedis = instance.getResource();
            String id = String.valueOf(userId);
            // key 即db中文件名，
            String key = "tUser";
            if (jedis.hexists(key,id)) {
                jedis.hdel(key,id);
                log.debug("向缓存中删除用户信息，用户id为：" + userId);
            } else {
                log.debug("向缓存中不存在此用户信息，用户id为：" + userId);
            }
        } catch (Exception e) {
            log.error("向缓存中删除杂志社信息失败！用户id为：" + userId, e);
        } finally {
            if (jedis != null)
                instance.retrunResource(jedis);
        }
    }

   

   

   
   

}
