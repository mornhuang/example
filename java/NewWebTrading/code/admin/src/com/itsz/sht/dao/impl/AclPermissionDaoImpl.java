package com.itsz.sht.dao.impl;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.AclPermissionDao;
import com.itsz.sht.dao.hibernate.model.AclPermission;

public class AclPermissionDaoImpl extends BaseDao<AclPermission> implements
		AclPermissionDao {

	public AclPermissionDaoImpl() {
		super(AclPermission.class);

	}

	@Override
	public void addAclPermission(AclPermission aclPermission)
			throws DataAccessException {

		this.save(aclPermission);
	}

	@Override
	public void deletelPermission(String aclPermissionId)
			throws DataAccessException {

		this.remove(aclPermissionId);
	}

	@Override
	public AclPermission getAclPermission(String aclPermissionId)
			throws DataAccessException {
			

		return  this.get(aclPermissionId);
	}

	@Override
	public void updateAclPermission(AclPermission aclPermission)
			throws DataAccessException {
		// TODO Auto-generated method stub
		this.update(aclPermission);
	}

}
