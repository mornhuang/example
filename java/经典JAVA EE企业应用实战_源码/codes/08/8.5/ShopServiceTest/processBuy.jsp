<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ page import="java.util.*,org.crazyit.service.*,javax.naming.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.0 Transitional//EN">
<html>
<head>
<title>购买的物品列表</title>
</head>
<body>
<%
ShopService ss = (ShopService)session.getAttribute("ss");
if (ss == null)
{
	Context ctx = new InitialContext();
	//通过JNDI查找EJB的引用
	Object stub = ctx.lookup
		("ShopService#org.crazyit.service.ShopService");
	ss = (ShopService)stub;
	session.setAttribute("ss" , ss);
}
//获取上个页面的请求参数
String[] buys = request.getParameterValues("item");
//遍历数组的各元素
for (String item : buys)
{
	ss.addItem(item);
}
%>
您所购买的物品：<br/>
<%
//通过EJB获取购买的详细信息
Map<String , Integer> buyInfo = ss.showDetail();
//输出所有购买信息
for(String item : buyInfo.keySet())
{
	out.println(item + "的数量为：" 
		+ buyInfo.get(item)	+ "<br />");
}
%>
<hr/>
<a href="shop.jsp">再次购买</a>
</body>
</html>
