package com.itsz.sht.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.dao.AclRoleDao;
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.itsz.sht.dao.hibernate.model.AclRoleFnctnPrmisn;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;

public class AclRoleDaoImpl extends BaseDao<AclRole> implements AclRoleDao {

	public AclRoleDaoImpl() {
		super(AclRole.class);
		
	}

	@Override
	public void addAclRole(AclRole aclRole) throws DataAccessException {

		this.save(aclRole);
	}

	@Override
	public void deleteAclRole(String aclRoleId) throws DataAccessException {
		this.remove(aclRoleId);
	}

	@Override
	public AclRole getAclRole(String aclRoleId) throws DataAccessException {
		AclRole aclRole=this.get(aclRoleId);
		List<AclRoleFnctnPrmisn> listRoleFunc=this.getAclRoleFuctnPrmisnByRoleId(aclRoleId);
	    int size=listRoleFunc.size();
		if(listRoleFunc!=null&&size>0){
			aclRole.setRoleFunctionPer(listRoleFunc);
			String[] selFcuntId=new String[size];
			for (int i = 0; i < size; i++) {
				AclRoleFnctnPrmisn arp=listRoleFunc.get(i);
				String functionId=arp.getFnctnId();
				selFcuntId[i]=functionId;
				List<AclRoleFnctnPrmisn> temList=aclRole.getFunctionPrmisnMap().get(functionId);
				if(temList==null){
					temList=new ArrayList<AclRoleFnctnPrmisn>();
					aclRole.getFunctionPrmisnMap().put(functionId, temList);
				}
				temList.add(arp);
			}
			aclRole.setSelectFunctionsId(selFcuntId);
		}
		return aclRole;
	}

	@Override
	public void updateAclRole(AclRole aclRole) throws DataAccessException {

		this.update(aclRole);
	}

	@Override
	public Page listAclRolePage(StringMap params, Integer pageNumber,
			Integer pageSize) throws DataAccessException {
		final StringBuffer hql = new StringBuffer();
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		this.buildHQL(params, hql, hqlParams);
		HibernatePage hibernatePage = null;
		Query query = this.getSession().createQuery(hql.toString());
		for (String name : hqlParams.keySet()) {
			Object value = hqlParams.get(name);
			query.setParameter(name, value);
		}
		
		Query query1=this.getSession().createQuery(
				"select count(*)" + hql);
		for (String name : hqlParams.keySet()) {
			Object value = hqlParams.get(name);
			query1.setParameter(name, value);
		}
		int count = ((Integer)query1.uniqueResult()).intValue();
		log.info("hql---"+hql+" count--"+count);
		hibernatePage = new HibernatePage(query, pageNumber, pageSize, count);
		return hibernatePage;
	}

	
	
	private void buildHQL(StringMap params, StringBuffer hql,
			HashMap<String, Object> hqlParams) {
		hql.append(" from AclRole r where 1=1");
	
		String roleId = params.getAsStringEmptyNull("roleId");
		if (roleId != null&&!"".equals(roleId)) {
			hql.append(" and r.roleId=:roleId"  );
			hqlParams.put("roleId", roleId);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<AclRoleFnctnPrmisn> getAclRoleFuctnPrmisnByRoleId(String roleId)
	throws DataAccessException {
		String hql=" from AclRoleFnctnPrmisn arp where arp.roleId='"+roleId+"'";
		return this.listByHql(hql);
  }

	@Override
	public List<AclRole> listAclRole() throws DataAccessException {
		return this.list();
	}
}
