Option Explicit 

Dim objFactorial
Dim lngResult 
Dim lngInputValue 

Set objFactorial=CreateObject("CSharpFactorial.Factorial")
lngInputValue=InputBox("Number?")
lngResult=objFactorial.ComputeFactorial(CLng(lngInputValue))
Call MsgBox(lngResult)
