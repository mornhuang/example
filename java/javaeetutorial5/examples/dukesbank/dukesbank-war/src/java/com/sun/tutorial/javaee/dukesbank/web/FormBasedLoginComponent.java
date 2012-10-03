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

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;


public class FormBasedLoginComponent extends UIOutput {
    private static String defaultUsernameLabel = "Username:";
    private static String defaultPasswordLabel = "Password:";
    private static String defaultButtonLabel = "Login";
    private static String defaultResetButtonLabel = "Reset";
    private String buttonLabel;
    private String passwordLabel;
    private String resetButtonLabel;
    private String usernameLabel;

    public FormBasedLoginComponent() {
    }

    public String getFamily() {
        return "bank.FormBasedLogin";
    }

    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("form", this);
        writer.writeAttribute("method", "post", null);
        writer.writeAttribute("action", "j_security_check", null);
        writer.startElement("table", this);
        writer.startElement("tr", this);
        writer.startElement("td", this);
        writer.startElement("label", this);
        writer.writeAttribute("for", "j_username", null);
        writer.writeText(
            this.getUsernameLabel(),
            "usernameLabel");
        writer.endElement("label");
        writer.endElement("td");

        writer.startElement("td", this);
        writer.startElement("input", this);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("name", "j_username", null);
        writer.writeAttribute("id", "j_username", null);
        writer.endElement("td");
        writer.endElement("tr");

        writer.startElement("tr", this);

        writer.startElement("td", this);
        writer.startElement("label", this);
        writer.writeAttribute("for", "j_password", null);
        writer.writeText(
            this.getPasswordLabel(),
            "passwordLabel");
        writer.endElement("label");
        writer.endElement("td");

        writer.startElement("td", this);
        writer.startElement("input", this);
        writer.writeAttribute("type", "password", null);
        writer.writeAttribute("name", "j_password", null);
        writer.endElement("td");

        writer.endElement("tr");

        writer.startElement("tr", this);
        writer.startElement("tr", this);
        writer.startElement("td", this);
        writer.startElement("input", this);
        writer.writeAttribute("type", "reset", null);
        writer.writeAttribute("name", "reset", null);
        writer.writeAttribute(
                "value",
                getResetButtonLabel(),
                "resetButtonLabel");
        writer.endElement("td");
        writer.startElement("td", this);
        writer.startElement("input", this);
        writer.writeAttribute("type", "submit", null);
        writer.writeAttribute("name", "submit", null);
        writer.writeAttribute(
            "value",
            getButtonLabel(),
            "buttonLabel");
        writer.endElement("td");
        writer.endElement("tr");
        writer.endElement("table");

        writer.endElement("form");
    }

    public void encodeChildren(FacesContext context) throws IOException {
    }

    public void encodeEnd(FacesContext context) throws IOException {
    }

    public String getUsernameLabel() {
        if (this.usernameLabel != null) {
            return this.usernameLabel;
        } else {
            return (defaultUsernameLabel);
        }
    }

    public void setUsernameLabel(String usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public String getPasswordLabel() {
        if (this.passwordLabel != null) {
            return (this.passwordLabel);
        } else {
            return (defaultPasswordLabel);
        }
    }

    public void setPasswordLabel(String passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public String getButtonLabel() {
        if (this.buttonLabel != null) {
            return (this.buttonLabel);
        } else {
            return (defaultButtonLabel);
        }
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    /**
     * Getter for property resetButtonLabel.
     *
     * @return Value of property resetButtonLabel.
     */
    public String getResetButtonLabel() {
        if (this.resetButtonLabel != null) {
            return (this.resetButtonLabel);
        } else {
            return (defaultResetButtonLabel);
        }
    }

    /**
     * Setter for property resetButtonLabel.
     *
     * @param resetButtonLabel New value of property resetButtonLabel.
     */
    public void setResetButtonLabel(String resetButtonLabel) {
        this.resetButtonLabel = resetButtonLabel;
    }
}
