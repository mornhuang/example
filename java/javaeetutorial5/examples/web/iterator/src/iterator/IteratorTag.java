/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;


public class IteratorTag extends SimpleTagSupport {
    private Collection group;
    private Iterator iterator;
    private String type;
    private String var;

    public void doTag() throws JspException, IOException {
        if (iterator == null) {
            return;
        }

        while (iterator.hasNext()) {
            getJspContext()
                .setAttribute(
                var,
                iterator.next());
            getJspBody()
                .invoke(null);
        }
    }

    public void setGroup(Collection group) {
        this.group = group;

        if ((group != null) && (group.size() > 0)) {
            iterator = group.iterator();
        }
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setType(String type) {
        this.type = type;
    }
}
