/*#include <iostream>
#include <vector>
using namespace std;

void main()
{
vector<int> vecobj(10,5);
vector<int>::iterator iter=vecobj.begin();
vecobj.insert(iter,6);
//for(;iter!=vecobj.end();iter++)
for(iter=vecobj.begin();iter!=vecobj.end();iter++)
{
cout<<*iter<<endl;
}
}*/
#pragma warning(disable : 4786)

#include <iostream>

#include <vector>

using namespace std;

 

int main (void) 

{

vector<int> nvec(10,7);

vector<int>::iterator it=nvec.begin();

for(;it!=nvec.end();it++)

{


if(*it==7) nvec.erase(it);
//nvec.remove(7);
}


for(it=nvec.begin();it!=nvec.end();it++)

{

cout<<*it<<endl;

}

return 0;

}
//在初始化了迭代子后，如果容器有删除元素操作时，迭代子可能失效