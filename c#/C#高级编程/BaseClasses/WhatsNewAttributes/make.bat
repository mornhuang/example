csc /target:library WhatsNewAttributes.cs
csc /target:library /reference:WhatsNewAttributes.dll VectorClass.cs
csc /reference:WhatsNewAttributes.dll /reference:VectorClass.dll LookUpWhatsNew.cs
LookUpWhatsNew