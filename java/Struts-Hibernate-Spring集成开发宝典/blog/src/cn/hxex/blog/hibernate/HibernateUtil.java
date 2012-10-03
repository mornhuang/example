package cn.hxex.blog.hibernate;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


/**
 * 基础的Hibernate辅助类，用于Hibernate的配置和启动。
 * <p>
 * 通过静态的初始化代码来读取Hibernate启动参数，并初始化
 * <tt>Configuration</tt>和<tt>SessionFactory</tt>对象。
 * <p>
 *
 * @author galaxy
 */
public class HibernateUtil 
{

    private static Log log = LogFactory.getLog(HibernateUtil.class);

    // 指定定义拦截器属性名
    private static final String INTERCEPTOR_CLASS = "hibernate.util.interceptor_class";

    // 静态Configuration和SessionFactory对象的实例（全局唯一的）
    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static 
    {
        // 从缺省的配置文件创建SessionFactory
        try 
        {
        	// 创建默认的Configuration对象的实例
        	// 如果你不使用JDK 5.0或者注释请使用new Configuration()
        	// 来创建Configuration()对象的实例
            configuration = new Configuration();

            // 读取hibernate.properties或者hibernate.cfg.xml文件
            configuration.configure();

            // 如果在配置文件中配置了拦截器，那么将其设置到configuration对象中
            String interceptorName = configuration.getProperty(INTERCEPTOR_CLASS);
            if (interceptorName != null) 
            {
                Class interceptorClass =
                        HibernateUtil.class.getClassLoader().loadClass(interceptorName);
                Interceptor interceptor = (Interceptor)interceptorClass.newInstance();
                configuration.setInterceptor(interceptor);
            }

            if (configuration.getProperty(Environment.SESSION_FACTORY_NAME) != null) 
            {
                // 让Hibernate将SessionFacory绑定到JNDI
                configuration.buildSessionFactory();
            } 
            else 
            {
                // 使用静态变量来保持SessioFactory对象的实例
                sessionFactory = configuration.buildSessionFactory();
            }

        } 
        catch (Throwable ex) 
        {
            // 输出异常信息
            log.error("Building SessionFactory failed.", ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * 返回原始的Configuration对象的实例
     *
     * @return Configuration
     */
    public static Configuration getConfiguration() 
    {
        return configuration;
    }

    /**
     * 返回全局的SessionFactory对象的实例
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() 
    {
        SessionFactory sf = null;
        String sfName = configuration.getProperty(Environment.SESSION_FACTORY_NAME);
        if ( sfName != null) 
        {
            log.debug("Looking up SessionFactory in JNDI.");
            try 
            {
                sf = (SessionFactory) new InitialContext().lookup(sfName);
            } 
            catch (NamingException ex) 
            {
                throw new RuntimeException(ex);
            }
        } 
        else 
        {
            sf = sessionFactory;
        }
        if (sf == null)
            throw new IllegalStateException( "SessionFactory not available." );
        return sf;
    }

    /**
     * 关闭当前的SessionFactory并且释放所有的资源
     */
    public static void shutdown() 
    {
        log.debug("Shutting down Hibernate.");
        // Close caches and connection pools
        getSessionFactory().close();

        // Clear static variables
        configuration = null;
        sessionFactory = null;
    }


    /**
     * 使用静态的Configuration对象来重新构建SessionFactory。
     */
     public static void rebuildSessionFactory() 
     {
        log.debug("Using current Configuration for rebuild.");
        rebuildSessionFactory(configuration);
     }

    /**
     * 使用指定的Configuration对象来重新构建SessionFactory对象。
     *
     * @param cfg
     */
     public static void rebuildSessionFactory(Configuration cfg) 
     {
        log.debug("Rebuilding the SessionFactory from given Configuration.");
        synchronized(sessionFactory) 
        {
            if (sessionFactory != null && !sessionFactory.isClosed())
                sessionFactory.close();
            if (cfg.getProperty(Environment.SESSION_FACTORY_NAME) != null)
                cfg.buildSessionFactory();
            else
                sessionFactory = cfg.buildSessionFactory();
            configuration = cfg;
        }
     }

    /**
     * 在当前SessionFactory中注册一个拦截器
     */
    public static SessionFactory registerInterceptorAndRebuild(Interceptor interceptor) 
    {
        log.debug("Setting new global Hibernate interceptor and restarting.");
        configuration.setInterceptor(interceptor);
        rebuildSessionFactory();
        return getSessionFactory();
    }

    /**
     * 获得拦截器对象
     * 
     * @return Interceptor
     */
    public static Interceptor getInterceptor() 
    {
        return configuration.getInterceptor();
    }

    /**
     * 提交当前事务，并开始一个新的事务
     */
    public static void commitAndBeginTransaction()
    {
    	sessionFactory.getCurrentSession().getTransaction().commit();
    	sessionFactory.getCurrentSession().beginTransaction();
    }
}

