<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><bean:message key="company.name"/></title>
    <base href="${pageContext.request.requestURL}"/>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
    
    <script type="text/javascript">
        $(function () {
            $("#ckAgree").click(function () {
                if ($(this).attr("checked")){
                    $("#btnConfirm").removeClass("disabled").attr("disabled", "");
                    $("#password").removeClass("disabled").attr("disabled", "");
                }
                else{
                    $("#btnConfirm").addClass("disabled").attr("disabled", "disabled");
                    $("#password").addClass("disabled").attr("disabled", "disabled");
                }
            });
        });
        function validateInput(){
			if(!$("#ckAgree").attr("checked")){
				alert('<bean:message key="message.quote.agree"/>');
				return false;
			}
			if($("#password").val()!=undefined && !parent.checkPsw($("#password"))){
				$("#password")[0].focus();
				return false;
			}else{
				return true;
			}
        }

        function formSubmit(){
            if(validateInput()){         
            	$("#aasBuyForm").submit();
            }
        }
    </script>
</head>
<%
	String prodId = request.getParameter("productId");
	String productPrice = request.getParameter("productPrice_SSTR_AAST");
	if(prodId.equals("SSTR_AAST_CN")){
    	productPrice = request.getParameter("productPrice_SSTR_AAST_CN");
    }
%>
<c:if test="${!empty error}">
<script type="text/javascript">
	alert('<bean:message key="${error}"/>');
	$("#password")[0].focus();
</script>
</c:if>
<body>
    <form action="../webPurchaseRTQ.do" id="aasBuyForm" method="post" onsubmit="return validateInput();">
    <input type="hidden" name="CLV" id="CLV" value="${sessionScope.CLV}"/>
    <input type="hidden" name="allowRenewal" id="allowRenewal" value="${PurchaseRTQForm.allowRenewal}"/>
    <input type="hidden" name="productId" id="productId" value="${PurchaseRTQForm.productId}"/>
    <input type="hidden" name="selectMonth" id="selectMonth" value="${PurchaseRTQForm.selectMonth}"/>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.quote.heading"/></span><b></b></span> <span class="position"><bean:message key="AccountServices"/>
            > <bean:message key="label.quote.heading"/></span>
    </h1>
    <div class="page-content">
        <h3>
           <bean:message key='message.quote.noteword'/></h3>
        <div class="user-assert">
            <p>
                <bean:message key='message.quote.warnword'/></p>
            <h2 class="center">
                <bean:message key='message.quote.aasword'/></h2>
            <p>
                <bean:message key='message.quote.agreeword'/>
            </p>
            <ul>
                <li><bean:message key='message.quote.agreeword1'/> </li>
                <li><bean:message key='message.quote.agreeword2'/></li>
                <li><bean:message key='message.quote.agreeword3'/> </li>
                <li><bean:message key='message.quote.agreeword4'/></li>
            </ul>
            <h2>
                <bean:message key='message.quote.detail'/></h2>
            <p><bean:message key='message.quote.details'/>
            </p>
            <h2>
                <bean:message key='message.quote.detailwarn'/></h2>
        </div>
        <p>
            <input type="checkbox" id="ckAgree" /><label for="ckAgree"><bean:message key='message.quote.agreenotice'/></label>
        </p>
        <br />
        <h2>
           <bean:message key='message.quote.agreeaasprice' arg0="<%=productPrice%>"/></h2>
        <p class="form-notice">
            <strong><bean:message key='message.quote.aassys'/></strong>
        </p>
        <p>
            <bean:message key='message.quote.feenotice'/></p>
        <br />        
        <h2>
           <bean:message key='message.quote.confirmpass'/>
   	    </h2>
        <p>
          <input type="password" id="password" name="password" class="form-input" disabled="disabled" onkeypress="javascript:return parent.pswKeyPress(event);"/>
        </p>
        <br />
        <p>
            <input type="submit" id="btnConfirm" class="yellow-btn disabled" value="<bean:message key='button.quote.ok'/>" disabled="disabled"/>
            <input type="button" class="graw-short-btn" value="<bean:message key='button.quote.cancel'/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webEnquireRTQProduct.do?CLV=${sessionScope.CLV}&'" />
        </p>
    </div>
    </form>
</body>
</html>
