#include <iostream.h>
#include <iomanip.h>
#include <math.h>

class InputReal
{
public:
	double getReal(const char *pszMsg);

protected:
	void prompt(const char *pszMsg);
	virtual double getInput();
};

class InputPositiveReal : public InputReal
{
protected:
	virtual double getInput();
};

double InputReal::getReal(const char *pszMsg)
{
    prompt(pszMsg);
	return getInput();
}

void InputReal::prompt(const char *pszMsg)
{
    cout << pszMsg;
}

double InputReal::getInput()
{
    double x;
	cin >> x;
	return x;
}

double InputPositiveReal::getInput()
{
    double x;
	do
	{
	    cin >> x;
		if(x<=0.0)
			cout << " Enter a positive value : ";
	}while(x<=0.0);
	return x;
}

main()
{
    InputPositiveReal Real;
	double x,y;
	
    x=Real.getReal(" Enter distance(feet) : ");
	y=Real.getReal(" Enter elapsed time(sec) : ");
	cout << " Speed = " << (x/y) << " ft/sec\n ";

	return 0;
}