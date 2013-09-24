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
@WebServlet(name="get-application", 
	urlPatterns={"/get-application"})
public class GetApplication extends HttpServlet
{
	public void service(HttpServletRequest request,
		HttpServletResponse response)throws IOException
	{
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>");
		out.println("测试application");
		out.println("</title></head><body>");		
		ServletContext sc = getServletConfig().getServletContext();
		out.print("application中当前的counter值为:");
		out.println(sc.getAttribute("counter"));
		out.println("</body></html>");
	}
}
