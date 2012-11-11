using System;
using System.Security;
using System.Security.Principal;
using System.Security.Permissions;

namespace SecurityApp8
{
   class Class1
   {
      static void Main(string[] args)
      {
         AppDomain.CurrentDomain.SetPrincipalPolicy(PrincipalPolicy.WindowsPrincipal);
         try
         {
            ShowMessage();
         }
         catch (SecurityException exception)
         {
            Console.WriteLine("Security exception caught (" + exception.Message + ")");
            Console.WriteLine("The current principal must be in the local Users group");
         }
      }

      [PrincipalPermissionAttribute(SecurityAction.Demand, Role = "BUILTIN\\Users")]
      static void ShowMessage()
      {
         Console.WriteLine("The current principal is logged in locally ");
         Console.WriteLine("(they are a member of the local Users group)");
      }
   }
}
