package lee;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;import java.util.*;/**
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
public class OnlineListener 
	implements HttpSessionListener
{
	//当用户与服务器之间开始session时触发该方法
	public void sessionCreated(HttpSessionEvent se) 
	{
		HttpSession session = se.getSession();
		ServletContext application = session.getServletContext();
		//获取session ID
		String sessionId = session.getId();
		//如果是一次新的会话
		if (session.isNew())
		{
			String user = (String)session.getAttribute("user");
			//未登录用户当游客处理
			user = (user == null) ? "游客" : user;
			Map<String , String> online = (Map<String , String>)
				application.getAttribute("online");
			if (online == null)
			{
				online = new Hashtable<String , String>();
			}
			//将用户在线信息放入Map中
			online.put(sessionId , user);
			application.setAttribute("online" , online);
		}
	}
	//当用户与服务器之间session断开时触发该方法
	public void sessionDestroyed(HttpSessionEvent se)
	{
		HttpSession session = se.getSession();
		ServletContext application = session.getServletContext();
		String sessionId = session.getId();
		Map<String , String> online = (Map<String , String>)
			application.getAttribute("online");
		if (online != null)
		{
			//删除该用户的在线信息
			online.remove(sessionId);
		}
		application.setAttribute("online" , online);
	}
}
