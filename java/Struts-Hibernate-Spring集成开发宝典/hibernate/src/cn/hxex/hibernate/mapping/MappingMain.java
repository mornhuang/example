package cn.hxex.hibernate.mapping;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cn.hxex.hibernate.mapping.model.Child;
import cn.hxex.hibernate.mapping.model.Parent;

public class MappingMain {

    private static Log log = LogFactory.getLog( MappingMain.class );

    // 静态Configuration和SessionFactory对象的实例（全局唯一的）
    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static 
    {
        // 从缺省的配置文件创建SessionFactory
        try 
        {
        	URL configURL = ClassLoader.getSystemResource( "cn/hxex/hibernate/mapping/hibernate.cfg.xml" );
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
    
	public static void main(String[] args) {
		
		SessionFactory sf = MappingMain.getSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Parent p = new Parent();
		session.save( p );
		
		Child c1 = new Child();
		p.getChildren().add( c1 );
		c1.setParent( p );
		session.save( c1 );
//
//		Child c2 = new Child();
//		p.getChildren().add( c2 );
//		c2.setParent( p );
//		session.save( c2 );
//		
//		session.flush();
		
//		Parent p = (Parent)session.get( Parent.class, "402881e40de552eb010de552ef5e0001" );
//		Child c = (Child)session.get( Child.class, "402881e40de552eb010de552ef7c0002" );
//		p.getChildren().remove( c );
//		session.delete( c );
//		session.flush();
		
//		User user = new User();
//		Role role = new Role();
//		user.getRoles().add( role );
//		// role.getUsers().add( user );
//		session.save( user );
//		session.save( role );
//		session.flush();
		
//		User user = (User)session.get( User.class, "402881e40dea568d010dea5691350001" );
//		Role role = (Role)session.get( Role.class, "402881e40dea568d010dea5691b70002" );
//		user.getRoles().remove( role );
//		session.flush();
		
		session.getTransaction().commit();
	}
}
