package com.taifook.adminportal.web.ipo.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @struts.form name="IPOExportRequestForm"
 */

public class IPOExportRequestForm
    extends ActionForm {

  long ipoMasterId;
  private String ipoRequestFilePath;

  public long getIpoMasterId() {
    return ipoMasterId;
  }

  public String getIpoRequestFilePath() {
    return ipoRequestFilePath;
  }

  public void setIpoMasterId(long ipoMasterId) {
    this.ipoMasterId = ipoMasterId;
  }

  public void setIpoRequestFilePath(String ipoRequestFilePath) {
    this.ipoRequestFilePath = ipoRequestFilePath;
  }

}
