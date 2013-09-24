package org.crazyit.service;

import javax.ejb.*;
import javax.annotation.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Stateless(mappedName="TimerEJB")
public class TimerEJBBean
	implements TimerEJB
{
	//定义设置定时器的方法
	@Resource
	TimerService timerService;
	public void setTime(java.util.Date init 
		, long interval)
	{
		//启动一个每隔interval时间就会启动定时器
		timerService.createTimer(init , interval
			, "新的定时器！");
	}
	//定义定时执行的方法
	@Timeout
	public void check(Timer timer)
	{
		System.out.println("定时器信息："
			+ timer.getInfo());
		System.out.println("模拟系统检查！");
	}
}
