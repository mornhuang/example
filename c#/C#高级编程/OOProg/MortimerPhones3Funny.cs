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
      protected decimal balance;
      public string GetFunnyString()
      {
         return "Plain ordinary customer. Kaark!";
      }
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
      public virtual void RecordCall(TypeOfCall callType, uint nMinutes)
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
   public class Nevermore60Customer : Customer
   {
      private uint highCostMinutesUsed;
      public new string GetFunnyString()
      {
         return "Nevermore60. Nevermore!";
      }
      public override void RecordCall(TypeOfCall callType, uint nMinutes)
      {
         switch (callType)
         {
            case TypeOfCall.CallToLandline:
               balance += (0.02M * nMinutes);
               break;
            case TypeOfCall.CallToCellPhone:
         uint highCostMinutes, lowCostMinutes;
         uint highCostMinutesToGo = 
            (highCostMinutesUsed < 60) ? 60 - highCostMinutesUsed : 0;
         if (nMinutes > highCostMinutesToGo)
         {
            highCostMinutes = highCostMinutesToGo;
            lowCostMinutes = nMinutes - highCostMinutes;
         }
         else
         {
            highCostMinutes = nMinutes;
            lowCostMinutes = 0;
         }
         highCostMinutesUsed += highCostMinutes;
         balance += (0.50M * highCostMinutes + 0.20M * 
            lowCostMinutes);
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
         Customer cust1;
         Nevermore60Customer cust2;
         cust1 = new Customer();
         Console.WriteLine("Customer referencing Customer: " 
            + cust1.GetFunnyString());
         cust1 = new Nevermore60Customer();
         Console.WriteLine("Customer referencing Nevermore60Customer: " 
            + cust1.GetFunnyString());
         cust2 = new Nevermore60Customer();
         Console.WriteLine("Nevermore60Customer referencing: " 
            + cust2.GetFunnyString());   
      }
   }
}
