package cn.hxex.hibernate.lock;

import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LockMain extends Thread{

    private static Log log = LogFactory.getLog( LockMain.class );

    // 静态Configuration和SessionFactory对象的实例（全局唯一的）
    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static 
    {
        // 从默认的配置文件创建SessionFactory
        try 
        {
        	URL configURL = ClassLoader.getSystemResource( "cn/hxex/hibernate/lock/hibernate.cfg.xml" );
        	// 创建默认的Configuration对象的实例
            configuration = new Configuration();

            // 读取hibernate.properties或者hibernate.cfg.xml文件
            configuration.configure( configURL );

            // 使用静态变量来保持SessioFactory对象的实例
            sessionFactory = configuration.buildSessionFactory();
        } 
        catch (Throwable ex) 
        {
            // 输出异常信息
            log.error("Building SessionFactory failed.", ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
	
    public static SessionFactory getSessionFactory() {
    	return sessionFactory;
    }
    
    public void testPessimisticLock() {

		SessionFactory sf = LockMain.getSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from User user");
		query.setLockMode("user", LockMode.UPGRADE);
		List users = query.list();
		for( int i=0; i<users.size(); i++ ) {
			System.out.println( users.get( i ) );
		}
		session.getTransaction().commit();
    }
    
    public void testOptimisticLock() {

    	SessionFactory sf = LockMain.getSessionFactory();
    	Session session = sf.openSession();
 
    	Transaction tx = session.beginTransaction();
    	
    	User userV1 = (User)session.load( User.class, "1" );
    	
    	// 等第二个进程执行
    	try {
			sleep( 3000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	userV1.setAge(new Integer(32));
    	tx.commit();

    	session.close();
    }
    
    public void run() {
    	testOptimisticLock();
    }
    
	public static void main(String[] args) {
		
//		LockMain main = new LockMain();
//		main.testPessimisticLock();
		
		LockMain main1 = new LockMain();
		main1.start();
		LockMain main2 = new LockMain();
		main2.start();
	}
}
