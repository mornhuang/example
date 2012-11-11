package cn.hxex.hibernate.cache;

import java.net.URL;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryCacheMain extends Thread{

    private static Log log = LogFactory.getLog( QueryCacheMain.class );

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
 
    	SessionFactory sf = QueryCacheMain.getSessionFactory();
    	Session session = sf.getCurrentSession();
    	
    	session.beginTransaction();
    	
    	Query query = session.createQuery( "from User" );
    	Iterator it = query.setCacheable( true ).list().iterator();
    	while( it.hasNext() ) {
    		System.out.println( it.next() );
    	}
    	
    	User user = (User)session.get( User.class, "1" );
    	System.out.println( user );
    	
    	session.getTransaction().commit();

    }
    
	public static void main(String[] args) {
		
		QueryCacheMain main1 = new QueryCacheMain();
		main1.start();
		
		try {
			Thread.sleep( 2000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		QueryCacheMain main2 = new QueryCacheMain();
		main2.start();
	}
}
