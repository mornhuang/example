package com.taifook.adminportal.web.ipo.delegate;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taifook.adminportal.web.ipo.dao.SysParaDAO;
import com.taifook.adminportal.web.ipo.dao.SysParaDAOFactory;
import com.taifook.adminportal.web.ipo.dto.SysPara;
import com.taifook.adminportal.web.ipo.exception.SysParaException;
import com.taifook.adminportal.exceptions.DAOException;
import com.taifook.adminportal.web.ipo.RecordNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SysParaDelegate {

  public SysPara querySysPara(int sysParaCode) throws
      SysParaException {

    Log logger = LogFactory.getLog(this.getClass());

    try {
      SysParaDAO sysParaDAO = (SysParaDAO) SysParaDAOFactory.instance().
          getIPODAO();
      SysPara ipoRecord = sysParaDAO.querySysPara(sysParaCode);

      return ipoRecord;

    }
    catch (DAOException de) {
      logger.error(de);
      throw new SysParaException();
    }
  }

  public List queryAllSysPara() throws
      SysParaException {

    Log logger = LogFactory.getLog(this.getClass());

    try {
      List sysParaList;
      SysParaDAO sysParaDAO = (SysParaDAO) SysParaDAOFactory.instance().
          getIPODAO();
      sysParaList = sysParaDAO.queryAllSysPara();

      return sysParaList;
    }
    catch (DAOException de) {
      logger.error(de);
      throw new SysParaException();
    }
  }

  public void insertSysPara(SysPara sysPara) throws
      SysParaException {

    Log logger = LogFactory.getLog(this.getClass());

    try {
      SysParaDAO sysParaDAO = (SysParaDAO) SysParaDAOFactory.instance().
          getIPODAO();
      sysParaDAO.insertSysPara(sysPara);
    }
    catch (DAOException de) {
      logger.error(de);
      throw new SysParaException();
    }
  }

  public void updateSysPara(SysPara sysPara) throws
      SysParaException {

    Log logger = LogFactory.getLog(this.getClass());

    try {
      SysParaDAO sysParaDAO = (SysParaDAO) SysParaDAOFactory.instance().
          getIPODAO();
      sysParaDAO.updateSysPara(sysPara);
    }
    catch (DAOException de) {
      logger.error(de);
      throw new SysParaException();
    }
  }

  public void removeSysPara(int sysParaCode) throws SysParaException {

    Log logger = LogFactory.getLog(this.getClass());

    try {
      SysParaDAO sysParaDAO = (SysParaDAO) SysParaDAOFactory.instance().
          getIPODAO();
      sysParaDAO.removeSysPara(sysParaCode);
    }
    catch (DAOException de) {
      logger.error(de);
      throw new SysParaException();
    }
  }

}
