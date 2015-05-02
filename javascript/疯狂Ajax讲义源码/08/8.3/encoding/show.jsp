<%-- 指定服务器请求地址 --%>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%
////服务器页面获取用户的value请求参数，并将其在控制台输出
//System.out.println(request.getParameter("value"));
%>
<%
////设置服务器的解码所用的字符集
//request.setCharacterEncoding("UTF-8");
////输出请求参数值
//System.out.println(request.getParameter("value"));
%>
<%
//处理POST请求
if (request.getMethod().equalsIgnoreCase("POST"))
{
	request.setCharacterEncoding("UTF-8");
	System.out.println(request.getParameter("value"));
}
//处理GET请求
else if (request.getMethod().equalsIgnoreCase("GET"))
{
	String tmp = request.getParameter("value");
	String a = new String(tmp.getBytes("ISO-8859-1") , "UTF-8");
	System.out.println(a);
}
%>
