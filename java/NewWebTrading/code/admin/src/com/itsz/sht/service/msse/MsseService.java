package com.itsz.sht.service.msse;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.taifook.mtss.mss.webservice.ClientFundMemo;
import com.taifook.mtss.mss.webservice.ErrorInfoException;

public interface MsseService {
	
	public BigDecimal getBalance(String accountStatementId, String ccyCode) throws RemoteException, ErrorInfoException, ServiceException;
	
	public MsseResult memoDebit(ClientFundMemo clientFundMemo) throws RemoteException, ServiceException ;
}
