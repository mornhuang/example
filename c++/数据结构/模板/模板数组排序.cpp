#include <iostream.h>
#include <iomanip.h>
#include <string.h>

const int MIN_SIZE=30;

enum Logical{FALSE,TRUE};

template<class T>
class myArray
{
public:
	myArray(int nSize,T Initial);
	~myArray()
	{delete[] m_pArray;}
	T &operator[](int nIndex)
	{return m_pArray[nIndex];}
	void Show(const int nNumElems,
		const char *pszMsg=" ",
		const Logical bOneLine=TRUE);
	void Sort(int nNumElems);

protected:
	T *m_pArray;
	int m_nSize;
};

template<class T>
myArray< T >::myArray(int nSize,T InitVal)
{
	m_nSize=(nSize>1)? nSize:1;
	m_pArray=new T[m_nSize];
	for(int i=0;i<m_nSize;i++)
		m_pArray[i]=InitVal;
}

template<class T>
void myArray< T >::Show(const int nNumElems,
				   const char *pszMsg,
				   const Logical bOneLine)
{
	cout << pszMsg<<endl;
	if(bOneLine)
	{
		for(int i=0;i<nNumElems;i++)
			cout << m_pArray[i] << ' ';
		cout << endl;
	}
	else
	{
		for(int i=0;i<nNumElems;i++)
			cout << m_pArray[i]<<endl;
		cout << endl;
	}
}

template<class T>
void myArray< T >::Sort(int nNumElems)
{
	int nOffset=nNumElems;
	Logical bSorted;

	//check argument of parameter nNumbers
	if(nNumElems<2)
		return;

	do
	{
		nOffset=(nOffset*8)/11;
		nOffset=(nOffset<1)?1:nOffset;
		bSorted=TRUE;//set sorted flag
		//compare elements
		for(int i=0,j=nOffset;
		        i < (nNumElems-nOffset);
				i++,j++)
		{
			if(m_pArray[i]>m_pArray[j])
			{
				//swap elements
				T nSwap=m_pArray[i];
				m_pArray[i]=m_pArray[j];
				m_pArray[j]=nSwap;
				bSorted=FALSE;//clear sorted flag
			}
		}
	}while(!bSorted||nOffset!=1);
}

class myString
{
public:
	myString(int nSize=MIN_SIZE)
	{m_pszString=new char[m_nSize=nSize];}
	myString(myString &String);
	myString(const char *pszString);
	myString(const char cChar);
	~myString()
	{delete[]m_pszString;}
	int getLen()
	{return strlen(m_pszString);}
	int getMaxLen()
	{return m_nSize;}
	myString &operator=(myString &aString);
	myString& operator=(const char *pszString);
	myString &operator=(const char cChar);
	friend int operator > (myString &aString1,myString &aString2)
	{return(strcmp(aString1.m_pszString,aString2.m_pszString) > 0)?1:0;}
    friend ostream &operator << (ostream &os,myString &aString);

protected:
	char *m_pszString;
	int m_nSize;
};

myString::myString(myString &aString)
{
	m_pszString=new char[m_nSize=aString.m_nSize];
	strcpy(m_pszString,aString.m_pszString);
}

myString::myString(const char *pszString)
{
	m_pszString=new char[m_nSize=strlen(pszString)+1];
	strcpy(m_pszString,pszString);
}

myString::myString(const char cChar)
{
	m_pszString=new char[m_nSize=MIN_SIZE];
	m_pszString[0]=cChar;
	m_pszString[1]='\0';
}

myString &myString::operator=(myString &aString)
{
	//source string small enough to copy?
	if(strlen(aString.m_pszString)<unsigned(m_nSize))
		strcpy(m_pszString,aString.m_pszString);
	else
		//copy first m_nSize-1 characters
		strncpy(m_pszString,aString.m_pszString,m_nSize-1);
	return *this;
}

myString &myString::operator=(const char *pszString)
{
	//source string small enough to copy?
	if(strlen(pszString)<unsigned(m_nSize))
		strcpy(m_pszString,pszString);
	else
		//copy first m_nSize-1 characters
		strncpy(m_pszString,pszString,m_nSize-1);
	return *this;
}

myString &myString::operator=(const char cChar)
{
	if(m_nSize > 1)
	{
		m_pszString[0]=cChar;
		m_pszString[1]='\0';
	}
    return *this;
}

ostream &operator << (ostream &os,myString &aString)
{
	os << aString.m_pszString;
	return os;
}

main()
{
	const int MAX_ELEMS=10;
	int nArr[MAX_ELEMS]={89,34,32,47,15,81,78,36,63,83};
	int cArr[MAX_ELEMS]={'C','W','r','Y','k','J','X','Z','y','s'};
	myArray<int> IntegerArray(MAX_ELEMS,0);
	myArray<char> CharArray(MAX_ELEMS,' ');
	myArray< myString > StringArray(MAX_ELEMS," ");

	for(int i=0;i<MAX_ELEMS;i++)
		IntegerArray[i]=nArr[i];
	for(i=0;i<MAX_ELEMS;i++)
		CharArray[i]=cArr[i];
	StringArray[0]="London ";
	StringArray[1]="Paris ";
	StringArray[2]="Madrid ";
	StringArray[3]="Rome ";
	StringArray[4]="Athens ";
	StringArray[5]="Bern ";
	StringArray[6]="Lisbon ";
	StringArray[7]="Warsaw ";
	StringArray[8]="Berlin ";
	StringArray[9]="Dublin ";

	IntegerArray.Show(MAX_ELEMS,"Unsorted array is: ");
	IntegerArray.Sort(MAX_ELEMS);
	IntegerArray.Show(MAX_ELEMS,"Sorted array is: ");
	cout << "\n\n" ;

	CharArray.Show(MAX_ELEMS,"Unsorted array is: ");
	CharArray.Sort(MAX_ELEMS);
	CharArray.Show(MAX_ELEMS,"Sorted array is: ");
	cout << "\n\n";

	StringArray.Show(MAX_ELEMS,"Unsorted array is: ");
	StringArray.Sort(MAX_ELEMS);
	StringArray.Show(MAX_ELEMS,"Sorted array is: ");

	return 0;
}