/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web;

import javax.el.ELResolver;
import javax.el.ELContext;
import javax.el.PropertyNotFoundException;
import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Collections;


/**
 *  An <code>ELResolver</code> implementation to detect instances
 * of <code>EnumManagedBean</code> and invoke the <code>getEnum()</code>
 * method.
 */
public class EnumResolver extends ELResolver {
    public Object getValue(
        ELContext elContext,
        Object base,
        Object property) {
        if (((base != null) && (property != null))
                && base instanceof EnumManagedBean) {
            elContext.setPropertyResolved(true);

            return ((EnumManagedBean) base).getEnum(property.toString());
        }

        return null;
    }

    public Class<?> getCommonPropertyType(
        ELContext elContext,
        Object base) {
        return EnumManagedBean.class;
    }

    public Class<?> getType(
        ELContext elContext,
        Object base,
        Object property) {
        if (((base != null) && (property != null))
                && base instanceof EnumManagedBean) {
            elContext.setPropertyResolved(true);
            throw new PropertyNotFoundException();
        }

        return null;
    }

    public void setValue(
        ELContext elContext,
        Object base,
        Object property,
        Object value) {
        if (((base != null) && (property != null))
                && base instanceof EnumManagedBean) {
            elContext.setPropertyResolved(true);
            throw new PropertyNotFoundException(); // readonly
        }
    }

    public boolean isReadOnly(
        ELContext elContext,
        Object base,
        Object property) {
        if (((base != null) && (property != null))
                && base instanceof EnumManagedBean) {
            elContext.setPropertyResolved(true);

            return true; // readonly
        }

        return false;
    }

    public Iterator<FeatureDescriptor> getFeatureDescriptors(
        ELContext elContext,
        Object base) {
        return Collections.<FeatureDescriptor>emptyList()
                          .iterator();
    }
}
