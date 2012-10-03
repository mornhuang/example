/*
 *
 */
package com.itsz.sht.struts.form.ipo;


import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.Globals;

import com.itsz.sht.struts.form.ITSZForm;

/**
 * @struts.form name="IPOResultForm"
 */

public class IPOResultForm extends ITSZForm {
/*
    private String applyStr;
    private String[] applyCodeStr;
    private String statusCode;

    public IPOResultForm() {
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors ers = new ActionErrors();
        ers = commonCheck(mapping, request);
        return ers;
    }
    protected ActionErrors commonCheck(ActionMapping mapping,
            HttpServletRequest request) {

        Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
        MessageResources mr = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        mr.setReturnNull(true);

        ActionErrors errors = new ActionErrors();

        return errors;
    }
    public String getApplyStr() {
        return applyStr;
    }
    public void setApplyStr(String applyStr) {
        this.applyStr = applyStr;
    }
    public String[] getApplyCodeStr() {
        return applyCodeStr;
    }
    public void setApplyCodeStr(String[] applyCodeStr) {
        this.applyCodeStr = applyCodeStr;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
*/
}

