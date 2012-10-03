<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String folder = request.getParameter("fold");
	String language = request.getParameter("language");
	String style = request.getParameter("style");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+path;        
	//String trading_action= basePath + "/appletBringPriceToOrder.do";
	String url = (String)(request.getSession().getAttribute("aastock_forward_url"));
	
	String forward_url = url + "?fold=" + folder + "&language=" + language + "&style=" + style;
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<script language="JavaScript">
	function placeOrder(symbol, buysell, price, instr){
		var key_color = "1";
		if (buysell !=null && buysell=="B") {
			key_color = "1";
		} else {
			key_color = "0";
		}
		//parent.order.location = "<%//=trading_action%>" + "?stock_code=" + symbol + "&unit_price=" + price + "&lot_size=" + "&key_color=" + key_color;
		parent.showOrderTicketForm();
		parent.resetPlaceOrder({instrCode: symbol, orderQuantity: parent.enquiryLotSize(symbol), orderPrice: price});
	}
</script>


<script type="text/javascript" src="<%=forward_url%>"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Order Ticket</title>
</head>

<body  topmargin="0" leftmargin="0" marginwidth="0" marginheight="0" onclick="">
<script type="text/javascript">
open_applet();
</script>
</body>
</html>
