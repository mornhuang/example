#include<iostream.h> 

void count(int *b,int n); 

void main() 
{int a[10],i; 
cout<<"please input the number of a"<<endl; 
int *p=new int[10];
for(i=0;i<10;i++) 
cin>>p[i]; 
cout<<"we can get a new round after the count"<<endl; 
count(p,10); 
for(i=0;i<10;i++) 
cout<<p[i]<<" "; 
} 


void count(int *b,int n) 
{int i,j,t; 
for(i=0;i<(n-1)/2;i++) 
{j=n-1-i; 
t=b[i]; 
b[i]=b[j]; 
b[j]=t; 
} 
} 