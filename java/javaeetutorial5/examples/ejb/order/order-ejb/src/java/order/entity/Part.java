/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
@IdClass(order.entity.PartKey.class)
@Entity
@Table(name = "EJB_ORDER_PART")
@SecondaryTable(name = "EJB_ORDER_PART_DETAIL", pkJoinColumns =  {
    @PrimaryKeyJoinColumn(name = "PARTNUMBER", referencedColumnName = "PARTNUMBER")
    , @PrimaryKeyJoinColumn(name = "REVISION", referencedColumnName = "REVISION")
}
)
public class Part implements java.io.Serializable {
    private Collection<Part> parts;
    private Date revisionDate;
    private Part bomPart;
    private Serializable drawing;
    private String description;
    private String partNumber;
    private String specification;
    private VendorPart vendorPart;
    private int revision;

    public Part() {
    }

    public Part(
        String partNumber,
        int revision,
        String description,
        Date revisionDate,
        String specification,
        Serializable drawing) {
        this.partNumber = partNumber;
        this.revision = revision;
        this.description = description;
        this.revisionDate = revisionDate;
        this.specification = specification;
        this.drawing = drawing;
    }

    @Id
    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    @Id
    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(DATE)
    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    @Column(table = "EJB_ORDER_PART_DETAIL")
    @Lob
    public Serializable getDrawing() {
        return drawing;
    }

    public void setDrawing(Serializable drawing) {
        this.drawing = drawing;
    }

    @Column(table = "EJB_ORDER_PART_DETAIL")
    @Lob
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "BOMPARTNUMBER",referencedColumnName = "PARTNUMBER")
        , @JoinColumn(name = "BOMREVISION", referencedColumnName = "REVISION")
    })
    public Part getBomPart() {
        return bomPart;
    }

    public void setBomPart(Part bomPart) {
        this.bomPart = bomPart;
    }

    @OneToMany(mappedBy = "bomPart")
    public Collection<Part> getParts() {
        return parts;
    }

    public void setParts(Collection<Part> parts) {
        this.parts = parts;
    }

    @OneToOne(mappedBy = "part")
    public VendorPart getVendorPart() {
        return vendorPart;
    }

    public void setVendorPart(VendorPart vendorPart) {
        this.vendorPart = vendorPart;
    }
}
