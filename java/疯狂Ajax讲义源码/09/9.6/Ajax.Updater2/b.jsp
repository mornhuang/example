<%--
网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>
<%@ page contentType="text/html; charset=GBK" language="java" %>
<script type="text/javascript">
hi = function()
{
	alert("服务器按钮激发的单击事件");
}
</script>
<!-- 服务器生成一个按钮 -->
<input type="button" value="服务器按钮" onclick="hi();"/>