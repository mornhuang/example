/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web.template;

import java.util.HashMap;


public class Definition {
    private HashMap params = new HashMap();

    public void setParam(Parameter p) {
        params.put(
            p.getName(),
            p);
    }

    public Parameter getParam(String name) {
        return (Parameter) params.get(name);
    }
}
