<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%
	String Language = request.getParameter("Language");
	String ErrMsg = request.getParameter("ErrMsg");
	if (Language==null) Language = "C" ;

	String lblEnterAccount,lblInvalidAccount,lblEnterPassword,lblInvalidPassword,buttonClose,buttonOK,lblCompanyName;

	if (Language.equals("C")) {
		lblEnterAccount = "請輸入登入名稱";
	    lblInvalidAccount = "登入名稱不正確";
	    lblEnterPassword = "請輸入密碼";
	    lblInvalidPassword = "密碼不正確，請重新輸入";
		buttonClose = "&#38364;&#38281;";
		buttonOK = "&#30906;&#23450;";
		lblCompanyName = "海通國際證劵有限公司";

	}
	else if (Language.equals("GB")) {
		lblEnterAccount = "请输入登入名称";
	    lblInvalidAccount = "登入名称不正确";
	    lblEnterPassword = "请输入密码";
	    lblInvalidPassword = "密码不正确，请重新输入";
		buttonClose = "&#20851;&#38381;";
		buttonOK = "&#30830;&#23450;";
		lblCompanyName = "海通国际证劵有限公司";
	}
	else {
		lblEnterAccount = "Please Enter Login ID";
		lblInvalidAccount = "Invalid Login ID";
		lblEnterPassword = "Please Enter Password";
		lblInvalidPassword = "Invalid password! Please input again.";
		buttonClose = "Close";
		buttonOK = "OK";
		lblCompanyName = "Haitong International Securities Company Limited";
	}

	String dispMsg = "" ;
	if ("EnterAccount".equals(ErrMsg)){
		dispMsg = lblEnterAccount;
	}
	else if ("InvalidAccount".equals(ErrMsg)){
		dispMsg = lblInvalidAccount;
	}
	else if ("EnterPasswd".equals(ErrMsg)){
		dispMsg = lblEnterPassword;
	}
	else if ("InvalidPassword".equals(ErrMsg)){
		dispMsg = lblInvalidPassword;
	}



%>
<html>
<head>
<title><%=lblCompanyName%></title>
<script language="javascript">

</script>
</head>
<body bgcolor="scrollbar">

<p>&nbsp;
<p align="center"><%=dispMsg%>
<p align="center"><input type=button name="button1" value="<%=buttonOK%>" onclick="javascript:window.close();" style="width:60px">

</body>
</html>
