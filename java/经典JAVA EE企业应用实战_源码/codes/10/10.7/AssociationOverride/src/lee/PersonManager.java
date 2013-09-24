package lee;

import javax.persistence.*;

import java.util.*;

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
public class PersonManager
{
	private static final EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("qs");
	public static void main(String[] args)
	{
		PersonManager mgr = new PersonManager();
		mgr.createAndStorePerson();
	}
	private void createAndStorePerson()
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//创建一个普通员工
		Employee zhu = new Employee();
		//设置员工的基本属性
		zhu.setName("老朱");
		zhu.setTitle("项目组长");
		zhu.setGender('男');
		zhu.setSalary(4500);
		//创建第二个员工
		Employee zhang = new Employee();
		//设置该员工的基本属性
		zhang.setName("张美丽");
		zhang.setTitle("项目分析");
		zhang.setGender('女');
		zhang.setSalary(5500);
		//创建一个Customer对象
		Customer he = new Customer();
		//设置Customer对象的基本属性
		he.setName("小贺");
		he.setGender('男');
		he.setComments("喜欢购物");
		he.setEmployee(zhang);
		Address address = new Address("广州 天河");
		zhu.setAddress(address);
		zhang.setAddress(address);
		he.setAddress(address);
		
		//持久化所有实体。
		em.persist(zhu);
		em.persist(zhang);
		em.persist(he);
		em.getTransaction().commit();
		em.close();
	}
}
