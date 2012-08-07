package weimin.magazine.back.cache;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weimin.magazine.back.cache.JedisQueue;
import weimin.magazine.back.vo.Weibo;
import weimin.magazine.util.Tools;


public class JedisQueuePushTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPushTArray() throws InterruptedException {
//        fail("Not yet implemented");
        JedisQueue<Weibo> weiboQueue = JedisQueue.newQ(Weibo.class); 
        ArrayList<Weibo> weibos = new ArrayList<Weibo>();
       
        for(int i = 0 ;i < 10; i++){
            Thread.sleep(100);
            Weibo weibo = new Weibo();
            weibo.setText(Tools.getDate().toString());
            weibos.add(weibo);
        }
        
        while(true){
            weiboQueue.push(weibos);
            Thread.sleep(3000);
        }
       
    }

}
