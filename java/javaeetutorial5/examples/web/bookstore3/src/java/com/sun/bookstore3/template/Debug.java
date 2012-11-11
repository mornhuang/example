/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore3.template;

public final class Debug {
    public static final boolean debuggingOn = true;

    public static void print(String msg) {
        if (debuggingOn) {
            System.err.print(msg);
        }
    }

    public static void println(String msg) {
        if (debuggingOn) {
            System.err.println(msg);
        }
    }

    public static void print(
        Exception e,
        String msg) {
        print((Throwable) e, msg);
    }

    public static void print(Exception e) {
        print(e, null);
    }

    public static void print(
        Throwable t,
        String msg) {
        if (debuggingOn) {
            System.err.println(
                    "Received throwable with Message: " + t.getMessage());

            if (msg != null) {
                System.err.print(msg);
            }

            t.printStackTrace();
        }
    }

    public static void print(Throwable t) {
        print(t, null);
    }
}
