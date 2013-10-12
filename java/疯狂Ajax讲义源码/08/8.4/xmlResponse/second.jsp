<%@ page contentType="text/xml; charset=GBK"%>
<%
//设置生成响应的编码方式
response.setContentType("text/xml; charset=UTF-8");
response.setHeader("Cache-Control","no-cache");
//输出XML文档的根元素
out.println("<citylist>");
int id = Integer.parseInt(request.getParameter("id"));
//对不同的参数，生成不同的XML文档
switch(id)
{
	case 1:
%>
<city>上海</city>
<city>广州</city>
<city>北京</city>
<%
	break;
	case 2:
%>
<city>华盛顿</city>
<city>纽约</city>
<city>加洲</city>
<%
	break;
	case 3:
%>
<city>东京</city>
<city>大阪</city>
<city>福冈</city>
<%
	break;
}
%>
</citylist>
