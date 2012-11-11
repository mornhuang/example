using System;

namespace Wrox.ProCSharp.Basics
{
   class MainEntryPoint
   {
      static void Main(string[] args)
      {
         Console.WriteLine("Type in a string");
         string input;
         input = Console.ReadLine();
         if (input == "")
         {
            Console.WriteLine("You typed in an empty string");
         }
         else if (input.Length < 5)
         {
            Console.WriteLine("The string had less than 5 characters");
         }
         else if (input.Length < 10)
         {
            Console.WriteLine("The string had at least 5 but less than 10 characters");
         }
         Console.WriteLine("The string was " + input);
      }
   }
}