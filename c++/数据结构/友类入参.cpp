#include <iostream.h>
#include <iomanip.h>

const int MIN_ELEMS=2;
const int MIN_ROWS=2;
const int MIN_COLS=2;

enum Logical{FALSE,TRUE};

class myArray
{
friend class myMatrix;

public:
	myArray(int nMaxElems=MIN_ELEMS);
	~myArray()
	{delete[]m_pfArray;}
	int getNumElems()
	{return m_nMaxElems;}
	Logical Store(double fVal,int nIndex);
	Logical Recall(double &fVal,int nIndex);

	void Show(const int nNumElems,
		const char *pszMsg=" ",
		const Logical bOneLine=TRUE);
protected:
	int m_nMaxElems;
	double *m_pfArray;
};

class myMatrix
{
public:
	myMatrix(int nMaxRows=MIN_ROWS,
		int nMaxCols=MIN_COLS);
	~myMatrix()
	{delete[]m_pfMatrix;}
	int getNumRows()
	{return m_nMaxRows;}
	int getNumCols()
	{return m_nMaxCols;}
	Logical Store(double fVal,int nRow,int nCol);
	Logical Recall(double &fVal,int nRow,int nCol);

	void Show(const int nRows,int nCols,
		const char *pszMsg=" ");
	myMatrix &CopyToRow(myArray &anArray,int nRow);

protected:
	int m_nMaxRows;
	int m_nMaxCols;
	double *m_pfMatrix;
};

myArray::myArray(int nMaxElems)
{
	m_nMaxElems=(nMaxElems<MIN_ELEMS)?MIN_ELEMS:nMaxElems;
	m_pfArray=new double[nMaxElems];
	for(int i=0;i<m_nMaxElems;i++)
		m_pfArray[i]=0.0;
}

Logical myArray::Store(double fVal,int nIndex)
{
	if(nIndex>=0&&nIndex<m_nMaxElems)
	{
		m_pfArray[nIndex]=fVal;
		return TRUE;
	}
	else
		return FALSE;
}

Logical myArray::Recall(double &fVal,int nIndex)
{
	if(nIndex>=0&&nIndex<m_nMaxElems)
	{
		fVal=m_pfArray[nIndex];
		return TRUE;
	}
	else
		return FALSE;
}

void myArray::Show(const int nNumElems,
				   const char *pszMsg,
				   const Logical bOneLine)
{
	cout << pszMsg << endl;
	if(bOneLine)
	{
		for(int i=0;i<nNumElems;i++)
			cout << m_pfArray[i] <<' ';
		cout << endl;
	}
	else
	{
		for(int i=0;i<nNumElems;i++)
			cout << m_pfArray[i] << endl;
		cout << endl;
	}
}

myMatrix::myMatrix(int nMaxRows,int nMaxCols)
{
	m_nMaxRows=(nMaxRows<MIN_ROWS)?MIN_ROWS:nMaxRows;
	m_nMaxCols=(nMaxCols<MIN_COLS)?MIN_COLS:nMaxCols;
	m_pfMatrix=new double[m_nMaxRows*m_nMaxCols];
	for(int i=0;i<(m_nMaxRows*m_nMaxCols);i++)
		m_pfMatrix[i]=0.0;
}

Logical myMatrix::Store(double fVal,int nRow,int nCol)
{
	if(nRow >=0&&nRow<m_nMaxRows&&
		nCol>=0&&nCol<m_nMaxCols)
	{
		m_pfMatrix[nRow+nCol*m_nMaxRows]=fVal;
		return TRUE;
	}
	else
		return FALSE;
}

Logical myMatrix::Recall(double &fVal,int nRow,int nCol)
{
	if(nRow >=0&&nRow<m_nMaxRows&&
		nCol >= 0 && nCol < m_nMaxCols)
	{
		fVal=m_pfMatrix[nRow+nCol*m_nMaxRows];
		return TRUE;
	}
	else
		return FALSE;
}

void myMatrix::Show(int nRows,int nCols,const char *pszMsg)
{
	cout << pszMsg << endl;
	for(int i=0;i<nRows;i++)
	{
		for(int j=0;j<nCols;j++)
			cout << m_pfMatrix[i+j*m_nMaxRows] << ' ';
		cout << endl;
	}
	
}

myMatrix &myMatrix::CopyToRow(myArray &anArray,int nRow)
{
	if(nRow<0||nRow>=m_nMaxRows)
		return *this;//exit

	//note:the number of rows=m_nMaxCols and
	//the number of columns=m_nMaxRows
	if(anArray.m_nMaxElems<m_nMaxCols)
		return *this;

	//copy the array elements to the targeted matrix row
	for(int j=0;j<m_nMaxCols;j++)
		//the next statement derectly accesses the
		//elements of the parameter anArray
		m_pfMatrix[nRow+j*m_nMaxRows]=anArray.m_pfArray[j];

	return *this;
}

main()
{
	const int TEST_SIZE=5;
	myArray anArray(TEST_SIZE);
	myMatrix aMatrix(TEST_SIZE,TEST_SIZE);
	for(int i=0;i<anArray.getNumElems();i++)
		anArray.Store(double(i+1),i);

	//copy the array object to each matrix row
	for(int nRow=0;nRow<aMatrix.getNumCols();nRow++)
		aMatrix.CopyToRow(anArray,nRow);

	//display the elements of the array object
	anArray.Show(anArray.getNumElems(),"The array is: ");
	cout << endl;

	//display the elements of the matrix object
	aMatrix.Show(aMatrix.getNumRows(),
		aMatrix.getNumCols(),
		"The matrix is: ");

	return 0;
}