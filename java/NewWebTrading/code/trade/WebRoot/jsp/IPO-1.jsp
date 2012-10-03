<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
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
<c:if test="${empty result}">
$(function(){
	$("#Submit").attr('disabled',true);  
	});
</c:if>
</script>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo1.heading"/></span><b></b></span>
        <span class="position"><bean:message key="label.ipo1.heading"/>> <bean:message key="label.ipo.heading2"/></span>
    </h1>
    <div class="page-content">
        <div class="form-table-center ui-corner-all">
            <div class="title">
                IPO</div>
                <form action="../webIPOQueryCode.do" method="post">
            <table>
                <tr>
                    <td>
                        <bean:message key="message.ipo1.title"/>：
                    </td>
                </tr>
                <tr>
                    <td>
                        <select class="jquery-select" name="ipoMasterId">
                            <c:choose>
                   			<c:when test="${empty result}">
								<option value="0"><bean:message key="label.ipo1.message" /></option>                    				
                   			</c:when>
                   			<c:otherwise>
                   				<c:forEach var="ipolist" items="${result}">
	                          	  <option value="${ipolist.ipoMasterId }">${ipolist.ipoName_dsply }</option>
	                            </c:forEach>
                   			</c:otherwise>
                   		</c:choose>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="Submit" type="submit" class="short-button" value="<bean:message key="message.ipo1.button"/>" />
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
</body>
</html>
