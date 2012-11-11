#include <iostream.h>
#include <iomanip.h>

enum Logical{FALSE,TRUE};

const int ALL_ARRAY=-1;
const int NOT_FOUND=-1;
const int BAD_VALUE=-32768;

class myBasicArray
{
public:
	myBasicArray(int nMaxElems,int nInitVal=0);
	virtual ~myBasicArray()
	{delete []m_pnArray;}
	virtual Logical Recall(int &nVal,int nIndex);
	virtual Logical Store(int nVal,int nIndex);

protected:
	int m_nMaxElems;
	int m_nWorkSize;
	int *m_pnArray;
};

class myArray : public myBasicArray
{
public:
	myArray(int nMaxElems,int nInitVal=0):
	  myBasicArray(nMaxElems,nInitVal){}
	virtual ~myArray()
	{}

	void Show(const char *pszMag=" ",
		const int nNumElems=ALL_ARRAY,
		const Logical bOneLine=TRUE);
	int LinearSearch(int nSearchVal,int nFirst=0);
};

class myOrderedArray : public myArray
{
public:
	myOrderedArray(int nMaxElems,int nInitVal=0):
		myArray(nMaxElems,nInitVal)
	  {m_bIsSorted=FALSE;}
	virtual ~myOrderedArray()
	{}
	virtual Logical Store(int nVal,int nIndex);
	void Sort();
	int SmArtSearch(int nSearchVal,int nFirst=0);
	int BinarySearch(int nSearchVal,int nFirst=0);
protected:
	Logical m_bIsSorted;
};

myBasicArray::myBasicArray(int nMaxElems,int nInitVal)
{
    m_nMaxElems=(nMaxElems<1)?1:nMaxElems;
	m_nWorkSize=0;
	m_pnArray=new int[m_nMaxElems];
	for(int i=0;i<m_nMaxElems;i++)
		m_pnArray[i ]=nInitVal;
}

Logical myBasicArray::Recall(int &nVal,int nIndex)
{
    if(nIndex >=0&&nIndex<m_nWorkSize)
	{
	    nVal=m_pnArray[nIndex];
		return TRUE;
	}
	else
		return FALSE;
}

Logical myBasicArray::Store(int nVal,int nIndex)
{
    if(nIndex<0||nIndex>=m_nMaxElems)
		return FALSE;

	if(nIndex >= m_nWorkSize)
		m_nWorkSize=nIndex+1;
	m_pnArray[nIndex]=nVal;
	return TRUE;
}

void myArray::Show(const char *pszMag,
				   const int nNumElems,
				   const Logical bOneLine)
{
    int nCount;

	cout << pszMag << endl;
	nCount=(nNumElems==ALL_ARRAY)?m_nWorkSize:nNumElems;
	if(bOneLine)
	{
	    for(int i=0;i<nCount;i++)
			cout << m_pnArray[i ] << ' ';
		cout << endl;
	}
}

int myArray::LinearSearch(int nSearchVal,int nFirst)
{
    for(int i=nFirst;i<m_nWorkSize;i++)
		if(m_pnArray[i ]==nSearchVal)
			break;
		return(i<m_nWorkSize)?i:NOT_FOUND;
}

Logical myOrderedArray::Store(int nVal,int nIndex)
{
    Logical bResult=myBasicArray::Store(nVal,nIndex);

	if(bResult)
		m_bIsSorted=FALSE;

	return bResult;
}

void myOrderedArray::Sort()
{
    if(m_bIsSorted)
		return;

	int nOffset=m_nWorkSize;
	int nElemI,nElemJ;

	do
	{
	    nOffset=(nOffset * 8)/11;
		nOffset=(nOffset<1)?1:nOffset;
		m_bIsSorted=TRUE;

		for(int i=0, j=nOffset;i<(m_nWorkSize-nOffset);i++,j++)
		{
		    nElemI=m_pnArray[i ];
			nElemJ=m_pnArray[j];
			if(nElemI>nElemJ)
			{
			    m_pnArray[i ]=nElemJ;
				m_pnArray[j]=nElemI;
				m_bIsSorted=FALSE;
			}
		}
	}while(!m_bIsSorted||nOffset!=1);
}

int myOrderedArray::SmArtSearch(int nSearchVal,int nFirst)
{
    return(m_bIsSorted)?BinarySearch(nSearchVal,nFirst):LinearSearch(nSearchVal,nFirst);
}

int myOrderedArray::BinarySearch(int nSearchVal,int nFirst)
{
    int nMedian;
	int nLast=m_nWorkSize-1;

	nFirst=(nFirst<0||nFirst>=m_nWorkSize)?0:nFirst;
	do
	{
	    nMedian=(nFirst+nLast)/2;
		if(nSearchVal<m_pnArray[nMedian])
			nLast=nMedian-1;
		else
			nFirst=nMedian+1;
	}while(!(nSearchVal==m_pnArray[nMedian]||nFirst>nLast));
    return(nSearchVal==m_pnArray[nMedian])?nMedian:NOT_FOUND;
}

main()
{
    const int MAX_ELEMS=10;
	int nArr[MAX_ELEMS]={33,54,98,47,15,
		81,78,36,63,83};
	myOrderedArray anArray(10);
	int k;

	for(int i=0;i<MAX_ELEMS;i++)
		anArray.Store(nArr[i ],i);

	anArray.Show(" Unordered array is: ");

	for(i=MAX_ELEMS-1;i>=0;i--)
	{
	    cout << " Searching for " << nArr[i ] << ": ";
		k=anArray.SmArtSearch(nArr[i ]);
		if(k!=NOT_FOUND)
			cout << " found at index " << k << endl;
		else
			cout << " no match found " << endl;
	}
	cout << endl;

	anArray.Sort();
	anArray.Show( " Ordered array is : ");

	for(i=MAX_ELEMS-1;i>=0;i--)
	{
	    cout << " Searching for " << nArr[i ] << ": ";
		k=anArray.SmArtSearch(nArr[i ]);
		if(k!=NOT_FOUND)
			cout << " found at index " << k << endl;
		else
			cout << " no match found " << endl;
	}
    return 0;
}