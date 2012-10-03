package com.taifook.adminportal.web.ipo.dao.hibernate;

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.web.ipo.dao.IPODAO;
import com.taifook.adminportal.web.ipo.dao.IPODAOFactory;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.web.ipo.dao.IPOQtyAmtDAO;
import com.taifook.adminportal.web.ipo.dto.IPOQtyAmt;
import com.taifook.adminportal.web.ipo.dto.IPOQtyAmtKey;
import com.taifook.adminportal.common.util.StringUtil;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.common.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import com.taifook.adminportal.common.util.OracleHibernateUtil;
import com.itsz.common.util.OracleHibernateUtil;
import com.taifook.adminportal.web.ipo.dto.IPOQtyAmtKey;

public class IPOQtyAmtDAOHbmImpl
    implements IPOQtyAmtDAO {
    private  static Log logger = LogFactory.getLog(Constants.LOG_DEBUG_DB);
    public void insertIPOQtyAmtRcrd(IPOQtyAmt newIpoQtyAmt) throws DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();

            session.save(newIpoQtyAmt);
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

    public Collection getIPOQtyAmtRcrd(long ipoMasterId) throws DAOException{
        try {

            Session session = OracleHibernateUtil.currentSession();
            Query query = session.createQuery(
                "from IPOQtyAmt as ipoQtyAmt where ipoQtyAmt.id.ipoMasterId= ? order by ipoQtyAmt.id.shareQty");
            query.setLong(0, ipoMasterId);
            List IPOQtyAmtList = query.list();
            for(int i=0;i<IPOQtyAmtList.size();i++)
            {
                IPOQtyAmt ipoQytAmt = (IPOQtyAmt)IPOQtyAmtList.get(i);
                ipoQytAmt.getId().setShareQty_dsply(DataFormatUtil.formatQty(ipoQytAmt.getId().getShareQty()));
                ipoQytAmt.setAmount_dsply(DataFormatUtil.formatAmt(ipoQytAmt.getAmount()));

            }
            // Close session
            OracleHibernateUtil.closeSession();

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

    public void removeIPOQtyAmtRcrd(long ipoMasterId) throws DAOException{

        try {
            Session session = OracleHibernateUtil.currentSession();

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

    public BigDecimal getAmtbyMasterIdQty(long ipoMasterId, int ipoQty) throws
        DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

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
            // Close session
            // OracleHibernateUtil.closeSession();

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

    }

    public void importFile(String filePath) throws DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();

            Transaction tx = session.beginTransaction();

            File objFile;
            FileReader objFileReader;
            Long ipoMasterId = new Long(0);
            String pIpoCode = "", ipoCode = "initIpo", strData = "";
            int shareQty;
            String id;
            BigDecimal amount;
            BigDecimal zeroBg=new BigDecimal(0);
            IPORecord ipoRcrd = new IPORecord();

            objFile = new File(filePath);
            if (objFile.exists()) {
                objFileReader = new FileReader(objFile);
                BufferedReader br = new BufferedReader(objFileReader);

                StringUtil stringUtil = new StringUtil();
                Vector vec;
                int ch;
                IPODAO ipoDAO = (IPODAO) IPODAOFactory.instance().getIPODAO();
                while ( (ch = br.read()) != -1) {
                    IPOQtyAmt ipoQtyAmt = new IPOQtyAmt();
                    IPOQtyAmtKey ipoQtyAmtKey = new IPOQtyAmtKey();
                    strData = (char) ch + br.readLine();
                    if(strData==null||strData==""||strData.length()<2)
                        continue;
                    vec = stringUtil.strSplit(strData, "|");

                    ipoCode = ( (String) vec.get(0)).trim();

                    if (!ipoCode.equals(pIpoCode)) {
                        try {
                            ipoRcrd = ipoDAO.getIPOInfo(ipoCode);
                            ipoMasterId = ipoRcrd.getIpoMasterId();
                        }
                        catch (Exception e) {
                            pIpoCode = ipoCode;
                            logger.error("Sorry,Can't find you give the ipoCode :" +
                                         ipoCode);

                            continue;
                        }
                    }

                    pIpoCode = ipoCode;
                    shareQty = Integer.parseInt( (String) vec.get(1));

                    if(shareQty>0){

                      ipoQtyAmtKey.setIpoMasterId(ipoMasterId);
                      ipoQtyAmtKey.setShareQty(shareQty);
                      amount = this.getAmtbyMasterIdQty(ipoMasterId.longValue(),
                                                        shareQty);
                      if (amount.floatValue() > 0) {

                        ipoQtyAmt = (IPOQtyAmt) session.load(IPOQtyAmt.class,
                            ipoQtyAmtKey);

                        amount = new BigDecimal( ( (String) vec.get(2)).trim());
                        ipoQtyAmt.setAmount(amount);
                        session.saveOrUpdate(ipoQtyAmt);
                      }
                      else {
                        amount = new BigDecimal( ( (String) vec.get(2)).trim());
                        if(amount.compareTo(zeroBg)==1){
                          ipoQtyAmt.setId(ipoQtyAmtKey);
                          ipoQtyAmt.setAmount(amount);

                          session.save(ipoQtyAmt);
                        }
                      }
                    }

                }
            }
            else {
                logger.error("Sorry,Can't find you give file :" + filePath);
                throw new DAOException("Sorry,Can't find you give file :" +
                                       filePath);
            }

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
            BigDecimal bg1 = new BigDecimal(2.0);
            BigDecimal bg2 = new BigDecimal(2.1);
            System.out.print("compare="+bg1.compareTo(bg2));
           // testAction.importFile("D:\\cvsroot\\IPOMasterQtyCriteria.dat");
        }
        catch (Exception e) {
        }
    }

}
