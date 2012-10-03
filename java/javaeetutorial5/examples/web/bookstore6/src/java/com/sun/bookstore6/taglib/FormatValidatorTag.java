/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore6.taglib;

import javax.faces.context.FacesContext;
import javax.el.*;
import javax.faces.webapp.ValidatorELTag;
import javax.faces.validator.Validator;
import javax.servlet.jsp.JspException;
import com.sun.bookstore6.validators.FormatValidator;


/**
 * FormatValidatorTag is the tag handler class for FormatValidator tag,
 * <code>format_validator</code>.
 *
 */
public class FormatValidatorTag extends ValidatorELTag {
    private static String validatorID = null;
    protected ValueExpression formatPatterns = null;

    public FormatValidatorTag() {
        super();

        if (validatorID == null) {
            validatorID = "FormatValidator";
        }
    }

    public void setValidatorID(String validatorID) {
        this.validatorID = validatorID;
    } // END setValidatorId
      // Attribute Instance Variables

    //
    // Class methods
    //
    public void setFormatPatterns(ValueExpression formatPatterns) {
        this.formatPatterns = formatPatterns;
    }

    protected Validator createValidator() throws JspException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FormatValidator result = null;

        if (validatorID != null) {
            result = (FormatValidator) facesContext.getApplication()
                                                   .createValidator(
                        validatorID);
        }

        String patterns = null;

        if (formatPatterns != null) {
            if (!formatPatterns.isLiteralText()) {
                patterns = (String) formatPatterns.getValue(
                            facesContext.getELContext());
            } else {
                patterns = formatPatterns.getExpressionString();
            }
        }

        result.setFormatPatterns(patterns);

        return result;
    }
} // end of class FormatValidatorTag
