<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/webchannels.tld" prefix="taifook"%>

<script language="JavaScript" src="/js/FormChek.js"></script>
<script language="JavaScript" src="/js/general.js"></script>  

<link rel="StyleSheet" HREF="/css/other.css" TYPE="text/css">
<link rel="StyleSheet" HREF="/css/orderinput.css" TYPE="text/css">

<script type="text/javascript">
<!--
alert('<bean:message key="message.ipo.common.cancel"/>');
window.location = "ipoSubscriptionEnquiry.do?CLV=${sessionScope.CLV}";
//-->
</script>

