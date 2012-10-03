<%@ Page language="c#" Codebehind="WebForm1.aspx.cs" AutoEventWireup="false" Inherits="PCSCustomWebControlsTestApp.WebForm1" %>
<%@ Register TagPrefix="PCS" Namespace="PCSCustomWebControls" Assembly="PCSCustomWebControls" %>
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
			<!-- <PCS:WebCustomControl1 ID="testControl" Runat="server" Text="Testing again..." /> -->
			<!-- <PCS:RainbowLabel Runat="server" Text="Multicolored label!" ID="rainbowLabel1" />
			<asp:Button Runat="server" ID="cycleButton" Text="Cycle colors" /> -->
			<!-- <PCS:RainbowLabel2 Runat="server" Text="Multicolored label composite" ID="rainbowlabel2" /> --><PCS:STRAWPOLL id="strawPoll1" title="Who is your favorite James Bond?" Runat="server" PollStyle="voteonly" Voted="strawPoll1_Voted">
				<PCS:Candidate Name="Sean Connery" Votes="101" />
				<PCS:Candidate Name="Roger Moore" Votes="83" />
				<PCS:Candidate Name="George Lazenby" Votes="32" />
				<PCS:Candidate Name="Timothy Dalton" Votes="28" />
				<PCS:Candidate Name="Pierce Brosnan" Votes="95" />
			</PCS:STRAWPOLL>
			<br>
			<br>
			<asp:Label Runat="server" ID="resultLabel" Text="No vote cast." /></form>
	</body>
</HTML>
