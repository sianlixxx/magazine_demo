package weimin.magazine.back.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import weibo4j.model.WeiboException;
import weimin.magazine.util.SystemProperties;
public class BaseServiceTest {
	ApplicationContext applicationContext = null;
	SystemProperties s;
	BaseService b;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = new SystemProperties();
		s.loadSystemProperties();
		b = (BaseService) applicationContext.getBean("baseService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShowLabels() {
//		fail("Not yet implemented");
		System.out.println(b.showLabels());
	}

	@Test
	public void testShowUserLabels() {
//		fail("Not yet implemented");
		System.out.println(b.showUserLabels(1));
	}

//	@Test
	public void testModifyUserLabels() {
//		fail("Not yet implemented");
		Set<String> labels = new LinkedHashSet<String>();
		labels.add("it");
		labels.add("hahahah");
		labels.add("goodboy");
		labels.add("man");
		System.out.println(labels);
		b.modifyUserLabels(1, labels);
		System.out.println(b.showUserLabels(1000000009));
	}
	
	

	

    @Test
    public void testshowTop(){
        System.out.println( b.showTop().size());
    }
    
    @Test
    public void testshowEmotions(){
        System.out.println( b.showEmotions().size());
    }
    
    @Test
    public void testtransUid2UserId(){
        b.transUid2UserId(16775803670l);    
    }
    
    

}
