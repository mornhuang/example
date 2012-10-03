<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<link rel=StyleSheet HREF="<%=request.getContextPath()%>/Style/other.css" TYPE="text/css">
<SCRIPT>
 
	function popDisclaimerAll() {
		var TfDisclaimers = window.open("<%=request.getContextPath()%>/disclaimer.do", "Disclaimers",'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=450,height=510');
	}
	function popDisclaimers_C() {
		var TfDisclaimers = window.open("http://www.htisec.com/tc/disclaimer.jsp", "Disclaimers",'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=450,height=510');
	}
	function popDPP_C() {
		var TfDPP = window.open("http://www.htisec.com/tc/dpp.jsp", "DPP",'toolbar=0,location=0, directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=450,height=510');
	}
	function popDisclaimers_CN() {
		var TfDisclaimers = window.open("http://gb.htisec.com/gb/www.htisec.com/tc/disclaimer.jsp", "Disclaimers",'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=450,height=510');
	}
	function popDPP_CN() {
		var TfDPP = window.open("http://gb.htisec.com/gb/www.htisec.com/tc/dpp.jsp", "DPP",'toolbar=0,location=0, directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=450,height=510');
	}
	function popDisclaimers_E() {
		var TfDisclaimers = window.open("http://www.htisec.com/en/disclaimer.jsp", "Disclaimers",'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=450,height=510');
	}
	function popDPP_E() {
		var TfDPP = window.open("http://www.htisec.com/en/dpp.jsp", "DPP",'toolbar=0,location=0, directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=450,height=510');
	}

	
	
	<logic:present name="org.apache.struts.action.LOCALE" scope="session">
		<logic:equal name="org.apache.struts.action.LOCALE" value="<%=java.util.Locale.TRADITIONAL_CHINESE.toString()%>" scope="session">
			function popDisclaimers(){
				popDisclaimers_C();
			}
			
			function popDPP() {
				popDPP_C();
			}
		</logic:equal>
		<logic:equal name="org.apache.struts.action.LOCALE" value="<%=java.util.Locale.SIMPLIFIED_CHINESE.toString()%>" scope="session">
			function popDisclaimers(){
				popDisclaimers_CN();
			}
			
			function popDPP() {
				popDPP_CN();
			}
		</logic:equal>
		<logic:equal name="org.apache.struts.action.LOCALE" value="<%=java.util.Locale.US.toString()%>" scope="session">
			function popDisclaimers(){
				popDisclaimers_E();
			}
			
			function popDPP() {
				popDPP_E();
			}
		</logic:equal>
	</logic:present>
	<logic:notPresent name="org.apache.struts.action.LOCALE" scope="session">

			function popDisclaimers(){
				popDisclaimers_E();
			}
			
			function popDPP() {
				popDPP_E();
			}

	</logic:notPresent>
	
	
</SCRIPT>


<body bgcolor="white">
<table border=0 width="600" align="elft">
	<tr>
		<td align=center>
			<HR><font class="otiny"><bean:message bundle="WEB_RESOURCE_KEY" key="message.copyright.statement"/><BR>
			<a href="javascript:popDisclaimers()"><u><bean:message bundle="WEB_RESOURCE_KEY" key="label.copyright.disclaimers"/></u></a>&nbsp;<bean:message bundle="WEB_RESOURCE_KEY" key="label.general.and"/>&nbsp;<a href="javascript:popDPP()"><u><bean:message bundle="WEB_RESOURCE_KEY" key="label.copyright.dataPrivacyPolicy"/></u></a>
			<P ALIGN=JUSTIFY><bean:message bundle="WEB_RESOURCE_KEY" key="message.copyright.attention"/></P>
			<P ALIGN=JUSTIFY><bean:message bundle="WEB_RESOURCE_KEY" key="message.copyright.disclaimer"/></P>
		</td>
	</tr>
</table>
</body>
