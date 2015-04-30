package com.manning.ajaxinaction.portal;

public class JSUtil {

	public static String initUI() {
      StringBuffer jsBuf=new StringBuffer()
	   .append("document.getElementById('login').innerHTML='Welcome back!'\n")                       
  	   .append("document.getElementById('defaultContent').style.display='none';\n");
  	  return jsBuf.toString();
	}

	public static String initWindow(PortalWindow window) {
      StringBuffer jsBuf=new StringBuffer()
	   .append("CreateWindow(new NewWin('")
	   .append(window.getId())
	   .append("',")
	   .append(window.getXPos())
	   .append(",")
	   .append(window.getYPos())
	   .append(",")
	   .append(window.getWidth())
	   .append(",")
	   .append(window.getHeight())
	   .append(",'")
	   .append(window.getUrl())
	   .append("','")
	   .append(window.getTitle())
	   .append("'));\n");
		return jsBuf.toString();
	}

	public static String logout() {
	      StringBuffer jsBuf=new StringBuffer()
		    .append("top.logout()");
	      return jsBuf.toString();
	}

	/**
	 * @return
	 */
	public static String getLoginError() {
      StringBuffer jsBuf=new StringBuffer()
	    .append("document.getElementById('spanProcessing')\n")
	    .append("  .innerHTML = 'The Username and Password is invalid';\n");
      return jsBuf.toString();
	}

}
