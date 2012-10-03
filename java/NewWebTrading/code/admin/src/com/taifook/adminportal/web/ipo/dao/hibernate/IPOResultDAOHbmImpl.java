package com.taifook.adminportal.web.ipo.dao.hibernate;

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.web.ipo.dao.IPOResultDAO;
import com.taifook.adminportal.web.ipo.dto.IPOResult;
import com.taifook.adminportal.web.ipo.dto.IPOResultStatus;
import com.taifook.adminportal.common.util.StringUtil;
import com.taifook.adminportal.exceptions.DAOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import com.taifook.adminportal.common.util.OracleHibernateUtil;
import com.itsz.common.util.OracleHibernateUtil;

public class IPOResultDAOHbmImpl
    implements IPOResultDAO {
    private  static Log logger = LogFactory.getLog(Constants.LOG_DEBUG_DB);
    public IPOResult getIPOResult(long applyId) throws DAOException{
        try {
            Session session = OracleHibernateUtil.currentSession();

            IPOResult ipoResult = (IPOResult) session.load(IPOResult.class,
                new Long(applyId));

            // Close session
            // OracleHibernateUtil.closeSession();
            ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoResult.
                getApplyQuantity()));
            ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoResult.
                getDsptAmount()));
            return ipoResult;

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

    public void insertIPOResult(IPOResult newIpoResult) throws DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();

            session.save(newIpoResult);
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

    public Collection getAllIPOResult(String ipoCode) throws DAOException{

        try {

            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.ipoCode=? order by ipoResult.prgStatus");
            query.setString(0, ipoCode);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoResult.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPOResultList;
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

    public Collection getPageIPOResult(String ipoCode, int firstResult,
                                       int fetchSize) throws DAOException{

        try {

            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.ipoCode=? order by ipoResult.applyId");
            query.setString(0, ipoCode);
            query.setFirstResult(firstResult);
            query.setMaxResults(fetchSize);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoResult.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPOResultList;
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

    public Collection getIPOResultStatusAndCount(String ipoCode) throws
        DAOException {

        try {

            Session session = OracleHibernateUtil.currentSession();
            Query query = session.createQuery("select ipoResult.prgStatus,count(ipoResult.prgStatus) from IPOResult as ipoResult where ipoResult.ipoCode=? group by ipoResult.prgStatus");
            query.setString(0, ipoCode);
            List ipoResultStatusList = query.list();
            Object[] record;
            ArrayList statusCountList = new ArrayList();
            String status;
            for (int i = 0; i < ipoResultStatusList.size(); i++) {
                IPOResultStatus ipoResultStatus = new IPOResultStatus();
                record = (Object[]) ipoResultStatusList.get(i);
                status = record[0].toString();
                if ("0".equals(status)) {
                    status = Constants.IPO_APP_PRG;
                }
                else {
                    if ("3".equals(status)) {
                        status = Constants.IPO_ALL_AVL;
                    }
                    else {
                        if ("2".equals(status)) {
                            status = Constants.IPO_MIS_CLD;
                        }
                        else {
                            status = Constants.IPO_MIS_RJD;
                        }
                    }
                }

                ipoResultStatus.setResultStatus(status);
                ipoResultStatus.setRecordCount(record[1].toString());
                statusCountList.add(ipoResultStatus);
            }
            query = session.createQuery(
                "select count(*) from IPOResult as ipoResult where ipoResult.ipoCode=?");
            query.setString(0, ipoCode);

            List ipoResultList = query.list();
            IPOResultStatus ipoResultTotal = new IPOResultStatus();

            String count = "";
            count = ipoResultList.get(0).toString();
            status = Constants.IPO_TOTAL;
            ipoResultTotal.setResultStatus(status);
            ipoResultTotal.setRecordCount(count);
            statusCountList.add(ipoResultTotal);

            OracleHibernateUtil.closeSession();
            return statusCountList;
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

    public Collection getIPOResultByAccount(String accountId) throws
        DAOException{

        try {

            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.accountId like ? order by ipoResult.applyId desc");
            query.setString(0, accountId);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoResult.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPOResultList;
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

    public Collection getPageIPOResultByAccount(String accountId,
                                                int firstResult, int fetchSize) throws
        DAOException{

        try {

            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.accountId like ? order by ipoResult.applyId desc");
            query.setString(0, accountId);
            query.setFirstResult(firstResult);
            query.setMaxResults(fetchSize);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoResult.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPOResultList;
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

    public Collection getIPOResultCol(String ipoCode, String accountId) throws
        DAOException{

        try {

            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.ipoCode=? and ipoResult.accountId like ? order by ipoResult.applyId desc");
            query.setString(0, ipoCode);
            query.setString(1, accountId);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoResult.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPOResultList;
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

    public Collection getPageIPOResultCol(String ipoCode, String accountId,
                                          int firstResult, int fetchSize) throws
        DAOException {

        try {

            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.ipoCode=? and ipoResult.accountId like ? order by ipoResult.applyId desc");
            query.setString(0, ipoCode);
            query.setString(1, accountId);
            query.setFirstResult(firstResult);
            query.setMaxResults(fetchSize);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(
                    ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(
                    ipoResult.getDsptAmount()));

            }

            // Close session
            OracleHibernateUtil.closeSession();

            return IPOResultList;
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

    public void removeIPOResult(long applyId) throws DAOException{

        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();

            IPOResult ipoResult = (IPOResult) session.load(IPOResult.class,
                new Long(applyId));
            session.delete(ipoResult);
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

    public void updateIPOResult(long applyId, IPOResult ipoResult) throws
        DAOException{
        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();
            session.update(ipoResult);
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

    public void updateAllIPOResult(String ipoCode, String status) throws
        DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.ipoCode=? and (ipoResult.prgStatus='3' or ipoResult.prgStatus='0') order by ipoResult.prgStatus");
            query.setString(0, ipoCode);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setPrgStatus(status);
                session.saveOrUpdate(ipoResult);
            }

            // Close session
            tx.commit();
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

    public void importFile(String filePath) throws DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();

            File objFile;
            FileReader objFileReader;
            String strData = "";

            Long applyId;
            String ipoCode;
            String accountId;
            int applyQuantity;
            BigDecimal dsptAmount;
            String prgStatus;
            String misRefCode;
            String rejectCode;

            objFile = new File(filePath);
            Long initApplyId;
            if (objFile.exists()) {
                objFileReader = new FileReader(objFile);
                BufferedReader br = new BufferedReader(objFileReader);
                StringUtil stringUtil = new StringUtil();
                Vector vec;
                int ch;

                while ( (ch = br.read()) != -1) {
                    IPOResult testResult = new IPOResult();
                    IPOResult ipoResult = new IPOResult();
                    strData = (char) ch + br.readLine();
                    if(strData==null||strData==""||strData.length()<6)
                        continue;
                    vec = stringUtil.strSplit(strData, "|");
                    ipoCode = ( (String) vec.get(0)).trim();
                    accountId = ( (String) vec.get(1)).trim();
                    applyQuantity = Integer.parseInt( ( (String) vec.get(2)).
                        trim());

                    dsptAmount = new BigDecimal( ( (String) vec.get(3)).trim());

                    applyId = new Long( ( (String) vec.get(5)).trim());
                    prgStatus = ( (String) vec.get(6)).trim();
                    misRefCode = ( (String) vec.get(7)).trim();

                    try {
                        rejectCode = ( (String) vec.get(8)).trim();
                    }
                    catch (Exception e) {
                        rejectCode = "";
                        logger.error(e.getMessage());
                    }

                    try {
                        testResult = getIPOResult(applyId.longValue());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    if (testResult.getApplyId() != null &&
                        testResult.getApplyId().longValue() > 0) {

                        initApplyId = testResult.getApplyId();
                        ipoResult = (IPOResult) session.load(IPOResult.class,
                            applyId);
                        ipoResult.setApplyId(initApplyId);
                        ipoResult.setIpoCode(ipoCode);
                        ipoResult.setAccountId(accountId);
                        ipoResult.setApplyQuantity(applyQuantity);
                        ipoResult.setDsptAmount(dsptAmount);
                        ipoResult.setMisRefCode(misRefCode);
                        ipoResult.setPrgStatus(prgStatus);
                        ipoResult.setRejectCode(rejectCode);

                        session.update(ipoResult);
                    }
                    else {
                        ipoResult.setIpoCode(ipoCode);
                        ipoResult.setAccountId(accountId);
                        ipoResult.setApplyId(applyId);
                        ipoResult.setApplyQuantity(applyQuantity);
                        ipoResult.setDsptAmount(dsptAmount);
                        ipoResult.setMisRefCode(misRefCode);
                        ipoResult.setPrgStatus(prgStatus);
                        ipoResult.setRejectCode(rejectCode);

                        session.save(ipoResult);
                    }

                }
                tx.commit();
                OracleHibernateUtil.closeSession();
                objFileReader.close(); // Close session

            }
            else {
                logger.error("Sorry,Can't find you give file :" + filePath);
            }

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
        IPOResultDAOHbmImpl testAction = new IPOResultDAOHbmImpl();
        try {

            //IPOResult ipoResult = new IPOResult();
            //BigDecimal dsptAmount = new BigDecimal("55555 ");
            //Collection col = testAction.getAllIPOResult("IP05000026");
            //testAction.removeIPOResult(9);
            //ipoResult=testAction.getIPOResult(161);

            // ipoResult.setAccountId("82583");
            ///ipoResult.setPrgStatus("4");
            //ipoResult.setIpoCode("IP05000027");
            testAction.getIPOResultStatusAndCount("IP05000026");

            // Collection col=testAction.getIPORequestByIPO(9);
            // testAction.importFile(
            //     "D:\\cvsroot\\IPOOrderRequestResult_IP04000083.dat");
            ///IPOResult ipoRequest = new IPOResult();
            ///testAction.removeIPORequest(6);

        }
        catch (Exception e) {
        }
    }

}
