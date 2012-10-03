<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}"/>
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
<!--    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>-->
    <script type="text/javascript" src="../Script/jquery.tablesorter.min.js"></script>
    <script type="text/javascript" src="../Script/jquery.fmatter-min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
    <script type="text/javascript" src="../Script/sht.locale-${sessionScope['org.apache.struts.action.LOCALE']}.js"></script>
    <script type="text/javascript" src="../Script/until.js"></script>
    <script type="text/javascript" src="../Script/main.js"></script>
    <script type="text/javascript" src="../Script/ajax.js"></script>
    <script type="text/javascript">
    	var popupManual = null; //弹出RTQ　app，RTQ-popup.jsp/RTQ-Status.jsp
    	function popupRtqApp(desc,url) {
    		if(popupManual == null || popupManual.closed){
    			var desURL = url; 
    			if(desc == "RTQ Application") desc = "RTQApplication";
    			var top = (screen.height - 600)/2;
    			var left = (screen.width - 900)/2;
    			popupManual = window.open(desURL, desc, "width=900,height=600,location=no,toolbar=no,resizable=yes,scrollbars=yes,status=yes,top="+top+",left="+left);
    		}
       		popupManual.focus();
    	}
    	function closeRtqApp(){
    		if(popupManual != null && !popupManual.closed){  //关闭已打开的RTQ　app
            	popupManual.close();
            }
    	}
    	
    	User.transactionProtection = "${sessionScope['loginUserInfo'].transactionProtection}";
    	User.allowTradeStatusFlag = "${sessionScope['User'].tradeInfoObject.allowTradeStatusFlag}";
    	LOCALE = "${sessionScope['org.apache.struts.action.LOCALE']}";
    	var ContextPath = "${pageContext.request.contextPath}";
        $(init);
        function logout(){
            if(window.confirm('<bean:message key="topic.logout"/>')){
                window.location="${pageContext.request.contextPath}/logout.do?CLV=${sessionScope.CLV}";
            }
        }
    </script>
