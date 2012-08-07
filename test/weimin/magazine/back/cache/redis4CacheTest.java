package weimin.magazine.back.cache;


import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weimin.magazine.back.cache.Redis4Cache;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.util.SystemProperties;
import weimin.magazine.util.Tools;

public class redis4CacheTest {
	SystemProperties s = new SystemProperties();
	Redis4Cache redis4Cache;

	@Before
	public void setUp() throws Exception {
		s.loadSystemProperties();
		redis4Cache = new Redis4Cache();
		
	}
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryTopByTheme() {
//		fail("Not yet implemented");
		String theme = "IT";
		Map<String, String> hash   = new HashMap<String, String>();
		hash.put("0", "1");
		hash.put("1", "2");
		redis4Cache.addTopByTheme(theme, hash);
		redis4Cache.queryTopByTheme("i");
		
	}

//	@Test
	public void testQueryTop() {
//		fail("Not yet implemented");
		Map<String, String> hash   = new HashMap<String, String>();
		hash.put("0", "1");
		hash.put("1", "2");
		redis4Cache.addTop(hash);
		redis4Cache.queryTop();
	}


	
//	@Test
	public void testQueryUserLabels() {
//		fail("Not yet implemented");
		long userId = 1000000009l;
		String label = "lyf";
		redis4Cache.addLabel2User(userId, label);
		String label2 = "man";
		redis4Cache.addLabel2User(userId, label2);
		String label3 = "it";
		redis4Cache.addLabel2User(userId, label3);
		Set<String> labels = redis4Cache.queryUserLabels(userId);
		System.out.println(labels);
		Iterator<String> iterator =  labels.iterator();
		while (iterator.hasNext()){
			 System.out.println(iterator.next()+ ";");
		}
		
		long userId2 = 1000000008l;
		String label11 = "game";
		redis4Cache.addLabel2User(userId2, label11);
		String label12 = "th";
		redis4Cache.addLabel2User(userId2, label12);
		String label13 = "it";
		redis4Cache.addLabel2User(userId2, label13);
	}
	
	@Test
	public void testQueryDepartmentLabels() {
//		fail("Not yet implemented");
		long departmentId = 1l;
		String label = "game";
		redis4Cache.addLabel2User(departmentId, label);
		String label2 = "th";
		redis4Cache.addLabel2User(departmentId, label2);
		String label3 = "it";
		redis4Cache.addLabel2User(departmentId, label3);
		Set<String> labels = redis4Cache.queryDepartmentLabels(departmentId);
		System.out.println(labels);
		Iterator<String> iterator =  labels.iterator();
		while (iterator.hasNext()){
			 System.out.println(iterator.next()+ ";");
		}
		
		long departmentId2 = 2l;
		String label11 = "lfy";
		redis4Cache.addLabel2User(departmentId2, label11);
		String label12 = "man";
		redis4Cache.addLabel2User(departmentId2, label12);
		String label13 = "it";
		redis4Cache.addLabel2User(departmentId2, label13);
	}
	
	@Test
	public void testaAddAccessTokenByUid(){
//		redis4Cache.addAccessTokenByUid(2784497140l, "2.00Mu98CD7kMXAC4f73ce2eaa0kJJLD");
//		redis4Cache.addAccessTokenByUid(2248448383l, "2.00Mu98CD7kMXAC4f73ce2eaa0kJJLD");
//		redis4Cache.addAccessTokenByUid(2784497140l, "2.00");
		System.out.println(redis4Cache.queryAccessTokenByUid(2784497141l));
		redis4Cache.flushDb();
		
	}
	
//	@Test
	public void testqueryLabels(){
		List<String> labels = new ArrayList<String>();
		labels.add("70后");labels.add("game");
		labels.add("90后");labels.add("it");
		labels.add("xx后");labels.add("定身法后");
		labels.add("请求后");labels.add("顺达副食后");
		labels.add("22后");labels.add(" 发生地分后");
		labels.add("44后");labels.add("福师大fds后");
		labels.add("55后");labels.add("的发放等后");
		labels.add("熬啊后");labels.add("5厹后");
		labels.add("80后");labels.add("man");
		labels.add("呵呵后");labels.add("IT");
		labels.add("拜44后");labels.add("慈悲后");
		labels.add("方法4后");labels.add("佛挡杀佛的后");
		labels.add("试试后");labels.add("打发斯蒂芬后");
		labels.add("有经验后");labels.add("O(∩_∩)O~后");
		labels.add("得到后");labels.add("后");
		
		System.out.println(labels);
		redis4Cache.addSuggestionsLabels(labels);
		System.out.println(redis4Cache.querySuggestionsLabels());
	}
	
	
	@Test
	public void testaddRecomments() throws ParseException{
		Set<String> rs = redis4Cache.queryRecomments(1);
		Iterator<String> iterator =  rs.iterator();
		while (iterator.hasNext()){
			 System.out.println(iterator.next()+ ";");
		}
		
		
	}

//	   @Test
	    public void testaTranc(){
	     
	      redis4Cache.addUid4UserId(2784497140l, 1l);
	      redis4Cache.addUserId4Uid(1, 2784497140l); 
	     // assertequals
	      Assert.assertEquals(String.valueOf(redis4Cache.queryUidByUserId(1)),"2784497140l");
	      Assert.assertEquals(String.valueOf(redis4Cache.queryUidByUserId(2784497140l)),"1");
	     
	    }
	   
	   @Test
       public void testqueryUserIdByUid(){
	       long id = redis4Cache.queryUserIdByUid(1254);
	       System.out.println(id == 0);
	   }
	   
	   @Test
       public void testqueryDepartment(){
	       redis4Cache.queryDepartment(1l);
	   }


}
