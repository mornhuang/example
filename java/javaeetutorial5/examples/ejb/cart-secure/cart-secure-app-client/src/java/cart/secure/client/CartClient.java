/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package cart.secure.client;

import cart.secure.ejb.BookException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import cart.secure.ejb.Cart;


/**
 *
 * The client class for the CartBean example. Client adds books to the cart,
 * prints the contents of the cart, and then removes a book which hasn't been
 * added yet, causing a BookException.
 * @author ian
 */
public class CartClient {
    @EJB
    private static Cart cart;

    public CartClient(String[] args) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CartClient client = new CartClient(args);
        client.doTest();
    }

    public void doTest() {
        try {
            cart.initialize("Duke d'Url", "123");
            cart.addBook("Infinite Jest");
            cart.addBook("Bel Canto");
            cart.addBook("Kafka on the Shore");

            List<String> bookList = cart.getContents();

            bookList = cart.getContents();

            Iterator iterator = bookList.iterator();

            while (iterator.hasNext()) {
                String title = (String) iterator.next();
                System.out.println(title);
            }

            cart.removeBook("Gravity's Rainbow");
            cart.remove();

            System.exit(0);
        } catch (BookException ex) {
            System.err.println("Caught a BookException: " + ex.getMessage());
            System.exit(1);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
