#include<iostream.h>
#include<time.h>
inline int comp1(int a, int b){return (a>b)?a:b;}
int comp2(int a, int b){return (a>b)?a:b;}

void main()
{
double t1,t2;
int m=0,n=0;
t1=(double)clock();
for(m=0;m<10000;m++)
  for(n=0;n<10000;n++)
    comp1(m,n);
  t2=(double)clock();
cout<<"调用内联函数运行时间为:\t"<<(t2-t1)/CLK_TCK<<"s\n";

  m=0;n=0;
t1=(double)clock();
for(m=0;m<10000;m++)
  for(n=0;n<10000;n++)
    comp2(m,n);
  t2=(double)clock();
cout<<"调用外部函数运行时间为:\t"<<(t2-t1)/CLK_TCK<<"s\n";

  m=0;n=0;
t1=(double)clock();
for(m=0;m<10000;m++)
  for(n=0;n<10000;n++)
    (m>n)?m:n;
  t2=(double)clock();
cout<<"直接运行时间为:\t"<<(t2-t1)/CLK_TCK<<"s\n";
}