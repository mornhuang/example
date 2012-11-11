#include <iostream>
using namespace std;
typedef void(* menu_fcn)();
menu_fcn command[4];
void main()
{
   extern void assign_functions();
   extern void funtion_1();
   extern void funtion_2();
   extern void funtion_3();
   extern void funtion_4();

   int choice;
   
   void assing_functions();

   do
   {
     cout << "\nMenu item 1-->";
     cout << "\nMenu item 2-->";
     cout << "\nMenu item 3-->";
     cout << "\nMenu item 4-->";
     cout << "\nMenu item 5(Exit program-->";
     cout << "\n\nEnter your choic:";
     cin >>choice;
     if(choice >= 1 && choice <= 4)
         command[choice-1];
   }while (choice !=5);
  
}
void function_1()
{
     cout << "\nActivated menu item 1. \n";
}

void function_2()
{
     cout << "\nActivated menu item 2. \n";
}

void function_3()
{
     cout << "\nActivated menu item 3. \n";
}

void function_4()
{
     cout << "\nActivated menu item 4. \n";
}

void assign_functions()
{
     command[0]=function_1;
     command[1]=function_2;
     command[3]=function_3;
     command[4]=function_4;
}