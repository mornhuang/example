using System;

namespace Wrox.ProCSharp.OOProg
{
   class MainEntryPoint
   {
      static void Main()
      {
         Authenticator simon = new Authenticator();
         bool done;
         done = simon.ChangePassword("", "MyNewPassword");
         if (done == true) 
            Console.WriteLine("Password for Simon changed");
         else
            Console.WriteLine("Failed to change password for Simon");
         done = simon.ChangePassword("", "AnotherPassword");
         if (done == true) 
            Console.WriteLine("Password for Simon changed");
         else
            Console.WriteLine("Failed to change password for Simon");

         if (simon.IsPasswordCorrect("WhatPassword"))
            Console.WriteLine("Verified Simon\'s password");
         else
            Console.WriteLine("Failed to verify Simon\'s password");
      }

   }

   public class Authenticator
   {
      // implementation as shown earlier

      private string password = "";
   
      public bool IsPasswordCorrect(string tryPassword)
      {
         return (tryPassword == password) ? true : false;
      }

      public bool ChangePassword(string oldPassword, string newPassword)
      {
         if (oldPassword == password)
         {
            password = newPassword;
            return true;
         }
         else
            return false;
      }
   }
}
