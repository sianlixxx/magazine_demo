package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import weimin.magazine.back.dao.pojo.TMagazineFinal;
import weimin.magazine.util.Tools;


public class TMagazineFinalDaoImplTest {
	ApplicationContext applicationContext = null;
	TMagazineFinalDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TMagazineFinalDaoImpl)applicationContext.getBean("tMagazineFinalDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TMagazineFinal t = (TMagazineFinal) s.queryById(1);
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
		TMagazineFinal t = (TMagazineFinal) s.queryById(1);
//		t.setMagazineThemeId(1);
		t.setCommentCount(222);
		t.setDescription("thisisupdatatest");
		s.update(t);
		
	}

//	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TMagazineFinal t = new TMagazineFinal ();
		t.setCoverPic("http://ww3.sinaimg.cn/bmiddle/67dd74e0gw1duqfw4zhc0j.jpg");
		t.setCommentCount(22);
		t.setContributeCount(2);
		t.setCreaterUserId((long) 1);
		t.setMagazineUrl("www.baidu.com/test/magazine");
		t.setCreatedAt(Tools.getDate());
		t.setDepartmentId(1l );
		t.setMagazineUrl("http://172.20.50.19:8080/magazine_demo/html/show/showMagazine.html");
		t.setName("it");
		t.setPublishAt(Tools.getDate());
		t.setSerialNumber(0);
		t.setStatus(0);
		s.insert(t);
		for(int i = 1 ;i<=4;i++){
		    t.setCreaterUserId((long)i);
		    t.setStatus(i);
		    s.insert(t);
		}
	}

//	@Test
	public void testDeleteById() {
		int id = 3;
		TMagazineFinal t = (TMagazineFinal) s.queryById(id);
		assertNotNull(t);
		s.deleteById(id);
	}
	
//	@Test
    public void testCreateMagazine(){
	    TMagazineFinal t = new TMagazineFinal ();
        t.setCoverPic("http://ww3.sinaimg.cn/bmiddle/67dd74e0gw1duqfw4zhc0j.jpg");
        t.setCommentCount(22);
        t.setContributeCount(2);
        t.setCreaterUserId((long) 1);
        t.setMagazineUrl("www.baidu.com/test/magazine");
        t.setCreatedAt(Tools.getDate());
        t.setDepartmentId(1l );
        t.setMagazineUrl("http://172.20.50.19:8080/magazine_demo/html/show/showMagazine.html");
        t.setName("it");
        t.setPublishAt(Tools.getDate());
        t.setSerialNumber(0);
        t.setStatus(0);
        s.createMagazine(t);
        for(int i = 5 ;i<=40;i++){
            t.setCreaterUserId((long)i);
            t.setStatus(i);
            t.setDepartmentId(Long.valueOf(i));
            s.createMagazine(t);
        }
	}
	
	@Test
    public void testqueryCurrent(){
	    TMagazineFinal t = s.queryCurrent(3);
	    assertNotNull(t);
	}

}
