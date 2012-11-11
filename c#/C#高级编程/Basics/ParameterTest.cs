using System;

namespace Wrox.ProCSharp.Basics
{
   class ParameterTest
   {
      static void SomeFunction(int[] ints, ref int i)
      {
         ints[0] = 100;
         i = 100;
      }
   
      public static int Main()
      {
         int i = 0;
         int[] ints = { 0, 1, 2, 4, 8 };

         // Display the original values
         Console.WriteLine("i = " + i);
         Console.WriteLine("ints[0] = " + ints[0]);
         Console.WriteLine("Calling SomeFunction...");

         // After this method returns, ints will be changed,
         // but i will not
         SomeFunction(ints, ref i);
         Console.WriteLine("i = " + i);
         Console.WriteLine("ints[0] = " + ints[0]);
         return 0;
      }
   }
}