using System;
using System.Security;
using System.Security.Permissions;

namespace SecurityApp3
{
   /// <summary>
   /// Summary description for Class1.
   /// </summary>
   class Class1
   {
      static void Main(string[] args)
      {
         CodeAccessPermission permissionA = new FileIOPermission(FileIOPermissionAccess.AllAccess, @"c:\");
         CodeAccessPermission permissionB = new FileIOPermission(FileIOPermissionAccess.Read, @"c:\temp");
         if (permissionB.IsSubsetOf(permissionA))
         {	
            System.Console.WriteLine("PermissionB is a subset of PermissionA");
         }
         else
         {
            Console.WriteLine("PermissionB is NOT a subset of PermissionA");
         }
      }
   }
}
