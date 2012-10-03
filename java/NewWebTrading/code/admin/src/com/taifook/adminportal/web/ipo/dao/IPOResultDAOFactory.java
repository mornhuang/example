package com.taifook.adminportal.web.ipo.dao;

import com.taifook.adminportal.web.ipo.dao.hibernate.IPOResultDAOHbmImpl;


public class IPOResultDAOFactory {

    private static IPOResultDAOFactory instance = null;

    protected IPOResultDAOFactory() {
    }

    public static IPOResultDAOFactory instance() {
        if( instance == null ) {
            synchronized( IPOResultDAOFactory.class ) {
                if( instance == null ) {
                    instance = new IPOResultDAOFactory();
                }
            }
        }
        return instance;
    }

    public IPOResultDAO getIPOResultDAO() {
        return new IPOResultDAOHbmImpl();
    }
}

