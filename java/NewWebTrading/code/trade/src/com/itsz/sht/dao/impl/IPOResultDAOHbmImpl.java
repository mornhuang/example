package com.itsz.sht.dao.impl;

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.itsz.sht.common.Constants;
import com.itsz.sht.dao.IPOResultDAO;
import com.itsz.sht.dto.ipo.IPOResult;
import com.itsz.sht.dto.ipo.IPOResultStatus;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

public class IPOResultDAOHbmImpl extends HibernateDaoSupport implements IPOResultDAO {

    public IPOResult getIPOResult(long applyId) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
        	IPOResult ipoResult = null;
            List IPOResultList = getHibernateTemplate().find("from IPOResult as ipoResult where ipoResult.applyId="+String.valueOf(applyId)+" order by ipoResult.prgStatus");            
            if(IPOResultList!=null && IPOResultList.size()>0){
            	ipoResult = (IPOResult)IPOResultList.get(0);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoResult.getDsptAmount()));
                return ipoResult;
            }
            return ipoResult;
        }
        catch (ObjectNotFoundException onfe) {
            logger.info("IPO Result for id " + applyId + " not found in the database!");
            throw new RecordNotFoundException("IPO Result for id " + applyId +" not found in the database!", onfe);
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

    public void insertIPOResult(IPOResult newIpoResult) throws DAOException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            getHibernateTemplate().save(newIpoResult);
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

    public Collection getAllIPOResult(String ipoCode) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            List IPOResultList = getHibernateTemplate().find("from IPOResult as ipoResult where ipoResult.ipoCode='"+ipoCode+"' order by ipoResult.prgStatus");
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoResult.getDsptAmount()));
            }
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

    public Collection getPageIPOResult(String ipoCode, int firstResult,int fetchSize) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();  
            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.ipoCode=? order by ipoResult.applyId");
            query.setString(0, ipoCode);
            query.setFirstResult(firstResult);
            query.setMaxResults(fetchSize);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoResult.getDsptAmount()));
            }
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
        finally{
        	if(session!=null){
        		session.close();
        	}        	
        }
    }

    public Collection getIPOResultStatusAndCount(String ipoCode) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
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
        finally{
        	if(session!=null){
        		session.close();
        	}        	
        }
    }

    public Collection getIPOResultByAccount(String accountId) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            List IPOResultList = getHibernateTemplate().find("from IPOResult as ipoResult where ipoResult.accountId like '"+accountId+"' order by ipoResult.applyId desc");
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoResult.getDsptAmount()));
            }
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

    public Collection getPageIPOResultByAccount(String accountId,int firstResult, int fetchSize) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession(); 

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.accountId like ? order by ipoResult.applyId desc");
            query.setString(0, accountId);
            query.setFirstResult(firstResult);
            query.setMaxResults(fetchSize);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoResult.getDsptAmount()));

            }
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
        finally{
        	if(session!=null){
        		session.close();
        	}        	
        }
    }

    public Collection getIPOResultCol(String ipoCode, String accountId) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession(); 
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
        finally{
        	if(session!=null){
        		session.close();
        	}        	
        }
    }

    public Collection getPageIPOResultCol(String ipoCode, String accountId,int firstResult, int fetchSize) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        List ipoResultList = null;
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession(); 
            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.ipoCode=? and ipoResult.accountId like ? order by ipoResult.applyId desc");
            query.setString(0, ipoCode);
            query.setString(1, accountId);
            query.setFirstResult(firstResult);
            query.setMaxResults(fetchSize);
            ipoResultList = query.list();
            IPOResult ipoResult;
            for (int i = 0; i < ipoResultList.size(); i++) {
                ipoResult = (IPOResult) ipoResultList.get(i);
                ipoResult.setApplyQuantity_dsply(DataFormatUtil.formatQty(ipoResult.getApplyQuantity()));
                ipoResult.setDsptAmount_dsply(DataFormatUtil.formatAmt(ipoResult.getDsptAmount()));
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
        return ipoResultList;
    }

    public void removeIPOResult(long applyId) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            IPOResult ipoResult = (IPOResult) session.load(IPOResult.class, new Long(applyId));
            session.delete(ipoResult);
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

    public void updateIPOResult(long applyId, IPOResult ipoResult) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
        	getHibernateTemplate().update(ipoResult);
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

    public void updateAllIPOResult(String ipoCode, String status) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            Query query = session.createQuery("from IPOResult as ipoResult where ipoResult.ipoCode=? and (ipoResult.prgStatus='3' or ipoResult.prgStatus='0') order by ipoResult.prgStatus");
            query.setString(0, ipoCode);
            List IPOResultList = query.list();
            IPOResult ipoResult;
            logger.info("ipoCode="+ipoCode+" status="+status+" size="+IPOResultList.size());
            for (int i = 0; i < IPOResultList.size(); i++) {
                ipoResult = (IPOResult) IPOResultList.get(i);
                ipoResult.setPrgStatus(status);
                System.out.println(ipoResult.getApplyId());
                session.saveOrUpdate(ipoResult);
            }
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

    public void importFile(String filePath) throws DAOException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession(); 
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
                String[] vec;
                int ch;

                while ( (ch = br.read()) != -1) {
                    IPOResult testResult = new IPOResult();
                    IPOResult ipoResult = new IPOResult();
                    strData = (char) ch + br.readLine();
                    vec = StringUtils.split(strData, "|");
                    ipoCode = ( (String) vec[0]).trim();
                    accountId = ( (String) vec[1]).trim();
                    applyQuantity = Integer.parseInt( ( (String) vec[2]).
                        trim());

                    dsptAmount = new BigDecimal( ( (String) vec[3]).trim());

                    applyId = new Long( ( (String) vec[5]).trim());
                    prgStatus = ( (String) vec[6]).trim();
                    misRefCode = ( (String) vec[7]).trim();

                    try {
                        rejectCode = ( (String) vec[8]).trim();
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
            //System.out.print(ipoResult.getAccountId());
            // testAction.importFile(
            //     "D:\\cvsroot\\IPOOrderRequestResult_IP04000083.dat");
            ///IPOResult ipoRequest = new IPOResult();
            ///testAction.removeIPORequest(6);

        }
        catch (Exception e) {
            System.out.print(e);
        }
    }

}
