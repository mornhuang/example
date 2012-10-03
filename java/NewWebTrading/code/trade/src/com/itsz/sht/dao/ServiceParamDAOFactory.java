package com.itsz.sht.dao;

import com.itsz.sht.dao.impl.ServiceParamDAOHbmImpl;


/**
 * 
 * $Id: ServiceParamDAOFactory.java,v 1.2 2010/11/12 04:41:07 kyzou Exp $
 * @Project:portal
 * @File:ServiceParamDAOFactory.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceParamDAOFactory {

    private static ServiceParamDAOFactory instance = null;

    protected ServiceParamDAOFactory() {
    }

    public static ServiceParamDAOFactory instance() {
        if( instance == null ) {
            synchronized( ServiceParamDAOFactory.class ) {
                if( instance == null ) {
                    instance = new ServiceParamDAOFactory();
                }
            }
        }
        return instance;
    }
    
    public ServiceParamDAO getServiceParamDAO() {
    	return new ServiceParamDAOHbmImpl();
    }

	
}
