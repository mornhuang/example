<%-- 编译指定设置JSP页面的内容、字符集 --%>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<%
int id = Integer.parseInt(request.getParameter("id"));
System.out.println(id);
switch(id)
{
	case 1:
%>
上海$广州$北京
<%
	break;
	case 2:
%>
华盛顿$纽约$加州
<%
	break;
	case 3:
%>
东京$大板$福冈
<%
	break;
}
%>
