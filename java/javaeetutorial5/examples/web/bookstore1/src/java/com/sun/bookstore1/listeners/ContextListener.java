/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore1.listeners;

import com.sun.bookstore1.database.BookDBAO;
import javax.servlet.*;
import com.sun.bookstore1.util.Counter;
import javax.persistence.*;


public final class ContextListener implements ServletContextListener {
    @PersistenceUnit
    private EntityManagerFactory emf;
    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();

        Counter counter = new Counter();
        context.setAttribute("hitCounter", counter);
        counter = new Counter();
        context.setAttribute("orderCounter", counter);

        try {
            BookDBAO bookDB = new BookDBAO(emf);
            context.setAttribute("bookDB", bookDB);
        } catch (Exception ex) {
            System.out.println(
                    "Couldn't create bookstore database bean: "
                    + ex.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();
        context.removeAttribute("hitCounter");
        context.removeAttribute("orderCounter");

        BookDBAO bookDB = (BookDBAO) context.getAttribute("bookDB");

        if (bookDB != null) {
            bookDB.remove();
        }

        context.removeAttribute("bookDB");
    }
}
