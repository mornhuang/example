package com.itsz.sht.dao;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class BaseTest extends TestCase {

	protected ApplicationContext context = null;
	protected XmlBeanFactory factory;
	protected Log log = LogFactory.getLog(this.getClass());

	protected void setUp() throws Exception {
        ClassPathResource resource = new ClassPathResource("beans.xml");
        factory = new XmlBeanFactory(resource);
	}

	protected void tearDown() throws Exception {
		context = null;
	}

	protected static void println(Object print) {
		System.out.println(print);
	}
	
//    private void init() {
////      ClassPathResource resource = new ClassPathResource("adminContext.xml");
//      factory = new ClassPathXmlApplicationContext("beans.xml");
//  }
	
}
