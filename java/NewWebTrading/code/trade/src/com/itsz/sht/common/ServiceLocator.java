package com.itsz.sht.common;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * $Id: ServiceLocator.java,v 1.2 2011/01/19 06:26:55 kyzou Exp $
 * @Project:portal.head
 * @File:ServiceLocator.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-22
 */
public class ServiceLocator {
    private static ServiceLocator locator;
//    protected XmlBeanFactory factory;
    protected BeanFactory factory;
    
    static{
        locator = new ServiceLocator();
    }
    
    public static ServiceLocator getInstance() {
        return locator;
    }
    
    public Object getService(String serviceName) {
        return factory.getBean(serviceName);
    }
    
    private ServiceLocator() {
        init();
    }
    
    private void init() {
//        ClassPathResource resource = new ClassPathResource("beans.xml");
//        factory = new XmlBeanFactory(resource);
        factory = new ClassPathXmlApplicationContext("beans.xml");
    }
    
    public static void main(String[] args) {
        ServiceLocator locator = ServiceLocator.getInstance();
        locator.getService("facade");
        System.out.println("----------------class------");
    }
    
}
