package com.itsz.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *@description
 *
 *@author            yangm
 *@created           2010-12-04 11:44:19
 *@version           1.0
 *
 */

public class SpringBeanUtil implements ApplicationContextAware
{
	protected static ApplicationContext context = null;
    
    public static final String BEAN_NAME_JDBC="jdbcTemplate";
    public static final String BEAN_NAME_HIBERNATE="hibernateTemplate";
	
	public void setApplicationContext(ApplicationContext arg0) 
	{
		SpringBeanUtil.context = arg0;
	}
	
	public static ApplicationContext getContext()
	{
		return context;
	}
	
	public static Object getBean(String beanId)
	{
		return context.getBean(beanId);
	}

    public static JdbcTemplate getJdbcTemplate() {
        return (JdbcTemplate)context.getBean(BEAN_NAME_JDBC);
    }

    public static HibernateTemplate getHibernateTemplate() {
        return (HibernateTemplate)context.getBean(BEAN_NAME_HIBERNATE);
    }
}
