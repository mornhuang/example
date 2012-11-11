/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "EJB_ORDER_VENDOR_PART")
@NamedQueries({
    @NamedQuery(name = "findAverageVendorPartPrice",query = "SELECT AVG(vp.price) "
    + "FROM VendorPart vp")
    , @NamedQuery(name = "findTotalVendorPartPricePerVendor", query = "SELECT SUM(vp.price) "
    + "FROM VendorPart vp " + "WHERE vp.vendor.vendorId = :id")
})
public class VendorPart implements java.io.Serializable {
    private Long vendorPartNumber;
    private Part part;
    private String description;
    private Vendor vendor;
    private double price;

    public VendorPart() {
    }

    public VendorPart(
        String description,
        double price,
        Part part) {
        this.description = description;
        this.price = price;
        this.part = part;
        part.setVendorPart(this);
    }

    @TableGenerator(name = "vendorPartGen", table = "EJB_ORDER_SEQUENCE_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "VENDOR_PART_ID", allocationSize = 10)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "vendorPartGen")
    public Long getVendorPartNumber() {
        return vendorPartNumber;
    }

    public void setVendorPartNumber(Long vendorPartNumber) {
        this.vendorPartNumber = vendorPartNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "PARTNUMBER",referencedColumnName = "PARTNUMBER")
        , @JoinColumn(name = "PARTREVISION", referencedColumnName = "REVISION")
    })
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @JoinColumn(name = "VENDORID")
    @ManyToOne
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
