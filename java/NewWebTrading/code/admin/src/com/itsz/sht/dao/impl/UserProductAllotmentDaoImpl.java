package com.itsz.sht.dao.impl;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import com.itsz.sht.common.DateUtil;
import com.itsz.sht.dao.UserProductAllotmentDao;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;

public class UserProductAllotmentDaoImpl extends BaseDao<UserProductAllotment>
		implements UserProductAllotmentDao {

	public UserProductAllotmentDaoImpl() {
		super(UserProductAllotment.class);

	}

	@Override
	public void addUserProductAllotment(
			UserProductAllotment userProductAllotment)
			throws DataAccessException {

		this.save(userProductAllotment);
	}

	@Override
	public void deleteUserProductAllotment(String userProductAllotmentId)
			throws DataAccessException {
		this.remove(userProductAllotmentId);

	}

	@Override
	public UserProductAllotment getUserProductAllotment(
			String userProductAllotmentId) throws DataAccessException {
		return this.get(userProductAllotmentId);
	}

	@Override
	public void updateUserProductAllotment(
			UserProductAllotment userProductAllotment)
			throws DataAccessException {
		this.update(userProductAllotment);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProductAllotment> getSubscriberUserProductAllotmentByClientId(
			String clientId, String status) throws DataAccessException {
		String hql = "from UserProductAllotment upa where upa.clntId='"
				+ clientId
				+ "' and alltStatus in ('RESERVE','RESRV_AUTO') and expDate=to_date('"
				+ DateUtil.getStringforDate(DateUtil
						.getNextMonthLastDateofMonth(new Date()))
				+ "','yyyy-mm-dd hh24:mi:ss')";
		return this.listByHql(hql);
	}

	@Override
	public void deleteUserProductAllotment(String productId, String clientId)
			throws DataAccessException {
		String hql = " delete UserProductAllotment upa where upa.prodId='"
				+ productId + "' and upa.clntId='" + clientId + "'";
		this.deleteByHql(hql);
	}

	@Override
	public void deleteUserProductAllotmentByClnId(String clientId)
			throws DataAccessException {
		String hql = "delete UserProductAllotment upa where upa.clntId='"
				+ clientId
				+ "' and upa.alltStatus in ('RESERVE','RESRV_AUTO') and upa.expDate=to_date('"
				+ DateUtil.getStringforDate(DateUtil
						.getNextMonthLastDateofMonth(new Date()))
				+ "','yyyy-mm-dd hh24:mi:ss')";
		this.deleteByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProductAllotment> findMemoDebitList() {
		Date thisMonthFirstDate = DateUtil.getThisMonthFirstDateofMonth(new Date());
		Date thisMonthLastDate = DateUtil.getThisMonthLastDateofMonth(new Date());
		String hql = "select upa from UserProductAllotment upa,UserProductPayment upp where upa.clntId=upp.clntId and upa.prodId=upp.prodId and upa.effDate=to_date('"
					+ DateUtil.getStringforDate(thisMonthFirstDate)
					+ "','yyyy-mm-dd hh24:mi:ss') and upa.expDate=to_date('"
					+ DateUtil.getStringforDate(thisMonthLastDate)
					+ "','yyyy-mm-dd hh24:mi:ss') and upa.alltStatus='SUCC' and upp.paySatus='MEMO' and upa.payReqId=upp.usrProdPayId";
		return this.listByHql(hql);
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserProductAllotment findReserveUserProductAllotmentByClnId(
			String clientId) throws DataAccessException {
		UserProductAllotment userProductAllotment = null;
		String hql = "from UserProductAllotment upa where upa.clntId='"
				+ clientId
				+ "' and upa.alltStatus in ('RESERVE','RESRV_AUTO') and upa.expDate=to_date('"
				+ DateUtil.getStringforDate(DateUtil
						.getNextMonthLastDateofMonth(new Date()))
				+ "','yyyy-mm-dd hh24:mi:ss')";
		List<UserProductAllotment> list = this.listByHql(hql);
		if (list != null && list.size() > 0) {
			userProductAllotment = list.get(0);
		}
		return userProductAllotment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProductAllotment> findReserveList() {
		Date thisMonthFirstDate = DateUtil
				.getThisMonthFirstDateofMonth(new Date());
		Date thisMonthLastDate = DateUtil
				.getThisMonthLastDateofMonth(new Date());
		String hql = "select upa from UserProductAllotment upa,UserProductPayment upp where upa.clntId=upp.clntId and upa.prodId=upp.prodId and upa.effDate=to_date('"
				+ DateUtil.getStringforDate(thisMonthFirstDate)
				+ "','yyyy-mm-dd hh24:mi:ss') and upa.expDate=to_date('"
				+ DateUtil.getStringforDate(thisMonthLastDate)
				+ "','yyyy-mm-dd hh24:mi:ss') and upa.alltStatus in ('RESERVE','RESRV_AUTO') and upp.paySatus='PENDING' and upa.payReqId=upp.usrProdPayId";
		return this.listByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProductAllotment> getAccountInUseThisMonth(String productId)
			throws DataAccessException {
		Date thisMonthFirstDate = DateUtil
				.getThisMonthFirstDateofMonth(new Date());
		Date thisMonthLastDate = DateUtil
				.getThisMonthLastDateofMonth(new Date());
		String hql = "select upa from UserProductAllotment upa where upa.effDate=to_date('"
				+ DateUtil.getStringforDate(thisMonthFirstDate)
				+ "','yyyy-mm-dd hh24:mi:ss') and upa.expDate=to_date('"
				+ DateUtil.getStringforDate(thisMonthLastDate)
				+ "','yyyy-mm-dd hh24:mi:ss') and upa.prodId ='"
				+ productId
				+ "'";
		return this.listByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProductAllotment> getAutoPurchaseNumByProductId(
			String productId) throws DataAccessException {
		Date nextMonthFirstDate = DateUtil
				.getNextMonthFirstDateofMonth(new Date());
		Date nextMonthLastDate = DateUtil
				.getNextMonthLastDateofMonth(new Date());
		String hql = "select upa from UserProductAllotment upa,UserProductPayment upp where upa.clntId=upp.clntId and upa.prodId=upp.prodId and upa.effDate=to_date('"
			+ DateUtil.getStringforDate(nextMonthFirstDate)
			+ "','yyyy-mm-dd hh24:mi:ss') and upa.expDate=to_date('"
			+ DateUtil.getStringforDate(nextMonthLastDate)
			+ "','yyyy-mm-dd hh24:mi:ss') and upa.alltStatus in ('RESERVE','RESRV_AUTO') and upp.paySatus='PENDING' and upa.payReqId=upp.usrProdPayId and upa.prodId ='"
			+ productId + "'";
		return this.listByHql(hql);
	}

	private void buildHQL(String productId, String startYearMonth,
			StringBuffer hql) {
		Date thisMonthLastDate = null;
		if (startYearMonth == null || "".equals(startYearMonth)) {
			thisMonthLastDate = DateUtil.getThisMonthLastDateofMonth(new Date());
		} else {
			String[] args = startYearMonth.split("-");
			String year = args[0];
			String month = args[1];
			if (!("".equals(year) || "".equals(month))) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Integer.parseInt(year), Integer.parseInt(month)-1, 1);
				thisMonthLastDate = DateUtil.getThisMonthLastDateofMonth(calendar.getTime());
			}
		}
		hql.append("select new com.itsz.sht.model.MonthlyPurchaseEnquiry(upa.clntId,upa.prodId,upa.effDate,upa.expDate,upp.usrProdPayId,upp.resTime,upp.priceHkd,upp.defDebtAcc,upp.resMessage,upp.paySatus,upp.updDate,upp.updBy) "
				        +" from UserProductAllotment upa,UserProductPayment upp where upa.prodId=upp.prodId and upa.clntId=upp.clntId and upa.expDate=to_date('"
						+ DateUtil.getStringforDate(thisMonthLastDate)
						+ "','yyyy-mm-dd hh24:mi:ss') and upa.alltStatus='SUCC' and  upp.paySatus in ('MEMO', 'FULLY-PAID') and upa.payReqId=upp.usrProdPayId");
		if(productId!=null && !productId.equals("")){
			hql.append(" and upa.prodId ='" + productId + "'");
		}
		hql.append(" order by upa.prodId, upp.paySatus");
	}

	@Override
	public Page findMonthlyPurchaseEnquiry(String productId,
			String startYearMonth, Integer pageNumber, Integer pageSize)
			throws DataAccessException {
		final StringBuffer hql = new StringBuffer();
		this.buildHQL(productId, startYearMonth, hql);

		HibernatePage hibernatePage = null;
		Query query = this.getSession().createQuery(hql.toString());
		int count = 0;
		count = query.list().size();
//		log.info("hql---" + hql + " count--" + count);
		hibernatePage = new HibernatePage(query, pageNumber, pageSize, count);
		return hibernatePage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Date getEffect(String clentId, String productId) {
		String sql="select eff_date from NW_USR_PROD_ALLT where clnt_id='"+clentId+"' and prod_id='"+productId+"' and allt_status='SUCC' and exp_date>to_date('"
						+ DateUtil.getStringforDate(new Date())
						+ "','yyyy-mm-dd hh24:mi:ss') order by eff_date desc";
		List list=this.listBySql(sql);
		if(list.size()>0){
			return new Date(((Timestamp) list.get(0)).getTime());
		}
		return null;
	}
}
