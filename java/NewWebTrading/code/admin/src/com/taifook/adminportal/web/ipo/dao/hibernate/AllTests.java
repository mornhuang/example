package com.taifook.adminportal.web.ipo.dao.hibernate;

import junit.framework.*;

public class AllTests
    extends TestCase {

    public AllTests(String s) {
        super(s);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(com.taifook.adminportal.web.ipo.dao.hibernate.
                           AELoginTestCase.class);
        return suite;
    }
}
