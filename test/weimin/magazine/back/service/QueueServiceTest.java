package weimin.magazine.back.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weimin.magazine.back.cache.JedisQueue;
import weimin.magazine.back.cache.message.MSGCollectUserInfo;
import weimin.magazine.back.dao.pojo.TAccessToken;
import weimin.magazine.back.vo.Weibo;

public class QueueServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testQueueService() throws InterruptedException {
        JedisQueue<Weibo> weiboQueue = JedisQueue.newQ(Weibo.class); 
        JedisQueue<MSGCollectUserInfo> collectUserInfoQueue = JedisQueue.newQ(MSGCollectUserInfo.class); 
        
        TAccessToken tAccessToken = new TAccessToken();
        tAccessToken.setUserId(1l);
        Weibo weibo = new Weibo();
        weibo.settAccessToken(tAccessToken);
        MSGCollectUserInfo mSGCollectUserInfo = new MSGCollectUserInfo();
        mSGCollectUserInfo.setType(0);
        mSGCollectUserInfo.setUserId(1l);
        while(true){
          
            for(int i = 0 ;i < 10; i++){
                Thread.sleep(100);
                weibo.setText(String.valueOf(i));
                weiboQueue.push(weibo);
            }
            for(int i = 0 ;i < 1; i++){
                Thread.sleep(100);
                collectUserInfoQueue.push(mSGCollectUserInfo);
            }
        }
    }

}
