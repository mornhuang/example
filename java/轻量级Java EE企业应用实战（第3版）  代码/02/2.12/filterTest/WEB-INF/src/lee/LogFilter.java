package lee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;

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

@WebFilter(filterName="log"
	,urlPatterns={"/*"})
public class LogFilter implements Filter 
{
	//FilterConfig可用于访问Filter的配置信息
	private FilterConfig config;
	//实现初始化方法
	public void init(FilterConfig config)
	{
		this.config = config; 
	}
	//实现销毁方法
	public void destroy()
	{
		this.config = null; 
	}
	//执行过滤的核心方法
	public void doFilter(ServletRequest request,
		ServletResponse response, FilterChain chain)
		throws IOException,ServletException
	{
		//---------下面代码用于对用户请求执行预处理---------
		//获取ServletContext对象，用于记录日志
		ServletContext context = this.config.getServletContext(); 
		long before = System.currentTimeMillis();
		System.out.println("开始过滤...");
		//将请求转换成HttpServletRequest请求
		HttpServletRequest hrequest = (HttpServletRequest)request;
		//输出提示信息
		System.out.println("Filter已经截获到用户的请求的地址： " + 
			hrequest.getServletPath());
		//Filter只是链式处理，请求依然放行到目的地址
		chain.doFilter(request, response);
		//---------下面代码用于对服务器响应执行后处理---------
		long after = System.currentTimeMillis();
		//输出提示信息
		System.out.println("过滤结束");
		//输出提示信息
		System.out.println("请求被定位到" + hrequest.getRequestURI() + 
			"   所花的时间为: " + (after - before)); 
	} 
}