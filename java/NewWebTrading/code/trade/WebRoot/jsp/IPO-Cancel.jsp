<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<c:if test="${!empty failure}">
<script type="text/javascript">
	alert('<bean:message key="${failure}"/>');
</script>
</c:if>
<script type="text/javascript">
function CheckForm(theForm)
{
	//form1.confirm.disabled=true;
	if(document.getElementById("pwd").value=="")
	{
        alert('<bean:message key="errors.logonForm.password" />');
	    theForm.password.focus();
		//form1.confirm.disabled=false;
		return false;
	}
	else
	{   
	  if (window.confirm('<bean:message key="message.ipo.status.cancel"/>'))
		{
	      theForm.submit();
		 }
		 else
		{				 
			 //form1.confirm.disabled=false;
			 return false;
		}
		
	}
}
</script>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
    <form name="form1" action="../webIPOCancelCode.do" method="post" onsubmit="return CheckForm(this)">
    <div class="page-content">
        <p>
<bean:message key="message.ipo.status.ac1"/> ${result.ipoRecord.ipoName_dsply }（${result.ipoRecord.stockCode }）<bean:message key="message.ipo.status.ac3"/></p>
    <br />
        <table class="form-table ui-corner-all">
            <tr>
                <td class="title" colspan="2">
                    <bean:message key="label.ipo.heading1"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="message.ipo.status.row1"/>
                </th>
                <td>
                    ${result.ipoRecord.ipoName_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo.status.row2"/>
                </th>
                <td>
                    ${result.ipoRecord.stockCode }
                </td>
            </tr>
            <tr>
                <th>
                    &nbsp;
                </th>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo.status.row10"/>
                </th>
                <td>
                    $${result.ipoRecord.price_dsply }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo.status.row11"/>
                </th>
                <td>
                    ${result.ipoRequest.applyQuantity_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo.status.row12"/>
                </th>
                <td>
                    $${result.ipoRecord.fee }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo.status.row13"/>
                </th>
                <td>
                    $${result.ipoRequest.dsptAmount_dsply }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo.status.row14"/>
                </th>
                <td>
                    ${result.ipoRequest.telephone }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo.status.row15"/>
                </th>
                <td>
                    ${result.ipoRequest.email }
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo.status.row16"/>
                </th>
                <td>
                    ${result.ipoRequest.applyId }
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="message.ipo.status.row17"/>
                </th>
                <td>
                    <strong><bean:message key="message.ipo.status.status1"/></strong>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="message.ipo.status.row19"/>
                </th>
                <td>
                    <input id="pwd" type="password" name="password" class="form-input" />
                </td>
            </tr>
        </table>
        <input type="hidden" name="applyId" value="${result.ipoRequest.applyId }"/>
        <input type="hidden" name="ipoMasterId" value="${result.ipoRecord.ipoMasterId }"/>
        <input type="submit" name="confirm" class="prospectus-btn" value="<bean:message key="message.ipo.status.buttuon1"/>"/>
        <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo.status.buttuon2"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webIOPQueryList.do'" />
        <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo.status.buttuon3"/>" onclick="javascript:window.print()" />
    </div>
    </form>
</body>
</html>


