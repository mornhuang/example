package com.taifook.adminportal.web.ipo.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * @struts.form name="IPOImportResultForm"
 */

public class IPOImportResultForm
    extends ActionForm {

    private String ipoResultFilePath;

    public String getIpoResultFilePath() {
        return ipoResultFilePath;
    }
    public void setIpoResultFilePath(String ipoResultFilePath) {
        this.ipoResultFilePath = ipoResultFilePath;
    }


}
