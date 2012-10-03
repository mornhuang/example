package com.itsz.sht.dao.impl;

import java.util.List;

import com.itsz.sht.dao.FundDepositDAO;
import com.itsz.sht.dao.hibernate.model.FundDeposit;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;

/**
 * $Id: FundDepositDAOImpl.java,v 1.1 2011/01/17 01:56:27 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FundDepositDAOImpl.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-15
 */
public class FundDepositDAOImpl extends BaseDao<FundDeposit> implements FundDepositDAO {
    public FundDepositDAOImpl() {
		super(FundDeposit.class);
	}

	public void insertFundDeposit(FundDeposit newFundDeposit) throws DAOException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            this.save(newFundDeposit);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }
	
	public List<FundDeposit> findFundDeposit() throws DAOException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
            return (List<FundDeposit>) getHibernateTemplate().find("from FundDeposit as fundDeposit where fundDeposit.isSended<>'Y' order by fundDeposit.receiveDate desc");
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }
	
	public void updateFundDeposit(List<FundDeposit> fundDeposits) throws DAOException {
        WebLogger logger = WebLoggerFactory.instance().getLogger(this);
        try {
        	this.saveOrUpdateBatch(fundDeposits);
        }
        catch (Exception e) {
            logger.error("Exception " + e.getMessage());
            throw new DAOException("Exception " + e.getMessage(), e);
        }
    }
}
