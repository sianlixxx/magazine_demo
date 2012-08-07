/**
 * jedisPool，manger,
 *	
 */
package weimin.magazine.back.cache;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import weimin.magazine.util.*;

/**
 * @author tianhao
 * 
 */
public class JedisPoolManager {
    
    private static final Log log = LogFactory.getLog(JedisPoolManager.class);

	private static JedisPoolManager instance;

	private JedisPool pool;
	
	private String password;
	
	private JedisPoolManager() {
		//判断是否加载系统属性
		if (SystemConfig.getValue("redis.pool.maxActive") == null){
//			SystemProperties.loadSystemProperties();
			Thread.currentThread().getContextClassLoader().getResourceAsStream("redisConfig.properties");
			log.debug("加载redis配置！");
		}
		// 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxActive(Integer.parseInt(SystemConfig.getValue("redis.pool.maxActive"))); 
        config.setMaxIdle(Integer.parseInt(SystemConfig.getValue("redis.pool.maxIdle"))); 
        config.setMaxWait( Long.parseLong(SystemConfig.getValue("redis.pool.maxWait"))); 
        config.setTestOnBorrow(Tools.toBoolean(SystemConfig.getValue("redis.pool.testOnBorrow"))); 
        String ip = SystemConfig.getValue("redis.server.ip");
        int port = Integer.parseInt(SystemConfig.getValue("redis.server.port"));
		pool = new JedisPool(config, ip, port);
		//获取访问密码
		this.password = SystemConfig.getValue("redis.client.password");
	}

	/** 线程同步控制确保模块仅有一个实例。 */
	static synchronized public JedisPoolManager getInstance() {
		if (instance == null) {
			instance = new JedisPoolManager();
		}
		return instance;
	}

	public Jedis getResource() {
		Jedis jedis = pool.getResource();
	//	log.debug("从redis池中取资源！");
		//填入密码
//		jedis.auth(password);
		return jedis;
	}

	// 使用完之后将连接返回到连接池中
	public void retrunResource(Jedis jedis) {
		pool.returnResource(jedis);
	//	log.debug("释放redis池中资源！");
	}

	// 销毁连接池中的所有连接
	public void destory() {
		pool.destroy();
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
