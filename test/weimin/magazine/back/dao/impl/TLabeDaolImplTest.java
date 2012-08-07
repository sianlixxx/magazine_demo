package weimin.magazine.back.dao.impl;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.impl.TLabelDaoImpl;
import weimin.magazine.back.dao.pojo.TLabel;
import weimin.magazine.util.Tools;

public class TLabeDaolImplTest {
	
	ApplicationContext applicationContext = null;
	TLabelDaoImpl s = null;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		s = (TLabelDaoImpl)applicationContext.getBean("tLabelDAO");
	}

	@After
	public void tearDown() throws Exception {
		
	}

//	@Test
	public void testQueryById() {
//		fail("Not yet implemented");
		TLabel l = (TLabel) s.queryById(154);
		assertNotNull(l);
	}
	
//	@Test
	public void testQueryAll() {
//		fail("Not yet implemented");
		List<Object> l = (List<Object>) s.queryAll();
		assertNotNull(l);
	}

//	@Test
	public void testUpdate() {
//		fail("Not yet implemented");
		TLabel l = new TLabel();
//		l.setDomainId("12");
		l.setName("update22");
		
	}

	@Test
	public void testInsert() {
//		fail("Not yet implemented");
		TLabel l = new TLabel();
//		l.setDomainId("101");
		l.setUserId(1);
		l.setName("it");
		l.setDomainType(false);// 0= false ,1 = ture
		l.setCreatedAt(Tools.getDate());//标签创建时间为用户注册时间
		
//		for(int i =0 ; i<10;i++){
//			l.setFrequency(i+1);
//			l.setUserId(1+i);
//			System.out.println(s.insert(l));
//		}
		
		l.setDomainType(true);
		for(int i =10 ; i<50;i++){
            l.setFrequency(i+10);
            l.setUserId(i+ 1);
            System.out.println(s.insert(l));
        }
		
		
		
	}

//	@Test
	public void testDeleteById() {
//		fail("Not yet implemented");
		s.deleteById(101);
	}
	
//	@Test
	public void testqueryLabelsByUserId() {
//		fail("Not yet implemented");
		long userId = 1000000009l;
		Set<String> labels = new LinkedHashSet<String>();
		// 通过dao层从mysql中获取用户标签 labels
					List<TLabel> domainLabel = s.queryLabelsByUserId(userId);
					for (TLabel l : domainLabel) {
						String label = l.getName();
						labels.add(label);
						System.out.println(label);
						System.out.println("......"+l.getDomainType());
					}
					System.out.println(labels.size());
	}
	
//	@Test
	public void testqueryDepartmentIdsByLabel() {
		List<TLabel> l = s.queryDepartmentIdsByLabel("it");
		System.out.println("........."+l.size());
		
	}
	
	
	@Test
	public void testcountLabelsFrequency() {
		System.out.println(s.countLabelsFrequency("it"));
	}
	
//	@Test
	public void testdeleteUserLabel() {
		s.deleteLabel(1,"test");
	}
	
//	@Test
	public void testqueryRecommendLabels() {
//		fail("Not yet implemented");
		List<TLabel> TLabels = s.queryRecommendLabels();
		//TODO系统默认推荐标签个数，暂定为20
		int size = 10;
		for(int i = 0 ; i < size ;i++){
			System.out.println(TLabels.get(i).getName());
		}	
	}
}
