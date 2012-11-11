/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web.template;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


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
            Iterator nr = null;

            if (params != null) {
                nr = params.iterator();

                while (nr.hasNext()) {
                    System.out.println(((Parameter) nr.next()).getName());
                }
            }

            Set screenset = screens.keySet();
            Object[] anArray;
            anArray = new Object[screenset.size()];
            anArray = screenset.toArray();

            for (int i = 0; i < anArray.length; i++) {
                String s = new String();
                s = (String) anArray[i];
                System.out.print(anArray[i]);
            }

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
