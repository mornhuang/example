Imports System.Text

Public Class DoubleComboXML
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

    Private Sub Page_Load( _
                ByVal sender As System.Object, _
                ByVal e As System.EventArgs) _
                Handles MyBase.Load

        Response.ContentType = "text/xml"

        Dim strQuery As String
        strQuery = Request.Form("q")
        Dim strForm As String
        strForm = Request.Form("f")
        Dim strElem As String
        strElem = Request.Form("e")

        Dim strSql As String = "SELECT " & _
                      "TerritoryDescription, " & _
                      "TerritoryID" & _
                      " FROM Territories" & _
                      " WHERE regionid = " & _
                      strQuery & " ORDER BY " & _
                      "TerritoryDescription"

        Dim dtOptions As DataTable
        dtOptions = FillDataTable(strSql)

        Dim strXML As StringBuilder
        strXML = New StringBuilder("<?xml " & _
                             "version=""1.0"" ?>")
        strXML.Append("<selectChoice>")
        strXML.Append("<selectElement>")
        strXML.Append("<formName>" & _
                      strForm & _
                      "</formName>")
        strXML.Append("<formElem>" & _
                      strElem & _
                      "</formElem>")
        strXML.Append("</selectElement>")

        If dtOptions.Rows.Count > 0 Then

            strXML.Append("<entry>")
            strXML.Append("<optionText>" & _
                          "Select A Territory" & _
                          "</optionText>")
            strXML.Append("<optionValue>-1" & _
                          "</optionValue>")
            strXML.Append("</entry>")

            Dim row As DataRow
            For Each row In dtOptions.Rows
                strXML.Append("<entry>")
                strXML.Append("<optionText>" & _
                          row("TerritoryDescription") & _
                          "</optionText>")
                strXML.Append("<optionValue>" & _
                              row("TerritoryID") & _
                              "</optionValue>")
                strXML.Append("</entry>")
            Next

        End If

        strXML.Append("</selectChoice>")
        Response.Write(strXML.ToString)

    End Sub

    Public Function FillDataTable( _
                     ByVal sqlQuery As String) _
                     As DataTable
        Dim strConn As String = _
               "Initial Catalog = Northwind; " & _
               "Data Source=127.0.0.1; " & _
               "Integrated Security=true;"
        Dim cmd1 As _
        New SqlClient.SqlDataAdapter(sqlQuery, _
                                     strConn)
        Dim dataSet1 As New DataSet
        cmd1.Fill(dataSet1)
        cmd1.Dispose()
        Return dataSet1.Tables(0)
    End Function


End Class
