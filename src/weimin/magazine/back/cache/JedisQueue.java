package weimin.magazine.back.cache;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import com.google.gson.Gson;


public class JedisQueue<T> {
    
    private static final Log log = LogFactory.getLog(JedisQueue.class); 
    
    private JedisPoolManager instance;
    private String name;
    private Class<T> clazz;
    private Gson JsonUtils = new Gson();

    public JedisQueue( Class<T> clazz) {
        this.instance = JedisPoolManager.getInstance();
        this.clazz = clazz;
        this.name = clazz.getName();
    }

    public JedisQueue( Class<T> clazz, String name) {
        this.instance = JedisPoolManager.getInstance();
        this.name = clazz.getName();
        this.clazz = clazz;
        this.name = name;
    }

    public void push(T... ts) {
        Jedis jedis = instance.getResource();
        try {
            for (T t : ts)
                jedis.lpush(name, JsonUtils.toJson(t,clazz));
                log.debug("增加一条消息-->"+name);
        } finally {
            instance.retrunResource(jedis);
        }
    }

    public void push(Collection<T> collection) {
        Jedis jedis = instance.getResource();
        try {
            for (T t : collection)
                jedis.lpush(name, JsonUtils.toJson(t,clazz));
                log.debug("增加一条消息-->"+name);
        } finally {
            instance.retrunResource(jedis);
        }
    }

    public T take() throws InterruptedException {
        Jedis jedis = instance.getResource();
        try {
            while (true) {
              //  log.debug("从队列里取消息！");
                Thread.sleep(300l);
                if (!jedis.exists(name))
                    continue;
                String jsonT = jedis.rpop(name);
                log.debug("获取一条消息-->"+name);
                if (jsonT != null) {
                    return (T) JsonUtils.fromJson(jsonT, clazz);
                }
            }
        } finally {
            instance.retrunResource(jedis);
        }
    }

    public Long size() {
        Jedis jedis = instance.getResource();
        try {
            if (!jedis.exists(name))
                return 0l;
            return jedis.llen(name);
        } finally {
            instance.retrunResource(jedis);
        }
    }

    public T poll() {
        Jedis jedis = instance.getResource();
        try {
            if (!jedis.exists(name))
                return null;
            String jsonT = jedis.rpop(name);

            return jsonT == null ? null : (T) JsonUtils.fromJson(jsonT, clazz);
        } finally {
            instance.retrunResource(jedis);
        }
    }

    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        Jedis jedis = instance.getResource();
        try {
            long nanos = unit.toNanos(timeout);
            while (true) {
                long lastTime = System.nanoTime();
                Thread.sleep(300l);
                if (!jedis.exists(name))
                    continue;
                String jsonT = jedis.rpop(name);
                if (jsonT != null) {
                    return (T) JsonUtils.fromJson(jsonT, clazz);
                }
                if (nanos <= 0)
                    return null;
                nanos -= (System.nanoTime() - lastTime);
            }
        } finally {
            instance.retrunResource(jedis);
        }
    }

    public static <T> JedisQueue<T> newQ(
            Class<T> clazz) {
        return new JedisQueue<T>(clazz);
    }

    public static <T> JedisQueue<T> newQ(
            Class<T> clazz, String name) {
        return new JedisQueue<T>( clazz, name);
    }
}
