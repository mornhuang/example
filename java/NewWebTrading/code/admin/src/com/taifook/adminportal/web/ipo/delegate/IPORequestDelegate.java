
package com.taifook.adminportal.web.ipo.delegate;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taifook.adminportal.web.ipo.dao.IPORequestDAO;
import com.taifook.adminportal.web.ipo.dao.IPORequestDAOFactory;
import com.taifook.adminportal.web.ipo.dto.IPORequest;
import com.taifook.adminportal.web.ipo.dto.IPOReqResOrder;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.web.ipo.RecordNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class IPORequestDelegate {

	public IPORequest getIPORequest(long  applyId)
		throws IPORequestException {

		Log logger = LogFactory.getLog(this.getClass());

		try {

	    	IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
	    	IPORequest ipoRequest = ipoRequestDAO.getIPORequest(applyId);

	    	return ipoRequest;

		}
		catch (DAOException de) {
            logger.error(de);
			throw new IPORequestException();
		}
	}

    public IPORequest getIPORequest(String accountId,long ipoMasterId)
        throws IPORequestException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
            IPORequest ipoRequest = ipoRequestDAO.getIPORequest(accountId,ipoMasterId);

            return ipoRequest;

        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPORequestException();
        }
    }

	public Collection getAllIPORequest()
		throws IPORequestException {

		Log logger = LogFactory.getLog(this.getClass());

		try {

			IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
			Collection ipoRecordList = ipoRequestDAO.getAllIPORequest();

			return ipoRecordList;
		}
		catch (DAOException de) {
            logger.error(de);
			throw new IPORequestException();
		}
	}

    public Collection getIPORequestByIPO(long ipoMasterId)
        throws IPORequestException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
            Collection ipoRecordList = ipoRequestDAO.getIPORequestByIPO(ipoMasterId);

            return ipoRecordList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPORequestException();
        }
    }
    public Collection getIPORequestCol(String accountId, long ipoMasterId)
       throws IPORequestException {

       Log logger = LogFactory.getLog(this.getClass());

       try {

           IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
           Collection ipoRecordList = ipoRequestDAO.getIPORequestCol(accountId,ipoMasterId);

           return ipoRecordList;
       }
       catch (DAOException de) {
           logger.error(de);
           throw new IPORequestException();
       }
   }

    public Collection getIPORequestByAccount(String accountId)
        throws IPORequestException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
            Collection ipoRecordList = ipoRequestDAO.getIPORequestByAccount(accountId);

            return ipoRecordList;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPORequestException();
        }
    }

	public void insertIPORequest(IPORequest newIpoRequest)
		throws IPORequestException {

		Log logger = LogFactory.getLog(this.getClass());

		try {

            IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
	    	ipoRequestDAO.insertIPORequest(newIpoRequest);
		}
		catch (DAOException de) {
            logger.error(de);
			throw new IPORequestException();
		}
	}


	public void removeIPORequest(long ipoMasterId)
		throws IPORequestException {

		Log logger = LogFactory.getLog(this.getClass());

		try {
            IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
	    	ipoRequestDAO.removeIPORequest(ipoMasterId);
		}
		catch (DAOException de) {
            logger.error(de);
			throw new IPORequestException();
		}
	}

    public void exportIPOQtyAmtFile(long ipoMasterId,String ipoExportRequestFilePath)
        throws IPORequestException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
            ipoRequestDAO.exportFileByIPO(ipoMasterId,ipoExportRequestFilePath);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPORequestException();
        }
    }

    public IPOReqResOrder getIPORequestAndResultStauts(long ipoMasterId,String accountId) throws
    IPORequestException {

    Log logger = LogFactory.getLog(this.getClass());

    try {
        IPOReqResOrder ipoRequestAndResult = new IPOReqResOrder();

        IPORequestDAO ipoRequestDAO = (IPORequestDAO) IPORequestDAOFactory.instance().getIPORequestDAO();
        ipoRequestAndResult = ipoRequestDAO.getIPORequestAndResultStauts(ipoMasterId,accountId);


        return ipoRequestAndResult;
    }
    catch (DAOException de) {
        logger.error(de);
        throw new IPORequestException();
    }
}


}
