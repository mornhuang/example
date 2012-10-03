package com.itsz.sht.dao;

import org.springframework.dao.DataAccessException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.taifook.adminportal.common.util.page.Page;

public interface AclUserProfileDao {

	public void addAclUserProfile(AclUserProfile aclUserProfile) throws DataAccessException;
	public void updateAclUserProfile(AclUserProfile aclUserProfile) throws DataAccessException;
	public void deleteAclUserProfile(String lognId) throws DataAccessException;
	
	public AclUserProfile getAclUserProfile(String lognId) throws DataAccessException;
	
	public AclUserProfile loginAclUserProfile(String lognId) throws DataAccessException;
	
	public Page listAclUserProfile(StringMap params,Integer pageNumber,Integer pageSize)  throws DataAccessException;

	public AclUserProfile isLogin(String loginId,String pwd) throws DataAccessException;
	
    public Integer findUserCountByRoleId(String roleId)throws DataAccessException;
	
}
