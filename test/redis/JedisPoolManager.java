/**
 * jedisPool，manger,
 *	
 */
package redis;

import redis.clients.jedis.*;

/**
 * @author tianhao
 * 
 */
public class JedisPoolManager {

	private static JedisPoolManager instance;

	private JedisPool pool;

	private String ip;
	private int port;
	private String password;
	
	private JedisPoolManager() {
		// 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
//        config.setMaxActive(20); 
//        config.setMaxIdle(5); 
//        config.setMaxWait(1000l); 
//        config.setTestOnBorrow(false); 

        pool = new JedisPool(config, "192.168.120.51", 6379);
		pool = new JedisPool(new JedisPoolConfig(), ip, port);
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
		jedis.auth(password);
		return jedis;
	}

	// 使用完之后将连接返回到连接池中
	public void retrunResource(Jedis jedis) {
		pool.returnResource(jedis);
	}

	// 销毁连接池中的所有连接
	public void destory() {
		pool.destroy();
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
