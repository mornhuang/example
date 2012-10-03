package com.taifook.adminportal.web.ipo.dao.hibernate;

import java.util.*;
import java.util.zip.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.util.DateUtil;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.web.ipo.dao.IPORequestDAO;
import com.taifook.adminportal.web.ipo.dto.IPOReqResOrder;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.common.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.taifook.adminportal.web.ipo.dto.IPORequest;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;
//import com.taifook.adminportal.common.util.OracleHibernateUtil;
import com.itsz.common.util.OracleHibernateUtil;

public class IPORequestDAOHbmImpl
    implements IPORequestDAO {
    private static Log logger = LogFactory.getLog(Constants.LOG_DEBUG_DB);
    public IPORequest getIPORequest(long applyId) throws DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

            IPORequest ipoRequest = (IPORequest) session.load(IPORequest.class,
                new Long(applyId));

            // Close session
            OracleHibernateUtil.closeSession();
            ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.
                getApplyDate()));
            ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                ipoRequest.getApplyQuantity()));
            ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoRequest.
                getDsptAmount()));
            OracleHibernateUtil.closeSession();
            return ipoRequest;

        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public Collection getIPORequestByIPO(long ipoMasterId) throws DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPORequest as ipoApply where ipoApply.ipoMasterId = ? order by ipoApply.applyId desc");
            query.setLong(0, ipoMasterId);
            List IPORequestList = query.list();
            IPORequest ipoRequest;
            for (int i = 0; i < IPORequestList.size(); i++) {
                ipoRequest = (IPORequest) IPORequestList.get(i);
                ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.
                    getApplyDate()));
                ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoRequest.getApplyQuantity()));
                ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoRequest.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPORequestList;

        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public Long getApplyId(String accountId, long ipoMasterId) throws
        DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPORequest as ipoApply where ipoApplyId.accountId=? and ipoApply.ipoMasterId = ?");
            query.setString(0, accountId);
            query.setLong(1, ipoMasterId);
            java.util.List list = query.list();
            Long applyId = new Long(0);
            if (list.size() > 0) {
                IPORequest ipoRequest = new IPORequest();
                ipoRequest = (IPORequest) list.get(0);
                applyId = ipoRequest.getApplyId();
            }
            // Close session
            //OracleHibernateUtil.closeSession();

            return applyId;

        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public IPORequest getIPORequest(String accountId, long ipoMasterId) throws
        DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPORequest as ipoApply where ipoApply.accountId=? and ipoApply.ipoMasterId = ? and ipoApply.status='received' ");
            query.setString(0, accountId);
            query.setLong(1, ipoMasterId);
            java.util.List list = query.list();
            IPORequest ipoRequest = new IPORequest();
            if (list.size() > 0) {
                ipoRequest = (IPORequest) list.get(0);
            }
            // Close session
            OracleHibernateUtil.closeSession();
            ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.
                getApplyDate()));
            ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                ipoRequest.getApplyQuantity()));
            ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoRequest.
                getDsptAmount()));
            OracleHibernateUtil.closeSession();
            return ipoRequest;

        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public Collection getIPORequestByAccount(String accountId) throws
        DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPORequest as ipoApply where ipoApply.accountId like ? order by ipoApply.applyId desc");
            query.setString(0, accountId);
            List IPORequestList = query.list();
            IPORequest ipoRequest;
            for (int i = 0; i < IPORequestList.size(); i++) {
                ipoRequest = (IPORequest) IPORequestList.get(i);
                ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.
                    getApplyDate()));
                ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoRequest.getApplyQuantity()));
                ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoRequest.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPORequestList;

        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public Collection getIPORequestCol(String accountId, long ipoMasterId) throws
        DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPORequest as ipoApply where ipoApply.accountId like ? and ipoApply.ipoMasterId = ? order by ipoApply.applyId desc");
            query.setString(0, accountId);
            query.setLong(1, ipoMasterId);
            java.util.List IPORequestList = query.list();
            IPORequest ipoRequest;
            for (int i = 0; i < IPORequestList.size(); i++) {
                ipoRequest = (IPORequest) IPORequestList.get(i);
                ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.
                    getApplyDate()));
                ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoRequest.getApplyQuantity()));
                ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoRequest.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();
            return IPORequestList;

        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public void insertIPORequest(IPORequest newIpoRequest) throws DAOException {
        try {

            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();
            //IPORequest ipoRequest=getIPORequest(newIpoRequest.getAccountId(),newIpoRequest.getIpoMasterId().longValue());
            Query query = session.createQuery("from IPORequest as ipoApply where ipoApply.accountId=? and ipoApply.ipoMasterId = ?");
            query.setString(0, newIpoRequest.getAccountId());
            query.setLong(1, newIpoRequest.getIpoMasterId().longValue());
            java.util.List list = query.list();
            IPORequest ipoRequest = new IPORequest();
            if (list.size() > 0) {
                ipoRequest = (IPORequest) list.get(0);
            }
            if (ipoRequest != null && ipoRequest.getApplyId() != null) {
                if("received".equals(ipoRequest.getStatus()))
                {
                    logger.error("Exception:double ipoApply for account and ipo! " );
                    throw new DAOException("Exception:double ipoApply for account and ipo! ");

                }
                else
                {
                    session.delete(ipoRequest);
                }
            }
            session.save(newIpoRequest);

            tx.commit();

            // Close session
            OracleHibernateUtil.closeSession();
        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }

    }

    public Collection getAllIPORequest() throws DAOException {

        try {

            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery(
                "from IPORequest as ipoApply order by ipoApply.applyId desc");
            List IPORequestList = query.list();
            IPORequest ipoRequest;
            for (int i = 0; i < IPORequestList.size(); i++) {
                ipoRequest = (IPORequest) IPORequestList.get(i);
                ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.
                    getApplyDate()));
                ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoRequest.getApplyQuantity()));
                ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoRequest.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPORequestList;
        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }

    }

    public void removeIPORequest(long applyId) throws DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();

            IPORequest ipoRequest = (IPORequest) session.load(IPORequest.class,
                new Long(applyId));
            ipoRequest.setStatus("Cancelled");
            session.update(ipoRequest);
            session.flush();

            tx.commit();

            // Close session
            OracleHibernateUtil.closeSession();
        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public void exportFileByIPO(long ipoMasterId, String filePath) throws
        DAOException {
        try {
            String ipoCode = "";

            Session bgnSession = OracleHibernateUtil.currentSession();

            IPORecord ipoRecord = (IPORecord) bgnSession.load(IPORecord.class,
                new Long(ipoMasterId));
            ipoCode = ipoRecord.getIpoCode();

            //OracleHibernateUtil.closeSession();

            Session session = OracleHibernateUtil.currentSession();
            Query query = session.createQuery("from IPORequest as ipoApply where ipoApply.ipoMasterId = ? and ipoApply.status='received' order by ipoApply.applyId desc");
            query.setLong(0, ipoMasterId);
            List ipoRequestList = query.list();
            int listLen = ipoRequestList.size();

            File objFile;
            FileWriter objFileWriter;

            objFile = new File(filePath);
            objFileWriter = new FileWriter(objFile);
            BufferedWriter br = new BufferedWriter(objFileWriter);
            PrintWriter pw = new PrintWriter(br);

            Vector vec;
            String spltStr = "|";
            String lineStr = "";

            long applyId;
            String accountId;
            String stockCode;
            int applyQuantity;
            BigDecimal dsptAmount;
            Date applDate;
            String remark;
            String telephone;
            String eMail;
            String status;

            for (int i = 0; i < listLen; i++) {
                IPORequest ipoRequest = new IPORequest();
                ipoRequest = (IPORequest) ipoRequestList.get(i);

                applyId = ipoRequest.getApplyId().longValue();
                accountId = ipoRequest.getAccountId();
                stockCode = ipoRequest.getStockCode();
                applyQuantity = ipoRequest.getApplyQuantity();
                dsptAmount = ipoRequest.getDsptAmount();
                applDate = ipoRequest.getApplyDate();
                try {
                    remark = ipoRequest.getRemark().toString();
                }
                catch (Exception e) {
                    remark = "";
                    logger.error(e.getMessage());
                }
                //remark = "online";
                telephone = ipoRequest.getTelephone();
                eMail = ipoRequest.getEmail();
                status = ipoRequest.getStatus();

                lineStr = ipoCode + spltStr + "" + spltStr + accountId +
                    spltStr +
                    applyQuantity + spltStr +
                    dsptAmount + spltStr + remark +
                    spltStr + applyId;
                if (i == listLen - 1) {
                    pw.print(lineStr);
                }
                else {
                    pw.print(lineStr + "\r\n");
                }
            }
            if (listLen < 1) {
                lineStr = "";
                pw.print(lineStr + "\r\n");
            }

            pw.close();
            objFileWriter.close();
            OracleHibernateUtil.closeSession();

        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(),
                                   re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public IPOReqResOrder getIPORequestAndResultStauts(long ipoMasterId,
        String accountId) throws
        DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();
            IPOReqResOrder ipoRequestAndResult = new IPOReqResOrder();
            Query query = session.getNamedQuery("ipoApplyQuery");
            query.setLong(0, ipoMasterId);
            query.setString(1, accountId);
            List list = query.list();
            if (list.size() > 0) {
                ipoRequestAndResult = (IPOReqResOrder) list.get(0);
            }
            OracleHibernateUtil.closeSession();
            return ipoRequestAndResult;
        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }

    }

    public static void main(String[] args) {
        IPORequestDAOHbmImpl testAction = new IPORequestDAOHbmImpl();
        try {
            Session session = OracleHibernateUtil.currentSession();
            IPOReqResOrder ipoRequestAndResult = new IPOReqResOrder();

            Query query = session.getNamedQuery("ipoApplyQuery");
            query.setLong(0, 7);
            query.setString(1, "02-0082098-33");
            List list = query.list();
            ipoRequestAndResult = (IPOReqResOrder) list.get(0);

        }
        catch (Exception e) {

        }
    }

}
