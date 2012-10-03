<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/zh-HK/style.css" />
    <link rel="stylesheet" type="text/css" href="../Style/blue/jquery-ui-1.8.11.custom.css"/>	
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../Script/shieldingMouse.js"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/calendar/jquery.ui.datepicker-${sessionScope['org.apache.struts.action.LOCALE']}.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
    <script type="text/javascript" src="../Script/until.js"></script>

</head>

<body>
<script type="text/javascript">
	$(function(){
		var dates = $('#depositDate').datepicker({
			changeMonth: true,
			changeYear: true,
			beforeShow:function(input, inst){
				dates.datepicker( "option", "minDate",'19990101');
				dates.datepicker( "option", "maxDate", new Date());
				dates.datepicker("option", "showAnim", "slideDown");

			}
		});
	});

	function InputAmountN(el,ev){
		//reg=/\d+\.?\d{0,2}/;
		reg=/^[1-9]\d*$/;
		var v = el.value.match(reg);
		el.value = v ? v : "";
	}
	function checkDeposit(){
		//reg=/\d+\.?\d{0,2}/;
		reg=/^[1-9]\d*$/;
		
		var d = new Date();
		var s = "";
		s += d.getFullYear();
		var month = d.getMonth() + 1;
		if (month < 10) {
			month = "0" + month;
		}
		s += month;
		var day = d.getDate();
		if (day < 10) {
			day = "0" + day;
		}
		s+=day;		
		var amountN=$("#amountN").val();
		var vreg=amountN.match(reg);
		
		if(!reg.test(amountN)||vreg==null){
			alert('<bean:message key="money"/>');
			$("#amountN").val("");
			return;	
		}
	
		if($("#depositDate").val()==""){
			alert('<bean:message key="depositDateNull"/>');
			return;
		}else if($("#depositDate").val()>s||$("#depositDate").val()<19990101){
			alert('<bean:message key="depositDateError"/>');
			return;
		}
		if($("#depositsTimeH").val()==""){
			alert('<bean:message key="depositsTime"/>');
			return;
		}
		if($("#depositsTimeM").val()==""){
			alert('<bean:message key="depositsTime"/>');
			return;
		}
		if($("#acclist").val()==""){
			alert('<bean:message key="acclist"/>');
			return;	
		}
		if($("#depositMeansB").val()==""){
			alert('<bean:message key="depositchannel"/>');
			return;	
		}
		if($("#depositMeansB").val()==""){
			alert('<bean:message key="depositchannel"/>');
			return;		
		}
		if($("#bankNumber").val()==""){
			alert('<bean:message key="depositbankNo"/>');
			return;		
		}
		if($("#amount").val()==""){
			alert('<bean:message key="moneyType"/>');
			return;	
		}
		if($("#amountN").val()==""||vreg==null){
			alert('<bean:message key="money"/>');
			$("#amountN").val("");
			return;	
		}
		if($("#receiptNo").val()==""){
			alert('<bean:message key="inputReceipt"/>');
			return;	
		}
		$("#Deposit").submit();
	}

	function selDeposit(){
		var sel;
		var text;
		sel=$("#depositMeansB").val();
		if(sel!="IPPS"){
			text=
				"</br><option value='ATM'><bean:message key='depositMeansA1'/></option></br>"+
            	"<option value='Online Banking'><bean:message key='depositMeansA2'/></option></br>"+
            	"<option value='Phone Banking'><bean:message key='depositMeansA3'/></option></br>"+
          		"<option value='Counter'><bean:message key='depositMeansA4'/></option></br>"+
           	 	"<option value='Cheque'><bean:message key='depositMeansA5'/></option>"
		}else{
			text=
				"<option value='Online PPS'><bean:message key='depositMeansA6'/></option>"+
                "<option value='Phone PPS'><bean:message key='depositMeansA7'/></option>"
			}
		$("#depositMeansA").html(text); 
		$.jNice.SelectUpdate($("#depositMeansA"));
	}
</script>


