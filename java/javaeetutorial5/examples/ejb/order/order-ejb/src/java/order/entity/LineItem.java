/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@IdClass(order.entity.LineItemKey.class)
@Entity
@Table(name = "EJB_ORDER_LINEITEM")
@NamedQuery(name = "findAllLineItems", query = "SELECT l " + "FROM LineItem l")
public class LineItem implements java.io.Serializable {
    private Integer orderId;
    private Order order;
    private VendorPart vendorPart;
    private int itemId;
    private int quantity;

    public LineItem() {
    }

    public LineItem(
        Order order,
        int quantity,
        VendorPart vendorPart) {
        this.order = order;
        this.itemId = order.getNextId();
        this.orderId = order.getOrderId();
        this.quantity = quantity;
        this.vendorPart = vendorPart;
    }

    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JoinColumn(name = "VENDORPARTNUMBER")
    @ManyToOne
    public VendorPart getVendorPart() {
        return vendorPart;
    }

    public void setVendorPart(VendorPart vendorPart) {
        this.vendorPart = vendorPart;
    }

    @ManyToOne
    @JoinColumn(name = "ORDERID")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
