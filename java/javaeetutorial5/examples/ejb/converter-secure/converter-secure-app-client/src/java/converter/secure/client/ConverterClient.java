/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


/*
 * Client.java
 *
 * Created on January 20, 2006, 4:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package converter.secure.client;

import converter.secure.ejb.Converter;
import java.math.BigDecimal;
import javax.ejb.EJB;


/**
 *
 * @author ian
 */
public class ConverterClient {
    @EJB
    private static Converter converter;

    /** Creates a new instance of Client */
    public ConverterClient(String[] args) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConverterClient client = new ConverterClient(args);
        client.doConversion();
    }

    public void doConversion() {
        try {
            BigDecimal param = new BigDecimal("100.00");
            BigDecimal yenAmount = converter.dollarToYen(param);

            System.out.println("$" + param + " is " + yenAmount + " Yen.");

            BigDecimal euroAmount = converter.yenToEuro(yenAmount);
            System.out.println(yenAmount + " Yen is " + euroAmount + " Euro.");

            System.exit(0);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
    }
}
