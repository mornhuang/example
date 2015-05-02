<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="java.io.*,org.dom4j.*,org.dom4j.io.XPPReader,java.util.*"%>
<%
//定义一个StringBuffer对象，用于接收请求参数
StringBuffer xmlBuffer = new StringBuffer();
String line = null;
//通过request对象获取输入流
BufferedReader reader = request.getReader();
//依次读取请求输入流的数据
while((line = reader.readLine()) != null ) 
{
    xmlBuffer.append(line);
}
//将从输入流中读取到的内容转换为字符串
String xml = xmlBuffer.toString();
//以Dom4J开始解析XML字串串
Document xmlDoc = new XPPReader().read( 
	new ByteArrayInputStream(xml.getBytes()));
//获得countrys节点的所有子节点
List countryList = xmlDoc.getRootElement().elements();
//定义服务器响应的结果
String result = "";
//遍历countrys节点的所有子节点
for(Iterator it = countryList.iterator(); it.hasNext();)
{
	Element country = (Element)it.next();
	//如果发送的该节点的值为1，表明选中了中国
	if (country.getText().equals("1"))
	{
		result += "上海$广州$北京";
	}
	//如果发送的该节点的值为1，表明选中了美国
	else if(country.getText().equals("2"))
	{
		result += "$华盛顿$纽约$加洲";
	}
	//如果发送的该节点的值为1，表明选中了日本
	else if(country.getText().equals("3"))
	{
		result += "$东京$大板$福冈";
	}
}
//向客户端发送响应
out.println(result);
%>