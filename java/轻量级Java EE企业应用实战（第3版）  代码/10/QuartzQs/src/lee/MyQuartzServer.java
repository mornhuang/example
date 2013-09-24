package lee;

import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

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
public class MyQuartzServer
{
	public static void main(String[] args)
	{
		MyQuartzServer server = new MyQuartzServer();
		try
		{
			server.startScheduler();
		}
		catch (SchedulerException ex)
		{
			ex.printStackTrace();
		}
	}
	//执行调度
	private void startScheduler() throws SchedulerException 
	{
		//使用工厂创建调度器实例
		Scheduler scheduler = StdSchedulerFactory
			.getDefaultScheduler(); 
		//以作业创建JobDetail实例
		JobDetail jobDetail = new JobDetail("dd", 
			Scheduler.DEFAULT_GROUP , TestJob.class);
		//创建trigger，创建一个简单的调度器
		//指定该任务被重复调度50次，每次间隔2秒
		Trigger trigger = new SimpleTrigger("dd" , 
			Scheduler.DEFAULT_GROUP, 50, 20000) ;
		//调度器将作业与trigger关联起来
		scheduler.scheduleJob(jobDetail, trigger ); 
		//开始调度
		scheduler.start();
	}
}