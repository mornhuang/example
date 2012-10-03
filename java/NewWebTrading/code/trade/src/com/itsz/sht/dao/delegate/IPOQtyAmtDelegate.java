package com.itsz.sht.dao.delegate;

import java.util.Collection;
import java.math.BigDecimal;
import com.itsz.sht.dao.IPOQtyAmtDAO;
import com.itsz.sht.dto.ipo.IPOQtyAmt;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

public class IPOQtyAmtDelegate {
	private IPOQtyAmtDAO ipoQtyAmtDAO;
	
    public void setIpoQtyAmtDAO(IPOQtyAmtDAO ipoQtyAmtDAO) {
		this.ipoQtyAmtDAO = ipoQtyAmtDAO;
	}

	public Collection getIPOQtyAmtRcrd(long ipoMasterId) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            Collection ipoCollection = ipoQtyAmtDAO.getIPOQtyAmtRcrd(ipoMasterId);
            return ipoCollection;
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("No QtyAmt found in the database!");
            return null;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public void insertIPOQtyAmt(IPOQtyAmt newIpoQtyAmtRcrd) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoQtyAmtDAO.insertIPOQtyAmtRcrd(newIpoQtyAmtRcrd);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public void removeIPOQtyAmtRcrd(long ipoMasterId) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoQtyAmtDAO.removeIPOQtyAmtRcrd(ipoMasterId);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO QtyAmt for the id " + ipoMasterId +
                        " is not found in the database!");
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public BigDecimal getAmtByMasterIdQty(long ipoMasterId, int ipoQty) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            return ipoQtyAmtDAO.getAmtbyMasterIdQty(ipoMasterId, ipoQty);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public void importIPOQtyAmtFile(String ipoCrtFilePath) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            ipoQtyAmtDAO.importFile(ipoCrtFilePath);
        }
        catch (RecordNotFoundException rnfe) {
            logger.info("IPO Record for the id " + ipoCrtFilePath +
                        " is not found in the database!");
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }

}
