/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import com.sun.cb.ws.server.AddressBean;
import com.sun.cb.ws.server.CustomerBean;
import com.sun.cb.ws.server.LineItemBean;
import com.sun.cb.common.URLHelper;
import com.sun.cb.saaj.OrderRequest;
import com.sun.cb.ws.client.OrderCaller;
import com.sun.cb.ws.server.ConfirmationBean;
import com.sun.cb.ws.server.OrderBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.el.ELContext;
import javax.el.ValueExpression;


public class CheckoutFormBean {
    static final Logger logger = Logger.getLogger(
                "com.sun.cb.jsf.CheckoutFormBean");
    private OrderConfirmations ocs = null;
    private String CCNumber;
    private String areaCode;
    private String city;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String state;
    private String street;
    private String zip;
    private int CCOption;

    /**
     * <p>Backing file bean for checkoutForm of CoffeeBreak demo.</p>
     */
    public CheckoutFormBean() {
    }

    public OrderConfirmations getOrderConfirmations() {
        return ocs;
    }

    public void setOrderConfirmations(OrderConfirmations newOrders) {
        this.ocs = ocs;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getZip() {
        return zip;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getCCOption() {
        return CCOption;
    }

    public String getCCNumber() {
        return CCNumber;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCCOption(int CCOption) {
        this.CCOption = CCOption;
    }

    public void setCCNumber(String CCNumber) {
        this.CCNumber = CCNumber;
    }

    /*
     * Processes the customer order by forwarding the order details to the supplier.
     */
    public String submit() {
        ocs = new OrderConfirmations();

        FacesContext context = FacesContext.getCurrentInstance();
        ELContext elContext = context.getCurrentInstance()
                                     .getELContext();
        ValueExpression ve = context.getApplication()
                                    .getExpressionFactory()
                                    .createValueExpression(
                    elContext,
                    "#{CoffeeBreakBean}",
                    Object.class);
        CoffeeBreakBean cbBean = (CoffeeBreakBean) ve.getValue(elContext);

        RetailPriceList rpl = cbBean.getRetailPriceList();
        ShoppingCart cart = cbBean.getCart();

        ConfirmationBean confirmation = null;
        String orderId = CCNumber;

        AddressBean address = new AddressBean();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);

        CustomerBean customer = new CustomerBean();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber("(" + areaCode + ") " + phoneNumber);
        customer.setEmailAddress(email);

        for (Iterator d = rpl.getSuppliers()
                             .iterator(); d.hasNext();) {
            String supplier = (String) d.next();
            logger.info(supplier);

            List<LineItemBean> lis = new ArrayList<LineItemBean>();
            BigDecimal price = new BigDecimal("0.00");
            BigDecimal total = new BigDecimal("0.00");

            for (Iterator c = cart.getItems()
                                  .iterator(); c.hasNext();) {
                ShoppingCartItem sci = (ShoppingCartItem) c.next();

                if ((sci.getItem()
                            .getSupplier()).equals(supplier)
                        && ((sci.getPounds()).floatValue() > 0)) {
                    price = sci.getItem()
                               .getWholesalePricePerPound()
                               .multiply(sci.getPounds());
                    total = total.add(price);

                    LineItemBean li = new LineItemBean();
                    li.setCoffeeName(sci.getItem().getCoffeeName());
                    li.setPounds(sci.getPounds());
                    li.setPrice(sci.getItem().getWholesalePricePerPound());
                    lis.add(li);
                }
            }

            if (!lis.isEmpty()) {
                logger.info("creating OrderBean");

                OrderBean order = new OrderBean();
                order.setAddress(address);
                order.setCustomer(customer);
                order.setId(orderId);

                int i = 0;

                for (Iterator<LineItemBean> j = lis.iterator(); j.hasNext();) {
                    order.getLineItems()
                         .add(j.next());
                    i++;
                }

                order.setTotal(total);

                String SAAJOrderURL = URLHelper.getSaajURL() + "/orderCoffee";

                if (supplier.equals(SAAJOrderURL)) {
                    logger.info("creating OrderRequest for" + SAAJOrderURL);

                    OrderRequest or = new OrderRequest(SAAJOrderURL);
                    confirmation = or.placeOrder(order);
                } else {
                    logger.info("creating OrderCaller");

                    OrderCaller ocaller = new OrderCaller(supplier);
                    confirmation = ocaller.placeOrder(order);
                }

                logger.info("crating OrderConfirmation");

                OrderConfirmation oc = new OrderConfirmation(
                            order,
                            confirmation);
                ocs.add(oc);
            }
        }

        logger.info("returning submit");

        return "submit";
    }

    /*
     * Clears the Customer information.
     */
    public String clear() {
        firstName = "";
        lastName = "";
        email = "";
        areaCode = "";
        phoneNumber = "";
        street = "";
        city = "";
        state = "";
        zip = "";
        CCOption = 0;
        CCNumber = "";

        return null;
    }

    /**
     * Validates the "email" field of checkoutForm. If it does not follow
     * the expected syntax, queues an error message.
     */
    public void validateEmail(
        FacesContext context,
        UIComponent toValidate,
        Object value) {
        logger.info("validateEmail");

        String message = "";

        String email = (String) value;

        if (email.indexOf('@') == -1) {
            ((UIInput) toValidate).setValid(false);
            message = CoffeeBreakBean.loadErrorMessage(
                        context,
                        CoffeeBreakBean.CB_RESOURCE_BUNDLE_NAME,
                        "EMailError");
            context.addMessage(
                    toValidate.getClientId(context),
                    new FacesMessage(message));
        }
    }
}
