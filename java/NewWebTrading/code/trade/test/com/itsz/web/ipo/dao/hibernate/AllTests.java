package com.itsz.web.ipo.dao.hibernate;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests
    extends TestCase {

    public AllTests(String s) {
        super(s);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(com.itsz.web.ipo.dao.hibernate.
        		AllTests.class);
        return suite;
    }
}
