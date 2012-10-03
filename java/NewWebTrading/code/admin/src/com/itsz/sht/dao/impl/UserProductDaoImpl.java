package com.itsz.sht.dao.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.common.DateUtil;
import com.itsz.sht.dao.UserProductDao;
import com.itsz.sht.dao.hibernate.model.RtqAccId;
import com.itsz.sht.dao.hibernate.model.RtqAccount;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.model.ReserveAndRenewalEnquiry;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;

public class UserProductDaoImpl extends BaseDao<UserProduct> implements
		UserProductDao {

	public UserProductDaoImpl() {
		super(UserProduct.class); 

	}

	@Override
	public void addUserProduct(UserProduct userProduct)
			throws DataAccessException {
		this.save(userProduct);
	}

	@Override
	public void updateUserProduct(UserProduct userProduct)
			throws DataAccessException {
		this.update(userProduct);
	}

	public Page getPage(StringMap params, Integer pageNumber, Integer pageSize)
			throws DataAccessException {
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
		hql.append(" from UserProduct up where 1=1");
		String clientId = params.getAsStringEmptyNull("clientId");
		if (clientId != null) {
			hql.append(" and up.id.clntId=:clientId");
			hqlParams.put("clientId", clientId);
		}

		String productId = params.getAsStringEmptyNull("productId");
		if (productId != null) {
			hql.append(" and up.id.prodId=:productId");
			hqlParams.put("productId", productId);
		}

		String status = params.getAsStringEmptyNull("status");
		if (status != null) {
			hql.append(" and up.status=:status");
			hqlParams.put("status", status);
		}

		String allowRenewal = params.getAsStringEmptyNull("allowRenewal");
		if (allowRenewal != null) {
			hql.append(" and up.allwRenl=:allowRenewal");
			hqlParams.put("allowRenewal", allowRenewal);
		}
		String expireDate1 = params.getAsStringEmptyNull("expireDate1");
		if (expireDate1 != null) {
			hql.append(" and up.exprDate>= to_date(:expireDate1,'yyyy-mm-dd hh24:mi:ss')");
			hqlParams.put(" expireDate1", expireDate1);
		}
		String expireDate2 = params.getAsStringEmptyNull("expireDate2");
		if (expireDate2 != null) {
			hql.append(" and up.exprDate<=to_date(:expireDate2,'yyyy-mm-dd hh24:mi:ss')");
			hqlParams.put("expireDate2", expireDate2);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserProduct> findUserProductByClientId(String clientId)
			throws DataAccessException {
		Object[] objs = new Object[] { clientId };
		return (List<UserProduct>) getHibernateTemplate().findByNamedQuery(
				"findUserProductByClientId", objs);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public UserProduct findAvailUserProductByClientIdAndRTQ(String clientId) throws DataAccessException{
		UserProduct up = null;
		String hql = " from com.itsz.sht.dao.hibernate.model.UserProduct up "
						+ "where up.exprDate=to_date('" + DateUtil.getStringforDate(DateUtil.getThisMonthLastDateofMonth(new Date())) + "','yyyy-mm-dd hh24:mi:ss') and up.id.prodId like 'SSTR%' "
							+ "and up.id.clntId='" + clientId + "'";
		List<UserProduct> list = this.listByHql(hql);
		if (list != null && list.size() > 0) {
			up = list.get(0);
		}
		return up;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserProduct> findUserProductByProductId(String productId)
			throws DataAccessException {
		Object[] objs = new Object[] { productId };
		return (List<UserProduct>) getHibernateTemplate().findByNamedQuery(
				"findUserProductByProductId", objs);
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserProduct findUserProductByKey(String productId, String clientId)
			throws DataAccessException {
		UserProduct up = null;
		Object[] objs = new Object[] { clientId, productId };
		List<UserProduct> list = getHibernateTemplate().findByNamedQuery(
				"findUserProductByKey", objs);
		if (list != null && list.size() > 0) {
			up = list.get(0);
		}
		return up;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public UserProduct findUserProductById(String productId, String clientId)
			throws DataAccessException {
		UserProduct up = null;
		String hql = "from UserProduct up where	up.id.clntId='" + clientId + "' and up.id.prodId='" + productId + "' order by up.updDate desc";
		List<UserProduct> list = this.listByHql(hql);
		if (list != null && list.size() > 0) {
			up = list.get(0);
		}
		return up;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserProduct> findAvailUserProduct(String productId)
			throws DataAccessException {
		String hql = "select ups from com.itsz.sht.dao.hibernate.model.UserProduct ups "
					+ "where ups.id.prodId='" + productId 
					+ "' and ups.status='AVAIL' and ups.exprDate=to_date('" + DateUtil.getStringforDate(DateUtil.getThisMonthLastDateofMonth(new Date())) + "','yyyy-mm-dd hh24:mi:ss')";
		return this.listByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProduct> findUserProductByClientId(String clientId,
			Date date) throws DataAccessException {
		String hql = "from UserProduct up where up.id.clntId='" + clientId 
						+ "' and up.exprDate>to_date('" + DateUtil.getStringforDate(date) + "','yyyy-mm-dd hh24:mi:ss') ";
		return this.listByHql(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProduct> findUserProductByClientIdForCancle(String clientId,
			Date date) throws DataAccessException {
		String hql = "from UserProduct up where up.id.clntId='" + clientId 
					+ "' and up.status='AVAIL' and up.exprDate>to_date('" + DateUtil.getStringforDate(date) + "','yyyy-mm-dd hh24:mi:ss')";
		return this.listByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProduct> findUserProductByClientId(String clientId,
			Date date, String status) throws DataAccessException {
		String hql = "from UserProduct up where up.id.clntId='" + clientId 
					+ "' and up.status='" + status 
					+ "' and up.exprDate>to_date('" + DateUtil.getStringforDate(date) + "','yyyy-mm-dd hh24:mi:ss')";
		return this.listByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProduct> getExistingUserProductByClientId(String clientId, boolean rtqFlag) throws DataAccessException {
		String hql = "from UserProduct up where up.id.clntId='" + clientId 
					+ "' and up.exprDate>to_date('" + DateUtil.getStringforDate(new Date()) + "','yyyy-mm-dd hh24:mi:ss')"
					+ (rtqFlag?" and up.id.prodId like 'SSTR%'":"");
		return this.listByHql(hql);
	}
	
	@Override
	public List<UserProduct> getExistingUserProductByClientIdAndRTQ(String clientId) throws DataAccessException {
		return getExistingUserProductByClientId(clientId, true);
	}

	@Override
	public void updateUserProductList(List<UserProduct> userProductList)
			throws DataAccessException {
		this.saveOrUpdateBatch(userProductList);
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<UserProduct> findRenewalList() throws DataAccessException {
		Date da = DateUtil.getBeforeMonthLastDateofMonth(new Date());
		String hql = "from UserProduct up where up.allwRenl='Y' and up.status='AVAIL' and up.exprDate=to_date('" + DateUtil.getStringforDate(da) + "','yyyy-mm-dd hh24:mi:ss')";
		return this.listByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProduct> findAccountRequiredThisMonth(String productId)
			throws DataAccessException {
		Date startDate = DateUtil.getThisMonthFirstDateofMonth(new Date());
		Date endDate = DateUtil.getThisMonthLastDateofMonth(new Date());
		String hql = "from UserProduct up where up.id.prodId='" + productId + "' and up.exprDate <= to_date('" + DateUtil.getStringforDate(endDate) 
					+ "','yyyy-mm-dd hh24:mi:ss') and up.exprDate >= to_date('" + DateUtil.getStringforDate(startDate) + "','yyyy-mm-dd hh24:mi:ss')";
		return this.listByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProduct> getRenewalNumByProductId(String productId)
			throws DataAccessException {
		Date thisMonthLastDate = DateUtil.getThisMonthLastDateofMonth(new Date());
		String hql = "from UserProduct up where up.id.prodId='" + productId 
					+ "' and up.allwRenl='Y' and up.status='AVAIL' and up.exprDate=to_date('" + DateUtil.getStringforDate(thisMonthLastDate) + "','yyyy-mm-dd hh24:mi:ss')";
		return this.listByHql(hql);
	}

	
	@SuppressWarnings("unchecked")
	public Page findReserveAndRenewalEnquiry(Integer pageNumber, Integer pageSize,String productId){
		Date da =  DateUtil.getThisMonthLastDateofMonth(new Date());
		Date nextMonthFirstDate = DateUtil.getNextMonthFirstDateofMonth(new Date());
		Date nextMonthLastDate = DateUtil.getNextMonthLastDateofMonth(new Date());	
		String sql="select NW_USR_PROD.CLNT_ID AS clientId,"
	      +" NW_USR_PROD.PROD_ID AS productId,"
	      +" NW_PROD.PRICE_IN_HKD AS prcieInHKD,"
	      +" NW_USR_PROF.DEF_DEBT_ACC AS defDebtAcc,"
	      +" NW_USR_PROD.UPD_DATE AS updateDate,"
	      +" NW_USR_PROD.UPD_BY AS updateBy,"
	      +" 'RENEWAL' AS allRenl"
	      +" from NW_USR_PROD, NW_USR_PROF, NW_PROD"
	      +" where NW_USR_PROD.ALLW_RENL = 'Y'"
	      +"  AND NW_USR_PROD.STATUS = 'AVAIL'"
	      +" AND NW_USR_PROD.EXPR_DATE = TO_DATE('"+ DateUtil.getStringforDate(da)+"','yyyy-mm-dd hh24:mi:ss')"
	      +"  AND NW_USR_PROF.CLNT_ID = NW_USR_PROD.CLNT_ID"
	      +"  AND NW_PROD.PROD_ID = NW_USR_PROD.PROD_ID";
	    if(productId!=null&&!"".equals(productId)){
			sql=sql+" AND NW_USR_PROD.PROD_ID='"+productId+"'";
		}
	    sql=sql+" union all"
	      +" select NW_USR_PROD_ALLT.CLNT_ID AS clientId,"
	      +" NW_USR_PROD_ALLT.PROD_ID  AS productId,"
	      +" NW_PROD.PRICE_IN_HKD  AS priceInHKD,"
	      +" NW_USR_PROF.DEF_DEBT_ACC AS defDebtAcc,"
	      +" NW_USR_PROD_ALLT.UPD_DATE AS updateDate,"
	      +" NW_USR_PROD_ALLT.Upd_BY AS updateBy,"
	      +" NW_USR_PROD_ALLT.ALLT_STATUS AS allRenl"
	      +" from NW_USR_PROD_ALLT, NW_USR_PROD_PAY, NW_USR_PROF, NW_PROD"
	      +" where NW_USR_PROD_ALLT.CLNT_ID = NW_USR_PROD_PAY.CLNT_ID"
	      +" AND NW_USR_PROD_ALLT.PROD_ID = NW_USR_PROD_PAY.PROD_ID"
	      +" AND NW_USR_PROD_ALLT.EFF_DATE = TO_DATE('"+DateUtil.getStringforDate(nextMonthFirstDate)+"','yyyy-mm-dd hh24:mi:ss')"
	      +" AND NW_USR_PROD_ALLT.EXP_DATE = TO_DATE('"+DateUtil.getStringforDate(nextMonthLastDate)+"','yyyy-mm-dd hh24:mi:ss')"
	      +" AND (NW_USR_PROD_ALLT.ALLT_STATUS in ('RESERVE', 'RESRV_AUTO'))"
	      +" AND NW_USR_PROD_PAY.PAY_SATUS = 'PENDING'"
	      +" AND NW_USR_PROF.CLNT_ID = NW_USR_PROD_ALLT.CLNT_ID"
	      +" AND NW_PROD.PROD_ID = NW_USR_PROD_ALLT.PROD_ID"
	      +" AND NW_USR_PROD_ALLT.PAY_REQ_ID = NW_USR_PROD_PAY.USR_PROD_PAY_ID";
	    if(productId!=null&&!"".equals(productId)){
			sql=sql+" AND NW_USR_PROD_ALLT.PROD_ID='"+productId+"'";
		}	
	    sql += " order by productId, allRenl";
		HibernatePage hibernatePage = null;
		Query query = this.getSession().createSQLQuery(sql);
		List list=query.list();
		int count=0;
		if(list!=null&&list.size()>0)
		{	
			count=list.size();
		}
		log.info("sql---" + sql + " count--" + count);
		hibernatePage = new HibernatePage(query, pageNumber, pageSize, count);
		return hibernatePage;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReserveAndRenewalEnquiry> findreserveAndRenewalEnquiryById(String clientId) throws DataAccessException{
		Date da =  DateUtil.getThisMonthLastDateofMonth(new Date());
		Date nextMonthFirstDate = DateUtil.getNextMonthFirstDateofMonth(new Date());
		Date nextMonthLastDate = DateUtil.getNextMonthLastDateofMonth(new Date());
		List<ReserveAndRenewalEnquiry> list = new ArrayList<ReserveAndRenewalEnquiry>();
		String sql="select nw_usr_prod.clnt_id as clientid,"
		      +" nw_usr_prod.prod_id as productId,"
		      +" to_char(nw_prod.price_in_hkd) as prcieInHKD,"
		      +" nw_usr_prof.def_debt_acc as defDebtAcc,"
		      +" to_char(nw_usr_prod.upd_date,'yyyy-mm-dd hh24:mi:ss') as updateDate,"
		      +" nw_usr_prod.upd_by as updateBy,"
		      +" 'RENEWAL' as allRenl"
		      +" from nw_usr_prod, nw_usr_prof, nw_prod"
		      +" where nw_usr_prod.allw_renl = 'Y'"
		      +"  and nw_usr_prod.status = 'AVAIL'"
		      +" and nw_usr_prod.expr_date = to_date('"+ DateUtil.getStringforDate(da)+"','yyyy-mm-dd hh24:mi:ss')"
		      +"  and nw_usr_prof.clnt_id = nw_usr_prod.clnt_id"
		      +"  and nw_prod.prod_id = nw_usr_prod.prod_id and nw_usr_prod.clnt_id='"+clientId+"'";
		sql += " union all"
				+" select nw_usr_prod_allt.clnt_id as clientId,"
				+" nw_usr_prod_allt.prod_id  as productId,"
				+" to_char(nw_prod.price_in_hkd)  as prcieInHKD,"
				+" nw_usr_prof.def_debt_acc as defDebtAcc,"
				+" to_char(nw_usr_prod_allt.upd_date,'yyyy-mm-dd hh24:mi:ss') as updateDate,"
				+" nw_usr_prod_allt.upd_by as updateBy,"
				+" nw_usr_prod_allt.allt_status as allRenl"
				+" from nw_usr_prod_allt, nw_usr_prod_pay, nw_usr_prof, nw_prod"
				+" where nw_usr_prod_allt.clnt_id = nw_usr_prod_pay.clnt_id"
				+" and nw_usr_prod_allt.prod_id = nw_usr_prod_pay.prod_id"
				+" and nw_usr_prod_allt.eff_date = to_date('"+DateUtil.getStringforDate(nextMonthFirstDate)+"','yyyy-mm-dd hh24:mi:ss')"
				+" and nw_usr_prod_allt.exp_date = to_date('"+DateUtil.getStringforDate(nextMonthLastDate)+"','yyyy-mm-dd hh24:mi:ss')"
				+" and (nw_usr_prod_allt.allt_status in ('RESERVE', 'RESRV_AUTO'))"
				+" and nw_usr_prod_pay.pay_satus = 'PENDING'"
				+" and nw_usr_prof.clnt_id = nw_usr_prod_allt.clnt_id"
				+" and nw_usr_prod_allt.pay_req_id = nw_usr_prod_pay.usr_prod_pay_id"
				+" and nw_prod.prod_id = nw_usr_prod_allt.prod_id and nw_usr_prod_allt.clnt_id='"+clientId+"' order by productId";
		List<Object[]> objList = this.listBySql(sql);
		for(Object[] obj : objList){
			if(obj.length == 7){
				ReserveAndRenewalEnquiry ra = new ReserveAndRenewalEnquiry();
				if(obj[0]!=null && (obj[0] instanceof String)){
					ra.setClientId((String)obj[0]);
				}
				if(obj[1]!=null && (obj[1] instanceof String)){
					ra.setProductId((String)obj[1]);
				}
				if(obj[2]!=null && (obj[2] instanceof String)){
					ra.setPriceInHK((String)obj[2]);
				}
				if(obj[3]!=null && (obj[3] instanceof String)){
					ra.setDefAccount((String)obj[3]);
				}
				if(obj[4]!=null && (obj[4] instanceof String)){
					ra.setUpdDate((String)obj[4]);
				}
				if(obj[5]!=null && (obj[5] instanceof String)){
					ra.setUpdBy((String)obj[5]);
				}
				if(obj[6]!=null && (obj[6] instanceof String)){
					ra.setType((String)obj[6]);
				}
				list.add(ra);
			}
		}
//		String hql = "select new com.itsz.sht.model.ReserveAndRenewalEnquiry(up.id.clntId,up.id.prodId,p.priceInHkd,u.defDebtAcc,up.updDate,up.updBy,'RENEWAL') from UserProduct up ,UserProfile u,Product p where up.allwRenl='Y' and up.status='AVAIL' and up.exprDate=to_date('" 
//					+ DateUtil.getStringforDate(da) + "','yyyy-mm-dd hh24:mi:ss') and  u.clntId=up.id.clntId and  p.prodId=up.id.prodId and up.id.clntId='" + clientId + "' ";
//		List<ReserveAndRenewalEnquiry> list1 = this.listByHql(hql);
//		hql = "select new com.itsz.sht.model.ReserveAndRenewalEnquiry(upa.clntId,upa.prodId,p.priceInHkd,u.defDebtAcc,upa.updDate,upa.updBy,upa.alltStatus) from UserProductAllotment upa,UserProductPayment upp,UserProfile u,Product p where upa.clntId=upp.clntId and upa.prodId=upp.prodId and upa.effDate=to_date('"
//			+ DateUtil.getStringforDate(nextMonthFirstDate)
//			+ "','yyyy-mm-dd hh24:mi:ss') and upa.expDate=to_date('"
//		  + DateUtil.getStringforDate(nextMonthLastDate)
//			+ "','yyyy-mm-dd hh24:mi:ss') and upa.alltStatus in ('RESERVE','RESRV_AUTO') and upp.paySatus='PENDING' and u.clntId=upa.clntId and p.prodId=upa.prodId and upa.payReqId=upp.usrProdPayId and upa.clntId='" + clientId + "' ";    
//		List<ReserveAndRenewalEnquiry> list2 = this.listByHql(hql);
//		list1.addAll(list2); 
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> findNoAccountUserProductForClientId(String productId) throws DataAccessException{
		String sql = "select clnt_id from nw_usr_prod where (prod_id,clnt_id) not in (select prod_id,clnt_id from nw_rtq_acc_asgn) and nw_usr_prod.prod_id='"+productId+"'"
						+ " and nw_usr_prod.status='AVAIL' and expr_date>to_date('" + DateUtil.getStringforDate(new Date()) + "','yyyy-mm-dd hh24:mi:ss')"
						+ " order by nw_usr_prod.prod_id ";
		return this.listBySql(sql);
	}

}
