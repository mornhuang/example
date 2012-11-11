<%@ Page language="c#" Codebehind="WebForm1.aspx.cs" AutoEventWireup="false" Inherits="PCSWebApp3.WebForm1" %>
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
			<h1 align="center">Enter details and set a day to initiate an event.
			</h1>
			<br>
			<table borderColor="#000000" cellSpacing="0" cellPadding="8" rules="none" width="540" align="center" bgColor="#fff99e" border="2">
				<tr>
					<td vAlign="top">Your Name:</td>
					<td vAlign="top"><asp:textbox id="nameBox" Width="160px" Runat="server"></asp:textbox><asp:requiredfieldvalidator id="validateName" Runat="server" Display="None" ControlToValidate="nameBox" ErrorMessage="You must enter a name."></asp:requiredfieldvalidator></td>
					<td vAlign="center" rowSpan="4"><asp:calendar id="calendar" Runat="server" BackColor="White"></asp:calendar></td>
				</tr>
				<tr>
					<td vAlign="top">Event Name:</td>
					<td vAlign="top"><asp:textbox id="eventBox" Width="160px" Runat="server"></asp:textbox><asp:requiredfieldvalidator id="validateEvent" Runat="server" Display="None" ControlToValidate="eventBox" ErrorMessage="You must enter an event name."></asp:requiredfieldvalidator></td>
				</tr>
				<tr>
					<td vAlign="top">Meeting Room:</td>
					<td vAlign="top"><asp:dropdownlist id="roomList" Width="160px" Runat="server" DataValueField="ID" DataTextField="Room"></asp:dropdownlist><asp:requiredfieldvalidator id="validateRoom" Runat="server" Display="None" ControlToValidate="roomList" ErrorMessage="You must select a room."></asp:requiredfieldvalidator></td>
				</tr>
				<tr>
					<td vAlign="top">Attendees:</td>
					<td vAlign="top"><asp:listbox id="attendeeList" Width="160px" Runat="server" DataValueField="ID" DataTextField="Name" Rows="6" SelectionMode="Multiple"></asp:listbox><asp:requiredfieldvalidator id="validateAttendees" Runat="server" Display="None" ControlToValidate="attendeeList" ErrorMessage="You must have at least one attendee."></asp:requiredfieldvalidator></td>
				</tr>
				<tr>
					<td align="middle" colSpan="3"><asp:button id="submitButton" Width="100%" Runat="server" Text="Submit meeting room request"></asp:button></td>
				</tr>
				<tr>
					<td align="middle" colSpan="3"><asp:validationsummary id="validationSummary" Runat="server" HeaderText="Before submitting your request:"></asp:validationsummary></td>
				</tr>
				<tr>
					<td align="left" colspan="3" width="100%">
						<table cellspacing="4">
							<tr>
								<td width="40%" bgcolor="#ccffcc">
									<asp:DataList Runat="server" ID="eventDetails2" OnSelectedIndexChanged="eventDetails2_SelectedIndexChanged">
										<ItemTemplate>
											<asp:LinkButton Runat="server" CommandName="Select" ForeColor="#0000ff" ID="Linkbutton1" CausesValidation="False">
												<%# DataBinder.Eval(Container.DataItem, "Name") %>
											</asp:LinkButton>
											<br>
										</ItemTemplate>
										<SelectedItemTemplate>
											<b>
												<%# DataBinder.Eval(Container.DataItem, "Name") %>
											</b>
											<br>
										</SelectedItemTemplate>
									</asp:DataList>
								</td>
								<td valign="top">
									<asp:Label Runat="server" ID="edName" Font-Name="Arial" Font-Bold="True" Font-Italic="True" Font-Size="14">
								    Select an event to view details.
								</asp:Label>
									<br>
									<asp:Label Runat="server" ID="edDate" />
									<br>
									<asp:Label Runat="server" ID="edRoom" />
									<br>
									<asp:Label Runat="server" ID="edAttendees" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>
			Results:
			<asp:Label id="resultLabel" Runat="server" Text="None."></asp:Label>
			<br>
			<br>
			<asp:DataGrid Runat="server" ID="eventDetails1" />
		</form>
	</body>
</HTML>
