package org.crazyit.hrsystem.service.impl;

import org.crazyit.hrsystem.dao.*;
import org.crazyit.hrsystem.domain.*;
import org.crazyit.hrsystem.vo.*;
import org.crazyit.hrsystem.exception.*;
import org.crazyit.hrsystem.service.*;

import java.text.*;
import java.util.*;

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
public class EmpManagerImpl
	implements EmpManager
{
	private ApplicationDao appDao;
	private AttendDao attendDao;
	private AttendTypeDao typeDao;
	private CheckBackDao checkDao;
	private EmployeeDao empDao;
	private ManagerDao mgrDao;
	private PaymentDao payDao;

	public void setAppDao(ApplicationDao appDao)
	{
		this.appDao = appDao;
	}

	public void setAttendDao(AttendDao attendDao)
	{
		this.attendDao = attendDao;
	}

	public void setTypeDao(AttendTypeDao typeDao)
	{
		this.typeDao = typeDao;
	}

	public void setCheckDao(CheckBackDao checkDao)
	{
		this.checkDao = checkDao;
	}

	public void setEmpDao(EmployeeDao empDao)
	{
		this.empDao = empDao;
	}

	public void setMgrDao(ManagerDao mgrDao)
	{
		this.mgrDao = mgrDao;
	}

	public void setPayDao(PaymentDao payDao)
	{
		this.payDao = payDao;
	}

	/**
	 * 以经理身份来验证登录
	 * @param mgr 登录的经理身份
	 * @return 登录后的身份确认:0为登录失败，1为登录emp 2为登录mgr
	 */
	public int validLogin(Manager mgr)
	{
		//如果找到一个经理，以经理登录
		if (mgrDao.findByNameAndPass(mgr).size()
			>= 1)
		{
			return LOGIN_MGR;
		}
		//如果找到普通员工，以普通员工登录
		else if (empDao.findByNameAndPass(mgr)
			.size() >= 1)
		{
			return LOGIN_EMP;
		}
		else
		{
			return LOGIN_FAIL;
		}
	}

	/**
	 * 自动打卡，周一到周五，早上7：00为每个员工插入旷工记录
	 */
	public void autoPunch()
	{
		System.out.println("自动插入旷工记录");
		List<Employee> emps = empDao.findAll();
		//获取当前时间
		String dutyDay = new java.sql.Date(
			System.currentTimeMillis()).toString();
		for (Employee e : emps)
		{
			//获取旷工对应的出勤类型
			AttendType atype = typeDao.get(6);
			Attend a = new Attend();
			a.setDutyDay(dutyDay);
			a.setType(atype);
			//如果当前时间是是早上，对应于上班打卡
			if (Calendar.getInstance()
				.get(Calendar.HOUR_OF_DAY) < AM_LIMIT)
			{
				//上班打卡
				a.setIsCome(true);
			}
			else
			{
				//下班打卡
				a.setIsCome(false);
			}
			a.setEmployee(e);
			attendDao.save(a);
		}
	}

	/**
	 * 自动结算工资，每月1号，结算上个月工资
	 */
	public void autoPay()
	{
		System.out.println("自动插入工资结算");
		List<Employee> emps = empDao.findAll();
		//获取上个月时间
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		//为每个员工计算上个月工资
		for (Employee e : emps)
		{
			Payment pay = new Payment();
			//获取该员工的工资
			double amount = e.getSalary();
			//获取该员工上个月的出勤记录
			List<Attend> attends = attendDao.findByEmp(e);
			//用工资累积其出勤记录的工资
			for ( Attend a : attends )
			{
				amount += a.getType().getAmerce();
			}
			//添加工资结算
			pay.setPayMonth(payMonth);
			pay.setEmployee(e);
			pay.setAmount(amount);
			payDao.save(pay);
		}
	}

	/**
	 * 验证某个员工是否可打卡
	 * @param user 员工名
	 * @param dutyDay 日期
	 * @return 可打卡的类别
	 */
	public int validPunch(String user , String dutyDay)
	{
		//不能查找到对应用户，返回无法打卡
		Employee emp = empDao.findByName(user);
		if (emp == null)
		{
			return NO_PUNCH;
		}
		//找到员工当前的出勤记录
		List<Attend> attends = attendDao.findByEmpAndDutyDay(emp , dutyDay);
		//系统没有为用户在当天插入空打卡记录，无法打卡
		if (attends == null || attends.size() <= 0)
		{
			return NO_PUNCH;
		}
		//开始上班打卡
		else if (attends.size() == 1
			&& attends.get(0).getIsCome() 
			&& attends.get(0).getPunchTime() == null)
		{
			return COME_PUNCH;
		}
		else if (attends.size() == 1 
			&& attends.get(0).getPunchTime() == null)
		{
			return LEAVE_PUNCH;
		}
		else if (attends.size() == 2)
		{
			//可以上班、下班打卡
			if (attends.get(0).getPunchTime() == null 
				&& attends.get(1).getPunchTime() == null)
			{
				return BOTH_PUNCH;
			}
			//可以下班打卡
			else if (attends.get(1).getPunchTime() == null)
			{
				return LEAVE_PUNCH;
			}
			else
			{
				return NO_PUNCH;
			}
		}
		return NO_PUNCH;
	}

	/**
	 * 打卡
	 * @param user 员工名
	 * @param dutyDay 打卡日期
	 * @param isCome 是否是上班打卡
	 * @return 打卡结果
	 */
	public int punch(String user , String dutyDay , boolean isCome)
	{
		Employee emp = empDao.findByName(user);
		if (emp == null)
		{
			return PUNCH_FAIL;
		}
		//找到员工本次打卡对应的出勤记录
		Attend attend = 
			attendDao.findByEmpAndDutyDayAndCome(emp , dutyDay , isCome);
		if (attend == null)
		{
			return PUNCH_FAIL;
		}
		//已经打卡
		if (attend.getPunchTime() != null)
		{
			return PUNCHED;
		}
		System.out.println("============打卡==========");
		//获取打卡时间
		int punchHour = Calendar.getInstance()
			.get(Calendar.HOUR_OF_DAY);
		attend.setPunchTime(new Date());
		//上班打卡
		if (isCome)
		{
			// 9点之前算正常
			if (punchHour < COME_LIMIT)
			{
				attend.setType(typeDao.get(1));
			}
			// 9～11点之间算迟到
			else if (punchHour < LATE_LIMIT)
			{
				attend.setType(typeDao.get(4));
			}
			//11点之后算旷工,无需理会
		}
		//下班打卡
		else
		{
			//18点之后算正常
			if (punchHour > LEAVE_LIMIT)
			{
				attend.setType(typeDao.get(1));
			}
			//16~18点之间算早退
			else if (punchHour < EARLY_LIMIT)
			{
				attend.setType(typeDao.get(5));
			}
		}
		attendDao.update(attend);
		return PUNCH_SUCC;
	}

	/**
	 * 根据员工浏览自己的工资
	 * @param empName 员工名
	 * @return 该员工的工资列表
	 */
	public List<PaymentBean> empSalary(String empName)
	{
		//获取当前员工
		Employee emp = empDao.findByName(empName);
		//获取该员工的全部工资列表
		List<Payment> pays = payDao.findByEmp(emp);
		List<PaymentBean> result = new ArrayList<PaymentBean>();
		//封装VO集合
		for (Payment p : pays )
		{
			result.add(new PaymentBean(p.getPayMonth()
				,p.getAmount()));
		}
		return result;
	}

	/**
	 * 员工查看自己的最近三天非正常打卡
	 * @param empName 员工名
	 * @return 该员工的最近三天的非正常打卡
	 */
	public List<AttendBean> unAttend(String empName)
	{
		//找出正常上班
		AttendType type = typeDao.get(1);
		Employee emp = empDao.findByName(empName);
		//找出非正常上班的出勤记录
		List<Attend> attends = attendDao.findByEmpUnAttend(emp, type);
		List<AttendBean> result = new ArrayList<AttendBean>();
		//封装VO集合
		for (Attend att : attends )
		{
			result.add(new AttendBean(att.getId() , att.getDutyDay()
				, att.getType().getName() , att.getPunchTime()));
		}
		return result;
	}

	/**
	 * 返回全部的出勤类别
	 * @return 全部的出勤类别
	 */
	public List<AttendType> getAllType()
	{
		return typeDao.findAll();
	}

	/**
	 * 添加申请
	 * @param attId 申请的出勤ID
	 * @param typeId 申请的类型ID
	 * @param reason 申请的理由
	 * @return 添加的结果
	 */
	public boolean addApplication(int attId , int typeId 
		, String reason)
	{
		//创建一个申请
		Application app = new Application();
		//获取申请需要改变的出勤记录
		Attend attend = attendDao.get(attId);
		AttendType type = typeDao.get(typeId);
		app.setAttend(attend);
		app.setType(type);
		if (reason != null)
		{
			app.setReason(reason);
		}
		appDao.save(app);
		return true;
	}
}