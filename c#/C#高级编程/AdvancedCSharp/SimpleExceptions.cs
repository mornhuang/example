using System;

namespace Wrox.ProCSharp.AdvancedCSharp
{
   public class MainEntryPoint
   {
      public static void Main()
      {
         string userInput;
         while ( true )
         {
            try
            {
               Console.Write("Input a number between 0 and 5 " + 
                  "(or just hit return to exit)> ");
               userInput = Console.ReadLine();
               if (userInput == "")
                  break;
               int index = Convert.ToInt32(userInput);
               if (index < 0 || index > 5)
                  throw new IndexOutOfRangeException(
                     "You typed in " + userInput);
                Console.WriteLine("Your number was " + index);
            }
            catch (IndexOutOfRangeException e)
            {
               Console.WriteLine("Exception: " + 
                  "Number should be between 0 and 5. " + e.Message);
            }            
            catch (Exception e)
            {
               Console.WriteLine(
                  "An exception was thrown. Message was: " + e.Message);
            }
            catch
            {
               Console.WriteLine("Some other exception has occurred");
            }
            finally
            {
               Console.WriteLine("Thank you");
            }
         }
      }
   }
}