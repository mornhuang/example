package lee;

import java.util.List;
import javax.persistence.*;

import org.crazyit.model.*;
import java.io.*;

import java.awt.*;
import javax.swing.*;
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
		throws IOException
	{
		final EntityManager em = emf.createEntityManager();
		Person person = new Person();
		person.setName("crazyit.org");
		person.setBirth(new java.util.Date());
		try
		{
			//开启事务
			em.getTransaction().begin();
			//保存实体
			em.persist(person);
			//提交事务
			em.getTransaction().commit();
		} 
		finally 
		{
			em.close();
		}
	}
}