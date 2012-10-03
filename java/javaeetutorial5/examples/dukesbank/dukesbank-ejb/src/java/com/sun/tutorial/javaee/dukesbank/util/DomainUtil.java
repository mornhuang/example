/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.util;

import com.sun.tutorial.javaee.dukesbank.exception.IllegalAccountTypeException;


/**
 * This helper class methods for getting and checking
 * the domains of business entity variables.
 */
public final class DomainUtil {
    // The accountTypes array stores the valid account types.
    private static String[] accountTypes = {
            "Checking", "Savings", "Credit", "Money Market"
        };

    public static String[] getAccountTypes() {
        return accountTypes;
    }

    public static void checkAccountType(String type)
        throws IllegalAccountTypeException {
        boolean foundIt = false;

        for (int i = 0; i < accountTypes.length; i++) {
            if (accountTypes[i].equals(type)) {
                foundIt = true;
            }
        }

        if (foundIt == false) {
            throw new IllegalAccountTypeException(type);
        }
    }

    public static boolean isCreditAccount(String type) {
        if (type.equals("Credit")) {
            return true;
        } else {
            return false;
        }
    }
}
