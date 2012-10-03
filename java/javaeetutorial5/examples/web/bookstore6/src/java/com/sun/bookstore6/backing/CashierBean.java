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
import com.sun.bookstore6.backing.AbstractBean;
import com.sun.bookstore6.converters.CreditCardConverter;
import com.sun.bookstore.database.Book;
import java.util.Date;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectBoolean;


/**
 * <p>Backing Bean for the <code>/bookcashier.jsp</code> page.</p>
 */
public class CashierBean extends AbstractBean {
    protected Date shipDate;

    // ---------------------------------------------------- Component Properties
    protected String name = null;
    protected String shippingOption = "2";
    protected String[] newsletters = new String[0];
    UIOutput specialOfferText = null;
    UIOutput thankYou = null;
    UISelectBoolean specialOffer = null;
    private CreditCardConverter creditCard = null;
    private String stringProperty = "This is a String property";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public CreditCardConverter getCreditCard() {
        if (creditCard == null) {
            return new CreditCardConverter();
        }

        return creditCard;
    }

    public void setCreditCard(CreditCardConverter newCreditCard) {
        this.creditCard = newCreditCard;
    }

    public void setNewsletters(String[] newsletters) {
        this.newsletters = newsletters;
    }

    public String[] getNewsletters() {
        return this.newsletters;
    }

    public Date getShipDate() {
        return this.shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public void setShippingOption(String shippingOption) {
        this.shippingOption = shippingOption;
    }

    public String getShippingOption() {
        return this.shippingOption;
    }

    public UIOutput getSpecialOfferText() {
        return this.specialOfferText;
    }

    public void setSpecialOfferText(UIOutput specialOfferText) {
        this.specialOfferText = specialOfferText;
    }

    public UISelectBoolean getSpecialOffer() {
        return this.specialOffer;
    }

    public void setSpecialOffer(UISelectBoolean specialOffer) {
        this.specialOffer = specialOffer;
    }

    public UIOutput getThankYou() {
        return this.thankYou;
    }

    public void setThankYou(UIOutput thankYou) {
        this.thankYou = thankYou;
    }

    public String getStringProperty() {
        return (this.stringProperty);
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    // ----------------------------------------------------- Application Actions
    public String submit() {
        // Subscribing to newsletters would go here

        // Calculate and save the ship date
        int days = Integer.valueOf(shippingOption)
                          .intValue();
        Date shipDate = new Date();
        shipDate = new Date(
                    shipDate.getTime() + ((long) days * (long) 86400000));
        setShipDate(shipDate);

        if ((cart()
                     .getTotal() > 100.00) && !specialOffer.isRendered()) {
            specialOfferText.setRendered(true);
            specialOffer.setRendered(true);

            return null;
        } else if (specialOffer.isRendered() && !thankYou.isRendered()) {
            thankYou.setRendered(true);

            return null;
        } else {
            try {
                updateInventory();
            } catch (Exception ex) {
                return "bookordererror";
            }

            clear();

            return ("receipt");
        }
    }
}
