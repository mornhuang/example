/*用递归算法编程求解。若一头小母牛，
从出生起第四个年头开始每年生一头
母牛，按此规律第n年有多少头母牛*/
#include <iostream.h>
p(int);
int n=1;
void main()
{cin>>n;
  cout<<p(n);
}
p(int n)
{
if (n>3)
return(p(n-1)+n/4);
return(1);

}