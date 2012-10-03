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
import com.sun.bookstore6.backing.AbstractBean;


/**
 * <p>Backing Bean for the <code>/bookdetails.jsp</code> page.</p>
 */
public class BookDetailsBean extends AbstractBean {
    // ----------------------------------------------------- Application Actions

    /**
     * <p>Add the displayed item to our shopping cart.</p>
     */
    public String add() {
        Book book = selected();
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
}
