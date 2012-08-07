package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TDepartmentEditor;

public class TDepartmentEditorDaoImplTest {
	ApplicationContext applicationContext = null;
	 TDepartmentEditorDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TDepartmentEditorDaoImpl)applicationContext.getBean("tDepartmentEditorDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TDepartmentEditor t = (TDepartmentEditor) s.queryById(2);
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
		TDepartmentEditor t = (TDepartmentEditor) s.queryById(4);
		t.setEditerChief(false);
		s.update(t);
		
	}

//	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TDepartmentEditor t = new TDepartmentEditor();
		t.setDepartmentId((long) 111);
		t.setEditerChief(true);
		t.setUserId((long) 5421);
		s.insert(t);
		System.out.println(s.insert(t));
	}

//	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(1);
	}
	
	@Test
	public void testQueryByDepartmentId() {
//		fail("Not yet implemented");
		List<TDepartmentEditor> l = s.queryByDepartmentId(5435l);
		System.out.println(l.size());
	}

}
