package com.itsz.sht.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.AclRoleFnctnPrmisnDao;
import com.itsz.sht.dao.hibernate.model.AclRoleFnctnPrmisn;

public class AclRoleFnctnPrmisnDaoImpl extends BaseDao<AclRoleFnctnPrmisn> implements
		AclRoleFnctnPrmisnDao {

	public AclRoleFnctnPrmisnDaoImpl() {
		super(AclRoleFnctnPrmisn.class);
	
	}

	@Override
	public void addAclRoleFuctnPrmisn(AclRoleFnctnPrmisn aclRoleFnctnPrmisn)
			throws DataAccessException {
	
		this.save(aclRoleFnctnPrmisn);

	}

	@Override
	public void deleteAclRoleFuctnPrmisn(String aclRoleFnctnPrmisnId)
			throws DataAccessException {
		this.remove(aclRoleFnctnPrmisnId);
	}

	@Override
	public AclRoleFnctnPrmisn getAclRoleFuctnPrmisn(String aclRoleFnctnPrmisnId)
			throws DataAccessException {

		return this.get(aclRoleFnctnPrmisnId);
	}

	@Override
	public void updateAclRoleFuctnPrmisn(AclRoleFnctnPrmisn aclRoleFnctnPrmisn)
			throws DataAccessException {
		this.update(aclRoleFnctnPrmisn);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AclRoleFnctnPrmisn> getAclRoleFuctnPrmisnByRoleId(String roleId)
			throws DataAccessException {
		String hql=" from AclRoleFnctnPrmisn arp where arp.roleId='"+roleId+"'";
		Query query=getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteAclRoleFuctnByRoleId(String roleId)
			throws DataAccessException {
		String hql=" delete from AclRoleFnctnPrmisn arp where arp.roleId='"+roleId+"'";
	   this.deleteByHql(hql);
		
	}

}
