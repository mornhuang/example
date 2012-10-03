#include <iostream.h> 
void total(int n,int &a); 
void main() 
{ 
int n,num=0; 
cout<<"请输入你要走的台阶数："; 
cin>>n; 
total(n,num); 
cout<<"总共有"<<num<<"走法"<<endl; 
} 
void total(int n,int &a) 
{ 
int &num=a; 
if(n==0) num++; 
else 
{ 
if(n-3<0) 
{ 
n=n-2; 
if(n<0) {n=n+1;total(n,num);} 
else {total(n,num);total(n+1,num);} 
} 
else 
{ 
total(n-3,num); 
total(n-2,num); 
total(n-1,num); 
} 
} 
}
