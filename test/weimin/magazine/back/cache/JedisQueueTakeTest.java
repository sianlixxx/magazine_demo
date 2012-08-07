package weimin.magazine.back.cache;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weimin.magazine.back.cache.JedisQueue;
import weimin.magazine.back.vo.Weibo;

public class JedisQueueTakeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTake() throws InterruptedException {
        JedisQueue<Weibo> weiboQueue = JedisQueue.newQ(Weibo.class);
        while(true){
            Weibo weibo = weiboQueue.take();
            System.out.println(weibo.getText());
        }
    }

}
