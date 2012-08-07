package weimin.magazine.back.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weimin.magazine.back.dao.impl.TAccessTokenDaoImpl;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.exception.SinaWeiboExpiredTokenException;
import weimin.magazine.back.vo.Weibo;
import weimin.magazine.util.SystemProperties;

public class WeiboServiceTest {ApplicationContext applicationContext = null;
	WeiboService w ;
	SystemProperties s;
	TAccessTokenDaoImpl t;
	TAccessToken tAccessToken;


@Before
public void setUp() throws Exception {
	applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
	w = new WeiboService();
	t = (TAccessTokenDaoImpl) applicationContext.getBean("tAccessTokenDAO") ;
	s = new SystemProperties();
	s.loadSystemProperties();
	tAccessToken = (TAccessToken) t.queryById(1);
}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShowUserTimeline() {
//		fail("Not yet implemented");
		StatusWapper s = w.showUserTimeline(tAccessToken);
		System.out.println(s.getTotalNumber());
	}

	@Test
	public void testShowWeibo() {
//		fail("Not yet implemented");
		String id = "3461833036190243";
		Status s = w.showWeibo(tAccessToken, id);
		System.out.println(s.toString());
	}

	@Test
	public void testShowFriendsTimeline() {
//		fail("Not yet implemented");
		StatusWapper s = w.showFriendsTimeline(tAccessToken);
		System.out.println(s.getTotalNumber());
	}

	@Test
	public void testPublishWeibo() throws SinaWeiboExpiredTokenException {
//		fail("Not yet implemented");
		Weibo s = new Weibo();
		s.setAnnotations("[{\"topic\":\"game\",\"time\":\"2012\"}]");
		s.setText("thisistest今天下雨010-66015367北京市 西城区 西单北大街178号 华南大厦中友百货一层3");
		s.settAccessToken(tAccessToken);
		Status stutas = w.publishWeibo( s);
		System.out.println(stutas.toString());
	}

}
