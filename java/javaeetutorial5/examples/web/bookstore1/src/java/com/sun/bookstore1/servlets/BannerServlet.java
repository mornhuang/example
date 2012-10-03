/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore1.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


// This is a simple example of an HTTP Servlet.
public class BannerServlet extends HttpServlet {
    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        output(request, response);
    }

    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        output(request, response);
    }

    private void output(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // then write the data of the response
        out.println(
                "<body  bgcolor=\"#ffffff\">" + "<center>" + "<hr> <br> &nbsp;"
                + "<h1>"
                + "<font size=\"+3\" color=\"#CC0066\">Duke's </font> <img src=\""
                + request.getContextPath()
                + "/duke.books.gif\" alt=\"Duke holding books\"\">"
                + "<font size=\"+3\" color=\"black\">Bookstore</font>"
                + "</h1>" + "</center>" + "<br> &nbsp; <hr> <br> ");
    }
}
