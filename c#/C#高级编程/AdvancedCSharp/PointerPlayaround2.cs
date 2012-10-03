using System;

namespace Wrox.ProCSharp.AdvancedCSharp
{
   public class MainEntryPoint
   {
      public static unsafe void Main()
      {
         Console.WriteLine("Size of Currency struct is " + sizeof(CurrencyStruct));
         CurrencyStruct amount1, amount2;
         CurrencyStruct *pAmount = &amount1;
         long *pDollars = &(pAmount->Dollars);
         byte *pCents = &(pAmount->Cents);

         Console.WriteLine("Address of amount1 is 0x{0:X}", (uint)&amount1);
         Console.WriteLine("Address of amount2 is 0x{0:X}", (uint)&amount2);
         Console.WriteLine("Address of pAmt is 0x{0:X}", (uint)&pAmount);
         Console.WriteLine("Address of pDollars is 0x{0:X}", (uint)&pDollars);
         Console.WriteLine("Address of pCents is 0x{0:X}", (uint)&pCents);
         pAmount->Dollars = 20;
         *pCents = 50;
         Console.WriteLine("amount1 contains " + amount1);
         --pAmount;   // this should get it to point to amount2
         Console.WriteLine("amount2 has address 0x{0:X} and contains {1}", 
            (uint)pAmount, *pAmount);
         // do some clever casting to get pCents to point to cents
         // inside amount2
         CurrencyStruct *pTempCurrency = (CurrencyStruct*)pCents;
         pCents = (byte*) ( --pTempCurrency );
         Console.WriteLine("Address of pCents is now 0x{0:X}", (uint)&pCents);
         Console.WriteLine("\nNow with classes");
         // now try it out with classes
         CurrencyClass amount3 = new CurrencyClass();

         fixed(long *pDollars2 = &(amount3.Dollars))
         fixed(byte *pCents2 = &(amount3.Cents))
         {
            Console.WriteLine("amount3.Dollars has address 0x{0:X}", (uint)pDollars2);
            Console.WriteLine("amount3.Cents has address 0x{0:X}", (uint) pCents2);
            *pDollars2 = -100;
            Console.WriteLine("amount3 contains " + amount3);
         }
      }
   }

   struct CurrencyStruct
   {
      public long Dollars;
      public byte Cents;

      public override string ToString()
      {
         return "$" + Dollars + "." + Cents;
      }
   }

   class CurrencyClass
   {
      public long Dollars;
      public byte Cents;

      public override string ToString()
      {
         return "$" + Dollars + "." + Cents;
      }
   }
}