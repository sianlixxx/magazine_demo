package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TAccessToken;

public class TAccessTokenDaoImplTest {
	ApplicationContext applicationContext = null;
	TAccessTokenDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TAccessTokenDaoImpl)applicationContext.getBean("tAccessTokenDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TAccessToken t = (TAccessToken) s.queryById(1);
		assertNotNull(t);
	}

//	@Test
	public void testQueryAll() {
//		fail("Not yet implemented");
		List<Object> l = (List<Object>) s.queryAll();
		System.out.println(l.size());
		assertNotNull(l);
	}

//	@Test
	public void testUpdate() {
//		fail("Not yet implemented");
		TAccessToken t = (TAccessToken) s.queryById((long)2784497144l);
//		t.setUid((long) 2784497140l);
		s.update(t);
	}

//	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TAccessToken t = new TAccessToken();
		long userId = 2784497151l;
		t.setUserId(userId);
		System.out.println(s.insert(t));
	}

//	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		
	}
	
//	@Test
	public void testQueryByUid() {
//		fail("Not yet implemented");
		System.out.println(s.queryByUid(2784497140l).getUid());
	}

}
