package weimin.magazine.back.snsApi.sinaWeibo.tags;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weibo4j.model.WeiboException;

public class ReadTagsTest {
	
	String ac = "2.00Mu98CDooILhD2539fa2b3eScNGRE";
	long uid = 2784497140l;
	ReadTags r ;
	

	@Before
	public void setUp() throws Exception {
		r = new ReadTags(ac);
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testGetTags() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserTags() throws WeiboException {
//		fail("Not yet implemented");
		Set<String> labels = r.showUserTags(uid);
		for(String label:labels){
			System.out.println(label);
		}
	}

}
