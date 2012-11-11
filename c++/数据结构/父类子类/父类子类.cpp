# include <stdio.h>

class Parent
{
private:
	int private1,private2;
public:
	Parent(int p1,int p2)
	{
	   private1=p1;
	   private2=p2;
	}
	void assign(int p1,int p2)
	{
	   private1=p1;
	   private2=p2;
	}

	int inc1(){return ++private1;}
	int inc2(){return ++private2;}

	void display()
	{
	   printf("\nprivate1=%d private2=%d",private1,private2);
	}
};

class Derived:private Parent
{
private:
	int private3;
	Parent private4;
public:
	Derived(int p1,int p2,int p3,int p4,int p5)
		:Parent(p1,p2),private4(p3,p4)
	{
	   private3=p5;
	}
	
	void assign(int p1,int p2)
	{
		Parent::assign(p1,p2);
	}

	int inc1()
	{
		return Parent::inc1();
	}

	int inc2()
	{
	    return ++private3;
	}

	void display()
	{
		Parent::display();
		private4.Parent::display();
		printf("\nprivate3=%d\n\n",private3);
	}
};

int main()
{
    Derived d1(17,18,1,2,-5);

	d1.inc1();
	d1.display();
}