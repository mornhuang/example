package com.itsz.sht.service.mis;

import java.math.BigDecimal;

import com.taifook.framework.platform.persist.DBException;
import com.taifook.misgateway.TradingAccount;
import com.taifook.misgateway.TransactionResult;



public  class MisServiceImpl implements MisService {

	private static MisServiceImpl instance ;
	
	
	private static synchronized void createInstance() {
        if (instance == null)
            instance = new MisServiceImpl();
    }
	
	public static MisServiceImpl getInstance () {
		if (instance == null)
            createInstance();
        return instance;
	}
	
	private MisServiceImpl() {		
		
	}
	
    public static void main(String[] args) {
    //  System.out.println(getCustcodeFrmAc(new TradingAccount("02-0071234-00")));
    	System.out.println(MisServiceImpl.getInstance().getMisDayEndProcessingFlag());
    	
    //	BigDecimal  aa=	MisServiceImpl.getInstance().getAccBalance("0077444");
    //	System.out.println("aa==="+aa); 
    }



	@Override
	public String getMisDayEndProcessingFlag() {
		return MISUtil.getInstance().getMisDayEndProcessingFlag();
	}



	@Override
	public BigDecimal getAccBalance(String acCode) throws DBException {
		
		return MISUtil.getInstance().getAccBalance(acCode);
	}



	@Override
	public TransactionResult memoDebit(String acCode, BigDecimal money,
			String memoCodeType, String remark) throws DBException {
		return MISUtil.getInstance().memoDebit(acCode, money, memoCodeType, remark);
	}

	@Override
	public boolean updateMisDayEndProcessingFlag(String flag)
			throws DBException {
		try {
			 MISUtil.getInstance().updateMisDayEndProcessingFlag(flag);
			 return true;
		} catch (Exception e) {
			 return false;
		}
	}



}
