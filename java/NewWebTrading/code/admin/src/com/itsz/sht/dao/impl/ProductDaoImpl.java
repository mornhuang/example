package com.itsz.sht.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.itsz.common.util.SpringBeanUtil;
import com.itsz.common.util.StringMap;
import com.itsz.sht.common.DateUtil;
import com.itsz.sht.dao.ProductDao;
import com.itsz.sht.dao.hibernate.model.Product;


public  class ProductDaoImpl extends BaseDao<Product> implements ProductDao {

	public ProductDaoImpl() {
		super(Product.class);
	
	}

	@Override
	public void addProduct(Product product) throws DataAccessException {

		this.save(product);
	}

	@Override
	public void deleteProduct(String productId) throws DataAccessException {

		this.remove(productId);
	}

	@Override
	public Product getProduct(String productId) throws DataAccessException {

		return this.get(productId);
	}

	@Override
	public void upateProduct(Product product) throws DataAccessException {

		this.update(product);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Product findRTQProduct(String clientId) throws DataAccessException{
		Product product = null;
		String hql = "select p from Product p,UserProduct up where p.prodId=up.id.prodId and up.status='AVAIL' and up.exprDate>to_date('" + DateUtil.getStringforDate(new Date()) + "','yyyy-mm-dd hh24:mi:ss') and p.prodId like 'SSTR%' and up.id.clntId='" + clientId + "'";
		List<Product> list = this.listByHql(hql);
		if(list!=null && list.size()>0){
			product = list.get(0);
		}
		return product;
		
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findRTQProductList(final String clientId)
			throws DataAccessException {
		String hql = "select p from Product p,RtqApplication ra where p.prodStatus='AVAIL' and ra.rtqStatus='AVAIL' and p.prodId like 'SSTR%' and p.prodId=ra.prodId and p.discType not in (select case when up.cnDiscFlag='PRC' then 'NULL' else 'PRC' end from UserProfile up where up.clntId='" + clientId + "')";
		return this.listByHql(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findRTQProductListByClientCnDiscFlag(String cnDiscFlag)
			throws DataAccessException {
		String hql = "select p from Product p,RtqApplication ra where p.prodStatus='AVAIL' and ra.rtqStatus='AVAIL' and p.prodId like 'SSTR%' and p.prodId=ra.prodId";
		if(cnDiscFlag.equals("OTHERS")){
			hql += " and p.discType<>'PRC'";
		}else if(!cnDiscFlag.equals("OTHERS") && !cnDiscFlag.equals("PRC")){
			hql += " and 1=2";
		}
		return this.listByHql(hql);
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public List<Product> findProductListByClientCnDiscFlag(String cnDiscFlag)
			throws DataAccessException {
		String hql = "select p from Product p,RtqApplication ra where p.prodStatus='AVAIL' and ra.rtqStatus='AVAIL' and p.prodId=ra.prodId";
		if(cnDiscFlag.equals("OTHERS")){
			hql += " and p.discType<>'PRC'";
		}else if(!cnDiscFlag.equals("OTHERS") && !cnDiscFlag.equals("PRC")){
			hql += " and 1=2";
		}
		return this.listByHql(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findProductByCondition(StringMap params,  String order, boolean asc)  throws DataAccessException{
		final StringBuffer sql = new StringBuffer();
		final HashMap<String, Object> sqlParams = new HashMap<String, Object>();
		sql.append("select PROD_ID,PROD_STATUS,QUOTA,ALLT_QUOTA,SVC_MODE,VALT_OF_SVC,ACES_UNIT,BILL_TYPE,DISC_TYPE,PRICE_IN_HKD,EFF_DATE,EXPR_DATE,REMARKS,INIT_DATE,INIT_BY,UPD_DATE,UPD_BY");
		sql.append(" from NW_PROD P");
		sql.append(" where 1=1");
		buildSQL(params, sql, sqlParams, "list");
		if (order != null)
		{
			if (asc)
			{
				sql.append(" order by " + order + " asc");
			}
			else
			{
				sql.append(" order by " + order + " desc");
			}
		}
		else
		{
			sql.append(" order by P.PROD_ID");
		}
		HibernateTemplate ht = SpringBeanUtil.getHibernateTemplate();
		return ht.executeFind(new HibernateCallback()
		{
			public Object doInHibernate(Session sess) throws HibernateException, SQLException
			{
				Query query = sess.createSQLQuery(sql.toString());
//				query.setFirstResult(start);
//				query.setMaxResults(size);
				for (String name : sqlParams.keySet())
				{
					Object value = sqlParams.get(name);
					query.setParameter(name, value);
				}
				return query.list();
			}
		});
	}
	
	private void buildSQL(StringMap params, StringBuffer sql, HashMap<String, Object> sqlParams, String flag)
	{
		// 澶ч檰浼樻儬鐗堟爣璇�
		String isCn=params.getAsStringEmptyNull("isCn");
		if (isCn!= null&&isCn.equals("Y"))
		{
			sql.append(" and P.DISC_TYPE='PRC'");
			
		}
		
		// Product绫诲瀷
		if (params.getAsStringEmptyNull("stauts") != null)
		{
			sql.append(" and P.PROD_STATUS =:stauts");
			sqlParams.put("stauts", params.getAsStringEmptyNull("stauts") );
		}	
	}

	@Override
	public List<Product> getProductList() throws DataAccessException {
		// TODO Auto-generated method stub
		return this.listByHql("from Product p order by prod_id asc");
	}

}
