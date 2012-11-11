using System;
using System.Drawing;

namespace Wrox.ProCSharp.OOPCSharp
{
   class MainEntryPoint
   {
      static void Main(string[] args)
      {
         Console.WriteLine("User-preferences: BackColor is: " + 
                            UserPreferences.BackColor.ToString());
      }
   }

   sealed class UserPreferences
   {
      public static readonly Color BackColor;

      static UserPreferences()
      {
         DateTime now = DateTime.Now;
         if (now.DayOfWeek == DayOfWeek.Saturday 
            || now.DayOfWeek == DayOfWeek.Sunday)
            BackColor = Color.Green;
         else
            BackColor = Color.Red;
      }

      private UserPreferences()
      {
      }
   }
}