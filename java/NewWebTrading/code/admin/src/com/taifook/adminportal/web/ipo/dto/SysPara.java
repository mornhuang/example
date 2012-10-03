package com.taifook.adminportal.web.ipo.dto;

import java.io.Serializable;

public class SysPara
    implements Serializable {

  private int sysParaCode;
  private String sysParaName;
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
  public String getSysParaName() {
    return sysParaName;
  }
  public void setSysParaName(String sysParaName) {
    this.sysParaName = sysParaName;
  }


}
