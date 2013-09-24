package org.crazyit.hrsystem.schedule;

import java.util.Date;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.crazyit.hrsystem.service.EmpManager;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class PayJob
	extends QuartzJobBean 
{
	//判断作业是否执行的旗标
	private boolean isRunning = false;
	//该作业类所依赖的业务逻辑组件
	private EmpManager empMgr;
	public void setEmpMgr(EmpManager empMgr)
	{
		this.empMgr = empMgr;
	}
	//定义任务执行体
	public void executeInternal(JobExecutionContext ctx) 
		throws JobExecutionException 
	{
		if (!isRunning)
		{
			System.out.println("开始调度自动结算工资");
			isRunning = true;
			//调用业务逻辑方法
			empMgr.autoPay();
			isRunning = false;
		}
	}
}