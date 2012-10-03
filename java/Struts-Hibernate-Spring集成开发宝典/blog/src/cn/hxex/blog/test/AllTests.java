package cn.hxex.blog.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite()
	{
		TestSuite suite = new TestSuite("Test for cn.hxex.blog.test");
		// $JUnit-BEGIN$
		suite.addTestSuite(TestDaoFactory.class);
		// $JUnit-END$
		return suite;
	}

}
