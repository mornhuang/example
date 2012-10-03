package com.taifook.adminportal.web.ipo.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @struts.form name="IPOImportForm"
 */

public class IPOImportForm
    extends ActionForm {

    private String ipoFilePath;
    private String ipoCrtFilePath;


    public String getIpoCrtFilePath() {
        return ipoCrtFilePath;
    }
    public void setIpoCrtFilePath(String ipoCrtFilePath) {
        this.ipoCrtFilePath = ipoCrtFilePath;
    }
    public String getIpoFilePath() {
        return ipoFilePath;
    }
    public void setIpoFilePath(String ipoFilePath) {
        this.ipoFilePath = ipoFilePath;
    }
}
