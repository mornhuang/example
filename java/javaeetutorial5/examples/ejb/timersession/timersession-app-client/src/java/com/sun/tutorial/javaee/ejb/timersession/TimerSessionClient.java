/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.ejb.timersession;

import javax.ejb.EJB;


public class TimerSessionClient {
    @EJB
    private static TimerSession timer;

    public TimerSessionClient(String[] args) {
    }

    public static void main(String[] args) {
        TimerSessionClient client = new TimerSessionClient(args);
        client.doClient();
    }

    public void doClient() {
        try {
            long intervalDuration = 8000;
            System.out.println(
                    "Creating a timer with an interval duration of "
                    + intervalDuration + " ms.");
            timer.setTimer(intervalDuration);

            System.exit(0);
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception.");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
