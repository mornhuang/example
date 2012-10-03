#include <iostream.h>

class Complex
{
private:
	double real,imag;
public:
	void set(double r,double i=0.0);
	void print();
	Complex operator+(Complex c);
	Complex operator-(Complex c);
};

int main(void);

void Complex::set(double r,double i)
{
    real = r;
	imag = i;
}

void Complex::print()
{
    cout << "(" << real << "," << imag << " )";
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

int main(void)
{
    Complex a,b,c,d;

	a.set(1.0,1.0);
	b.set(2.0,2.0);
	c=a+b;
	d=b+c-a;

	cout << "c=";
	c.print();
	cout << "and ";

	cout << "d=";
	d.print();
	cout <<"\n";
}