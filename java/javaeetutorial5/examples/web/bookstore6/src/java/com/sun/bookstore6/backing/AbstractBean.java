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
import com.sun.bookstore.cart.ShoppingCart;
import com.sun.bookstore.cart.ShoppingCartItem;
import com.sun.bookstore.database.Book;
import com.sun.bookstore.exception.OrderException;
import com.sun.bookstore.database.Book;
import com.sun.bookstore6.database.BookDBAO;
import com.sun.bookstore.exception.*;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.el.ValueExpression;
import javax.el.ELContext;
import javax.annotation.Resource;
import javax.persistence.*;
import javax.transaction.UserTransaction;


/**
 * <p>Abstract base class for backing beans to share utility methods.</p>
 */
public abstract class AbstractBean {
    @PersistenceContext(name = "books")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    // ----------------------------------------------------- Application Actions

    /**
     * <p>Buy the items currently in the shopping cart.</p>
     */
    public String buy() {
        // "Cannot happen" sanity check
        if (cart()
                    .getNumberOfItems() < 1) {
            message(null, "CartEmpty");

            return (null);
        } else {
            return ("purchase");
        }
    }

    /**
     * <p>Clear all items from the shopping cart.</p>
     */
    public String clear() {
        cart()
            .clear();
        message(null, "CartCleared");

        return (null);
    }

    // ------------------------------------------------------- Protected Methods

    /**
     * <p>Return the currently selected <code>Book</code> instance
     * from the user request.</p>
     */
    protected Book book() {
        Book book = (Book) context()
                               .getExternalContext()
                               .getRequestMap()
                               .get("book");

        return (book);
    }

    /**
     * <p>Return the <code>ShoppingCart</code> instance from the
     * user session.</p>
     */
    protected ShoppingCart cart() {
        FacesContext context = context();
        ELContext elContext = context.getCurrentInstance()
                                     .getELContext();
        ValueExpression ve = context.getApplication()
                                    .getExpressionFactory()
                                    .createValueExpression(
                    elContext,
                    "#{cart}",
                    Object.class);

        return ((ShoppingCart) ve.getValue(elContext));
    }

    /**
     * <p>Return the <code>FacesContext</code> instance for the
     * current request.
     */
    protected FacesContext context() {
        return (FacesContext.getCurrentInstance());
    }

    /**
     * <p>Return the <code>BookDBAO</code> object for our database.</p>
     */
    protected BookDBAO dbao() {
        FacesContext context = context();
        ELContext elContext = context.getCurrentInstance()
                                     .getELContext();
        ValueExpression ve = context.getApplication()
                                    .getExpressionFactory()
                                    .createValueExpression(
                    elContext,
                    "#{dbao}",
                    Object.class);

        return ((BookDBAO) ve.getValue(elContext));
    }

    /**
     * <p>Return the <code>ShoppingCartItem</code> instance from the
     * user request.</p>
     */
    protected ShoppingCartItem item() {
        ShoppingCartItem item = (ShoppingCartItem) context()
                                                       .getExternalContext()
                                                       .getRequestMap()
                                                       .get("item");

        return (item);
    }

    /**
     * <p>Add a localized message to the <code>FacesContext</code> for the
     * current request.</p>
     *
     * @param clientId Client identifier of the component this message
     *  relates to, or <code>null</code> for global messages
     * @param key Message key of the message to include
     */
    protected void message(
        String clientId,
        String key) {
        // Look up the requested message text
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(
                        "com.sun.bookstore.messages.BookstoreMessages",
                        context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        // Construct and add a FacesMessage containing it
        context()
            .addMessage(
            clientId,
            new FacesMessage(text));
    }

    /**
     * <p>Add a localized message to the <code>FacesContext</code> for the
     * current request.</p>
     *
     * @param clientId Client identifier of the component this message
     *  relates to, or <code>null</code> for global messages
     * @param key Message key of the message to include
     * @param params Substitution parameters for using the localized text
     *  as a message format
     */
    protected void message(
        String clientId,
        String key,
        Object[] params) {
        // Look up the requested message text
        String text = null;

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(
                        "com.sun.bookstore.messages.BookstoreMessages",
                        context().getViewRoot().getLocale());
            text = bundle.getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        // Perform the requested substitutions
        if ((params != null) && (params.length > 0)) {
            text = MessageFormat.format(text, params);
        }

        // Construct and add a FacesMessage containing it
        context()
            .addMessage(
            clientId,
            new FacesMessage(text));
    }

    /**
     * <p>Return the explicitly selected <code>Book</code> instance
     * from the user request.</p>
     */
    protected Book selected() {
        Book selected = (Book) context()
                                   .getExternalContext()
                                   .getSessionMap()
                                   .get("selected");

        return (selected);
    }

    protected void updateInventory() throws OrderException {
        try {
            utx.begin();
            dbao()
                .buyBooks(cart());
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception exe) {
                throw new OrderException(
                        "Rollback failed: " + exe.getMessage());
            }

            throw new OrderException("Commit failed: " + ex.getMessage());
        }
    }
}
