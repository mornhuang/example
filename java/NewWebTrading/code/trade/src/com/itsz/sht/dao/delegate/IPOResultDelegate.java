
package com.itsz.sht.dao.delegate;

import java.util.Collection;
import com.itsz.sht.dao.IPOResultDAO;
import com.itsz.sht.dto.ipo.IPOResult;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;


public class IPOResultDelegate {
	private IPOResultDAO ipoResultDAO;
	
	public void setIpoResultDAO(IPOResultDAO ipoResultDAO) {
		this.ipoResultDAO = ipoResultDAO;
	}

	public IPOResult getIPOResult(long applyId)throws Exception {
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		try {
	    	IPOResult ipoResult = ipoResultDAO.getIPOResult(applyId);
	    	return ipoResult;
		}
		catch (RecordNotFoundException rnfe) {
			logger.info("IPO Result for the id " + applyId + " is not found in the database!");
			return null;
		}
		catch (DAOException de) {
            logger.error(de);
			throw new Exception();
		}
	}

	public Collection getAllIPOResult(String ipoCode)throws Exception {
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		try {
			Collection ipoRecordList = ipoResultDAO.getAllIPOResult(ipoCode);
			return ipoRecordList;
		}
		catch (RecordNotFoundException rnfe) {
			logger.info("No IPO Result found in the database!");
			return null;
		}
		catch (DAOException de) {
            logger.error(de);
			throw new Exception();
		}
	}


    public Collection getPageIPOResult(String ipoCode,int firstResult,int fetchSize) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoRecordList = ipoResultDAO.getPageIPOResult(ipoCode,firstResult,fetchSize);
            return ipoRecordList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Result found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public Collection getIPOResultStatusAndCount(String ipoCode) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoRecordStatusList = ipoResultDAO.getIPOResultStatusAndCount(ipoCode);
            return ipoRecordStatusList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Result found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public Collection getIPOResultByAccount(String accountId) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoRecordList = ipoResultDAO.getIPOResultByAccount(accountId);
            return ipoRecordList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Result found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public Collection getPageIPOResultByAccount(String accountId,int firstResult,int fetchSize) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoRecordList = ipoResultDAO.getPageIPOResultByAccount(accountId,firstResult,fetchSize);
            return ipoRecordList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Result found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public Collection getIPOResultCol(String ipoCode,String accountId) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoRecordList = ipoResultDAO.getIPOResultCol(ipoCode,accountId);
            return ipoRecordList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Result found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public Collection getPageIPOResultCol(String ipoCode,String accountId,int firstResult,int fetchSize) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoRecordList = ipoResultDAO.getPageIPOResultCol(ipoCode,accountId,firstResult,fetchSize);
            return ipoRecordList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Result found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

	public void insertIPOResult(IPOResult newIpoResult)throws Exception {
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		try {
	    	ipoResultDAO.insertIPOResult(newIpoResult);
		}
		catch (DAOException de) {
            logger.error(de);
			throw new Exception();
		}
	}

	public void removeIPOResult(long applyId) throws Exception {
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		try {
	    	ipoResultDAO.removeIPOResult(applyId);
		}
		catch (RecordNotFoundException rnfe) {
			logger.info("IPO Result for the id " + applyId + " is not found in the database!");
		}
		catch (DAOException de) {
			throw new Exception();
		}
	}

    public void updateIPOResult(long applyId,IPOResult ipoResult) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoResultDAO.updateIPOResult(applyId,ipoResult);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Result for the id " + applyId + " can not update in the database!");
        }
        catch (DAOException de) {
            throw new Exception();
        }
    }

    public void updateAllIPOResult(String ipoCode, String status) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoResultDAO.updateAllIPOResult(ipoCode,status);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Result for the ipoCode " + ipoCode + " can not update in the database!");
        }
        catch (DAOException de) {
            throw new Exception();
        }
    }

    public void importIPOResultFile(String ipoResultFilePath) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoResultDAO.importFile(ipoResultFilePath);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Record for the id " + ipoResultFilePath + " is not found in the database!");
        }
        catch (DAOException de) {
            throw new Exception();
        }
    }


}
