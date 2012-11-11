#include<iostream.h> 

int fun1(int *p,int n); 

void main() 
{int i,number1,y=10; 
cout<<"please input the number of a"<<endl; 
int *p=new int[10];
for(i=0;i<y;i++) 
cin>>p[i]; 
number1=fun1(p,y); 
cout<<"so we can get the new number of a"<<endl<<"max="<<number1<<endl; 
} 

int fun1(int * p,int n) 
{int i,max=0; 
for(i=0;i<n;i++) 
if(*(p+i)>max) 
max=*(p+i); 
return max; 
} 


