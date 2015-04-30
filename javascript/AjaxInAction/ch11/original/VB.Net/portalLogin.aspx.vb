Public Class portalLogin
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

    Private Sub Page_Load(ByVal sender As System.Object, _
      ByVal e As System.EventArgs) Handles MyBase.Load
        'Put user code to initialize the page here
        Response.ContentType = "text/html"

        Dim strAction As String = Request.Form("action").ToString()

        If IsNothing(Session("UserId")) And strAction = "login" Then

            Dim strUser As String = Request.Form("user").ToString()
            Dim strPass As String = Request.Form("pass").ToString()

            Dim strSql As String = "SELECT id FROM users where username = '" & strUser & "' and password = '" & strPass & "'"

            Dim dtResults As DataTable = FillDataTable(strSql)

            If dtResults.Rows.Count = 1 Then

                Session("UserId") = dtResults.Rows(0).Item(0)
                BuildWindows()

            Else

                Dim strScript As System.Text.StringBuilder = New System.Text.StringBuilder("")

                strScript.Append("document.getElementById('spanProcessing').innerHTML = '<span style=""color:red"">The Username and Password is invalid. </span>';")
                strScript.Append("document.Form1.password.value = '';")
                strScript.Append("document.Form1.password.focus();")
                Response.Write(strScript)

            End If

        ElseIf Not IsNothing(Session("UserId")) And strAction = "PageLoad" Then

            BuildWindows()

        ElseIf Not IsNothing(Session("UserId")) And strAction = "settings" Then

            UserPanel()

        ElseIf Not IsNothing(Session("UserId")) And strAction = "AddWindow" Then

            AddRow()

        ElseIf Not IsNothing(Session("UserId")) And strAction = "UpdateRow" Then

            Dim strUpdate As String = "xPos = " & Request.Form("x") & ", "
            strUpdate += "yPos = " & Request.Form("y") & ", "
            strUpdate += "width = " & Request.Form("w") & ", "
            strUpdate += "height = " & Request.Form("h") & ", "
            strUpdate += "url = '" & Request.Form("u") & "', "
            strUpdate += "title = '" & Request.Form("t") & "'"

            UpdateRow(strUpdate, Request.Form("ref"))
            UserPanel()

        ElseIf Not IsNothing(Session("UserId")) And strAction = "UpdateDragWindow" Then

            Dim strUpdate As String = "xPos = " & Request.Form("x") & ", "
            strUpdate += "yPos = " & Request.Form("y") & ", "
            strUpdate += "width = " & Request.Form("w") & ", "
            strUpdate += "height = " & Request.Form("h")

            UpdateRow(strUpdate, Request.Form("ref"))

        ElseIf Not IsNothing(Session("UserId")) And strAction = "DeleteWindow" Then

            DeleteRow(Request.Form("ref").ToString())

        Else

            Response.Write("")

        End If

    End Sub

    Public Function FillDataTable(ByVal sqlQuery As String) As DataTable
        Dim strConn As String = "Initial Catalog = Northwind; Data Source=127.0.0.1; Integrated Security=false;Connect Timeout=30; User ID =sa"
        Dim cmd1 As New SqlClient.SqlDataAdapter(sqlQuery, strConn)
        Dim dataSet1 As New DataSet
        cmd1.Fill(dataSet1)
        cmd1.Dispose()
        Return dataSet1.Tables(0)
    End Function


    Public Sub BuildWindows()

        Dim strScript As System.Text.StringBuilder = New System.Text.StringBuilder("")
        strScript.Append("document.getElementById('login').innerHTML='Welcome back!<br/>" & _
        "<a href=""javascript:Settings(\'settings\',true)"">Settings/Add Windows</a><br/>';")

        strScript.Append("document.getElementById('defaultContent').style.display='none';")

        Dim strSql As String = "SELECT 'win' + CAST(id AS varchar) AS windowRef, " & _
        "xPos, yPos, width, height, URL, Title FROM portal_windows WHERE (user_id = '" & Session("UserId") & "')"

        Dim dtResults As DataTable = FillDataTable(strSql)
        Dim dRow As DataRow
        For Each dRow In dtResults.Rows
            strScript.Append("CreateWindow(new NewWin('" & dRow("windowRef") & "',")
            strScript.Append(dRow("xPos") & "," & dRow("yPos") & "," & dRow("width") & "," & dRow("height") & ",")
            strScript.Append("'" & dRow("url") & "','" & dRow("title") & "'));")
        Next
        Response.Write(strScript.ToString)

    End Sub

    Public Function UserPanel()

        Dim strSql As String = "SELECT id , xPos, yPos, width, height, URL, Title FROM portal_windows WHERE (user_id = '" & Session("UserId") & "')"

        Dim strScript As System.Text.StringBuilder = New System.Text.StringBuilder("<hr><h2>Window Settings</h2><hr/><table id='survey'>")
        strScript.Append("<tr><th>&nbsp;</th><th>X Pos</th><th>Y Pos</th><th>Width</th><th>Height</th><th>URL</th><th>Title</th></tr>")

        Dim dtResults As DataTable = FillDataTable(strSql)
        Dim dRow As DataRow

        For Each dRow In dtResults.Rows

            strScript.Append("<tr>")
            strScript.Append("<td><input type='button' name='b_" & dRow("id") & "' value='Save' onclick='Save(" & dRow("id") & ")'/></td>")
            strScript.Append("<td><input type='button' name='b_" & dRow("id") & "' value='Delete' onclick='Delete(" & dRow("id") & ")'/></td>")
            strScript.Append("<td><input type='text' name='tx_" & dRow("id") & "' value='" & dRow("xPos") & "'/></td>")
            strScript.Append("<td><input type='text' name='ty_" & dRow("id") & "' value='" & dRow("yPos") & "'/></td>")
            strScript.Append("<td><input type='text' name='tw_" & dRow("id") & "' value='" & dRow("width") & "'/></td>")
            strScript.Append("<td><input type='text' name='th_" & dRow("id") & "' value='" & dRow("height") & "'/></td>")
            strScript.Append("<td><input type='text' name='tu_" & dRow("id") & "' value='" & dRow("URL") & "'/></td>")
            strScript.Append("<td><input type='text' name='tt_" & dRow("id") & "' value='" & dRow("title") & "'/></td>")
            strScript.Append("</tr>")

        Next

        strScript.Append("</table>")

        strScript.Append("<button name='btnAdd' onclick=""Settings('AddWindow',1)"">Add Window</button>")
        strScript.Append("<button name='btnExit' onclick='ExitSettings()'>Exit Settings</button>")
        Response.Write(strScript)

    End Function

    Private Sub UpdateRow(ByVal strUpdate As String, ByVal id As String)

        Dim strSQL As String = "UPDATE portal_windows SET " & strUpdate & " WHERE id = " & id & " AND user_id = " & Session("UserId")
        ExeNonQuery(strSQL)
        Response.Write("Save Complete")

    End Sub

    Private Sub AddRow()

        Dim strSQL As String = "INSERT INTO portal_windows(xPos,yPos,width," & _
        "height,URL,title,user_id) VALUES(100,100,300,300,'about:blank','new','" & Session("UserId") & "')"
        ExeNonQuery(strSQL)
        UserPanel()


    End Sub

    Private Sub DeleteRow(ByVal strID As String)

        Dim strSQL As String = "DELETE FROM portal_windows where user_id = " & Session("UserId") & " AND id = " & strID
        ExeNonQuery(strSQL)
        UserPanel()


    End Sub



    Private Sub ExeNonQuery(ByVal strSQL As String)
        Dim strConn As String = "Initial Catalog = Northwind; Data Source=127.0.0.1; " & _
        "Integrated Security=false;Connect Timeout=30; User ID =sa"
        Dim cmdINSERT As New SqlClient.SqlCommand(strSQL)
        cmdINSERT.Connection = New SqlClient.SqlConnection(strConn)
        cmdINSERT.Connection.Open()
        cmdINSERT.ExecuteNonQuery()
        cmdINSERT.Connection.Close()

    End Sub



End Class
