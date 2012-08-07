package weimin.magazine.back.cache;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weimin.magazine.back.cache.Redis4Cache;
import weimin.magazine.back.dao.pojo.TDepartment;
import weimin.magazine.back.dao.pojo.TUser;
import weimin.magazine.util.SystemProperties;
import weimin.magazine.util.Tools;

public class Redis4LoadTest {
    
    SystemProperties s = new SystemProperties();
    Redis4Cache redis4Cache;
    int size =100000;
    TDepartment t;
    TUser u;

    @Before
    public void setUp() throws Exception {
        
        s.loadSystemProperties();
        redis4Cache = new Redis4Cache();
       
        t = new TDepartment();
        t.setCreaterUserId((long) 1);
        t.setDepartmentLevel(1);
        t.setDepartmentScore(777);
        t.setDepartmentType(2);
        t.setDescription("newzgtc");
        t.setEditerChiefCount(88);
        t.setEditerCount(77);
        t.setName("newzgtc");
        t.setReleaseCount(4227);
        t.setSubscribeCount(455);
        t.setSubscribeCount(488);
        t.setTotalContributeCount(499);
        t.setTotalPublishCount(488);
        t.setTotalReadCount(4888);
        t.setDepartmentDomain("www.baidu.com");
        t.setCreatedAt(Tools.getDate());
        t.setLogoUrl("http://t2.gstatic.com/images?q=tbn:ANd9GcSAIsu2X-0fDPfE80QExWQ9VhUYpEKQMFI0_d72M6pmkADFg1ZJ");
        t.setCreatedAt(Tools.getDate());
        
        u = new TUser();
        u.setAllowAllActMsg(true);
        u.setAllowAllComment(true);
        u.setAvatarLarge("aass");
        u.setBiFollowersCount(2);
        u.setBlogUrl("www.baidu.com");
        u.setCity(22);
        u.setCreatedAt(Tools.getDate());
        u.setCreateEnabled(false);
        u.setDescription("ssssssssssssss");
        u.setDomain("");
        u.setFavouritesCount(22);
        u.setFollowersCount(33);
        u.setFollowMe(false);
        u.setFriendsCount(521);
        u.setGender("genge ");
        u.setGeoEnabled(false);
        u.setLastLoginTime(Tools.getDate());
        u.setLocation("dfda  ");
        u.setName("th");
        u.setOnlineStatus(0);
        u.setParticipateCount(22);
        u.setProfileImageUrl("jsdljfdklj klfjl ja");
        u.setProvince(2);
        u.setScreenName("sss");
        u.setStatusesCount(334);
        u.setSubmissionCount(1454);
        u.setSubscribeCount(4574);
        u.setUid(123456l);
        u.setUserDomain("sddd");
        u.setUserSource(235);
        u.setVerified(true);
        u.setVerifiedReason("dddddddddddddd");
        u.setVerifiedType(2);
        u.setWeihao("1489797");
        
        
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
    public void testQueryUserIdByUid() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddUserId4Uid() {
//        fail("Not yet implemented");
        for(int i=0 ;i<size;i++){
            redis4Cache.addUserId4Uid(10000+i,10000+i);
        }
        
    }

//    @Test
    public void testQueryUidByUserId() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddUid4UserId() {
//        fail("Not yet implemented");
        for(int i=0 ;i<size;i++){
            redis4Cache.addUid4UserId(10000+i,10000+i);
        }
    }

//    @Test
    public void testQueryTopByTheme() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddTopByTheme() {
//        fail("Not yet implemented");
        for(int i=0 ;i<10;i++){
            Map<String ,String > hash = new HashMap<String ,String >();
            for(int j=0 ;j<10;j++){
                hash.put(String.valueOf(j), "teststesljljljkljkljlj");
            }
            redis4Cache.addTopByTheme(String.valueOf(i), hash);
        }
    }

//    @Test
    public void testQueryTop() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddTop() {
//        fail("Not yet implemented");
      
            Map<String ,String > hash = new HashMap<String ,String >();
            for(int j=0 ;j<100;j++){
                hash.put(String.valueOf(j), "teststesljljljkljkljlj");
            }
            redis4Cache.addTop(hash);
        
    }

//    @Test
    public void testQueryUserLabels() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddLabel2User() {
//        fail("Not yet implemented");
        for(int i=0 ;i<size;i++){
            for(int j=0 ;j<10;j++){
                redis4Cache.addLabel2User(10000+i, "testsfds");
            }
        }
    }

//    @Test
    public void testDelUserLabel() {
        fail("Not yet implemented");
    }

//    @Test
    public void testQueryDepartmentLabels() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddLabel2Department() {
//        fail("Not yet implemented");
        for(int i=0 ;i<size;i++){
            for(int j=0 ;j<10;j++){
                redis4Cache.addLabel2Department(10000+i, "testsfds");
            }
        }
    }

//    @Test
    public void testQueryAllUid() {
        fail("Not yet implemented");
    }

//    @Test
    public void testQueryAccessTokenByUid() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddAccessTokenByUid() {
//        fail("Not yet implemented");
        for(int i=0 ;i<size;i++){
            redis4Cache.addAccessTokenByUid(10000+i, "weimin.magazine.util.SystemProperties");
            }
    }
    

//    @Test
    public void testQuerySuggestionsLabels() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddSuggestionsLabels() {
//        fail("Not yet implemented");
        List<String >labels = new ArrayList<String >();
        for (int i = 0; i < 100; i++) {
            labels.add("sss"+String.valueOf(i));
        }
        redis4Cache.addSuggestionsLabels(labels);
    }

//    @Test
    public void testDeleteLabels() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddRecomments() {
//        fail("Not yet implemented");
        Set<TDepartment> recomments = new LinkedHashSet<TDepartment>();
        for (int j = 0; j < 100; j++) {
            t.setDepartmentId(10000+Long.valueOf(j));
            recomments.add(t);
        }
        for (int i = 0; i < size; i++) {
            redis4Cache.addRecomments(10000+i, recomments);
        }
        
    }

//    @Test
    public void testQueryRecomments() {
        fail("Not yet implemented");
    }

//    @Test
    public void testDeleteRecomments() {
        fail("Not yet implemented");
    }

//    @Test
    public void testDeleteSubscribe() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddDepartment() {
//        fail("Not yet implemented");
        for(int i=0 ;i<size;i++){
            redis4Cache.addDepartment(t);
        }
    }

//    @Test
    public void testQueryDepartment() {
        fail("Not yet implemented");
    }

//    @Test
    public void testDeleteDepartment() {
        fail("Not yet implemented");
    }

//    @Test
    public void testAddWeiboTagTid() {
        fail("Not yet implemented");
    }

//    @Test
    public void testQueryWeiboTagTid() {
        fail("Not yet implemented");
    }

//    @Test
    public void testQueryEmotions() {
        fail("Not yet implemented");
    }

//    @Test
    public void testAddEmotions() {
        fail("Not yet implemented");
    }

//    @Test
    public void testDeleteThemes() {
        fail("Not yet implemented");
    }

//    @Test
    public void testAddSuggestionsThemes() {
        fail("Not yet implemented");
    }

//    @Test
    public void testQuerySuggestionsThemes() {
        fail("Not yet implemented");
    }

//    @Test
    public void testFlushDb() {
        fail("Not yet implemented");
    }

//    @Test
    public void testQueryRecommentEditors() {
        fail("Not yet implemented");
    }

//    @Test
    public void testObject() {
        fail("Not yet implemented");
    }

//    @Test
    public void testGetClass() {
        fail("Not yet implemented");
    }

//    @Test
    public void testHashCode() {
        fail("Not yet implemented");
    }

//    @Test
    public void testEquals() {
        fail("Not yet implemented");
    }

//    @Test
    public void testClone() {
        fail("Not yet implemented");
    }

//    @Test
    public void testToString() {
        fail("Not yet implemented");
    }

//    @Test
    public void testNotify() {
        fail("Not yet implemented");
    }

//    @Test
    public void testNotifyAll() {
        fail("Not yet implemented");
    }

//    @Test
    public void testWaitLong() {
        fail("Not yet implemented");
    }

//    @Test
    public void testWaitLongInt() {
        fail("Not yet implemented");
    }

//    @Test
    public void testWait() {
        fail("Not yet implemented");
    }

//    @Test
    public void testFinalize() {
        fail("Not yet implemented");
    }

}
