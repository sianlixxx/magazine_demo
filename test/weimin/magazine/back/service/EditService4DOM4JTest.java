package weimin.magazine.back.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import weimin.magazine.back.dao.impl.TAccessTokenDaoImpl;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.util.SystemProperties;

public class EditService4DOM4JTest {
    ApplicationContext applicationContext = null;
    SystemProperties s;
    EditService e;
    TAccessTokenDaoImpl t;
    TAccessToken tAccessToken;
    @Before
    public void setUp() throws Exception {
       this.init();
       this.createDiv();
    }
    
    private void init() {
        applicationContext =new FileSystemXmlApplicationContext("conf/applicationContext*.xml");
        t = (TAccessTokenDaoImpl) applicationContext.getBean("tAccessTokenDAO") ;
        e = (EditService) applicationContext.getBean("editService") ;
        s = new SystemProperties();
        s.loadSystemProperties();
    }
    

    private void createDiv() {
        String div1 = " <div class=\"mag-weibo-title\" style=\"text-align: left\">" +
        		            "<h2>笑话</h2>" +
        		        "</div>" +
        		            
        		        "<div class=\"mag-weibo-content\"  style=\"float:left; position:relative;\">" +
        		            " 某日数学老师下课的时候说：“同学们，马上就要中考了，为了让大家都能考出好成绩，昨晚我去书店找了一份非常好的辅导资料，上面好多内容都是前几中考的内容，我建议…”没说完被一个男声打断：“别说那么多废话了，开个价吧！” [转]" +
        				"</div>" +
        		        
        				"div class=\"mag-weibo-img\" style=\"float:right;\" >" +
        					"<img width=\"120\" height=\"150\" src=\"http://ww4.sinaimg.cn/thumbnail/6ffff2e2jw1dsz6o8xa9zg.gif\" >" +
        					"</img>" +
        				"</div>" +
        					
        				"<div class=\"mag-weibo-meta\" style=\"float:left;position: relative;\">" +
        				    "<span mag-auther>" +
        				        "作者：笑话全集" +
        				    "</span>" +
        				     "<span mag-command>" +
        				           "评论 (99)" +
        				     "</span>" +
        				     "<span mag-rt>" +
        				           "转发(2)" +
        				     "</span>" +
        				"</div>";
    }
        						

    @After
    public void tearDown() throws Exception {
        
    }

    @Test
    public void testPublishMagazine() {
        fail("Not yet implemented");
    }

}
