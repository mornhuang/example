#include<iostream.h>

int get(int); 
void main() 
{ 
int i=12; 
int m=get(i); 
cout<<m<<endl; 
} 
int get(int m) 
{ 
if(m<10) 
 return m; 
int i,tmp; 
for(i=10,tmp=9;i<=m;i++) 
{ 
tmp++; 
if((tmp%10)==((tmp/10)%10)) 
tmp=(tmp/10+1)*10; 
} 
return tmp; 
} 