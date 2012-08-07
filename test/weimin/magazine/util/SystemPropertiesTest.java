package weimin.magazine.util;

import static org.junit.Assert.*;

import org.junit.Test;

import weimin.magazine.util.SystemProperties;

public class SystemPropertiesTest {

	@Test
	public void testSetSystemConfig() {
//		fail("Not yet implemented");
	}

	@Test
	public void testLoadSystemProperties() {
//		fail("Not yet implemented");
		SystemProperties s = new SystemProperties();
		s.loadSystemProperties();
		assertEquals(System.getProperties().getProperty("log4j.appender.CONSOLE"),"org.apache.log4j.ConsoleAppender");
	}

}
