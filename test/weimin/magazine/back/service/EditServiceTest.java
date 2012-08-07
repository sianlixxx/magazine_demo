package weimin.magazine.back.service;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import weimin.magazine.back.dao.impl.TAccessTokenDaoImpl;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.dao.pojo.TDepartmentEditor;
import weimin.magazine.back.dao.pojo.TMagazineFinal;
import weimin.magazine.back.dao.pojo.TUserContribute;
import weimin.magazine.back.vo.Weibo;
import weimin.magazine.util.SystemProperties;
import weimin.magazine.util.Tools;

public class EditServiceTest {
	ApplicationContext applicationContext = null;
	SystemProperties s;
	EditService e;
	TAccessTokenDaoImpl t;
	TAccessToken tAccessToken;
	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		t = (TAccessTokenDaoImpl) applicationContext.getBean("tAccessTokenDAO") ;
		e = (EditService) applicationContext.getBean("editService") ;
		s = new SystemProperties();
		s.loadSystemProperties();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testCreateDepartment() {
//		fail("Not yet implemented");
		tAccessToken = (TAccessToken) t.queryById(1000000009l);
		e.createDepartment(tAccessToken.getUserId());
	}

	@Test
	public void testShowEditors() {
//		fail("Not yet implemented");
		List<TDepartmentEditor> editors = e.showEditors(5435l);
		System.out.println(editors.size());
	}
	
//	@Test
    public void testCreateMagazine(){
	    tAccessToken = (TAccessToken) t.queryById(1l);
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
        e.createMagazine(t);
	}
	



}
