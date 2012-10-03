package com.taifook.adminportal.web.ipo.dao;

import java.util.Collection;
import java.math.BigDecimal;
import com.taifook.adminportal.web.ipo.dto.IPOQtyAmt;
import com.taifook.adminportal.exceptions.DAOException;

public interface IPOQtyAmtDAO {

    public void insertIPOQtyAmtRcrd(IPOQtyAmt newIPOQtyAmt) throws DAOException;

    public Collection getIPOQtyAmtRcrd(long ipoMasterId) throws DAOException;

    public void removeIPOQtyAmtRcrd(long ipoMasterId) throws DAOException;

    public void importFile(String filePath) throws DAOException;

    public BigDecimal getAmtbyMasterIdQty(long ipoMasterId,int ipoQty) throws DAOException;
}

