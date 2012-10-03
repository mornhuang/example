<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.itsz.sht.common.CLVSplitUtil"%>
<%@page import="com.itsz.sht.common.LocaleUtil"%>
<%@page import="org.apache.struts.util.RequestUtils"%>

<script type="text/javascript">
<!--
	function document.onkeydown() {
		 if (event.ctrlKey && event.keyCode==78) {
		 	event.returnValue=false;
		 }
	}
//-->
</script>
<%
	response.addHeader("Progma", "No-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expires", 1);

	String clv = request.getParameter("CLV");
	//根据用户的语言设定显示的语言
	String language = CLVSplitUtil.getLanguage(clv);
	if (StringUtils.isBlank(language)) {
		language = "BIG5";
	}
	String stockCode = request.getParameter("stockCode");
	LocaleUtil.setLocale(request,language);
	String sessionid = request.getParameter("sessionId");
	String contextpath = request.getContextPath();
	String path = contextpath + "/stt/";
	if (StringUtils.isNotBlank(sessionid)) {
		Cookie cookie = new Cookie("JSESSIONID", sessionid);
		cookie.setPath(path);
		response.addCookie(cookie);
		response.sendRedirect(RequestUtils.getActionMappingURL("/shkSubmit.do?CLV=" + clv+"&stockCode="+stockCode, pageContext));
	}
%>

