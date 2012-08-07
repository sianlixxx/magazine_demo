package weimin.magazine.back.snsApi.sinaWeibo.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import weimin.magazine.back.dao.pojo.TUser;

public class ReadUserTest {
	String ac = "2.00Mu98CD7kMXAC4ab896788ecAyFZE";
	long uid = 2784497140l;
	ReadUser  r;

	@Before
	public void setUp() throws Exception {
		r = new ReadUser(ac);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShowUserById() {
//		fail("Not yet implemented");
		TUser user = r.showUserById(uid);
		System.out.println(user.toString());
	}

	@Test
	public void testTransform2pojo() {
//		fail("Not yet implemented");
		TUser user = r.showUserById(uid);
		System.out.println(user.getName());
	}

}
