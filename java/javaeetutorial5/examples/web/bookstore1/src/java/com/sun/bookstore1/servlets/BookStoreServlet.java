/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore1.servlets;

import com.sun.bookstore.database.Book;
import com.sun.bookstore.exception.BookNotFoundException;
import com.sun.bookstore1.database.BookDBAO;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.sun.bookstore.exception.*;


/**
 * An HTTP Servlet that overrides the service method to return a
 * simple web page.
 */
public class BookStoreServlet extends HttpServlet {
    private BookDBAO bookDB;

    public void init() throws ServletException {
        bookDB = (BookDBAO) getServletContext()
                                .getAttribute("bookDB");

        if (bookDB == null) {
            throw new UnavailableException("Couldn't get database.");
        }
    }

    public void destroy() {
        bookDB = null;
    }

    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle messages = (ResourceBundle) session.getAttribute(
                    "messages");

        if (messages == null) {
            Locale locale = request.getLocale();
            messages = ResourceBundle.getBundle(
                        "com.sun.bookstore.messages.BookstoreMessages",
                        locale);
            session.setAttribute("messages", messages);
        }

        // set content-type header before accessing the Writer
        response.setContentType("text/html");
        response.setBufferSize(8192);

        PrintWriter out = response.getWriter();

        // then write the data of the response
        out.println("<html>" + "<head><title>Duke's Bookstore</title></head>");

        // Get the dispatcher; it gets the banner to the user
        RequestDispatcher dispatcher = getServletContext()
                                           .getRequestDispatcher("/banner");

        if (dispatcher != null) {
            dispatcher.include(request, response);
        }

        try {
            Book bd = bookDB.getBook("203");

            //Left cell -- the "book of choice"
            out.println(
                    "<b>" + messages.getString("What") + "</b>" + "<p>"
                    + "<blockquote>" + "<em><a href=\""
                    + response.encodeURL(
                            request.getContextPath()
                            + "/bookdetails?bookId=203") + "\">"
                    + bd.getTitle() + "</a></em>" + messages.getString("Talk")
                    + "</blockquote>");

            //Right cell -- various navigation options
            out.println(
                    "<p><a href=\""
                    + response.encodeURL(
                            request.getContextPath() + "/bookcatalog")
                    + "\"><b>" + messages.getString("Start")
                    + "</b></a></font><br>" + "<br> &nbsp;" + "<br> &nbsp;"
                    + "<br> &nbsp;" + "</body>" + "</html>");
        } catch (BookNotFoundException ex) {
            response.resetBuffer();
            throw new ServletException(ex);
        }

        out.close();
    }

    public String getServletInfo() {
        return "The BookStore servlet returns the main web page "
        + "for Duke's Bookstore.";
    }
}
