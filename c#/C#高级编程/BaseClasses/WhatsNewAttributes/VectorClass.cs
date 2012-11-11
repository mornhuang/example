using System;
using Wrox.ProCSharp.WhatsNewAttributes;
using System.Collections;
using System.Text;

[assembly: SupportsWhatsNew]

namespace Wrox.ProCSharp.VectorClass
{
   [LastModified("14 Feb 2002", "IEnumerable interface implemented\n" +
       "So Vector can now be treated as a collection")]
   [LastModified("10 Feb 2002", "IFormattable interface implemented\n" +
       "So Vector now responds to format specifiers N and VE")]
   class Vector : IFormattable, IEnumerable
   {
      public double x, y, z;

      public Vector(double x, double y, double z)
      {
         this.x = x;
         this.y = y;
         this.z = z;
      }

      [LastModified("10 Feb 2002", "Method added in order to provide formatting support")]
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

      [LastModified("14 Feb 2002", "Method added in order to provide collection support")]
      public IEnumerator GetEnumerator()
      {
         return new VectorEnumerator(this);
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

      public static bool operator == (Vector lhs, Vector rhs)
      {
         if (System.Math.Abs(lhs.x - rhs.x) < double.Epsilon && 
            System.Math.Abs(lhs.y - rhs.y) < double.Epsilon &&
            System.Math.Abs(lhs.z - rhs.z) < double.Epsilon )
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

      #region enumerator class
      [LastModified("14 Feb 2002", "Class created as part of collection support for Vector")]
      private class VectorEnumerator : IEnumerator
      {
         Vector theVector;      // Vector object that this enumerato refers to 
         int location;   // which element of theVector the enumerator is currently referring to 

         public VectorEnumerator(Vector theVector)
         {
            this.theVector = theVector;
            location = -1;
         }

         public bool MoveNext()
         {
            ++location;
            return (location > 2) ? false : true;
         }

         public object Current
         {
            get
            {
               if (location < 0 || location > 2)
                  throw new InvalidOperationException(
                     "The enumerator is either before the first element or " +
                     "after the last element of the Vector");
               return theVector[(uint)location];
            }
         }

         public void Reset()
         {
            location = -1;
         }

      }
      #endregion
   }
}