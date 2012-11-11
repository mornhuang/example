using System;
using Wrox.ProCSharp.OOCSharp.BankProtocols;
using Wrox.ProCSharp.OOCSharp.VenusBank;
using Wrox.ProCSharp.OOCSharp.JupiterBank;

namespace Wrox.ProCSharp.OOCSharp.BankProtocols
{
   public interface IBankAccount
   {
      void PayIn(decimal amount);
      bool Withdraw(decimal amount);

      decimal Balance
      {
         get;
      }
   }
}

namespace Wrox.ProCSharp.OOCSharp.JupiterBank
{
   public class GoldAccount : IBankAccount
   {
      private decimal balance;

      public void PayIn(decimal amount)
      {
         balance += amount;
      }

      public bool Withdraw(decimal amount)
      {
         if (balance >= amount)
         {
            balance -= amount;
            return true;
         }
         Console.WriteLine("Withdrawal attempt failed.");
         return false;
      }

      public decimal Balance
      {
         get
         {
            return balance;
         }
      }
      public override string ToString()
      {
         return String.Format("Jupiter Bank Saver: Balance = {0,6:C}", balance);
      }
   }
}

namespace Wrox.ProCSharp.OOCSharp.VenusBank
{
   public class SaverAccount : IBankAccount
   {
      private decimal balance;

      public void PayIn(decimal amount)
      {
         balance += amount;
      }

      public bool Withdraw(decimal amount)
      {
         if (balance >= amount)
         {
            balance -= amount;
            return true;
         }
         Console.WriteLine("Withdrawal attempt failed.");
         return false;
      }

      public decimal Balance
      {
         get
         {
            return balance;
         }
      }
      public override string ToString()
      {
         return String.Format("Venus Bank Saver: Balance = {0,6:C}", balance);
      }
   }
}


namespace Wrox.ProCSharp.OOCSharp
{
   class MainEntryPoint
   {
      static void Main()
      {
         IBankAccount venusAccount = new SaverAccount();
         IBankAccount jupiterAccount = new GoldAccount();
         venusAccount.PayIn(200);
         venusAccount.Withdraw(100);
         Console.WriteLine(venusAccount.ToString());
         jupiterAccount.PayIn(500);
         jupiterAccount.Withdraw(600);
         jupiterAccount.Withdraw(100);
         Console.WriteLine(jupiterAccount.ToString());
      }
   }
}

