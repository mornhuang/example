package lee;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ChatServlet extends HttpServlet
{
	public void service(HttpServletRequest request,
		HttpServletResponse response)throws IOException,ServletException
	{
		//设置使用GBK字符集来解析请求参数
		request.setCharacterEncoding("GBK");
		String msg = request.getParameter("chatMsg");
		if ( msg != null &&  !msg.equals(""))
		{
			//取得当前用户
			String user = (String)request.getSession(true)
				.getAttribute("user");
			//调用ChatService的addMsg来添加聊天消息
			ChatService.instance().addMsg(user , msg);
		}
		request.setAttribute("msg" , ChatService.instance().getMsg());
		//转发到聊天页面
		forward("/chat.jsp" , request , response);
	}

	//执行转发请求的方法
	private void forward(String url , HttpServletRequest request,
		HttpServletResponse response)throws ServletException,IOException
	{
		//获取RequestDispatcher对象
		RequestDispatcher rd = request.getRequestDispatcher(url);
		//执行转发
		rd.forward(request,response);
	}
}