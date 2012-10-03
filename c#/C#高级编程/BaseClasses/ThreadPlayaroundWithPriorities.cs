using System;
using System.Threading;

namespace Wrox.ProCSharp.ThreadPlayaround
{
   class EntryPoint
   {
      static int interval;

      static void Main()
      {
         Console.Write("Interval to display results at?> ");
         interval = int.Parse(Console.ReadLine());

         Thread thisThread = Thread.CurrentThread;
         thisThread.Name = "Main Thread";

         ThreadStart workerStart = new ThreadStart(StartMethod);
         Thread workerThread = new Thread(workerStart);
         workerThread.Name = "Worker";
         workerThread.Priority = ThreadPriority.AboveNormal;
         workerThread.Start();

         DisplayNumbers();
         Console.WriteLine("Main Thread Finished");

         Console.ReadLine();

      }

      static void StartMethod()
      {
         DisplayNumbers();
         Console.WriteLine("Worker Thread Finished");
      }

      static void DisplayNumbers()
      {
         Thread thisThread = Thread.CurrentThread;
         string name = thisThread.Name;
         Console.WriteLine("Starting thread: " + name);
         Console.WriteLine(name + ": Current Culture = " + thisThread.CurrentCulture);
         for (int i=1 ; i<= 8*interval ; i++)
         {
            if (i%interval == 0)
               Console.WriteLine(name + ": count has reached " + i);
         }
      }
   }
}