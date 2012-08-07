package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.util.Tools;

public class TDepartmentDaoImplTest {
	ApplicationContext applicationContext = null;
	TDepartmentDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TDepartmentDaoImpl)applicationContext.getBean("tDepartmentDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TDepartment t = (TDepartment) s.queryById(3);
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
		TDepartment t = (TDepartment) s.queryById(2);
		t.setDepartmentDomain("www.baidu.com");
		s.update(t);
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TDepartment t = new TDepartment();
		t.setCreaterUserId((long) 1);
		t.setDepartmentLevel(1);
		t.setDepartmentScore(777);
		t.setDepartmentType(2);
		t.setDescription("newzgtc");
		t.setEditerChiefCount(88);
		t.setEditerCount(77);
		t.setName("newzgtc");
		t.setReleaseCount(4227);
		t.setSubscribeCount(455);
		t.setSubscribeCount(488);
		t.setTotalContributeCount(499);
		t.setTotalPublishCount(488);
		t.setTotalReadCount(4888);
		t.setDepartmentDomain("www.baidu.com");
		t.setCreatedAt(Tools.getDate());
		t.setLogoUrl("http://t2.gstatic.com/images?q=tbn:ANd9GcSAIsu2X-0fDPfE80QExWQ9VhUYpEKQMFI0_d72M6pmkADFg1ZJ");
//		Date d = new Date();
//		t.setCreatedAt(d);
		System.out.println("insert ............");
		System.out.println(s.insert(t));
		t.setDescription("检索到与用户具有相同标签的杂志总计个数为：");
		t.setName("杂志总计");
		t.setLogoUrl("http://t3.baidu.com/it/u=402540699,3315095924&fm=52&gp=0.jpg");
		System.out.println(s.insert(t));
		t.setDescription("java.sql.PreparedStatement:27] {pstm-100037} Types: [java.lang.String]");
		t.setName("pstm-100037");
		t.setLogoUrl("http://t1.gstatic.com/images?q=tbn:ANd9GcSoLUanpYfqUYq1cxQVAtg-bBjij1PFfpN0bGe4l5LgNC_y_8I5");
		System.out.println(s.insert(t));
		t.setDescription("If you want you can bind a single interface, if the bind option is not");
		t.setName("2b");
		t.setLogoUrl("http://t3.baidu.com/it/u=1230719647,3066496499&fm=52&gp=0.jpg");
		System.out.println(s.insert(t));
		t.setDescription("地对地导弹");
		t.setName("哈巴");
		t.setLogoUrl("http://t1.baidu.com/it/u=3616573585,1646016771&fm=52&gp=0.jpg");
		System.out.println(s.insert(t));
		t.setDescription("http://t1.baidu.com/it/u=2165710914,3325588684&fm=52&gp=0.jpg");
		t.setName("模特");
		t.setLogoUrl("http://t1.baidu.com/it/u=2165710914,3325588684&fm=52&gp=0.jpg");
		System.out.println(s.insert(t));
		t.setDescription("If the key already exists and is a string, this command appends the provided value at the end of the string.");
		t.setName("新能源车");
		t.setLogoUrl("http://t1.baidu.com/it/u=362215551,961594893&fm=52&gp=0.jpg");
		System.out.println(s.insert(t));
		t.setDescription("开发者可以在应用控制台填写取消授权回调页，当用户取消你的应用授权时，开放平台会回调你填写的这个地址。并传递给你以");
		t.setName("兰博基尼");
		t.setLogoUrl("http://t3.baidu.com/it/u=2444299734,3503766699&fm=52&gp=0.jpg");
		System.out.println(s.insert(t));
		t.setDescription("如果你的应用是站外网页应用，你需要在平台网站填写redirect_url（授权回调页），才能使用OAuth2.0。");
		t.setName("军车");
		t.setLogoUrl("http://t3.baidu.com/it/u=2637371182,3929219798&fm=52&gp=0.jpg");
		System.out.println(s.insert(t));
		
	}

//	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(1);
	}

//	@Test
	public void testaddSubscribeCount(){
		s.addSubscribeCount(1,2);
	}
	
//	@Test
    public void testgetTop(){
        System.out.println( "............."+s.getTop().get(0));
    }
}
