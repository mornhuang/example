/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import order.entity.*;


@Stateful
public class RequestBean implements Request {
    @PersistenceContext
    private EntityManager em;

    public void createPart(
        String partNumber,
        int revision,
        String description,
        java.util.Date revisionDate,
        String specification,
        Serializable drawing) {
        try {
            Part part = new Part(
                        partNumber,
                        revision,
                        description,
                        revisionDate,
                        specification,
                        drawing);
            em.persist(part);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public void addPartToBillOfMaterial(
        String bomPartNumber,
        int bomRevision,
        String partNumber,
        int revision) {
        try {
            PartKey bomKey = new PartKey();
            bomKey.setPartNumber(bomPartNumber);
            bomKey.setRevision(bomRevision);

            Part bom = em.find(Part.class, bomKey);

            PartKey partKey = new PartKey();
            partKey.setPartNumber(partNumber);
            partKey.setRevision(revision);

            Part part = em.find(Part.class, partKey);
            bom.getParts()
               .add(part);
            part.setBomPart(bom);
        } catch (EJBException e) {
            e.printStackTrace();
        }
    }

    public void createVendor(
        int vendorId,
        String name,
        String address,
        String contact,
        String phone) {
        try {
            Vendor vendor = new Vendor(vendorId, name, address, contact, phone);
            em.persist(vendor);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public void createVendorPart(
        String partNumber,
        int revision,
        String description,
        double price,
        int vendorId) {
        try {
            PartKey pkey = new PartKey();
            pkey.setPartNumber(partNumber);
            pkey.setRevision(revision);

            Part part = em.find(Part.class, pkey);

            VendorPart vendorPart = new VendorPart(description, price, part);
            em.persist(vendorPart);

            Vendor vendor = em.find(Vendor.class, vendorId);
            vendor.addVendorPart(vendorPart);
            vendorPart.setVendor(vendor);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void createOrder(
        Integer orderId,
        char status,
        int discount,
        String shipmentInfo) {
        try {
            Order order = new Order(orderId, status, discount, shipmentInfo);
            em.persist(order);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void addLineItem(
        Integer orderId,
        String partNumber,
        int revision,
        int quantity) {
        try {
            Order order = em.find(Order.class, orderId);

            PartKey pkey = new PartKey();
            pkey.setPartNumber(partNumber);
            pkey.setRevision(revision);

            Part part = em.find(Part.class, pkey);

            LineItem lineItem = new LineItem(
                        order,
                        quantity,
                        part.getVendorPart());
            order.addLineItem(lineItem);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public double getBillOfMaterialPrice(
        String bomPartNumber,
        int bomRevision,
        String partNumber,
        int revision) {
        double price = 0.0;

        try {
            PartKey bomkey = new PartKey();
            bomkey.setPartNumber(bomPartNumber);
            bomkey.setRevision(bomRevision);

            Part bom = em.find(Part.class, bomkey);
            Collection<Part> parts = bom.getParts();

            for (Iterator iterator = parts.iterator(); iterator.hasNext();) {
                Part part = (Part) iterator.next();
                VendorPart vendorPart = part.getVendorPart();
                price += vendorPart.getPrice();
            }
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }

        return price;
    }

    public double getOrderPrice(Integer orderId) {
        double price = 0.0;

        try {
            Order order = em.find(Order.class, orderId);
            price = order.calculateAmmount();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }

        return price;
    }

    public void adjustOrderDiscount(int adjustment) {
        try {
            List orders = em.createNamedQuery("findAllOrders")
                            .getResultList();

            for (Iterator it = orders.iterator(); it.hasNext();) {
                Order order = (Order) it.next();
                int newDiscount = order.getDiscount() + adjustment;
                order.setDiscount((newDiscount > 0) ? newDiscount : 0);
            }
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Double getAvgPrice() {
        try {
            return (Double) em.createNamedQuery("findAverageVendorPartPrice")
                              .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Double getTotalPricePerVendor(int vendorId) {
        try {
            return (Double) em.createNamedQuery(
                    "findTotalVendorPartPricePerVendor")
                              .setParameter("id", vendorId)
                              .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Collection<String> locateVendorsByPartialName(String name) {
        Collection<String> names = new ArrayList<String>();

        try {
            List vendors = em.createNamedQuery("findVendorsByPartialName")
                             .setParameter("name", name)
                             .getResultList();

            for (Iterator iterator = vendors.iterator(); iterator.hasNext();) {
                Vendor vendor = (Vendor) iterator.next();
                names.add(vendor.getName());
            }
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }

        return names;
    }

    public int countAllItems() {
        int count = 0;

        try {
            count = em.createNamedQuery("findAllLineItems")
                      .getResultList()
                      .size();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }

        return count;
    }

    public void removeOrder(Integer orderId) {
        try {
            Order order = em.find(Order.class, orderId);
            em.remove(order);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public String reportVendorsByOrder(Integer orderId) {
        StringBuffer report = new StringBuffer();

        try {
            List vendors = em.createNamedQuery("findVendorByOrder")
                             .setParameter("id", orderId)
                             .getResultList();

            for (Iterator iterator = vendors.iterator(); iterator.hasNext();) {
                Vendor vendor = (Vendor) iterator.next();
                report.append(vendor.getVendorId())
                      .append(' ')
                      .append(vendor.getName())
                      .append(' ')
                      .append(vendor.getContact())
                      .append('\n');
            }
        } catch (Exception e) {
            throw new EJBException(e);
        }

        return report.toString();
    }
}
