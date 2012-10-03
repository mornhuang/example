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
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
<!--    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>-->
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/jquery.fmatter-min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
    <script type="text/javascript" src="../Script/sht.locale-${sessionScope['org.apache.struts.action.LOCALE']}.js"></script>
    <script type="text/javascript" src="../Script/until.js"></script>
    <script type="text/javascript" src="../Script/main.js"></script>
    <script type="text/javascript" src="../Script/ajax.js"></script>
    
</head>
<script type="text/javascript">
function submitAccess(productId){
	if(productId=='SSTR_AAST' || productId=='SSTR_AAST_CN'){
		//parent.showSouthPannel();
		//parent.showOrderTicketForm();
		document.frm.submit();
	}else {
		parent.popupRtqApp('<bean:message key="label.menu.rtqServiceApply"/>', '${pageContext.request.contextPath}/webAccessRTQ.do?accessFlag=mini&CLV=${sessionScope.CLV}&productId='+productId);
	}  
}
</script>
<c:if test="${!empty error}">
<script type="text/javascript">
<%if(request.getAttribute("usedProductId")!=null && !request.getAttribute("usedProductId").equals("")) {%>
	<bean:define id="arg0">
	  <bean:message key="ProductMSG_${usedProductId}" />
	</bean:define>
<%} %>
	alert('<bean:message key="${error}" arg0="${arg0}"/>');
</script>
</c:if>
<body>
<form action="${pageContext.request.contextPath}/webAccessRTQ.do?productId=${usedProductId}&CLV=${sessionScope.CLV}" name="frm" method="post">
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.quote.heading"/></span><b></b></span> <span class="position"><bean:message key="AccountServices"/>
            > <bean:message key="label.quote.heading"/></span>
    </h1>
    <div class="page-content">
        <ul class="rtq-status-ul">
            <li><a href="../webEnquireRTQProduct.do?method=purchase&productId=${productId}&CLV=${sessionScope.CLV}" class="rtq-buy-btn"></a></li>
            
            <c:if test="${existProductId!=null || productId!=null}">
                <c:choose>
                	<c:when test="${existProductId!=null && productId==null}">
                	    <li><a href="../webListSelectRTQ.do?method=update&productId=${existProductId}&CLV=${sessionScope.CLV}" class="rtq-update-btn"></a></li>
                	</c:when>
                	<c:otherwise>
                		<li><a href="../webListSelectRTQ.do?method=update&productId=${productId}&CLV=${sessionScope.CLV}" class="rtq-update-btn"></a></li>
                	</c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${existProductId==null && productId==null}">
                 
            </c:if>

            <c:if test="${productId!=null}">
                 <li><a href="../webListSelectRTQ.do?method=cancel&productId=${productId}&CLV=${sessionScope.CLV}" class="rtq-cancel-btn"></a></li>
            </c:if>
            <c:if test="${productId==null}">
                 
            </c:if>
            
            <c:if test="${usedProductId!=null}">
            	<li>
           			<a href="javascript:submitAccess('${usedProductId }');" class="rtq-enter-btn"></a>
                </li>
            </c:if>
            <c:if test="${existProductId==null}">
                 
            </c:if>           
        </ul>
    </div>
 </form>
</body>
</html>
