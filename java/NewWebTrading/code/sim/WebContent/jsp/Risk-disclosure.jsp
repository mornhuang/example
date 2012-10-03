<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="${pageContext.request.requestURL}" />
<title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/until.js" ></script>
    <script type="text/javascript" src="../Script/shieldingMouse.js"></script>
</head>
<script type="text/javascript">
	$(function () {
		$(".header-lang .${sessionScope['org.apache.struts.action.LOCALE']}").addClass("active");
	});
	function loadPage(){
	document.getElementById("nextPage").focus();
	}

    function logout(){
        if(window.confirm('<bean:message key="topic.logout"/>')){
            window.location="${pageContext.request.contextPath}/logout.do?CLV=${sessionScope.CLV}";
        }
    }
</script>
<body class="body" onload="loadPage()">
    <div class="warp">
        <div id="header">
            <ul class="header-lang">
                <li><a href="javascript:logout();" class="logout"><bean:message key="label.menu.logoff"/></a></li>
				<li><a href="../changeLang.do?CLV=WE25S&page=riskPage" class="en_US"><bean:message key="lang.en"/></a></li>
                <li><a href="../changeLang.do?CLV=WG25S&page=riskPage" class="zh_CN"><bean:message key="lang.gb"/></a></li>
                <li><a href="../changeLang.do?CLV=WT25S&page=riskPage" class="zh_TW"><bean:message key="lang.tw"/></a></li>
				<li><a href="javascript:openCommonDialog('<bean:message key="label.bottom.help"/>','FAQ.jsp');" class="help"></a></li>            
			</ul>
        </div>
        <div id="container" style="overflow:auto">
            <div class="ui-center">
                <div class="ui-center-content">
                    <div class="login-risk">
                        <h2>
                       <bean:message key="label.risk.title"/></h2>
                        <ul class="form-number-ul">
                            <li><span class="number-yellow">1</span><bean:message key="label.risk.line1"/>
                            </li>
                            <li><span class="number-yellow">2</span><bean:message key="label.risk.line2"/>
                            </li>
                            <li><span class="number-yellow">3</span><bean:message key="label.risk.line3"/>
                            </li>
                            <li><span class="number-yellow">4</span><bean:message key="label.risk.line4"/>
                            </li>
                            <li><span class="number-yellow">5</span><bean:message key="label.risk.line5"/>
                            </li>
                            <li><span class="number-yellow">6</span><bean:message key="label.risk.line6"/>
                            </li>
                        </ul>
                        <h2>
                           <bean:message key="label.risk.line8"/></h2>
                        <p>
                           <bean:message key="label.risk.line9"/>
                        </p>
                        <p class="center">
                            <input type="button" class="yellow-btn" value="<bean:message key="button.common.next" />" name="nextPage" id="nextPage" onclick="location.href='${pageContext.request.contextPath}/jsp/Main.jsp'" />
                        </p>
                        <p class="center">
                            <a href="javascript:openCommonDialog('<bean:message key="label.risk.line10"/>','${pageContext.request.contextPath}/html/${sessionScope['org.apache.struts.action.LOCALE']}/Offer-service-and-the-trading-indicator.html#pre-mkt');">
                               <bean:message key="label.risk.line10"/></a> <bean:message key="label.risk.line11"/> <a href="javascript:openCommonDialog('<bean:message key="label.risk.line12"/>','${pageContext.request.contextPath}/html/${sessionScope['org.apache.struts.action.LOCALE']}/Offer-service-and-the-trading-indicator.html#special');">
                                    <bean:message key="label.risk.line12"/></a>
                        </p>
                        <div id="dialog" class="hide">
                            <iframe src="" frameborder="0"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer">
            <span><bean:message key="copyright"/> <bean:message key="copyright.year"/> <bean:message key="company.name"/>。 <bean:message key="label.bottom.all"/> </span> 
            <a href="javascript:openCommonDialog('<bean:message key="label.bottom.disclaimer" />','${pageContext.request.contextPath}/html/${sessionScope['org.apache.struts.action.LOCALE']}/Disclaimers.html');"><bean:message key="label.bottom.disclaimer" /></a> | 
            <a href="javascript:openCommonDialog('<bean:message key="label.bottom.dpp" />','${pageContext.request.contextPath}/html/${sessionScope['org.apache.struts.action.LOCALE']}/Data-Privacy-Policy.html');"><bean:message key="label.bottom.dpp" /></a>
        </div>
    </div>
</body>
</html>