#include <iostream.h>
#include <iomanip.h>

const int MINIMUM_SIZE=10;

class myArray
{
public:
	myArray(int nMaxSize=MINIMUM_SIZE,int nInitVal=0);
	myArray(myArray& anArray);
	~myArray();

	int& operator[](int nIndex)
	{return m_pnArray[nIndex];}
	void show(int nNumElems,const char* pszMsg=" ");

protected:
	int * m_pnArray;
	int m_nMaxSize;
};

myArray::myArray(int nMaxSize,int nInitVal)
{
    m_nMaxSize=(nMaxSize < MINIMUM_SIZE)?
                           MINIMUM_SIZE : nMaxSize;
	m_pnArray = new int[m_nMaxSize];
	for(int i=0;i<m_nMaxSize;i++)
		m_pnArray[i]=nInitVal;
	cout << " Creating new class instance\n";
}

myArray::myArray(myArray &anArray)
{
    m_nMaxSize = anArray.m_nMaxSize;
	m_pnArray = new int[m_nMaxSize];
	for(int i=0;i<m_nMaxSize;i++)
	  m_pnArray[i] = anArray.m_pnArray[i];
	cout << " Creating new class instance using copy constructor\n ";
}

myArray::~myArray()
{
    //delete array
	delete []m_pnArray;
	cout << "Deleting a class instance\n";
}

void myArray::show(int nNumElems,const char *pszMsg)
{
    cout << pszMsg;
	for(int i=0; i<nNumElems;i++)
		cout << m_pnArray[i] << ' ';
	cout << endl;
}

main()
{
    int MAX;
	cin >> MAX;
	myArray Array1(MAX,1);
	myArray Array2(Array1);
	myArray Array3(MAX);

	for(int i=0; i<MAX;i++)
	{
	    Array2[i] *=(2 + i);
		Array3[i] = Array1[i] + Array2[i];
	}

	Array1.show(MAX," Array 1 is :\n"); cout << endl;
	Array2.show(MAX," Array 2 is :\n"); cout << endl;
	Array3.show(MAX," Array 3 is :\n"); cout << endl;

	return 0;
}