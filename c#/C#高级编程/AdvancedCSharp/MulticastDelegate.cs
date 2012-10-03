using System;

namespace Wrox.ProCSharp.AdvancedCSharp
{
   delegate void DoubleOp(double value);

   class MainEntryPoint
   {
      static void Main()
      {
         DoubleOp operations = new DoubleOp(MathsOperations.MultiplyByTwo);
         operations += new DoubleOp(MathsOperations.Square);

         ProcessAndDisplayNumber(operations, 2.0);
         ProcessAndDisplayNumber(operations, 7.94);
         ProcessAndDisplayNumber(operations, 1.414);
         Console.WriteLine();
      }

      static void ProcessAndDisplayNumber(DoubleOp action, double value)
      {
         Console.WriteLine("\nProcessAndDisplayNumber called with value = " + value);
         action(value);
      }
   }

   class MathsOperations
   {
      public static void MultiplyByTwo(double value)
      {
         double result = value*2;
         Console.WriteLine("Multiplying by 2: {0} gives {1}", value, result);
      }

      public static void Square(double value)
      {
         double result = value*value;
         Console.WriteLine("Squaring: {0} gives {1}", value, result);
      }
   }
}