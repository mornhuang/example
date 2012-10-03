#include <iostream.h>
#include <iomanip.h>
#include <math.h>

const double INIT_SEED=13.17;
const double PI=4*atan(1);

class Random
{
public:
	Random(double fInitSeed = INIT_SEED)
	{
	    m_fSeed = fInitSeed;
		cout << "Initializing a class instance\n";
	}

	double getRandom();

protected:
	double m_fSeed;
	double cube(double x)
	{return x*x*x;}
	double fraction(double x)
	{return x - long(x);}
};

double Random::getRandom()
{
    m_fSeed = fraction(cube(PI + m_fSeed));
	return m_fSeed;
}

main()
{
    const int MAX = 5;
	Random RN1;
	Random RN2(47.0);
	Random RN3(0.13);

	cout << " Here is a sequence of random numbers: \n";
	for(int i=0; i<MAX; i++)
		cout << RN1.getRandom() << endl;
	cout << endl;

	cout << " Here is a second sequence of random number:\n ";
	for(i=0;i<MAX;i++)
		cout << RN2.getRandom() << endl;
	cout << endl;

	cout << " Here is a third sequence of random number:\n";
	for(i=0;i<MAX;i++)
		cout << RN3.getRandom() << endl;
	return 0;
}