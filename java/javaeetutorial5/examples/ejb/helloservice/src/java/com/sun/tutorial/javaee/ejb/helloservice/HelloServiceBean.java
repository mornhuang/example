/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.ejb.helloservice;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;


/**
 * HelloServiceBean is a web service endpoint implemented as a stateless
 * session bean.
 * Created Jan 9, 2006 3:07:38 PM
 * @author ian
 */
@Stateless
@WebService
public class HelloServiceBean {
    private String message = "Hello, ";

    public void HelloServiceBean() {
    }

    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }
}
