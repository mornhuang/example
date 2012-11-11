/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package simpleclient;

import javax.xml.ws.WebServiceRef;
import helloservice.endpoint.HelloService;
import helloservice.endpoint.Hello;


public class HelloClient {
    @WebServiceRef(wsdlLocation = "http://localhost:8080/helloservice/hello?wsdl")
    static HelloService service;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            HelloClient client = new HelloClient();
            client.doTest(args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void doTest(String[] args) {
        try { // Call Web Service Operation
            System.out.println(
                    "Retrieving the port from the following service: "
                    + service);

            Hello port = service.getHelloPort();

            System.out.println("Invoking the sayHello operation on the port.");

            String name;

            if (args.length > 0) {
                name = args[0];
            } else {
                name = "No Name";
            }

            String response = port.sayHello(name);
            System.out.println(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
