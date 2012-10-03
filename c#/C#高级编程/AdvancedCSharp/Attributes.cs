#define DEBUG   // comment out this line if doing a release build

using System;
using System.Runtime.InteropServices;
using System.Diagnostics;

namespace Wrox.ProCSharp.AdvancedCSharp
{

   class MainEntryPoint
   {
      [DllImport("User32.dll")]
      public static extern int MessageBox(int hParent, string Message, 
         string Caption, int Type);
 
      static void Main()
      {
         DisplayRunningMessage();
         DisplayDebugMessage();
         MessageBox(0, "Hello", "Message", 0);
      }

      [Conditional("DEBUG")]
      private static void DisplayRunningMessage()
      {
         Console.WriteLine("Starting Main routine. Current time is " + DateTime.Now);
      }

      [Conditional("DEBUG")]
      [Obsolete()]
      private static void DisplayDebugMessage()
      {
         Console.WriteLine("Starting Main routine");
      }
   }
}