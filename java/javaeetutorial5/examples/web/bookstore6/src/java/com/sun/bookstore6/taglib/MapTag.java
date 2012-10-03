/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore6.taglib;

import com.sun.bookstore6.components.MapComponent;
import javax.faces.component.UIComponent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.webapp.UIComponentELTag;


/**
 * <p>{@link UIComponentTag} for an image map.</p>
 */
public class MapTag extends UIComponentELTag {
    private javax.el.MethodExpression action = null;
    private javax.el.MethodExpression actionListener = null;
    private javax.el.ValueExpression current = null;
    private javax.el.ValueExpression immediate = null;
    private javax.el.ValueExpression styleClass = null;

    public void setCurrent(javax.el.ValueExpression current) {
        this.current = current;
    }

    public void setActionListener(javax.el.MethodExpression actionListener) {
        this.actionListener = actionListener;
    }

    public void setAction(javax.el.MethodExpression action) {
        this.action = action;
    }

    public void setImmediate(javax.el.ValueExpression immediate) {
        this.immediate = immediate;
    }

    public void setStyleClass(javax.el.ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public String getComponentType() {
        return ("DemoMap");
    }

    public String getRendererType() {
        return ("DemoMap");
    }

    public void release() {
        super.release();
        current = null;
        styleClass = null;
        actionListener = null;
        action = null;
        immediate = null;
        styleClass = null;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        MapComponent map = (MapComponent) component;

        if (styleClass != null) {
            if (!styleClass.isLiteralText()) {
                map.setValueExpression("styleClass", styleClass);
            } else {
                map.getAttributes()
                   .put(
                    "styleClass",
                    styleClass.getExpressionString());
            }
        }

        if (actionListener != null) {
            map.addActionListener(
                    new MethodExpressionActionListener(actionListener));
        }

        if (action != null) {
            map.setActionExpression(action);
        }

        if (immediate != null) {
            if (!immediate.isLiteralText()) {
                map.setValueExpression("immediate", immediate);
            } else {
                map.setImmediate(
                        new Boolean(immediate.getExpressionString())
                        .booleanValue());
            }
        }
    }
}
