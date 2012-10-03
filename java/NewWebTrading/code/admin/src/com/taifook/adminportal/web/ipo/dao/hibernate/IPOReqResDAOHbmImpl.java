package com.taifook.adminportal.web.ipo.dao.hibernate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.itsz.common.util.OracleHibernateUtil;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.web.ipo.dao.IPOReqResDAO;
import com.taifook.adminportal.web.ipo.dto.IPOReqRes;
import com.taifook.adminportal.web.ipo.dto.IPOReqResOrder;
import com.taifook.adminportal.web.ipo.dto.IPORequest;


public class IPOReqResDAOHbmImpl
    implements IPOReqResDAO {
    private  static Log logger = LogFactory.getLog(Constants.LOG_DEBUG_DB);
    public ArrayList getIPOReqRes(long ipoMasterId) throws DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();
            ArrayList listAll = new ArrayList();
            IPORequest ipoRequest;
            String accountId, telephone, email, status, remark;

            Query query = session.createQuery("from IPORequest as ipoApply where ipoApply.ipoMasterId = ?  and  ipoApply.status ='received'");
            query.setLong(0, ipoMasterId);

            List list = query.list();

            for (int i = 0; i < list.size(); i++) {
                IPOReqRes ipoReqRes = new IPOReqRes();
                ipoRequest = (IPORequest) list.get(i);

                accountId = "";
                try {
                    accountId = ipoRequest.getAccountId().toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
                telephone = "";
                try {
                    telephone = ipoRequest.getTelephone().toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
                email = "";
                try {
                    email = ipoRequest.getEmail().toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
                status = "";
                try {
                    status = ipoRequest.getStatus().toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
                remark = "";
                try {
                    remark = ipoRequest.getRemark().toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }

                ipoReqRes.setApplyId(ipoRequest.getApplyId().longValue());
                ipoReqRes.setAccountId(accountId);
                ipoReqRes.setTelephone(telephone);
                ipoReqRes.setEmail(email);
                ipoReqRes.setStatus(status);
                ipoReqRes.setRemark(remark);

                listAll.add(ipoReqRes);
            }

            Query rsltQuery = session.createQuery("select ipoApplyRslt.applyId , ipoApplyRslt.accountId , ipoApply.telephone, ipoApply.email, ipoApplyRslt.prgStatus, ipoApply.remark from IPORequest as ipoApply, IPOResult as ipoApplyRslt where ipoApplyRslt.applyId=ipoApply.applyId and ipoApply.ipoMasterId = ?");
            rsltQuery.setLong(0, ipoMasterId);

            List rsltList = rsltQuery.list();
            Object[] record;

            for (int i = 0; i < rsltList.size(); i++) {
                IPOReqRes ipoReqRes = new IPOReqRes();
                record = (Object[]) rsltList.get(i);

                accountId = "";
                try {
                    accountId = record[1].toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
                telephone = "";
                try {
                    telephone = record[2].toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
                email = "";
                try {
                    email = record[3].toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }
                status = "";
                try {
                    status = record[4].toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }

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

                remark = "";
                try {
                    remark = record[5].toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }

                ipoReqRes.setApplyId(Long.parseLong(record[0].toString()));
                ipoReqRes.setAccountId(accountId);
                ipoReqRes.setTelephone(telephone);
                ipoReqRes.setEmail(email);
                ipoReqRes.setStatus(status);
                ipoReqRes.setRemark(remark);
                listAll.add(ipoReqRes);
            }

            OracleHibernateUtil.closeSession();
            return listAll;

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

    public void exportFileByIPO(long ipoMasterId, String filePath,
                                String ipoName) throws
        DAOException{
        try {
            Session bgnSession = OracleHibernateUtil.currentSession();

            List ipoReqResList = getIPOReqRes(ipoMasterId);
            int listLen = ipoReqResList.size();

            File objFile;
            FileWriter objFileWriter;

            objFile = new File(filePath);
            objFileWriter = new FileWriter(objFile);
            BufferedWriter br = new BufferedWriter(objFileWriter);
            PrintWriter pw = new PrintWriter(br);

            Vector vec;
            String spltStr = ",";
            String lineStr = "";

            long applyId;
            String accountId;
            String telephone;
            String eMail;
            String status;
            String remark;

            for (int i = 0; i < listLen; i++) {
                IPOReqRes ipoReqRes = new IPOReqRes();
                ipoReqRes = (IPOReqRes) ipoReqResList.get(i);

                applyId = ipoReqRes.getApplyId();
                accountId = ipoReqRes.getAccountId();

                remark = ipoReqRes.getRemark();

                telephone = ipoReqRes.getTelephone();
                eMail = ipoReqRes.getEmail();
                status = ipoReqRes.getStatus();

                lineStr = ipoName + spltStr + applyId + spltStr + accountId +
                    spltStr +
                    telephone +
                    spltStr + eMail + spltStr + status;
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

    public ArrayList getIPORequestOrder(String aeCode, long ipoMasterId,
                                        ArrayList acList) throws
        DAOException{
        try {
            Session session = OracleHibernateUtil.currentSession();
            /*
                         ArrayList acList = new ArrayList();
                         IPOReqResDelegate delegate = new IPOReqResDelegate();
                         try {
                acList = (ArrayList) delegate.queryAcByAe(aeCode);
                //return acList;
                         }
                         catch(Exception e)
                         {
                logger.debug(e);
                throw new Exception();
                         }*/
//            System.out.println("aeCode="+aeCode+",ipoMasterID="+ipoMasterId+",acList size=");
//            System.out.println(acList.size());
            ArrayList listAll = new ArrayList();
            IPORequest ipoRequest;
            long applyId = 0;
            String accountId, telephone, email, status, resultStatus,
                misRefCode, rejectCode, receiveTime;
            BigDecimal needAmount, dsptAmount = new BigDecimal(0);
            String dspAmount_dsply="";
            int needQuantity = 0, gainQuantity = 0;
            long _st = System.currentTimeMillis();
            Query query = session.createQuery(
             "select ipoApply.applyId,ipoApply.stockCode,ipoApply.applyQuantity,ipoApply.dsptAmount,ipoApply.accountId,ipoApply.telephone,ipoApply.email,ipoApply.status,ipoApply.ipoResult.prgStatus,ipoApply.ipoResult.rejectCode,ipoApply.applyDate  from IPORequest ipoApply left join fetch ipoApply.ipoResult where ipoApply.ipoMasterId = ?");
            //Query query = session.createQuery(
              //  "select ipoApply.applyId,ipoApply.stockCode,ipoApply.applyQuantity,ipoApply.dsptAmount,ipoApply.accountId,ipoApply.telephone,ipoApply.email,ipoApply.status,ipoApply.ipoResult.prgStatus,ipoApply.ipoResult.rejectCode,ipoApply.applyDate from IPORequest ipoApply  inner join  ipoApply.ipoResult o where ipoApply.ipoMasterId = ?");
            query.setLong(0, ipoMasterId);

            List list = query.list();
            long _re = System.currentTimeMillis() - _st;
            logger.debug("query database: " + _re);
            Object[] record;
            _st = System.currentTimeMillis();
            for (int i = 0; i < list.size(); i++) {
                IPOReqResOrder ipoReqResOrder = new IPOReqResOrder();

                record = (Object[]) list.get(i);

                accountId = "";
                try {
                    accountId = record[4].toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }

                if (acList.contains(accountId)) { //if account under AE
                    try {
                        needQuantity = Integer.parseInt(record[2].toString());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                    try {
                        dsptAmount = new BigDecimal(record[3].
                            toString());
                        dspAmount_dsply = DataFormatUtil.formatAmt(dsptAmount);
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    try {
                        applyId = Long.parseLong(record[0].toString());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    telephone = "";
                    try {
                        telephone = record[5].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    email = "";
                    try {
                        email = record[6].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    status = "";
                    try {
                        status = record[7].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    resultStatus = "";
                    try {
                        resultStatus = record[8].toString();
                        if ("0".equals(resultStatus)) {
                            status = Constants.IPO_APP_PRG;
                        }
                        else {
                            if ("3".equals(resultStatus)) {
                                status = Constants.IPO_ALL_AVL;
                            }
                            else {
                                if ("2".equals(resultStatus)) {
                                    status = Constants.IPO_MIS_CLD;
                                }
                                else {
                                    status = Constants.IPO_MIS_RJD;
                                }
                            }
                        }

                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                    rejectCode = "";
                    try {
                        rejectCode = record[9].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    receiveTime = "";
                    try {
                        receiveTime = record[10].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                    ipoReqResOrder.setApplyId(applyId);
                    ipoReqResOrder.setAccountId(accountId);
                    ipoReqResOrder.setNeedQuantity(needQuantity);
                    ipoReqResOrder.setDsptAmount(dsptAmount);
                    ipoReqResOrder.setTelephone(telephone);
                    ipoReqResOrder.setEmail(email);
                    ipoReqResOrder.setApplyStatus(status);
                    ipoReqResOrder.setIpoMasterId(ipoMasterId);
                    ipoReqResOrder.setRejectCode(rejectCode);
                    ipoReqResOrder.setApplyTime(receiveTime);
                    ipoReqResOrder.setDsptAmount_dsply(dspAmount_dsply);
                    listAll.add(ipoReqResOrder);
                }
            }
            _re = System.currentTimeMillis() - _st;
            logger.debug("compare List: " + _re);
            OracleHibernateUtil.closeSession();
            return listAll;

        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }

    public ArrayList getIPORequestOrder(String aeCode, String acCode,
                                        ArrayList acList) throws
        DAOException {
        try {
            Session session = OracleHibernateUtil.currentSession();

            /*ArrayList acList = new ArrayList();
                         IPOReqResDelegate delegate = new IPOReqResDelegate();
                         try {
                acList = (ArrayList) delegate.queryAcByAe(aeCode);
                         }
                         catch (Exception e) {
                logger.debug(e);
                throw new Exception();
                         }*/

            ArrayList listAll = new ArrayList();
            IPORequest ipoRequest;
            long applyId = 0, ipoMasterId = 0;
            String accountId, telephone, email, status, resultStatus,
                misRefCode, rejectCode, receiveTime;
            BigDecimal needAmount, dsptAmount = new BigDecimal(0);
            String dspAmount_dsply="";
            int needQuantity = 0, gainQuantity = 0;
            Query query = session.createQuery(
                "select ipoApply.applyId,ipoApply.stockCode,ipoApply.applyQuantity,ipoApply.dsptAmount,ipoApply.accountId,ipoApply.telephone,ipoApply.email,ipoApply.status,ipoApply.ipoResult.prgStatus,ipoApply.ipoResult.rejectCode,ipoApply.applyDate,ipoApply.ipoMasterId  from IPORequest ipoApply left join fetch ipoApply.ipoResult where ipoApply.accountId like ?");
            query.setString(0, acCode);

            List list = query.list();
            Object[] record;
            for (int i = 0; i < list.size(); i++) {
                IPOReqResOrder ipoReqResOrder = new IPOReqResOrder();

                record = (Object[]) list.get(i);

                accountId = "";
                try {
                    accountId = record[4].toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }

                if (acList.contains(accountId)) { //if account under AE
                    try {
                        needQuantity = Integer.parseInt(record[2].toString());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    try {
                        dsptAmount =new BigDecimal(record[3].
                            toString());
                        dspAmount_dsply = DataFormatUtil.formatAmt(dsptAmount);
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    try {
                        applyId = Long.parseLong(record[0].toString());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    telephone = "";
                    try {
                        telephone = record[5].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    email = "";
                    try {
                        email = record[6].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    status = "";
                    try {
                        status = record[7].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    resultStatus = "";
                    try {
                        resultStatus = record[8].toString();
                        if ("0".equals(resultStatus)) {
                            status = Constants.IPO_APP_PRG;
                        }
                        else {
                            if ("3".equals(resultStatus)) {
                                status = Constants.IPO_ALL_AVL;
                            }
                            else {
                                if ("2".equals(resultStatus)) {
                                    status = Constants.IPO_MIS_CLD;
                                }
                                else {
                                    status = Constants.IPO_MIS_RJD;
                                }
                            }
                        }

                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                    rejectCode = "";
                    try {
                        rejectCode = record[9].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    receiveTime = "";
                    try {
                        receiveTime = record[10].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    ipoMasterId = 0;
                    try {
                        ipoMasterId = Long.parseLong(record[11].toString());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                    ipoReqResOrder.setApplyId(applyId);
                    ipoReqResOrder.setAccountId(accountId);
                    ipoReqResOrder.setNeedQuantity(needQuantity);
                    ipoReqResOrder.setDsptAmount(dsptAmount);
                    ipoReqResOrder.setTelephone(telephone);
                    ipoReqResOrder.setEmail(email);
                    ipoReqResOrder.setApplyStatus(status);
                    ipoReqResOrder.setIpoMasterId(ipoMasterId);
                    ipoReqResOrder.setRejectCode(rejectCode);
                    ipoReqResOrder.setApplyTime(receiveTime);
                    ipoReqResOrder.setDsptAmount_dsply(dspAmount_dsply);
                    listAll.add(ipoReqResOrder);
                }
            }

            OracleHibernateUtil.closeSession();
            return listAll;

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

    public ArrayList getIPORequestOrder(String aeCode, String acCode,
                                        long ipoMasterId, ArrayList acList) throws
        DAOException {

        try {
            Session session = OracleHibernateUtil.currentSession();
            /*ArrayList acList = new ArrayList();
                         IPOReqResDelegate delegate = new IPOReqResDelegate();
                         try {
                acList = (ArrayList) delegate.queryAcByAe(aeCode);
                         }
                         catch (Exception e) {
                logger.debug(e);
                throw new Exception();
                         }*/

            ArrayList listAll = new ArrayList();
            IPORequest ipoRequest;
            long applyId = 0;
            String accountId, telephone, email, status, resultStatus,
                misRefCode, rejectCode, receiveTime;
            BigDecimal needAmount, dsptAmount = new BigDecimal(0);
            String dspAmount_dsply="";
            int needQuantity = 0, gainQuantity = 0;
            Query query = session.createQuery(
                "select ipoApply.applyId,ipoApply.stockCode,ipoApply.applyQuantity,ipoApply.dsptAmount,ipoApply.accountId,ipoApply.telephone,ipoApply.email,ipoApply.status,ipoApply.ipoResult.prgStatus,ipoApply.ipoResult.rejectCode,ipoApply.applyDate,ipoApply.ipoMasterId  from IPORequest ipoApply left join fetch ipoApply.ipoResult where ipoApply.accountId like ? and ipoApply.ipoMasterId = ?");
            query.setString(0, acCode);
            query.setLong(1, ipoMasterId);

            List list = query.list();
            Object[] record;
            for (int i = 0; i < list.size(); i++) {
                IPOReqResOrder ipoReqResOrder = new IPOReqResOrder();

                record = (Object[]) list.get(i);

                accountId = "";
                try {
                    accountId = record[4].toString();
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                }

                if (acList.contains(accountId)) { //if account under AE
                    try {
                        needQuantity = Integer.parseInt(record[2].toString());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    try {
                        dsptAmount = new BigDecimal(record[3].
                            toString());
                        dspAmount_dsply = DataFormatUtil.formatAmt(dsptAmount);
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    try {
                        applyId = Long.parseLong(record[0].toString());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    telephone = "";
                    try {
                        telephone = record[5].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    email = "";
                    try {
                        email = record[6].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    status = "";
                    try {
                        status = record[7].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    resultStatus = "";
                    try {
                        resultStatus = record[8].toString();
                        if ("0".equals(resultStatus)) {
                            status = Constants.IPO_APP_PRG;
                        }
                        else {
                            if ("3".equals(resultStatus)) {
                                status = Constants.IPO_ALL_AVL;
                            }
                            else {
                                if ("2".equals(resultStatus)) {
                                    status = Constants.IPO_MIS_CLD;
                                }
                                else {
                                    status = Constants.IPO_MIS_RJD;
                                }
                            }
                        }

                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                    rejectCode = "";
                    try {
                        rejectCode = record[9].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    receiveTime = "";
                    try {
                        receiveTime = record[10].toString();
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    ipoMasterId = 0;
                    try {
                        ipoMasterId = Long.parseLong(record[11].toString());
                    }
                    catch (Exception e) {
                        logger.error(e.getMessage());
                    }

                    ipoReqResOrder.setApplyId(applyId);
                    ipoReqResOrder.setAccountId(accountId);
                    ipoReqResOrder.setNeedQuantity(needQuantity);
                    ipoReqResOrder.setDsptAmount(dsptAmount);
                    ipoReqResOrder.setTelephone(telephone);
                    ipoReqResOrder.setEmail(email);
                    ipoReqResOrder.setApplyStatus(status);
                    ipoReqResOrder.setIpoMasterId(ipoMasterId);
                    ipoReqResOrder.setRejectCode(rejectCode);
                    ipoReqResOrder.setApplyTime(receiveTime);
                    ipoReqResOrder.setDsptAmount_dsply(dspAmount_dsply);
                    listAll.add(ipoReqResOrder);
                }

            }

            OracleHibernateUtil.closeSession();

            return listAll;

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

    public static void main(String[] args) {
                /*
                 IPOReqResDAOHbmImpl testAction = new IPOReqResDAOHbmImpl();
             }
               try {
                ArrayList acList = new ArrayList();
                IPOReqResDelegate delegate = new IPOReqResDelegate();
                try {
                    acList = (ArrayList) delegate.queryAcByAe("001");
                }
                catch (Exception e) {
                }
                for(int i=0;i<acList.size();i++)
                {

                 }
                 catch (Exception e) {
     }*/
    //String    dsptAmount = DataFormatUtil.formatAmt((new BigDecimal("156")));
    java.util.Calendar applyDate  = java.util.Calendar.getInstance();
    java.util.Calendar afterDate  = java.util.Calendar.getInstance();

    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
    afterDate.add(java.util.Calendar.MONTH,-3);
    while(applyDate.after(afterDate))
    {
        applyDate.add(java.util.Calendar.DATE,-1);
    }
  }
}
