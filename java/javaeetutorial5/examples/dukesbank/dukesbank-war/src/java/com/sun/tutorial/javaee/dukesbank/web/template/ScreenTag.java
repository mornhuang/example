/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web.template;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.ArrayList;
import java.util.HashMap;


public class ScreenTag extends SimpleTagSupport {
    private ArrayList parameters = null;
    private String screenId;

    public ScreenTag() {
        super();
    }

    public ArrayList getParameters() {
        return parameters;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public void doTag() {
        parameters = new ArrayList();

        HashMap screens = (HashMap) ((DefinitionTag) getParent())
            .getScreens();

        if (screens != null) {
            try {
                if (!screens.containsKey(screenId)) {
                    screens.put(screenId, parameters);
                }

                getJspBody()
                    .invoke(null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Debug.println("ScreenTag: Unable to get screens object.");
        }
    }
}
