package com.taifook.adminportal.web.ipo.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.web.ipo.dao.SysParaDAO;
import com.taifook.adminportal.web.ipo.dto.SysPara;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.common.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import com.taifook.adminportal.common.util.OracleHibernateUtil;
import com.itsz.common.util.OracleHibernateUtil;

public class SysParaDAOHbmImpl
    implements SysParaDAO {
  private static Log logger = LogFactory.getLog(Constants.LOG_DEBUG_DB);

  public void insertSysPara(SysPara sysPara) throws DAOException {
    try {
      Session session = OracleHibernateUtil.currentSession();

      Transaction tx = session.beginTransaction();
      session.save(sysPara);
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

  public void updateSysPara(SysPara sysPara) throws
      DAOException {
    try {
      Session session = OracleHibernateUtil.currentSession();

      Transaction tx = session.beginTransaction();

      session.update(sysPara);
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

  public void removeSysPara(int sysParaCode) throws DAOException {

    try {
      Session session = OracleHibernateUtil.currentSession();

      Transaction tx = session.beginTransaction();

      SysPara sysPara = (SysPara) session.load(SysPara.class,
                                               new Integer(sysParaCode));

      session.delete(sysPara);
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

  public SysPara querySysPara(int sysParaCode) throws DAOException {

    try {
      Session session = OracleHibernateUtil.currentSession();

      SysPara sysPara = (SysPara) session.load(SysPara.class,
                                               new Integer(sysParaCode));
      return sysPara;
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

  public List queryAllSysPara() throws DAOException {

    try {

      Session session = OracleHibernateUtil.currentSession();

      Query query = session.createQuery(
          "from SysPara as sysPara order by sysPara.sysParaCode desc");

      List sysParaList = query.list();
      OracleHibernateUtil.closeSession();
      return sysParaList;

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
    SysParaDAOHbmImpl testAction = new SysParaDAOHbmImpl();

    try {
      SysPara sysPara = new SysPara();
/*
      sysPara.setSysParaValue("close");
      sysPara.setSysParaDesc("test2 close");
      sysPara.setSysParaName("status2");
      testAction.insertSysPara(sysPara);

      sysPara=testAction.querySysPara(17);
      System.out.println("value="+sysPara.getSysParaValue());
      System.out.println("desc="+sysPara.getSysParaDesc());
 */
List list = testAction.queryAllSysPara();
System.out.println("col size="+list.size());
sysPara = (SysPara)list.get(1);
System.out.println("value="+sysPara.getSysParaValue());
   }
    catch (Exception e) {
    }

  }

}
