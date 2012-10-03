/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore3.template;

public class Parameter {
    private String name;
    private String value;
    private boolean isDirect;

    public Parameter(
        String name,
        String value,
        boolean isDirect) {
        this.name = name;
        this.isDirect = isDirect;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public boolean isDirect() {
        return isDirect;
    }

    public String getValue() {
        return value;
    }
}
