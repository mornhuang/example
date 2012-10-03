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
				alert("<bean:message key='message.quote.agree'/>");
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
            	$("#etnetBuyForm").submit();
            }
        }
    </script>
</head>
<%
	String prodId = request.getParameter("productId");
	String productPrice = request.getParameter("productPrice_SSTR_IQS");
	if(prodId.equals("SSTR_IQS_CN")){
		productPrice = request.getParameter("productPrice_SSTR_IQS_CN");
	}
%>
<c:if test="${!empty error}">
<script type="text/javascript">
alert('<bean:message key="${error}"/>');
document.getElementById("password").focus();
</script>
</c:if>
<body>
    <form action="../webPurchaseRTQ.do" id="etnetBuyForm" method="post" onsubmit="return validateInput();">
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
            <p><bean:message key='message.quote.agreeword'/></p>
            <h2><bean:message key='message.quote.etnetnotice1'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice11'/> </li>
            </ul>
            <h2><bean:message key='message.quote.etnetnotice2'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice21'/></li>
            </ul>
            
            <h2><bean:message key='message.quote.etnetnotice3'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice31'/> </li>
                <li><bean:message key='message.quote.etnetnotice32'/></li>
                <li><bean:message key='message.quote.etnetnotice33'/> 
                    <ul>
                        <li><bean:message key='message.quote.etnetnotice33i'/> </li>
                        <li><bean:message key='message.quote.etnetnotice33ii'/></li>
                        <li><bean:message key='message.quote.etnetnotice33ii'/></li>
                        <li><bean:message key='message.quote.etnetnotice33iv'/> </li>
                    </ul>
                </li>
                <li><bean:message key='message.quote.etnetnotice34'/></li>
            </ul>
            <h2><bean:message key='message.quote.etnetnotice4'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice41'/></li>
                <li><bean:message key='message.quote.etnetnotice42'/></li>
                <li><bean:message key='message.quote.etnetnotice43'/> </li>
                <li><bean:message key='message.quote.etnetnotice44'/></li>
                <li><bean:message key='message.quote.etnetnotice45'/></li>
                <li><bean:message key='message.quote.etnetnotice46'/>
                    <ul>
                        <li><bean:message key='message.quote.etnetnotice46i'/></li>
                        <li><bean:message key='message.quote.etnetnotice46ii'/></li>
                        <li><bean:message key='message.quote.etnetnotice46iii'/> </li>
                    </ul>
                </li>
                <li><bean:message key='message.quote.etnetnotice47'/></li>
                <li><bean:message key='message.quote.etnetnotice48'/></li>
                <li><bean:message key='message.quote.etnetnotice49'/></li>
                <li><bean:message key='message.quote.etnetnotice410'/></li>
                <li><bean:message key='message.quote.etnetnotice411'/> </li>
                <li><bean:message key='message.quote.etnetnotice412'/></li>
                <li><bean:message key='message.quote.etnetnotice413'/></li>
            </ul>
            <h2><bean:message key='message.quote.etnetnotice5'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice51'/></li>
                <li><bean:message key='message.quote.etnetnotice52'/></li>
                <li><bean:message key='message.quote.etnetnotice53'/> </li>
            </ul>
            <h2><bean:message key='message.quote.etnetnotice6'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice61'/></li>
                <li><bean:message key='message.quote.etnetnotice62'/></li>
            </ul>
            <h2><bean:message key='message.quote.etnetnotice7'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice71'/></li>
                <li><bean:message key='message.quote.etnetnotice72'/></li>
                <li><bean:message key='message.quote.etnetnotice73'/></li>
                <li><bean:message key='message.quote.etnetnotice74'/></li>
                <li><bean:message key='message.quote.etnetnotice75'/> </li>
                <li><bean:message key='message.quote.etnetnotice76'/></li>
                <li><bean:message key='message.quote.etnetnotice77'/></li>
            </ul>
            <h2><bean:message key='message.quote.etnetnotice8'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice81'/></li>
                <li><bean:message key='message.quote.etnetnotice82'/> </li>
                <li><bean:message key='message.quote.etnetnotice83'/></li>
                <li><bean:message key='message.quote.etnetnotice84'/> 
                    <ul>
                        <li><bean:message key='message.quote.etnetnotice84i'/></li>
                        <li><bean:message key='message.quote.etnetnotice84ii'/></li>
                    </ul>
                </li>
            </ul>
            <h2><bean:message key='message.quote.etnetnotice9'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice91'/></li>
                <li><bean:message key='message.quote.etnetnotice92'/>
                    <ul>
                        <li><bean:message key='message.quote.etnetnotice92i'/> </li>
                        <li><bean:message key='message.quote.etnetnotice92ii'/></li>
                        <li><bean:message key='message.quote.etnetnotice92iii'/></li>
                        <li><bean:message key='message.quote.etnetnotice92iv'/></li>
                    </ul>
                </li>
                <li><bean:message key='message.quote.etnetnotice93'/>
                    <ul>
                        <li><bean:message key='message.quote.etnetnotice93i'/></li>
                        <li><bean:message key='message.quote.etnetnotice93ii'/></li>
                    </ul>
                </li>
            </ul>
            <h2><bean:message key='message.quote.etnetnotice10'/></h2>
            <ul>
                <li><bean:message key='message.quote.etnetnotice101'/></li>
                <li><bean:message key='message.quote.etnetnotice102'/></li>
                <li><bean:message key='message.quote.etnetnotice103'/></li>
                <li><bean:message key='message.quote.etnetnotice104'/> </li>
                <li><bean:message key='message.quote.etnetnotice105'/></li>
                <li><bean:message key='message.quote.etnetnotice106'/></li>
            </ul>
            <h2>
            <bean:message key='message.quote.detailwarn'/></h2>
        </div>
        <p>
            <input type="checkbox" id="ckAgree" /><label for="ckAgree"><bean:message key='message.quote.agreenotice'/></label>
        </p>
        <br />
        <h2>
            <bean:message key='message.quote.agreeetnetprice' arg0="<%=productPrice%>"/></h2>
        <p class="form-notice">
            <strong><bean:message key='message.quote.etnetsys'/></strong>
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
            <input type="button" class="graw-short-btn" value="<bean:message key='button.quote.cancel'/>" onclick="javascript:location.href='${pageContext.request.contextPath}/webEnquireRTQProduct.do?CLV=WT25'" />
        </p>
    </div>
    </form>
</body>
</html>
