package lee;

import java.util.List;
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
public class JpaQs
{
	//一个持久单元对应一个EntityManagerFactory
	private static final EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("qs");
	public static void main(String[] args)
	{
		EntityManager em = emf.createEntityManager();
		Person person = new Person();
		person.setName("孙悟空");
		person.setAge(500);
		Address address = new Address();
		address.setDetail("花果山福地 水帘洞洞天");
		try
		{
			//开启事务
			em.getTransaction().begin();
			//设置两个实体的关联关系
			address.setPerson(person);
			//保存address实体，因此设置了cascade=ALL，
			//因此将级联保存关联实体Person
			em.persist(address);
			//提交事务
			em.getTransaction().commit();
		} 
		finally 
		{
			em.close();
		}
	}
}