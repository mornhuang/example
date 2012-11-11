/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore6.taglib;

import com.sun.bookstore6.components.AreaComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.webapp.UIComponentELTag;


/**
 * <p>{@link UIComponentTag} for an image map hotspot.</p>
 */
public class AreaTag extends UIComponentELTag {
    private javax.el.ValueExpression alt;
    private javax.el.ValueExpression coords;
    private javax.el.ValueExpression onmouseout;
    private javax.el.ValueExpression onmouseover = null;
    private javax.el.ValueExpression shape = null;
    private javax.el.ValueExpression styleClass = null;
    private javax.el.ValueExpression targetImage;
    private javax.el.ValueExpression value = null;

    public void setAlt(javax.el.ValueExpression alt) {
        this.alt = alt;
    }

    public void setTargetImage(javax.el.ValueExpression targetImage) {
        this.targetImage = targetImage;
    }

    public void setCoords(javax.el.ValueExpression coords) {
        this.coords = coords;
    }

    public void setOnmouseout(javax.el.ValueExpression newonmouseout) {
        onmouseout = newonmouseout;
    }

    public void setOnmouseover(javax.el.ValueExpression newonmouseover) {
        onmouseover = newonmouseover;
    }

    public void setShape(javax.el.ValueExpression shape) {
        this.shape = shape;
    }

    public void setStyleClass(javax.el.ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public void setValue(javax.el.ValueExpression newValue) {
        value = newValue;
    }

    public String getComponentType() {
        return ("DemoArea");
    }

    public String getRendererType() {
        return ("DemoArea");
    }

    public void release() {
        super.release();
        this.alt = null;
        this.coords = null;
        this.onmouseout = null;
        this.onmouseover = null;
        this.shape = null;
        this.styleClass = null;
        this.value = null;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        AreaComponent area = (AreaComponent) component;

        if (alt != null) {
            if (!alt.isLiteralText()) {
                area.setValueExpression("alt", alt);
            } else {
                area.setAlt(alt.getExpressionString());
            }
        }

        if (coords != null) {
            if (!coords.isLiteralText()) {
                area.setValueExpression("coords", coords);
            } else {
                area.setCoords(coords.getExpressionString());
            }
        }

        if (onmouseout != null) {
            if (!onmouseout.isLiteralText()) {
                area.setValueExpression("onmouseout", onmouseout);
            } else {
                area.getAttributes()
                    .put(
                    "onmouseout",
                    onmouseout.getExpressionString());
            }
        }

        if (onmouseover != null) {
            if (!onmouseover.isLiteralText()) {
                area.setValueExpression("onmouseover", onmouseover);
            } else {
                area.getAttributes()
                    .put(
                    "onmouseover",
                    onmouseover.getExpressionString());
            }
        }

        if (shape != null) {
            if (!shape.isLiteralText()) {
                area.setValueExpression("shape", shape);
            } else {
                area.setShape(shape.getExpressionString());
            }
        }

        if (styleClass != null) {
            if (!styleClass.isLiteralText()) {
                area.setValueExpression("styleClass", styleClass);
            } else {
                area.getAttributes()
                    .put(
                    "styleClass",
                    styleClass.getExpressionString());
            }
        }

        if (area instanceof ValueHolder) {
            ValueHolder valueHolder = (ValueHolder) component;

            if (value != null) {
                if (!value.isLiteralText()) {
                    area.setValueExpression("value", value);
                } else {
                    valueHolder.setValue(value.getExpressionString());
                }
            }
        }

        // target image is required
        area.setValueExpression("targetImage", targetImage);
    }
}
