package com.taifook.adminportal.web.ipo.dao;

import com.taifook.adminportal.web.ipo.dao.hibernate.IPOQtyAmtDAOHbmImpl;

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

