<%--
 * Copyright (c) 2002 Sun Microsystems, Inc.  All rights reserved.  U.S. 
 * Government Rights - Commercial software.  Government users are subject 
 * to the Sun Microsystems, Inc. standard license agreement and 
 * applicable provisions of the FAR and its supplements.  Use is subject 
 * to license terms.  
 * 
 * This distribution may include materials developed by third parties. 
 * Sun, Sun Microsystems, the Sun logo, Java and J2EE are trademarks 
 * or registered trademarks of Sun Microsystems, Inc. in the U.S. and 
 * other countries.  
 * 
 * Copyright (c) 2002 Sun Microsystems, Inc. Tous droits reserves.
 * 
 * Droits du gouvernement americain, utilisateurs gouvernementaux - logiciel
 * commercial. Les utilisateurs gouvernementaux sont soumis au contrat de 
 * licence standard de Sun Microsystems, Inc., ainsi qu'aux dispositions 
 * en vigueur de la FAR (Federal Acquisition Regulations) et des 
 * supplements a celles-ci.  Distribue par des licences qui en 
 * restreignent l'utilisation.
 * 
 * Cette distribution peut comprendre des composants developpes par des 
 * tierces parties. Sun, Sun Microsystems, le logo Sun, Java et J2EE 
 * sont des marques de fabrique ou des marques deposees de Sun 
 * Microsystems, Inc. aux Etats-Unis et dans d'autres pays.
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/functions.tld" prefix="f" %>

<html>
<head><title>Localized Dates</title></head>
<body bgcolor="white">

<jsp:useBean id="locales" scope="application" class="mypkg.MyLocales"/>

<form name="localeForm" action="index.jsp" method="post">
</p>
<c:set var="selectedLocaleString" value="${param.locale}" />
<c:set var="selectedFlag" value="${!empty selectedLocaleString}" />
<b>Locale:</b>
<select name=locale>
<c:forEach var="localeString" items="${locales.localeNames}" >
  <c:choose>
  	<c:when test="${selectedFlag}">
      <c:choose>
    		<c:when test="${f:equals(selectedLocaleString,localeString)}" >
      		<option selected>${localeString}</option>
    		</c:when>
    		<c:otherwise>
    			<option>${localeString}</option>
    		</c:otherwise>
      </c:choose>
  	</c:when>
  	<c:otherwise>
    	<option>${localeString}</option>
  	</c:otherwise>
  </c:choose>
</c:forEach>
</select>
<input type="submit" name="Submit" value="Get Date">
</form>

<c:if test="${selectedFlag}" >
  <jsp:setProperty name="locales" property="selectedLocaleString" value="${selectedLocaleString}" />
  <jsp:useBean id="date" class="mypkg.MyDate"/>
  <jsp:setProperty name="date" property="locale" value="${locales.selectedLocale}"/>
  <b>Date: </b>${date.date}
</c:if>

</body>
</html>
