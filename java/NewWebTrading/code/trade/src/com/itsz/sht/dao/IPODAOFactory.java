package com.itsz.sht.dao;

import com.itsz.sht.dao.impl.IPODAOHbmImpl;

public class IPODAOFactory {

    private static IPODAOFactory instance = null;

    protected IPODAOFactory() {
    }

    public static IPODAOFactory instance() {
        if( instance == null ) {
            synchronized( IPODAOFactory.class ) {
                if( instance == null ) {
                    instance = new IPODAOFactory();
                }
            }
        }
        return instance;
    }

    public IPODAO getIPODAO() {
        return new IPODAOHbmImpl();
    }
}

