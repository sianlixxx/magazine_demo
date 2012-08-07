package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TTopMagazine;

public class TTopMagazineDaoImplTest {
	ApplicationContext applicationContext = null;
	TTopMagazineDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TTopMagazineDaoImpl)applicationContext.getBean("tTopMagazineDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TTopMagazine t = (TTopMagazine) s.queryById(1);
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
		TTopMagazine t = (TTopMagazine) s.queryById(1);
		t.setDepartmentDomain("www.google.com");
		s.update(t);
		
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TTopMagazine t = new TTopMagazine();
		t.setDepartmentDomain("www.baidu.com");
		t.setDepartmentType(2);
		t.setExRanking(22);
		t.setExSubRanking(3);
		t.setRanking(33);
		t.setSubRanking(32);
		
		s.insert(t);
		
	}

	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(2);
	}

}
