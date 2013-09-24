package org.crazyit.hrsystem.dao.impl;

import java.util.*; 
import java.text.*; 

import org.crazyit.hrsystem.domain.*;
import org.crazyit.common.hibernate3.support.*;
import org.crazyit.hrsystem.dao.*;

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
public class AttendDaoHibernate
	extends YeekuHibernateDaoSupport
	implements AttendDao
{
	/**
	 * 根据标识属性来加载Attend实例
	 * @param id 需要加载的Attend实例的标识属性值
	 * @return 指定标识属性对应的Attend实例
	 */
	public Attend get(Integer id)
	{
		return getHibernateTemplate()
			.get(Attend.class , id);
	}

	/**
	 * 持久化指定的Attend实例
	 * @param attend 需要被持久化的Attend实例
	 * @return Attend实例被持久化后的标识属性值
	 */
	public Integer save(Attend attend)
	{
		return (Integer)getHibernateTemplate()
			.save(attend);
	}

	/**
	 * 修改指定的Attend实例
	 * @param attend 需要被修改的Attend实例
	 */
	public void update(Attend attend)
	{
		getHibernateTemplate()
			.update(attend);
	}

	/**
	 * 删除指定的Attend实例
	 * @param attend 需要被删除的Attend实例
	 */
	public void delete(Attend attend)
	{
		getHibernateTemplate()
			.delete(attend);
	}

	/**
	 * 根据标识属性删除Attend实例
	 * @param id 需要被删除的Attend实例的标识属性值
	 */
	public void delete(Integer id)
	{
		getHibernateTemplate()
			.delete(get(id));
	}

	/**
	 * 查询全部的Attend实例
	 * @return 数据库中全部的Attend实例
	 */
	public List<Attend> findAll()
	{
		return (List<Attend>)getHibernateTemplate()
			.find("from Attend");
	}

	/**
	 * 根据员工查询该员工的打卡记录
	 * @param emp 员工
	 * @return 该员工的全部出勤记录
	 */ 
	public List<Attend> findByEmp(Employee emp)
	{
		return (List<Attend>)getHibernateTemplate()
			.find("from Attend as a where a.employee=?" , emp);
	}

	/**
	 * 根据员工、日期查询该员工的打卡记录集合
	 * @param emp 员工
	 * @param dutyDay  日期
	 * @return 该员工的某天的打卡记录集合
	 */
	public List<Attend> findByEmpAndDutyDay(Employee emp 
		, String dutyDay)
	{
		return (List<Attend>)getHibernateTemplate()
			.find("from Attend as a where a.employee=? and "
			+ "a.dutyDay=?" , new Object[]{emp , dutyDay});
	}

	/**
	 * 根据员工、日期 、上下班查询该员工的打卡记录集合
	 * @param emp 员工
	 * @param dutyDay  日期
	 * @param isCome 是否上班
	 * @return 该员工的某天上班或下班的打卡记录
	 */
	public Attend findByEmpAndDutyDayAndCome(Employee emp , 
		String dutyDay , boolean isCome)
	{
		List<Attend> al = findByEmpAndDutyDay(emp , dutyDay);
		if (al != null || al.size() > 1)
		{
			for (Attend attend : al)
			{
				if (attend.getIsCome() == isCome )
				{
					return attend;
				}
			}
		}
		return null;
	}

	/**
	 * 查看员工前三天的非正常打卡
	 * @param emp 员工
	 * @return 该员工的前三天的非正常打卡
	 */
	public List<Attend> findByEmpUnAttend(Employee emp
		, AttendType type)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String end = sdf.format(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -3);
		String start = sdf.format(c.getTime());

		Object[] args = {emp , type , start , end};
		return (List<Attend>)getHibernateTemplate()
			.find("from Attend as a where a.employee=? and "
			+ "a.type != ? and a.dutyDay between ? and ?" , args);
	}
}
