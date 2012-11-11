using System;
using System.Text;
using System.Windows.Forms;
using System.Reflection;

namespace Wrox.ProCSharp.TypeView
{
   class MainClass
   {
      static void Main()
      {
         // modify this line to retrieve details of any
         // other data type
         Type t = typeof(double);
   
         AnalyzeType(t);
         MessageBox.Show(OutputText.ToString(), "Analysis of type " + t.Name);
         Console.ReadLine();
      }

      static void AnalyzeType(Type t)
      {
         AddToOutput("Type Name:  " + t.Name);
         AddToOutput("Full Name:  " + t.FullName);
         AddToOutput("Namespace:  " + t.Namespace);
         Type tBase = t.BaseType;
         if (tBase != null)
            AddToOutput("Base Type:" + tBase.Name);
         Type tUnderlyingSystem = t.UnderlyingSystemType;
         if (tUnderlyingSystem != null)
            AddToOutput("UnderlyingSystem Type:" + tUnderlyingSystem.Name);

         AddToOutput("\nPUBLIC MEMBERS:");
         MemberInfo [] Members = t.GetMembers();
         foreach (MemberInfo NextMember in Members)
         {
            AddToOutput(NextMember.DeclaringType + " " + NextMember.MemberType +
               " " + NextMember.Name);
         }
      }

      static void AddToOutput(string Text)
      {
         OutputText.Append("\n" + Text);
      }

      static StringBuilder OutputText = new StringBuilder(500);
   }
}