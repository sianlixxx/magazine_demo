package weimin.magazine.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SystemConfig {
    private static final Log log = LogFactory.getLog(SystemConfig.class);
	public SystemConfig(){}
	private static Properties props = new Properties(); 
	static{
		try {
		    log.debug("开始加载系统配置文件");
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("redisConfig.properties"));
			log.debug("加载系统配置文件‘redisConfig.properties’完成！");
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("systemConfig.properties"));
			log.debug("加载系统配置文件‘systemConfig.properties’完成！");
			log.info("系统配置文件加载完成！");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
