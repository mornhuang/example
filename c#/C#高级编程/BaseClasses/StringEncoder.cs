using System;
using System.Text;

namespace Wrox.ProCSharp.StringEncoder
{
   class MainEntryPoint
   {
      static void Main(string[] args)
      {
         string greetingText = "Hello from all the guys at Wrox Press. ";
         greetingText += "We do hope you enjoy this book as much as we enjoyed writing it.";

         for(int i = (int)'z'; i>=(int)'a' ; i--)
         {
            char Old = (char)i;
            char New = (char)(i+1);
            greetingText = greetingText.Replace(Old, New);
         }

         for(int i = (int)'Z'; i>=(int)'A' ; i--)
         {
            char Old = (char)i;
            char New = (char)(i+1);
            greetingText = greetingText.Replace(Old, New);
         }
         Console.WriteLine("Encoded:\n" + greetingText);

         StringBuilder greetingBuilder =
            new StringBuilder("Hello from all the guys at Wrox Press. ", 150);
         greetingBuilder.Append("We do hope you enjoy this book as much as we enjoyed writing it");

         for(int i = (int)'z'; i>=(int)'a' ; i--)
         {
            char Old = (char)i;
            char New = (char)(i+1);
            greetingBuilder = greetingBuilder.Replace(Old, New);
         }

         for(int i = (int)'Z'; i>=(int)'A' ; i--)
         {
            char Old = (char)i;
            char New = (char)(i+1);
            greetingBuilder = greetingBuilder.Replace(Old, New);
         }
         Console.WriteLine("Encoded:\n" + greetingBuilder.ToString());
					
      }
   }
}