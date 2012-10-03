using System; 

namespace Wrox.ProCSharp.OOPCSharp
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

      static void Main()
      {
         Vector vect1, vect2, vect3;
         vect1 = new Vector(3.0, 3.0, 1.0);
         vect2 = new Vector(2.0, -4.0, -4.0);
         vect3 = vect1 + vect2;
         Console.WriteLine("vect1 = " + vect1.ToString());
         Console.WriteLine("vect2 = " + vect2.ToString());
         Console.WriteLine("vect3 = " + vect3.ToString());
      }
   }
}

