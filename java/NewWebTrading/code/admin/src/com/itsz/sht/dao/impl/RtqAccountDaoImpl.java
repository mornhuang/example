package com.itsz.sht.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.dao.RtqAccountDao;
import com.itsz.sht.dao.hibernate.model.RtqAccId;
import com.itsz.sht.dao.hibernate.model.RtqAccount;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;

public class RtqAccountDaoImpl  extends BaseDao<RtqAccount> implements RtqAccountDao{

	public RtqAccountDaoImpl() {
		super(RtqAccount.class);

	}

	@Override
	public void addRtqAccount(RtqAccount rtqAccount) throws DataAccessException {
		this.save(rtqAccount);
		
	}

	@Override
	public void updateRtqAccount(RtqAccount rtqAccount)
			throws DataAccessException {
		this.update(rtqAccount);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public RtqAccount getRtqAccount(String productId, String rtqLoginId)
			throws DataAccessException {
		RtqAccount rtqAccount=null;
		String hql=" from RtqAccount ra where ra.id.prodId='"+productId+"' and ra.id.rtqLognId='"+rtqLoginId+"'";
		List<RtqAccount> list=this.listByHql(hql);
		if(list!=null&&list.size()>0){
			rtqAccount=list.get(0);
		}
		return rtqAccount;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<RtqAccount> getRtqAccountByProductId(String productId)
			throws DataAccessException {
		String hql=" from RtqAccount ra where ra.id.prodId='"+productId+"' order by ra.id.rtqLognId desc";
		List<RtqAccount> list=this.listByHql(hql);
		return list;
	}

	@Override
	public void addRtqAccountList(List<RtqAccount> rtqAccountList)
			throws DataAccessException {
			this.saveBatch(rtqAccountList);
		}


	@Override
	public void deleteRtqAccountList(List<RtqAccount> rtqAccountList)
			throws DataAccessException {
		if(rtqAccountList!=null&&rtqAccountList.size()>0){
			for (RtqAccount rtqAccount : rtqAccountList) {
				String productId=rtqAccount.getId().getProdId();
				String rtqLoginId=rtqAccount.getId().getRtqLognId();
				if(productId!=null&&!"".equals(productId)&&rtqLoginId!=null&&!"".equals(rtqLoginId)){
					String hql=" delete from RtqAccount ra where ra.id.prodId='"+productId+"' and ra.id.rtqLognId='"+rtqLoginId+"'";
					this.deleteByHql(hql);
				}
			}
		}	
	}
	@Override
	public void updateRtqAccountList(List<RtqAccount> rtqAccountList)
			throws DataAccessException {
		this.updateBatch(rtqAccountList);
		
	}
	
	@Override
	public void updateRtqAccountPassword(String productId, List<String> rtqLognIdUpdateList, String password) throws DataAccessException{
		StringBuffer idStrBuff = new StringBuffer();
		if (rtqLognIdUpdateList != null && rtqLognIdUpdateList.size() > 0) {
			for (int i = 0; i < rtqLognIdUpdateList.size(); i++) {
				idStrBuff.append("'");
				idStrBuff.append(rtqLognIdUpdateList.get(i));
				idStrBuff.append("'");
				idStrBuff.append(", ");
			}
			String idStr = idStrBuff.toString();
			String idStr1 = idStr.substring(0, idStr.length() - 2);
			String hql = "update from RtqAccount r set r.rtqLognPwd='" + password
						+ "' where r.id.rtqLognId in (" + idStr1 + ") and r.id.prodId='" + productId + "'";
			getSession().createQuery(hql).executeUpdate();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> deleteLastNRtqAccountByProductId(String productId, Long n)
			throws DataAccessException {
		String sql="select rtq_logn_id from nw_rtq_acc where nw_rtq_acc.prod_id='"+productId+"' and rownum<="+ n +" order by init_date desc, rtq_logn_id desc";
		List list=this.listBySql(sql);
		List<String> rtqLognIdList = new ArrayList<String>();
		if(list!=null&&list.size()>0){
			String rtqLognIdStr = "'null'";
			for (Object rtqLognId : list) {
				rtqLognIdStr += ",'" + rtqLognId.toString() + "'";
		    }
			String hql = "delete RtqAccount ra where ra.id.prodId='" + productId + "' and ra.id.rtqLognId in (" + rtqLognIdStr + ")";
			List idList = this.listBySql("select rtq_logn_id from nw_rtq_acc_asgn where rtq_logn_id in (" + rtqLognIdStr + ") and prod_id='" + productId + "'");
			if(idList!=null && idList.size()>0){
				rtqLognIdList.addAll(idList);
				rtqLognIdStr = "'null'";
				for (Object rtqLognId : idList) {
					rtqLognIdStr += ",'" + rtqLognId.toString() + "'";
			    }
				hql += " and ra.id.rtqLognId not in (" + rtqLognIdStr +")";
			}
			this.deleteByHql(hql);
		}
		return rtqLognIdList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RtqAccount> findNotTakenUpAccount(String productId) throws DataAccessException{
		String sql="select * from nw_rtq_acc where (prod_id,rtq_logn_id) not in (select prod_id,rtq_logn_id from nw_rtq_acc_asgn) and nw_rtq_acc.prod_id='"+productId+"' order by nw_rtq_acc.prod_id ";
		List<RtqAccount>  listAccount=new ArrayList<RtqAccount>();
		List<Object[]> listObject=this.listBySql(sql);
		if(listObject!=null&&listObject.size()>0){
			for (int i = 0; i < listObject.size(); i++) {
				Object[] rtqAcc=(Object[]) listObject.get(i);
				RtqAccount rtqAccount=new RtqAccount();
				RtqAccId rtqAccId=new RtqAccId();
				if(rtqAcc[0]!=null){
					rtqAccId.setProdId(rtqAcc[0].toString());
				}
				if(rtqAcc[1]!=null){
					rtqAccId.setRtqLognId(rtqAcc[1].toString());
				}
				if(rtqAcc[2]!=null){
					rtqAccount.setRtqLognPwd(rtqAcc[2].toString());
				}
				if(rtqAcc[3]!=null){
					rtqAccount.setInitDate((Date)rtqAcc[3]);
				}
				if(rtqAcc[4]!=null){
					rtqAccount.setInitBy(rtqAcc[4].toString());
				}
				if(rtqAcc[5]!=null){
					rtqAccount.setUpdDate((Date)rtqAcc[5]);
				}
				if(rtqAcc[6]!=null){
					rtqAccount.setUpdBy(rtqAcc[6].toString());
				}
				rtqAccount.setId(rtqAccId);
				listAccount.add(rtqAccount);
			}
		}
		
		return listAccount;
	}

	@Override
	public Long getRtqAccountMountByProductId(String productId)
			throws DataAccessException {
		String sql="SELECT COUNT(*) FROM NW_RTQ_ACC R WHERE R.PROD_ID='"+productId+"'";
	   Long mount=	new Long( String.valueOf(this.listBySql(sql).get(0)));
		return mount;
	}

	@Override
	public RtqAccount findLastAccountId(String productId) {
		List <RtqAccount> list=this.getRtqAccountByProductId(productId);
		if(list!=null&&list.size()>0)
		{	return list.get(0);}
		return null;
	}

	private Page getPage(StringMap params, Integer pageNumber, Integer pageSize)
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
		hql.append(" from RtqAccount r where 1=1");
		
		String productId = params.getAsStringEmptyNull("productId");
		if (productId != null) {
			hql.append(" and r.id.prodId=:productId"  );
			hqlParams.put("productId", productId);
		}
		
		String rtpLoginId = params.getAsStringEmptyNull("rtqLoginId");
		if (rtpLoginId != null) {
			hql.append(" and r.id.rtqLognId=:rtqLoginId");
			hqlParams.put("rtqLoginId", rtpLoginId);
		}
	}

	@Override
	public Page listRtqAccountByProIdOrLoginId(String productId,
			String rtqLoginId, Integer pageNumber, Integer pageSize)
			throws DataAccessException {
		StringMap params=new StringMap();
		params.put("productId", productId);
		params.put("rtqLoginId", rtqLoginId);
		return this.getPage(params,pageNumber,pageSize);
	}

}
