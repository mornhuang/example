#include <iostream.h>

class Complex
{
private:
	double real, imag;
public:
	Complex(double r=0.0,double i=0.0);
	void print();
	Complex operator +(Complex c);
	Complex operator -(Complex c);
};

Complex::Complex(double r,double i)
{
    real = r;
	imag = i;
}

Complex Complex::operator +(Complex c)
{
   Complex temp;
   temp.real=real+c.real;
   temp.imag=imag+c.imag;
   return temp;
}

Complex Complex::operator -(Complex c)
{
   Complex temp;
   temp.real=real-c.real;
   temp.imag=imag-c.imag;
   return temp;
}

void Complex::print()
{
    cout << "(" << real << "," << imag << ")";
}
int main()
{
Complex a(1.0,1.0),b(2.0,2.0),c,d;

c=a+b;
d=b+c-a;

cout << "c=";
c.print();
cout << "and";

cout << "d= ";
d.print();
cout << "\n";
}