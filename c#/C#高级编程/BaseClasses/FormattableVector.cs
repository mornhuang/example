using System;
using System.Text;

namespace Wrox.ProCSharp.FormattableVector
{
   class MainEntryPoint
   {
      static void Main()
      {
         Vector v1 = new Vector(1,32,5);
         Vector v2 = new Vector(845.4, 54.3, -7.8);
         Console.WriteLine("\nIn IJK format,\nv1 is {0,30:IJK}\nv2 is {1,30:IJK}", v1, v2);
         Console.WriteLine("\nIn default format,\nv1 is {0,30}\nv2 is {1,30}", v1, v2);
         Console.WriteLine("\nIn VE format\nv1 is {0,30:VE}\nv2 is {1,30:VE}", v1, v2);
         Console.WriteLine("\nNorms are:\nv1 is {0,20:N}\nv2 is {1,20:N}", v1, v2);
      }
   }

   struct Vector : IFormattable
   {
      public double x, y, z;

      public Vector(double x, double y, double z)
      {
         this.x = x;
         this.y = y;
         this.z = z;
      }

      public string ToString(string format, IFormatProvider formatProvider)
      {
         if (format == null)
            return ToString();
         string formatUpper = format.ToUpper();
         switch (formatUpper)
         {
            case "N":
               return "|| " + Norm().ToString() + " ||";
            case "VE":
               return String.Format("( {0:E}, {1:E}, {2:E} )", x, y, z);
            case "IJK":
               StringBuilder sb = new StringBuilder(x.ToString(), 30);
               sb.Append(" i + ");
               sb.Append(y.ToString());
               sb.Append(" j + ");
               sb.Append(z.ToString());
               sb.Append(" k");
               return sb.ToString();
            default:
               return ToString();
         }
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

      public double this [uint i]
      {
         get
         {
            switch (i)
            {
               case 0:
                  return x;
               case 1:
                  return y;
               case 2:
                  return z;
               default:
                  throw new IndexOutOfRangeException(
                     "Attempt to retrieve Vector element" + i) ;
            }
         }
         set
         {
            switch (i)
            {
               case 0:
                  x = value;
                  break;
               case 1:
                  y = value;
                  break;
               case 2:
                  z = value;
                  break;
               default:
                  throw new IndexOutOfRangeException(
                     "Attempt to set Vector element" + i) ;
            }
         }
      }

         /* public static bool operator == (Vector lhs, Vector rhs)
            {
               if (lhs.x == rhs.x && lhs.y == rhs.y && lhs.z == rhs.z)
                  return true;
               else
                  return false;
            }*/

      private const double Epsilon = 0.0000001;

      public static bool operator == (Vector lhs, Vector rhs)
      {
         if (System.Math.Abs(lhs.x - rhs.x) < Epsilon && 
            System.Math.Abs(lhs.y - rhs.y) < Epsilon &&
            System.Math.Abs(lhs.z - rhs.z) < Epsilon )
            return true;
         else
            return false;
      }

      public static bool operator != (Vector lhs, Vector rhs)
      {
         return ! (lhs == rhs);
      }

      public static Vector operator + (Vector lhs, Vector rhs)
      {
         Vector Result = new Vector(lhs);
         Result.x += rhs.x;
         Result.y += rhs.y;
         Result.z += rhs.z;
         return Result;
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

      public double Norm()
      {
         return x*x + y*y + z*z;
      }
   }
}