package weimin.magazine.util;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ErrorCodeConstantTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTrancErrorCode() {
        Assert.assertEquals(1001, ErrorCodeConstant.trancErrorCode(21327));
    }

}
