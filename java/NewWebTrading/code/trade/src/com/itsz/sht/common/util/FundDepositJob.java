package com.itsz.sht.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.common.model.request.FundDepositRequestModel;
import com.itsz.sht.service.Facade;

/**
 * $Id: FundDepositJob.java,v 1.3 2011/02/17 03:51:20 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FundDepositJob.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-15
 */
public class FundDepositJob extends QuartzJobBean
{
	private Log log=LogFactory.getLog(FundDepositJob.class);
	private Facade facade = (Facade) ServiceLocator.getInstance().getService("facade");
    public static void main(String[] args) {
    	try {
			new FundDepositJob().executeInternal(null);
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}
    
	@Override
	protected void executeInternal(JobExecutionContext context)throws JobExecutionException 
	{
		log.info("Enquiry FundDepositJob Scheduler is run at "+ new java.util.Date());
		FundDepositRequestModel fundDepositRequestModel = new FundDepositRequestModel();
		fundDepositRequestModel.setChannelId(Consts.Channel.Id.NWEB);
		fundDepositRequestModel.setChannelType(Consts.Channel.NWEB);
		facade.transFundDeposit(fundDepositRequestModel);
		log.info("Enquiry FundDepositJob Scheduler is finish at "+ new java.util.Date());
	}
}
