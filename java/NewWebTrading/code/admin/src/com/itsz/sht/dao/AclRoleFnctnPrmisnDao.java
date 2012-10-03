package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.AclRoleFnctnPrmisn;

public interface AclRoleFnctnPrmisnDao {

	public void addAclRoleFuctnPrmisn(AclRoleFnctnPrmisn aclRoleFnctnPrmisn) throws DataAccessException;
	public void deleteAclRoleFuctnPrmisn(String aclRoleFnctnPrmisnId) throws DataAccessException;
	public void updateAclRoleFuctnPrmisn(AclRoleFnctnPrmisn aclRoleFnctnPrmisn) throws DataAccessException;
	public AclRoleFnctnPrmisn getAclRoleFuctnPrmisn(String aclRoleFnctnPrmisnId) throws DataAccessException;
	
	public List<AclRoleFnctnPrmisn> getAclRoleFuctnPrmisnByRoleId(String roleId) throws DataAccessException;

	public void deleteAclRoleFuctnByRoleId(String roleId)throws DataAccessException;
}
