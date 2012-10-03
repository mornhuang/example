using System;
using System.IO;
using System.Security;
using System.Security.Permissions;
namespace SecurityApp5
{
   class Class1
   {
      static void Main(string[] args)
      {
         CodeAccessPermission permission = new FileIOPermission(FileIOPermissionAccess.Append,@"c:\audit.txt");
         permission.Deny();
         AuditClass.Save("some data to audit");
         CodeAccessPermission.RevertDeny();
      }
   }
   class AuditClass
   {
      public static void Save(string value)
      {
         try
         {
            FileIOPermission permission = new FileIOPermission(FileIOPermissionAccess.Append,@"c:\audit.txt");
            permission.Assert();
            FileStream stream = new FileStream(@"c:\audit.txt",FileMode.Append, FileAccess.Write);
            // code to write to audit file here...
            CodeAccessPermission.RevertAssert();
            Console.WriteLine("Data written to audit file");
         }
         catch
         {
            Console.WriteLine("Failed to write data to audit file");
         }
      }
   }
}
