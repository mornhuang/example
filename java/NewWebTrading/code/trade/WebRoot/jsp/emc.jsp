<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.apache.struts.util.RequestUtils"%>

<%
response.addHeader("Progma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
response.addDateHeader("Expires", 1);

String url = (String)request.getAttribute("emcURL");
response.sendRedirect(url);
//response.sendRedirect(RequestUtils.getActionMappingURL(url, pageContext));
%>

