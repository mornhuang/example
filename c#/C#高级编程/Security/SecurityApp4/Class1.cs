using System;
using System.IO;
using System.Security;
using System.Security.Permissions;

namespace SecurityApp4
{
   class Class1
   {
      static void Main(string[] args)
      {
         CodeAccessPermission permission = new FileIOPermission(FileIOPermissionAccess.AllAccess,@"c:\");
         permission.Deny();
         UntrustworthyClass.Method();
         CodeAccessPermission.RevertDeny();
      }
   }

   class UntrustworthyClass
   {
      public static void Method()
      {
         try
         {
            StreamReader din = File.OpenText(@"c:\animals.txt");
         }
         catch
         {
            Console.WriteLine("Failed to open file");
         }
      }
   }
}