</head>
<body class="body" onunload="closeRtqApp()">
    <div class="warp">
        <div id="header">
            <ul class="header-menu">
                <li class="header-menu-trading"><a href="javascript:;"><span><bean:message key="Transactions"/></span><b></b></a>
                    <div class="header-menu-trading-div">
                        <a href="javascript:void(0);" onclick="showStockTrade();"><font <c:if test="${sessionScope['org.apache.struts.action.LOCALE']=='en_US'}">style="font-size:12px; padding-left:3px"</c:if>><bean:message key="label.menu.stockTrade"/></font></a> <a href="javascript:changeCenterSrc('Margin.jsp')"><font <c:if test="${sessionScope['org.apache.struts.action.LOCALE']=='en_US'}">style="font-size:12px; padding-left:3px"</c:if>><bean:message key="MarginRatio"/></font></a>
                    </div>
                </li>
                <li class="line"></li>
                <li class="header-menu-ipo"><a href="javascript:;"><span><bean:message key="label.ipo.heading1"/></span><b></b></a>
                	<div class="header-menu-ipo-div">
                        <a href="javascript:changeCenterSrc('../webIOPQueryList.do?CLV=${sessionScope.CLV}')"><font <c:if test="${sessionScope['org.apache.struts.action.LOCALE']=='en_US'}">style="font-size:12px; padding-left:3px"</c:if>><bean:message key="label.ipo.heading1"/></font></a> <a href="javascript:changeCenterSrc('../ipoSubscriptionEnquiry.do?CLV=${sessionScope.CLV}')"><font <c:if test="${sessionScope['org.apache.struts.action.LOCALE']=='en_US'}">style="font-size:12px; padding-left:3px"</c:if>><bean:message key="label.eipo.subscription.enquiry.title"/></font></a>
                    </div>
                </li>
                <li class="line"></li>
                <li class="header-menu-account-service"><a href="javascript:;"><span><bean:message key="label.fundTransferForm.head2"/></span><b></b></a>
                    <div class="header-menu-account-service-div ui-helper-clearfix">
                        <div class="left<c:if test="${sessionScope['org.apache.struts.action.LOCALE']=='en_US'}"> en_US</c:if>">
                            <a href="javascript:changeCenterSrc('../webEnquireMisAccount.do');"><bean:message key="label.fundTransferForm.head3"/></a>
                            <a href="javascript:changeCenterSrc('../webAccountEnquiryInit.do');"><bean:message key="label.fundTransferForm.head4"/></a>
                            <a href="javascript:changeCenterSrc('${pageContext.request.contextPath}/jsp/CheckFundDeposit.jsp');"><bean:message key="ConfirmationDeposit"/></a>
                            <a href="javascript:changeCenterSrc('../webOrderTransactionHistory.do');"><bean:message key="label.trade_history.transactionshistory"/></a>
                            <a href="javascript:openCommonDialog('<bean:message key="label.menu.checkComCon"/>','<bean:message key="link.menu.checkComCon"/>')"><bean:message key="label.menu.checkComCon"/></a>
                            <a href="javascript:changeCenterSrc('../webEPaymentEntrance.do');"><bean:message key="label.fundTransferForm.head5"/></a>
                            <a href="javascript:changeCenterSrc('Remittance-service.jsp');"><bean:message key="label.icbc.title"/></a>
                            <a href="javascript:changeCenterSrc('ChangePassword.jsp');"><bean:message key="changePassword"/></a>
                            <a href="javascript:changeCenterSrc('../webGetUserNotificationEmail.do');"><bean:message key="label.menu.mailAlert"/></a>
                            <a href="javascript:openCommonDialog('<bean:message key="label.menu.tableDownload"/>','<bean:message key="link.menu.tableDownload"/>')"><bean:message key="label.menu.tableDownload"/></a>
                            <div class="clear"></div>
                            <a href="javascript:changeCenterSrc('../webEPaymentEntrance.do');" class="pic-link"><img src="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/images/menu_pic_link_1.png" alt="" /></a>
                            <a href="javascript:changeCenterSrc('../webEnquireRTQProduct.do?CLV=${sessionScope.CLV}');" class="pic-link"><img src="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/images/menu_pic_link_2.png" alt="" /></a>
                        </div>
                        <div class="right">
                            <a href="javascript:void(0);" class="down"><bean:message key="label.menu.investTool"/></a>
                            <div class="second">
                                <a href="javascript:changeCenterSrc('../webEnquireRTQProduct.do?CLV=${sessionScope.CLV}');"><bean:message key="label.menu.rtqServiceApply"/></a>
								<a href="javascript:changeCenterSrc('../AccessShk.do?CLV=${sessionScope.CLV}');"><bean:message key="label.menu.shkServiceApply"/></a>
								<a href="javascript:openCommonDialog('<bean:message key="label.menu.chatAnalyse"/>','<bean:message key="link.menu.chatAnalyse"/>');"><bean:message key="label.menu.chatAnalyse"/></a>
								<a href="javascript:openCommonDialog('<bean:message key="label.menu.companyDividendsNotices"/>','<bean:message key="link.menu.companyDividendsNotices"/>');"><bean:message key="label.menu.companyDividendsNotices"/></a>
								<a href="javascript:openCommonDialog('<bean:message key="label.menu.realNews"/>','<bean:message key="link.menu.realNews"/>');"><bean:message key="label.menu.realNews"/></a>
								<a href="javascript:changeCenterSrc('Warrants-CBBCs.jsp');"><bean:message key="label.menu.CBBCAnalyse"/></a>
                                <a href="javascript:openCommonDialog('<bean:message key="label.menu.ETF"/>','<bean:message key="link.menu.ETF"/>');"><bean:message key="label.menu.ETF"/></a>
                                <a href="javascript:openCommonDialog('<bean:message key="label.menu.IPOtimetable"/>','<bean:message key="link.menu.IPO"/>');"><bean:message key="label.menu.IPOtimetable"/></a>
                                <a href="javascript:openCommonDialog('<bean:message key="label.menu.companyNotices"/>','<bean:message key="link.menu.companyNotices"/>');"><bean:message key="label.menu.companyNotices"/></a>
								<a href="javascript:openCommonDialog('<bean:message key="label.menu.companyTimetable"/>','<bean:message key="link.menu.companyTimetable"/>');"><bean:message key="label.menu.companyTimetable"/></a>
								<a href="javascript:openCommonDialog('<bean:message key="label.menu.profitForecast"/>','<bean:message key="link.menu.profitForecast"/>');"><bean:message key="label.menu.profitForecast"/></a>
								<a href="javascript:openCommonDialog('<bean:message key="label.menu.AH"/>','<bean:message key="link.menu.AH"/>');"><bean:message key="label.menu.AH"/></a>
								<a href="javascript:openCommonDialog('<bean:message key="label.menu.htiNetTV"/>','<bean:message key="link.menu.htiNetTV"/>');"><bean:message key="label.menu.htiNetTV"/></a>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="line"></li>
                <li class="header-menu-estatement">
                	<a href="javascript:openCommonDialog('<bean:message key="label.menu.eSatament"/>','../webEMC.do');">
                    	<span><bean:message key="label.menu.eSatament"/></span>
                    </a>
                </li>
            </ul>
            <ul class="header-lang">
                <li class="userid">${User.loginName }</li>
                <li class="message">(<a id="EMCCount" href="javascript:openCommonDialog('EMC','../webEMC.do');"></a>)</li>
                <li><a href="javascript:logout();" class="logout"><bean:message key="label.menu.logoff"/></a></li>
                <li><a href="../changeLang.do?CLV=WE25S&page=mainPage" class="en_US"><bean:message key="lang.en"/></a></li>
                <li><a href="../changeLang.do?CLV=WG25S&page=mainPage" class="zh_CN"><bean:message key="lang.gb"/></a></li>
                <li><a href="../changeLang.do?CLV=WT25S&page=mainPage" class="zh_TW"><bean:message key="lang.tw"/></a></li>
                <li><a href="javascript:openCommonDialog('<bean:message key="label.bottom.help"/>','FAQ.jsp');" class="help"></a></li>
            </ul>
        </div>
        <div id="container">
            <div class="ui-north">
                <div class="ui-north-left">
                    <a href="javascript:;" class="home-btn-buy-sell"></a>
                </div>
                <div class="ui-north-pannel">
                    <form action="" id="ticketOrder" onkeydown="if(event.keyCode==13){return false;}">
                    <table class="form">
                        <tr>
                            <td><bean:message key="stockCode"/></td>
                            <td>
                                <input type="text" name="instrCode" id="instrCode" tabindex="1" maxlength="10" onkeypress="return numKeyPress(event);" class="textbox txt-common" />
                            </td>
                        </tr>
                        <tr>
                            <td><bean:message key="label.trading.rtq"/></td>
                            <td>
                                <input type="text" name="orderPrice" id="orderPrice" tabindex="2" onkeypress="return priceKeyPress(event);" onkeyup="priceKeyUp(this)" class="textbox txt-common" />
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="checkbox" class="chk-buy-all" id="chkBuyAll" />
                                <a href="javascript:;" id="lkallbuyin"><bean:message key="label.status.buyall"/>?</a> 
                            </td>
                        </tr>
                        <tr>
                            <td><bean:message key="label.trading.qty"/></td>
                            <td>
                                <input type="text" name="orderQuantity" id="orderQuantity" tabindex="3" onkeypress="return numKeyPress(event);" class="textbox txt-common" />
                            </td>
                        </tr>
                        <tr class="tpPassword">
                            <td><bean:message key="label.trading.password"/></td>
                            <td>
                                <input type="password" name="password" id="password" tabindex="4" maxlength="8" onkeypress="return pswKeyPress(event);" class="textbox txt-common" />
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a href="javascript:void(0);" id="lkBypassPassword"><bean:message key="label.trading.byPass"/></a>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="reset" class="btn-clear" onclick="resetPlaceOrder();" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="button" class="btn-buy" tabindex="5" value="" />
                                <input type="button" class="btn-sell" tabindex="6" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="sepace-line"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <p>
                                    <bean:message key="label.trading.tradeType"/> <a href="javascript:openCommonDialog('<bean:message key="label.risk.line10"/>','Offer-service-and-the-trading-indicator.jsp#pre-mkt');">
                                        ?</a>
                                </p>
                                <select name="orderType" id="orderType" onchange="chgOrderType(this)" class="jquery-select select-order-type">
                                    <option value="ENHANCED_LIMIT"><bean:message key="label.orderType.ENHANCED_LIMIT"/></option>
                                    <option value="AT_AUCTION"><bean:message key="label.orderType.AT_AUCTION"/></option>
                                    <option value="SPECIAL_LIMIT"><bean:message key="label.orderType.SPECIAL_LIMIT"/></option>
                                    <option value="CONDITIONAL"><bean:message key="label.orderType.CONDITIONAL"/></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="sepace-line"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><bean:message key="label.trading.triggerPrice"/></td>
                        </tr>
                        <tr>
                            <td>
                                <select name="conditionType" id="conditionType" class="jquery-select select-triggering-price" disabled="disabled">
                                    <option value="GTE">&ge;</option>
                                    <option value="LTE">&le;</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="triggerPrice" id="triggerPrice" onkeypress="return priceKeyPress(event);" onkeyup="priceKeyUp(this)" class="textbox txt-common" disabled="disabled" />
                            </td>
                        </tr>
                    </table>
                    </form>
                    <div class="ui-north-pannel-buy">
                        <span class="icon-buy"></span>
                        <form action="" id="buyOrder" onkeydown="if(event.keyCode==13){return false;}">
                        <table>
                            <tr>
                                <td><bean:message key="stockCode"/></td>
                                <td>
                                    <input type="text" id="buyinstrCode" name="instrCode" readonly="readonly" class="normal-area" value="" />
                                    <input type="hidden" id="buyorderSide" name="orderSide" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.qty"/></td>
                                <td>
                                    <input type="text" id="buyorderQuantityS" readonly="readonly" class="normal-area" value="" />
                                    <input type="hidden" id="buyorderQuantity" name="orderQuantity" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.rtq"/></td>
                                <td>
                                    <input type="text" id="buyorderPriceS" readonly="readonly" class="normal-area" value="" />
                                    <input type="hidden" id="buyorderPrice" name="orderPrice" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.tradeType"/></td>
                                <td>
                                    <input type="text" id="buyorderTypeS" readonly="readonly" class="normal-area" value="" />
                                    <input type="hidden" id="buyorderType" name="orderType" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.triggerPrice"/></td>
                                <td>
                                    <input type="text" id="buyconditionTypeS" readonly="readonly" class="sign-area" value="" />
                                    <input type="hidden" id="buyconditionType" name="conditionType" />
                                    <input type="text" id="buytriggerPriceS" readonly="readonly" class="value-area" value="" />
                                    <input type="hidden" id="buytriggerPrice" name="triggerPrice"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:void(0);" class="title-tooltip" title='<bean:message key="label.trading.refFeeDetail"/>'><bean:message key="label.trading.refFee"/></a>
                                </td>
                                <td>
                                    <input type="text" id="buycommonCharges" readonly="readonly" class="normal-area" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:;" class="lk-fees-and-charges"><bean:message key="label.trading.otherFee"/></a>
                                </td>
                                <td>
                                    <input type="text" id="buyotherFee" readonly="readonly" class="normal-area" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.netAmount"/></td>
                                <td>
                                    <input type="text" id="buynetAmount" readonly="readonly" class="normal-area" value="" /> 
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="button" value='<bean:message key="button.common.comfirm1"/>' class="btn-confirm" />
                                    <input type="button" value='<bean:message key="button.common.cancel1"/>' class="btn-cancel" />
                                </td>
                            </tr>
                        </table>
                        </form>
                    </div>
                    <div class="ui-north-pannel-sell">
                        <span class="icon-sell"></span>
                        <form action="" id="sellOrder" onkeydown="if(event.keyCode==13){return false;}">
                        <table>
                            <tr>
                                <td><bean:message key="stockCode"/></td>
                                <td>
                                    <input type="text" id="sellinstrCode" name="instrCode" readonly="readonly" class="normal-area" value="" />
                                    <input type="hidden" id="sellorderSide" name="orderSide" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.qty"/></td>
                                <td>
                                    <input type="text" id="sellorderQuantity" name="orderQuantity" readonly="readonly" class="normal-area" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.rtq"/></td>
                                <td>
                                    <input type="text" id="sellorderPriceS" readonly="readonly" class="normal-area" value="" />
                                    <input type="hidden" id="sellorderPrice" name="orderPrice" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.tradeType"/></td>
                                <td>
                                    <input type="text" id="sellorderTypeS" readonly="readonly" class="normal-area" value="" />
                                    <input type="hidden" id="sellorderType" name="orderType" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.triggerPrice"/></td>
                                <td>
                                    <input type="text" id="sellconditionTypeS" readonly="readonly" class="sign-area" value="" />
                                    <input type="hidden" id="sellconditionType" name="conditionType" />
                                    <input type="text" id="selltriggerPrice" name="triggerPrice" readonly="readonly" class="value-area" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td>
									<a href="javascript:void(0);" class="title-tooltip" title='<bean:message key="label.trading.refFeeDetail"/>'><bean:message key="label.trading.refFee"/></a>
                                </td>
                                <td>
                                    <input type="text" id="sellcommonCharges" readonly="readonly" class="normal-area" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:;" class="lk-fees-and-charges"><bean:message key="label.trading.otherFee"/></a>
                                </td>
                                <td>
                                    <input type="text" id="sellotherFee" readonly="readonly" class="normal-area" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.trading.netAmount"/></td>
                                <td>
                                	<input type="text" id="sellnetAmount" readonly="readonly" class="normal-area" value="" /> 
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="button" value='<bean:message key="button.common.comfirm1"/>' class="btn-confirm" />
                                    <input type="button" value='<bean:message key="button.common.cancel1"/>' class="btn-cancel" />
                                </td>
                            </tr>
                        </table>
                        </form>
                    </div>
                    <a href="javascript:;" class="ui-north-pannel-close"></a>
                </div>
                <div class="ui-north-right">
                    <a class="menu-prev disabled" href="javascript:;"></a>
                    <div class="menu-container">
                        <ul>
                            <li><a class="chart" title='<bean:message key="label.menu.chatAnalyse"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.chatAnalyse"/>','<bean:message key="link.menu.chatAnalyse"/>');">
                            </a></li>
                            <li><a class="ism" title='<bean:message key="Product_SHK"/>' href="javascript:changeCenterSrc('../AccessShk2.do?CLV=${sessionScope.CLV}');"></a>
                            </li>
                            <li><a class="warrants" title='<bean:message key="label.menu.CBBCAnalyse1"/>' href="javascript:changeCenterSrc('Warrants-CBBCs.jsp');">
                            </a></li>
                            <li><a class="news" title='<bean:message key="label.menu.realNews"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.realNews"/>','<bean:message key="link.menu.realNews"/>');">
                            </a></li>
                            <li><a class="ipo" title='<bean:message key="label.menu.IPOtimetable"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.IPOtimetable"/>','<bean:message key="link.menu.IPO"/>');">
                            </a></li>
                            <li><a class="ah" title='<bean:message key="label.menu.AH"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.AH"/>','<bean:message key="link.menu.AH"/>');">
                            </a></li>
                            <li><a class="profit" title='<bean:message key="label.menu.profitForecast"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.profitForecast"/>','<bean:message key="link.menu.profitForecast"/>');">
                            </a></li>
                            <li><a class="profile" title='<bean:message key="label.menu.companyTimetable"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.companyTimetable"/>','<bean:message key="link.menu.companyTimetable"/>');">
                            </a></li>
                            <li><a class="announcement" title='<bean:message key="label.menu.companyNotices"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.companyNotices"/>','<bean:message key="link.menu.companyNotices"/>');">
                            </a></li>
                            <li><a class="divident" title='<bean:message key="label.menu.companyDividendsNotices"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.companyDividendsNotices"/>','<bean:message key="link.menu.companyDividendsNotices"/>');">
                            </a></li>
                            <li><a class="webtv" title='<bean:message key="label.menu.htiNetTV"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.htiNetTV"/>','<bean:message key="link.menu.htiNetTV"/>');">
                            </a></li>
                            <li><a class="epayment" title='<bean:message key="label.fundTransferForm.head5"/>' href="javascript:changeCenterSrc('../webEPaymentEntrance.do');">
                            </a></li>
                            <li><a class="estatement" title='<bean:message key="label.menu.eSatament"/>' href="javascript:openCommonDialog('<bean:message key="label.menu.eSatament"/>','../webEMC.do');">
                            </a></li>
                            <li><a class="rtq" title='<bean:message key="label.menu.rtqServiceApply"/>' href="javascript:changeCenterSrc('../webEnquireRTQProduct.do?CLV=${sessionScope.CLV}');"></a>
                            </li>
                        </ul>
                    </div>
                    <a class="menu-next" href="javascript:;"></a>
                    <div class="ui-menu-style">
                        <a href="javascript:;" class="ui-menu-style-normal ui-menu-style-normal-active">
                        </a><a href="javascript:;" class="ui-menu-style-mini"></a>
                    </div>
                </div>
            </div>
            <div class="ui-center-main">
                <div class="ui-center-content">
                    <div class="font-size" title='<bean:message key="label.menu.fontSize"/>'>
                        <a href="javascript:void(0);" class="font-size-N active">A</a>
                        <a href="javascript:void(0);" class="font-size-B">A</a>
                        <a href="javascript:void(0);" class="font-size-L">A</a>
                    </div>
                   		<iframe src="" name="centerIframe" frameborder="0" id="center-frame">
                   		</iframe>
                   		<script>
                   		 //alert($.sht.clv);
                   	     document.getElementById("center-frame").src="../webPopupRTQ.do?CLV="+$.sht.clv;
                   		</script>
                </div>
            </div>
            <div class="ui-south">
                <div class="ui-resizer ui-resizer-south ui-resizer-south-open"> 
                    <span class="ui-toggler ui-toggler-south ui-toggler-south-open"></span>
                </div>
                <ul class="ui-south-tab ui-helper-clearfix">
                    <li class="active"><a href="javascript:showOrderBookTab();"><bean:message key="label.menu.tradeStatus"/></a></li>
                    <li><a href="javascript:showPortfolio();"><bean:message key="label.accountEnquiryForm.head1"/></a></li>
                    <li><a href="javascript:showPositionTab();"><bean:message key="label.accountEnquiryForm.head1b"/></a></li>
                    <li><a href="javascript:showDcyTrade();"><bean:message key="label.menu.todayDC"/></a></li>
                </ul>
                <div class="ui-south-tab-operation">
                    <a href="../webExcelExport.do?CLV=${sessionScope.CLV}" class="south-btn-export"></a>
                    <div class="south-div-filter">
                        <span><bean:message key="label.menu.filter"/></span>
                        <input type="text" id="filterCode" onkeypress="return filterCodeKeyPress(event);" class="filter" />
                        <a href="javascript:filterOrderBook();" class="go"></a>
                    </div>
                    <div class="south-div-desc">
                    <!-- 
                        <span><bean:message key="label.menu.symbol"/></span>
                     -->
                        <a href="javascript:void(0);" class="symbol-1" title='<bean:message key="label.menu.symbol-1"/>'></a>
                        <a href="javascript:void(0);" class="symbol-2" title='<bean:message key="label.menu.symbol-2"/>'></a>
                        <a href="javascript:void(0);" class="symbol-3" title='<bean:message key="label.menu.symbol-3"/>'></a>
                    </div>
                    <div class="south-div-pager">
                        <a href="javascript:void(0);" class="first"></a><a href="javascript:void(0);" class="prev"></a><span
                            class="pages">第1頁 共1頁</span> <a href="javascript:void(0);" class="next"></a><a href="javascript:void(0);" class="last">
                        </a>
                    </div>
                </div>
                <div class="ui-south-tab-content">
                    <div class="ui-south-content-order-status">
                        <table class="ui-south-grid">
                            <thead>
                                <tr>
                                    <th colspan="2">
                                        <a href="javascript:void(0);" class="refresh"></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.status"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.menu.buySell"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.orderNo1"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="stockCode"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.currency"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.trdPrice"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.triggerPrice"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.qty"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.trading.tradeType"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.trdQty"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.osQty"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.avgPrice"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.status.channel"/></a>
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="orderBook">
                                <tr class="hide">
                                    <td class="oper amd">
                                        <a class="trading-amend-link" href="javascript:;"></a>
                                    </td>
                                    <td class="oper">
                                        <a class="trading-cancel-link" href="javascript:;"></a>
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" class="trading-status-link"></a>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="hide ui-south-content-account-enquiry">
                        <table id="portfolio">
                            <tr class="alternating">
                                <td></td>
                                <td></td>
                                <td></td>
                                <td style="text-align:right;"><bean:message key="label.currency"/>:HKD</td>
                                <th rowspan="7">
									<p><bean:message key="label.fundTransferForm.accountId"/></p>
                                    <select class="jquery-select" id="acclist">
                                    <c:forEach var="account" varStatus="s" items="${User.tradeInfoObject.misAccountList.accountListDetail}">
                                   		<option value="${s.index}">${account.accountId}</option>
                                   	</c:forEach>
                                    </select>
                                    <p>
                                        <input type="button" id="acConfirm" value='<bean:message key="button.common.comfirm"/>' onclick="enquireAccount()" class="form-button" />
                                    </p>
                                </th>
                            </tr>
                            <tr>
                                <td><bean:message key="label.fundTransferForm.ledgerBalance"/></td>
                                <td>$ <span id="ledgerBalance"></span></td>
                                <td><bean:message key="label.fundTransferForm.availableBalance"/></td>
                                <td>$ <span id="availableBalance"></span></td>
                                
                            </tr>
                            <tr class="alternating">
                                <td><bean:message key="label.accountEnquiryForm.holdBalance"/></td>
                                <td>$ <span id="onHoldBalance"></span></td>
                                <td><bean:message key="label.accountEnquiryForm.cashBalance"/></td>
                                <td>$ <span id="netCashBalance"></span></td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.accountEnquiryForm.pendingSettlementD1"/></td>
                                <td>$ <span id="pendingSettlementDay1"></span></td>
                                <td><bean:message key="label.account.stockMktBal"/></td>
                                <td>$ <span id="stockMarketBalance"></span></td>
                            </tr>
                            <tr class="alternating">
                                <td><bean:message key="label.accountEnquiryForm.pendingSettlementD2"/></td>
                                <td>$ <span id="pendingSettlementDay2"></span></td>
                                <td><bean:message key="label.account.discountVal"/></td>
                                <td>$ <span id="discountedValue"></span></td>
                            </tr>
                            <tr>
                                <td><bean:message key="label.accountEnquiryForm.clearingCheque1"/></td>
                                <td>$ <span id="clearingCheque1"></span></td>
                                <td><bean:message key="label.accountEnquiryForm.buyOrderControlLimit"/></td>
                                <td id="buyOrderControlLimit"></td>
                            </tr>
                            <tr class="alternating">
                                <td><bean:message key="label.accountEnquiryForm.clearingCheque2"/></td>
                                <td>$ <span id="clearingCheque2"></span></td>
                                <td><bean:message key="label.accountEnquiryForm.maximumOrderSize"/></td>
                                <td>$ <span id="totalPurchasingPower"></span></td>
                            </tr>
                        </table>
                    </div>
                    <div class="hide ui-south-content-stock-pos">
                        <table class="ui-south-grid">
                            <thead>
                                <tr>
                                    <th>
                                        <a href="javascript:void();" class="refresh"></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0)"><bean:message key="label.accountEnquiryForm.equity"/></a>
                                    </th>
                                     <th>
                                        <a href="javascript:void(0)"><bean:message key="label.currency"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0)"><bean:message key="label.accountEnquiryForm.prevClosingPrice"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0)"><bean:message key="label.accountEnquiryForm.availableQuantity"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0)"><bean:message key="label.accountEnquiryForm.prevClosingValue"/></a>
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="stockPos">
                                <tr class="hide">
                                    <td class="oper">
                                        <a href="javascript:void(0);"><bean:message key="button.trading.sell"/></a>
                                    </td>
                                    <td class="character"></td>
                                    <td class="character"></td>
                                    <td class="digital"></td>
                                    <td class="digital"></td>
                                    <td class="digital"></td>
                                    <td class="digital"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="hide ui-south-content-dcy-trade">
                        <table class="ui-south-grid">
                            <thead>
                                <tr>
                                    <td></td>
                                    <td colspan="3"><bean:message key="label.dcyTrade.todayBuy"/></td>
                                    <td colspan="2"><bean:message key="label.dcyTrade.todaySell"/></td>
                                    <td rowspan="2"><bean:message key="label.dcyTrade.todayPL"/></td>
                                </tr>
                                <tr>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="stockCode"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.currency"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.trading.qty"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.dcyTrade.avgPrice"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.trading.qty"/></a>
                                    </th>
                                    <th>
                                        <a href="javascript:void(0);"><bean:message key="label.dcyTrade.avgPrice"/></a>
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="dcyTrade">
                                <tr class="hide">
                                    <td class="character"></td>
                                    <td class="character"></td>
                                    <td class="digital"></td>
                                    <td class="digital"></td>
                                    <td class="digital"></td>
                                    <td class="digital"></td>
                                    <td>
                                        <span></span>(<span></span> <bean:message key="label.dcyTrade.stk"/>)
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer">
            <span>Copyright &copy; 2011 <bean:message key="group.name"/> <bean:message key="label.bottom.all"/></span>
            <a href="javascript:openCommonDialog('<bean:message key="label.bottom.disclaimer"/>','Disclaimers.jsp');"><bean:message key="label.bottom.disclaimer"/></a> | <a href="javascript:openCommonDialog('<bean:message key="label.bottom.dpp"/>','Data-Privacy-Policy.jsp');"><bean:message key="label.bottom.dpp"/></a>
        </div>
    </div>
    <div id="dialog" class="hide">
        <iframe src="" frameborder="0"></iframe>
    </div>
    <div id="dialog2" class="hide">
        <iframe src="" frameborder="0"></iframe>
    </div>
    <div id="dialog3" class="hide">
        <iframe src="" frameborder="0"></iframe>
    </div>
    <div id="dialog4" class="hide">
        <iframe src="" frameborder="0"></iframe>
    </div>
    <div id="dialog5" class="hide">
        <iframe src="" frameborder="0"></iframe>
    </div>
    <div id="statusDialog" class="trading-status-pop ui-pop-padding" title='<bean:message key="label.trading.tradeStatus"/>'>
        <table class="ui-corner-all">
            <tr>
                <th><bean:message key="label.epayment.pps.row4"/></th>
                <td id="accountId"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeNo"/></th>
                <td id="mcsOrderId"></td>
            </tr>
            <tr>
                <th><bean:message key="label.status.status"/></th>
                <td id="mtssOrderState"></td>
            </tr>
            <tr>
                <th><bean:message key="label.general.buy"/>/<bean:message key="label.general.sell"/></th>
                <td id="orderSideS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeType"/></th>
                <td id="orderTypeS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.accountEnquiryForm.equity"/></th>
                <td id="stockCode"></td>
            </tr>
            <tr>
                <th><bean:message key="label.currency"/></th>
                <td id="ccy"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradePrice"/></th>
                <td id="orderPrice"></td>
            </tr>
            <tr class="condition">
                <th><bean:message key="label.trading.triggerCondition"/></th>
                <td id="condition"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.initQty"/></th>
                <td id="initialQuantity"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.chgModfiyQty"/></th>
                <td id="changedQty"></td>
            </tr>
            <tr>
                <th>
					<bean:message key="label.trading.tradeVolume"/>
					<a class="traing-status-detail-link" href="javascript:;">（<bean:message key="label.quote.detail"/>）</a>
                </th>
                <td>
					<a class="traing-status-detail-link2" href="javascript:;"><em id="filledQty" style="font-style: normal"></em></a>
                </td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.osQty"/></th>
                <td id="outstandingQuantity"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.validation"/></th>
                <td id="validityType"></td>
            </tr>
            <tr>
                <th><bean:message key="label.status.rejectReason"/></th>
                <td id="rejectMessage"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.aon"/></th>
                <td id="allOrNothingFlag"></td>
            </tr>
            <tr>
                <th><bean:message key="label.status.channel"/></th>
                <td id="channelType"></td>
            </tr>
        </table>
        <p>
            <bean:message key="label.menu.symbol-1"/></p>
        <p>
            <bean:message key="label.menu.symbol-2"/></p>
        <p>
            <bean:message key="label.menu.symbol-3"/></p>
        <a class="notice" href="javascript:openCommonDialog('<bean:message key="label.bottom.help"/>','FAQ.jsp?#q5');"><bean:message key="label.trading.note"/></a>
    </div>
    <div id="amendDialog" class="trading-status-pop ui-pop-padding" title='<bean:message key="label.trading.modfiyOrder"/>'>
		<form action="" id="amendDialogForm" onkeydown="if(event.keyCode==13){return false;}">
        <table class="ui-corner-all">
            <tr>
                <th><bean:message key="label.epayment.pps.row4"/></th>
                <td id="accountId"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeNo"/></th>
                <td id="mcsOrderId"></td>
            </tr>
            <tr>
                <th><bean:message key="label.status.status"/></th>
                <td id="mtssOrderState"></td>
            </tr>
            <tr>
                <th><bean:message key="label.general.buy"/>/<bean:message key="label.general.sell"/></th>
                <td id="orderSideS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeType"/></th>
                <td id="orderTypeS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.stock"/></th>
                <td id="stockCode"></td>
            </tr>
             <tr>
                <th><bean:message key="label.currency"/></th>
                <td id="ccy"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradePrice"/></th>
                <td id="orderPrice"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.initQty"/></th>
                <td id="initialQuantityS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.chgModfiyQty"/></th>
                <td id="changedQtyS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeVolume"/></th>
                <td id="filledQtyS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.osQty"/></th>
                <td id="outstandingQuantity"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.validation"/></th>
                <td id="validityType"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.aon"/></th>
                <td id="allOrNothingFlag"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.triggerCondition"/></th>
                <td id="conditionType"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.triggerPrice2"/></th>
                <td>
                    <input type="text" id="atriggerPrice" name="triggerPrice" onkeypress="return priceKeyPress(event);" onkeyup="priceKeyUp(this)" class="form-input" value="" />
                </td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tgPrice"/></th>
                <td>
                    <input type="text" id="anewOrderPrice" name="newOrderPrice" onkeypress="return priceKeyPress(event);" onkeyup="priceKeyUp(this)" class="form-input" value="" />
                    <input type="hidden" id="amcsOrderId" name="mcsOrderId" />
                    <input type="hidden" id="amtssOrderId" name="mtssOrderId" />
                    <input type="hidden" id="ainstrCode" />
                    <input type="hidden" id="aorderType" />
                    <input type="hidden" id="aorderSide" />
                    <input type="hidden" id="ainitialQuantity" />
                    <input type="hidden" id="achangedQty" />
                    <input type="hidden" id="afilledQty" />
                </td>
            </tr>
            <tr>
                <th><bean:message key="label.status.ultimateQty"/></th>
                <td>
                    <input type="text" id="anewOrderQty" name="newOrderQty" onkeypress="return numKeyPress(event);" class="form-input" value="" />
                </td>
            </tr>
            <tr class="tpPassword">
                <th><bean:message key="label.trading.inputPsw"/></th>
                <td>
                    <input type="password" name="password" id="apassword" class="form-input" maxlength="10" onkeypress="return pswKeyPress(event);" />
                </td>
            </tr>
        </table>
        </form>
        <p class="operation">
            <input type="button" class="yellow-btn" onclick="modfiyOrderAction()" value='<bean:message key="button.common.comfirmModfiy"/>' />
            <input type="button" id="btnBackAmend" class="graw-short-btn" value='<bean:message key="button.common.back"/>' />
        </p>
        <p>
            <bean:message key="label.menu.symbol-1"/></p>
        <p>
            <bean:message key="label.menu.symbol-2"/></p>
        <p>
            <bean:message key="label.menu.symbol-3"/></p>
        <p>
            <a class="notice" href="javascript:openCommonDialog('<bean:message key="label.bottom.help"/>','FAQ.jsp?#q5');"><bean:message key="label.trading.note"/></a>
        </p>
        <p>
            <a class="notice amend-cancel-link" href="javascript:void(0);"><bean:message key="label.trading.cancelModfiyNote"/></a>
        </p>
    </div>
    <div id="cancelDialog" class="trading-status-pop ui-pop-padding" title='<bean:message key="label.trading.cancelOrder"/>'>
    	<form action="" id="cancelDialogForm" onkeydown="if(event.keyCode==13){return false;}">
        <table class="ui-corner-all">
            <tr>
                <th><bean:message key="label.epayment.pps.row4"/></th>
                <td id="accountId"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeNo"/></th>
                <td id="mcsOrderId"></td>
            </tr>
            <tr>
                <th><bean:message key="label.status.status"/></th>
                <td id="mtssOrderState"></td>
            </tr>
            <tr>
                <th><bean:message key="label.general.buy"/>/<bean:message key="label.general.sell"/></th>
                <td id="orderSideS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeType"/></th>
                <td id="orderTypeS"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.stock"/></th>
                <td id="stockCode"></td>
            </tr>
            <tr>
                <th><bean:message key="label.currency"/></th>
                <td id="ccy"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradePrice"/></th>
                <td id="orderPrice"></td>
            </tr>
            <tr class="condition">
                <th><bean:message key="label.trading.triggerCondition"/></th>
                <td id="condition"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.initQty"/></th>
                <td id="initialQuantity"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.chgModfiyQty"/></th>
                <td id="changedQty"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeVolume"/></th>
                <td id="filledQty"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.osQty"/></th>
                <td id="outstandingQuantity"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.validation"/></th>
                <td id="validityType"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.aon"/></th>
                <td id="allOrNothingFlag"></td>
            </tr>
            <tr class="tpPassword">
                <th><bean:message key="label.trading.inputPsw"/></th>
                <td>
                    <input type="password" id="cpassword" name="password" class="form-input" maxlength="10" onkeypress="return pswKeyPress(event);" />
                    <input type="hidden" id="MCSOrderID" name="MCSOrderID" />
                    <input type="hidden" id="MTSSOrderID" name="MTSSOrderID" />
                </td>
            </tr>
        </table>
        </form>
        <p class="operation">
            <input type="button" class="yellow-btn" onclick="cancelOrderAction()" value='<bean:message key="button.common.comfirmCancel"/>' />
            <input type="button" id="btnBackCancel" class="graw-short-btn" value='<bean:message key="button.common.back"/>' />
        </p>
        <p>
            <bean:message key="label.menu.symbol-1"/></p>
        <p>
            <bean:message key="label.menu.symbol-2"/></p>
        <p>
            <bean:message key="label.menu.symbol-3"/></p>
        <a class="notice" href="javascript:openCommonDialog('<bean:message key="label.bottom.help"/>','FAQ.jsp?#q5');"><bean:message key="label.trading.note"/></a>
        <p>
            <a class="notice amend-cancel-link" href="javascript:void(0);"><bean:message key="label.trading.cancelModfiyNote"/></a>
        </p>
    </div>
    <div id="noticeDialog" class="trading-status-pop ui-pop-padding" title='<bean:message key="label.trading.cancelModfiyNote"/>'>
        <h2>
            <bean:message key="label.trading.cancelModfiyNote"/>:
        </h2>
        <p>
            <bean:message key="label.trading.cancelModfiyNote1"/>
            <bean:message key="label.trading.cancelModfiyNote2"/>
        </p>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="label.trading.cancelModfiyNote3"/></li>
            <li><span class="number-yellow">2</span><bean:message key="label.trading.cancelModfiyNote4"/></li>
            <li><span class="number-yellow">3</span><bean:message key="label.trading.cancelModfiyNote5"/></li>
        </ul>
    </div>
    <div id="confirmRequestDialog" class="trading-status-pop ui-pop-padding" title='<bean:message key="label.trading.readyOrder"/>'>
        <table class="ui-corner-all">
            <tr class="alternating">
                <th><bean:message key="label.trading.tradeId"/></th>
                <td id="confirmmcsOrderId"></td>
            </tr>
            <tr>
                <th><bean:message key="label.general.buy"/>/<bean:message key="label.general.sell"/></th>
                <td id="confirmorderSide"></td>
            </tr>
            <tr class="alternating">
                <th><bean:message key="label.trading.stock"/></th>
                <td id="confirminstrCode"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.qty"/></th>
                <td id="confirmorderQuantity"></td>
            </tr>
            <tr class="alternating">
                <th><bean:message key="label.status.transaction"/></th>
                <td id="confirmorderPrice"></td>
            </tr>
            <tr>
                <th><bean:message key="label.trading.tradeAmount"/></th>
                <td id="confirmnetAmount"></td>
            </tr>
        </table>
    </div>
    <div id="bypassPasswordDialog" class="bypass-password-pop ui-pop-padding" title='<bean:message key="label.bypass.bypassSetting"/>'>
    <form action="" id="modfiyTPForm" onkeydown="if(event.keyCode==13){return false;}">
        <h2>
            <bean:message key="label.bypass.bypassSetting1"/>
        </h2>
        <div class="user-policy">
            <p>
                <bean:message key="label.bypass.bypassSetting2"/>
            </p>
            <p>
                <bean:message key="label.bypass.bypassSetting3"/>
            </p>
            <p>
                <bean:message key="label.bypass.bypassSetting4"/>
            </p>
        </div>
        <p>
            <input type="radio" name="transactionProtection" id="rdUsePassword" value="Y" checked="checked" /><label
                for="rdUsePassword"><bean:message key="label.bypass.executPsw"/></label>
        </p>
        <p>
            <input type="radio" name="transactionProtection" id="rdBypassPassword" value="N" /><label for="rdBypassPassword"><bean:message key="label.bypass.noExecutPsw"/></label>
        </p>
        <p class="bypass-password-pop-password disabled">
            <bean:message key="label.setting.passProt.confirm"/>：<input type="password" id="tPassword" name="password" disabled="disabled" class="form-input" />
        </p>
        <p class="operation">
            <input type="button" class="yellow-btn" value='<bean:message key="button.common.comfirm"/>' onclick="modfiyTP()" />
            <input type="button" id="btnCancelBypassPassword" class="graw-short-btn" value='<bean:message key="button.common.back"/>' />
        </p>
    </form>
    </div>
    <div id="AllbuyinDialog" class="bypass-password-pop ui-pop-padding" title='<bean:message key="label.status.buyall"/>'>
		<p><bean:message key="label.status.buyallDeteil"/></p>
	</div>
    <div id="feesAndChargesDialog" class="fees-and-charges-pop ui-pop-padding" title='<bean:message key="label.trading.otherFee"/>'>
        <table class="ui-corner-all">
            <tr class="alternating">
                <th><bean:message key="label.fees.proj"/></th>
                <th><bean:message key="label.fees.fee"/></th>
                <th><bean:message key="label.fees.minFee"/></th>
                <th><bean:message key="label.fees.maxFee"/></th>
            </tr>
            <tr>
                <td><bean:message key="label.fees.tradeFee"/></td>
                <td>
                    0.003%
                </td>
                <td>
                    --
                </td>
                <td>
                    --
                </td>
            </tr>
            <tr class="alternating">
                <td><bean:message key="label.fees.hkEx"/></td>
                <td>
                    0.005%
                </td>
                <td>
                    --
                </td>
                <td>
                    --
                </td>
            </tr>
            <tr>
                <td><bean:message key="label.fees.CCASS"/></td>
                <td><bean:message key="label.fees.hkExTotal"/></td>
                <td>
                    HK$2
                </td>
                <td>
                    HK$100
                </td>
            </tr>
            <tr class="alternating">
                <td><bean:message key="label.fees.levyCharge"/></td>
                <td>
                    0.10%
                </td>
                <td>
                    HK$1
                </td>
                <td>
                    --
                </td>
            </tr>
        </table>
    </div>
    <div id="statusDetailDialog" class="fees-and-charges-pop ui-pop-padding" title='<bean:message key="label.quote.detail"/>'>
        <table class="ui-corner-all">
        <thead>
            <tr class="alternating">
                <th><bean:message key="label.trade_history.executedprice"/></th>
                <th><bean:message key="label.trade_history.executedquantity"/></th>
                <th><bean:message key="label.trading.broker"/></th>
            </tr>
        </thead>
        <tbody id="statusDetailGrid">
			<tr class="hide">
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </tbody>
		</table>
	</div>
	<div id="statusDetailDialog2" class="fees-and-charges-pop ui-pop-padding" title='<bean:message key="label.quote.detail"/>'>
        <table class="ui-corner-all">
        <thead>
            <tr class="alternating">
                <th><bean:message key="label.trade_history.executedprice"/></th>
                <th><bean:message key="label.trade_history.executedquantity"/></th>
            </tr>
        </thead>
        <tbody id="statusDetailGrid">
			<tr class="hide">
                <td></td>
                <td></td>
            </tr>
        </tbody>
		</table>
	</div>
</body>
</html>
