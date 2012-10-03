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
import com.sun.bookstore.database.Book;
import com.sun.bookstore.exception.BookNotFoundException;
import com.sun.bookstore.exception.BooksNotFoundException;
import com.sun.bookstore1.database.BookDBAO;
import com.sun.bookstore1.util.Currency;
import com.sun.bookstore.exception.*;


/**
 * This is a simple example of an HTTP Servlet.  It responds to the GET
 * method of the HTTP protocol.
 */
public class CatalogServlet extends HttpServlet {
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
        // Get the user's session and shopping cart
        HttpSession session = request.getSession(true);
        ResourceBundle messages = (ResourceBundle) session.getAttribute(
                    "messages");

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        // If the user has no cart, create a new one
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        // set content-type header before accessing the Writer
        response.setContentType("text/html");
        response.setBufferSize(8192);

        PrintWriter out = response.getWriter();

        // then write the data of the response
        out.println(
                "<html>" + "<head><title>"
                + messages.getString("TitleBookCatalog") + "</title></head>");

        // Get the dispatcher; it gets the banner to the user
        RequestDispatcher dispatcher = getServletContext()
                                           .getRequestDispatcher("/banner");

        if (dispatcher != null) {
            dispatcher.include(request, response);
        }

        //Information on the books is from the database through its front end

        // Additions to the shopping cart
        String bookId = request.getParameter("bookId");

        if (bookId != null) {
            try {
                Book book = bookDB.getBook(bookId);
                cart.add(bookId, book);
                out.println(
                        "<p><h3>" + "<font color=\"#ff0000\">"
                        + messages.getString("CartAdded1") + "<i>"
                        + book.getTitle() + "</i> "
                        + messages.getString("CartAdded2") + "</font></h3>");
            } catch (BookNotFoundException ex) {
                response.reset();
                throw new ServletException(ex);
            }
        }

        //Give the option of checking cart or checking out if cart not empty
        if (cart.getNumberOfItems() > 0) {
            out.println(
                    "<p><strong><a href=\""
                    + response.encodeURL(
                            request.getContextPath() + "/bookshowcart") + "\">"
                    + messages.getString("CartCheck")
                    + "</a>&nbsp;&nbsp;&nbsp;" + "<a href=\""
                    + response.encodeURL(
                            request.getContextPath() + "/bookcashier") + "\">"
                    + messages.getString("Buy") + "</a>" + "</p></strong>");
        }

        // Always prompt the user to buy more -- get and show the catalog
        out.println(
                "<br> &nbsp;" + "<h3>" + messages.getString("Choose") + "</h3>"
                + "<center> <table summary=\"layout\">");

        try {
            Collection coll = bookDB.getBooks();

            Iterator i = coll.iterator();
            Currency c = (Currency) session.getAttribute("currency");

            if (c == null) {
                c = new Currency();
                c.setLocale(request.getLocale());
                session.setAttribute("currency", c);
            }

            while (i.hasNext()) {
                Book book = (Book) i.next();
                bookId = book.getBookId();
                c.setAmount(book.getPrice());

                //Print out info on each book in its own two rows
                out.println(
                        "<tr>" + "<td bgcolor=\"#ffffaa\">" + "<a href=\""
                        + response.encodeURL(
                                request.getContextPath()
                                + "/bookdetails?bookId=" + bookId)
                        + "\"> <strong>" + book.getTitle()
                        + "&nbsp; </strong></a></td>"
                        + "<td bgcolor=\"#ffffaa\" rowspan=2>" + c.getFormat()
                        + "&nbsp; </td>" + "<td bgcolor=\"#ffffaa\" rowspan=2>"
                        + "<a href=\""
                        + response.encodeURL(
                                request.getContextPath()
                                + "/bookcatalog?bookId=" + bookId)
                        + "\"> &nbsp;" + messages.getString("CartAdd")
                        + "&nbsp;</a></td></tr>" + "<tr>"
                        + "<td bgcolor=\"#ffffff\">" + "&nbsp; &nbsp;"
                        + messages.getString("By") + "<em> "
                        + book.getFirstName() + " " + book.getSurname()
                        + "</em></td></tr>");
            }
        } catch (BooksNotFoundException ex) {
            response.reset();
            throw new ServletException(ex);
        }

        out.println("</table></center></body></html>");
        out.close();
    }

    public String getServletInfo() {
        return "The Catalog servlet adds books to the user's "
        + "shopping cart and prints the catalog.";
    }
}
