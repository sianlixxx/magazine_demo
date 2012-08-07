package weimin.magazine.back.cache;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weimin.magazine.back.cache.JedisQueue;
import weimin.magazine.back.vo.Weibo;

public class JedisQueuePushObjectTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPushCollectionOfT() throws InterruptedException {
//        fail("Not yet implemented");
        JedisQueue<Weibo> weiboQueue = JedisQueue.newQ(Weibo.class); 
        while(true){
            for(int i = 0 ;i < 10; i++){
                Thread.sleep(100);
                Weibo weibo = new Weibo();
                weibo.setText(String.valueOf(i));
                weiboQueue.push(weibo);
            }
            Thread.sleep(3000);
        }
       
    }

}
