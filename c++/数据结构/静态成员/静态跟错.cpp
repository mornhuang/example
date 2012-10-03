#include <iostream.h>
#include <iomanip.h>

const int DUMMY_VAL=-32768;
const int MINIMUM_SIZE=10;

enum Logical{FALSE,TRUE};

class myArray
{
public:
	myArray(int nMaxSize=MINIMUM_SIZE,int nInitVal=0);
	~myArray();

	//declare static member function
	static Logical getAccessError();

	int &operator[](int nIndex);
	void show(int nNumElems,const char* pszMsg=" ");

protected:
	int *m_pnArray;
	int m_nMaxSize;
	int m_nDummy;

	//declare static data member
	static Logical m_bAccessError;
};

//initialize the static data member
Logical myArray::m_bAccessError=FALSE;

myArray::myArray(int nMaxSize,int nInitVal)
{
    m_nMaxSize=(nMaxSize<MINIMUM_SIZE)?
                     MINIMUM_SIZE : nMaxSize;
	m_pnArray=new int[m_nMaxSize];
	for(int i=0;i<m_nMaxSize;i++)
		m_pnArray[i]=nInitVal;
	m_nDummy=DUMMY_VAL;
}

myArray::~myArray()
{
    //delete array
	delete []m_pnArray;
}

Logical myArray::getAccessError()
{
    Logical bResult=m_bAccessError;
	m_bAccessError=FALSE;//reset access error
	return bResult;
}

int &myArray::operator [](int nIndex)
{
	if(nIndex >=0&&nIndex<m_nMaxSize)
		return m_pnArray[nIndex];
	else
	{
		m_bAccessError=TRUE;
	    return m_nDummy;
	}
}

void myArray::show(int nNumElems,const char* pszMsg)
{
    cout << pszMsg;
	for(int i=0;i<nNumElems;i++)
		cout << m_pnArray[i] << ' ';
	cout << endl;
}

main()
{
    const int SIZE1 = 15;
	const int SIZE2 = 20;
	const int SIZE3 = 30;
	int nNumElems;
	myArray Array1(SIZE1,1);
	myArray Array2(SIZE2,2);
	myArray Array3(SIZE3);
	for(int i=0;i<3;i++)
	{
	    switch(i)
		{
		case 0:
			nNumElems=SIZE1;
			break;

		case 1:
			nNumElems=SIZE2;
			break;

		case 2:
			nNumElems=SIZE3;
			break;
		}

		cout << " processing " << nNumElems << " array elements\n ";
		for(int i=0;i<nNumElems;i++)
			Array3[i]=Array1[i]+Array2[i];
		if(!myArray::getAccessError())
		{
		    Array1.show(nNumElems," Array 1 is:\n ");
			Array2.show(nNumElems," Array 2 is:\n ");
			Array3.show(nNumElems," Array 3 is:\n ");
		}
		else
			cout << " Last array operation had access error\n ";
	}
	return 0;
}