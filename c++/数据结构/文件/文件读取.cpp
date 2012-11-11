#include <iostream.h> 
#include <stdlib.h> 
#include <stdio.h>
#include <fstream.h> 

int main() 
{ 


     short i=1; 
     char buf[100]; 
     ifstream aa("aa.txt"); 

     while (1) { 
            
           cout << i++ << ':' ; 
           aa.seekg(0,ios::beg);//第二次循环到不了文件头，用aa.rdbuf可以显示其余的内容，但我不会按行处理其中的内容。 
           while(aa.getline(buf,100)) { 
               cout << buf << endl; 

           } 
           cout << "Please press any key to continue..."; 
           cin.get(); 
     } 

     return 0; 
} 
