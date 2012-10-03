package com.itsz.sht.common;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLocator {
    private static ServiceLocator locator;
    private BeanFactory factory;
    
    public static ServiceLocator getInstance() {
	    if (locator == null) {
			synchronized(ServiceLocator.class){
				if(locator == null){
					locator = new ServiceLocator();
				}
			}
		}
	    return locator;
    }
    
    public Object getService(String serviceName) {
        return factory.getBean(serviceName);
    }
    
    private ServiceLocator() {
        init();
    }
    
    private void init() {
    	factory = new ClassPathXmlApplicationContext("beans.xml");
    }
    
    public static void main(String[] args) {
        ServiceLocator locator = ServiceLocator.getInstance();
        System.out.println("--------class------" +  locator.getService("facade").toString());
    }
    
}
