#include <fstream.h>
#include <iomanip.h>

enum Logical {FALSE,TRUE};

const int MAX_ELEMS=10;

class AbstractArray
{
public:
    AbstractArray(){}
	virtual void Store(double fVal,int nIndex)=0;
	virtual void Recall(double &fVal,int nIndex)=0;

	void Show(const char *pszMsg=" ",
		const int nNumElems=MAX_ELEMS,
		const int bOneLine=TRUE);
	//void Sort(int nNumElems);
};

class OrderdAbstractArray:public AbstractArray
//在抽象类下多加个类层次可以使程序更有弹性
{
public:
	void Sort(int nNumElems);
};

class MemArray:public OrderdAbstractArray
//class MemArray:public AbstractArray
{
public:
	MemArray(double fInitVal=0);
	double &operator[](int nIndex)
	{return m_fArray[nIndex];}
	virtual void Store(double fVal,int nIndex);
	virtual void Recall(double &fVal,int nIndex);

protected:
	double m_fArray[MAX_ELEMS];
};

class DiskArray:public OrderdAbstractArray
//class DiskArray:public AbstractArray
{
public:
	DiskArray(const char *pszFilename,
		int nMaxSize=MAX_ELEMS,
		double fInitVal=0);
	~DiskArray()
	{m_f.close();}
	virtual void Store(double fVal,int nIndex);
	virtual void Recall(double &fVal,int nIndex);

protected:
	fstream m_f;
	int m_nMaxSize;
};

void AbstractArray::Show(const char *pszMsg,
						 const int nNumElems,
						 const int bOneLine)
{
    double fVal;

	cout << pszMsg << endl;
	if(bOneLine)
	{
	    for(int i=0;i<nNumElems;i++)
		{
		    Recall(fVal,i);
			cout << fVal << ' ';
		}
		cout << endl;
	}
	else
	{
	    for(int i=0;i<nNumElems;i++)
		{
		    Recall(fVal,i);
			cout << fVal << endl;
		}
		cout << endl;
	}
}

void OrderdAbstractArray::Sort(int nNumElems)
//void AbstractArray::Sort(int nNumElems)
{
    int nOffset=nNumElems;
	double nElemI,nElemJ;
	Logical bSorted;

	if(nNumElems<2)
		return;

	do
	{
		nOffset=(nOffset*8)/11;
		nOffset=(nOffset<1)?1:nOffset;
		bSorted=TRUE;

		for(int i=0,j=nOffset;
		i<(nNumElems-nOffset);
		i++,j++)
		{
			Recall(nElemI,i);
			Recall(nElemJ,j);
			if(nElemI>nElemJ)
			{
				Store(nElemI,j);
				Store(nElemJ,i);
				bSorted=FALSE;
			}
		}
	}while(!bSorted||nOffset!=1);
}

MemArray::MemArray(double fInitVal)
{
	for(int i=0;i<MAX_ELEMS;i++)
		m_fArray[i]=fInitVal;
}

void MemArray::Store(double fVal,int nIndex)
{
	if(nIndex>=0&&nIndex<MAX_ELEMS)
		m_fArray[nIndex]=fVal;
}

void MemArray::Recall(double &fVal,int nIndex)
{
	if(nIndex>=0&&nIndex<MAX_ELEMS)
		fVal=m_fArray[nIndex];
}

DiskArray::DiskArray(const char *pszFilename,
					 int nMaxSize,double fInitVal)
{
	m_f.open(pszFilename,ios::in|ios::out|ios::binary);
	m_nMaxSize=nMaxSize;
	for(int i=0;i<m_nMaxSize;i++)
		m_f.write((const char*)&fInitVal,sizeof(fInitVal));
}

void DiskArray::Store(double fVal,int nIndex)
{
	if(nIndex>=0&&nIndex<m_nMaxSize)
	{
		m_f.seekg(nIndex * sizeof(fVal));
		m_f.write((const char*)&fVal,sizeof(fVal));
	}
}

void DiskArray::Recall(double &fVal,int nIndex)
{
	if(nIndex>=0&&nIndex<m_nMaxSize)
	{
		m_f.seekg(nIndex * sizeof(fVal));
		m_f.read((char*)&fVal,sizeof(fVal));
	}
}

main()
{
	double fArr[MAX_ELEMS]={33.4,54.1,98.2,47.5,15.6,
		81.2,78.7,36.7,63.3,83.9};

	MemArray Array;
	DiskArray VirtArray("ARRDATA.BAT");

	for(int i=0;i<MAX_ELEMS;i++)
		Array.Store(fArr[i],i);

	for(i=0;i<MAX_ELEMS;i++)
		VirtArray.Store(fArr[i],i);

	Array.Show(" Unsotred array is: ");
	Array.Sort(MAX_ELEMS);
	Array.Show(" Sorted array is: ");

	VirtArray.Show("Unsotred disk-based array is: ");
	VirtArray.Sort(MAX_ELEMS);
	VirtArray.Show("Sorted disk-based array is: ");


	return 0;
}