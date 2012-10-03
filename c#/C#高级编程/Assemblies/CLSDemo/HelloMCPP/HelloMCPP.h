// HelloMCPP.h

#pragma once
#include <stdio.h>
using namespace System;
namespace Wrox
{
   namespace ProCSharp
   {
      namespace Assemblies
      {
         namespace CrossLanguage
         {
            public __gc class HelloMCPP
            {
            public:
               virtual void Hello()
               {
                  Console::WriteLine(S"Hello, Managed C++");
               }
               virtual void Hello2()
               {
                  printf("Hello, calling native code\n");
               }
               int Add(int val1, int val2)
               {
                  return val1 + val2;
               }
            };
         }
      }
   }
}
