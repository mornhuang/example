<%@ tag pageEncoding="GBK" import="java.util.List"%>
<!-- 定义了四个标签属性 -->
<%@ attribute name="bgColor" %>
<%@ attribute name="cellColor" %>
<%@ attribute name="title" %>
<%@ attribute name="bean" %>
<table border="1" bgcolor="${bgColor}">
<tr>
	<td><b>${title}</b></td>
</tr>
<%List<String> list = (List<String>)
	request.getAttribute("a");
//遍历输出list集合的元素
for (Object ele : list){%>
	<tr>
		<td bgcolor="${cellColor}">
		<%=ele%>
		</td>
	</tr>
<%}%>
</table>