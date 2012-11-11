/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore2.dispatcher;

import com.sun.bookstore2.database.BookDBAO;
import javax.servlet.http.*;
import com.sun.bookstore.cart.ShoppingCart;
import com.sun.bookstore.cart.ShoppingCart;
import com.sun.bookstore.database.Book;
import com.sun.bookstore.exception.BookNotFoundException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;


public class Dispatcher extends HttpServlet {
    @Resource
    private UserTransaction utx;

    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response) {
        String bookId = null;
        String clear = null;
        Book book = null;
        BookDBAO bookDBAO = (BookDBAO) getServletContext()
                                           .getAttribute("bookDBAO");
        HttpSession session = request.getSession();
        String selectedScreen = request.getServletPath();

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        if (selectedScreen.equals("/books/bookcatalog")) {
            bookId = request.getParameter("Add");

            if (!bookId.equals("")) {
                try {
                    book = bookDBAO.getBook(bookId);
                    cart.add(bookId, book);
                } catch (BookNotFoundException ex) {
                    // not possible
                }
            }
        } else if (selectedScreen.equals("/books/bookshowcart")) {
            bookId = request.getParameter("Remove");

            if (bookId != null) {
                cart.remove(bookId);
            }

            clear = request.getParameter("Clear");

            if ((clear != null) && clear.equals("clear")) {
                cart.clear();
            }
        } else if (selectedScreen.equals("/books/bookreceipt")) {
            // Update the inventory
            try {
                utx.begin();
                bookDBAO.buyBooks(cart);
                utx.commit();
            } catch (Exception ex) {
                try {
                    utx.rollback();
                } catch (Exception exe) {
                    System.out.println("Rollback failed: " + exe.getMessage());
                }

                selectedScreen = "/books/bookordererror";
            }
        }

        String screen = selectedScreen + ".jsp";

        try {
            request.getRequestDispatcher(screen)
                   .forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response) {
        String screen = request.getServletPath() + ".jsp";

        try {
            request.getRequestDispatcher(screen)
                   .forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