<form action="../CheckFundDeposit.do?CLV=${sessionScope.CLV}" id="Deposit" name="Deposit" method="post">
 <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="ConfirmationDeposit"/></span><b></b></span> <span class="position"><bean:message key="AccountServices"/> >
          <bean:message key="ConfirmationDeposit"/></span>
    </h1>
    <from>
    <div class="page-content">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    <bean:message key="ConfirmationDeposit"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
               <bean:message key="depositDate"/>
                </th>
                <td>
                    <input type="text" id="depositDate" name="depositDate" class="form-input"/>（YYYYMMDD）
                </td>
            </tr>
            <tr class="alternating">
                <th>
                <bean:message key="depositsTime"/>
                </th>
                <td>
                    <select class="jquery-select" id="depositsTimeH" name="depositsTimeH">
						<option value="00">00</option>
						<option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                        <option value="05">05</option>
                        <option value="06">06</option>
                        <option value="07">07</option>
                        <option value="08">08</option>
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>

                    </select>
                    <span class="float-left">：</span>
                    <select class="jquery-select" id="depositsTimeM" name="depositsTimeM">
                        <option value="00">00</option>
						<option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                        <option value="05">05</option>
                        <option value="06">06</option>
                        <option value="07">07</option>
                        <option value="08">08</option>
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                        <option value="32">32</option>
                        <option value="33">33</option>
                        <option value="34">34</option>
                        <option value="35">35</option>
                        <option value="36">36</option>
                        <option value="37">37</option>
                        <option value="38">38</option>
                        <option value="39">39</option>
                        <option value="40">40</option>
                        <option value="41">41</option>
                        <option value="42">42</option>
                        <option value="43">43</option>
                        <option value="44">44</option>
                        <option value="45">45</option>
                        <option value="46">46</option>
                        <option value="47">47</option>
                        <option value="48">48</option>
                        <option value="49">49</option>
                        <option value="50">50</option>
                        <option value="51">51</option>
                        <option value="52">52</option>
                        <option value="53">53</option>
                        <option value="54">54</option>
                        <option value="55">55</option>
                        <option value="56">56</option>
                        <option value="57">57</option>
                        <option value="58">58</option>
                        <option value="59">59</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="accountNumber"/>
                </th>
                <td>
               <select class="jquery-select" id="acclist" name="accountNumber">
                  <c:forEach var="account" varStatus="s" items="${User.tradeInfoObject.misAccountList.accountListDetail}">
                       <option value="${account.accountId}">${account.accountId}</option>
                  </c:forEach>
                </select>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                 <bean:message key="depositMeans"/>
                </th>
                <td>
                    <select class="jquery-select" id="depositMeansB" name="depositMeansB" onchange="selDeposit()">
                        <option value="HSBCNET"><bean:message key="depositMeansB1"/></option>
                        <option value="HENGSANGNET"><bean:message key="depositMeansB2"/></option>
                        <option value="BOC"><bean:message key="depositMeansB3"/></option>
                        <option value="SCB"><bean:message key="depositMeansB4"/></option>
                        <option value="IPPS"><bean:message key="depositMeansB5"/></option>
                    </select>
                    <select class="jquery-select" id="depositMeansA" name="depositMeansA">
                        <option value="ATM"><bean:message key="depositMeansA1"/></option>
            			<option value="Online Banking"><bean:message key="depositMeansA2"/></option>
            			<option value="Phone Banking"><bean:message key="depositMeansA3"/></option>
          				<option value="Counter"><bean:message key="depositMeansA4"/></option>
           	 			<option value="Cheque"><bean:message key="depositMeansA5"/></option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                 <bean:message key="bankNumberc"/>
                </th>
                <td>
                    <select class="jquery-select form-select" id="bankNumber" name="bankNumber">
                        <option value="500-113089-001">500-113089-001</option>
                        <option value="502-040850-001">502-040850-001</option>
                        <option value="262-065899-001">262-065899-001</option>
                        <option value="262-152200-001">262-152200-001</option>
                        <option value="012-875-00245564">012-875-00245564</option>
                        <option value="012-875-00327716">012-875-00327716</option>
                        <option value="44700027575">44700027575</option>
                        <option value="44706677997">44706677997</option>
                    </select>
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="amount"/>
                </th>
                <td>
                    <select class="jquery-select" id="amount" name="amount">
                        <option value="HKD">HKD</option>
                        <option value="RMB">RMB</option>
                        <option value="USD">USD</option>
                    </select>
                    <input type="text" id="amountN" name="amountN"  class="form-input" value="" onKeyUp="InputAmountN(this);" onkeypress="return priceKeyPress(event)" />
                </td>
            </tr>
            <tr>
                <th>
                    <bean:message key="receiptNo"/>
                </th>
                <td>
                    <input type="text" id="receiptNo" name="receiptNo" class="form-input" />（<bean:message key="showReceipt"/>）
                </td>
            </tr>
            <tr class="alternating">
                <th>
                    <bean:message key="depositDeadline"/>
                </th>
                <td>
                    1：00 PM
                </td>
            </tr>
            <tr>
                <th>
                </th>
                <td>
                    <input type="button" class="form-button" value="<bean:message key="button.common.submit"/>" onclick="checkDeposit()" />
                </td>
            </tr>
        </table>
	 </div>
	</form>
	
</body>
</html>