#include <iostream.h>
#include <iomanip.h>
#include <string.h>

enum Logical{FALSE,TRUE};

template< class T,int nSize >
class myArray
{
public:
	myArray(T InitVal);
	T &operator[](int nIndex)
	{return m_Array[nIndex];}
	void Show(const int nNumElems,
		const char *pszMsg=" ",
		const Logical bOneLine=TRUE);

protected:
	T m_Array[nSize];
	int m_nSize;
};

template< class T,int nSize >
class mySortedArray:public myArray<T,nSize>
{
public:
	mySortedArray(T InitVal)
		:myArray< T,nSize >(InitVal){}
	void Sort(int nNumElems);
};

template< class T,int nSize >
myArray< T,nSize >::myArray(T InitVal)
{
	m_nSize=nSize;
	for(int i=0;i<m_nSize;i++)
		m_Array[i]=InitVal;
}

template< class T,int nSize >
void myArray< T,nSize >::Show(const int nNumElems,
							  const char *pszMsg,
							  const Logical bOneLine)
{
	cout << pszMsg << endl;
	if(bOneLine)
	{
		for(int i=0;i<nNumElems;i++)
			cout << m_Array[i] << ' ';
		cout << endl;
	}
	else
	{
		for(int i=0;i<nNumElems;i++)
			cout << m_Array[i] << endl;
		cout << endl;
	}
}

template< class T, int nSize >
void mySortedArray< T,nSize > ::Sort(int nNumElems)
{
	int nOffset = nNumElems;
	Logical bSorted;

	//check argument of parameter nNumbers
	if(nNumElems < 2)
		return;

	do
	{
		nOffset=(nOffset*8)/11;
		nOffset=(nOffset<1)?1:nOffset;
		bSorted=TRUE;//set sorted flag
		//compare elements
		for(int i=0,j=nOffset;
		   i<(nNumElems-nOffset);
		   i++,j++)
		{
		    if(m_Array[i]>m_Array[j])
			{
				T nSwap=m_Array[i];
				m_Array[i]=m_Array[j];
				m_Array[j]=nSwap;
				bSorted=FALSE;//clear sorted flag
			}
		}
	}while(!bSorted||nOffset !=1);
}

main()
{
	const int MAX_ELEMS=10;
	int nArr[MAX_ELEMS]={89,34,32,47,15,81,78,36,63,83};
	int cArr[MAX_ELEMS]={'C','W','r','Y','k','J','X','Z','y','s'};

	mySortedArray<int, MAX_ELEMS>IntegerArray(0);
	mySortedArray<char, MAX_ELEMS>CharArray(' ');

	//assign integers to elements of array IntegerArray
	for(int i=0;i<MAX_ELEMS;i++)
		IntegerArray[i]=nArr[i];
	//assign characters to elements of array CharArray
	for(i=0;i<MAX_ELEMS;i++)
		CharArray[i]=cArr[i];

	//test array IntegerArray
	IntegerArray.Show(MAX_ELEMS,"Unsorted array is: ");
	IntegerArray.Sort(MAX_ELEMS);
	IntegerArray.Show(MAX_ELEMS,"Sorted array is: ");
	cout << "\n\n ";

	//test array CharArray
	CharArray.Show(MAX_ELEMS, "Unsorted array is: ");
	CharArray.Sort(MAX_ELEMS);
	CharArray.Show(MAX_ELEMS, "Sorted array is: ");

	cout << "\n\n";

	return 0;
}