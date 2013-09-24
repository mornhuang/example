//JSP页面经过Tomcat编译后默认的包
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
//继承HttpJspBase类，该类其实是Servlet的子类
public final class test_jsp extends org.apache.jasper.runtime.HttpJspBase
	implements org.apache.jasper.runtime.JspSourceDependent {
		
	private static final JspFactory _jspxFactory 
		= JspFactory.getDefaultFactory();
	private static java.util.List<String> _jspx_dependants;	
	private javax.el.ExpressionFactory _el_expressionfactory;
	private org.apache.tomcat.InstanceManager _jsp_instancemanager;
	
	public java.util.List<String> getDependants() {
		return _jspx_dependants;
	}
	
	public void _jspInit() {
		_el_expressionfactory = _jspxFactory.getJspApplicationContext
			(getServletConfig().getServletContext()).getExpressionFactory();
		_jsp_instancemanager = org.apache.jasper.runtime
			.InstanceManagerFactory
			.getInstanceManager(getServletConfig());
	}	public void _jspDestroy() {
	}
	//用于响应用户请求的方法
	public void _jspService(HttpServletRequest request
		, HttpServletResponse response)
		throws java.io.IOException, ServletException {
		PageContext pageContext = null;
		HttpSession session = null;
		ServletContext application = null;
		ServletConfig config = null;
		JspWriter out = null;
		Object page = this;
		JspWriter _jspx_out = null;
		PageContext _jspx_page_context = null;
		try {
			response.setContentType("text/html; charset=GBK");
			pageContext = _jspxFactory.getPageContext(this
				, request, response,
				"", true, 8192, true);
			_jspx_page_context = pageContext;
			application = pageContext.getServletContext();
			config = pageContext.getServletConfig();
			session = pageContext.getSession();
			out = pageContext.getOut();
			_jspx_out = out;
			
			out.write("\r\n");
			out.write("\r\n");
			out.write("\r\n");
			out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\r\n");
			out.write("\t\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
			out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
			out.write("<head>\r\n");
			out.write("\t<title> 第二个JSP页面 </title>\r\n");
			out.write("\t<meta name=\"website\" content=\"http://www.crazyit.org\" />\r\n");
			out.write("</head>\r\n");
			out.write("<body>\r\n");
			out.write("<!-- 下面是Java脚本 -->\r\n");
			for(int i = 0 ; i < 7; i++)
			{
			out.println("<font size='" + i + "'>");	
			
			out.write("\r\n");
			out.write("疯狂Java训练营(Wild Java Camp)</font>\r\n");
			out.write("<br/>\r\n");
			}
			out.write("\r\n");
			out.write("</body>\r\n");
			out.write("</html>");
		} catch (Throwable t) {
			if (!(t instanceof SkipPageException)){
				out = _jspx_out;
				if (out != null && out.getBufferSize() != 0)
				try {
					
					out.clearBuffer(); 
				}
				catch (java.io.IOException e) {}
				if (_jspx_page_context != null)
					_jspx_page_context.handlePageException(t);
			}
		} finally {
			_jspxFactory.releasePageContext(_jspx_page_context);
		}
	}
}
