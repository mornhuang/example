using System;

namespace Wrox.ProCSharp.Assemblies.AppDomains
{
   class Class1
   {
      [STAThread]
      static void Main(string[] args)
      {
         AppDomain currentDomain = AppDomain.CurrentDomain;
         Console.WriteLine(currentDomain.FriendlyName);
         AppDomain secondDomain = AppDomain.CreateDomain("New AppDomain");
         secondDomain.ExecuteAssembly("AssemblyA.exe");
      }
   }
}

