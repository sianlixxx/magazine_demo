package weimin.magazine.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weimin.magazine.back.cache.JedisPoolManager;


public class SystemProperties {
	
    private static final Log log = LogFactory.getLog(SystemProperties.class);
    
	/**
	 * 
	 * @param p
	 * @return
	 */
	public static boolean setSystemConfig(Properties p) {
		try {
			for (Enumeration e = p.keys(); e.hasMoreElements();) {
				String k = (String) e.nextElement();
				System.getProperties().setProperty(k, p.getProperty(k).trim());
			}

		} catch (Exception e) {
			
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public Properties loadSystemProperties() {
	    log.debug("开始加载系统参数！");
		InputStream in = null;
		Properties properties = new Properties();
		try {
		    URL url = Thread.currentThread().getContextClassLoader().getResource("");
		    log.debug("当前路径为："+url.toString());
		    
			File path = new File( url + "/");

			if (!path.exists()) {
					//
			} else {
				File[] confFiles = path.listFiles(new DirFilter("*.properties"));

				for (int i = 0; i < confFiles.length; i++) {
					File f = confFiles[i];
					if (f.exists() && f.isFile()) {
						in = new FileInputStream(f);
						properties.load(in);
						setSystemConfig(properties);
					}
				}
			}
		} catch (FileNotFoundException e) {
			//
		} catch (IOException e) {
				//
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ioe) {
					//
				}
			}
		}
		return properties;
	}
}
