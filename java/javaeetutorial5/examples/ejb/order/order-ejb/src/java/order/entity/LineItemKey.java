/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import java.io.Serializable;


public class LineItemKey implements Serializable {
    private Integer orderId;
    private int itemId;

    public LineItemKey() {
    }

    public LineItemKey(
        Integer orderId,
        int itemId) {
        this.setOrderId(orderId);
        this.setItemId(itemId);
    }

    public int hashCode() {
        return (((this.getOrderId() == null) ? 0 : this.getOrderId()
                                                       .hashCode())
        ^ ((int) this.getItemId()));
    }

    public boolean equals(Object otherOb) {
        if (this == otherOb) {
            return true;
        }

        if (!(otherOb instanceof LineItemKey)) {
            return false;
        }

        LineItemKey other = (LineItemKey) otherOb;

        return (((this.getOrderId() == null) ? (other.getOrderId() == null)
                                             : this.getOrderId()
                                                   .equals(other.getOrderId()))
        && (this.getItemId() == other.getItemId()));
    }

    public String toString() {
        return "" + getOrderId() + "-" + getItemId();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
