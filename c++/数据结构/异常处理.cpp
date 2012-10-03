#include <iostream.h>

class myError1{};
class myError2
{
public:
	myError2(int nErrorCode)
	{m_nErrorCode=nErrorCode;}
	int m_nErrorCode;
};

main()
{
	int nErrorCode=-1;
	try
	{
		throw myError2(nErrorCode);
	}
	catch(int nErrorCode)
	{
		cout << "Handing int exception\n ";
		cout << nErrorCode << "is invalid\n ";
	}
	catch(myError1)
	{
		cout << "Handing myError1 exception\n ";
	}
	catch(myError2 errObj)
	{
		cout << "Handing myError2 exception\n ";
		cout << errObj.m_nErrorCode << " is invalid\n ";
	}
	catch(...)
	{
		cout << "Handing other errors\n ";
	}

	return 0;
}