package com.itsz.sht.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.AclFunctionDao;
import com.itsz.sht.dao.hibernate.model.AclFunction;

public class AclFunctionDaoImpl extends BaseDao<AclFunction> implements AclFunctionDao {

	public AclFunctionDaoImpl() {
		super(AclFunction.class);
	
	}

	@Override
	public void addAclFunction(AclFunction aclFuction)
			throws DataAccessException {

		this.save(aclFuction);
	}

	@Override
	public void deleteAclFunction(String aclFuctionId)
			throws DataAccessException {

		this.remove(aclFuctionId);
	}

	@Override
	public AclFunction getAclFunction(String aclFuctionId)
			throws DataAccessException {

		return this.get(aclFuctionId);
	}

	@Override
	public void updateAclFunction(AclFunction aclFuction)
			throws DataAccessException {

		this.update(aclFuction);
	}

	@Override
	public List<AclFunction> getAllAclFunction() throws DataAccessException {

		return this.list();
	}

}
