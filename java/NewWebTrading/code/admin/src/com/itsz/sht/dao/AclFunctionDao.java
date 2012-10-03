package com.itsz.sht.dao;



import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.AclFunction;

public interface AclFunctionDao {

	public void addAclFunction(AclFunction aclFuction) throws DataAccessException;
	
	public void updateAclFunction(AclFunction aclFuction) throws DataAccessException;
	
	public void deleteAclFunction(String aclFuctionId) throws DataAccessException;
	
	public AclFunction getAclFunction(String aclFuctionId) throws DataAccessException;
	
	public List<AclFunction> getAllAclFunction() throws DataAccessException;
}
