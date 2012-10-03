package com.itsz.sht.dao;

import java.util.Collection;

import com.itsz.sht.dto.ipo.IPOResult;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;

public interface IPOResultDAO {

    public IPOResult getIPOResult(long applyId) throws DAOException, RecordNotFoundException;

    public void insertIPOResult(IPOResult newIpoResult) throws DAOException;

    public Collection getAllIPOResult(String ipoCode) throws DAOException, RecordNotFoundException;

    public Collection getIPOResultStatusAndCount(String ipoCode) throws DAOException, RecordNotFoundException;

    public void removeIPOResult(long applyId) throws DAOException, RecordNotFoundException;

    public void importFile(String filePath) throws DAOException ;

    public Collection getIPOResultByAccount(String accountId) throws DAOException,
        RecordNotFoundException;

    public Collection getIPOResultCol(String ipoCode,String accountId) throws DAOException,
        RecordNotFoundException;

    public void updateIPOResult(long applyId,IPOResult ipoResult) throws DAOException,
        RecordNotFoundException ;

    public Collection getPageIPOResult(String ipoCode,int firstResult,int fetchSize) throws DAOException,
        RecordNotFoundException;

    public Collection getPageIPOResultByAccount(String accountId,int firstResult,int fetchSize) throws
        DAOException,
        RecordNotFoundException;

    public Collection getPageIPOResultCol(String ipoCode, String accountId,int firstResult,int fetchSize) throws
        DAOException,
        RecordNotFoundException;

    public void updateAllIPOResult(String ipoCode, String status) throws
        DAOException,
        RecordNotFoundException;

}

