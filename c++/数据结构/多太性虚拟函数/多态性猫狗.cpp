#include <stdio.h>
#include <string.h>
#include <stdlib.h>

class Animal
{
protected:
	char *name;

public:
	Animal()
	{name=NULL;}

	Animal(char *n)
	{
		name = new char[strlen(n)+1];
		strcpy(name, n);
	}

	~Animal()
	{delete name;}

	virtual void WhoAmI()
	{printf("generic animal ");}
};

class Cat:public Animal
{
public:
	Cat()
		:Animal()
	{}

	Cat(char *n)
		:Animal(n)
	{}

	virtual void WhoAmI()
	{
		printf("I am a cat name %s.\n",name);
	}
};

class Dog:public Animal
{
public:
	Dog()
		:Animal()
	{}

	Dog(char *n)
		:Animal(n)
	{}

	virtual void WhoAmI()
	{
		printf("I am a dog name %s.\n",name);
	}
};

class Zoo
{
private:
	unsigned int maxanimals;
	unsigned int numanimals;
	Animal **residents;

public:
	Zoo(unsigned int max)
	{
		maxanimals=max;
		numanimals=0;

		residents=new Animal *[maxanimals];

		for(int i=0;i<maxanimals;++i)
			residents[i]=NULL;
	}

	~Zoo()
	{delete residents;}

	unsigned int Accept(Animal *d);

	Animal *Release(unsigned int pen);

	void ListAnimals();
};

unsigned int Zoo::Accept(Animal *d)
{
	if(numanimals==maxanimals)
		return 0;
	++numanimals;

	int i=0;
	while(residents[i]!=NULL)
		++i;

	residents[i]=d;

	return i+1;

}

Animal *Zoo::Release(unsigned int pen)
{
	if(pen>maxanimals)
		return NULL;
	--pen;

	if(residents[pen]!=NULL)
	{
		Animal *temp=residents[pen];
		residents[pen]=NULL;
		--numanimals;
		return temp;
	}
	else
		return NULL;
}

void Zoo::ListAnimals()
{
	if(numanimals>0)
		for(int i=0;i<maxanimals;++i)
			if(residents[i]!=NULL)
			{
				printf("The dog is pen%d says: ",i+1);
				residents[i]->WhoAmI();
			}
}

Dog d1("Rover");
Dog d2("Spot");
Dog d3("Chip");
Dog d4("Buddy");
Dog d5("Butch");

Cat c1("Tinkerbell");
Cat c2("Inky");
Cat c3("Fluffy");
Cat c4("Princess");
Cat c5("Sylvester");

int main()
{
	Zoo z(20);

	z.Accept(&d1);

	unsigned int c2pen=z.Accept(&c2);

	z.Accept(&d3);
	z.Accept(&c1);

	unsigned int d4pen=z.Accept(&d4);

	z.Accept(&d5);
	z.Accept(&c5);
	z.Release(c2pen);

	z.Accept(&c4);
	z.Accept(&c3);
	z.Release(d4pen);

	z.Accept(&d2);

	z.ListAnimals();
}