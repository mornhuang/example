
#include <iostream.h>
#include <stdlib.h>

class parent
{
public:
	virtual void foobar(void) {}; 
};

class child1: public parent
	{
	public:
	virtual void foobar(void);
	};


extern "C"
{
parent *CreateObject(void);
}
