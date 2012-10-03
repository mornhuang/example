/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore6.backing;

import com.sun.bookstore.cart.ShoppingCart;
import com.sun.bookstore.cart.ShoppingCartItem;
import com.sun.bookstore.cart.ShoppingCartItem;
import com.sun.bookstore.database.Book;
import com.sun.bookstore6.backing.AbstractBean;
import com.sun.bookstore.database.Book;
import java.util.*;


/**
 * <p>Backing Bean for the <code>/bookcatalog.jsp</code> page.</p>
 */
public class CatalogBean extends AbstractBean {
    private int totalBooks = 0;

    // ----------------------------------------------------- Application Actions
    //  private static Log log = LogFactory.getLog(CatalogBean.class);

    /**
     * <p>Add the selected item to our shopping cart.</p>
     */
    public String add() {
        Book book = book();
        cart()
            .add(
            book.getBookId(),
            book);
        message(
            null,
            "ConfirmAdd",
            new Object[] { book.getTitle() });

        return ("catalog");
    }

    /**
     * <p>Show the details page for the current book.</p>
     */
    public String details() {
        context()
            .getExternalContext()
            .getSessionMap()
            .put(
            "selected",
            book());

        return ("details");
    }

    public int getTotalBooks() {
        totalBooks = cart()
                         .getNumberOfItems();

        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public int getBookQuantity() {
        int bookQuantity = 0;
        Book book = book();

        if (book == null) {
            return bookQuantity;
        }

        List<ShoppingCartItem> results = cart()
                                             .getItems();

        for (Iterator items = results.iterator(); items.hasNext();) {
            ShoppingCartItem item = (ShoppingCartItem) items.next();
            Book bd = (Book) item.getItem();

            if ((bd.getBookId()).equals(book.getBookId())) {
                bookQuantity = item.getQuantity();

                break;
            }
        }

        return bookQuantity;
    }
}
