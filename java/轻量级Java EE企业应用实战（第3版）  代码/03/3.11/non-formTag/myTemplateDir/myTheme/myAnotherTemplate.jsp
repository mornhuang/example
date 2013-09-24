<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2012, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div style="background-color:#bbbbbb;">
JSP自定义模板<br/>
请选择您喜欢的图书<br/>
<select>
<s:iterator value="%{top.parameters.list}">
	<option><s:property/></option>
</s:iterator>
</select>
</div>