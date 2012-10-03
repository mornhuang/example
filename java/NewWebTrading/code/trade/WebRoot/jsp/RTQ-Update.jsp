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
	    function validateInput(){
			if($("#password").val()!=undefined && !parent.checkPsw($("#password"))){
				$("#password")[0].focus();
				return false;
			}else{
				return true;
			}
	    }
	
	    function formSubmit(){
	        if(validateInput()){                
	        	$("#rtqUpdateForm").submit();
	        }
	    }
    </script>
</head>
<c:if test="${!empty error}">
<script type="text/javascript">
	alert('<bean:message key="${error}"/>');
	document.getElementById("password").focus();
</script>
</c:if>
<body>
    <form action="../webUpdateRTQStatus.do" id="rtqUpdateForm" method="post" onsubmit="return validateInput();">
    <input type="hidden" name="CLV" id="CLV" value="${sessionScope.CLV}"/>
    <c:choose>
    	<c:when test="${productId!=null}">
    	    <input type="hidden" name="productId" id="productId" value="${productId}"/>
    	</c:when>
    	<c:otherwise>
    		<input type="hidden" name="productId" id="productId" value="${existProductId}"/>
    	</c:otherwise>
    </c:choose>
    
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.quote.heading"/></span><b></b></span> <span class="position"><bean:message key="AccountServices"/>
            > <bean:message key="label.quote.heading"/></span>
    </h1>
    <div class="page-content">
        <table class="form-table ui-corner-all">
            <tr>
                <td class="title" colspan="2">
                    <bean:message key="lable.quote.updateautorevs"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    <bean:message key="lable.quote.autorevcheck"/>
                </th>
                <td>
                    <c:choose>
				    	<c:when test="${productId!=null}">
		                    <c:if test="${productAllotment!=null && productAllotment.alltStatus=='RESRV_AUTO'}">
		                    	<input type="radio" name="allwRenl" id="rdYes" checked="checked" value="Y"/><label for="rdYes"><bean:message key="lable.quote.wordyes"/></label>
		                    	<input type="radio" name="allwRenl" id="rdNo"  value="N"/><label for="rdNo"><bean:message key="lable.quote.wordno"/></label>
		                    </c:if>
		                    <c:if test="${productAllotment==null || productAllotment.alltStatus!='RESRV_AUTO'}">
		                    	<input type="radio" name="allwRenl" id="rdYes" value="Y"/><label for="rdYes"><bean:message key="lable.quote.wordyes"/></label>
		                    	<input type="radio" name="allwRenl" id="rdNo" checked="checked" value="N"/><label for="rdNo"><bean:message key="lable.quote.wordno"/></label>
		                    </c:if>
				    	</c:when>
			    	    <c:otherwise>
                		    <c:if test="${existProduct!=null && existProduct.allwRenl=='Y'}">
		                    	<input type="radio" name="allwRenl" id="rdYes" checked="checked" value="Y"/><label for="rdYes"><bean:message key="lable.quote.wordyes"/></label>
		                    	<input type="radio" name="allwRenl" id="rdNo"  value="N"/><label for="rdNo"><bean:message key="lable.quote.wordno"/></label>
		                    </c:if>
		                    <c:if test="${existProduct==null || existProduct.allwRenl=='N'}">
		                    	<input type="radio" name="allwRenl" id="rdYes" value="Y"/><label for="rdYes"><bean:message key="lable.quote.wordyes"/></label>
		                    	<input type="radio" name="allwRenl" id="rdNo" checked="checked" value="N"/><label for="rdNo"><bean:message key="lable.quote.wordno"/></label>
		                    </c:if>
                   	    </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
        <p class="form-notice">
            <strong><bean:message key="lable.quote.autorevnotice"/></strong>
        </p>
        <br />
        <h2>
            <bean:message key="lable.quote.noticeword"/></h2>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="lable.quote.noticeword1"/></li>
            <li><span class="number-yellow">2</span><bean:message key="lable.quote.noticeword2"/></li>
            <li><span class="number-yellow">3</span><bean:message key="lable.quote.noticeword3"/></li>
        </ul>
        <br />
        <h2>
           <bean:message key='message.quote.confirmpass'/>
		  </h2>
        <p>
        <input type="password" id="password" name="password" class="form-input" onkeypress="javascript:return parent.pswKeyPress(event);"/>
        </p>
        <br />
 	    <p>
            <input type="submit" id="btnConfirm" class="yellow-btn" value="<bean:message key='button.quote.ok'/>"/>
            <input type="button" class="graw-short-btn" value="<bean:message key='button.quote.cancel'/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webSelectRTQStatus.do?CLV=${sessionScope.CLV}'" />
        </p>
    </div>
    </form>
</body>
</html>
