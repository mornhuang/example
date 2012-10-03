package com.taifook.adminportal.web.ipo.dao;

import java.util.Collection;

import com.taifook.adminportal.web.ipo.dto.IPOResult;
import com.taifook.adminportal.exceptions.DAOException;


public interface IPOResultDAO {

    public IPOResult getIPOResult(long applyId) throws DAOException;

    public void insertIPOResult(IPOResult newIpoResult) throws DAOException;

    public Collection getAllIPOResult(String ipoCode) throws DAOException;

    public Collection getIPOResultStatusAndCount(String ipoCode) throws DAOException;

    public void removeIPOResult(long applyId) throws DAOException;

    public void importFile(String filePath) throws DAOException ;

    public Collection getIPOResultByAccount(String accountId) throws DAOException;

    public Collection getIPOResultCol(String ipoCode,String accountId) throws DAOException;

    public void updateIPOResult(long applyId,IPOResult ipoResult) throws DAOException;

    public Collection getPageIPOResult(String ipoCode,int firstResult,int fetchSize) throws DAOException;

    public Collection getPageIPOResultByAccount(String accountId,int firstResult,int fetchSize) throws
        DAOException;

    public Collection getPageIPOResultCol(String ipoCode, String accountId,int firstResult,int fetchSize) throws
        DAOException;

    public void updateAllIPOResult(String ipoCode, String status) throws
        DAOException;

}

