/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore6.backing;

import com.sun.bookstore6.backing.AbstractBean;
import com.sun.bookstore6.listeners.AreaSelectedEvent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.HashMap;
import java.util.Locale;


/**
 * <p>This is the main bean for the application.  It maintains a
 * <code>Map</code> of {@link CarBean} instances, keyed by model name,
 * and a <code>Map</code> of {@link CarCustomizer} instances, keyed by
 * package name.  The <code>CarBean</code> instances in the model
 * <code>Map</code> are accessed from several pages, as described
 * below.</p>
 *
 * <p>Several pages in the application use this bean as the target of
 * method reference and value reference expressions.</p>
 *
 * <ul>
 *
 * <li><p>The "chooseLocale" page uses <code>actionListener</code>
 * attributes to point to the {@link #chooseLocaleFromMap} and {@link
 * #chooseLocaleFromLink} methods.</p></li>
 *
 * <li><p>The "storeFront" page uses value binding expressions to pull
 * information about four of the known car models in the store.</p></li>
 *
 * <li><p>The "carDetail" page uses value binding expressions to pull
 * information about the currently chosen model.  It also uses the
 * <code>action</code> attribute to convey the user's package
 * choices.</p></li>
 *
 * <li><p>The "confirmChoices" page uses value binding expressions to
 * pull the user's choices from the currently chosen model.</p></li>
 *
 * </ul>
 */
public class LocaleBean extends AbstractBean {
    /**
     * <p>The locales to be selected for each hotspot, keyed by the
     * alternate text for that area.</p>
     */
    private HashMap<String, Locale> locales = null;

    public LocaleBean() {
        locales = new HashMap<String, Locale>(4);
        locales.put(
            "NAmerica",
            new Locale("en", "US"));
        locales.put(
            "SAmerica",
            new Locale("es", "MX"));
        locales.put(
            "Germany",
            new Locale("de", "DE"));
        locales.put(
            "France",
            new Locale("fr", "FR"));
    }

    // 
    // ActionListener handlers
    //
    public void chooseLocaleFromMap(ActionEvent actionEvent) {
        AreaSelectedEvent event = (AreaSelectedEvent) actionEvent;
        String current = event.getMapComponent()
                              .getCurrent();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot()
               .setLocale((Locale) locales.get(current));
    }

    public void chooseLocaleFromLink(ActionEvent event) {
        String current = event.getComponent()
                              .getId();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot()
               .setLocale((Locale) locales.get(current));
    }
}
