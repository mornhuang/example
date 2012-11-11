/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web.template;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class InsertTag extends SimpleTagSupport {
    private String definitionName = null;
    private String parameterName = null;

    public InsertTag() {
        super();
    }

    public void setParameter(String parameter) {
        this.parameterName = parameter;
    }

    public void setDefinition(String name) {
        this.definitionName = name;
    }

    public void doTag() throws JspTagException {
        Definition definition = null;
        Parameter parameter = null;
        boolean directInclude = false;
        PageContext context = (PageContext) getJspContext();

        // get the definition from the page context
        definition = (Definition) context.getAttribute(
                    definitionName,
                    context.APPLICATION_SCOPE);

        // get the parameter
        if ((parameterName != null) && (definition != null)) {
            parameter = (Parameter) definition.getParam(parameterName);
        }

        if (parameter != null) {
            directInclude = parameter.isDirect();
        }

        try {
            // if parameter is direct, print to out
            if (directInclude && (parameter != null)) {
                context.getOut()
                       .print(parameter.getValue());
            }
            // if parameter is indirect, include results of dispatching to page 
            else {
                if ((parameter != null) && (parameter.getValue() != null)) {
                    context.include(parameter.getValue());
                }
            }
        } catch (Exception ex) {
            Throwable rootCause = null;

            if (ex instanceof ServletException) {
                rootCause = ((ServletException) ex).getRootCause();
            }

            throw new JspTagException(
                ex.getMessage(),
                rootCause);
        }
    }
}
