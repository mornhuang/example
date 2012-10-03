<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.itsz.sht.struts.form.PPSTransferForm" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webchannels.tld" prefix="taifook" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 

<%
		response.addHeader("Progma", "No-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addDateHeader("Expires", 1);

		String clv = request.getParameter("CLV");
		//根据用户的语言设定显示的语言
        String language = com.itsz.sht.common.CLVSplitUtil.getLanguage(clv);
        if("".equals(language)){
        	language = "BIG5";
        } 
        com.itsz.sht.common.LocaleUtil.setLocale(request,language);
        //set cookie for display locale
        //javax.servlet.http.Cookie cke = new Cookie("com.taifook.mtss.web.common.Constants.CookieConstants.DEFAULT_LANGUAGE", "zh_TW");
        //   cke.setMaxAge(60*60*24*10);
        //   response.addCookie(cke);
        String sessionid = request.getParameter("sessionId");
		String contextpath = request.getContextPath(); 
		String path = contextpath + "/stt/";
  		if(sessionid!=null||!"".equals(sessionid)){
  			javax.servlet.http.Cookie cookie=new javax.servlet.http.Cookie("JSESSIONID",sessionid);
  			cookie.setPath(path);
  			response.addCookie(cookie);
  		}
		String ac = "";
		String tamount = "";
		boolean disableFromAccount = false;
	
	PPSTransferForm form = (PPSTransferForm) request.getAttribute("ppsTransferForm");
	if (form != null) {
		ac = (form.getAccountId() == null ? "" : form.getAccountId());
		tamount = (form.getTamount() == null ? "" : form.getTamount());
		disableFromAccount = form.isDisableFromAccount();
	}
%>

<HTML> <HEAD> <title><bean:message bundle="STT" key="label.ppsTransferForm.title"/></title></HEAD>

<FRAMESET ROWS="0,100%" BORDER=0 FRAMEBORDER="NO">
	<FRAME NAME="epsmon" src="test.htm">
	<FRAME NAME="epsmain" SRC="<%=org.apache.struts.util.RequestUtils.getActionMappingURL("/ppsTransferInit", pageContext)%>?ac=<%=ac%>&tamount=<%=tamount%>&disableFromAccount=<%=disableFromAccount%>&CLV=<%=clv%>"> 
	<NOFRAMES>You must use a browser that can display frames to see this page. </NOFRAMES>
</FRAMESET>
</HTML>
