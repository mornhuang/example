package lee;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

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
		//设置使用UTF-8字符集来解析请求参数
		//XMLHttpRequest所发送的POST请求默认采用UTF-8字符集
        request.setCharacterEncoding("UTF-8");
		String msg = request.getParameter("chatMsg");
		if ( msg != null &&  !msg.equals(""))
		{
			//取得当前用户
			String user = (String)request.getSession(true)
				.getAttribute("user");
			//调用ChatService的addMsg来添加聊天消息
			ChatService.instance().addMsg(user , msg);
		}
		//设置响应内容的类型
		response.setContentType("text/html;charset=GBK");
		//获取页面输出流
		PrintWriter out = response.getWriter();
		//直接生成响应
		out.println(ChatService.instance().getMsg());
	}
}