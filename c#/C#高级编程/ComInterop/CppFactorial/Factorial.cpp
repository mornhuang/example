// Factorial.cpp : Implementation of CRCWTestApp and DLL registration.

#include "stdafx.h"
#include "RCWTest.h"
#include "Factorial.h"

/////////////////////////////////////////////////////////////////////////////
//

STDMETHODIMP CFactorial::InterfaceSupportsErrorInfo(REFIID riid)
{
	static const IID* arr[] = 
	{
		&IID_IFactorial,
	};

	for (int i=0;i<sizeof(arr)/sizeof(arr[0]);i++)
	{
		if (InlineIsEqualGUID(*arr[i],riid))
			return S_OK;
	}
	return S_FALSE;
}

STDMETHODIMP CFactorial::GetFactorial(int iValue, long *iFactorial)
{
	long iSubTotal = iValue;

	for (int i = 1; i < iValue; i++)
		iSubTotal *= i;

	*iFactorial = iSubTotal;

	return S_OK;
}
