package weimin.magazine.back.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.util.SystemProperties;


public class OAuth2ServiceTest {
	ApplicationContext applicationContext = null;
	OAuth2Service oAuth2Service ;
	SystemProperties s;

	@Before
	public void setUp() throws Exception {
		applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
		oAuth2Service = (OAuth2Service) applicationContext.getBean("oAuth2Service") ;
		s = new SystemProperties();
		s.loadSystemProperties();
		
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testOpenURL() {
//		fail("Not yet implemented");
		oAuth2Service.openURL();
	}

	@Test
	public void testSaveAccessToken() {
//		fail("Not yet implemented");
		String code = "da1f82e021b26b671aad09395a4fdbb3";
		System.out.println(oAuth2Service.oAuthByCode(code).getUserId());
	}

}
