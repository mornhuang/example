package com.itsz.sht.dao;

import com.itsz.sht.dao.impl.IPORequestDAOHbmImpl;

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

