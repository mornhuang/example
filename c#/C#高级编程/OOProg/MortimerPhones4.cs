namespace Wrox.ProCSharp.OOProg
{
   using System;
   public enum TypeOfCall 
   {
      CallToCellPhone, CallToLandline
   } 

   public abstract class GenericCustomer
   {
      private string name;
      protected decimal balance;
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
      public abstract void RecordCall(TypeOfCall callType, uint nMinutes);
   }

   public class Nevermore60Customer : GenericCustomer
   {
      private uint highCostMinutesUsed;
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
   public class PayAsYouGoCustomer : GenericCustomer
   {
      public override void RecordCall(TypeOfCall callType, uint nMinutes)
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
         GenericCustomer arabel = new Nevermore60Customer();
         arabel.Name = "Arabel Jones";
         GenericCustomer mrJones = new PayAsYouGoCustomer();
         mrJones.Name = "Ben Jones";                
         GenericCustomer [] customers = new GenericCustomer[2];
         customers[0] = arabel;
         customers[0].RecordCall(TypeOfCall.CallToLandline, 20);
         customers[0].RecordCall(TypeOfCall.CallToCellPhone, 5);
         customers[1] = mrJones;
         customers[1].RecordCall(TypeOfCall.CallToLandline, 10);
         foreach (GenericCustomer nextCustomer in customers)
         {
            Console.WriteLine("{0,-20} owes ${1:F2}", nextCustomer.Name, nextCustomer.Balance);
         }
      }
   }
}
