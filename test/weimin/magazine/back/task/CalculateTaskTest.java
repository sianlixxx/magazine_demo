package weimin.magazine.back.task;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.service.BackService;
import weimin.magazine.back.service.BaseService;
import weimin.magazine.back.service.RecommendService;
import weimin.magazine.util.SystemProperties;

public class CalculateTaskTest {

    ApplicationContext applicationContext = null;
    SystemProperties s;
    CalculateTask c;
    
    @Before
    public void setUp() throws Exception {
        applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
        s = new SystemProperties();
        s.loadSystemProperties();
        c = new CalculateTask();
        c.setBackService((BackService) applicationContext.getBean("backService"));
        c.setBaseService((BaseService) applicationContext.getBean("baseService"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCalculateRecomments() {
//        fail("Not yet implemented");
        c.calculateRecomments();
    }

    @Test
    public void testCalculateTop() {
        c.calculateTop();
    }

}
