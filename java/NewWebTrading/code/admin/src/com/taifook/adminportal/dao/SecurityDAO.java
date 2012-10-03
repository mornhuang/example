package com.taifook.adminportal.dao;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import com.taifook.adminportal.common.CommonDAO;
import com.taifook.adminportal.dto.User;

public interface SecurityDAO extends CommonDAO{
	public User userLogin(String userid,String pwd)throws Exception;
	public boolean userLogout(HttpSession userSession);
	public boolean changePwd(String userId,String oldPwd,String newPwd) throws Exception;
	public Collection queryAcByAe(String aeCode) throws Exception;
}
