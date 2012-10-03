package com.taifook.adminportal.web.ipo.dao;

import com.taifook.adminportal.web.ipo.dao.hibernate.IPOReqResDAOHbmImpl;


public class IPOReqResDAOFactory {

    private static IPOReqResDAOFactory instance = null;

    protected IPOReqResDAOFactory() {
    }

    public static IPOReqResDAOFactory instance() {
        if( instance == null ) {
            synchronized( IPOReqResDAOFactory.class ) {
                if( instance == null ) {
                    instance = new IPOReqResDAOFactory();
                }
            }
        }
        return instance;
    }

    public IPOReqResDAO getIPOReqResDAO() {
        return new IPOReqResDAOHbmImpl();
    }
}

