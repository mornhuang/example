#include <iostream.h>
#include <iomanip.h>

class classA
{
public:
	classA(int nA)
	{
	    m_nA=nA;
		cout << " Invoking constructor of class classA\n ";
	}
	void show(const char* pszMsg=" ")
	{cout << pszMsg << m_nA << endl;}

protected:
	int m_nA;
};

class classB : public classA
{
public:
	classB(int nA,int nB)
		: classA(nA)
	{
	    m_nB=nB;
		cout << " Invoking constructor of class classB\n";
	}

	void show(const char* pszMsg = " ")
	{
	    cout << pszMsg << m_nA
			<< "," << m_nB << endl;
	}

protected:
	int m_nB;
};

class classC : public classB
{
public:
	classC(int nA,int nB,int nC)
		: classB(nA,nB)
	{
	    m_nC=nC;
		cout << " Invoking constructor of class classC\n ";
	}

	void show(const char* pszMsg=" ")
	{
	    cout << pszMsg << m_nA
			<< "," << m_nB
			<< "," << m_nC
			<< endl;
	}

protected:
	int m_nC;
};

main()
{
    classA objectA(1);
	objectA.show(" Object A stores ");
	cout << endl;
	classB objectB(10,20);
	objectB.show(" Object B stores ");
	cout << endl;
	classC objectC(100,200,300);
	objectC.show(" Object C stores ");

	return 0;
}