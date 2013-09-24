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
		final EntityManager em = emf.createEntityManager();
		News news = new News();
		news.setTitle("疯狂Java联盟成立了！");
		news.setContent("疯狂Java联盟成立了！"
			+ "联盟特色是：所有技术发帖必有回复！");
		try
		{
			//开启事务
			em.getTransaction().begin();
			//保存实体
			em.persist(news);
			//提交事务
			em.getTransaction().commit();
		} 
		finally 
		{
			em.close();
		}
	}
}