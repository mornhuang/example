using System;
using System.Globalization;
using System.Threading;

namespace Wrox.ProCSharp.Assemblies.LocalizationNumbers
{
   class Class1
   {
      [STAThread]
      static void Main(string[] args)
      {
         int val = 1234567890;
         // culture of the current thread
         Console.WriteLine(val.ToString("N"));
         // use IFormatProvider
         Console.WriteLine(val.ToString("N", new CultureInfo("fr-fr")));
         // change the culture of the thread
         Thread.CurrentThread.CurrentCulture = new CultureInfo("de-de");
         Console.WriteLine(val.ToString("N"));
      }
   }
}
