using System;
using System.Windows.Forms;

namespace Wrox.ProCSharp.AdvancedCSharp
{
   class MainEntryPoint
   {
      static void Main()
      {
         UserInputMonitor inputMonitor = new UserInputMonitor();
         MessageDisplayer inputProcessor = new MessageDisplayer(inputMonitor);
         ManagersStaffMonitor mortimer = new ManagersStaffMonitor(inputMonitor);
         inputMonitor.Run();
      }
   }

   class UserInputMonitor
   {
      public delegate void UserRequest(object sender, UserRequestEventArgs e);

      public event UserRequest OnUserRequest;

      public void Run()
      {
         bool finished = false;
         do
         {
            Console.WriteLine("Select preferred option:");
            Console.WriteLine("  Request advertisement - hit A then return");
            Console.WriteLine("  Request personal message from Mortimer - hit P then return");
            Console.WriteLine("  Exit - hit X then return");
            string response = Console.ReadLine();

            char responseChar = (response == "") ? ' ' : char.ToUpper(response[0]);
            switch(responseChar)
            {
               case 'X':
                  finished = true;
                  break;
               case 'A':
                  OnUserRequest(this, new UserRequestEventArgs(RequestType.AdRequest));
                  break;
               case 'P':
                  OnUserRequest(this, new UserRequestEventArgs(RequestType.PersonalMessageRequest));
                  break;
            }            
         }
         while (!finished);
      }
   }

   enum RequestType {AdRequest, PersonalMessageRequest}; 

   class UserRequestEventArgs : EventArgs
   {
      private RequestType request;

      public UserRequestEventArgs(RequestType request)
         :   base()
      {
         this.request = request;
      }

      public RequestType Request
      {
         get
         {
            return request;
         }
      }
   }

   class MessageDisplayer
   {
      public MessageDisplayer(UserInputMonitor monitor)
      {
         monitor.OnUserRequest += 
            new UserInputMonitor.UserRequest(UserRequestHandler);
      }

      protected void UserRequestHandler(object sender, UserRequestEventArgs e)
      {
         switch (e.Request)
         {
            case RequestType.AdRequest:
               Console.WriteLine("Mortimer Phones is better than anyone else because all our software is written in C#!\n");
               break;
            case RequestType.PersonalMessageRequest:
               Console.WriteLine("Today Mortimer issued the following statement:" +
                  "\n  Nevermore!\n");
               break;
         }
      }
   }

   class ManagersStaffMonitor
   {
      public ManagersStaffMonitor(UserInputMonitor monitor)
      {
         monitor.OnUserRequest += 
            new UserInputMonitor.UserRequest(UserRequestHandler);
      }

      protected void UserRequestHandler(object sender, UserRequestEventArgs e)
      {
         if (e.Request == RequestType.PersonalMessageRequest)
         {
            MessageBox.Show("Kaark!", "Mortimer says ...");
         }
      }
   }
}