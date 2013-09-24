<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ page import="java.util.*,org.crazyit.service.*,javax.naming.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.0 Transitional//EN">
<html>
<head>
<title>删除EJB</title>
</head>
<body>
<%
Lifecycle ss = (Lifecycle)session.getAttribute("ss");
if (ss == null)
{
	Context ctx = new InitialContext();
	//通过JNDI查找EJB的引用
	Object stub = ctx.lookup
		("Lifecycle#org.crazyit.service.Lifecycle");
	ss = (Lifecycle)stub;
	session.setAttribute("ss" , ss);
}
ss.remove();
%>
<hr/>
<h3>EJB被删除！</h3>
</body>
</html>
