<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.warrantCBBC.title"/></span><b></b></span>
        <span class="position"><bean:message key="label.warrantCBBC.head"/></span>
    </h1>
    <div class="page-content">
        <div class="warrants-cbbcs-div">
            <ul class="warrants">
               <li><a class="a-1" href="javascript:parent.changeCenterSrc('Warrants.jsp?url=<bean:message key="link.warrantCBBC.message1"/>');"><bean:message key="label.warrantCBBC.message1"/></a></li>
               <li><a class="a-2" href="javascript:parent.changeCenterSrc('Warrants.jsp?url=<bean:message key="link.warrantCBBC.message2"/>');"><bean:message key="label.warrantCBBC.message2"/></a></li>
               <li><a class="a-3" href="javascript:parent.changeCenterSrc('Warrants.jsp?url=<bean:message key="link.warrantCBBC.message3"/>');"><bean:message key="label.warrantCBBC.message3"/></a></li>
               <li><a class="a-4" href="javascript:parent.changeCenterSrc('Warrants.jsp?url=<bean:message key="link.warrantCBBC.message4"/>');"><bean:message key="label.warrantCBBC.message4"/></a></li>
               <li><a class="a-5" href="javascript:parent.changeCenterSrc('Warrants.jsp?url=<bean:message key="link.warrantCBBC.message5"/>');"><bean:message key="label.warrantCBBC.message5"/></a></li>
               <li><a class="a-6" href="javascript:parent.changeCenterSrc('Warrants.jsp?url=<bean:message key="link.warrantCBBC.message6"/>');"><bean:message key="label.warrantCBBC.message6"/></a></li>
               <li><a class="a-7" href="javascript:parent.changeCenterSrc('Warrants.jsp?url=<bean:message key="link.warrantCBBC.message7"/>');"><bean:message key="label.warrantCBBC.message7"/></a></li>
               <li><a class="a-8" href="javascript:parent.changeCenterSrc('Warrants.jsp?url=<bean:message key="link.warrantCBBC.message8"/>');"><bean:message key="label.warrantCBBC.message8"/></a></li>
            </ul>
            <ul class="cbbcs">
               <li><a class="a-1" href="javascript:parent.changeCenterSrc('CBBCs.jsp?url=<bean:message key="link.warrantCBBC.message9"/>');"><bean:message key="label.warrantCBBC.message9"/></a></li>
               <li><a class="a-2" href="javascript:parent.changeCenterSrc('CBBCs.jsp?url=<bean:message key="link.warrantCBBC.message10"/>');"><bean:message key="label.warrantCBBC.message10"/></a></li>
               <li><a class="a-3" href="javascript:parent.changeCenterSrc('CBBCs.jsp?url=<bean:message key="link.warrantCBBC.message11"/>');"><bean:message key="label.warrantCBBC.message11"/></a></li>
               <li><a class="a-4" href="javascript:parent.changeCenterSrc('CBBCs.jsp?url=<bean:message key="link.warrantCBBC.message12"/>');"><bean:message key="label.warrantCBBC.message12"/></a></li>
               <li><a class="a-5" href="javascript:parent.changeCenterSrc('CBBCs.jsp?url=<bean:message key="link.warrantCBBC.message13"/>');"><bean:message key="label.warrantCBBC.message13"/></a></li>
               <li><a class="a-6" href="javascript:parent.changeCenterSrc('CBBCs.jsp?url=<bean:message key="link.warrantCBBC.message14"/>');"><bean:message key="label.warrantCBBC.message14"/></a></li>
               <li><a class="a-7" href="javascript:parent.changeCenterSrc('CBBCs.jsp?url=<bean:message key="link.warrantCBBC.message15"/>');"><bean:message key="label.warrantCBBC.message15"/></a></li>
               <li><a class="a-8" href="javascript:parent.changeCenterSrc('CBBCs.jsp?url=<bean:message key="link.warrantCBBC.message16"/>');"><bean:message key="label.warrantCBBC.message16"/></a></li>
            </ul>
        </div>
    </div>
</body>
</html>
