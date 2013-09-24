package lee;

import javax.servlet.*;
import javax.servlet.http.*;

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
public class MyAsyncListener
	implements AsyncListener
{
	public void onComplete(AsyncEvent event)
		throws IOException
	{
		System.out.println("------异步调用完成------" + new Date());
	}
	public void onError(AsyncEvent event)
		throws IOException
	{}
	public void onStartAsync(AsyncEvent event) 
		throws IOException
	{
		System.out.println("------异步调用开始------" + new Date());
	}
	public void onTimeout(AsyncEvent event) 
		throws IOException
	{}
}
