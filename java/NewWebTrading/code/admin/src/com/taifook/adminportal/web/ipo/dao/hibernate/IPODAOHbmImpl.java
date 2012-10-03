package com.taifook.adminportal.web.ipo.dao.hibernate;

import java.util.List;
import java.util.Vector;
import java.util.Date;
import java.util.Collection;
import java.util.Locale;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.struts.Globals;

import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.common.util.DateUtil;
import com.taifook.adminportal.web.ipo.dao.IPODAO;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.web.ipo.dto.SimpleIPORecord;
import com.taifook.adminportal.web.ipo.dto.IPORequest;
import com.taifook.adminportal.web.ipo.dto.IPOResult;
import com.taifook.adminportal.web.ipo.dto.IPOQtyAmt;
import com.taifook.adminportal.common.util.StringUtil;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.common.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import com.taifook.adminportal.common.util.OracleHibernateUtil;
import com.itsz.common.util.OracleHibernateUtil;

public class IPODAOHbmImpl
    implements IPODAO {
    private static Log logger = LogFactory.getLog(Constants.LOG_DEBUG_DB);
    public IPORecord getIPORecord(long ipoMasterId) throws DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();
            IPORecord ipoRecord = (IPORecord) session.load(IPORecord.class,
                new Long(ipoMasterId));
            String fee = "";
            if (ipoRecord.getHandFee() != null) {
                fee = DataFormatUtil.hold2Decimal(ipoRecord.getHandFee());
            }
            ipoRecord.setFee(fee);
            ipoRecord.setCreateDate_dsply(DateUtil.formatDate(ipoRecord.
                getCreateDate()));
            ipoRecord.setDeadLine_dsply(DateUtil.formatDate(ipoRecord.
                getDeadLine()));
            ipoRecord.setDepositDate_dsply(DateUtil.formatDate(ipoRecord.
                getDepositDate()));
            ipoRecord.setLastAmendDate_dsply(DateUtil.formatDate(ipoRecord.
                getLastAmendDate()));
            ipoRecord.setRefundDate_dsply(DateUtil.formatOnlyDate(ipoRecord.
                getRefundDate()));
            ipoRecord.setShrCollectDate_dsply(DateUtil.formatDate(ipoRecord.
                getShrCollectDate()));
            ipoRecord.setTradeDate_dsply(DateUtil.formatOnlyDate(ipoRecord.
                getTradeDate()));
            ipoRecord.setDebitDate_dsply(DateUtil.formatDate(ipoRecord.
                getDebitDate()));
            ipoRecord.setDebitDate_dsply_mng(DateUtil.formatDate(ipoRecord.
                getDebitDate()));
            ipoRecord.setPrice_dsply(DataFormatUtil.hold3Decimal(ipoRecord.
                getPrice()));
            ipoRecord.setQuantity_dsply(DataFormatUtil.formatQty(ipoRecord.
                getQuantity()));
            ipoRecord.setShareAmt_dsply(DataFormatUtil.formatAmt(ipoRecord.
                getShareAmt()));

            // Close session
            OracleHibernateUtil.closeSession();

            return ipoRecord;

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

    public void insertIPORecord(IPORecord newIpoRecord) throws DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();
            session.save(newIpoRecord);
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

    public void updateIPORecord(long ipoMasterId, IPORecord newIpoRecord) throws
        DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();

            session.update(newIpoRecord);
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

    public java.util.Collection getAllIPORecord() throws DAOException {

        try {

            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery(
                "from IPORecord as ipoRecord order by ipoRecord.ipoMasterId desc");

            java.util.Collection IPOList = query.list();
            // Close session
            OracleHibernateUtil.closeSession();
            return IPOList;

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

    public void removeIPORecord(long ipoMasterId) throws DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();

            IPORecord ipoRecord = (IPORecord) session.load(IPORecord.class,
                new Long(ipoMasterId));

            Query query = session.createQuery(
                "from IPOQtyAmt as ipoQtyAmt where ipoQtyAmt.id.ipoMasterId= ? order by ipoQtyAmt.id.ipoMasterId desc,ipoQtyAmt.id.shareQty desc");
            query.setLong(0, ipoMasterId);
            java.util.List it = query.list();
            int len = it.size();
            IPOQtyAmt newIpoQtyAmt;
            for (int i = 0; i < len; i++) {
                newIpoQtyAmt = (IPOQtyAmt) it.get(i);
                session.delete(newIpoQtyAmt);
            }
            query = session.createQuery(
                "from IPORequest as ipoRequest where ipoRequest.ipoMasterId = ? order by ipoRequest.ipoMasterId desc");
            query.setLong(0, ipoMasterId);
            it = query.list();
            len = it.size();
            IPORequest ipoRequest;
            for (int i = 0; i < len; i++) {
                ipoRequest = (IPORequest) it.get(i);
                session.delete(ipoRequest);
            }

            session.delete(ipoRecord);
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

    public IPORecord getIPOInfo(String ipoCode) throws DAOException {

        IPORecord ipoRcrd = new IPORecord();
        try {
            Session session = OracleHibernateUtil.currentSession();

            Query query = session.createQuery(
                "from IPORecord as ipoRcrd where ipoRcrd.ipoCode=? ");
            query.setString(0, ipoCode);
            List ipoRecordList = query.list();

            int lenList = ipoRecordList.size();
            if (lenList > 0) {
                if (lenList > 1) {
                    logger.error("Sorry,the have two same ipoCode");
                    for (int i = 0; i < lenList; i++) {
                        ipoRcrd = (IPORecord) ipoRecordList.get(i);
                        logger.error("ipoMasterId=" + ipoRcrd.getIpoMasterId());
                    }
                    throw new DAOException(
                        "Exception:the have two same ipoCode");
                }
                else {
                    ipoRcrd = (IPORecord) ipoRecordList.get(0);
                    ipoRcrd.setCreateDate_dsply(DateUtil.formatDate(ipoRcrd.
                        getCreateDate()));
                    ipoRcrd.setDeadLine_dsply(DateUtil.formatDate(ipoRcrd.
                        getDeadLine()));
                    ipoRcrd.setDepositDate_dsply(DateUtil.formatDate(ipoRcrd.
                        getDepositDate()));
                    ipoRcrd.setLastAmendDate_dsply(DateUtil.formatDate(ipoRcrd.
                        getLastAmendDate()));
                    ipoRcrd.setRefundDate_dsply(DateUtil.formatOnlyDate(ipoRcrd.
                        getRefundDate()));
                    ipoRcrd.setShrCollectDate_dsply(DateUtil.formatDate(ipoRcrd.
                        getShrCollectDate()));
                    ipoRcrd.setTradeDate_dsply(DateUtil.formatOnlyDate(ipoRcrd.
                        getTradeDate()));
                    ipoRcrd.setDebitDate_dsply(DateUtil.formatDate(ipoRcrd.
                        getDebitDate()));
                    ipoRcrd.setDebitDate_dsply_mng(DateUtil.formatDate(ipoRcrd.
                        getDebitDate()));
                    ipoRcrd.setPrice_dsply(DataFormatUtil.hold3Decimal(ipoRcrd.
                        getPrice()));
                    ipoRcrd.setQuantity_dsply(DataFormatUtil.formatQty(ipoRcrd.
                        getQuantity()));
                    ipoRcrd.setShareAmt_dsply(DataFormatUtil.formatAmt(ipoRcrd.
                        getShareAmt()));

                }
            }
            else {
                logger.error("Not find this ipoRecord");
            }

            /// OracleHibernateUtil.closeSession();

            String fee = "";
            if (ipoRcrd.getHandFee() != null) {
                fee = DataFormatUtil.hold2Decimal(ipoRcrd.getHandFee());
            }

            ipoRcrd.setFee(fee);

            return ipoRcrd;

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
            String ipoCode;
            String ipoName;
            String stockCode;
            BigDecimal price;
            int quantity;
            BigDecimal shareAmt;
            BigDecimal handFee;
            Date deadLine;
            Date depositDate;
            Date refundDate;
            Date shrCollectDate;
            Date tradeDate;
            BigDecimal loanIntRate;
            BigDecimal minDpstRate;
            Date createDate;
            Date lastAmendDate;
            String status;

            Long initMasterId;
            IPORecord ipoRcrd = new IPORecord();

            objFile = new File(filePath);
            if (objFile.exists()) {
                objFileReader = new FileReader(objFile);
                BufferedReader br = new BufferedReader(objFileReader);
                StringUtil stringUtil = new StringUtil();
                Vector vec;
                int ch;

                while ( (ch = br.read()) != -1) {
                    IPORecord ipoRecord = new IPORecord();
                    strData = (char) ch + br.readLine();
                    if(strData==null||strData==""||strData.length()<6)
                        continue;
                    vec = stringUtil.strSplit(strData, "|");

                    ipoCode = ( (String) vec.get(0)).trim();
                    ipoName = ( (String) vec.get(1)).trim();
                    stockCode = ( (String) vec.get(2)).trim();
                    try{
                       int stockCodeInt = Integer.parseInt(stockCode);
                    }
                    catch(Exception e){
                      logger.error("ipoStockCode error!stockCode="+stockCode);
                      throw e;
                    }
                    price = new BigDecimal( ( (String) vec.get(3)).trim());
                    quantity = Integer.parseInt( (String) vec.get(4));
                    shareAmt = new BigDecimal( ( (String) vec.get(5)).trim());
                    handFee = new BigDecimal( ( (String) vec.get(6)).trim());
                    deadLine = Timestamp.valueOf( (String) vec.get(7));
                    depositDate = Timestamp.valueOf( (String) vec.get(8));
                    refundDate = Timestamp.valueOf( (String) vec.get(9));
                    shrCollectDate = Timestamp.valueOf( (String) vec.get(10));
                    tradeDate = Timestamp.valueOf( (String) vec.get(11));
                    createDate = Timestamp.valueOf( (String) vec.get(14));
                    lastAmendDate = Timestamp.valueOf( (String) vec.get(15));
                    loanIntRate = new BigDecimal( ( (String) vec.get(12)).trim());
                    minDpstRate = new BigDecimal( ( (String) vec.get(13)).trim());

                    ipoRcrd = getIPOInfo(ipoCode);
                    if (ipoRcrd.getIpoMasterId() != null) {
                        initMasterId = ipoRcrd.getIpoMasterId();
                        ipoRecord = (IPORecord) session.load(IPORecord.class,
                            initMasterId);
                        ipoRecord.setIpoMasterId(initMasterId);
                        ipoRecord.setIpoCode(ipoCode);
                        ipoRecord.setIpoName(ipoName);
                        ipoRecord.setStockCode(stockCode);
                        ipoRecord.setPrice(price);
                        ipoRecord.setQuantity(quantity);
                        ipoRecord.setShareAmt(shareAmt);
                        ipoRecord.setHandFee(handFee);

                        ipoRecord.setDeadLine(deadLine);
                        ipoRecord.setDepositDate(depositDate);
                        ipoRecord.setRefundDate(refundDate);
                        ipoRecord.setShrCollectDate(shrCollectDate);
                        ipoRecord.setTradeDate(tradeDate);
                        ipoRecord.setLoanIntRate(loanIntRate);
                        ipoRecord.setMinDpstRate(minDpstRate);
                        ipoRecord.setCreateDate(createDate);
                        ipoRecord.setLastAmendDate(lastAmendDate);
                        ipoRecord.setStatus("OPEN");

                        session.saveOrUpdate(ipoRecord);
                    }
                    else {
                        ipoRecord.setIpoCode(ipoCode);
                        ipoRecord.setIpoName(ipoName);
                        ipoRecord.setStockCode(stockCode);
                        ipoRecord.setPrice(price);
                        ipoRecord.setQuantity(quantity);
                        ipoRecord.setShareAmt(shareAmt);
                        ipoRecord.setHandFee(handFee);

                        ipoRecord.setDeadLine(deadLine);
                        ipoRecord.setDepositDate(depositDate);
                        ipoRecord.setRefundDate(refundDate);
                        ipoRecord.setShrCollectDate(shrCollectDate);
                        ipoRecord.setTradeDate(tradeDate);
                        ipoRecord.setLoanIntRate(loanIntRate);
                        ipoRecord.setMinDpstRate(minDpstRate);
                        ipoRecord.setCreateDate(createDate);
                        ipoRecord.setLastAmendDate(lastAmendDate);
                        ipoRecord.setStatus("OPEN");

                        session.save(ipoRecord);

                    }

                }
                tx.commit();
                OracleHibernateUtil.closeSession();
                objFileReader.close();
            }
            else {
                logger.error("Sorry,Can't find you give file :" + filePath);
            }

            // Close session

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

    public java.util.Collection getAllIPORecordAndStatus(HttpServletRequest
        request, String accountId,int firstRecord) throws DAOException {

        try {

            Session session = OracleHibernateUtil.currentSession();
            HttpSession requestSession = request.getSession();

            Query query = session.getNamedQuery("ipoRecordQuery");
            query.setString(0, accountId);
            query.setFirstResult(firstRecord);
            query.setMaxResults(20);
            List IPOQueryList = query.list();
            SimpleIPORecord ipoRecord = new SimpleIPORecord();

            if (IPOQueryList.size() > 0) {
                Locale currentLocaleObj = (Locale) requestSession.getAttribute(Globals.
                    LOCALE_KEY);
                String currentLocale = currentLocaleObj.toString();
                for (int i = 0; i < IPOQueryList.size(); i++) {
                    ipoRecord = (SimpleIPORecord) IPOQueryList.get(i);
                    ipoRecord.setDeadline_dsply(DateUtil.formatDateToMM(
                        ipoRecord.getDeadLine()));
                    ipoRecord.setTradeDate_dsply(DateUtil.formatOnlyDate(
                        ipoRecord.getTradeDate()));
                    if (ipoRecord.getResultStatus() != null &&
                        ipoRecord.getResultStatus() != "") {
                        ipoRecord.setApplyStatus(ipoRecord.getResultStatus());
                    }
                    if ("zh_TW".equals(currentLocale)) {
                        ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_big());
                    }
                    else {
                        if ("zh_CN".equals(currentLocale)) {
                            ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_gb());
                        }
                        else {
                            ipoRecord.setIpoName_dsply(ipoRecord.getIpoName());
                        }
                    }

                }
            }

            // Close session
            OracleHibernateUtil.closeSession();
            return IPOQueryList;

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

        IPODAOHbmImpl testAction = new IPODAOHbmImpl();
        try {
            Session session = OracleHibernateUtil.currentSession();

            Query query = session.getNamedQuery("ipoRecordQuery");
            query.setString(0, "02-0082098-33");
            query.setFirstResult(1);
            query.setMaxResults(20);
            java.util.List IPOList = query.list();
            for (int i = 0; i < IPOList.size(); i++) {
                SimpleIPORecord record = (SimpleIPORecord) IPOList.get(i);
            }

            // Close session
            OracleHibernateUtil.closeSession();

        }
        catch (Exception e) {
        }

    }

}
