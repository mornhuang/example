using System;

namespace Wrox.ProCSharp.Basics
{
   class ScopeTest2
   {
      static int j = 20;

      public static void Main()
      {
         int j = 30;
         Console.WriteLine(j);
         return;
      }
   }
}