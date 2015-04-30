Public Class Contact
    Inherits System.Web.UI.Page

#Region " Web Form Designer Generated Code "

    'This call is required by the Web Form Designer.
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()

    End Sub

    'NOTE: The following placeholder declaration is required by the Web Form Designer.
    'Do not delete or move it.
    Private designerPlaceholderDeclaration As System.Object

    Private Sub Page_Init(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Init
        'CODEGEN: This method call is required by the Web Form Designer
        'Do not modify it using the code editor.
        InitializeComponent()
    End Sub

#End Region

    Private Sub Page_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'Put user code to initialize the page here
        SetXML()
    End Sub

    Private Sub SetXML()

        'Set the Page Content Type
        Response.ContentType = "text/xml"

        'Grab Customer Information
        Dim strQuery As String = Request.QueryString.Get("q")

        Dim strSql As String = "Select CompanyName, ContactName, Country, Phone FROM Customers WHERE CompanyName like '" & strQuery & "%' ORDER BY CompanyName"
        Dim dtQuestions As DataTable = FillDataTable(strSql)

        'set xml file format
        Dim strXML As System.Text.StringBuilder = New System.Text.StringBuilder("<?xml version=""1.0"" ?>")

        strXML.Append("<phonebook>")

        'Determine if any results were returned
        If dtQuestions.Rows.Count > 0 Then

            Dim row As DataRow
            For Each row In dtQuestions.Rows
                strXML.Append("<entry>")
                strXML.Append("<company>" & row("CompanyName") & "</company>")
                strXML.Append("<contact>" & row("ContactName") & "</contact>")
                strXML.Append("<country>" & row("Country") & "</country>")
                strXML.Append("<phone>" & row("Phone") & "</phone>")
                strXML.Append("</entry>")
            Next

        Else

            'Show that no results were returned
            strXML.Append("<entry>")
            strXML.Append("<company>No Results</company>")
            strXML.Append("<contact>N/A</contact>")
            strXML.Append("<country>N/A</country>")
            strXML.Append("<phone>N/A</phone>")
            strXML.Append("</entry>")

        End If

        strXML.Append("</phonebook>")

        'Write XML to the page!
        Response.Write(strXML)
    End Sub


    Public Function FillDataTable(ByVal sqlQuery As String) As DataTable
        Dim strConn As String = "Initial Catalog = Northwind; Data Source=127.0.0.1; Integrated Security=true;"
        Dim cmd1 As New SqlClient.SqlDataAdapter(sqlQuery, strConn)
        Dim dataSet1 As New DataSet
        cmd1.Fill(dataSet1)
        cmd1.Dispose()
        Return dataSet1.Tables(0)

    End Function


End Class
