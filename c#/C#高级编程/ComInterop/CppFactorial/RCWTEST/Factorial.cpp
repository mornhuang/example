// Factorial.cpp : Implementation of CRCWTESTApp and DLL registration.

#include "stdafx.h"
#include "RCWTEST.h"
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

STDMETHODIMP CFactorial::GetFactorial(int iValue, unsigned int* uFactorial)
{
   unsigned int uSubTotal = iValue;
   for (int i = 1; i < iValue; i++)
      uSubTotal *= i;

   *uFactorial = uSubTotal;
   
	return S_OK;
}
