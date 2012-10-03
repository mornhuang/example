<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.itsz.channelsserver.common.CLVSplitUtil"%>
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
	String accountId = request.getParameter("accountId");
	String instrCode = request.getParameter("instrCode");
	String orderSide = request.getParameter("orderSide");
	String channel = request.getParameter("channel");
	String fromDate = request.getParameter("fromDate");
	String toDate = request.getParameter("toDate");
	String parameters = "&accountId=" + accountId;
	parameters = parameters + "&instrCode=" + instrCode;
	parameters = parameters + "&orderSide=" + orderSide;
	parameters = parameters + "&channel=" + channel;
	parameters = parameters + "&fromDate=" + fromDate;
	parameters = parameters + "&toDate=" + toDate;
	String sessionid = request.getParameter("sessionId");
	String contextpath = request.getContextPath();
	String path = contextpath + "/stt/";
	if (StringUtils.isNotBlank(sessionid)) {
		Cookie cookie = new Cookie("JSESSIONID", sessionid);
		cookie.setPath(path);
		response.addCookie(cookie);
		response.sendRedirect(RequestUtils.getActionMappingURL("/exportTradeList.do?CLV=" + clv+parameters, pageContext));
	}
%>
<script language="javascript">
    //window.document.title = "Blank";
    //window.location = "<%=RequestUtils.getActionMappingURL("/exportTradeList.do?CLV=" + clv+parameters, pageContext)%>";
</script>
