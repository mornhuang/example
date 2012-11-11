/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package mypkg;

import java.util.*;
import java.text.DateFormat;


public class MyLocales {
    ArrayList<String> localeNames;
    HashMap<String, Locale> locales;
    Locale selectedLocale;
    String selectedLocaleString;

    public MyLocales() {
        locales = new HashMap<String, Locale>();
        localeNames = new ArrayList<String>();

        Locale[] list = DateFormat.getAvailableLocales();

        for (int i = 0; i < list.length; i++) {
            locales.put(
                list[i].getDisplayName(),
                list[i]);
            localeNames.add(list[i].getDisplayName());
        }

        Collections.sort(localeNames);
        selectedLocale = null;
        selectedLocaleString = null;
    }

    public static boolean equals(
        String l1,
        String l2) {
        return l1.equals(l2);
    }

    public Collection getLocaleNames() {
        return localeNames;
    }

    public void setSelectedLocaleString(String displayName) {
        this.selectedLocaleString = displayName;
        this.selectedLocale = (Locale) locales.get(displayName);
    }

    public Locale getSelectedLocale() {
        return selectedLocale;
    }

    public String getSelectedLocaleString() {
        return selectedLocaleString;
    }
}
