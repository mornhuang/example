
package com.taifook.adminportal.web.ipo.delegate;

import java.util.Collection;

import com.taifook.adminportal.web.ipo.dao.IPOResultDAO;
import com.taifook.adminportal.web.ipo.dao.IPOResultDAOFactory;
import com.taifook.adminportal.web.ipo.dto.IPOResult;
import com.taifook.adminportal.web.ipo.exception.IPOResultException;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.web.ipo.RecordNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class IPOResultDelegate {

	public IPOResult getIPOResult(long applyId)
		throws IPOResultException {

		Log logger = LogFactory.getLog(this.getClass());

		try {

	    	IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
	    	IPOResult ipoResult = ipoResultDAO.getIPOResult(applyId);

	    	return ipoResult;

		}
		catch (DAOException de) {
            logger.error(de);
			throw new IPOResultException();
		}
	}

	public Collection getAllIPOResult(String ipoCode)
		throws IPOResultException {

		Log logger = LogFactory.getLog(this.getClass());

		try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
			Collection ipoRecordList = ipoResultDAO.getAllIPOResult(ipoCode);

			return ipoRecordList;
		}
		catch (DAOException de) {
            logger.error(de);
			throw new IPOResultException();
		}
	}


    public Collection getPageIPOResult(String ipoCode,int firstResult,int fetchSize)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            Collection ipoRecordList = ipoResultDAO.getPageIPOResult(ipoCode,firstResult,fetchSize);

            return ipoRecordList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOResultException();
        }
    }

    public Collection getIPOResultStatusAndCount(String ipoCode)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            Collection ipoRecordStatusList = ipoResultDAO.getIPOResultStatusAndCount(ipoCode);

            return ipoRecordStatusList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOResultException();
        }
    }

    public Collection getIPOResultByAccount(String accountId)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            Collection ipoRecordList = ipoResultDAO.getIPOResultByAccount(accountId);

            return ipoRecordList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOResultException();
        }
    }

    public Collection getPageIPOResultByAccount(String accountId,int firstResult,int fetchSize)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            Collection ipoRecordList = ipoResultDAO.getPageIPOResultByAccount(accountId,firstResult,fetchSize);

            return ipoRecordList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOResultException();
        }
    }

    public Collection getIPOResultCol(String ipoCode,String accountId)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            Collection ipoRecordList = ipoResultDAO.getIPOResultCol(ipoCode,accountId);

            return ipoRecordList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOResultException();
        }
    }

    public Collection getPageIPOResultCol(String ipoCode,String accountId,int firstResult,int fetchSize)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            Collection ipoRecordList = ipoResultDAO.getPageIPOResultCol(ipoCode,accountId,firstResult,fetchSize);

            return ipoRecordList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOResultException();
        }
    }

	public void insertIPOResult(IPOResult newIpoResult)
		throws IPOResultException {

		Log logger = LogFactory.getLog(this.getClass());

		try {

            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
	    	ipoResultDAO.insertIPOResult(newIpoResult);
		}
		catch (DAOException de) {
            logger.error(de);
			throw new IPOResultException();
		}
	}



	public void removeIPOResult(long applyId)
		throws IPOResultException {

		Log logger = LogFactory.getLog(this.getClass());

		try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
	    	ipoResultDAO.removeIPOResult(applyId);
		}
		catch (DAOException de) {
			throw new IPOResultException();
		}
	}

    public void updateIPOResult(long applyId,IPOResult ipoResult)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            ipoResultDAO.updateIPOResult(applyId,ipoResult);
        }
        catch (DAOException de) {
            throw new IPOResultException();
        }
    }

    public void updateAllIPOResult(String ipoCode, String status)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoResultDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            ipoResultDAO.updateAllIPOResult(ipoCode,status);
        }
        catch (DAOException de) {
            throw new IPOResultException();
        }
    }

    public void importIPOResultFile(String ipoResultFilePath)
        throws IPOResultException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOResultDAO ipoQtyAmtDAO = (IPOResultDAO) IPOResultDAOFactory.instance().getIPOResultDAO();
            ipoQtyAmtDAO.importFile(ipoResultFilePath);
        }
        catch (DAOException de) {
            throw new IPOResultException();
        }
    }


}
