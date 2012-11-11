<%@ Register TagPrefix="PCS" TagName="UserC1" Src="PCSUserC1.ascx" %>
<%@ Page language="c#" Codebehind="WebForm1.aspx.cs" AutoEventWireup="false" Inherits="PCSUserCWebApp1.WebForm1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>WebForm1</title>
		<meta content="Microsoft Visual Studio 7.0" name="GENERATOR">
		<meta content="C#" name="CODE_LANGUAGE">
		<meta content="JavaScript" name="vs_defaultClientScript">
		<meta content="http://schemas.microsoft.com/intellisense/ie5" name="vs_targetSchema">
	</HEAD>
	<body MS_POSITIONING="GridLayout">
		<form id="Form1" method="post" runat="server">
			<PCS:USERC1 id="myUserControl" Runat="server"></PCS:USERC1>
			<asp:RadioButtonList Runat="server" ID="suitList" AutoPostBack="True">
				<asp:ListItem Value="club" Selected="True">Club</asp:ListItem>
				<asp:ListItem Value="diamond">Diamond</asp:ListItem>
				<asp:ListItem Value="heart">Heart</asp:ListItem>
				<asp:ListItem Value="spade">Spade</asp:ListItem>
			</asp:RadioButtonList>
			<asp:ImageButton Runat="server" ID="clubButton" ImageUrl="CLUB.BMP" OnClick="clubButton_Click" />
			<asp:ImageButton Runat="server" ID="diamondButton" ImageUrl="DIAMOND.BMP" OnClick="diamondButton_Click" />
			<asp:ImageButton Runat="server" ID="heartButton" ImageUrl="HEART.BMP" OnClick="heartButton_Click" />
			<asp:ImageButton Runat="server" ID="spadeButton" ImageUrl="SPADE.BMP" OnClick="spadeButton_Click" /></form>
	</body>
</HTML>
