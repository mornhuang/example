/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package order.entity;

import java.io.Serializable;


/**
 *
 * @author ian
 */
public class PartKey implements Serializable {
    private String partNumber;
    private int revision;

    public int hashCode() {
        return (((this.getPartNumber() == null) ? 0
                                                : this.getPartNumber()
                                                      .hashCode())
        ^ ((int) this.getRevision()));
    }

    public boolean equals(Object otherOb) {
        if (this == otherOb) {
            return true;
        }

        if (!(otherOb instanceof PartKey)) {
            return false;
        }

        PartKey other = (PartKey) otherOb;

        return (((this.getPartNumber() == null) ? (other.getPartNumber() == null)
                                                : this.getPartNumber()
                                                      .equals(
                other.getPartNumber()))
        && (getRevision() == other.getRevision()));
    }

    public String toString() {
        return getPartNumber() + " rev" + this.getRevision();
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }
}
