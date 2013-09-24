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
public class MgrManagerImpl
	implements MgrManager
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
	 * 新增员工
	 * @param emp 新增的员工
	 * @param mgr 员工所属的经理
	 */
	public void addEmp(Employee emp , String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("新增员工的业务异常");
		}
		emp.setManager(m);
		empDao.save(emp);
	}

	/**
	 * 根据经理返回所有的部门上个月工资
	 * @param mgr 新增的员工名
	 * @return 部门上个月工资
	 */
	public List<SalaryBean> getSalaryByMgr(String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("您是经理吗？或你还未登录？");
		}
		//查询该经理对应的全部员工
		List<Employee> emps = empDao.findByMgr(m);
		//部门依然没有员工
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("您的部门没有员工");
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH , -1);
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		List<SalaryBean> result = new ArrayList<SalaryBean>();
		//遍历本部门每个员工
		for (Employee e : emps)
		{
			Payment p = payDao.findByMonthAndEmp(payMonth , e);
			if (p != null)
			{
				result.add(new SalaryBean(e.getName() 
					, p.getAmount()));
			}
		}
		return result;

	}

	/**
	* 根据经理返回该部门的全部员工
	* @param mgr 经理名
	* @return 经理的全部下属
	*/
	public List<EmpBean> getEmpsByMgr(String mgr)
		throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("您是经理吗？或你还未登录？");
		}
		//查询该经理对应的全部员工
		List<Employee> emps = empDao.findByMgr(m);
		//部门依然没有员工
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("您的部门没有员工");
		}
		//封装VO集
		List<EmpBean> result = new ArrayList<EmpBean>();		
		for (Employee e : emps)
		{
			result.add(new EmpBean(e.getName() ,
				e.getPass() , e.getSalary()));
		}
		return result;
	}

	/**
	 * 根据经理返回该部门的没有批复的申请
	 * @param mgr 经理名
	 * @return 该部门的全部申请
	 */
	public List<AppBean> getAppsByMgr(String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("您是经理吗？或你还未登录？");
		}
		//查询该经理对应的全部员工
		List<Employee> emps = empDao.findByMgr(m);
		//部门依然没有员工
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("您的部门没有员工");
		}
		//封装VO集
		List<AppBean> result = new ArrayList<AppBean>();		
		for (Employee e : emps)
		{
			//查看该员工的全部申请
			List<Application> apps = appDao.findByEmp(e);
			if (apps != null && apps.size() > 0)
			{
				for (Application app : apps)
				{
					//只选择还未处理的申请
					if (app.getResult() == false)
					{
						Attend attend = app.getAttend();
						result.add(new AppBean(app.getId() ,
							e.getName(), attend.getType().getName(), 
							app.getType().getName(), app.getReason()));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 处理申请
	 * @param appid 申请ID
	 * @param mgrName 经理名字
	 * @param result 是否通过
	 */
	public void check(int appid, String mgrName, boolean result)
	{
		Application app = appDao.get(appid);
		CheckBack check = new CheckBack();
		check.setApp(app);
		//同意通过申请
		if (result)
		{
			//设置通过申请
			check.setResult(true);
			//修改申请为已经批复
			app.setResult(true);
			appDao.save(app);
			//为真时，还需要修改出勤的类型
			Attend attend = app.getAttend();
			attend.setType(app.getType());
			attendDao.update(attend);
		}
		else
		{
			//没有通过申请
			check.setResult(false);
			app.setResult(true);
			appDao.save(app);
		}
		//保存申请批复
		checkDao.save(check);
	}
}
