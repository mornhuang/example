<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.util.Random"%>
<%
//创建伪随机器，以系统时间作为伪随机器的种子
Random rand = new Random(System.currentTimeMillis());
//生成三个伪随机数字，并以$符号隔开后发送到客户端
out.println(rand.nextInt(10) + "$" + rand.nextInt(10)
	+ "$" + rand.nextInt(10));
%>
