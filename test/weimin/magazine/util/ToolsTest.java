package weimin.magazine.util;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ToolsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDateFormat() {
	    Date a = Tools.getEarlyDate(20);
	    Date b = Tools.getEarlyDate(30);
	    System.out.println(a);
	    System.out.println(b);
	    System.out.println(a.compareTo(b)>0);
	}

	@Test
	public void testGetDate() {
		// fail("Not yet implemented");
		System.out.println(Tools.getDate());
		System.out.println(Tools.date2String(new Date()));
		System.out.println(Tools.String2date("2008-08-08 12:10:12"));
		}
		
	@Test
    public void testvalidateContribution(){
	    String flag = "$$";
	    String text = "$$ aaaa";
	    Assert.assertEquals(true, text.startsWith(flag));
	    
	    text = "  $$ aaaa";
	    Assert.assertEquals(false, text.startsWith(flag));
	    
	    System.out.println(text.indexOf("xx"));
	   
	    System.out.println(text.substring(text.indexOf(flag)+2, text.length()));;
	    
	    
	}

}
