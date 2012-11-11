using System;

namespace Wrox.ProCSharp.Basics
{
   class Client
   {
      public static int Main()
      {
         MathExample.Main();
         return 0;
      }
   }

   class MathExample
   {
      static int Add(int x, int y)
      {
         return x + y;
      }

      public static int Main()
      {
         int i = Add(5,10);
         Console.WriteLine(i);
         return 0;
      }
   }
}