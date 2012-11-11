package cn.hxex.hibernate.cache;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CacheMain extends Thread{

    private static Log log = LogFactory.getLog( CacheMain.class );

    // 静态Configuration和SessionFactory对象的实例（全局唯一的）
    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static 
    {
        // 从缺省的配置文件创建SessionFactory
        try 
        {
        	URL configURL = ClassLoader.getSystemResource( "cn/hxex/hibernate/cache/hibernate.cfg.xml" );
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
    
    public void run() {
    	SessionFactory sf = CacheMain.getSessionFactory();
    	Session session = sf.getCurrentSession();
    	
    	session.beginTransaction();
    	
    	User user = (User)session.get( User.class, "1" );
    	System.out.println( user );
    	
    	session.getTransaction().commit();

    }
    
	public static void main(String[] args) {
		
		CacheMain main1 = new CacheMain();
		main1.start();

		CacheMain main2 = new CacheMain();
		main2.start();
	}
}
