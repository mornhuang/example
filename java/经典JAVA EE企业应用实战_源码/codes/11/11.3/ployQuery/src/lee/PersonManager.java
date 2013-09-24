package lee;

import javax.persistence.*;

import java.util.*;

import org.crazyit.model.*;

/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a> 
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
		List<String> names = em.createQuery("select p.name"
			+ " from Person p")
			.getResultList();
		for (int i = 0 ; i < names.size() ; i++)
		{
			System.out.println(names.get(i));
		}
		em.getTransaction().commit();
		em.close();
	}
}
