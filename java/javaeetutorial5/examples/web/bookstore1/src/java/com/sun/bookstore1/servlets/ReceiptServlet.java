/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore1.servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.sun.bookstore.cart.*;
import com.sun.bookstore.cart.ShoppingCart;
import com.sun.bookstore1.database.BookDBAO;
import com.sun.bookstore.exception.*;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;


/**
 * An HTTP servlet that responds to the POST method of the HTTP protocol.
 * The Receipt servlet updates the book database inventory, invalidates the user session,
 * thanks the user for the order. */
public class ReceiptServlet extends HttpServlet {
    private BookDBAO bookDB;
    @Resource
    private UserTransaction utx;

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

    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        boolean orderCompleted = true;

        // Get the user's session and shopping cart
        HttpSession session = request.getSession(true);
        ResourceBundle messages = (ResourceBundle) session.getAttribute(
                    "messages");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        // Update the inventory
        try {
            utx.begin();
            bookDB.buyBooks(cart);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception e) {
                System.out.println("Rollback failed: " + e.getMessage());
            }

            System.err.println(ex.getMessage());
            orderCompleted = false;
        }

        // Payment received -- invalidate the session
        session.invalidate();

        // set content type header before accessing the Writer
        response.setContentType("text/html");
        response.setBufferSize(8192);

        PrintWriter out = response.getWriter();

        // then write the response
        out.println(
                "<html>" + "<head><title>" + messages.getString("TitleReceipt")
                + "</title></head>");

        // Get the dispatcher; it gets the banner to the user
        RequestDispatcher dispatcher = getServletContext()
                                           .getRequestDispatcher("/banner");

        if (dispatcher != null) {
            dispatcher.include(request, response);
        }

        if (orderCompleted) {
            out.println(
                    "<h3>" + messages.getString("ThankYou")
                    + request.getParameter("cardname") + ".");
        } else {
            out.println("<h3>" + messages.getString("OrderError"));
        }

        out.println(
                "<p> &nbsp; <p><strong><a href=\""
                + response.encodeURL(request.getContextPath())
                + "/bookstore\">" + messages.getString("ContinueShopping")
                + "</a> &nbsp; &nbsp; &nbsp;" + "</body></html>");
        out.close();
    }

    public String getServletInfo() {
        return "The Receipt servlet updates the book database inventory, invalidates the user session, "
        + "thanks the user for the order.";
    }
}
