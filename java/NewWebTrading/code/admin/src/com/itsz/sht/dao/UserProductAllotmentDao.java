package com.itsz.sht.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.taifook.adminportal.common.util.page.Page;

public interface UserProductAllotmentDao {

	public void addUserProductAllotment(UserProductAllotment userProductAllotment)throws DataAccessException;
	public void updateUserProductAllotment(UserProductAllotment userProductAllotment)throws DataAccessException;
	public void deleteUserProductAllotment(String userProductAllotmentId)throws DataAccessException;
	public UserProductAllotment getUserProductAllotment(String userProductAllotmentId)throws DataAccessException;
    public Date getEffect(String clentId,String productId);
	//其它查询参数参考需求
    
    public List<UserProductAllotment> getSubscriberUserProductAllotmentByClientId(String clientId,String status)throws DataAccessException;
    public void deleteUserProductAllotment(String productId,String clientId)throws DataAccessException;

    /**
     * 检出预定购买的ReserveList 
     * User Product Allotment
     * Effective Date=本月月初
     * Expire Date=本月月底
     * Allot Status=RESERVE
     * 连接UserProductPayment查询 Payment Status=PENDING
     * @return List UserProductAllotment
     */
	public List<UserProductAllotment> findReserveList();
	
	/**检出需要做memo debit的数据
	 * User Product Allotment
	 * Effective Date=本月月初
	 * Expire Date=本月月底
	 * Allot Status=SUCC
	 * @return List UserProductAllotment
	 */
	public List<UserProductAllotment> findMemoDebitList();


	/**
	 * 查询条件
	 * Effective Date=“下月1日”
	 * Expire Date=“下月月末”
	 * Allot Status=RESERVE
	 * @return List UserProductAllotment
	 * @throws DataAccessException
	 */
	public List<UserProductAllotment>  getAutoPurchaseNumByProductId(String productId)  throws  DataAccessException;
	
	
	
	/**
	 * 查询条件
	 * Effective Date=“当月1日”
	 * Expire Date=“当月月末”
	 * @return List UserProductAllotment
	 * @throws DataAccessException
	 */
	public List<UserProductAllotment>  getAccountInUseThisMonth(String productId)  throws  DataAccessException;
	
	/**
	 * 删除用户已预定的数据
	 * clientId
	 * Expire Date=下月月底
	 * Allot Status=RESERVE|RESRV_AUTO
	 * @return
	 * @throws DataAccessException
	 */		
	public void deleteUserProductAllotmentByClnId(String clientId) throws DataAccessException;
	
	/**
	 * 获取用户已预定的数据
	 * clientId
	 * Expire Date=下月月底
	 * Allot Status=RESERVE|RESRV_AUTO
	 * @return
	 * @throws DataAccessException
	 */	
	public UserProductAllotment findReserveUserProductAllotmentByClnId(String clientId) throws DataAccessException;
	
	public Page findMonthlyPurchaseEnquiry(String productId,String startYearMonth, Integer pageNumber, Integer pageSize)throws DataAccessException;
}


