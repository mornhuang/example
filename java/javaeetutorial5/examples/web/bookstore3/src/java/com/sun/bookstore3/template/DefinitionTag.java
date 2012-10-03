/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore3.template;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.*;


public class DefinitionTag extends SimpleTagSupport {
    private HashMap screens = null;
    private String definitionName = null;
    private String screenId;

    public DefinitionTag() {
        super();
    }

    public HashMap getScreens() {
        return screens;
    }

    public void setName(String name) {
        this.definitionName = name;
    }

    public void setScreen(String screenId) {
        this.screenId = screenId;
    }

    public void doTag() {
        try {
            screens = new HashMap();

            getJspBody()
                .invoke(null);

            Definition definition = new Definition();
            PageContext context = (PageContext) getJspContext();
            ArrayList params = (ArrayList) screens.get(screenId);
            Iterator ir = null;

            if (params != null) {
                ir = params.iterator();

                while (ir.hasNext()) {
                    definition.setParam((Parameter) ir.next());
                }

                // put the definition in the page context
                context.setAttribute(
                        definitionName,
                        definition,
                        context.APPLICATION_SCOPE);
            } else {
                Debug.println("DefinitionTag: params are not defined.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
