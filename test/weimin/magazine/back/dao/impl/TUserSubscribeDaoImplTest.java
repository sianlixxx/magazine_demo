package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TUserSubscribe;

public class TUserSubscribeDaoImplTest {
	ApplicationContext applicationContext = null;
	TUserSubscribeDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TUserSubscribeDaoImpl)applicationContext.getBean("tUserSubscribeDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TUserSubscribe t = (TUserSubscribe) s.queryById(1);
		assertNotNull(t);
	}

//	@Test
	public void testQueryAll() {
//		fail("Not yet implemented");
		List<Object> l = s.queryAll();
		System.out.println(l.size());
		assertTrue(l.size()!=0);
	}

//	@Test
	public void testUpdate() {
//		fail("Not yet implemented");
		TUserSubscribe t = (TUserSubscribe) s.queryById(1);
		t.setStatus(2);
		s.update(t);
		
	}

//	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TUserSubscribe t = new TUserSubscribe();
		t.setDepartmentId((long) 5421);
		t.setStatus(2);
		s.insert(t);
		System.out.println(s.insert(t));
		
	}

	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(2);
	}
	
	@Test
	public void testfindSubscribe() {
//		fail("Not yet implemented");
		TUserSubscribe o = (TUserSubscribe) s.findSubscribe(3, 3);
		System.out.println(o.getSubscribeId());
	}

}
