/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.common;

import java.util.ResourceBundle;


public final class URLHelper {
    public static final String getSaajURL() {
        ResourceBundle registryBundle = ResourceBundle.getBundle(
                    "com.sun.cb.common.CoffeeBreak");

        String saajURL = registryBundle.getString("saaj.url");

        return saajURL;
    }

    public static final String getEndpointURL() {
        ResourceBundle registryBundle = ResourceBundle.getBundle(
                    "com.sun.cb.common.CoffeeBreak");

        String endPointURL = registryBundle.getString("endpoint.url");

        return endPointURL;
    }
} // class
