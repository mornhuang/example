/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf.messages;

import java.util.*;


public class CBMessages_en extends ListResourceBundle {
    static final Object[][] contents = {
            {
                "ServerError",
                "Your request cannot be completed.  The server got the following error: "
            },
            { "TitleServerError", "Server Error" },
            { "TitleOrderForm", "Order Form" },
            { "TitleCheckoutForm", "Checkout Form" },
            { "TitleCheckoutAck", "Confirmation" },
            {
                "OrderInstructions",
                "Enter the amount of coffee and click Update to update the totals.<br>Click Checkout to proceed with your order. "
            },
            { "OrderForm", "OrderForm" },
            { "Clear", "Clear" },
            { "Price", "Price" },
            { "Quantity", "Quantity" },
            { "Total", "Total" },
            { "Update", "Update" },
            { "Checkout", "Checkout" },
            {
                "CheckoutInstructions",
                "To complete your order, fill in the form and click Submit."
            },
            { "YourOrder", "Your order totals " },
            { "CheckoutForm", "Checkout Form" },
            { "FirstName", "First Name" },
            { "FirstNameError", "Please enter your first name." },
            { "LastName", "Last Name" },
            { "LastNameError", "Please enter your last name." },
            { "EMail", " E-Mail" },
            { "EMailError", "Please enter a valid e-mail address." },
            { "PhoneNumber", "Phone Number" },
            { "AreaCodeError", "Please enter your area code." },
            { "PhoneNumberError", "Please enter your phone number." },
            { "Street", "Street" },
            { "StreetError", "Please enter your street." },
            { "City", "City" },
            { "CityError", "Please enter your city." },
            { "State", "State" },
            { "StateError", "Please enter your state." },
            { "Zip", "Zip" },
            { "ZipError", "Please enter a valid zip code." },
            { "CCOption", "Credit Card" },
            { "CCNumber", "Credit Card Number" },
            { "CCNumberError", "Please enter your credit card number." },
            { "Submit", "Submit" },
            { "Reset", "Reset" },
            { "ItemPrice", "Price" },
            { "OrderConfirmed", "Your order has been confirmed." },
            { "ShipDate", "Ship Date" },
            { "Items", "Items" },
            { "Coffee", "Coffee" },
            { "Pounds", "Pounds" },
            { "ContinueShopping", "Continue Shopping" }
        };

    public Object[][] getContents() {
        return contents;
    }
}
