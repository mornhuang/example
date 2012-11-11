/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.persistence.Transient;


@Entity
@Table(name = "EJB_ORDER_ORDER")
@NamedQuery(name = "findAllOrders", query = "SELECT o FROM Order o")
public class Order implements java.io.Serializable {
    private Collection<LineItem> lineItems;
    private Date lastUpdate;
    private Integer orderId;
    private String shipmentInfo;
    private char status;
    private int discount;

    public Order() {
    }

    public Order(
        Integer orderId,
        char status,
        int discount,
        String shipmentInfo) {
        this.orderId = orderId;
        this.status = status;
        this.discount = discount;
        this.shipmentInfo = shipmentInfo;
        this.lastUpdate = new Date();
        this.lineItems = new ArrayList<LineItem>();
    }

    @Id
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Temporal(TIMESTAMP)
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(String shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }

    @OneToMany(cascade = ALL, mappedBy = "order")
    public Collection<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Collection<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public double calculateAmmount() {
        double ammount = 0;
        Collection<LineItem> items = getLineItems();

        for (Iterator it = items.iterator(); it.hasNext();) {
            LineItem item = (LineItem) it.next();
            VendorPart part = item.getVendorPart();
            ammount += (part.getPrice() * item.getQuantity());
        }

        return (ammount * (100 - getDiscount())) / 100;
    }

    public void addLineItem(LineItem lineItem) {
        this.getLineItems()
            .add(lineItem);
    }

    @Transient
    public int getNextId() {
        return this.lineItems.size() + 1;
    }
}
