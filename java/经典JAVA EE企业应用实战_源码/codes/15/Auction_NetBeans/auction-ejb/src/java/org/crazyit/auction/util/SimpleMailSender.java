package org.crazyit.auction.util;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SimpleMailSender
{
	//SMTP服务器地址
	private static final String SMTP_SERVER 
		= "smtp.sina.com"; 
	//登录SMTP服务器的用户名用
	private static final String SMTP_USER 
		= "spring_test";
	//登录SMTP服务器的密码
	private static final String SMTP_PASS 
		= "123abc";
	//收件人邮箱地址
	private String to; 
	//发件人邮箱地址
	private String from; 
	//邮件主题
	private String subject; 
	//邮件正文
	private String content; 
	//无参数的构造器
	public SimpleMailSender()
	{
	}
	/**
	 * 初始化各属性的构造器
	 * @param to 指定收件人地址.
	 * @param from 指定发件人地址.
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 */
	public SimpleMailSender(String to , String from  
		, String subject , String content)
	{ 
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.content = content;
	}
	//to属性的setter方法
	public void setTo(String to)
	{
		this.to = to;
	}
	//from属性的setter方法
	public void setFrom(String from)
	{
		this.from = from;
	}
	//subject属性的setter方法
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	//content属性的setter方法
	public void setContent(String content)
	{
		this.content = content;
	}
	//把邮件主题转换为中文
	public String transferChinese(String strText)
	{
		try
		{
			strText = MimeUtility.encodeText(
				new String(strText.getBytes()
				, "GB2312"), "GB2312", "B");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strText;
	}
	//发送邮件
	public boolean send()
	{
		//创建邮件Session所需的Properties对象
		Properties props = new Properties();
		props.put("mail.smtp.host" , SMTP_SERVER);
		props.put("mail.smtp.auth" , "true");
		//创建Session对象
		Session session = Session.getDefaultInstance(props
			//以匿名内部类的形式创建登录服务器的认证对象
			, new Authenticator()
			{
				public PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(SMTP_USER , SMTP_PASS); 
				}
			});
		try
		{
			//构造MimeMessage并设置相关属性值
			MimeMessage msg = new MimeMessage(session);
			//设置发件人
			msg.setFrom(new InternetAddress(from));
			//设置收件人
			InternetAddress[] addresses = {new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO , addresses);
			//设置邮件主题
			subject = transferChinese(subject);
			msg.setSubject(subject);	
			//构造Multipart
			Multipart mp = new MimeMultipart();
			//向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setText(content);
			//向MimeMessage添加
			mp.addBodyPart(mbpContent);
			//向Multipart添加MimeMessage
			msg.setContent(mp);
			//设置发送日期
			msg.setSentDate(new Date());
			//发送邮件
			Transport.send(msg);
		}
		catch (MessagingException mex)
		{
			mex.printStackTrace();
			return false;
		}
		return true;
	}
}