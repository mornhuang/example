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
import com.sun.bookstore.database.Book;


/**
 * <p>Backing Bean for the <code>/bookshowcart.jsp</code> page.</p>
 */
public class ShowCartBean extends AbstractBean {
    // ----------------------------------------------------- Application Actions

    /**
     * <p>Show the details page for the current book.</p>
     */
    public String details() {
        context()
            .getExternalContext()
            .getSessionMap()
            .put(
            "selected",
            item().getItem());

        return ("details");
    }

    /**
     * <p>Remove the item from the shopping cart.</p>
     */
    public String remove() {
        Book book = (Book) item()
                               .getItem();
        cart()
            .remove(book.getBookId());
        message(
            null,
            "ConfirmRemove",
            new Object[] { book.getTitle() });

        return (null);
    }

    /**
     * <p>Update the quantities in the shopping cart, based on the
     * values entered in the "Quantity" column.</p>
     */
    public String update() {
        message(null, "QuantitiesUpdated");

        //    FacesContext context = context();
        //    ValueBinding vb =
        //        context.getApplication().createValueBinding("#{catalog.totalBooks.value}");
        //     vb.setValue(context, new Integer(cart().getNumberOfItems()));
        return (null);
    }
}
