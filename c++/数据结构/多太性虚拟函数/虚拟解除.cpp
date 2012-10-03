#include <iostream.h>
#include <iomanip.h>

class OneInt
{
public:
	OneInt()
	{m_pnInt1=new int;}
	virtual ~OneInt()
	{
	    delete m_pnInt1;
		cout << " Invoking destructor ~OneInt\n ";
	}

	void setInt1(int nInt)
	{*m_pnInt1=nInt;}
	int getInt1()
	{return *m_pnInt1;}
protected:
	int *m_pnInt1;
};

class TwoInts:public OneInt
{
public:
	TwoInts():OneInt()
	{m_pnInt2=new int;}
	virtual ~TwoInts()
	{
	    delete m_pnInt2;
		cout << " Invoking destructor ~TwoInts\n ";
	}
	void setInt2(int nInt)
	{*m_pnInt2=nInt;}
	int getInt2()
	{return *m_pnInt2;}

protected:
	int *m_pnInt2;
};

main()
{
    OneInt Int1;
	TwoInts Int2;

	Int1.setInt1(1);
	Int2.setInt1(100);
	Int2.setInt2(200);

	cout << " Integer in Int1 is " << Int1.getInt1() << endl;
	cout << " Integer 1 in Int2 is " << Int2.getInt1() << endl;
	cout << " Integer 2 in Int2 is " << Int2.getInt2() << endl;

	return 0;
}