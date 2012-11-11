<%--
 * Copyright (c) 2003 Sun Microsystems, Inc.  All rights reserved.  U.S. 
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
 * Copyright (c) 2003 Sun Microsystems, Inc. Tous droits reserves.
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
 *'
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/tlt" prefix="tlt" %>
<html>
    <jsp:useBean id="myorg" class="myorg.Organization"/>
    <c:set var="deptName" value="${param.deptName}" />
    <c:set var="dept" value="${myorg.departments[deptName]}"/>
    <head>
    <title>${deptName} Department</title>
    </head>
    <body bgcolor="white">
    <table border=2 cellspacing=3 cellpadding=3>
        <tr>
                <td colspan=3><b><center>${deptName}</b></center></td>
        </tr>
        <tr>
                <td width=100><b>Name</b></td>
                <td width=100><b>Extension</b></td>
                <td width=100><b>Email</b></td>
        </tr>
        <%-- List all department members --%>
        <tlt:iterator var="member" type="myorg.Member" group="${dept.members}">
        <tr>
                <td>${member.name}</td>
                <td>${member.phone}</td>
                <td>${member.email}</td>
        </tr>
        </tlt:iterator>		
    </table>
    </body>
</html>		  	

