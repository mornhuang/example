package com.itsz.sht.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.model.ReserveAndRenewalEnquiry;
import com.taifook.adminportal.common.util.page.Page;

public interface UserProductDao {
	
	
	public void addUserProduct(UserProduct userProduct) throws DataAccessException;
	
	public void updateUserProduct(UserProduct userProduct) throws DataAccessException;
	
	public void updateUserProductList(List<UserProduct> userProductList) throws DataAccessException;
	
	public List<UserProduct> findUserProductByClientId(String clientId) throws  DataAccessException;
	
	public UserProduct findAvailUserProductByClientIdAndRTQ(String clientId) throws DataAccessException;
	
	public List<UserProduct> findUserProductByClientId(String clientId,Date date) throws  DataAccessException;
	
	public List<UserProduct> findUserProductByClientId(String clientId,Date date,String stauts) throws  DataAccessException;
	
	public List<UserProduct> findUserProductByProductId(String productId) throws  DataAccessException;
	
	public List<UserProduct> findUserProductByClientIdForCancle(String clientId,Date date) throws DataAccessException;
	
	public List<UserProduct> getExistingUserProductByClientIdAndRTQ(String clientId) throws DataAccessException;
	
	/**
	 * 查询正在使用的产品
	 * @param productId
	 * @param clientId
	 * UserProduct.status='AVAIL'
	 * @return
	 * @throws DataAccessException
	 */
	public UserProduct findUserProductByKey(String productId,String clientId)throws  DataAccessException;
	
	/**
	 * 查询客户产品
	 * @param productId
	 * @param clientId
	 * @return
	 * @throws DataAccessException
	 */
	public UserProduct findUserProductById(String productId,String clientId) throws DataAccessException;
	
	/**
	 * 现在使用状态的 Produc和User Product联合查询
	 * @param productId
	 * UserProduct.exprDate>=当前时间
	 * @return 
	 * @throws DataAccessException
	 */
	public List<UserProduct> findAvailUserProduct(String productId)throws  DataAccessException;
	/**
	 * 客户存在的没有过期的 查询条件 
	 * exprDate>=当前时间
	 * @param clientId
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserProduct> getExistingUserProductByClientId(String clientId, boolean rtqFlag)throws DataAccessException;
	
	
	/**
	 * 检出需自动续期的数据 RenewalList
	 * User Product满足以下条件
	 * Expire Date 为上月月底
	 * User Product Status=AVAIL
	 * Allow Renewal=Y
	 * @return List UserProduct
	 * @throws DataAccessException
	 */
	public List<UserProduct> findRenewalList()throws  DataAccessException;
	
	/**
	 * "本月月初" 到"本月月底" 新增的记录查询条件
	 * "本月月初" <= updDate<="本月月底"
	 * @param productId
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserProduct> findAccountRequiredThisMonth(String productId) throws  DataAccessException;
	/**
	 * 查询需要自动续期的数据
	 * @param productId
	 * Expire Date=上月月底
	 * User Product Status=AVAIL
	 * Allow Renewal=Y
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserProduct> getRenewalNumByProductId(String productId) throws  DataAccessException;
	

	/**
	 * Reserve and Renewal Details Enquiry
	 * 查询的逻辑可以参考为客户自动购买预订的服务产品中的renewalList和reserveList
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page findReserveAndRenewalEnquiry(Integer pageNumber, Integer pageSize,String productId);
	
	public List<ReserveAndRenewalEnquiry> findreserveAndRenewalEnquiryById(String clientId) throws DataAccessException;
	
	/**查询未分配RTQ账号的clientId
	 * @param productId
	 * @return 
	 * @throws DataAccessException
	 */
	public List<String> findNoAccountUserProductForClientId(String productId) throws DataAccessException;
}
