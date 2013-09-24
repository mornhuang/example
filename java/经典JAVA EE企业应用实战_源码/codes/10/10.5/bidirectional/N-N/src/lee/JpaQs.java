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
		Person zhu = new Person();
		zhu.setName("猪八戒");
		zhu.setAge(380);
		Address road = new Address("西天取经路");
		try
		{
			//开启事务
			em.getTransaction().begin();
			//添加关联实体
			//因为Address不控制关联关系，所以应通过Person实体来管理关联关系
			sun.getAddresses().add(new Address("花果山福地 水帘洞洞天"));
			sun.getAddresses().add(road);
			zhu.getAddresses().add(new Address("高老庄"));
			//将系统中已有的Address实体添加成关联关系
			zhu.getAddresses().add(road);
			//保存实体
			em.persist(sun);
			em.persist(zhu);
			//提交事务
			em.getTransaction().commit();
		} 
		finally 
		{
			em.close();
		}
	}
}