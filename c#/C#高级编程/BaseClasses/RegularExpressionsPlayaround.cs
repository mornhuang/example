using System;
using System.Text.RegularExpressions;

namespace Wrox.ProCSharp.RegularExpressionPlayaround
{
   class MainEntryPoint
   {
      static void Main()
      {
         Find1();
         Console.ReadLine();
      }

      static void Find1()
      {
         string text = @"XML has made a major impact in almost every aspect of 
            software development. Designed as an open, extensible, self-describing 
            language, it has become the standard for data and document delivery on 
            the web. The panoply of XML-related technologies continues to develop 
            at breakneck speed, to enable validation, navigation, transformation, 
            linking, querying, description, and messaging of data.";
         string pattern = @"\bn\S*ion\b";
         MatchCollection matches = Regex.Matches(text, pattern, 
            RegexOptions.IgnoreCase | RegexOptions.IgnorePatternWhitespace |
            RegexOptions.ExplicitCapture);
         WriteMatches(text, matches);
      }		

      static void Find2()
      {
         string text = @"XML has made a major impact in almost every aspect of 
            software development. Designed as an open, extensible, self-describing 
            language, it has become the standard for data and document delivery on 
            the web. The panoply of XML-related technologies continues to develop 
            at breakneck speed, to enable validation, navigation, transformation, 
            linking, querying, description, and messaging of data.";
         string pattern = @"\bn";
         MatchCollection matches = Regex.Matches(text, pattern, 
           RegexOptions.IgnoreCase);
         WriteMatches(text, matches);
      }

      static void WriteMatches(string text, MatchCollection matches)
      {
         Console.WriteLine("Original text was: \n\n" + text + "\n");
         Console.WriteLine("No. of matches: " + matches.Count);
         foreach (Match nextMatch in matches)
         {
            int Index = nextMatch.Index;
            string result = nextMatch.ToString();
            int charsBefore = (Index < 5) ? Index : 5;
            int fromEnd = text.Length - Index - result.Length;
            int charsAfter = (fromEnd < 5) ? fromEnd : 5;
            int charsToDisplay = charsBefore + charsAfter + result.Length;

            Console.WriteLine("Index: {0}, \tString: {1}, \t{2}",
               Index, result, 
               text.Substring(Index - charsBefore, charsToDisplay));

         }
      }
   }
}