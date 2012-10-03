package com.itsz.sht.dao;

import java.util.Date;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.UserProductPayment;
import com.taifook.adminportal.common.util.page.Page;

public interface UserProductPaymentDao {
	
	public void addUserProductPayment(UserProductPayment userProductPayment)throws DataAccessException;
	public void updateUserProductPayment(UserProductPayment userProductPayment)throws DataAccessException;
	public void deleteUserProductPayment(String userProductPaymentId)throws DataAccessException;
	public UserProductPayment getUserProductPayment(String userProductPaymentId)throws DataAccessException;
	
	/**
	 * 删除用户已预定的数据
	 * clientId
	 * Payment Status=PENDIG
	 * @return
	 * @throws DataAccessException
	 */		
	public void deleteUserProductPaymentByClnId(String clientId) throws DataAccessException;
	
	/**
	 * 获取时间段内需要手动扣款的数据
	 * RES_MESSAGE=MANUAL
	 * @param Date startTime
	 * @param Date endTime
	 * @return Page
	 * @throws DataAccessException
	 */
	public Page getUserProductPaymentListByManual(Date startTime, Date endTime, int pageNumber, int pageSize) throws DataAccessException; 
	
	/**
	 * 将指定数据确认已手动扣款成功
	 * RES_MESSAGE=MANUAL
	 * @param String[] usrProdPayIds
	 * @return
	 * @throws DataAccessException
	 */
	public void updateUserProductPaymentByManual(String[] usrProdPayIds) throws DataAccessException;
}
