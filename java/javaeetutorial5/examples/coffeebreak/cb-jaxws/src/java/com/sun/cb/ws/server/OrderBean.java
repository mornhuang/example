/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.server;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class OrderBean implements Serializable {
    private AddressBean address;
    private BigDecimal total;
    private CustomerBean customer;
    private List<LineItemBean> lineItems;
    private String id;

    public OrderBean() {
    }

    public OrderBean(
        AddressBean address,
        CustomerBean customer,
        String id,
        List<LineItemBean> lineItems,
        BigDecimal total) {
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.lineItems = lineItems;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<LineItemBean> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemBean> lineItems) {
        this.lineItems = lineItems;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }
}
