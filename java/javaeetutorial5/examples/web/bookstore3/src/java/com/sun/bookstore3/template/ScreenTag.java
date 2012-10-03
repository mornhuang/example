/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore3.template;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.PageContext;
import java.util.*;


public class ScreenTag extends SimpleTagSupport {
    private ArrayList<Parameter> parameters = null;
    private String screenId;

    public ScreenTag() {
        super();
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public void doTag() {
        parameters = new ArrayList<Parameter>();

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
