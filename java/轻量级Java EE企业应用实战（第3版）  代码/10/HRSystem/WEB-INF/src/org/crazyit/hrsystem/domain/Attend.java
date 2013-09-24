package org.crazyit.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;

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
public class Attend
	implements Serializable
{
	private static final long serialVersionUID = 48L;

	//代表标识属性
	private Integer id;
	//出勤日期
	private String dutyDay;
	//打卡时间
	private Date punchTime;
	//代表本次打卡是否为上班打卡
	private boolean isCome;
	//本次出勤的类型
	private AttendType type;
	//本次出勤关联的员工
	private Employee employee;

	//无参数的构造器
	public Attend()
	{
	}
	//初始化全部属性的构造器
	public Attend(Integer id , String dutyDay ,
		Date punchTime , boolean isCome ,
		AttendType type , Employee employee)
	{
		this.id = id;
		this.dutyDay = dutyDay;
		this.punchTime = punchTime;
		this.isCome = isCome;
		this.type = type;
		this.employee = employee;
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

	//dutyDay属性的setter和getter方法
	public void setDutyDay(String dutyDay)
	{
		this.dutyDay = dutyDay;
	}
	public String getDutyDay()
	{
		return this.dutyDay;
	}

	//punchTime属性的setter和getter方法
	public void setPunchTime(Date punchTime)
	{
		this.punchTime = punchTime;
	}
	public Date getPunchTime()
	{
		return this.punchTime;
	}

	//isCome属性的setter和getter方法
	public void setIsCome(boolean isCome)
	{
		this.isCome = isCome;
	}
	public boolean getIsCome()
	{
		return this.isCome;
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

	//employee属性的setter和getter方法
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}
	public Employee getEmployee()
	{
		return this.employee;
	}

	//根据employee、isCome、dutyDay来重写equals方法
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null &&
			obj.getClass() == Attend.class)
		{
			Attend attend = (Attend)obj;
			return getEmployee().equals(attend.getEmployee())
				&& getDutyDay().equals(attend.getDutyDay())
				&& getIsCome() == attend.getIsCome();
		}
		return false;
	}
	//根据employee、isCome、dutyDay来重写hashCode()方法
	public int hashCode ()
	{
		if (getIsCome())
		{
			return dutyDay.hashCode() + 
				29 * employee.hashCode() + 1;
		}
		return dutyDay.hashCode() + 
			29 * employee.hashCode();
	}
}