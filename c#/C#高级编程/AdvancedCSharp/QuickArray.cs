using System;

namespace Wrox.ProCSharp.AdvancedCSharp
{
   class MainEntryPoint
   {
      static unsafe void Main()
      {
         Console.Write("How big an array do you want? \n> ");
         string userInput = Console.ReadLine();
         uint size = uint.Parse(userInput);

         long *pArray = stackalloc long [(int)size];
         for (int i=0 ; i<size ; i++)
            pArray[i] = i*i;

         for (int i=0 ; i<size ; i++)
            Console.WriteLine("Element {0} = {1}", i, *(pArray+i));
      }
   }
}