package weimin.magazine.back.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.util.SystemProperties;

public class ReadServiceTest {
    
    ApplicationContext applicationContext = null;
    SystemProperties s;
    ReadService b;

    @Before
    public void setUp() throws Exception {
        applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
        s = new SystemProperties();
        s.loadSystemProperties();
        b = (ReadService) applicationContext.getBean("readService");
    }

    @After
    public void tearDown() throws Exception {
    }

//  @Test
   public void testsubscribe() {
       
       System.out.println(b.subscribe(1l, 1l));
       System.out.println(b.subscribe(1l, 2l));
       System.out.println(b.subscribe(1l, 3l));
       System.out.println(b.subscribe(1l, 4l));
       System.out.println(b.subscribe(2l, 1l));
       System.out.println(b.subscribe(2l, 2l));
       System.out.println(b.subscribe(2l, 3l));
       System.out.println(b.subscribe(2l, 4l));
       System.out.println(b.subscribe(3l, 1l));
       System.out.println(b.subscribe(4l, 1l));
   }
   
 @Test
   public void batchSubscribe() {
       ArrayList<String> departmentIds = new ArrayList<String>();
       departmentIds.add("2");
       departmentIds.add("3");
       departmentIds.add("4");
       System.out.println(b.batchSubscribe(3l,  departmentIds));
   }
 
//@Test
 public void testshowSubscribe(){
     b.showSubAndRec(1);
 }
 

}
