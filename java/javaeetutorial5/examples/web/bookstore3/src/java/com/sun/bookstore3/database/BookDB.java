/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore3.database;

import java.util.List;
import com.sun.bookstore.cart.*;
import com.sun.bookstore.cart.ShoppingCart;
import com.sun.bookstore.database.Book;
import com.sun.bookstore.exception.BookNotFoundException;
import com.sun.bookstore.exception.BooksNotFoundException;
import com.sun.bookstore.exception.OrderException;


public class BookDB {
    private BookDBAO database = null;
    private String bookId = "0";

    public BookDB() {
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

    public List getBooks() throws BooksNotFoundException {
        return database.getBooks();
    }

    public void buyBooks(ShoppingCart cart) throws OrderException {
        database.buyBooks(cart);
    }
}
