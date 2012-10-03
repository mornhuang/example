#include<iostream.h>

class A
{
public:
	int procA()
	{return procA1()* procA2();}
	//这里返回两个函数的乘法
	//其自身的两个函数是4和5
	virtual int procA1()
	//如果去掉virtual结果就是20
	//也就不存在越界操作
	//也就没有多太性
	{return 4;}      
	int procA2()
	{return 5;}
};
class B:public A
{
public:
	 int procA1()
	{return 10;}
	 int procA2()
	 {return 1;}
};

main()
{
	B objB;
	cout << objB.procA();
	//越界操作结果是50
	return 0;
}