package com.itsz.sht.dao;

import java.util.Collection;

import com.itsz.sht.dto.ipo.IPORequest;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;

public interface IPORequestDAO {

    public IPORequest getIPORequest(long applyId) throws DAOException, RecordNotFoundException;

    public void insertIPORequest(IPORequest newIpoRequest) throws DAOException;

    public Collection getAllIPORequest() throws DAOException, RecordNotFoundException;

    public void removeIPORequest(long applyId) throws DAOException, RecordNotFoundException;

    public IPORequest getIPORequest(String accountId, long ipoMasterId) throws
           DAOException,
           RecordNotFoundException;

    public void exportFileByIPO(long ipoMasterId, String filePath) throws DAOException,RecordNotFoundException;

    public Collection getIPORequestByIPO(long ipoMasterId) throws DAOException,
        RecordNotFoundException ;

    public Collection getIPORequestCol(String accountId, long ipoMasterId) throws
        DAOException,
        RecordNotFoundException;

    public Collection getIPORequestByAccount(String accountId) throws
        DAOException,
        RecordNotFoundException;

}

