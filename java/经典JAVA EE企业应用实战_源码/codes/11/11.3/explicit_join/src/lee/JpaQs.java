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
		try
		{
			//开启事务
			em.getTransaction().begin();
			List result = em.createQuery("select p.name , ad"
				+ " from Person as p"
				+ " left join p.addresses ad"
				+ " where p.age > ?1 or p.age < ?2")
				.setParameter(1 , 400)
				.setParameter(2 , 20)
				.getResultList();
			for (int i = 0 ; i < result.size() ; i++)
			{
				Object[] row = (Object[])result.get(i);
				for (int j = 0 ; j < row.length ; j++)
				{
					System.out.println(row[j]);
				}
			}
			//提交事务
			em.getTransaction().commit();
		} 
		finally 
		{
			em.close();
		}
	}
}