using System; 

namespace Wrox.ProCSharp.OOCSharp
{
   struct Vector
   {
      public double x, y, z;

      public Vector(double x, double y, double z)
      {
         this.x = x;
         this.y = y;
         this.z = z;
      }

      public Vector(Vector rhs)
      {
         x = rhs.x;
         y = rhs.y;
         z = rhs.z;
      }

      public override string ToString()
      {
         return "( " + x + " , " + y + " , " + z + " )"; 
      }

      public static Vector operator + (Vector lhs, Vector rhs)
      {
         Vector result = new Vector(lhs);
         result.x += rhs.x;
         result.y += rhs.y;
         result.z += rhs.z;
         return result;
      }

      public static Vector operator * (double lhs, Vector rhs)
      {
         return new Vector(lhs*rhs.x, lhs*rhs.y, lhs*rhs.z);
      }

      public static Vector operator * (Vector lhs, double rhs)
      {
         return rhs*lhs;
      }

      public static double operator * (Vector lhs, Vector rhs)
      {
         return lhs.x*rhs.x + lhs.y+rhs.y + lhs.z*rhs.z;
      }

      static void Main()
      {
         // stuff to demonstrate arithmetic operations
         Vector vect1, vect2, vect3;
         vect1 = new Vector(1.0, 1.5, 2.0);
         vect2 = new Vector(0.0, 0.0, -10.0);
         vect3 = vect1 + vect2;
         Console.WriteLine("vect1 = " + vect1);
         Console.WriteLine("vect2 = " + vect2);
         Console.WriteLine("vect3 = vect1 + vect2 = " + vect3);
         Console.WriteLine("2*vect3 = " + 2*vect3);
         vect3 += vect2;
         Console.WriteLine("vect3+=vect2 gives " + vect3);
         vect3 = vect1*2;
         Console.WriteLine("Setting vect3=vect1*2 gives " + vect3);
         double dot = vect1*vect3;
         Console.WriteLine("vect1*vect3 = " + dot);

      }
   }
}

