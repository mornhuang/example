package com.itsz.sht.dao.delegate;

import java.util.ArrayList;
import com.itsz.sht.dao.IPODAO;
import com.itsz.sht.dto.ipo.IPORecord;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

public class IPOMaintenanceDelegate {
	private IPODAO ipoDAO;
	
    public void setIpoDAO(IPODAO ipoDAO) {
		this.ipoDAO = ipoDAO;
	}

	public IPORecord getIPORecord(long ipoMaster) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            IPORecord ipoRecord = ipoDAO.getIPORecord(ipoMaster);
            return ipoRecord;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Record for the id " + ipoMaster + " is not found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public java.util.Collection getAllIPORecord() throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            java.util.Collection ipoRecordList = new ArrayList();
            ipoRecordList = ipoDAO.getAllIPORecord();
            return ipoRecordList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Record found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public void insertIPORecord(IPORecord newIpoRecord) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoDAO.insertIPORecord(newIpoRecord);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public void updateIPORecord(long ipoMaster, IPORecord newIpoRecord) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoDAO.updateIPORecord(ipoMaster, newIpoRecord);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Record for the id " + ipoMaster +
                        " is not found in the database!");
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public void removeIPORecord(long ipoMaster) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoDAO.removeIPORecord(ipoMaster);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Record for the id " + ipoMaster +
                        " is not found in the database!");
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public void importIPOBasicFile(String ipoFilePath) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoDAO.importFile(ipoFilePath);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Record for the id " + ipoFilePath +
                        " is not found in the database!");
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public IPORecord getIPOInfo(String ipoCode) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            IPORecord ipoRecord = ipoDAO.getIPOInfo(ipoCode);
            return ipoRecord;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Record for the ipoCode " + ipoCode +
                        " is not found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }

    }
}
