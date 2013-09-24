package org.crazyit.hrsystem.dao;

import java.util.*; 

import org.crazyit.hrsystem.domain.*;

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
public interface AttendDao
{
	/**
	 * 根据标识属性来加载Attend实例
	 * @param id 需要加载的Attend实例的标识属性值
	 * @return 指定标识属性对应的Attend实例
	 */
	Attend get(Integer id);

	/**
	 * 持久化指定的Attend实例
	 * @param attend 需要被持久化的Attend实例
	 * @return Attend实例被持久化后的标识属性值
	 */
	Integer save(Attend attend);

	/**
	 * 修改指定的Attend实例
	 * @param attend 需要被修改的Attend实例
	 */
	void update(Attend attend);

	/**
	 * 删除指定的Attend实例
	 * @param attend 需要被删除的Attend实例
	 */
	void delete(Attend attend);

	/**
	 * 根据标识属性删除Attend实例
	 * @param id 需要被删除的Attend实例的标识属性值
	 */
	void delete(Integer id);

	/**
	 * 查询全部的Attend实例
	 * @return 数据库中全部的Attend实例
	 */
	List<Attend> findAll();

	/**
	 * 根据员工查询该员工的打卡记录
	 * @param emp 员工
	 * @return 该员工的全部出勤记录
	 */ 
	List<Attend> findByEmp(Employee emp);

	/**
	 * 根据员工、日期查询该员工的打卡记录集合
	 * @param emp 员工
	 * @param dutyDay  日期
	 * @return 该员工的某天的打卡记录集合
	 */
	List<Attend> findByEmpAndDutyDay(Employee emp 
		, String dutyDay);

	/**
	 * 根据员工、日期 、上下班查询该员工的打卡记录集合
	 * @param emp 员工
	 * @param dutyDay  日期
	 * @param isCome 是否上班
	 * @return 该员工的某天上班或下班的打卡记录
	 */
	Attend findByEmpAndDutyDayAndCome(Employee emp , 
		String dutyDay , boolean isCome);

	/**
	 * 查看员工前三天的非正常打卡
	 * @param emp 员工
	 * @return 该员工的前三天的非正常打卡
	 */
	List<Attend> findByEmpUnAttend(Employee emp
		, AttendType type);
}
