using System;
using System.Security.Permissions;

namespace SecurityApp6
{

   class Class1
   {
      static void Main(string[] args)
      {
         MyClass.Method();
      }
   }

   [FileIOPermission(SecurityAction.Assert, Read="c:\\")]
   class MyClass
   {
      public static void Method()
      {	
         // implementation goes here
      }
   }
}
