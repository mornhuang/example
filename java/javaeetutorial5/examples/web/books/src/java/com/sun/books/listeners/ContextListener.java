/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.books.listeners;

import com.sun.books.database.*;
import javax.servlet.*;
import javax.ejb.*;
import javax.annotation.Resource;
import javax.persistence.*;
import javax.transaction.NotSupportedException;
import javax.transaction.UserTransaction;


public final class ContextListener implements ServletContextListener {
    @PersistenceUnit
    private EntityManagerFactory emf;
    private ServletContext context = null;
    @Resource
    private UserTransaction utx;

    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();

        try {
            context.setAttribute("emf", emf);
            Globals.UTX = utx;

            BookDBAO bookDBAO = new BookDBAO(emf);
            context.setAttribute("bookDBAO", bookDBAO);
        } catch (Exception ex) {
            System.out.println(
                    "Couldn't create bookstore database bean: "
                    + ex.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();

        BookDBAO bookDBAO = (BookDBAO) context.getAttribute("bookDBAO");
        bookDBAO.remove();
        context.removeAttribute("bookDBAO");
    }
}
