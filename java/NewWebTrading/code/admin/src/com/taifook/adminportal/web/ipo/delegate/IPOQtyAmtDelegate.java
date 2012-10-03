package com.taifook.adminportal.web.ipo.delegate;

import java.util.Collection;
import java.math.BigDecimal;

import com.taifook.adminportal.web.ipo.dao.IPOQtyAmtDAO;
import com.taifook.adminportal.web.ipo.dao.IPOQtyAmtDAOFactory;
import com.taifook.adminportal.web.ipo.dto.IPOQtyAmt;
import com.taifook.adminportal.web.ipo.exception.IPOQtyAmtException;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.web.ipo.RecordNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IPOQtyAmtDelegate {

    public Collection getIPOQtyAmtRcrd(long ipoMasterId) throws
        IPOQtyAmtException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPOQtyAmtDAO ipoQtyAmtDAO = (IPOQtyAmtDAO) IPOQtyAmtDAOFactory.
                instance().getIPOQtyAmtDAO();
            Collection ipoCollection = ipoQtyAmtDAO.getIPOQtyAmtRcrd(
                ipoMasterId);

            return ipoCollection;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOQtyAmtException();
        }
    }

    public void insertIPOQtyAmt(IPOQtyAmt newIpoQtyAmtRcrd) throws
        IPOQtyAmtException {

        Log logger = LogFactory.getLog(this.getClass());

        try {

            IPOQtyAmtDAO ipoQtyAmtDAO = (IPOQtyAmtDAO) IPOQtyAmtDAOFactory.
                instance().getIPOQtyAmtDAO();
            ipoQtyAmtDAO.insertIPOQtyAmtRcrd(newIpoQtyAmtRcrd);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOQtyAmtException();
        }
    }

    public void removeIPOQtyAmtRcrd(long ipoMasterId) throws IPOQtyAmtException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOQtyAmtDAO ipoDAO = (IPOQtyAmtDAO) IPOQtyAmtDAOFactory.instance().
                getIPOQtyAmtDAO();
            ipoDAO.removeIPOQtyAmtRcrd(ipoMasterId);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOQtyAmtException();
        }
    }

    public BigDecimal getAmtByMasterIdQty(long ipoMasterId, int ipoQty) throws
        IPOQtyAmtException {

        Log logger = LogFactory.getLog(this.getClass());
        BigDecimal amount = new BigDecimal(0);
        try {

            IPOQtyAmtDAO ipoQtyAmtDAO = (IPOQtyAmtDAO) IPOQtyAmtDAOFactory.
                instance().getIPOQtyAmtDAO();
            amount = ipoQtyAmtDAO.getAmtbyMasterIdQty(ipoMasterId, ipoQty);
            return amount;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOQtyAmtException();
        }
    }

    public void importIPOQtyAmtFile(String ipoCrtFilePath) throws
        IPOQtyAmtException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            //IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
            //ipoDAO.importFile(ipoFilePath);
            IPOQtyAmtDAO ipoQtyAmtDAO = (IPOQtyAmtDAO) IPOQtyAmtDAOFactory.
                instance().getIPOQtyAmtDAO();
            ipoQtyAmtDAO.importFile(ipoCrtFilePath);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPOQtyAmtException();
        }
    }

}
