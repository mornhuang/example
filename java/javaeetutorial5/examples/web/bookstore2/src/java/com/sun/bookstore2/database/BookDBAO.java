/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore2.database;

import java.util.*;
import com.sun.bookstore.cart.ShoppingCart;
import com.sun.bookstore.cart.ShoppingCartItem;
import com.sun.bookstore.database.Book;
import com.sun.bookstore.exception.BookNotFoundException;
import com.sun.bookstore.exception.BooksNotFoundException;
import com.sun.bookstore.exception.OrderException;
import javax.persistence.*;


public class BookDBAO {
    private ArrayList books;
    private EntityManager em;

    public BookDBAO(EntityManagerFactory emf) throws Exception {
        try {
            em = emf.createEntityManager();
        } catch (Exception ex) {
            throw new Exception(
                    "Couldn't open connection to database: " + ex.getMessage());
        }
    }

    public void remove() {
        try {
            em.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List getBooks() throws BooksNotFoundException {
        try {
            return em.createQuery("SELECT bd FROM Book bd ORDER BY bd.bookId")
                     .getResultList();
        } catch (Exception ex) {
            throw new BooksNotFoundException(
                    "Could not get books: " + ex.getMessage());
        }
    }

    public Book getBook(String bookId) throws BookNotFoundException {
        Book requestedBook = em.find(Book.class, bookId);

        if (requestedBook == null) {
            throw new BookNotFoundException("Couldn't find book: " + bookId);
        }

        return requestedBook;
    }

    public void buyBooks(ShoppingCart cart) throws OrderException {
        Collection items = cart.getItems();
        Iterator i = items.iterator();

        try {
            while (i.hasNext()) {
                ShoppingCartItem sci = (ShoppingCartItem) i.next();
                Book bd = (Book) sci.getItem();
                String id = bd.getBookId();
                int quantity = sci.getQuantity();
                buyBook(id, quantity);
            }
        } catch (Exception ex) {
            throw new OrderException("Commit failed: " + ex.getMessage());
        }
    }

    public void buyBook(
        String bookId,
        int quantity) throws OrderException {
        try {
            Book requestedBook = em.find(Book.class, bookId);

            if (requestedBook != null) {
                int inventory = requestedBook.getInventory();

                if ((inventory - quantity) >= 0) {
                    int newInventory = inventory - quantity;
                    requestedBook.setInventory(newInventory);
                } else {
                    throw new OrderException(
                            "Not enough of " + bookId
                            + " in stock to complete order.");
                }
            }
        } catch (Exception ex) {
            throw new OrderException(
                    "Couldn't purchase book: " + bookId + ex.getMessage());
        }
    }
}
