package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import weimin.magazine.back.dao.pojo.TMagazineTheme;

public class TMagazineThemeDaoImplTest {
	ApplicationContext applicationContext = null;
	TMagazineThemeDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TMagazineThemeDaoImpl)applicationContext.getBean("tMagazinethemeDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TMagazineTheme t = (TMagazineTheme) s.queryById(1);
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
		TMagazineTheme t = new TMagazineTheme ();
//		t.setMagazineThemeId(1);
		
		t.setMagazineThemeDes("this is test");
		
		s.update(t);
		
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TMagazineTheme t = new TMagazineTheme ();
		t.setMagazineThemeUsed(23);
		t.setMagazineThemeType(2);
		t.setMagazineThemeContent("tehs");
		s.insert(t);
		System.out.println(s.insert(t));
	}

//	@Test
	public void testDeleteById() {
		int id = 3;
		TMagazineTheme t = (TMagazineTheme) s.queryById(id);
		assertNotNull(t);
		s.deleteById(id);
	}

}
