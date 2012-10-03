package com.itsz.sht.service.mis;

import java.math.BigDecimal;

import com.taifook.framework.platform.persist.DBException;
import com.taifook.misgateway.TransactionResult;

public interface MisService {
	
	//
	public String getMisDayEndProcessingFlag() throws DBException; 
	
	public boolean updateMisDayEndProcessingFlag(String flag) throws DBException;
	
	public  BigDecimal getAccBalance(String acCode) throws DBException;
	
	public TransactionResult memoDebit(final String acCode, final BigDecimal money, final String memoCodeType, final String remark) throws DBException;

}
