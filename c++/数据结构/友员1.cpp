#include <iostream.h>

class Integer
{
private:
	int value;
public:
	Integer(int i=0)
	{
	   value=i;
	}
	void print();
	friend Integer operator +(Integer i1,Integer i2);
};
	Integer operator +(Integer i1,Integer i2)
	{
	  Integer temp =i1.value + i2.value;
	  return temp;
	}

	void Integer::print()
	{
	  cout << value;
	}
int main()
{
    Integer i1=10;
	Integer i2=i1+5;
    Integer i3=3+i2;
	cout << "i2 = ";
	i2.print();
	cout << "\n" << "i3 = ";
	i3.print();
	cout << endl;
}