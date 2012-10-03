<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<HTML> <HEAD> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
</HEAD>

<FRAMESET ROWS="0,100%" BORDER=0 FRAMEBORDER="NO">   
	<FRAME NAME="epsmon" src="test.htm">   
	<FRAME NAME="epsmain" SRC="<%=org.apache.struts.util.RequestUtils.getActionMappingURL("/webPPSEnquiry.do?type=pps", pageContext)%>"> 
	<NOFRAMES>You must use a browser that can display frames   to see this page. </NOFRAMES>
</FRAMESET>
</HTML>
