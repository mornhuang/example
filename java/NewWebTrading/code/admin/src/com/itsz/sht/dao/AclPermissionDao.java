package com.itsz.sht.dao;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.AclPermission;

public interface AclPermissionDao {
	
	public void addAclPermission(AclPermission aclPermission) throws DataAccessException;
	public void updateAclPermission(AclPermission aclPermission) throws DataAccessException;
	public void deletelPermission(String aclPermissionId) throws DataAccessException;
	public AclPermission getAclPermission(String aclPermissionId) throws DataAccessException;

}
