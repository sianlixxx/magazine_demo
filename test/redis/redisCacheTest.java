/**
 * 
 */
package redis;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @author tianhao
 *
 */
public class redisCacheTest {
	  private JedisPool jedisPool; 
	  private Jedis jedis; 
	redisCacheTest(){
		 // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxActive(20); 
        config.setMaxIdle(5); 
        config.setMaxWait(1000l); 
        config.setTestOnBorrow(false); 
        jedisPool = new JedisPool(config, "127.0.0.1", 6379); 
        jedis = jedisPool.getResource();
	}
	
	private void show(){
		  testList(); 
	}

	private void testList() {
		// TODO Auto-generated method stub
		// 清空数据 
        System.out.println(jedis.flushDB()); 
        // 添加数据 
        String key = "lists";
        String value = "";
        Date now = new Date();
        DateFormat d1 = DateFormat.getDateTimeInstance();
        System.out.println(d1.format(now));
        for(int i= 0 ;i<500; i++){
        	value = String.valueOf(i);
        	jedis.lpush(key, value); 
        }
     // 数组长度 
        System.out.println(jedis.llen("lists")); 
        Date now2 = new Date();
        System.out.println(d1.format(now2) );
        //list set 10w条记录约需10s
        // 排序 
        System.out.println(jedis.sort("lists")); 
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new redisCacheTest().show(); 

	}

}
