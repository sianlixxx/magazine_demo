package weimin.magazine.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取sping的ApplicationContext信息
 * @author lify
 *
 */
public class SpringContextTool implements  ApplicationContextAware {
	
    private static ApplicationContext context;
    
    public void setApplicationContext(ApplicationContext acx) {
     context = acx;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
    
}
