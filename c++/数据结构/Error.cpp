#include <iostream.h>
class Exception1{};
class Exception2
{
public:
	Exception2(int nErrorCode)
	{m_nErrorCode=nErrorCode;}
	int m_nErrorCode;
};

class Exception3
{
public:
	Exception3()
	{m_nErrorCode=0;}
	void setErrorCode(int nErrorCode)
	{m_nErrorCode=nErrorCode;}
	int getErrorCode()
	{return m_nErrorCode;}
protected:
	int m_nErrorCode;
};

main()
{
	int nNum=-32768;
	Exception2 errorObj2(32767);
	Exception3 errorObj3;

	cout<< " Testing throwing an int \n ";
	try{throw nNum;}

	catch(int nErrorCode)
	{
		cout << nErrorCode << "is not a valid int \n ";
	}

	catch(char cErrorCode)
	{
		cout << cErrorCode << "is not a valid char \n";
	}

	catch(long lErrorCode)
	{
		cout << lErrorCode << "is not a valid long \n";
	}

	cout << " Testing throwing an Exception1 excaption \n ";

	try{
		throw Exception1();
	}

	catch(Exception1)
	{
		cout << "Handling Exception1 exception \n ";
	}

	catch(char cErrorCode)
	{
		cout << cErrorCode << "is not a valid char \n ";
	}

	catch(long lErrorCode)
	{
		cout << lErrorCode << "is not a valid char \n ";
	}

	cout << "Testing throwing an Exception2 exception \n";

	try
	{
		throw errorObj2;
	}

	catch(Exception2 err)
	{
		cout << " Handling Exception2 exception \n";
		cout << err.m_nErrorCode << " is not a valid int \n";
	}

	catch(char cErrorCode)
	{
		cout << cErrorCode << "is not a valid char \n";
	}

	catch(long lErrorCode)
	{
		cout << lErrorCode << " is not a valid long \n";
	}

	return 0;
}