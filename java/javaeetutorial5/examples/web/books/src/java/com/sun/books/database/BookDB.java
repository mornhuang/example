/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.books.database;

import java.util.Collection;
import com.sun.bookstore.cart.*;
import com.sun.bookstore.cart.ShoppingCart;
import com.sun.bookstore.database.Book;
import com.sun.bookstore.exception.BookNotFoundException;
import com.sun.bookstore.exception.BooksNotFoundException;
import com.sun.bookstore.exception.OrderException;
import javax.transaction.UserTransaction;


public class BookDB {
    private BookDBAO database = null;
    private String bookId = "0";
    private UserTransaction utx;

    public BookDB() {
        utx = Globals.UTX;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setDatabase(BookDBAO database) {
        this.database = database;
    }

    public Book getBook() throws BookNotFoundException {
        return (Book) database.getBook(bookId);
    }

    public Collection getBooks() throws BooksNotFoundException {
        return database.getBooks();
    }

    public void buyBooks(ShoppingCart cart) throws OrderException {
        try {
            utx.begin();
            database.buyBooks(cart);
            utx.commit();
        } catch (Exception exe) {
            try {
                utx.rollback();
            } catch (Exception ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
        }
    }
}
