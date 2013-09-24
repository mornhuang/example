package org.crazyit.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.*;

import org.crazyit.app.dao.*;
import org.crazyit.app.domain.*;
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
public class PersonDaoJpa
	extends JpaDaoSupport implements PersonDao
{
	/**
	 * 加载Person实例
	 * @param id 需要加载的Person实例的标识属性值
	 * @return 指定id对应的Person实例
	 */ 
	public Person get(Integer id)
	{
		return getJpaTemplate()
			.find(Person.class, id); 
	}
	
	/**
	 * 保存Person实例
	 * @param person 需要保存的Person实例
	 * @return 刚刚保存的Person实例的标识属性值
	 */   
	public Integer save(Person person)
	{
		getJpaTemplate().persist(person);
		return person.getId();
	}
	
	/**
	 * 修改Person实例
	 * @param person 需要修改的Person实例
	 */
	public void update(Person person)
	{
		getJpaTemplate().merge(person);
	}	
	
	/**
	 * 删除Person实例
	 * @param id 需要删除的Person实例的标识属性值
	 */
	public void delete(Integer id)
	{
		getJpaTemplate().remove(get(id));
	}
	
	/**
	 * 删除Person实例
	 * @param person 需要删除的Person实例
	 */
	public void delete(Person person)
	{
		getJpaTemplate().remove(person);
	}
	
	/**
	 * 根据用户名查找Person
	 * @param name 查询的人名
	 * @return 指定用户名对应的全部Person
	 */
	public List<Person> findByName(String name)
	{
		return (List<Person>)getJpaTemplate()
			.find("select p from Person p where p.name = ?"
			, name);
	}
	
	/**
	 * 查询全部Person实例
	 * @return 全部Person实例
	 */
	public List findAllPerson()
	{
		return (List<Person>)getJpaTemplate()
			.find("select p from Person p");
	}
	
	/**
	 * 查询数据表中Person实例的总数
	 * @return 数据表中Person实例的总数
	 */
	public long getPersonNumber()
	{
		return (Long)getJpaTemplate().find
			("select count(*) from Person as p")
			.get(0);
	}
}