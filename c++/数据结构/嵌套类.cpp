#include <iostream.h>
#include <iomanip.h>

enum Logical {FALSE,TRUE};

class myFixedStack
{
public:
	myFixedStack(unsigned nMaxSize=10)
	{
		m_pArrObj=new Array(nMaxSize);
		m_uHeight=0;
	}
	~myFixedStack()
	{delete m_pArrObj;}
	Logical isEmpty()
	{return(m_uHeight==0)?TRUE:FALSE;}
	unsigned getHeight()
	{return m_uHeight;}
	void push(long &lNum);
	Logical pop(long &lNum);
	void clear()
	{m_pArrObj->erase();}

protected:
	//declare neste class Array
	class Array
	{
	public:
		Array(unsigned lArraySize)
		{
			m_plArray=new long[m_lArraySize=lArraySize];
			m_uWorkSize=0;
		}

		~Array()
		{delete []m_plArray;}

		Logical insert(long lNum)
		{
			if(m_uWorkSize<m_lArraySize)
			{
				m_plArray[m_uWorkSize++]=lNum;
				return TRUE;
			}
			else
				return FALSE;
		}

		Logical remove(long &lNum)
		{
			if(m_uWorkSize>0)
			{
				lNum=m_plArray[--m_uWorkSize];
				return TRUE;
			}
			else
				return FALSE;
		}

		void erase()
		{m_uWorkSize=0;}

	protected:
		unsigned m_uWorkSize;
		unsigned m_lArraySize;
		long *m_plArray;
	};

	Array *m_pArrObj;
	unsigned m_uHeight;//height of stack
};

void myFixedStack::push(long &lNum)
{
	if(m_pArrObj->insert(lNum))
		m_uHeight++;
}

Logical myFixedStack::pop(long &lNum)
{
	Logical ok=m_pArrObj->remove(lNum);
	if(ok)
		m_uHeight--;
	return ok;
}

main()
{
	const int ARRAY_SIZE=5;
	myFixedStack Stack(ARRAY_SIZE);
	long lVal;
	long lArr[ARRAY_SIZE]={586,487,461,956,931};

	cout << " Stack height is " << Stack.getHeight() << endl;
	//push integers onto the stack
	for(int i=0;i<ARRAY_SIZE;i++)
	{
		cout << " Pushing " << lArr[i] << " onto the stack\n ";
		Stack.push(lArr[i]);
	}

	cout << "Stack height is " << Stack.getHeight() << endl;
	//pop elements off the stack
	while(Stack.pop(lVal))
		cout << " Popping " << lVal << " off the stack\n ";

	cout << " Stack height is " << Stack.getHeight() << endl;
	return 0;
}