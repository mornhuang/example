VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
  Persistable = 0  'NotPersistable
  DataBindingBehavior = 0  'vbNone
  DataSourceBehavior  = 0  'vbNone
  MTSTransactionMode  = 0  'NotAnMTSObject
END
Attribute VB_Name = "Query"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = True
Attribute VB_PredeclaredId = False
Attribute VB_Exposed = True
Public Function Select_(ByVal Price As Integer) As ADODB.Recordset
   Dim ConnectionString As String
   ConnectionString = "Provider=SQLOLEDB.1;uid=sa;password=;" & _
                      "database=Northwind;Data Source=(local)"
   Dim strQuery As String
   strQuery = "SELECT * FROM Products WHERE UnitPrice > " & Price
   Set Select_ = ExecuteQuery_(ConnectionString, strQuery)
End Function

Public Function ExecuteQuery_(ByVal strConnection As String, _
                              ByVal SQLQuery As String) As ADODB.Recordset
   Dim objConnection As ADODB.Connection
   Dim objRecordset As ADODB.Recordset
   Set objConnection = New ADODB.Connection
   Call objConnection.Open(strConnection)
   Set objRecordset = New ADODB.Recordset
   objRecordset.CursorLocation = adUseClient
   objRecordset.Open SQLQuery, objConnection, adOpenStatic, _
                     adLockBatchOptimistic
   Set objRecordset.ActiveConnection = Nothing
   objConnection.Close
   Set ExecuteQuery_ = objRecordset
End Function


