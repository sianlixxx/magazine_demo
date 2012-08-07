package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import weimin.magazine.back.dao.pojo.TDepartmentComment;

public class TDepartmentCommentDaoImplTest {
	ApplicationContext applicationContext = null;
	TDepartmentCommentDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TDepartmentCommentDaoImpl)applicationContext.getBean("tDepartmentCommentDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TDepartmentComment t = (TDepartmentComment) s.queryById(1);
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
		TDepartmentComment t = (TDepartmentComment) s.queryById(1);
		t.setContent("updata");
		s.update(t);
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TDepartmentComment t = new TDepartmentComment();
		t.setContent("this is comment");
		
		t.setLevel(2);
		t.setScore(3454);
		t.setUserId((long) 5421);
		System.out.println(s.insert(t));
	}

//	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		
	}

}
