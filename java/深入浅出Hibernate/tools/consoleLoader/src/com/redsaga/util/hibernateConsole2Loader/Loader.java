package com.redsaga.util.hibernateConsole2Loader;

import net.sf.hibernate.cfg.Configuration;
import net.sf.hibernate.console.Start;
import net.sf.hibernate.HibernateException;

/**
 * Created by IntelliJ IDEA.
 * User: cao
 * Date: 2005-2-10
 * Time: 0:05:06
 * To change this template use File | Settings | File Templates.
 */
public class Loader {
    public static void main ( String[] args ) throws HibernateException {
        Configuration cfg = new Configuration().configure();
        Start.startWith( cfg );
    }

}
