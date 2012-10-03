<%@ Page language="c#" Codebehind="WebForm1.aspx.cs" AutoEventWireup="false" Inherits="PCSWebApp4.WebForm1" %>
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
			<h1 align="center">
				Enter details and set a day to initiate an event.
			</h1>
			<br>
			<table borderColor="#000000" cellSpacing="0" cellPadding="8" rules="none" align="center" bgColor="#fff99e" border="2" width="540">
				<tr>
					<td vAlign="top">Your Name:</td>
					<td vAlign="top">
						<asp:textbox id="nameBox" runat="server" width="160px" />
						<asp:requiredfieldvalidator id="validateName" Runat="server" errormessage="You must enter a name." ControlToValidate="nameBox" display="None" />
					</td>
					<td vAlign="center" rowSpan="4">
						<asp:calendar id="calendar" runat="server" BackColor="White" />
					</td>
				</tr>
				<tr>
					<td vAlign="top">Event Name:</td>
					<td vAlign="top">
						<asp:textbox id="eventBox" runat="server" width="160px" />
						<asp:requiredfieldvalidator id="validateEvent" Runat="server" errormessage="You must enter an event name." ControlToValidate="eventBox" display="None" />
					</td>
				</tr>
				<tr>
					<td vAlign="top">Meeting Room:</td>
					<td vAlign="top">
						<asp:dropdownlist id="roomList" runat="server" width="160px" datatextfield="Room" datavaluefield="ID" />
						<asp:requiredfieldvalidator id="validateRoom" Runat="server" errormessage="You must select a room." ControlToValidate="roomList" display="None" />
					</td>
				</tr>
				<tr>
					<td vAlign="top">Attendees:</td>
					<td vAlign="top">
						<asp:listbox id="attendeeList" runat="server" width="160px" selectionmode="Multiple" rows="6" datatextfield="Name" datavaluefield="ID" />
						<asp:requiredfieldvalidator id="validateAttendees" Runat="server" errormessage="You must have at least one attendee." ControlToValidate="attendeeList" display="None" />
					</td>
				</tr>
				<tr>
					<td align="middle" colSpan="3">
						<asp:button id="submitButton" runat="server" width="100%" Text="Submit meeting room request" />
					</td>
				</tr>
				<tr>
					<td align="middle" colSpan="3">
						<asp:validationsummary id="validationSummary" Runat="server" headertext="Before submitting your request:" />
					</td>
				</tr>
				<tr>
					<td align="left" colSpan="3" width="100%">
						<table cellspacing="4">
							<tr>
								<td width="40%" bgcolor="#ccffcc">
									<asp:DataList Runat="server" ID="eventDetails2" OnSelectedIndexChanged="eventDetails2_SelectedIndexChanged">
										<SelectedItemTemplate>
											<b>
												<%# DataBinder.Eval(Container.DataItem,
                                                        "Name") %>
											</b>
											<br>
										</SelectedItemTemplate>
										<ItemTemplate>
											<asp:LinkButton Runat="server" CommandName="Select" forecolor="#0000ff" ID="Linkbutton1" CausesValidation="false">
												<%# DataBinder.Eval(Container.DataItem,
                                                        "Name")%>
											</asp:LinkButton>
											<br>
										</ItemTemplate>
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
			<asp:Label Runat="server" ID="resultLabel" Text="None." />
			<br>
			<br>
			<asp:DataGrid Runat="server" ID="eventDetails1" />
		</form>
	</body>
</HTML>
