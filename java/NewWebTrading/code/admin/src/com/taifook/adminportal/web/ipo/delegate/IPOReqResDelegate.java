package com.taifook.adminportal.web.ipo.delegate;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.web.ipo.dao.IPOReqResDAO;
import com.taifook.adminportal.web.ipo.dao.IPOReqResDAOFactory;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;

public class IPOReqResDelegate {

    public Collection getIPOReqRes(long ipoMasterId) throws IPORequestException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOReqResDAO ipoReqResDAO = (IPOReqResDAO) IPOReqResDAOFactory.
                instance().getIPOReqResDAO();
            Collection col = ipoReqResDAO.getIPOReqRes(ipoMasterId);

            return col;
        }
        catch (DAOException de) {
            logger.error(de);
            throw new IPORequestException();
        }
    }

    public void exportFileByIPO(long ipoMasterId,
                                String ipoExportReqResFilePath, String ipoName) throws
        IPORequestException {

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOReqResDAO ipoReqResDAO = (IPOReqResDAO) IPOReqResDAOFactory.
                instance().getIPOReqResDAO();
            ipoReqResDAO.exportFileByIPO(ipoMasterId, ipoExportReqResFilePath,
                                         ipoName);
        }

        catch (DAOException de) {
            logger.error(de);
            throw new IPORequestException();
        }
    }

    public Collection getIPORequestOrder(String aeCode, String acCode,ArrayList acList) throws
        IPORequestException ,Exception{

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOReqResDAO ipoReqResDAO = (IPOReqResDAO) IPOReqResDAOFactory.
                instance().getIPOReqResDAO();
            Collection col = ipoReqResDAO.getIPORequestOrder(aeCode, acCode,acList);
            return col;
        }
        catch (Exception de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public Collection getIPORequestOrder(String aeCode, String acCode,
                                         long ipoMasterId,ArrayList acList) throws
        IPORequestException ,Exception{

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOReqResDAO ipoReqResDAO = (IPOReqResDAO) IPOReqResDAOFactory.
                instance().getIPOReqResDAO();
            Collection col = ipoReqResDAO.getIPORequestOrder(aeCode, acCode,
                ipoMasterId,acList);
            return col;
        }
        catch (Exception de) {
            logger.error(de);
            throw new Exception();
        }
    }

    public Collection getIPORequestOrder(String aeCode, long ipoMasterId,ArrayList acList) throws
        IPORequestException ,Exception{

        Log logger = LogFactory.getLog(this.getClass());

        try {
            IPOReqResDAO ipoReqResDAO = (IPOReqResDAO) IPOReqResDAOFactory.
                instance().getIPOReqResDAO();
            Collection col = ipoReqResDAO.getIPORequestOrder(aeCode,
                ipoMasterId,acList);
            return col;
        }
        catch (Exception de) {
            logger.error(de);
            throw new Exception();
        }
    }

//    public Collection queryAcByAe(String aeCode) throws
//        QueryAEMappingException {
//
//        Log logger = LogFactory.getLog(this.getClass());
//        try {
//            EserviceSecurityDelegate eserviceDelegate = new EserviceSecurityDelegate();
//            Collection acCol = eserviceDelegate.queryAcByAe(aeCode);
//            return acCol;
//        }
//        catch (Exception rnfe) {
//            throw new QueryAEMappingException();
//        }
//    }

}
