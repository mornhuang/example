package com.redsaga.hibernatesample.step4.util;

import org.apache.log4j.Logger;

import com.redsaga.hibernatesample.step4.base._BaseRootDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.hibernate.HibernateException;

/**
 * Created by IntelliJ IDEA.
 * User: cao
 * Date: 2005-3-12
 * Time: 23:33:25
 * To change this template use File | Settings | File Templates.
 */
public class InitializeServlet extends HttpServlet{
	private static final Logger logger = Logger
			.getLogger(InitializeServlet.class);

    public void init(ServletConfig servletConfig) throws ServletException {
       super.init(servletConfig);
        logger.info("loading servlet");
        try {
            _BaseRootDAO.initialize();
            logger.info("DAO initialized");
        } catch (HibernateException e) {
            e.printStackTrace(); 
            throw new ServletException(e);
        }
    }
}
