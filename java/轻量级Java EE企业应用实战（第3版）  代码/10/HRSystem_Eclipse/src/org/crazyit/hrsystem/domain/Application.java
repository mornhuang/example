package org.crazyit.hrsystem.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class Application
	implements Serializable
{
	private static final long serialVersionUID = 48L;
	//代表标识属性
	private Integer id;
	//申请理由
	private String reason;
	//是否处理
	private boolean result;
	//关联的出勤
	private Attend attend;
	//希望将指定出勤改为的type类型
	private AttendType type;
	//申请的结果
	private CheckBack check;
	
	//无参数的构造器
	public Application()
	{
	}
	//初始化全部属性的构造器
	public Application(Integer id , String reason , 
		boolean result , Attend attend , 
		AttendType type , CheckBack check)
	{
		this.id = id;
		this.reason = reason;
		this.result = result;
		this.attend = attend;
		this.type = type;
		this.check = check;
	}

	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	//reason属性的setter和getter方法
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	public String getReason()
	{
		return this.reason;
	}

	//result属性的setter和getter方法
	public void setResult(boolean result)
	{
		this.result = result;
	}
	public boolean getResult()
	{
		return this.result;
	}

	//attend属性的setter和getter方法
	public void setAttend(Attend attend)
	{
		this.attend = attend;
	}
	public Attend getAttend()
	{
		return this.attend;
	}

	//type属性的setter和getter方法
	public void setType(AttendType type)
	{
		this.type = type;
	}
	public AttendType getType()
	{
		return this.type;
	}

	//check属性的setter和getter方法
	public void setCheck(CheckBack check)
	{
		this.check = check;
	}
	public CheckBack getCheck()
	{
		return this.check;
	}

}