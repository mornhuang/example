package com.itsz.sht.dao;

import com.itsz.sht.dao.impl.IPOQtyAmtDAOHbmImpl;


public class IPOQtyAmtDAOFactory {

    private static IPOQtyAmtDAOFactory instance = null;

    protected IPOQtyAmtDAOFactory() {
    }

    public static IPOQtyAmtDAOFactory instance() {
        if( instance == null ) {
            synchronized( IPOQtyAmtDAOFactory.class ) {
                if( instance == null ) {
                    instance = new IPOQtyAmtDAOFactory();
                }
            }
        }
        return instance;
    }

    public IPOQtyAmtDAO getIPOQtyAmtDAO() {
        return new IPOQtyAmtDAOHbmImpl();
    }
}

