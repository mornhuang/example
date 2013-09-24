<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2010, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="javax.naming.*,javax.mail.*,javax.mail.internet.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 发送成功 </title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<%
final String TEST_JNDI = "testMail";
final String SEND_PROTOCOL = "smtp";
final String MAIL_SERVER = "smtp.sina.com";
final int MAIL_PORT = 25;
//初始化Context，使用InitialContext初始化Context
Context ctx = new InitialContext(); 
Session sess = (Session)ctx.lookup(TEST_JNDI);
//Properties props = new Properties();
//Session sess = Session.getDefaultInstance(props);
//获取smtp对应的Transport对象
Transport transport = sess.getTransport(SEND_PROTOCOL);
transport.connect(MAIL_SERVER , MAIL_PORT 
	, "spring_test" , "123abc");
request.setCharacterEncoding("GBK");
//获取邮件的收件人、标题、内容
String receiver = request.getParameter("receiver");
String title = request.getParameter("title");
String content = request.getParameter("content");
//构造MimeMessage并设置相关属性值
MimeMessage msg = new MimeMessage(sess);
//设置发件人
msg.setFrom(new InternetAddress("spring_test@sina.com"));
//设置收件人
InternetAddress[] addresses = {new InternetAddress(receiver)};
msg.setRecipients(Message.RecipientType.TO , addresses);
//设置邮件主题
msg.setSubject(title);    
//构造Multipart
Multipart mp = new MimeMultipart();
//向Multipart添加正文
MimeBodyPart mbpContent = new MimeBodyPart();
mbpContent.setText(content);
mp.addBodyPart(mbpContent);
//向Multipart添加MimeMessage
msg.setContent(mp);
//设置发送日期
msg.setSentDate(new java.util.Date());
//发送邮件
transport.sendMessage(msg, msg.getAllRecipients());
out.println("<h3>邮件发送成功</h3>");
%>
</body>
</html>