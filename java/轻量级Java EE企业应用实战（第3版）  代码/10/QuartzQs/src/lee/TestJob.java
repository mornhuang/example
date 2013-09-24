package lee;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.*;

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
public class TestJob
	implements Job
{
	//判断作业是否执行的旗标
	private boolean isRunning = false;
	public void execute(JobExecutionContext context) 
		throws JobExecutionException
	{
		//如果作业没有被调度
		if (!isRunning)
		{
			System.out.println(new Date() + "  作业被调度。");
			//循环100次来模拟任务的执行
			for (int i = 0; i < 100 ; i++)
			{
				System.out.println("作业完成" + (i + 1) + "%");
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException ex)
				{
					ex.printStackTrace();
				}
			}
			System.out.println(new Date() + "  作业调度结束。");
		}
		//如果作业正在运行，即使获得调度，也立即退出
		else
		{
			System.out.println(new Date() + "任务退出");
		}
	}
}
