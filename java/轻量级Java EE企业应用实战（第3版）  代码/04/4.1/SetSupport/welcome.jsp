<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>转换成功</title>
</head>
<body>
	<s:property value="tip"/><br/>
	<s:property value="users"/>
<!-- 访问user集合属性里索引属性值为crazyit.org的元素的name属性-->
用户crazyit.org的用户名为：<s:property value="users('crazyit.org').name"/><br/>
<!-- 访问user集合属性里索引属性值为crazyit.org的元素的pass属性-->
用户crazyit.org的密码为：<s:property value="users('crazyit.org').pass"/><br/>
<!-- 访问user集合属性里索引属性值为b的元素的name属性-->
用户b的用户名为：<s:property value="users('b').name"/><br/>
<!-- 访问user集合属性里索引属性值为b的元素的pass属性-->
用户b的密码为：<s:property value="users('b').pass"/><br/>
生日为：<s:property value="birth"/><br/>
</body>
</html>
