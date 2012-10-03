xsd product.xsd /d /nologo
csc /t:exe /debug+ /r:System.dll /r:System.Data.dll product.cs XSDDataSet.cs ..\login.cs