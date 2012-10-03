package cn.hxex.basic.exercise;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hxex.basic.model.User;

public class LifeCycle
{

	public static final SessionFactory sessionFactory;
	
	static
	{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static void main(String[] args)
	{
		LifeCycle.lifecycle();
	}
	
	public static void lifecycle()
	{
		// 创建游离态实体对象User
		User user = new User();
		user.setName( "abc" + String.valueOf( Math.random() ) );
		// 为了避免两次运行的用户名重复，所以增加了随机数
		user.setPassword( "def" );
		// user仍然处于游离态

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// 此时user对象仍然是Transient状态
		session.save( user );
		// 此时，user对象已经被纳入了Hibernate的实体管理容器中，并转变为Persistent状态
		System.out.println( "User 1:" + user );
		// 此时的id已经有值了。
		get( user.getId() );
		// 但并未真正的执行数据库的操作，所以无法得到对象的值
		tx.commit();
		// 事务被提交后，将向数据库的用户表中插入一条记录
		System.out.println( "Transaction 1 commit!" );
		get( user.getId() );
		// 这时可以由数据库中得到刚才插入的user对象了。
		
		Transaction tx2 = session.beginTransaction();
		user.setPassword( "mmmmmmmmmmmmm" );
		tx2.commit();
		// 虽然这个事务中并没有调用Session的save()方法来保存user对象
		// 但由于user对象处于Persistent状态，所以对user对象所做的任何修改都将被持久化到数据库中
		// 那么数据库中的用户密码也应该变为了def。
		System.out.println( "Transaction 2 commit!" );
		session.close();

		get( user.getId() );
		// 此时密码已经变为新的值了
	}
	
	public static User get( String id )
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User)session.get( User.class, id );
		// Hibernate在返回User对象之间会将其纳入到Hibernate的实体管理容器中
		// 所以，这时的user对象是Persistent状态的
		display( user );
		
		tx.commit();
		session.close();
		
		return user;
	}
	
	public static void display( Object obj )
	{
		System.out.println( obj );
	}
}
