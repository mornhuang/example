package com.itsz.sht.service.msse;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.taifook.mtss.mss.webservice.ClientFundMemo;
import com.taifook.mtss.mss.webservice.ErrorInfoException;

public class MsseServiceImpl implements MsseService {

	@Override
	public BigDecimal getBalance(String accountStatementId, String ccyCode)
			throws RemoteException, ErrorInfoException, ServiceException {
		return MsseUtil.getInstance().getBalance(accountStatementId, ccyCode);
	}

	@Override
	public MsseResult memoDebit(ClientFundMemo clientFundMemo)
			throws RemoteException, ServiceException {
		return MsseUtil.getInstance().memoDebit(clientFundMemo);
	}

}
