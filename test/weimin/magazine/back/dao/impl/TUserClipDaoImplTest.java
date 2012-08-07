package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TUserClip;

public class TUserClipDaoImplTest {
	ApplicationContext applicationContext = null;
	TUserClipDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TUserClipDaoImpl)applicationContext.getBean("tUserClipDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TUserClip t = (TUserClip) s.queryById(1);
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
		TUserClip t = (TUserClip) s.queryById(1);
		t.setUserId((long) 222333);
		s.update(t);
		
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TUserClip t = new TUserClip();
		t.setContent("clip");
		t.setDepartmentId((long) 123456);
		t.setDomain("www.weimin.com/23545/clip.jsp");
		t.setMagazineId((long) 231245);
		s.insert(t);
		
	}

	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(2);
	}

}
