package com.itsz.sht.dao.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.itsz.common.util.StringMap;
import com.itsz.sht.dao.RtqAccountAssignmentDao;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignment;
import com.itsz.sht.model.RtqAccountAssDto;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;

public class RtqAccountAssignmentDaoImpl extends BaseDao<RtqAccountAssignment>
		implements RtqAccountAssignmentDao {

	public RtqAccountAssignmentDaoImpl() {
		super(RtqAccountAssignment.class);
	}

	@Override
	public void addRtqAccountAssignment(
			RtqAccountAssignment rtqAccountAssignment)
			throws DataAccessException {

		this.save(rtqAccountAssignment);
	}

	@Override
	public void deleteRtqAccountAssignment(String rtqAccountAssignmentId)
			throws DataAccessException {
		this.remove(rtqAccountAssignmentId);
	}

	@Override
	public RtqAccountAssignment getRtqAccountAssignment(
			String rtqAccountAssignmentId) throws DataAccessException {
		return this.get(rtqAccountAssignmentId);
	}

	@Override
	public void updateRtqAccountAssignment(
			RtqAccountAssignment rtqAccountAssignment)
			throws DataAccessException {
		this.update(rtqAccountAssignment);
	}

	 @Override
	 @SuppressWarnings("unchecked")
	 public List<RtqAccountAssignment> getRtqAccountAssignmentByProductId(
	 String productId) throws DataAccessException {
	 String
	 hql=" from RtqAccountAssignment r where r.id.prodId='"+productId+"'";
	 return this.listByHql(hql);
	 }

	@Override
	public void deleteRtqAccountAssignment(List<String> clintIds, String productId)
			throws DataAccessException {
		StringBuffer clientStrBuff = new StringBuffer();
		if (clintIds != null && clintIds.size() > 0) {
			for (int i = 0; i < clintIds.size(); i++) {
				clientStrBuff.append("'");
				clientStrBuff.append(clintIds.get(i));
				clientStrBuff.append("'");
				clientStrBuff.append(" ,");
			}
			
			String clientStr = clientStrBuff.toString();
			String clientStr1 = clientStr.substring(0, clientStr.length() - 1);
			String hql = "delete from RtqAccountAssignment r where r.id.prodId='" + productId + "' and r.id.clntId in ("
					+ clientStr1 + ")";
			this.deleteByHql(hql);
		}
	}
	
	@Override
	public void updateRtqAccountAssignmentPassword(String productId, List<String> rtqLognIdUpdateList, String password) throws DataAccessException{
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
			String hql = "update from RtqAccountAssignment r set r.rtqLognPwd='" + password
						+ "' where r.id.rtqLognId in (" + idStr1 + ") and r.id.prodId='" + productId + "'";
			getSession().createQuery(hql).executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RtqAccountAssignment getRtqAccountAssignmentByClntIdAndProdId(String clientId,
			String productId) throws DataAccessException {
		String hql = " from RtqAccountAssignment r where r.id.clntId='" + clientId + "' and r.id.prodId='" + productId + "'";
		RtqAccountAssignment raa = null;
		List<RtqAccountAssignment> list = this.listByHql(hql);
		if(list!=null && list.size()>0){
			raa = list.get(0);
		}
		return raa;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public RtqAccountAssignment getRtqAccountAssignmentByProdIdAndRtqLoginId(
			String productId,String rtqLoginId) throws DataAccessException {
		String hql = " from RtqAccountAssignment r where r.id.rtqLognId='" + rtqLoginId + "' and r.id.prodId='" + productId + "'";
		RtqAccountAssignment raa = null;
		List<RtqAccountAssignment> list = this.listByHql(hql);
		if(list!=null && list.size()>0){
			raa = list.get(0);
		}
		return raa;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RtqAccountAssignment> getRtqAccountAssignment(String productId,
			String clientId) throws DataAccessException {
		String hql = " from RtqAccountAssignment r where r.id.prodId='"
				+ productId + "' and r.id.clntId='" + clientId + "'";
		return this.listByHql(hql);
	}

	@Override
	public void deleteRtqAccountAssignmentList(
			List<RtqAccountAssignment> rtqAccountAssignmentList)
			throws DataAccessException {
		if (rtqAccountAssignmentList != null && rtqAccountAssignmentList.size() > 0){
			for (RtqAccountAssignment rtqAccountAssignment : rtqAccountAssignmentList) {
				String productId = rtqAccountAssignment.getId().getProdId();
				String rtqLoginId = rtqAccountAssignment.getId().getRtqLognId();
				if (productId != null && !"".equals(productId)
						&& rtqLoginId != null && !"".equals(rtqLoginId)) {
					String hql = "delete from RtqAccountAssignment r where r.id.prodId='"
							+ productId
							+ "' and r.id.rtqLognId='"
							+ rtqLoginId
							+ "'";
					this.deleteByHql(hql);
				}
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RtqAccountAssignment> getRtqAccountByClientId(String clientId)
			throws DataAccessException {
		String hql = " from RtqAccountAssignment r where r.id.clntId='"
				+ clientId + "'";
		return this.listByHql(hql);
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

	@Override
	public Page getRtqAccountAssignmentByProductId(String productId,
			Integer pageNumber, Integer pageSize) throws DataAccessException {
		StringMap params=new StringMap();
		params.put("productId", productId);
	return	this.getPage(params, pageNumber, pageSize);
	}

	private void buildHQL(StringMap params, StringBuffer hql,
			HashMap<String, Object> hqlParams) {
		hql.append(" from RtqAccountAssignment r where 1=1");
		String clientId = params.getAsStringEmptyNull("clientId");
		if (clientId != null) {
			hql.append(" and r.id.clntId=:clientId");
			hqlParams.put("clientId", clientId);
		}

		String productId = params.getAsStringEmptyNull("productId");
		if (productId != null) {
			hql.append(" and r.id.prodId=:productId"  );
			hqlParams.put("productId", productId);
		}
		
		String rtpLoginId = params.getAsStringEmptyNull("rtpLoginId");
		if (rtpLoginId != null) {
			hql.append(" and r.id.rtqLognId=:rtpLoginId");
			hqlParams.put("rtpLoginId", rtpLoginId);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public RtqAccountAssignment getRtqAccountAssignment(String productId,
			String rtqLoginId, String clientId) throws DataAccessException {
		String hql = " from RtqAccountAssignment r where r.id.prodId='"
			+ productId +" and r.id.rtqLognId='"+rtqLoginId+ "' and r.id.clntId='" + clientId + "'";
	List<RtqAccountAssignment> list=this.listByHql(hql);
	RtqAccountAssignment rtqAccAss=null;
	if(list!=null&&list.size()>0){
		rtqAccAss=list.get(0);
	  }
	 return rtqAccAss;
  }

	@Override
	@SuppressWarnings("unchecked")
	public List<RtqAccountAssDto> listRtqAccountAssByProductId(String productId)
			throws DataAccessException {
		String sql="select ra.prod_id, ra.rtq_logn_id, ra.rtq_logn_pwd, raa.clnt_id from ( (select prod_id, rtq_logn_id, rtq_logn_pwd from nw_rtq_acc where prod_id = '"+productId+"') ra ) LEFT JOIN nw_rtq_acc_asgn raa on ra.prod_id = raa.prod_id and ra.rtq_logn_id = raa.rtq_logn_id order by ra.rtq_logn_id";
		List<RtqAccountAssDto> rtqAccountAssDtoList=new ArrayList<RtqAccountAssDto>();
	//	String hql="select new com.itsz.sht.model.RtqAccountAssDto(ra.id.prodId,ra.id.rtqLognId,raa.id.clntId,raa.rtqLognPwd) from RtqAccount ra ,RtqAccountAssignment raa where ra.id.prodId=raa.id.prodId and ra.id.rtqLognId=raa.id.rtqLognId and ra.id.prodId='"+productId+"'";
	   List<Object[]> listObject= this.listBySql(sql);
	   if(listObject!=null&&listObject.size()>0){
		   for (Object[] objects : listObject) {
			   RtqAccountAssDto dto=new RtqAccountAssDto();
				if(objects[0]!=null){
					dto.setProductId(objects[0].toString());
				}
                if(objects[1]!=null){
					dto.setRtqLoginId(objects[1].toString());
				}
                if(objects[2]!=null){
	                dto.setRtqLoginPwd(objects[2].toString());
                }else{
                	dto.setRtqLoginPwd("");
                }
                if(objects[3]!=null){
	               dto.setClientId(objects[3].toString());
                }else{
                	dto.setClientId("");
                }
                rtqAccountAssDtoList.add(dto);
			}  
	   }
		return rtqAccountAssDtoList;
	}
}
