#include<iostream.h> 

int number1(int (*p)[4]); 
int number2(int (*p)[4]); 

void main() 
{int a[4][4]; 
cout<<"please input the int"<<endl; 
for(int x=0;x<4;x++) 
for(int y=0;y<4;y++) 
cin>>a[x][y]; 
int b=number1(a); 
int c=number2(a); 
cout<<"第一条对角线的和为："<<" "<<b<<endl; 
cout<<"第二条对角线的和为："<<" "<<c<<endl; 
} 

int number1(int (*p)[4]) 
{int k=0,i,j; 
for(i=0,j=0;i<4,j<4;i++,j++) 
k+=(*(p+i))[j]; 
return k; 
} 

int number2(int (*p)[4]) 
{int k=0,i,j; 
for(i=0,j=3;i<4,j>=0;i++,j--) 
k+=(*(p+i))[j]; 
return k; 
} 