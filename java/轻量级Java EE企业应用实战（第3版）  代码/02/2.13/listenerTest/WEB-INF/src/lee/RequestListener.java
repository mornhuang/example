package lee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

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
 
@WebListener
public class RequestListener
	implements ServletRequestListener , ServletRequestAttributeListener
{
	//当用户请求到达、被初始化时触发该方法
	public void requestInitialized(ServletRequestEvent sre)  
	{
		HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
		System.out.println("----发向" + request.getRequestURI()
			+ "请求被初始化----");	}
	//当用户请求结束、被销毁时触发该方法
	public void requestDestroyed(ServletRequestEvent sre) 
	{
		HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
		System.out.println("----发向" + request.getRequestURI()
			+ "请求被销毁----");
	}
	//当程序向request范围添加属性时触发该方法
	public void attributeAdded(ServletRequestAttributeEvent event) 
	{
		ServletRequest request = event.getServletRequest();
		//获取添加的属性名和属性值
		String name = event.getName();
		Object value = event.getValue();
		System.out.println(request + "范围内添加了名为"
			+ name + "，值为" + value + "的属性!");
	}
	//当程序从request范围删除属性时触发该方法
	public void attributeRemoved(ServletRequestAttributeEvent event)
	{
		ServletRequest request = event.getServletRequest();
		//获取被删除的属性名和属性值
		String name = event.getName();
		Object value = event.getValue();
		System.out.println(request + "范围内名为"
			+ name + "，值为" + value + "的属性被删除了!");
	}
	//当request范围的属性被替换时触发该方法
	public void attributeReplaced(ServletRequestAttributeEvent event)
	{
		ServletRequest request = event.getServletRequest();
		//获取被替换的属性名和属性值
		String name = event.getName();
		Object value = event.getValue();
		System.out.println(request + "范围内名为"
			+ name + "，值为" + value + "的属性被替换了!");
	}
}
