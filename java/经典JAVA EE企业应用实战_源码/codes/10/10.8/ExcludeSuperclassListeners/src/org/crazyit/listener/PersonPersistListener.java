package org.crazyit.listener;

import javax.persistence.*;

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
public class PersonPersistListener
{
	@PrePersist
	public void prePersist(Object entity)
	{
		if (entity instanceof Person)
		{
			System.out.println("--保存之前--");
			Person person = (Person)entity;
			//简单加密
			person.setEmail(reverse(person.getEmail()));
		}
	}
	@PreUpdate
	public void preUpdate(Object entity)
	{
		if (entity instanceof Person)
		{
			System.out.println("--更新之前--");
			Person person = (Person)entity;
			//简单加密
			person.setEmail(reverse(person.getEmail()));
		}
	}
	@PostUpdate
	public void postUpdate(Object entity)
	{
		System.out.println("--更新之后--");
	}
	@PostLoad
	public void postLoad(Object entity)
	{
		if (entity instanceof Person)
		{
			System.out.println("--加载之后--");
			Person person = (Person)entity;
			//简单加密
			person.setEmail(reverse(person.getEmail()));
		}
	}
	//提供一个方法用于对字符串进行反转
	private String reverse(String raw)
	{
		StringBuffer sb = new StringBuffer(raw);
		return sb.reverse().toString();
	}
}
