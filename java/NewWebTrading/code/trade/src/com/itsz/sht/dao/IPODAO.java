package com.itsz.sht.dao;

import java.util.Collection;

import com.itsz.sht.dto.ipo.IPORecord;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;

public interface IPODAO {   

    public IPORecord getIPORecord(long ipoMasterId) throws DAOException, RecordNotFoundException;

    public void insertIPORecord(IPORecord newIpoRecord) throws DAOException;

    public Collection getAllIPORecord() throws DAOException, RecordNotFoundException;

    public void updateIPORecord(long ipoMasterId, IPORecord newIpoRecord) throws DAOException, RecordNotFoundException;

    public void removeIPORecord(long ipoMasterId) throws DAOException, RecordNotFoundException;

    public IPORecord getIPOInfo(String ipoCode) throws DAOException,RecordNotFoundException ;

    public void importFile(String filePath) throws DAOException;
}

