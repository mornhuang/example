package com.itsz.sht.dao;

import com.itsz.sht.dao.impl.ServiceStatusDAOHbmImpl;


/**
 * 
 * $Id: ServiceStatusDAOFactory.java,v 1.2 2010/11/12 04:41:07 kyzou Exp $
 * @Project:portal
 * @File:ServiceStatusDAOFactory.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceStatusDAOFactory {

    private static ServiceStatusDAOFactory instance = null;

    protected ServiceStatusDAOFactory() {
    }

    public static ServiceStatusDAOFactory instance() {
        if( instance == null ) {
            synchronized( ServiceStatusDAOFactory.class ) {
                if( instance == null ) {
                    instance = new ServiceStatusDAOFactory();
                }
            }
        }
        return instance;
    }
    
    public ServiceStatusDAO getServiceStatusDAO() {
    	return new ServiceStatusDAOHbmImpl();
    }
}
