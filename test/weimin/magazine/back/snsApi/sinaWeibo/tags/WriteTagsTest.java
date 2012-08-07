package weimin.magazine.back.snsApi.sinaWeibo.tags;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import weibo4j.model.WeiboException;
import weimin.magazine.back.snsApi.sinaWeibo.tags.WriteTags.TagRes;

public class WriteTagsTest {
    
    String ac = "2.00Mu98CDooILhD2539fa2b3eScNGRE";
    long uid = 2784497140l;
    WriteTags r ;

    @Before
    public void setUp() throws Exception {
        r = new WriteTags(ac);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreate() throws WeiboException {
//        fail("Not yet implemented");
        Gson gson = new Gson();
        TagRes tag = gson.fromJson("{\"tagid\":\"22802\"}", new TypeToken<TagRes>(){}.getType());
        System.out.println(tag.getTagid());
        r.create("w");
    }

    @Test
    public void testDestroy() throws WeiboException {
        r.destroy(201212352547l);
    }

//    @Test
    public void testBatchDestroy() throws WeiboException {
//        fail("Not yet implemented");
        Set<Long> tids = new LinkedHashSet<Long>();
        tids.add(537l);
        tids.add(1029l);
        r.batchDestroy(tids);
    }

}
