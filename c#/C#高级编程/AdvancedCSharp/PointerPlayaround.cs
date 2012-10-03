using System;

namespace Wrox.ProCSharp.AdvancedCSharp
{
   class MainEntryPoint
   {
      static unsafe void Main()
      {
         int x=10;
         short y = -1;
         byte y2 = 4;
         double z = 1.5;
         int *pX = &x;
         short *pY = &y;
         double *pZ = &z;

         Console.WriteLine(
            "Address of x is 0x{0:X}, size is {1}, value is {2}", 
            (uint)&x, sizeof(int), x);
         Console.WriteLine(
            "Address of y is 0x{0:X}, size is {1}, value is {2}", 
            (uint)&y, sizeof(short), y);
         Console.WriteLine(
            "Address of y2 is 0x{0:X}, size is {1}, value is {2}", 
            (uint)&y2, sizeof(byte), y2);
         Console.WriteLine(
            "Address of z is 0x{0:X}, size is {1}, value is {2}", 
            (uint)&z, sizeof(double), z);
         Console.WriteLine(
            "Address of pX=&x is 0x{0:X}, size is {1}, value is 0x{2:X}", 
            (uint)&pX, sizeof(int*), (uint)pX);
         Console.WriteLine(
            "Address of pY=&y is 0x{0:X}, size is {1}, value is 0x{2:X}", 
            (uint)&pY, sizeof(short*), (uint)pY);
         Console.WriteLine(
            "Address of pZ=&z is 0x{0:X}, size is {1}, value is 0x{2:X}", 
            (uint)&pZ, sizeof(double*), (uint)pZ);

         *pX = 20;
         Console.WriteLine("After setting *pX, x = {0}", x);
         Console.WriteLine("*pX = {0}", *pX);

         pZ = (double*)pX;
         Console.WriteLine("x treated as a double = {0}", *pZ);

         Console.ReadLine();
      }
   }
}