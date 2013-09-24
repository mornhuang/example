package org.crazyit.service;

import javax.ejb.*;
import javax.jws.*;
import javax.jws.soap.*;

import org.crazyit.model.*;
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
@Stateless
@WebService(name="person"
	, serviceName="personWS"
	, portName="personPort"
	, targetNamespace="http://www.crazyit.org")
@SOAPBinding(style=SOAPBinding.Style.RPC
	, use=SOAPBinding.Use.ENCODED)
public class PersonServiceBean
	implements PersonService
{
	//定制该Web Service操作的行为
	@WebMethod(operationName="sayHello"
		, action="http://www.crazyit.org/greet")
	public String hello(Person person)
	{
		return person.getName() + "，您好，您的年龄是："
			+ person.getAge()
			+ "，现在时间是：" + new java.util.Date();
	}
	//将该方法排除在Web Service之外
	@WebMethod(exclude=true)
	public long test()
	{
		return Math.round(Math.random() * 100);
	}
}
