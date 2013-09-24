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
		Person sun = new Person();
		sun.setName("孙悟空");
		sun.setAge(500);
		Address mountain = new Address("花果山福地 水帘洞洞天");
		Address road = new Address("西天取经路");
		try
		{
			//开启事务
			em.getTransaction().begin();
			//为Person添加2个关联实体
			//因为Person不控制关联关系，所以应通过Address实体来管理关联关系
			mountain.setPerson(sun);
			road.setPerson(sun);
			//保存Address实体，操作将会级联到Person实体。
			//但Person实体不控制关联关系，因此保存Person不会级联到Address实体
			em.persist(road);
			em.persist(mountain);
			//提交事务
			em.getTransaction().commit();
		} 
		finally 
		{
			em.close();
		}
	}
}