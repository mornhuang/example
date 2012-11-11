/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web;


/**
 * A managed bean to expose the Util.Navigation enum
 * as a managed bean.
 */
public class NavigationEnumBean extends EnumManagedBean {
    public NavigationEnumBean() {
        super(Util.Navigation.class);
    }
}
