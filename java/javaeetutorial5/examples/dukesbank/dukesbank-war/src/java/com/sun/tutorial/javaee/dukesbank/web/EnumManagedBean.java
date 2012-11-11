/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web;


/**
 * Exposes an Enum as a ManagedBean and resolvable
 * by a customer EnumVariable resolver.
 */
public abstract class EnumManagedBean {
    private Class<?extends Enum> e;

    protected EnumManagedBean(Class<?extends Enum> e) {
        this.e = e;
    }

    public Enum getEnum(String enumName) {
        return Enum.valueOf(e, enumName);
    }
}
