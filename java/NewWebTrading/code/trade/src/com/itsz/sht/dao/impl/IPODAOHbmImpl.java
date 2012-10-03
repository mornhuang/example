package com.itsz.sht.dao.impl;

import java.util.List;
import java.util.Date;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.itsz.sht.dao.IPODAO;
import com.itsz.sht.dto.ipo.IPOQtyAmt;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.dto.ipo.IPORequest;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.common.util.DateUtil;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

public class IPODAOHbmImpl extends HibernateDaoSupport implements IPODAO {

    public IPORecord getIPORecord(long ipoMasterId) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            List IPOList = getHibernateTemplate().find("from IPORecord as ipoRecord where ipoRecord.ipoMasterId="+String.valueOf(ipoMasterId));
            if(IPOList==null || IPOList.size()<=0){
            	return null;
            }
            IPORecord ipoRecord = (IPORecord)IPOList.get(0);
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
            return ipoRecord;

        }
        catch (ObjectNotFoundException onfe) {
            logger.info("IPO Record for id " + ipoMasterId + " not found in the database!");
            throw new RecordNotFoundException("IPO Record for id " +ipoMasterId + " not found in the database!",onfe);
        }
        catch (HibernateException he) {
            he.printStackTrace(); //
            logger.error("OracleHibernateUtil " + he.getMessage());
            throw new DAOException("OracleHibernateUtil " + he.getMessage(), he);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public void insertIPORecord(IPORecord newIpoRecord) throws DAOException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            getHibernateTemplate().save(newIpoRecord);
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

    public void updateIPORecord(long ipoMasterId, IPORecord newIpoRecord) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            getHibernateTemplate().update(newIpoRecord);
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

    public java.util.Collection getAllIPORecord() throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            java.util.Collection IPOList = getHibernateTemplate().find("from IPORecord as ipoRecord order by ipoRecord.ipoMasterId desc");
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

    public void removeIPORecord(long ipoMasterId) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();            
            Transaction tx = session.beginTransaction();
            IPORecord ipoRecord = (IPORecord) session.load(IPORecord.class, new Long(ipoMasterId));

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
        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
        finally{
        	if(session!=null){
        		session.close();
        	}        	
        }
    }

    public IPORecord getIPOInfo(String ipoCode) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        IPORecord ipoRcrd = new IPORecord();
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
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
        finally{
        	if(session!=null){
        		session.close();
        	}        	
        }
    }

    public void importFile(String filePath) throws DAOException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
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
                String[] vec;
                int ch;

                while ( (ch = br.read()) != -1) {
                    IPORecord ipoRecord = new IPORecord();
                    strData = (char) ch + br.readLine();
                    vec = StringUtils.split(strData, "|");

                    ipoCode = ( (String) vec[0]).trim();
                    ipoName = ( (String) vec[1]).trim();
                    stockCode = ( (String) vec[2]).trim();
                    price = new BigDecimal( ( (String) vec[3]).trim());
                    quantity = Integer.parseInt( (String) vec[4]);
                    shareAmt = new BigDecimal( ( (String) vec[5]).trim());
                    handFee = new BigDecimal( ( (String) vec[6]).trim());
                    deadLine = Timestamp.valueOf( (String) vec[7]);
                    depositDate = Timestamp.valueOf( (String) vec[8]);
                    refundDate = Timestamp.valueOf( (String) vec[9]);
                    shrCollectDate = Timestamp.valueOf( (String) vec[10]);
                    tradeDate = Timestamp.valueOf( (String) vec[11]);
                    createDate = Timestamp.valueOf( (String) vec[14]);
                    lastAmendDate = Timestamp.valueOf( (String) vec[15]);
                    loanIntRate = new BigDecimal( ( (String) vec[12]).trim());
                    minDpstRate = new BigDecimal( ( (String) vec[13]).trim());

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
                objFileReader.close();
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
        finally{
        	if(session!=null){
        		session.close();
        	}        	
        }
    }

    public static void main(String[] args) {
        IPODAOHbmImpl testAction = new IPODAOHbmImpl();
        try {
            // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            //Timestamp timeStamp = new Timestamp() ;
            /*IPORecord testRecord = new IPORecord();
            testRecord = testAction.getIPORecord(3);
            System.out.print(testRecord.getIpoName());
            Date dateline = Timestamp.valueOf("2005-06-13 12:34:12.234");
            Date testDate = Timestamp.valueOf("2005-06-16 15:00:00.0");
            Date debitDate = Timestamp.valueOf("2005-06-16 15:00:00.0");
            testRecord.setDeadLine(dateline);
            testRecord.setDebitDate(debitDate);
            testRecord.setDepositDate(testDate);

            testAction.updateIPORecord(3, testRecord);
            */




            //testAction.getIPOInfo("IP05000025");
            //System.out.println(ipoRecord.getIpoName());
            // testAction.importFile("D:\\cvsroot\\MIS_IPOMasterFullList.dat");
            /*
                         SimpleDateFormat df = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");*/
            //Date applyDate = new Date();

            //String dateString = df.format(applyDate);
            //System.out.print(dateString);
            //Integer pp = new Integer(2);
            //int page = pp.intValue();

        }
        catch (Exception e) {
            //System.out.print(e);
        }
    }

}
