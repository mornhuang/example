<%@ Page language="c#" Codebehind="WebForm1.aspx.cs" AutoEventWireup="false" Inherits="PCSWebClient3.WebForm1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>WebForm1</title>
		<meta name="GENERATOR" Content="Microsoft Visual Studio 7.0">
		<meta name="CODE_LANGUAGE" Content="C#">
		<meta name="vs_defaultClientScript" content="JavaScript">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5">
	</HEAD>
	<body MS_POSITIONING="GridLayout">
		<form id="Form1" method="post" runat="server">
			User Name:
			<asp:TextBox Runat="server" ID="userNameBox" /><br>
			Password:
			<asp:TextBox Runat="server" ID="passwordBox" /><br>
			<asp:Button Runat="server" ID="loginButton" Text="Log in" /><br>
			<asp:Label Runat="server" ID="tokenLabel" /><br>
			<asp:Button Runat="server" ID="invokeButton" Text="Invoke DoSomething()" /><br>
			<asp:Label Runat="server" ID="resultLabel" /><br>
		</form>
	</body>
</HTML>
