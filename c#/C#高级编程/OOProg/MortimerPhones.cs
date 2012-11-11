namespace Wrox.ProCSharp.OOProg
{
   using System;
   public enum TypeOfCall 
   {
      CallToCellPhone, CallToLandline
   } 

   public class Customer
   {
      private string name;
      private decimal balance;
      public string Name
      {
         get
         {
            return name;
         }
         set
         {
            name = value;
         }
      }

      public decimal Balance
      {
         get
         {
            return balance;
         }
      }
      public void RecordPayment(decimal amountPaid)
      {
         balance -= amountPaid;
      }
      public void RecordCall(TypeOfCall callType, uint nMinutes)
      {
         switch (callType)
         {
            case TypeOfCall.CallToLandline:
               balance += (0.02M * nMinutes);
               break;
            case TypeOfCall.CallToCellPhone:
               balance += (0.30M * nMinutes);
               break;
            default:
               break;
         }
      }
   }
   public class MainEntryPoint
   {
      public static void Main()
      {
         Customer arabel = new Customer();
         arabel.Name = "Arabel Jones";
         Customer mrJones = new Customer();
         mrJones.Name = "Ben Jones";
         arabel.RecordCall(TypeOfCall.CallToLandline, 20);
         arabel.RecordCall(TypeOfCall.CallToCellPhone, 5);
         mrJones.RecordCall(TypeOfCall.CallToLandline, 10);
         Console.WriteLine("{0,-20} owes ${1:F2}", arabel.Name, arabel.Balance);
         Console.WriteLine("{0,-20} owes ${1:F2}", mrJones.Name, mrJones.Balance);
      }
   }
}
