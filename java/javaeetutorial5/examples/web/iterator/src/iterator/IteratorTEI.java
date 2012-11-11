/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package iterator;

import javax.servlet.jsp.tagext.*;


public class IteratorTEI extends TagExtraInfo {
    public IteratorTEI() {
        super();
    }

    public VariableInfo[] getVariableInfo(TagData data) {
        String type = data.getAttributeString("type");

        if (type == null) {
            type = "java.lang.Object";
        }

        VariableInfo info1 = new VariableInfo(
                    data.getAttributeString("var"),
                    type,
                    true,
                    VariableInfo.NESTED);
        VariableInfo[] info = { info1 };

        return info;
    }
}
