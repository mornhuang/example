extern "C"
{
#include "stdio.h"
}

class Example
{
private:
	int value;
	static int minvalue;
public:
	friend class ExampleInit;

	Example()
	{
		value=minvalue;
	}

	static void Print()
	{
		printf("Min Value = %d\n",minvalue);
	}
};

int Example::minvalue=0;

class ExampleInit
{
private:
	static int initdone;
public:
	ExampleInit()
	{
		printf("Enter minimum value:");
		scanf("%i",&Example::minvalue);
	}
};

int ExampleInit::initdone=0;



int main()
{
ExampleInit e2;
	Example::Print();

	return 0;
}