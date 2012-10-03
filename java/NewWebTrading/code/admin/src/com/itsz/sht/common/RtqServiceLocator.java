package com.itsz.sht.common;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class RtqServiceLocator {
    private static RtqServiceLocator locator;
    protected XmlBeanFactory factory;
    
    static{
        locator = new RtqServiceLocator();
    }
    
    public static RtqServiceLocator getInstance() {
        return locator;
    }
    
    public Object getService(String serviceName) {
        return factory.getBean(serviceName);
    }
    
    private RtqServiceLocator() {
        init();
    }
    
    private void init() {
        ClassPathResource resource = new ClassPathResource("rtqbeans.xml");
        factory = new XmlBeanFactory(resource);
    }
}
