/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore3.listeners;

import com.sun.bookstore3.database.BookDBAO;
import javax.servlet.*;
import javax.persistence.*;


public final class ContextListener implements ServletContextListener {
    @PersistenceUnit
    private EntityManagerFactory emf;
    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();

        try {
            context.setAttribute("emf", emf);

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

        if (bookDBAO != null) {
            bookDBAO.remove();
        }

        context.removeAttribute("bookDBAO");
    }
}
