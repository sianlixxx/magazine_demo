package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TMagazineModel;

public class TMagazineModelDaoImplTest {
	ApplicationContext applicationContext = null;
	TMagazineModelDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TMagazineModelDaoImpl)applicationContext.getBean("tMagazineModelDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TMagazineModel t = (TMagazineModel) s.queryById(1);
		assertNotNull(t);
	}

	@Test
	public void testQueryAll() {
//		fail("Not yet implemented");
		List<Object> l = s.queryAll();
		System.out.println(l.size());
		assertTrue(l.size()!=0);
	}

	@Test
	public void testUpdate() {
//		fail("Not yet implemented");
		TMagazineModel t = (TMagazineModel) s.queryById(1);
		t.setModelContent("updateformodel");
		s.update(t);
		
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TMagazineModel t = new TMagazineModel();
		t.setCteateBy((long) 5421);
		t.setModelContent("game");
		t.setModelType(2);
		s.insert(t);
		
	}

	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(2);
	}

}
