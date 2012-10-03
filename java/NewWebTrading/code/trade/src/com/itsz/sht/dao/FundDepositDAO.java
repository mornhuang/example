package com.itsz.sht.dao;

import java.util.List;

import com.itsz.sht.dao.hibernate.model.FundDeposit;
import com.taifook.mtss.web.common.exception.dao.DAOException;

/**
 * $Id: FundDepositDAO.java,v 1.1 2011/01/17 01:56:27 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FundDepositDAO.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-15
 */
public interface FundDepositDAO {
	public void insertFundDeposit(FundDeposit newFundDeposit) throws DAOException;
	public List<FundDeposit> findFundDeposit() throws DAOException;
	public void updateFundDeposit(List<FundDeposit> fundDeposits) throws DAOException;
}
