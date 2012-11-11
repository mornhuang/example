/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.client;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.EJB;
import order.request.Request;


public class OrderClient {
    @EJB
    private static Request request;
    private static MessageFormat mf = new MessageFormat(
                ": {0, number, $#,##0.##}");

    /** Creates a new instance of OrderClient */
    public OrderClient(String[] args) {
    }

    public static void main(String[] args) {
        OrderClient client = new OrderClient(args);

        try {
            client.createData();
            client.printData();
        } catch (Exception ex) {
            System.err.println("Caught an exception in main():");
            ex.printStackTrace();
        }
    }

    private static void createData() {
        try {
            request.createPart(
                    "1234-5678-01",
                    1,
                    "ABC PART",
                    new java.util.Date(),
                    "PARTQWERTYUIOPASXDCFVGBHNJMKL",
                    null);
            request.createPart(
                    "9876-4321-02",
                    2,
                    "DEF PART",
                    new java.util.Date(),
                    "PARTQWERTYUIOPASXDCFVGBHNJMKL",
                    null);
            request.createPart(
                    "5456-6789-03",
                    3,
                    "GHI PART",
                    new java.util.Date(),
                    "PARTQWERTYUIOPASXDCFVGBHNJMKL",
                    null);
            request.createPart(
                    "ABCD-XYZW-FF",
                    5,
                    "XYZ PART",
                    new java.util.Date(),
                    "PARTQWERTYUIOPASXDCFVGBHNJMKL",
                    null);
            request.createPart(
                    "SDFG-ERTY-BN",
                    7,
                    "BOM PART",
                    new java.util.Date(),
                    "PARTQWERTYUIOPASXDCFVGBHNJMKL",
                    null);

            request.addPartToBillOfMaterial(
                    "SDFG-ERTY-BN",
                    7,
                    "1234-5678-01",
                    1);
            request.addPartToBillOfMaterial(
                    "SDFG-ERTY-BN",
                    7,
                    "9876-4321-02",
                    2);
            request.addPartToBillOfMaterial(
                    "SDFG-ERTY-BN",
                    7,
                    "5456-6789-03",
                    3);
            request.addPartToBillOfMaterial(
                    "SDFG-ERTY-BN",
                    7,
                    "ABCD-XYZW-FF",
                    5);

            request.createVendor(
                    100,
                    "WidgetCorp",
                    "111 Main St., Anytown, KY 99999",
                    "Mr. Jones",
                    "888-777-9999");
            request.createVendor(
                    200,
                    "Gadget, Inc.",
                    "123 State St., Sometown, MI 88888",
                    "Mrs. Smith",
                    "866-345-6789");

            request.createVendorPart("1234-5678-01", 1, "PART1", 100.00, 100);
            request.createVendorPart("9876-4321-02", 2, "PART2", 10.44, 200);
            request.createVendorPart("5456-6789-03", 3, "PART3", 76.23, 200);
            request.createVendorPart("ABCD-XYZW-FF", 5, "PART4", 55.19, 100);
            request.createVendorPart("SDFG-ERTY-BN", 7, "PART5", 345.87, 100);

            Integer orderId = new Integer(1111);
            request.createOrder(
                    orderId,
                    'N',
                    10,
                    "333 New Court, NewCity, CA 90000");
            request.addLineItem(orderId, "1234-5678-01", 1, 3);
            request.addLineItem(orderId, "9876-4321-02", 2, 5);
            request.addLineItem(orderId, "ABCD-XYZW-FF", 5, 7);

            orderId = new Integer(4312);
            request.createOrder(
                    orderId,
                    'N',
                    0,
                    "333 New Court, NewCity, CA 90000");
            request.addLineItem(orderId, "SDFG-ERTY-BN", 7, 1);
            request.addLineItem(orderId, "ABCD-XYZW-FF", 5, 3);
            request.addLineItem(orderId, "1234-5678-01", 1, 15);
        } catch (Exception ex) {
            System.err.println("Caught an exception in createData():");
            ex.printStackTrace();
        }
    }

    private static void printData() {
        try {
            double price = request.getBillOfMaterialPrice(
                        "SDFG-ERTY-BN",
                        7,
                        null,
                        0);
            System.out.println(
                    "Cost of Bill of Material for PN " + "SDFG-ERTY-BN"
                    + " Rev: " + 7
                    + mf.format(new Object[] { new Double(price) }));

            printCostOfOrders();

            System.out.println("\nAdding 5% discount");
            request.adjustOrderDiscount(5);
            printCostOfOrders();

            System.out.println("\nRemoving 7% discount");
            request.adjustOrderDiscount(-7);
            printCostOfOrders();

            java.lang.Double price0 = request.getAvgPrice();

            if (price0 == null) {
                System.out.println("\nNo parts found");
            } else {
                System.out.println(
                        "\nAverage price of all parts"
                        + mf.format(new Object[] { price0 }));
            }

            price0 = request.getTotalPricePerVendor(100);

            if (price0 == null) {
                System.out.println("\nNo parts found for Vendor " + 100);
            } else {
                System.out.println(
                        "\nTotal price of parts for Vendor " + 100 + ""
                        + mf.format(new Object[] { price0 }));
            }

            System.out.println("\nOrdered list of vendors for order 1111");
            System.out.println(request.reportVendorsByOrder(new Integer(1111)));

            System.out.println("Counting all line items");

            int count = request.countAllItems();
            System.out.println("Found " + count + " line items");

            System.out.println("\nRemoving Order 4312");
            request.removeOrder(new Integer(4312));

            System.out.println("Counting all line items");
            count = request.countAllItems();
            System.out.println("Found " + count + " line items");

            Collection names = request.locateVendorsByPartialName("I");
            System.out.println(
                    "\nFound " + names.size()
                    + " out of 2 vendors with 'I' in the name:");

            for (Iterator it = names.iterator(); it.hasNext();) {
                System.out.println(it.next());
            }
        } catch (Exception ex) {
            System.err.println("Caught an exception in printData():");
            ex.printStackTrace();
        }
    }

    private static void printCostOfOrders() {
        Integer orderId = new Integer(1111);
        double price = request.getOrderPrice(orderId);
        System.out.println(
                "Cost of Order " + orderId
                + mf.format(new Object[] { new Double(price) }));

        orderId = new Integer(4312);
        price = request.getOrderPrice(orderId);
        System.out.println(
                "Cost of Order " + orderId
                + mf.format(new Object[] { new Double(price) }));
    }
}
