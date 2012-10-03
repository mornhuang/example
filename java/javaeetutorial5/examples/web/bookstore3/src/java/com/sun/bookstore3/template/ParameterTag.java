/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore3.template;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.PageContext;
import java.util.*;


public class ParameterTag extends SimpleTagSupport {
    private String isDirectString = null;
    private String paramName = null;
    private String paramValue = null;

    public ParameterTag() {
        super();
    }

    public void setName(String paramName) {
        this.paramName = paramName;
    }

    public void setValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public void setDirect(String isDirectString) {
        this.isDirectString = isDirectString;
    }

    public void doTag() {
        boolean isDirect = false;

        if ((isDirectString != null)
                && isDirectString.toLowerCase()
                                     .equals("true")) {
            isDirect = true;
        }

        try {
            // retrieve the parameters list
            if (paramName != null) {
                ArrayList<Parameter> parameters = ((ScreenTag) getParent())
                    .getParameters();

                if (parameters != null) {
                    Parameter param = new Parameter(
                                paramName,
                                paramValue,
                                isDirect);
                    parameters.add(param);
                } else {
                    Debug.println("ParameterTag: parameters do not exist.");
                }
            }
        } catch (Exception e) {
            Debug.println("ParameterTag: error in doTag: " + e);
        }
    }
}
