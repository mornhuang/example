package com.taifook.adminportal.web.ipo.dao;

import com.taifook.adminportal.web.ipo.dao.hibernate.IPORequestDAOHbmImpl;



public class IPORequestDAOFactory {

    private static IPORequestDAOFactory instance = null;

    protected IPORequestDAOFactory() {
    }

    public static IPORequestDAOFactory instance() {
        if( instance == null ) {
            synchronized( IPORequestDAOFactory.class ) {
                if( instance == null ) {
                    instance = new IPORequestDAOFactory();
                }
            }
        }
        return instance;
    }

    public IPORequestDAO getIPORequestDAO() {
        return new IPORequestDAOHbmImpl();
    }
}

