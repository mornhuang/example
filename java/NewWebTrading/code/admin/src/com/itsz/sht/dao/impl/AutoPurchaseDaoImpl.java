/**
 * 
 */
package com.itsz.sht.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.AutoPurchaseDao;
import com.itsz.sht.dao.hibernate.model.AutoPurchase;

/**
 * @author cyzeng
 *
 */
public class AutoPurchaseDaoImpl extends BaseDao<AutoPurchase> implements AutoPurchaseDao {

	
	public AutoPurchaseDaoImpl() {
		super(AutoPurchase.class);
	}

	@Override
	public void addAutoPurchase(AutoPurchase autoPurchase)
			throws DataAccessException {
		this.save(autoPurchase);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AutoPurchase> getAutoPurchaseList(String status,
			String startTime, String endTime) throws DataAccessException {
		String hql = "from AutoPurchase ap where 1=1";
		if(status!=null && !status.equals("")){
			hql += " and ap.status='" + status + "'";
		}
		if(startTime!=null && !startTime.equals("")){
			hql += " and ap.updDate>=to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss')";
		} 
		if(endTime!=null && !endTime.equals("")){
			hql += " and ap.updDate<=to_date('" + endTime + "','yyyy-mm-dd hh24:mi:ss')";
		} 
		hql += "order by ap.status, ap.updDate";
		return this.listByHql(hql);
	}

}
