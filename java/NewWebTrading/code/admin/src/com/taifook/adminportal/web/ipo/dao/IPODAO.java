package com.taifook.adminportal.web.ipo.dao;

import java.util.List;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.exceptions.DAOException;

public interface IPODAO {

    public IPORecord getIPORecord(long ipoMasterId) throws DAOException;

    public void insertIPORecord(IPORecord newIpoRecord) throws DAOException;

    public java.util.Collection getAllIPORecord() throws DAOException;

    public void updateIPORecord(long ipoMasterId, IPORecord newIpoRecord) throws DAOException;

    public void removeIPORecord(long ipoMasterId) throws DAOException;

    public IPORecord getIPOInfo(String ipoCode) throws DAOException;

    public void importFile(String filePath) throws DAOException;

    public java.util.Collection getAllIPORecordAndStatus(HttpServletRequest request,String accountId,int firstRecord) throws DAOException;
}

