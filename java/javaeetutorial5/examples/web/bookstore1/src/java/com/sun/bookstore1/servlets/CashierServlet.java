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
import com.sun.bookstore1.util.Currency;


/**
 * An HTTP Servlet that responds to the GET method of the
 * HTTP protocol.  It returns a form to the user that gathers data.
 * The form POSTs to another servlet.
 */
public class CashierServlet extends HttpServlet {
    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        // Get the user's session and shopping cart
        HttpSession session = request.getSession();
        ResourceBundle messages = (ResourceBundle) session.getAttribute(
                    "messages");

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        // set content-type header before accessing Writer
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        Currency c = (Currency) session.getAttribute("currency");

        if (c == null) {
            c = new Currency();
            c.setLocale(request.getLocale());
            session.setAttribute("currency", c);
        }

        c.setAmount(cart.getTotal());

        // then write the data of the response
        out.println(
                "<html>" + "<head><title>" + messages.getString("TitleCashier")
                + "</title></head>");

        // Get the dispatcher; it gets the banner to the user
        RequestDispatcher dispatcher = getServletContext()
                                           .getRequestDispatcher("/banner");

        if (dispatcher != null) {
            dispatcher.include(request, response);
        }

        // Print out the total and the form for the user
        out.println(
                "<p>" + messages.getString("Amount") + "<strong>"
                + c.getFormat() + "</strong>" + "<p>"
                + messages.getString("Purchase") + "<form action=\""
                + response.encodeURL(request.getContextPath() + "/bookreceipt")
                + "\" method=\"post\">" + "<table summary=\"layout\">" + "<tr>"
                + "<td><strong>" + messages.getString("Name")
                + "</strong></td>"
                + "<td><input type=\"text\" name=\"cardname\""
                + "value=\"Gwen Canigetit\" size=\"19\"></td>" + "</tr>"
                + "<tr>" + "<td><strong>" + messages.getString("CCNumber")
                + "</strong></td>" + "<td>"
                + "<input type=\"text\" name=\"cardnum\" "
                + "value=\"xxxx xxxx xxxx xxxx\" size=\"19\"></td>" + "</tr>"
                + "<tr>" + "<td></td>" + "<td><input type=\"submit\""
                + "value=\"" + messages.getString("Submit") + "\"></td>"
                + "</tr>" + "</table>" + "</form>" + "</body>" + "</html>");
        out.close();
    }

    public String getServletInfo() {
        return "The Cashier servlet takes the user's name and "
        + "credit card number so that the user can buy the books.";
    }
}
