package com.itsz.sht.dao;

import java.util.Collection;
import java.math.BigDecimal;

import com.itsz.sht.dto.ipo.IPOQtyAmt;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;

public interface IPOQtyAmtDAO {

    public void insertIPOQtyAmtRcrd(IPOQtyAmt newIPOQtyAmt) throws DAOException;

    public Collection getIPOQtyAmtRcrd(long ipoMasterId) throws DAOException, RecordNotFoundException;

    public void removeIPOQtyAmtRcrd(long ipoMasterId) throws DAOException, RecordNotFoundException;

    public void importFile(String filePath) throws DAOException;

    public BigDecimal getAmtbyMasterIdQty(long ipoMasterId,int ipoQty) throws DAOException,
        RecordNotFoundException;
}

