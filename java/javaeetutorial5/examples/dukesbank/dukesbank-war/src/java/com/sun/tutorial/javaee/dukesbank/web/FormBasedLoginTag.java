/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


/*
 * This code was written by Ed Burns.
 */
package com.sun.tutorial.javaee.dukesbank.web;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;


/**
 * <p>The JSP <code>Tag</code> associated with the FormBasedLoginComponent.</p>
 */
public class FormBasedLoginTag extends UIComponentELTag {
    private ValueExpression buttonLabel;
    private ValueExpression passwordLabel;

    /**
     * Holds value of property resetButtonLabel.
     */
    private ValueExpression resetButtonLabel;
    private ValueExpression userNameLabel;

    public void setUserNameLabel(javax.el.ValueExpression userNameLabel) {
        this.userNameLabel = userNameLabel;
    }

    /**
     * Getter for property usernameLabel.
     * @return Value of property usernameLabel.
     */
    public ValueExpression getUserNameLabel() {
        return this.userNameLabel;
    }

    public void setPasswordLabel(ValueExpression passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    /**
     * Getter for property passwordLabel.
     * @return Value of property passwordLabel.
     */
    public ValueExpression getPasswordLabel() {
        return this.passwordLabel;
    }

    public void setButtonLabel(ValueExpression buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    /**
     * Getter for property buttonLabel.
     * @return Value of property buttonLabel.
     */
    public ValueExpression getButtonLabel() {
        return this.buttonLabel;
    }

    /**
     * Getter for property resetButtonLabel.
     * @return Value of property resetButtonLabel.
     */
    public ValueExpression getResetButtonLabel() {
        return this.resetButtonLabel;
    }

    /**
     * Setter for property resetButtonLabel.
     * @param resetButtonLabel New value of property resetButtonLabel.
     */
    public void setResetButtonLabel(ValueExpression resetButtonLabel) {
        this.resetButtonLabel = resetButtonLabel;
    }

    public String getComponentType() {
        return "bank.FormBasedLogin";
    }

    public String getRendererType() {
        return null;
    }

    public void release() {
        super.release();
        this.userNameLabel = null;
        this.passwordLabel = null;
        this.buttonLabel = null;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        FormBasedLoginComponent fblc = (FormBasedLoginComponent) component;

        if (userNameLabel != null) {
            if (!userNameLabel.isLiteralText()) {
                fblc.setValueExpression("userNameLabel", userNameLabel);
            } else {
                fblc.setUsernameLabel(userNameLabel.getExpressionString());
            }
        }

        if (passwordLabel != null) {
            if (!passwordLabel.isLiteralText()) {
                fblc.setValueExpression("passwordLabel", passwordLabel);
            } else {
                fblc.setPasswordLabel(passwordLabel.getExpressionString());
            }
        }

        if (buttonLabel != null) {
            if (!buttonLabel.isLiteralText()) {
                fblc.setValueExpression("buttonLabel", buttonLabel);
            } else {
                fblc.setButtonLabel(buttonLabel.getExpressionString());
            }
        }

        if (resetButtonLabel != null) {
            if (!resetButtonLabel.isLiteralText()) {
                fblc.setValueExpression("resetButtonLabel", resetButtonLabel);
            } else {
                fblc.setResetButtonLabel(
                        resetButtonLabel.getExpressionString());
            }
        }
    }
}
