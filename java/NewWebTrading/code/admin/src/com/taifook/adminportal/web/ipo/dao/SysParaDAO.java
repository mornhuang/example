package com.taifook.adminportal.web.ipo.dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.taifook.adminportal.web.ipo.dto.SysPara;
import com.taifook.adminportal.exceptions.DAOException;

public interface SysParaDAO {

    public SysPara querySysPara(int sysParaCode) throws DAOException;

    public List queryAllSysPara() throws DAOException;

    public void insertSysPara(SysPara sysPara) throws DAOException;

    public void updateSysPara(SysPara sysPara) throws DAOException;

    public void removeSysPara(int sysParaCode) throws DAOException;

}

