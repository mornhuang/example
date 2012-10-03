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

        $(function () {
            $(".ism-pannel-link a").hover(function () {
                var src = $(this).find("img").attr("src").replace("_thumb.", "_thumb_on.");
                $(this).find("img").attr("src", src);
            }, function () {
                resetActive();
                if ($(".ism-pannel-pic img").attr("src").indexOf("1") > 0)
                    setActive(0);
                if ($(".ism-pannel-pic img").attr("src").indexOf("2") > 0)
                    setActive(1);
                if ($(".ism-pannel-pic img").attr("src").indexOf("3") > 0)
                    setActive(2);
            });
            $(".ism-pannel-link a").click(function () {
                resetActive();
                var src = $(this).find("img").attr("src");
                $(this).find("img").attr("src", src.replace("_thumb.", "_thumb_on."));
                $(".ism-pannel-pic img").attr("src", src.replace("_thumb_on.", ".").replace("_thumb.", "."));
            });
            var resetActive = function () {
                $(".ism-pannel-link img").each(function () {
                    var src = $(this).attr("src").replace("_thumb_on", "_thumb");
                    $(this).attr("src", src);
                });
            }
            var setActive = function (item) {
                var src = $(".ism-pannel-link a").eq(item).find("img").attr("src");
                $(".ism-pannel-link a").eq(item).find("img").attr("src", src.replace("_thumb.", "_thumb_on."));
            }
        });



</script>

<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="Product_SHK"/></span><b></b></span> <span class="position"><bean:message key="AccountServices"/> >
            <bean:message key="Product_SHK"/></span>
    </h1>
    <div class="page-content">
        <div class="ism-pannel ui-corner-all ui-helper-clearfix">
            <div class="ism-pannel-pic">
                <img src="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/images/websec_product1.gif" alt="" />
            </div>
            <div class="ism-pannel-link">
                <a href="javascript:;">
                    <img src="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/images/websec_product1_thumb_on.gif" alt="" /></a>
                <a href="javascript:;">
                    <img src="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/images/websec_product2_thumb.gif" alt="" /></a>
                <a href="javascript:;">
                    <img src="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/images/websec_product3_thumb.gif" alt="" /></a>
            </div>
        </div>
        <p>
            <bean:message key="label.SHK.message1"/>
        </p>
        <br />
        <h2>
            <bean:message key="label.SHK.message2"/></h2>
        <p>
           <bean:message key="label.SHK.message3"/></p>
        <ul class="form-number-ul">
            <li><span class="number">1</span><strong><bean:message key="label.SHK.message4"/></strong> <bean:message key="label.SHK.message5"/></li>
            <li><span class="number">2</span><strong><bean:message key="label.SHK.message6"/></strong> <bean:message key="label.SHK.message7"/></li>
            <li><span class="number">3</span><strong><bean:message key="label.SHK.message8"/></strong> <bean:message key="label.SHK.message9"/></li>
            <li><span class="number">4</span><strong><bean:message key="label.SHK.message10"/></strong> <bean:message key="label.SHK.message11"/></li>
            <li><span class="number">5</span><strong><bean:message key="label.SHK.message12"/></strong> <bean:message key="label.SHK.message13"/></li>
            <li><span class="number">6</span><strong><bean:message key="label.SHK.message14"/></strong> <bean:message key="label.SHK.message15"/></li>
            <li><span class="number">7</span><strong><bean:message key="label.SHK.message16"/></strong> <bean:message key="label.SHK.message17"/></li>
            <li><span class="number">8</span><strong><bean:message key="label.SHK.message18"/></strong> <bean:message key="label.SHK.message19"/></li>
            <li><span class="number">9</span><strong><bean:message key="label.SHK.message20"/></strong> <bean:message key="label.SHK.message21"/></li>
            <li><span class="number">10</span><strong><bean:message key="label.SHK.message22"/></strong> <bean:message key="label.SHK.message23"/></li>
        </ul>
        <br />
        <h2>
            <bean:message key="label.SHK.message24"/>HKD${PriceInHkd }</h2>
        <p>
            <bean:message key="label.SHK.message25"/></p>
        <br />
        <h2>
            <bean:message key="label.SHK.message26"/>
        </h2>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="label.SHK.message27"/></li>
            <li><span class="number-yellow">2</span><bean:message key="label.SHK.message28"/></li>
            <li><span class="number-yellow">3</span><bean:message key="label.SHK.message29"/></li>
            <li><span class="number-yellow">4</span><bean:message key="label.SHK.message30"/></li>
        </ul>
         
       
         	<p>
         	<input type="button" class="yellow-btn" value="<bean:message key="button.quote.buyim"/>" onclick="javascript:location.href='${pageContext.request.contextPath}/jsp/ISM-2.jsp'"  />
        	 </p>
      
      
    </div>
</body>
</html>