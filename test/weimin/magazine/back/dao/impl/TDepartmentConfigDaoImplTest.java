package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TDepartmentConfig;

public class TDepartmentConfigDaoImplTest {
	ApplicationContext applicationContext = null;
	TDepartmentConfigDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TDepartmentConfigDaoImpl)applicationContext.getBean("tDepartmentConfigDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TDepartmentConfig t = (TDepartmentConfig) s.queryById(2);
		assertNotNull(t);
	}

	@Test
	public void testQueryAll() {
//		fail("Not yet implemented");
		List<Object> l = (List<Object>) s.queryAll();
		System.out.println(l.size());
		assertNotNull(l);
	}

	@Test
	public void testUpdate() {
//		fail("Not yet implemented");
		TDepartmentConfig t = (TDepartmentConfig) s.queryById(2);
		t.setConfigType(55);
		s.update(t);
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TDepartmentConfig t = new TDepartmentConfig();
		t.setConfigType(2);
		t.setConfigValue(22);
		t.setDepartmentId(131);
		s.insert(t);
		System.out.println(s.insert(t));
	}

//	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(1);
	}

}
