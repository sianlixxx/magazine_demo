package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TUser;

public class TUserDaoImplTest {
	ApplicationContext applicationContext = null;
	TUserDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TUserDaoImpl)applicationContext.getBean("tUserDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TUser t = (TUser) s.queryById(1);
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
		TUser t = (TUser) s.queryById(1);
		t.setUid((long) 2012);
		s.update(t);
		
	}

//	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TUser t = new TUser();
		t.setUserId(2248448383l);
		s.insert(t);
		System.out.println(s.insert(t));
		
	}

//	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(5422);
	}
	
//	@Test
	public void testQueryByUid() {
//		fail("Not yet implemented");
		TUser t = (TUser) s.queryByUid(2784497140l);
		System.out.println(t.getName());
		assertNotNull(t);
	}

}
