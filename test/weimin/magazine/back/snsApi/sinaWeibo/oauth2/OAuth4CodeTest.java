
package weimin.magazine.back.snsApi.sinaWeibo.oauth2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.snsApi.sinaWeibo.oauth2.OAuth4Code;

public class OAuth4CodeTest {
	
	OAuth4Code o = new OAuth4Code();


	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testOpenURL() {
//		fail("Not yet implemented");
		o.openURL();
	}

	@Test
	public void testGetAccessToken() {
//		fail("Not yet implemented");
		String code = "4ce984842cfe4b4293ebfbcf7e7a77ce";
		TAccessToken a = o.getAccessTokenByCode(code);
		System.out.println(a.toString());
		System.out.println(a.getAccessToken());
		
	}

}
