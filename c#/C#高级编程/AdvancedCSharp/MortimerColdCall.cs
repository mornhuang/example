using System;
using System.IO;

namespace Wrox.ProCSharp.AdvancedCSharp
{
   class MainEntryPoint
   {
      static void Main()
      {
         string fileName;
         Console.Write("Please type in the name of the file " +
            "containing the names of the people to be cold-called > ");
         fileName = Console.ReadLine();
         ColdCallFileReader peopleToRing = new ColdCallFileReader();

         try
         {
            peopleToRing.Open(fileName);
            for (int i=0 ; i<peopleToRing.NPeopleToRing; i++)
            {
               peopleToRing.ProcessNextPerson();
            }
            Console.WriteLine("All callees processed correctly");
         }
         catch(FileNotFoundException e)
         {
            Console.WriteLine("The file {0} does not exist", fileName);
         }
         catch(ColdCallFileFormatException e)
         {
            Console.WriteLine(
          "The file {0} appears to have been corrupted", fileName);
            Console.WriteLine("Details of problem are: {0}", e.Message);
            if (e.InnerException != null)
               Console.WriteLine(
                  "Inner exception was: {0}", e.InnerException.Message);
         }
         catch(Exception e)
         {
            Console.WriteLine("Exception occurred:\n" + e.Message);
         }         
         finally
         {
            peopleToRing.Dispose();
         }
         Console.ReadLine();
      }
   }

   class ColdCallFileReader :IDisposable
   {
      FileStream fs;
      StreamReader sr;
      uint nPeopleToRing;
      bool isDisposed = false;
      bool isOpen = false;

      public void Open(string fileName)
      {
         if (isDisposed)
            throw new ObjectDisposedException("peopleToRing");
         fs = new FileStream(fileName, FileMode.Open);
         sr = new StreamReader(fs);
         try 
         {
            string firstLine = sr.ReadLine();
            nPeopleToRing = uint.Parse(firstLine);
            isOpen = true;
         }
         catch (FormatException e)
         {
            throw new ColdCallFileFormatException(
               "First line isn\'t an integer", e);
         }
      }

      public uint NPeopleToRing
      {
         get
         {
            if (isDisposed)
               throw new ObjectDisposedException("peopleToRing");
            if (!isOpen)
               throw new UnexpectedException(
                  "Attempt to access cold call file that is not open");
            return nPeopleToRing;
         }
      }

      public void Dispose()
      {
         if (isDisposed)
            return;

         isDisposed = true;
         isOpen = false;
         if (fs != null)
         {
            fs.Close();
            fs = null;
         }
      }

      public void ProcessNextPerson()
      {
         if (isDisposed)
            throw new ObjectDisposedException("peopleToRing");
         if (!isOpen)
            throw new UnexpectedException(
               "Attempt to access cold call file that is not open");
         try
         {
            string name;
            name = sr.ReadLine();
            if (name == null)
               throw new ColdCallFileFormatException("Not enough names");
            if (name[0] == 'Z')
            {
               throw new LandLineSpyFoundException(name);
            }
            Console.WriteLine(name);
         }
         catch(LandLineSpyFoundException e)
         {
            Console.WriteLine(e.Message);
         }

         finally
         {
         }
      }
   }

   class LandLineSpyFoundException : ApplicationException
   {
      public LandLineSpyFoundException(string spyName)
         :   base("LandLine spy found, with name " + spyName)
      {
      }

      public LandLineSpyFoundException(string spyName, Exception innerException)
         :   base("LandLine spy found, with name " + spyName, innerException)
      {
      }
   }

   class ColdCallFileFormatException : ApplicationException
   {
      public ColdCallFileFormatException(string message)
         :   base(message)
      {
      }

      public ColdCallFileFormatException(string message, Exception innerException)
         :   base(message, innerException)
      {
      }
   }

   class UnexpectedException : ApplicationException
   {
      public UnexpectedException(string message)
         :   base(message)
      {
      }

      public UnexpectedException(string message, Exception innerException)
         :   base(message, innerException)
      {
      }
   }
}