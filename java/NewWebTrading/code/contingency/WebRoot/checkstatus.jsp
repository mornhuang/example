<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@ page import="com.itsz.Contingency.ClientMain"%>
<%

String status =ClientMain.secStatus;

%>
<html>
<head>
<title>
process
</title>
<script language="javascript"><!--
  var status = '<%=status%>';
  //Display or undisplay 
  if (status=='A'){
	  if (typeof(this.parent.document.getElementById("avilalert"))!="undefined") {
		this.parent.document.getElementById("avilalert").style.display = "" ;
	  }
	  if (typeof(this.parent.document.getElementById("inavilalert"))!="undefined") {
		  this.parent.document.getElementById("inavilalert").style.display = "none" ;
	  }
	  if(typeof(this.parent.document.getElementById("etnetQs2"))!="undefined"){
		  this.parent.document.getElementById("etnetQs2").style.display= "none" ;
	  }
	  if(typeof(this.parent.document.getElementById("delay_quote"))!="undefined"){
		this.parent.document.getElementById("delay_quote").style.display= "none" ;
	  }
	  if(typeof(this.parent.document.getElementById("contact_info"))!="undefined"){
		  this.parent.document.getElementById("contact_info").style.display= "none" ;
	  }	  
  }
  else{
	  if (typeof(this.parent.document.getElementById("avilalert"))!="undefined") {
		  this.parent.document.getElementById("avilalert").style.display = "none" ;
	  }
	  if (typeof(this.parent.document.getElementById("inavilalert"))!="undefined") {
		  this.parent.document.getElementById("inavilalert").style.display = "" ;
	  }
	  if(typeof(this.parent.document.getElementById("etnetQs2"))!="undefined"){
		  this.parent.document.getElementById("etnetQs2").style.display= "" ;
	  }
	  if(typeof(this.parent.document.getElementById("delay_quote"))!="undefined"){
		  this.parent.document.getElementById("delay_quote").style.display= "" ;
	  }
	  if(typeof(this.parent.document.getElementById("contact_info"))!="undefined"){
		  this.parent.document.getElementById("contact_info").style.display= "" ;
	  }
  }


	//--
	<%
	boolean iscon=true;
	Object value=session.getAttribute("iscontinueRTQ");
	if(value!=null && value instanceof Boolean){
		iscon=((Boolean)value).booleanValue();
	}
	%>
	var ParentFORM1 = this.parent.document.form1 ; 
	<%if(iscon){%>	   
	  if (typeof(ParentFORM1)=="object" && status=='I' && status!=ParentFORM1.status.value) {
		var QuoteURL ;
		if (ParentFORM1.RTQProdName.value=="aastock"){
			var sDialogHW ="width=810px,height=574px,resizable=yes,status=no";
			var sDialogURL="./showAAStocksRTQ.jsp";
			var sDialogName="AAStockQuoteWindow";
			window.open(sDialogURL,sDialogName,sDialogHW);

		}
		else if (ParentFORM1.RTQChnlName.value=="qpifull"){
				var sDialogHW = "width=800,height=600,resizable=yes,status=no,menubar=no";
				var sDialogURL="./rtq_qpifull.jsp";
				var sDialogName="qpifull";
				window.open(sDialogURL,sDialogName,sDialogHW);
		}
		else if(ParentFORM1.RTQChnlName.value=="Finet"){
				var sDialogHW = "width=800,height=600,resizable=yes,status=no,menubar=no";
				var sDialogURL=ParentFORM1.QuoteURL.value;
				var sDialogName="Finet";
				window.open(sDialogURL,sDialogName,sDialogHW);		
		}
		else if(ParentFORM1.RTQProdName.value=="ETNet"){
			window.location.href = "SelectQuotation.do";
		}
  }
  <%}%>
  ParentFORM1.status.value = status ;
--></script>
</head>
<body bgcolor="#ffffff">
<h3>

</h3>
<a href="manage.jsp">BACK</a>
</body>
</html>