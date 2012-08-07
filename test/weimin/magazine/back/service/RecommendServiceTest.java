package weimin.magazine.back.service;

import static org.junit.Assert.*;

import java.util.Iterator;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import weimin.magazine.back.dao.impl.TAccessTokenDaoImpl;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.util.SystemProperties;

public class RecommendServiceTest {
	ApplicationContext applicationContext = null;
	RecommendService r ;
	SystemProperties s;
	TAccessTokenDaoImpl t;
	TAccessToken tAccessToken;
	

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		r = (RecommendService) applicationContext.getBean("recommendService") ;
		t = (TAccessTokenDaoImpl) applicationContext.getBean("tAccessTokenDAO") ;
		s = new SystemProperties();
		s.loadSystemProperties();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRecommend() {
		// fail("Not yet implemented");
		tAccessToken = (TAccessToken) t.queryById(1l);
		Set<TDepartment> recommends = r.recommend(tAccessToken.getUserId());
		System.out.println(recommends.size());
		Iterator<TDepartment> iterator =  recommends.iterator();
		while (iterator.hasNext()){
			 System.out.println(iterator.next().getName()+ ";");
		}
	}



//	@Test
	public void testMatchMagazine() {

//		Set<String> labels = new LinkedHashSet<String>();
//		labels.add("test001");
//		labels.add("test002");
//		labels.add("test003");
//		System.out.println("labels`s size is :"+labels.size());
//		Iterator<String> iterator = labels.iterator();
//		while (iterator.hasNext()){
//			 System.out.println(iterator.next()+ ";");
//		}
		
	
//		Set<TDepartment> recomments = new LinkedHashSet<TDepartment> ();
//		TDepartment t = new TDepartment();
//		t.setDepartmentId(2214l);
//		recomments.add(t);
//		System.out.println("Recomments`s size is :" +recomments.size());
//		Iterator<TDepartment> iterator = recomments.iterator();
//		while (iterator.hasNext()){
//			 System.out.println(iterator.next().getDepartmentId()+ ";");
//		}
//		
		
//		r.showUserLabels(2214l);
////		r.matchMagazine(r.getLabels());
//		System.out.println("Recomments`s size is :" +r.getRecomments().size());
//		Iterator<TDepartment> iterator = r.getRecomments().iterator();
//		while (iterator.hasNext()){
//			//TODO返回值为空？？
//			 System.out.println(iterator.next()+ ";");
//		}
//		
////		Object[] objArray = s.getRecomments().toArray();
////		for(int index=0; index < objArray.length ; index++){
////		      System.out.println(objArray[index]);
////		  }
//		assertNotNull(r.getRecomments());
	}
	
	


//	@Test
	public void testMatchTop() {
		fail("Not yet implemented");
	}

//	@Test
	public void testFilter() {
		fail("Not yet implemented");
	}

}
