package com.itsz.sht.dao.delegate;

import java.util.List;

import com.itsz.sht.dao.FundDepositDAO;
import com.itsz.sht.dao.hibernate.model.FundDeposit;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

/**
 * $Id: FundDepositDelegate.java,v 1.1 2011/01/17 01:56:27 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FundDepositDelegate.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-15
 */
public class FundDepositDelegate {
	private FundDepositDAO fundDepositDAO;

	public void setFundDepositDAO(FundDepositDAO fundDepositDAO) {
		this.fundDepositDAO = fundDepositDAO;
	}
	
    public void insertFundDeposit(FundDeposit newFundDeposit) throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
        	fundDepositDAO.insertFundDeposit(newFundDeposit);
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }
    
    public List<FundDeposit> findFundDeposit() throws Exception {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
        	return fundDepositDAO.findFundDeposit();
        }
        catch (DAOException de) {
            logger.error(de);
            throw new Exception();
        }
    }
    
    public void updateFundDeposit(List<FundDeposit> fundDeposits){
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
        	fundDepositDAO.updateFundDeposit(fundDeposits);
        }
        catch (DAOException de) {
            logger.error(de);
            try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }    	
    }
}
