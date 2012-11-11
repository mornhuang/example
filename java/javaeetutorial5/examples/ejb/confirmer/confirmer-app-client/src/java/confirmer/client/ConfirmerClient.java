/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package confirmer.client;

import confirmer.ejb.Confirmer;
import javax.ejb.EJB;


/**
 *
 * @author ie139813
 */
public class ConfirmerClient {
    @EJB
    private static Confirmer confirmer;

    /** Creates a new instance of ConfirmerClient */
    public ConfirmerClient() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String recipient = null;

        if (args.length == 1) {
            recipient = args[0];
        } else {
            recipient = "pig.bodine@example.com";
        }

        try {
            confirmer.sendNotice(recipient);
            System.out.println("Message sent to " + recipient + ".");
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
