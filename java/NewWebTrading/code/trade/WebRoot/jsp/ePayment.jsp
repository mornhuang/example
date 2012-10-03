<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
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
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
    <script type="text/javascript">
    function ePayment() {
    	$(".tab-2").removeClass("active");
        $(".tab-1").addClass("active");
        $(".content-1").show();
        $(".content-2").hide();
    }

    function oPayment() {
    	$(".tab-1").removeClass("active");
        $(".tab-2").addClass("active");
        $(".content-1").hide();
        $(".content-2").show();
    }
	
	function popBOCG() {
		var browser = navigator.appName;	
		var XPos, YPos;
		if (browser == "Netscape"){
			XPos=window.screenX+5;
			YPos=window.screenY+100;
		}else{
			XPos=window.screenLeft - 2;
			YPos=window.screenTop;
		}
		window.open("<%=request.getContextPath()%>/ePaymentTransfer.do?CLV=${sessionScope.CLV}&bankCode=BOC","BOC","width=800,height=600, location=no,toolbar=no,resizable=yes,scrollbars=yes,status=yes,top=10,left=10");
	}	
	
	function popPPS(){
		window.open("<%=request.getContextPath()%>/ePaymentTransfer.do?CLV=${sessionScope.CLV}&bankCode=IPPS","PPS","width=800,height=600, location=no,toolbar=no,resizable=yes,scrollbars=yes,status=yes,top=10,left=10");
	}

	$(function () {
		<c:if test="${!empty error}">
		alert('<bean:message key="${error}"/>');
		</c:if>
	});
//    function openWin() {
//    	//window.open('${pageContext.request.contextPath}/jsp/ePayment-top.jsp','IEHelp',"toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=640,height=480,left=0,top=0");
//   		var browser = navigator.appName;	
//   		var XPos, YPos;
//   		if (browser == "Netscape")
//   		{
//   			XPos=window.screenX+5;
//   			YPos=window.screenY+100;
//   		}
//   		else
//   		{
//   			XPos=window.screenLeft - 2;
//   			YPos=window.screenTop;
//   		} 
//   		var iPPS = window.open("${pageContext.request.contextPath}/jsp/ePayment-top.jsp","PPS",'toolbar=0,location=0, directories=0,status=0,menubar=0,scrollbars=yes,resizable=0,width=798,height=498,top=' + YPos + ',left='+XPos);
//    }
        
//        $(function () {
//            $(".tab-1").click(function () {
//                $(".tab-2").removeClass("active");
//                $(this).addClass("active");
//                $(".content-1").show();
//                $(".content-2").hide();
//            });
//            $(".tab-2").click(function () {
//                $(".tab-1").removeClass("active");
//                $(this).addClass("active");
//                $(".content-1").hide();
//                $(".content-2").show();
//            });
//        });

    </script>
