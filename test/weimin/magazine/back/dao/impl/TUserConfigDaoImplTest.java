package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TUserConfig;

public class TUserConfigDaoImplTest {
	ApplicationContext applicationContext = null;
	TUserConfigDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TUserConfigDaoImpl)applicationContext.getBean("tUserConfigDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TUserConfig t = (TUserConfig) s.queryById(1);
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
		TUserConfig t = (TUserConfig) s.queryById(1);
		
		s.update(t);
		
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TUserConfig t = new TUserConfig();
		t.setConfigType(2);
		t.setConfigValue(2);
		t.setUserId(5421);
		System.out.println(s.insert(t));
		
	}

//	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(2);
	}

}
