/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import com.sun.tutorial.javaee.dukesbank.util.Debug;


/**
 * A simple utilities class.
 */
public class Util {
    public enum Navigation {
        accountHist,
        accountList,
        atm,
        atmAck,
        error,
        logout,
        main,
        transferAck,
        transferFunds;
        public Object action() {
            return this;
        }
    }

    private static String RESOURCE_BASE_NAME = null;
    private static Map<Locale, ResourceBundle> RESOURCES = new HashMap<Locale, ResourceBundle>(
                2);

    // ------------------------------------------------------------ Constructors
    private Util() {
    }

    // ---------------------------------------------------------- Public Methods
    public static ResourceBundle getBundle(FacesContext context) {
        if (RESOURCE_BASE_NAME == null) {
            RESOURCE_BASE_NAME = context.getApplication()
                                        .getMessageBundle();
        }

        Locale locale = context.getViewRoot()
                               .getLocale();
        ResourceBundle bundle = RESOURCES.get(locale);

        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle(RESOURCE_BASE_NAME, locale);
                RESOURCES.put(locale, bundle);
            } catch (Exception e) {
                Debug.print("Could not locate resource bundle.");
            }
        }

        return bundle;
    }

    public static String getString(
        FacesContext context,
        String key) {
        String text;

        try {
            text = getBundle(context)
                       .getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        return text;
    }

    public static void addMessage(
        FacesContext context,
        String clientId,
        String key,
        FacesMessage.Severity severity,
        Object... params) {
        // Look up the requested message text
        String text = getString(context, key);

        // Perform the requested substitutions
        if ((params != null) && (params.length > 0)) {
            text = MessageFormat.format(text, params);
        }

        // Construct and add a FacesMessage containing it
        context.addMessage(
            clientId,
            new FacesMessage(severity, text, text));

        // If debug is enabled, print the message to the log
        Debug.print(text);
    }

    public static void addErrorMessage(
        FacesContext context,
        String clientId,
        String key,
        Object... params) {
        addMessage(context, clientId, key, FacesMessage.SEVERITY_ERROR, params);
    }

    public static void addInfoMessage(
        FacesContext context,
        String clientId,
        String key,
        Object... params) {
        addMessage(context, clientId, key, FacesMessage.SEVERITY_INFO, params);
    }

    public static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance()
                           .getExternalContext();
    }
}