</head>
<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.epayment.title"/></span><b></b></span> <span class="position"><bean:message key="label.epayment.head"/></span>
    </h1>
    <div class="page-content">
        <div class="ePayment">
            <div class="tab">
                <a href="javascript: ePayment();" class="tab-1 active"></a><a href="javascript: oPayment();" class="tab-2"></a>
            </div>
            <div class="content">
                <div class="content-1">
                    <div class="pps">
                        <p>
                            <bean:message key="label.epayment.transfer1.row1"/><a href="javascript:popPPS()"><bean:message key="label.epayment.transfer1.row2"/></a><bean:message key="label.epayment.transfer1.row3"/><a
                                href="javascript:popPPS()"><bean:message key="label.epayment.transfer1.row4"/></a><bean:message key="label.epayment.transfer1.row5"/></p>
                        <p>
                            <bean:message key="label.epayment.transfer1.row6"/><a href="javascript:popPPS()"><bean:message key="label.epayment.transfer1.row7"/></a><bean:message key="label.epayment.transfer1.row8"/><a
                                href="javascript:popPPS()"><bean:message key="label.epayment.transfer1.row9"/></a><bean:message key="label.epayment.transfer1.row10"/></p>
                    </div>
                    <div class="boc">
                        <p>
                            <bean:message key="label.epayment.transfer1.row11"/><a href="javascript:popBOCG()"><bean:message key="label.epayment.transfer1.row12"/></a><bean:message key="label.epayment.transfer1.row13"/></p>
                    </div>
                </div>
                <div class="content-2">
                    <div class="hsbc">
                        <p>
                            <bean:message key="label.epayment.transfer2.row1"/><a href="javascript:parent.openCommonDialog('<bean:message key="label.epayment.transfer2.row2"/>','<bean:message key="link.epayment.transfer2.row2"/>');"><bean:message key="label.epayment.transfer2.row2"/></a><bean:message key="label.epayment.transfer2.row3"/>
                        </p>
                        <p class="desc">
                            <strong>*</strong><bean:message key="label.epayment.transfer2.row4a"/><a href="javascript:parent.openCommonDialog('<bean:message key="label.epayment.transfer2.row23"/>','<bean:message key="link.epayment.transfer2.row23"/>');"><bean:message key="label.epayment.transfer2.row4b"/></a><bean:message key="label.epayment.transfer2.row5"/></p>
                    </div>
                    <div class="hangseng">
                        <p>
                            <bean:message key="label.epayment.transfer2.row6"/><a href="javascript:parent.openCommonDialog('<bean:message key="label.epayment.transfer2.row7"/>','<bean:message key="link.epayment.transfer2.row7"/>');"><bean:message key="label.epayment.transfer2.row7"/></a><bean:message key="label.epayment.transfer2.row8"/>
                        </p>
                        <p class="desc">
                            <strong>*</strong><bean:message key="label.epayment.transfer2.row4a"/><a href="javascript:parent.openCommonDialog('<bean:message key="label.epayment.transfer2.row23"/>','<bean:message key="link.epayment.transfer2.row23"/>');"><bean:message key="label.epayment.transfer2.row4b"/></a><bean:message key="label.epayment.transfer2.row5"/>
                        </p>
                    </div>
                    <div class="bea">
                        <p>
                           <bean:message key="label.epayment.transfer2.row9a"/><a href="javascript:parent.openCommonDialog('<bean:message key="label.epayment.transfer2.row9b"/>','<bean:message key="link.epayment.transfer2.row9b"/>');"><bean:message key="label.epayment.transfer2.row9b"/></a><bean:message key="label.epayment.transfer2.row10"/>
                        </p>
                    </div>
                    <div class="pps">
                        <p>
                            <bean:message key="label.epayment.transfer2.row11"/></p>
                        <p class="p2">
                            <bean:message key="label.epayment.transfer2.row12"/><a href="http://www.ppshk.com/index_c.html" target="_blank"><bean:message key="label.epayment.transfer2.row13"/></a><bean:message key="label.epayment.transfer2.row14"/></p>
                    </div>
                    <div class="scb">
                        <p>
                           <bean:message key="label.epayment.transfer2.row15a"/><a href="javascript:parent.openCommonDialog('<bean:message key="label.epayment.transfer2.row15b"/>','<bean:message key="link.epayment.transfer2.row15b"/>');"><bean:message key="label.epayment.transfer2.row15b"/></a><bean:message key="label.epayment.transfer2.row16"/>
                        </p>
                    </div>
                    <div class="eps">
                        <p>
                           <bean:message key="label.epayment.transfer2.row17"/><a href="javascript:parent.changeCenterSrc('ePayment-eps.jsp');"><bean:message key="label.epayment.transfer2.row18"/></a><bean:message key="label.epayment.transfer2.row19"/></p>
                        <p>
                            <bean:message key="label.epayment.transfer2.row20"/></p>
                    </div>
                    <c:if test="${ICBCFlag == true}">
                    <div class="icbc">
                        <p>
                            <b><bean:message key="label.epayment.transfer2.row21"/></b></p>
                        <p class="desc">
                            <bean:message key="label.epayment.transfer2.row22"/><br/>${result.icbcAccountName }</p>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
