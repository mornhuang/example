<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
    String sessionid = request.getParameter("sessionId");
	String contextpath = request.getContextPath(); 
	String path = contextpath + "/stt/";
	if(sessionid!=null||!"".equals(sessionid)){
		javax.servlet.http.Cookie cookie=new javax.servlet.http.Cookie("JSESSIONID",sessionid);
		cookie.setPath(path);
		response.addCookie(cookie);
	}
	String redirct = org.apache.struts.util.RequestUtils.getActionMappingURL("/RTQForwardInit.do?CLV=" + clv, pageContext);
%>
<HTML> <HEAD>
 <title><bean:message key="company.name"/></title></HEAD>
<form name="rtqForm" action="" method="post" target="popupManual">
	<input type="hidden" name="sessionId" value="<%=sessionid%>">
	<input type="hidden" name="CLV" value="<%=clv%>">
</form>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
	function cloz() {
		var u = location.href;
    	var splitIndex = u.indexOf("?");
		if (splitIndex != -1) {
			u = u.substring(0, splitIndex);
		}
		var w =window.open("", "popupManual", "width=800,height=600, location=no,toolbar=no,resizable=yes,scrollbars=yes,status=yes,top=10,left=10");    //最关键的一句，注意最后的参数
		if (w) w.focus();
		rtqForm.action = u;
		rtqForm.submit();
        window.top.close();
	}
	
	document.onkeydown = function() {
		if (event.ctrlKey && event.keyCode == 78) {
			event.keyCode = 0; 
			event.returnValue = false; 
		}
		return true;
	};

    if(this.name != "popupManual") {
    	cloz();
    }
   	window.location = "<%=redirct%>";

</SCRIPT>
