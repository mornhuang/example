/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.request;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Remote;


@Remote
public interface Request {
    void addLineItem(
        Integer orderId,
        String partNumber,
        int revision,
        int quantity);

    void addPartToBillOfMaterial(
        String bomPartNumber,
        int bomRevision,
        String partNumber,
        int revision);

    void adjustOrderDiscount(int adjustment);

    int countAllItems();

    void createOrder(
        Integer orderId,
        char status,
        int discount,
        String shipmentInfo);

    void createPart(
        String partNumber,
        int revision,
        String description,
        Date revisionDate,
        String specification,
        Serializable drawing);

    void createVendor(
        int vendorId,
        String name,
        String address,
        String contact,
        String phone);

    void createVendorPart(
        String partNumber,
        int revision,
        String description,
        double price,
        int vendorId);

    Double getAvgPrice();

    double getBillOfMaterialPrice(
        String bomPartNumber,
        int bomRevision,
        String partNumber,
        int revision);

    double getOrderPrice(Integer orderId);

    Double getTotalPricePerVendor(int vendorId);

    Collection<String> locateVendorsByPartialName(String name);

    void removeOrder(Integer orderId);

    String reportVendorsByOrder(Integer orderId);
}
