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
import com.sun.bookstore.database.Book;
import com.sun.bookstore.exception.BooksNotFoundException;
import com.sun.bookstore6.backing.AbstractBean;
import com.sun.bookstore.exception.BooksNotFoundException;
import javax.faces.FacesException;


/**
 * <p>Backing Bean for the <code>/bookstore.jsp</code> page.</p>
 */
public class BookstoreBean extends AbstractBean {
    // -------------------------------------------------------------- Properties
    private Book featured = null;

    /**
     * <p>Return the <code>Book</code> for the featured book.</p>
     */
    public Book getFeatured() {
        if (featured == null) {
            try {
                featured = (Book) dbao()
                                      .getBooks()
                                      .get(5);
            } catch (BooksNotFoundException e) {
                // Real apps would deal with error conditions better than this
                throw new FacesException("Could not get book details " + e);
            }
        }

        return (featured);
    }

    // ----------------------------------------------------- Application Actions

    /**
     * <p>Add the selected item to our shopping cart.</p>
     */
    public String add() {
        Book book = getFeatured();
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
            getFeatured());

        return ("details");
    }
}
