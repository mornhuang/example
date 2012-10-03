package com.itsz.sht.dao.impl;

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.itsz.sht.dao.IPODAO;
import com.itsz.sht.dao.IPODAOFactory;
import com.itsz.sht.dao.IPOQtyAmtDAO;
import com.itsz.sht.dto.ipo.IPOQtyAmt;
import com.itsz.sht.dto.ipo.IPOQtyAmtKey;
import com.itsz.sht.dto.ipo.IPORecord;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

public class IPOQtyAmtDAOHbmImpl extends HibernateDaoSupport implements IPOQtyAmtDAO {

    public void insertIPOQtyAmtRcrd(IPOQtyAmt newIpoQtyAmt) throws DAOException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
        	getHibernateTemplate().save(newIpoQtyAmt);
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

    public Collection getIPOQtyAmtRcrd(long ipoMasterId) throws DAOException, RecordNotFoundException{
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            List IPOQtyAmtList = getHibernateTemplate().find("from IPOQtyAmt as ipoQtyAmt where ipoQtyAmt.id.ipoMasterId="+String.valueOf(ipoMasterId)+" order by ipoQtyAmt.id.shareQty");
            for(int i=0;i<IPOQtyAmtList.size();i++)
            {
                IPOQtyAmt ipoQytAmt = (IPOQtyAmt)IPOQtyAmtList.get(i);
                ipoQytAmt.getId().setShareQty_dsply(DataFormatUtil.formatQty(ipoQytAmt.getId().getShareQty()));
                ipoQytAmt.setAmount_dsply(DataFormatUtil.formatAmt(ipoQytAmt.getAmount()));
            }
            return IPOQtyAmtList;
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

    public void removeIPOQtyAmtRcrd(long ipoMasterId) throws DAOException,RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
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
        }finally{
        	if(session!=null){
        		session.close();
        	}        	
        }
    }

    public BigDecimal getAmtbyMasterIdQty(long ipoMasterId, int ipoQty) throws DAOException, RecordNotFoundException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        Session session = null;
        try {
            session = getHibernateTemplate().getSessionFactory().openSession();
            Query query = session.createQuery("from IPOQtyAmt as ipoQtyAmt where ipoQtyAmt.id.ipoMasterId=? and ipoQtyAmt.id.shareQty = ?");
            query.setLong(0, ipoMasterId);
            query.setInteger(1, ipoQty);
            java.util.List list = query.list();
            BigDecimal amount = new BigDecimal(0);
            if (list.size() > 0) {
                IPOQtyAmt ipoQtyAmt = new IPOQtyAmt();
                ipoQtyAmt = (IPOQtyAmt) list.get(0);
                amount = ipoQtyAmt.getAmount();
            }
            return amount;
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
            Long ipoMasterId = new Long(0);
            String pIpoCode = "", ipoCode = "initIpo", strData = "";
            int shareQty;
            String id;
            BigDecimal amount;
            IPORecord ipoRcrd = new IPORecord();

            objFile = new File(filePath);
            if (objFile.exists()) {
                objFileReader = new FileReader(objFile);
                BufferedReader br = new BufferedReader(objFileReader);
                String[] vec;
                int ch;
                IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
                while ( (ch = br.read()) != -1) {
                    IPOQtyAmt ipoQtyAmt = new IPOQtyAmt();
                    IPOQtyAmtKey ipoQtyAmtKey = new IPOQtyAmtKey();
                    strData = (char) ch + br.readLine();
                    vec = StringUtils.split(strData, "|");

                    ipoCode = ( (String) vec[0]).trim();

                    if (!ipoCode.equals(pIpoCode)) {
                        try {
                            ipoRcrd = ipoDAO.getIPOInfo(ipoCode);
                            ipoMasterId = ipoRcrd.getIpoMasterId();
                        }
                        catch (Exception e) {
                            pIpoCode = ipoCode;
                            //System.out.println("find not exit ipoCode="+ipoCode);
                            logger.error("Sorry,Can't find you give the ipoCode :" +
                                         ipoCode);

                            continue;
                        }
                    }

                    pIpoCode = ipoCode;
                    shareQty = Integer.parseInt( (String) vec[1]);

                    ipoQtyAmtKey.setIpoMasterId(ipoMasterId);
                    ipoQtyAmtKey.setShareQty(shareQty);
                    amount = this.getAmtbyMasterIdQty(ipoMasterId.longValue(),
                        shareQty);
                    if (amount.floatValue() > 0) {

                        ipoQtyAmt = (IPOQtyAmt) session.load(IPOQtyAmt.class,
                            ipoQtyAmtKey);

                        amount = new BigDecimal( ((String) vec[2]).trim());
                        ipoQtyAmt.setAmount(amount);
                        //System.out.println("now update ipoQtyAmt");
                        session.saveOrUpdate(ipoQtyAmt);
                    }
                    else {
                        amount = new BigDecimal( ((String) vec[2]).trim());
                        ipoQtyAmt.setId(ipoQtyAmtKey);
                        ipoQtyAmt.setAmount(amount);

                        session.save(ipoQtyAmt);
                    }

                }
            }
            else {
                logger.error("Sorry,Can't find you give file :" + filePath);
                throw new DAOException("Sorry,Can't find you give file :" +
                                       filePath);
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

    public static void main(String[] args) {
        IPOQtyAmtDAOHbmImpl testAction = new IPOQtyAmtDAOHbmImpl();
        try {
            //Collection col = testAction.getAllIPORecord();
            //IPOQtyAmt ipoQtyAmt = new IPOQtyAmt();
            /*
                 IPOQtyAmtKey key = new IPOQtyAmtKey(new Long(6), 8000);
                 ipoQtyAmt.setId(key);
                 ipoQtyAmt.setAmount(new BigDecimal(45));
                 testAction.insertIPOQtyAmtRcrd(ipoQtyAmt);
             */
            //Collection col=testAction.getIPOQtyAmtRcrd(6);
            //System.out.print(col.size());
           // BigDecimal amount = new BigDecimal(0);
           // amount = testAction.getAmtbyMasterIdQty(72, 5000);
          //  System.out.print("amount=" + amount);

            testAction.importFile("D:\\cvsroot\\IPOMasterQtyCriteria.dat");
        }
        catch (Exception e) {
           // System.out.print(e);
        }
    }

}
