package com.taifook.adminportal.web.ipo.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @struts.form name="SysParaForm"
 */

public class SysParaForm
    extends ActionForm {

  private int sysParaCode;
  private String sysParaValue;
  private String sysParaDesc;

  public int getSysParaCode() {
    return sysParaCode;
  }

  public String getSysParaDesc() {
    return sysParaDesc;
  }

  public String getSysParaValue() {
    return sysParaValue;
  }

  public void setSysParaCode(int sysParaCode) {
    this.sysParaCode = sysParaCode;
  }

  public void setSysParaDesc(String sysParaDesc) {
    this.sysParaDesc = sysParaDesc;
  }

  public void setSysParaValue(String sysParaValue) {
    this.sysParaValue = sysParaValue;
  }

}
