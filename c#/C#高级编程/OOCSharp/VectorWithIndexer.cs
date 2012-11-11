using System; 

namespace Wrox.ProCSharp.OOCSharp
{
   struct Vector
   {
      public double x, y, z;

      public double this [int i]
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
                                 "Attempt to retrieve Vector element " + i) ;
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
                                 "Attempt to set Vector element " + i);
            }
         }
      }

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
         Vector vect1 = new Vector(1.0, -5.0, 4.6);
         Vector vect2 = new Vector();
         Console.WriteLine("vect1 = " + vect1);
         Console.WriteLine("vect1[1] = " + vect1[1]);

         for(int i=0 ; i<3 ;i++)
         {
            vect2[i] = i;
         }
         Console.WriteLine("vect2 = " + vect2);
      }
   }
}

