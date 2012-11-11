#include "child1.h"

void child1::foobar(void)
	{
	cout << "child2" << endl;
	}

parent *CreateObject(void)
	{
	child1 *arse = new child1;
	return((parent *)arse);
	}
