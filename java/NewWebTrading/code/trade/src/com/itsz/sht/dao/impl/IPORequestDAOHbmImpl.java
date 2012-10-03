package com.itsz.sht.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.itsz.sht.dao.IPORequestDAO;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.dto.ipo.IPORequest;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.common.util.DateUtil;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

public class IPORequestDAOHbmImpl extends HibernateDaoSupport implements IPORequestDAO {

    public IPORequest getIPORequest(long applyId) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            IPORequest ipoRequest = (IPORequest) getHibernateTemplate().load(IPORequest.class,new Long(applyId));
            ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.getApplyDate()));
            ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoRequest.getApplyQuantity()));
            ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoRequest.getDsptAmount()));
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

    public Collection getIPORequestByIPO(long ipoMasterId) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            List IPORequestList = getHibernateTemplate().find("from IPORequest as ipoApply where ipoApply.ipoMasterId = "+String.valueOf(ipoMasterId)+" order by ipoApply.applyId desc");
            IPORequest ipoRequest;
            for (int i = 0; i < IPORequestList.size(); i++) {
                ipoRequest = (IPORequest) IPORequestList.get(i);
                ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.getApplyDate()));
                ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoRequest.getApplyQuantity()));
                ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoRequest.getDsptAmount()));
            }
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

    public Long getApplyId(String accountId, long ipoMasterId) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            java.util.List list = getHibernateTemplate().find("from IPORequest as ipoApply where ipoApply.accountId= '"+accountId+"' and ipoApply.ipoMasterId = "+String.valueOf(ipoMasterId));
            Long applyId = new Long(0);
            if (list.size() > 0) {
                IPORequest ipoRequest = new IPORequest();
                ipoRequest = (IPORequest) list.get(0);
                applyId = ipoRequest.getApplyId();
            }
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

    public IPORequest getIPORequest(String accountId, long ipoMasterId) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            java.util.List list = getHibernateTemplate().find("from IPORequest as ipoApply where ipoApply.accountId= '"+accountId+"' and ipoApply.ipoMasterId = "+String.valueOf(ipoMasterId)+" and ipoApply.status='received'");
            IPORequest ipoRequest = new IPORequest();
            if (list.size() > 0) {
                ipoRequest = (IPORequest) list.get(0);
                ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest. getApplyDate()));
                ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty( ipoRequest.getApplyQuantity()));
                ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoRequest.getDsptAmount()));
            }
            return ipoRequest;
        }
        catch (ObjectNotFoundException onfe) {
            logger.info("IPO Request for id " + ipoMasterId +
                        " not found in the database!");
            throw new RecordNotFoundException("IPO Request for id " +
                                              ipoMasterId +
                                              " not found in the database!",
                                              onfe);
        }
        catch (HibernateException he) {
            logger.error("OracleHibernateUtil " + he.getMessage());
            throw new DAOException("OracleHibernateUtil " + he.getMessage(), he);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public Collection getIPORequestByAccount(String accountId) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            List IPORequestList = getHibernateTemplate().find("from IPORequest as ipoApply where ipoApply.accountId like '"+accountId+"' order by ipoApply.applyId desc");;
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

    public Collection getIPORequestCol(String accountId, long ipoMasterId) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            java.util.List IPORequestList = getHibernateTemplate().find("from IPORequest as ipoApply where ipoApply.accountId like '"+accountId+"' and ipoApply.ipoMasterId = "+String.valueOf(ipoMasterId)+" order by ipoApply.applyId desc");
            IPORequest ipoRequest;
            for (int i = 0; i < IPORequestList.size(); i++) {
                ipoRequest = (IPORequest) IPORequestList.get(i);
                ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.getApplyDate()));
                ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoRequest.getApplyQuantity()));
                ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoRequest.getDsptAmount()));
            }
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
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            getHibernateTemplate().save(newIpoRequest);
        }catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(), re);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public Collection getAllIPORequest() throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            List IPORequestList = getHibernateTemplate().find("from IPORequest as ipoApply order by ipoApply.applyId desc");
            IPORequest ipoRequest;
            for (int i = 0; i < IPORequestList.size(); i++) {
                ipoRequest = (IPORequest) IPORequestList.get(i);
                ipoRequest.setApplyDate_dsply(DateUtil.formatDate(ipoRequest.getApplyDate()));
                ipoRequest.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoRequest.getApplyQuantity()));
                ipoRequest.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoRequest.getDsptAmount()));
            }
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

    public void removeIPORequest(long applyId) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            IPORequest ipoRequest = (IPORequest) session.load(IPORequest.class,new Long(applyId));
            ipoRequest.setStatus("Cancelled");
            session.update(ipoRequest);
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

    public void exportFileByIPO(long ipoMasterId, String filePath) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();     
            String ipoCode = "";
            IPORecord ipoRecord = (IPORecord) session.load(IPORecord.class,new Long(ipoMasterId));
            ipoCode = ipoRecord.getIpoCode();
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
                    pw.print(lineStr+"\r\n");
                }
            }
            if (listLen < 1) {
                lineStr = "";
                pw.print(lineStr+"\r\n");
            }

            pw.close();
            objFileWriter.close();
        }
        catch (RuntimeException re) {
            logger.error("RuntimeException " + re.getMessage());
            throw new DAOException("RuntimeException " + re.getMessage(),re);
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
        IPORequestDAOHbmImpl testAction = new IPORequestDAOHbmImpl();
        try {
            //Collection col = testAction.getAllIPORecord();

            //IPORequest ipoRequest = new IPORequest();
            ///Long applyId;

            //ipoRequest.setAccountId("02-0076449-301111");
            //ipoRequest.setApplyDate(new Date());
            //ipoRequest.setApplyQuantity(800);
            //ipoRequest.setDsptAmount(new BigDecimal(23423.45));
            //ipoRequest.setIpoMasterId(new Long(187));
            // ipoRequest.setUpdateTime(new Date());
            //List list = (List) testAction.getIPORequestCol("%7644%", 277);
            System.out.print(File.separatorChar);
            //System.out.print(list.size());

            //ipoRequest=testAction.getIPORequest("02-0076449-30",187);
            // System.out.print("applyId="+ipoRequest.getApplyId());
            // Date applyDate = new Date();
            // System.out.print("applyDate="+applyDate);
            // String dateStr = "2005-06-16 12:00:00.000";
            //String str1 = null;
            // String str2 = str1.toString();
            // System.out.print("str2=" + str2);

            // Collection col=testAction.getIPORequestByIPO(9);
            // System.out.print(col.size());
            //IPORequest ipoRequest = new IPORequest();
            //testAction.exportFileByIPO(9,"D:\\cvsroot\\request.dat");
            //String path = System.getProperty("user.dir");
            //System.out.print("path="+path);

        }
        catch (Exception e) {
            System.out.print(e);
        }
    }

}
