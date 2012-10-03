using System;

namespace Wrox.ProCSharp.AdvancedCSharp
{
   class MainEntryPoint
   {
      static void Main()
      {
         try
         {
            Currency balance = new Currency(50,35);
            Console.WriteLine(balance);
            Console.WriteLine("balance is " + balance);
            Console.WriteLine("balance is (using ToString()) " + balance.ToString());

            float balance2= balance;
            Console.WriteLine("After converting to float, = " + balance2);
            balance = (Currency) balance2;
            Console.WriteLine("After converting back to Currency, = " + balance);

            Console.WriteLine(
               "Now attempt to convert out of range value of -$100.00 to a Currency:"); 
            checked
            {
               balance = (Currency) (-50.5);
               Console.WriteLine("Result is " + balance.ToString());
            }
         }
         catch(Exception e)
         {
            Console.WriteLine("Exception occurred: " + e.Message);
         }
      }
   }

   struct Currency
   {
      public uint Dollars;
      public ushort Cents;

      public Currency(uint dollars, ushort cents)
      {
         this.Dollars = dollars;
         this.Cents = cents;
      }

      public override string ToString()
      {
         return string.Format("${0}.{1,-2:00}", Dollars,Cents);
      }

      public static explicit operator Currency (float value)
      {
         uint dollars = (uint)value;
         ushort cents = (ushort)((value-dollars)*100);
         return new Currency(dollars, cents);
      }
      
      public static implicit operator float (Currency value)
      {
         return value.Dollars + (value.Cents/100.0f);
      }
   }
}