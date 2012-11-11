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
   public interface ITransferBankAccount : IBankAccount
   {
      bool TransferTo(IBankAccount destination, decimal amount);
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

   public class CurrentAccount : ITransferBankAccount
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

      public bool TransferTo(IBankAccount destination, decimal amount)
      {
         bool result;
         if ((result = Withdraw(amount)) == true)
            destination.PayIn(amount);
         return result;
      }

      public override string ToString()
      {
         return String.Format("Jupiter Bank Current Account: Balance = {0,6:C}", balance);
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
         ITransferBankAccount jupiterAccount = new CurrentAccount();
         venusAccount.PayIn(200);
         jupiterAccount.PayIn(500);
         jupiterAccount.TransferTo(venusAccount, 100);
         Console.WriteLine(venusAccount.ToString());
         Console.WriteLine(jupiterAccount.ToString());
      }
   }
}

