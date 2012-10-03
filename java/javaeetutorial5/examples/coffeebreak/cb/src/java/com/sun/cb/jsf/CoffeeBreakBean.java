/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.math.BigDecimal;
import javax.faces.event.ActionEvent;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * <p>Backing file bean for orderForm of CoffeeBreak demo.</p>
 */
public class CoffeeBreakBean {
    static final Logger log = Logger.getLogger(
                "com.sun.cb.jsf.CoffeeBreakBean");
    public static final String CB_RESOURCE_BUNDLE_NAME = "com.sun.cb.jsf.messages.CBMessages";
    private static ResourceBundle bundle = null;
    private RetailPriceList retailPriceList = null;
    private ShoppingCart cart = null;

    public CoffeeBreakBean() {
        // load the different types of Coffee and their price list.
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extcontext = context.getExternalContext();
        Map<String, Object> applicationMap = context.getExternalContext()
                                                    .getApplicationMap();
        RetailPriceList rpl = (RetailPriceList) applicationMap.get(
                    "retailPriceList");

        if (retailPriceList == null) {
            try {
                retailPriceList = new RetailPriceList();
                applicationMap.put("retailPriceList", rpl);
            } catch (Exception ex) {
                log.severe(
                        "CoffeeBreakBean: Couldn't create retail price list: "
                        + ex.getMessage());
            }
        }

        // populate the shopping cart with the price list
        if (cart == null) {
            cart = new ShoppingCart(retailPriceList);
        }
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public RetailPriceList getRetailPriceList() {
        return retailPriceList;
    }

    /*
     * Handles "Update" action on the orderForm
     */
    public String update() {
        log.fine("CoffeeBreakBean: processUpdate ");

        updateCart();

        return null;
    }

    /*
     * Handles "Checkout" action on the orderForm
     */
    public String checkout() {
        log.fine("CoffeeBreakBean: processCheckout ");

        updateCart();

        return "checkout";
    }

    /*
     * Handles "Clear" action on the orderForm
     */
    public String clear() {
        log.fine("CoffeeBreakBean: processClear ");

        for (Iterator i = cart.getItems()
                              .iterator(); i.hasNext();) {
            ShoppingCartItem sci = (ShoppingCartItem) i.next();
            RetailPriceItem item = sci.getItem();
            sci.setPounds(new BigDecimal("0.0"));
            sci.setPrice(new BigDecimal("0.00"));
            cart.setTotal(new BigDecimal("0.00"));
        }

        return null;
    }

    /*
     * Updates the price on the Shopping Cart based on the quantity of Coffee
     * beans ordered.
     */
    public void updateCart() {
        BigDecimal total = new BigDecimal("0.00");
        BigDecimal price = new BigDecimal("0.00");

        for (Iterator i = cart.getItems()
                              .iterator(); i.hasNext();) {
            ShoppingCartItem sci = (ShoppingCartItem) i.next();
            RetailPriceItem item = sci.getItem();
            BigDecimal pounds = sci.getPounds();

            if (pounds != null) {
                price = item.getRetailPricePerPound()
                            .multiply(pounds)
                            .setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                price = new BigDecimal("0.00");
                sci.setPounds(new BigDecimal("0.0"));
            }

            sci.setPrice(price);
            total = total.add(sci.getPrice())
                         .setScale(2);
            cart.setTotal(total);
        }
    }

    public String continueShopping() {
        clear();

        return "continue";
    }

    /**
     * Returns a localized text by looking up the resource bundle with the
     * given basename and key.
     */
    public static String loadErrorMessage(
        FacesContext context,
        String basename,
        String key) {
        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle(
                            basename,
                            context.getViewRoot().getLocale());
            } catch (Exception e) {
                return null;
            }
        }

        return bundle.getString(key);
    }
}
