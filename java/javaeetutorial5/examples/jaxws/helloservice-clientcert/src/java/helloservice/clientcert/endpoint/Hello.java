/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package helloservice.clientcert.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.DeclareRoles;


@WebService
@DeclareRoles("user")
public class Hello {
    private String message = new String("Hello, ");

    @RolesAllowed("user")
    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }
}
