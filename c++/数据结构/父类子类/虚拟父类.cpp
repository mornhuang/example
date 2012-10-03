#include <stdio.h>

class Parent
{
private:
	int private1,private2;
public:
	Parent(int p1,int p2)
	{
	   private1 = p1;
	   private2 = p2;
	}

	int inc1(){return ++private1;}
	int inc2(){return ++private2;}

	void display()
	{
	    printf("\nprivate1 = %d private2 = %d",
			private1,private2);
	}
};

class Derived1:virtual private Parent
{
private:
	int private3;
public:
    Derived1(int p1,int p2,int p3)
		:Parent(p1,p2)
	{
	    private3 = p3;
	}

	int inc1()
	{
		return Parent::inc1();
	}

    int inc3()
	{
	    return ++private3;
	}

	void display()
	{
		Parent::display();
		printf("\nprivate3 = %d",private3);
	}
};

class Derived2:virtual public Parent
{
private:
	int private4;
public:
	Derived2(int p1,int p2,int p4)
		:Parent(p1,p2)
	{
	    private4 = p4;
	}

	int inc1()
	{
		Parent::inc1();
		Parent::inc1();
		return Parent::inc1();
	}

	int inc4()
	{
	    return ++private4;
	}

	void display()
	{
		Parent::display();
		printf("\nprivate4 = %d",private4);
	}
};

class Derived12:private Derived1,public Derived2
{
private:
	int private12;
public:
	Derived12(int p11,int p12,int p13,
		int p21,int p22,int p23,int p)
		:Derived1(p11,p12,p13),
		Derived2(p21,p22,p23),
		Parent(p11,p21)
	{
	    private12 = p;
	}

	int inc1()
	{
		Parent::inc1();
		Parent::inc2();
		return Derived2::inc1();
	}

	int inc5()
	{
	    return ++private12;
	}

	void display()
	{
	    printf("\nDerived1::display()");
		Derived1::display();
		printf("\nDerived2::display()");
		Derived2::display();
		printf("\nprivate12 = %d",private12);
	}
};

int main()
{
    Derived12 d(10,20,30,40,50,60,70);

	d.display();
	printf("\n\n");
	d.inc1();
	d.Parent::inc1();
	d.Derived2::inc1();
	d.display();
}