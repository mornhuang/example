package lee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;
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
public class Executor implements Runnable
{  
	private AsyncContext actx = null;  
	public Executor(AsyncContext actx)
	{
		this.actx = actx;  
	} 
	public void run()
	{
		try
		{
			//等待5秒钟，以模拟业务方法的执行  
			Thread.sleep(5 * 1000); 
			ServletRequest request = actx.getRequest();
			List<String> books = new ArrayList<String>();
			books.add("疯狂Java讲义");
			books.add("经典Java EE企业应用实战");
			books.add("疯狂XML讲义");
			request.setAttribute("books" , books);
			actx.dispatch("/async.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();  
		}
	}
}