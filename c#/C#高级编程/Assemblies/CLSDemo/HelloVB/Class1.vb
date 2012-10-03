Public Class HelloVB
    Inherits HelloMCPP

    Public Overrides Sub Hello()
        MyBase.Hello()
        System.Console.WriteLine("Hello, VB.NET")
    End Sub

    Public Shadows Function Add(ByVal val1 As Integer, _
                                ByVal val2 As Integer) As Integer
        Return val1 + val2
    End Function

End Class
