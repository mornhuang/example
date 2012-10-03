#include <iostream> 
#include <list> 
using namespace std; 

int nWays = 0; 
void Walk(int stepsLeft, int stepsGo, list<int> steplist) 
{ 
 if(stepsLeft == stepsGo) 
   { 
     steplist.push_back(stepsGo); 
     nWays++; 
     cout <<"Solution "<<nWays<<" : "; 
     list<int>::const_iterator lstIter; 
     for(lstIter = steplist.begin(); lstIter != steplist.end(); lstIter++) 
       { 
         cout <<*lstIter<<" "; 
       } 
     cout <<endl; 
   } 
 else if(stepsLeft > stepsGo) 
   { 
     steplist.push_back(stepsGo); 
     for(int i = 1; i<=3; i++) 
       { 
         Walk(stepsLeft-stepsGo, i, steplist); 
       } 
   } 
} 
      
  
void main() 
{ 
 int i; 
 list<int> lstSteps; 
 for(i=1; i<=3; i++) 
   { 
     //lstSteps.pushback(i); 
     Walk(17, i, lstSteps); 
   } 
} 
   