package weimin.magazine.back.service;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weibo4j.model.WeiboException;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TUser;
import weimin.magazine.util.SystemProperties;

public class BackServiceTest {

    ApplicationContext applicationContext = null;
    SystemProperties s;
    BackService a ;
    
    @Before
    public void setUp() throws Exception {
        applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
        s = new SystemProperties();
        s.loadSystemProperties();
        a = (BackService) applicationContext.getBean("backService");
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void testCollectUserLabels() throws WeiboException {
        a.collectUserLabels(1);
//        a.collectUserLabels(2);
//        a.collectUserLabels(3);
//        a.collectUserLabels(4);
    }
    
    @Test
    public void testcalculateTop(){
        a.calculateTop();
    }
    
//    @Test
    public void testreplaceEmotions(){
        a.replaceEmotions();
    }
    
    @Test
    public void testreplaceRecomments() {
       // fail("Not yet implemented");
        a.replaceRecomments(1);
        a.replaceRecomments(2);
        a.replaceRecomments(3);
        a.replaceRecomments(4);
//        Set<TDepartment> ds = new LinkedHashSet<TDepartment>();
//        TDepartment d1 = new TDepartment();
//        d1.setDepartmentId(1l);
//        d1.setCreaterUserId(1l);
//        System.out.println(ds.add(d1));
//        System.out.println(ds.add(d1));
//        System.out.println(ds.contains(d1));
////        TDepartment d2 = new TDepartment();
//        d2.setDepartmentId(1l);
//        d2.setCreaterUserId(1l);
//        System.out.println(ds.contains(d2));
//        System.out.println(ds.remove(d1));
//        System.out.println(ds.remove(d1));
//        System.out.println(ds.remove(d2));
    }
    
    @Test
    public void testcollectContributeInWeibo(){
        a.collectContributeInWeibo(1);
    }

}
