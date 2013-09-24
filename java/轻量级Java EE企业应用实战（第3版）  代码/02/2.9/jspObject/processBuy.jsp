<%@ page contentType="text/html; charset=gb2312" language="java" %>
<%@ page import="java.util.*"%>
<%
//取出session范围的itemMap属性
Map<String,Integer> itemMap = (Map<String,Integer>)session
	.getAttribute("itemMap");
//如果Map对象为空，则初始化Map对象
if (itemMap == null)
{
	itemMap = new HashMap<String,Integer>();
	itemMap.put("书籍" , 0);
	itemMap.put("电脑" , 0);
	itemMap.put("汽车" , 0);
}
//获取上个页面的请求参数
String[] buys = request.getParameterValues("item");
//遍历数组的各元素
for (String item : buys)
{
	//如果item为book，表示选择购买书籍
	if(item.equals("book"))
	{
		int num1 = itemMap.get("书籍").intValue();
		//将书籍key对应的数量加1
		itemMap.put("书籍" , num1 + 1);
	}
	//如果item为computer，表示选择购买电脑
	else if (item.equals("computer"))
	{
		int num2 = itemMap.get("电脑").intValue();
		//将电脑key对应的数量加1
		itemMap.put("电脑" , num2 + 1);
	}
	//如果item为car，表示选择购买汽车
	else if (item.equals("car"))
	{
		int num3 = itemMap.get("汽车").intValue();
		//将汽车key对应的数量加1
		itemMap.put("汽车" , num3 + 1);
	}
}
//将itemMap对象放到设置成session范围的itemMap属性
session.setAttribute("itemMap" , itemMap);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 购买的物品列表 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
您所购买的物品：<br/>
书籍：<%=itemMap.get("书籍")%>本<br/>
电脑：<%=itemMap.get("电脑")%>台<br/>
汽车：<%=itemMap.get("汽车")%>辆
<p><a href="shop.jsp">再次购买</a></p>
</body>
</html>
