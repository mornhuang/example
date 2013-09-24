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
			//获取主键为1的实体
			News news = em.find(News.class , 1);
			//修改实体的属性，底层将会自动转换为修改指定行、指定列的数据
			news.setContent("疯狂Java联盟的网址：crazyit.org!!");
			//提交事务
			em.getTransaction().commit();
		} 
		finally 
		{
			em.close();
		}
	}
}