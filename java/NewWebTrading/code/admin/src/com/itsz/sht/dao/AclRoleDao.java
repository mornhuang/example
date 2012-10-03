package com.itsz.sht.dao;


import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.taifook.adminportal.common.util.page.Page;

public interface AclRoleDao {

	public void addAclRole(AclRole aclRole) throws DataAccessException;
	
	public void updateAclRole(AclRole aclRole) throws DataAccessException;
	
	public void deleteAclRole(String aclRoleId) throws DataAccessException;
	
	public AclRole getAclRole(String aclRoleId) throws DataAccessException;
	
	public  List<AclRole> listAclRole()throws DataAccessException;
	
	public Page listAclRolePage(StringMap params,Integer pageNumber,Integer pageSize)  throws DataAccessException;
	
}
