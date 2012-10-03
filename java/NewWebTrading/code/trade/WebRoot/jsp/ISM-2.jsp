<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/zh-HK/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
</head>

<script type="text/javascript">
function onPageLoad(){
	
	<c:if test="${not empty PasswordStatus}">
		<c:choose>
			<c:when test="${PasswordStatus == 'fail'}">
				alert("<bean:message key='PurchCancelPwdN'/>");
			</c:when>
			<c:otherwise>
				alert("<bean:message key='PurchSHKN'/>");
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="${not empty error}">
		alert("<bean:message key='${error}'/>");
   </c:if>
}
</script>

<body onload="onPageLoad()">
<form action="../PurchaseShk.do?CLV=${sessionScope.CLV}" method="post">
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="shk"/></span><b></b></span> <span class="position"><bean:message key="AccountServices"/>
            > <bean:message key="shk"/></span>
    </h1>
    <div class="page-content">
        <h2>
          <bean:message key="BuySHK"/></h2>
        <p class="form-notice">
            <strong><bean:message key="shk"/></strong>
        </p>
        <p>
            (<bean:message key="MonthlyFeeTime"/>。)</p>
        <br />
        <h2>
           <bean:message key="ConfirmPassword"/>
        </h2>
        <p>
        	<input type="hidden" id="loginId" name="loginId" value="${User.tradeInfoObject.custCode}" />
            <input type="password" id="password" name="password" class="form-input" />
        </p>
        <br />
        
        <p>
        	<input type="submit" id="submit" class="yellow-btn" value="<bean:message key="button.common.ok"/>" />
            <input type="button" class="graw-short-btn" value="<bean:message key="button.common.cancel"/>" onclick="location.href='${pageContext.request.contextPath}/jsp/ISM-1.jsp'" />
         </p>
     </div>
</form>
</body>
</html>