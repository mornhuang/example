<%@ page contentType="text/html; charset=GBK" language="java" %>
<%
//从服务器获取id参数
int id = Integer.parseInt(request.getParameter("id"));
//根据id的值，确定需要返回给客户端的信息，返回客户端的城市信息以$符号隔开
switch(id)
{
	case 1:
%>
上海$广州$北京
<%
	break;
	case 2:
%>
华盛顿$纽约$加洲
<%
	break;
	case 3:
%>
东京$大板$福冈
<%
	break;
}
%>
