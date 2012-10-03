package com.itsz.sht.dao.impl;


import java.util.Date;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.itsz.sht.common.DateUtil;
import com.itsz.sht.dao.UserProductPaymentDao;
import com.itsz.sht.dao.hibernate.model.UserProductPayment;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;

public class UserProductPaymentDaoImpl extends BaseDao<UserProductPayment> implements
		UserProductPaymentDao {

	public UserProductPaymentDaoImpl() {
		super(UserProductPayment.class);
	}

	@Override
	public void addUserProductPayment(UserProductPayment userProductPayment)
			throws DataAccessException {
		this.save(userProductPayment);
		
	}

	@Override
	public void deleteUserProductPayment(String userProductPaymentId)
			throws DataAccessException {
		this.remove(userProductPaymentId);
		
	}

	@Override
	public UserProductPayment getUserProductPayment(String userProductPaymentId)
			throws DataAccessException {
		return this.get(userProductPaymentId);
	}

	@Override
	public void updateUserProductPayment(UserProductPayment userProductPayment)
			throws DataAccessException {
	this.update(userProductPayment);
		
	}
	
	@Override
	public void deleteUserProductPaymentByClnId(String clientId) throws DataAccessException{
		String hql = " delete UserProductPayment upp where upp.clntId='"+clientId+"' and upp.paySatus='PENDING'";
		this.deleteByHql(hql);
	}

	public Page getUserProductPaymentListByManual(
			Date startTime, Date endTime, int pageNumber, int pageSize) throws DataAccessException {
		StringBuffer hql = new StringBuffer("from UserProductPayment upp where upp.resMessage='MANUAL'");
		if(startTime != null){
			hql.append(" and upp.initDate>=to_date('" + DateUtil.getStringforDate(startTime).substring(0, 10) + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
		if(endTime != null){
			hql.append(" and upp.initDate<=to_date('" + DateUtil.getStringforDate(endTime).substring(0, 10) + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		hql.append(" order by upp.initDate desc");
		HibernatePage hibernatePage = null;
		Query query = this.getSession().createQuery(hql.toString());
		int count = query.list().size();
//		log.info("hql---" + hql + " count--" + count);
		hibernatePage = new HibernatePage(query, pageNumber, pageSize, count);
		return hibernatePage;
	}

	@Override
	public void updateUserProductPaymentByManual(String[] usrProdPayIds)
			throws DataAccessException {
		if(usrProdPayIds.length > 0){
			String hql = "update UserProductPayment upp set upp.resMessage='MANUAL-SUCC' where upp.resMessage='MANUAL'";
			StringBuffer sb = new StringBuffer();
			for(String id : usrProdPayIds){
				sb.append("'");
				sb.append(id);
				sb.append("',");
			}
			hql += " and upp.usrProdPayId in (" + sb.toString().substring(0, sb.toString().length()-1) + ")";
			this.deleteByHql(hql);//更新
		}
	}
}
