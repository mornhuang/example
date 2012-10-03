package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.itsz.sht.dao.hibernate.model.RtqAccount;
import com.taifook.adminportal.common.util.page.Page;

public interface RtqAccountDao {
	
	public void addRtqAccount(RtqAccount rtqAccount)throws DataAccessException;
	public void updateRtqAccount(RtqAccount rtqAccount)throws DataAccessException;
	public RtqAccount getRtqAccount(String productId,String rtqLoginId)throws DataAccessException;
	public List<RtqAccount> getRtqAccountByProductId(String productId)throws DataAccessException;
	public void addRtqAccountList(List<RtqAccount> rtqAccountList)throws DataAccessException;
	public void updateRtqAccountList(List<RtqAccount> rtqAccountList)throws DataAccessException;
	public void deleteRtqAccountList(List<RtqAccount> rtqAccountList)throws DataAccessException;
	
	public Long getRtqAccountMountByProductId(String productId)throws DataAccessException;
	/**
	 * 根据productId删除后面N条记录
	 * @param productId
	 * @param n
	 * @return 返回不能删除的记录 
	 * @throws DataAccessException
	 */
	public List<String> deleteLastNRtqAccountByProductId(String productId,Long n)throws DataAccessException;
	/**
	 * 查询未分配的RTQ帐号
	 * 查询逻辑：在RtqAccount中存在而在RtqAccountAssignment中不存在
	 * @return
	 */
	public List<RtqAccount> findNotTakenUpAccount(String productId) throws DataAccessException;
	
	public RtqAccount findLastAccountId(String productId) throws DataAccessException;
	
	public Page listRtqAccountByProIdOrLoginId(String productId,String rtqLoginId,Integer pageNumber, Integer pageSize)throws DataAccessException;
	
	public void updateRtqAccountPassword(String productId, List<String> rtqLognIdUpdateList, String password) throws DataAccessException;
}
