<%@ page contentType="text/html; charset=gb2312" language="java" %>
<!DOCtype HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>选择物品购买</title>
</head>
<body>
<form method="post" action="processBuy.jsp">
	书籍：<input type="checkbox" name="item" value="book"><br/>
	电脑：<input type="checkbox" name="item" value="computer"><br/>
	汽车：<input type="checkbox" name="item" value="car"><br/>
	<input type="submit" value="购买">
</form>
</body>
</html>
