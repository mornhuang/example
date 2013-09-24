package org.crazyit.app.service.impl;

import org.crazyit.app.dao.*;
import org.crazyit.app.service.*;
import org.crazyit.app.domain.*;

import org.springframework.transaction.annotation.*;
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
@Transactional
public class LeeServiceImpl
	implements LeeService
{
	private PersonDao personDao;
	//依赖注入DAO组件所需的setter方法
	public void setPersonDao(PersonDao personDao)
	{
		this.personDao = personDao;
	}
	//注册用户
	public boolean regist(Person person)
	{
		//调用DAO组件的方法来实现业务逻辑
		int result = personDao.save(person);
		if (result > 0)
		{
			return true;
		}
		return false;
	}
}
