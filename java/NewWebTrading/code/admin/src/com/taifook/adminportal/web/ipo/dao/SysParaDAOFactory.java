package com.taifook.adminportal.web.ipo.dao;

import com.taifook.adminportal.web.ipo.dao.hibernate.SysParaDAOHbmImpl;


public class SysParaDAOFactory {

    private static SysParaDAOFactory instance = null;

    protected SysParaDAOFactory() {
    }

    public static SysParaDAOFactory instance() {
        if( instance == null ) {
            synchronized( SysParaDAOFactory.class ) {
                if( instance == null ) {
                    instance = new SysParaDAOFactory();
                }
            }
        }
        return instance;
    }

    public SysParaDAO getIPODAO() {
        return new SysParaDAOHbmImpl();
    }
}

