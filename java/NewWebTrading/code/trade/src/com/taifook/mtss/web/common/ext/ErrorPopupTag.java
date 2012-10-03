package com.taifook.mtss.web.common.ext;

import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.taglib.html.ErrorsTag;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import com.itsz.sht.exception.WebActionError;

public class ErrorPopupTag extends ErrorsTag {

    private static final long serialVersionUID = 1L;
    protected String methodName = null;

    public void setMethodName( String methodName ) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public int doStartTag() throws JspException {

        StringBuffer results = new StringBuffer();

        if ( methodName == null ) {
            return super.doStartTag();
        } else {
            results.append("<SCRIPT LANGUAGE=\"JAVASCRIPT\">\n");
            results.append("function ").append( getMethodName().trim() ).append("() {\n");
        }

        ActionErrors errors = null;
        try {
            errors = RequestUtils.getActionErrors(pageContext, name);
        } catch (JspException e) {
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        if ((errors == null) || errors.isEmpty()) {
            if ( methodName != null ) {
                results.append("}\n");
                results.append("</SCRIPT>");
                ResponseUtils.write(pageContext, results.toString());
            }
            return (EVAL_BODY_INCLUDE);
        }


        StringBuffer message = new StringBuffer();
        //ServletConfig config = pageContext.getServletConfig();
        Iterator reports = (property == null) ? errors.get() : errors.get(property);


        while (reports.hasNext()) {
        	WebActionError report = (WebActionError) reports.next();
            String s =
                RequestUtils.message( pageContext, bundle, locale, report.getKey(), report.getValues());
            if ( s != null ) {
                message.append(s);
                if ( report.isIsErrorCodeDisplayed() && report.geErrorCode() != null ) {
                    message.append( " (" );
                    message.append( report.geErrorCode() );
                    message.append( ")" );
                }
                if ( report.isIsReferenceNoDisplayed() && report.getReferenceNo() != null ) {
                    message.append( " REF: " );
                    message.append( report.getReferenceNo() );
                }
                if ( reports.hasNext() ) {
                    message.append( "\\n\" + " + lineEnd  + "\"");
                }
            }
        }
        results.append("    var error = \"").append(message.toString()).append("\";\n");
        results.append("    window.alert(error);\n");
        results.append("}\n");
        results.append("</SCRIPT>");
        ResponseUtils.write(pageContext, results.toString());

        return (EVAL_BODY_INCLUDE);

    }

    public void release() {
        super.release();
        bundle = Globals.MESSAGES_KEY;
        locale = Globals.LOCALE_KEY;
        name = Globals.ERROR_KEY;
        property = null;
    }

}