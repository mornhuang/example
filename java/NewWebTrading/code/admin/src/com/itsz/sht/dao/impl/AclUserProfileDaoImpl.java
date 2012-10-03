package com.itsz.sht.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.dao.AclRoleDao;
import com.itsz.sht.dao.AclUserProfileDao;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;

public class AclUserProfileDaoImpl extends BaseDao<AclUserProfile> implements
		AclUserProfileDao {

	public AclUserProfileDaoImpl() {
		super(AclUserProfile.class);

	}

	@Override
	public void addAclUserProfile(AclUserProfile aclUserProfile)
			throws DataAccessException {
		this.save(aclUserProfile);

	}

	@Override
	public void deleteAclUserProfile(String lognId) throws DataAccessException {
		this.remove(lognId);

	}

	@Override
	public AclUserProfile loginAclUserProfile(String lognId)
			throws DataAccessException {
		AclUserProfile user = this.get(lognId);
		if (user != null) {
			AclRoleDao roleDao = new AclRoleDaoImpl();
			user.setAclRole(roleDao.getAclRole(user.getRoleId()));
		}
		return user;

	}

	@Override
	public void updateAclUserProfile(AclUserProfile aclUserProfile)
			throws DataAccessException {
		this.update(aclUserProfile);

	}

	@Override
	public Page listAclUserProfile(StringMap params, Integer pageNumber,
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

		Query query1 = this.getSession().createQuery("select count(*)" + hql);
		for (String name : hqlParams.keySet()) {
			Object value = hqlParams.get(name);
			query1.setParameter(name, value);
		}
		int count = ((Integer) query1.uniqueResult()).intValue();
		log.info("hql---" + hql + " count--" + count);
		hibernatePage = new HibernatePage(query, pageNumber, pageSize, count);
		return hibernatePage;
	}

	private void buildHQL(StringMap params, StringBuffer hql,
			HashMap<String, Object> hqlParams) {
		hql.append(" from AclUserProfile au where 1=1");
		String loginId = params.getAsStringEmptyNull("loginId");
		if (loginId != null && !"".equals(loginId)) {
			hql.append(" and au.lognId=:loginId");
			hqlParams.put("loginId", loginId);
		}

		String roleId = params.getAsStringEmptyNull("roleId");
		if (roleId != null && !"".equals(roleId)) {
			hql.append(" and au.roleId=:roleId");
			hqlParams.put("roleId", roleId);
		}

		String userName = params.getAsStringEmptyNull("userName");
		if (userName != null && !"".equals(userName)) {
			hql.append(" and au.usrNme=:userName");
			hqlParams.put("userName", userName);
		}
	}

	@Override
	public AclUserProfile isLogin(String loginId, String pwd)
			throws DataAccessException {
		Query query = (Query) getSession()
				.createQuery(
						" from AclUserProfile au where au.lognId=:loginId and au.pwd=:pwd")
				.uniqueResult();
		query.setString("loginId", loginId);
		query.setString("pwd", pwd);
		return (AclUserProfile) query.list();
	}

	@Override
	public AclUserProfile getAclUserProfile(String lognId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return this.get(lognId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer findUserCountByRoleId(String roleId)
			throws DataAccessException {
		String hql = "select count(*) as c from AclUserProfile where roleId='" + roleId + "'";
		List list = this.listByHql(hql);
		if(list!=null && list.size()>0){
			if(list.get(0) instanceof Integer){
				return (Integer) list.get(0);
			}
		}
		return 0 ;
	}

}
