/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * This is a simple example of an HTTP Servlet.  It responds to the GET
 * method of the HTTP protocol.
 */
public class RetailPriceListServlet extends HttpServlet {
    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        RetailPriceList rpl = new RetailPriceList();
        context.setAttribute("retailPriceList", rpl);

        ShoppingCart cart = new ShoppingCart(rpl);
        session.setAttribute("cart", cart);

        PrintWriter out = response.getWriter();

        // then write the data of the response
        out.println(
                "<html><body  bgcolor=\"#ffffff\">" + "Reloaded price list."
                + "</html></body>");
    }
}
