/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import java.util.Collection;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "EJB_ORDER_VENDOR")
@NamedQueries({
    @NamedQuery(name = "findVendorsByPartialName",query = "SELECT v "
    + "FROM Vendor v " + "WHERE LOCATE(:name, v.name) > 0")
    , @NamedQuery(name = "findVendorByOrder", query = "SELECT DISTINCT l.vendorPart.vendor "
    + "FROM Order o, IN(o.lineItems) l " + "WHERE o.orderId = :id "
    + "ORDER BY l.vendorPart.vendor.name")
})
public class Vendor implements java.io.Serializable {
    private Collection<VendorPart> vendorParts;
    private String address;
    private String contact;
    private String name;
    private String phone;
    private int vendorId;

    public Vendor() {
    }

    public Vendor(
        int vendorId,
        String name,
        String address,
        String contact,
        String phone) {
        this.vendorId = vendorId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.phone = phone;
    }

    @Id
    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    @Column(name = "VENDORNAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToMany(cascade = ALL, mappedBy = "vendor")
    public Collection<VendorPart> getVendorParts() {
        return vendorParts;
    }

    public void setVendorParts(Collection<VendorPart> vendorParts) {
        this.vendorParts = vendorParts;
    }

    public void addVendorPart(VendorPart vendorPart) {
        this.getVendorParts()
            .add(vendorPart);
    }
}
