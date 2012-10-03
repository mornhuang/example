using System;

namespace Wrox.ProCSharp.Basics
{
   class MainEntryPoint
   {
      static void Main()
      {
         // Declare a two-dimension jagged array of authors' names
         string[][] novelists = new string[3][];
         novelists[0] = new string[] {
            "Fyodor", "Mikhailovich", "Dostoyevsky"};
         novelists[1] = new string[] {
            "James", "Augustine", "Aloysius", "Joyce"};
         novelists[2] = new string[] {
            "Miguel", "de Cervantes", "Saavedra"};

         // Loop through each novelist in the array
         int i;
         for (i = 0; i < novelists.GetLength(0); i++)
         {
            // Loop through each name for the novelist
            int j;
            for (j = 0; j < novelists[i].GetLength(0); j++)
            {
               // Display current part of name
               Console.Write(novelists[i][j] + " ");
            }
            // Start a new line for the next novelist
            Console.Write("\n");
         }
      }
   }
}