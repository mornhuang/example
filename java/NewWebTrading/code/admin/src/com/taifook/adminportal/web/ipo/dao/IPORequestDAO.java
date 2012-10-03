package com.taifook.adminportal.web.ipo.dao;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.taifook.adminportal.web.ipo.dto.IPOReqResOrder;
import com.taifook.adminportal.web.ipo.dto.IPORequest;
import com.taifook.adminportal.exceptions.DAOException;


public interface IPORequestDAO {

    public IPORequest getIPORequest(long applyId) throws DAOException;

    public void insertIPORequest(IPORequest newIpoRequest) throws DAOException;

    public Collection getAllIPORequest() throws DAOException;

    public void removeIPORequest(long applyId) throws DAOException;

    public IPORequest getIPORequest(String accountId, long ipoMasterId) throws
           DAOException;

    public void exportFileByIPO(long ipoMasterId, String filePath) throws DAOException;

    public Collection getIPORequestByIPO(long ipoMasterId) throws DAOException;

    public Collection getIPORequestCol(String accountId, long ipoMasterId) throws
        DAOException;

    public Collection getIPORequestByAccount(String accountId) throws
        DAOException;

    public IPOReqResOrder getIPORequestAndResultStauts(long ipoMasterId,String accountId) throws
    DAOException ;
}

