/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore6.listeners;

import javax.faces.event.ValueChangeListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.AbortProcessingException;
import javax.faces.context.FacesContext;


public class NameChanged extends Object implements ValueChangeListener {
    public void processValueChange(ValueChangeEvent event)
        throws AbortProcessingException {
        if (null != event.getNewValue()) {
            FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap()
                        .put(
                "name",
                event.getNewValue());
        }
    }
}
