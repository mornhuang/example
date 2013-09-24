<%
  session.invalidate();
%>
<%@ page contentType="text/html; charset=gb2312" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <title>系统登出</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
<%@include file="header.jsp"%>
<table width="780" border="0" align="center"
	background="${pageContext.request.contextPath}/images/bodybg.jpg">
  <tr>
	<td height="264"><div align="center"><font color="#336666" size="3"><b>您已经成功地登出系统，欢迎下次光临</b></font></div></td>
  </tr>
</table>
<%@include file="footer.jsp"%>
</body>
</html>


