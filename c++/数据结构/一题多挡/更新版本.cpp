#include<iostream.h>
#include<iomanip.h>

enum Logical {FALSE,TRUE};

const int ALL_ARRAY=-1;
const int NOT_FOUND=-1;
const int BAD_VALUE=-32768;

class myArray
{
public:
	myArray(int nMaxElems,int nInitVal=0);
	~myArray();
	Logical Recall(int &nVal,int nInitVal);
	Logical Store(int nVal,int nIndex);
	void Show(const char* pszMag=" ",
		      const int nNumElems=ALL_ARRAY,
			  const Logical bOneLine=TRUE);
	void Sort();
	int Search(int nSearchVal,int nFirst=0);

protected:
	int m_nMaxElems;
	int m_nWorkSize;
	Logical m_bIsSorted;
	int *m_pnArray;

	int LinearSearch(int nSearchVal,int nFirst=0);
	int BinarySearch(int nSearchVal,int nFirst=0);
};

myArray::myArray(int nMaxElems,int nInitVal)
{
	m_nMaxElems=(nMaxElems<1)?1:nMaxElems;
	m_nWorkSize=0;
	m_bIsSorted=FALSE;
	m_pnArray=new int[m_nMaxElems];
	for(int i=0;i<m_nMaxElems;i++)
		m_pnArray[i ]=nInitVal;
}

myArray::~myArray()
{
	delete []m_pnArray;
}

Logical myArray::Recall(int &nVal,int nIndex)
{
	if(nIndex>=0&&nIndex<m_nWorkSize)
	{
		nVal=m_pnArray[nIndex];
		return TRUE;
	}
	else
		return FALSE;
}

Logical myArray::Store(int nVal,int nIndex)
{
	if(nIndex<0||nIndex>=m_nMaxElems)
		return FALSE;

	if(nIndex>=m_nWorkSize)
		m_nWorkSize=nIndex+1;
	m_pnArray[nIndex]=nVal;
	m_bIsSorted=FALSE;
	return TRUE;
}

void myArray::Show(const char*pszMsg,
				   const int nNumElems,
				   const Logical bOneLine)
{
	int nCount;

	cout<<pszMsg<<endl;
	nCount=(nNumElems==ALL_ARRAY)?m_nWorkSize:nNumElems;
	if(bOneLine)
	{
		for(int i=0;i<nCount;i++)
			cout<<m_pnArray[i ]<<' ';
		cout<<endl;
	}
	else
	{
		for(int i=0;i<nCount;i++)
			cout<<m_pnArray[i ]<<' ';
		cout<<endl;
	}
}

void myArray::Sort()
{
	if(m_bIsSorted)
		return; //exit if array is already sorted

	//sort the array
	int nOffset=m_nWorkSize;
	int nElemI,nElemJ;

	do
	{
		nOffset=(nOffset*8)/11;
		nOffset=(nOffset<1)?1:nOffset;
		m_bIsSorted=TRUE;  //set sorted flag;
		//compare elements
		for(int i=0,j=nOffset;
			i<(m_nWorkSize-nOffset);
			i++,j++)
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

int myArray::Search(int nSearchVal,int nFirst)
{
	return(m_bIsSorted)?BinarySearch(nSearchVal,nFirst):
						LinearSearch(nSearchVal,nFirst);
}

int myArray::BinarySearch(int nSearchVal,int nFirst)
{
	int nMedian;
	int nLast=m_nWorkSize-1;
	nFirst=(nFirst<0||nFirst>=m_nWorkSize)?
		0:nFirst;
	do
	{
		nMedian=(nFirst+nLast)/2;
		if(nSearchVal<m_pnArray[nMedian])
			nLast=nMedian-1;
		else
			nFirst=nMedian+1;
	}while(!(nSearchVal==m_pnArray[nMedian]||nFirst>nLast));

	return(nSearchVal==m_pnArray[nMedian])?
		nMedian:NOT_FOUND;
}

int myArray::LinearSearch(int nSearchVal,int nFirst)
{
	for(int i=nFirst;i<m_nWorkSize;i++)
		if(m_pnArray[i ]==nSearchVal)
			break;
		return(i<m_nWorkSize)?i:NOT_FOUND;
}

main()
{
	const int MAX_ELEMS=10;
	int nArr[MAX_ELEMS]={33,54,98,47,15,
		81,78,36,63,83};
	myArray anArray(10);
	int k;

	for(int i=0;i<MAX_ELEMS;i++)
		anArray.Store(nArr[i ],i);
	anArray.Show("Unordered array is:");

	for(i=MAX_ELEMS-1;i>=0;i--)
	{
		cout<<"Searching for"<<nArr[i ]<<":";
		k=anArray.Search(nArr[i ]);
		if(k!=NOT_FOUND)
			cout<<"found at index"<<k<<endl;
		else
			cout<<"no match found"<<endl;
	}
	cout<<endl;

	anArray.Sort();

	anArray.Show("Ordered array is:");

	for(i=MAX_ELEMS-1;i>=0;i--)
	{
		cout<<"Searching for"<<nArr[i ]<<":";
		k=anArray.Search(nArr[i ]);
		if(k!=NOT_FOUND)
			cout<<"found at index"<<k<<endl;
		else
			cout<<"no match found"<<endl;
	}
	return 0;
}