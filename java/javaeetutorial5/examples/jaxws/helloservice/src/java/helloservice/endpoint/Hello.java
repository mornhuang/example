/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package helloservice.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public class Hello {
    private String message = new String("Hello, ");

    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }
}
