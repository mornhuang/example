
package com.itsz.sht.dao.delegate;

import java.util.Collection;
import com.itsz.sht.dao.IPORequestDAO;
import com.itsz.sht.dto.ipo.IPORequest;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;


public class IPORequestDelegate {
	private IPORequestDAO ipoRequestDAO;
	
	public void setIpoRequestDAO(IPORequestDAO ipoRequestDAO) {
		this.ipoRequestDAO = ipoRequestDAO;
	}

	public IPORequest getIPORequest(long  applyId)throws Exception {
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		try {
	    	IPORequest ipoRequest = ipoRequestDAO.getIPORequest(applyId);
	    	return ipoRequest;
		}
		catch (RecordNotFoundException rnfe) {
			logger.info("IPO Request for the id " + applyId + " is not found in the database!");
			return null;
		}
		catch (DAOException de) {
            logger.error(de);
			throw new Exception();
		}
	}

    public IPORequest getIPORequest(String accountId,long ipoMasterId) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            IPORequest ipoRequest = ipoRequestDAO.getIPORequest(accountId,ipoMasterId);
            return ipoRequest;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Request for the id " + ipoMasterId + " is not found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

	public Collection getAllIPORequest()throws Exception {
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		try {
			Collection ipoRecordList = ipoRequestDAO.getAllIPORequest();
			return ipoRecordList;
		}
		catch (RecordNotFoundException rnfe) {
			logger.info("No IPO Request found in the database!");
			return null;
		}
		catch (DAOException de) {
            logger.error(de);
			throw new Exception();
		}
	}

    public Collection getIPORequestByIPO(long ipoMasterId) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoRecordList = ipoRequestDAO.getIPORequestByIPO(ipoMasterId);
            return ipoRecordList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Request found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }
    
    public Collection getIPORequestCol(String accountId, long ipoMasterId) throws Exception {
       WebLogger logger = WebLoggerFactory.instance().getLogger(this);
       try {
           Collection ipoRecordList = ipoRequestDAO.getIPORequestCol(accountId,ipoMasterId);
           return ipoRecordList;
       }
       catch (RecordNotFoundException rnfe) {
           logger.info("No IPO Request found in the database!");
           return null;
       }
       catch (DAOException de) {
           logger.error(de);
           throw new Exception();
       }
   }

    public Collection getIPORequestByAccount(String accountId) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoRecordList = ipoRequestDAO.getIPORequestByAccount(accountId);
            return ipoRecordList;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No IPO Request found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

	public void insertIPORequest(IPORequest newIpoRequest)throws Exception {
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		try {
	    	ipoRequestDAO.insertIPORequest(newIpoRequest);
		}
		catch (DAOException de) {
            logger.error(de);
			throw new Exception();
		}
	}


	public void removeIPORequest(long ipoMasterId)throws Exception {
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		try {
	    	ipoRequestDAO.removeIPORequest(ipoMasterId);
		}
		catch (RecordNotFoundException rnfe) {
			logger.info("IPO Request for the id " + ipoMasterId + " is not found in the database!");
		}
		catch (DAOException de) {
            logger.error(de);
			throw new Exception();
		}
	}

    public void exportIPOQtyAmtFile(long ipoMasterId,String ipoExportRequestFilePath)  throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoRequestDAO.exportFileByIPO(ipoMasterId,ipoExportRequestFilePath);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Record for the id " + ipoExportRequestFilePath + " is not found in the database!");
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }



}
