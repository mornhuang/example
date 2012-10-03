package com.itsz.sht.service.memodebit;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.taifook.framework.platform.persist.DBException;
import com.taifook.mtss.mss.webservice.ErrorInfoException;

public interface MemoDebitService {

	/**
	 * 扣款方式
	 * @return MSSE|MIS|MANUAL手动方式 
	 */
	public String getMemoDebitSystem();
	
	
	/**
	 * 取得账户总余额
	 * @param acCode
	 * @return balance
	 * @throws DBException
	 */
	public BigDecimal getAccBalance(String acCode) throws DBException, RemoteException, ErrorInfoException, ServiceException;
	
	/**
	 * 检查账户是否足够支付
	 * @param acCode
	 * @param check
	 * @return　-1：不足支付　
	 *　                        0：不用检查　　　
	 *           1：余额足够　
	 * @throws DBException, RemoteException, ErrorInfoException, ServiceException
	 */
	public int balanceCheck(String acCode, long check) throws DBException, RemoteException, ErrorInfoException, ServiceException;
	
	/**
	 * 对客户账户进行扣款
	 * @param acCode
	 * @param money
	 * @param memoCodeType
	 * @param remark
	 * @param language
	 * @return object
	 * @throws DBException, RemoteException, ServiceException
	 */
	public MemoDebitResult memoDebit(final String acCode, final BigDecimal money, final String memoCodeType, final String remark, final String language) throws DBException, RemoteException, ServiceException;
	
}
