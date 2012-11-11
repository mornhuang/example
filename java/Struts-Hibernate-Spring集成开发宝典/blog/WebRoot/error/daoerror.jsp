<%@ page contentType="text/html;charset=gb2312" %> 
<%@ page isErrorPage="true" %>

<html>
<head>
<title>错误信息!</title>
<meta http-equiv="content-type" content="text/html;charset=gb2312">
<link rel="stylesheet" href="css/common.css">
<script language="Javascript" src="pub/js/common.js"></script>
</head>
<body>
<center>
	<h1>错误信息！</h1>
	<br>
	<table width="500">
		<%
			if( exception!=null )
			{
		%>
		<tr>
			<td colspan="2" class="warnning">
				<%=exception.getMessage()%>
			</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td align="center" colspan="2">
				&nbsp;<p>
				<input type="button" name="register" value="返回" 
					onclick="javascript:history.go(-1);">
			</td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<img height="200" src="img/welcome.gif">
			</td>
		</tr>
	</table>
</center>
</body>
</html>
