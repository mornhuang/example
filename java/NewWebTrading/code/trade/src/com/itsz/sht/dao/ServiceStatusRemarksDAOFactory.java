package com.itsz.sht.dao;

import com.itsz.sht.dao.impl.ServiceStatusRemarksDAOHbmImpl;


/**
 * 
 * $Id: ServiceStatusRemarksDAOFactory.java,v 1.2 2010/11/12 04:41:07 kyzou Exp $
 * @Project:portal
 * @File:ServiceStatusRemarksDAOFactory.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceStatusRemarksDAOFactory {
    
	private static ServiceStatusRemarksDAOFactory instance = null;

    protected ServiceStatusRemarksDAOFactory() {
    }

    public static ServiceStatusRemarksDAOFactory instance() {
        if( instance == null ) {
            synchronized( ServiceStatusRemarksDAOFactory.class ) {
                if( instance == null ) {
                    instance = new ServiceStatusRemarksDAOFactory();
                }
            }
        }
        return instance;
    }
    
    public ServiceStatusRemarksDAO getServiceStatusRemarksDAO() {
    	return new ServiceStatusRemarksDAOHbmImpl();
    }
}
