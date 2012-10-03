using System;
using System.Text;
using System.Collections;

namespace Wrox.ProCSharp.SocialSecurityNumbers
{
   class MainEntryPoint
   {
      static void Main()
      {
         TestHarness harness = new TestHarness();
         harness.Run();
      }
   }

   class TestHarness
   {

      Hashtable employees = new Hashtable(31);

      public void Run()
      {
         EmployeeID idMortimer = new EmployeeID("B001");
         EmployeeData mortimer = new EmployeeData(idMortimer, "Mortimer", 100000.00M);
         EmployeeID idArabel = new EmployeeID("W234");
         EmployeeData arabel= new EmployeeData(idArabel, "Arabel Jones", 10000.00M);

         employees.Add(idMortimer, mortimer);
         employees.Add(idArabel, arabel);

         while (true)
         {
            try
            {
               Console.Write("Enter employee ID (format:A999, X to exit)> ");
               string userInput = Console.ReadLine();
               userInput = userInput.ToUpper();
               if (userInput == "X")
                  return;
               EmployeeID id = new EmployeeID(userInput);
               DisplayData(id);
            }
            catch (Exception e)
            {
               Console.WriteLine("Exception occurred. Did you use the correct format for the employee ID?");
               Console.WriteLine(e.Message);
               Console.WriteLine();
            }

            Console.WriteLine();
         }
      }

      private void DisplayData(EmployeeID id)
      {
         object empobj = employees[id];
         if (empobj != null)
         {
            EmployeeData employee = (EmployeeData)empobj;
            Console.WriteLine("Employee: " + employee.ToString());
         }
         else
            Console.WriteLine("Employee not found: ID = " + id);
      }
   }

   class EmployeeData
   {
      private string name;
      private decimal salary;
      private EmployeeID id;

      public EmployeeData(EmployeeID id, string name, decimal salary)
      {
         this.id = id;
         this.name = name;
         this.salary = salary;
      }

      public override string ToString()
      {
         StringBuilder sb = new StringBuilder(id.ToString(), 100);
         sb.Append(": ");
         sb.Append(string.Format("{0,-20}", name));
         sb.Append(" ");
         sb.Append(string.Format("{0:C}", salary));
         return sb.ToString();
      }
   }

   class EmployeeID
   {
      private readonly char prefix;
      private readonly int number;


      public EmployeeID(string id)
      {
         prefix = (id.ToUpper())[0];
         number = int.Parse(id.Substring(1,3));
      }

      public override string ToString()
      {
         return prefix.ToString() + string.Format("{0,3:000}", number);
      }

      public override int GetHashCode()
      {
         string str = this.ToString();
         return str.GetHashCode();
      }

      public override bool Equals(object obj)
      {
         EmployeeID rhs = obj as EmployeeID;
         if (rhs == null)
            return false;
         if (prefix == rhs.prefix && number == rhs.number)
            return true;
         return false;
      }
   }
}