package com.taifook.adminportal.web.ipo.delegate;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.taifook.adminportal.web.ipo.dao.IPODAO;
import com.taifook.adminportal.web.ipo.dao.IPOQtyAmtDAO;
import com.taifook.adminportal.web.ipo.dao.IPODAOFactory;
import com.taifook.adminportal.web.ipo.dao.IPOQtyAmtDAOFactory;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.web.ipo.RecordNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IPOMaintenanceDelegate {

    public IPORecord getIPORecord(long ipoMaster) throws
        IPOMaintenanceException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
            IPORecord ipoRecord = ipoDAO.getIPORecord(ipoMaster);

            return ipoRecord;

        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOMaintenanceException();
        }
    }

    public java.util.Collection getAllIPORecord() throws
        IPOMaintenanceException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            java.util.Collection ipoRecordList = new ArrayList();

            IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
            ipoRecordList = ipoDAO.getAllIPORecord();

            return ipoRecordList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOMaintenanceException();
        }
    }

    public void insertIPORecord(IPORecord newIpoRecord) throws
        IPOMaintenanceException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
            ipoDAO.insertIPORecord(newIpoRecord);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOMaintenanceException();
        }
    }

    public void updateIPORecord(long ipoMaster, IPORecord newIpoRecord) throws
        IPOMaintenanceException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
            ipoDAO.updateIPORecord(ipoMaster, newIpoRecord);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOMaintenanceException();
        }
    }

    public void removeIPORecord(long ipoMaster) throws IPOMaintenanceException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
            ipoDAO.removeIPORecord(ipoMaster);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOMaintenanceException();
        }
    }

    public void importIPOBasicFile(String ipoFilePath) throws
        IPOMaintenanceException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
            ipoDAO.importFile(ipoFilePath);
            //IPOQtyAmtDAO ipoQtyAmtDAO = (IPOQtyAmtDAO) IPODAOFactory.instance().getIPODAO();
            //ipoQtyAmtDAO.importFile(ipoCrtFilePath);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOMaintenanceException();
        }
    }

    public IPORecord getIPOInfo(String ipoCode) throws IPOMaintenanceException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
            IPORecord ipoRecord = ipoDAO.getIPOInfo(ipoCode);

            return ipoRecord;

        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOMaintenanceException();
        }

    }

    public java.util.Collection getAllIPORecordAndStatus(HttpServletRequest request,String accountId,int firstRecord) throws
    IPOMaintenanceException {

    Log logger = LogFactory.getLog(this.getClass());

    try {
        java.util.Collection ipoRecordAndStatusList = new ArrayList();

        IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
        ipoRecordAndStatusList = ipoDAO.getAllIPORecordAndStatus(request,accountId,firstRecord);

        return ipoRecordAndStatusList;
    }
    catch (DAOException de) {
        logger.error(de);
        throw new IPOMaintenanceException();
    }
}

}
