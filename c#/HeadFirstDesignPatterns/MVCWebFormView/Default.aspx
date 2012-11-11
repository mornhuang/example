<%@ Page language="c#" Codebehind="Default.aspx.cs" AutoEventWireup="false" Inherits="MVCWebFormView.Default" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>Default</title>
		<meta name="GENERATOR" Content="Microsoft Visual Studio .NET 7.1">
		<meta name="CODE_LANGUAGE" Content="C#">
		<meta name="vs_defaultClientScript" content="JavaScript">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5">
	</HEAD>
	<body MS_POSITIONING="FlowLayout">
		<form id="Form1" method="post" runat="server">
			<P>
				<asp:Button id="btnAddTodo" runat="server" Text="Add Todo"></asp:Button></P>
			<asp:datagrid id="dgTodoList" runat="server" AutoGenerateColumns="False" Width="100%" OnUpdateCommand="UpdateData"
				OnDeleteCommand="DeleteData" OnCancelCommand="CancelData" OnEditCommand="EditData" BorderColor="#999999"
				BorderStyle="None" BorderWidth="1px" BackColor="White" CellPadding="0" GridLines="Vertical">
				<FooterStyle ForeColor="Black" BackColor="#CCCCCC"></FooterStyle>
				<SelectedItemStyle Font-Bold="True" ForeColor="White" BackColor="#008A8C"></SelectedItemStyle>
				<AlternatingItemStyle Wrap="False" VerticalAlign="Top" BackColor="#DCDCDC"></AlternatingItemStyle>
				<ItemStyle Wrap="False" ForeColor="Black" VerticalAlign="Top" BackColor="#EEEEEE"></ItemStyle>
				<HeaderStyle Font-Bold="True" ForeColor="White" BackColor="#000084"></HeaderStyle>
				<Columns>
					<asp:TemplateColumn Visible="False"></asp:TemplateColumn>
					<asp:TemplateColumn HeaderText="Title">
						<HeaderTemplate>
							Todo Title
						</HeaderTemplate>
						<ItemTemplate>
							<asp:Label Text='<%# DataBinder.Eval(Container.DataItem, "Title" ) %>' runat="server" ID="lblTitle" Font-Bold=True/>
						</ItemTemplate>
						<EditItemTemplate>
							<asp:TextBox id="txtTitle" TextMode="SingleLine" Text='<%# DataBinder.Eval(Container.DataItem, "Title") %>' runat="server" />
						</EditItemTemplate>
					</asp:TemplateColumn>
					<asp:TemplateColumn HeaderText="Text">
						<HeaderTemplate>
							Todo Text
						</HeaderTemplate>
						<ItemTemplate>
							<asp:Label Text='<%# DataBinder.Eval(Container.DataItem, "Text") %>' runat="server" ID="lblText" />
						</ItemTemplate>
						<EditItemTemplate>
							<asp:TextBox id="txtText" TextMode="MultiLine" Text='<%# DataBinder.Eval(Container.DataItem, "Text") %>' runat="server" Width="100%" Rows="5"/>
						</EditItemTemplate>
					</asp:TemplateColumn>
					<asp:EditCommandColumn ButtonType="LinkButton" UpdateText="Update" CancelText="Cancel" EditText="Edit"></asp:EditCommandColumn>
					<asp:ButtonColumn Text="Delete" CommandName="Delete"></asp:ButtonColumn>
				</Columns>
				<PagerStyle HorizontalAlign="Center" ForeColor="Black" BackColor="#999999" Mode="NumericPages"></PagerStyle>
			</asp:datagrid>
		</form>
	</body>
</HTML>
