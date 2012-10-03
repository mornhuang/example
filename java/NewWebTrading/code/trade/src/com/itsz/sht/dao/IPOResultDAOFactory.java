package com.itsz.sht.dao;

import com.itsz.sht.dao.impl.IPOResultDAOHbmImpl;


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

