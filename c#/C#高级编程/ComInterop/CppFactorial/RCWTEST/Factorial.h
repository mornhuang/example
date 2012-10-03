// Factorial.h: Definition of the CFactorial class
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_FACTORIAL_H__AFA204FD_7A7C_4651_BC8B_CD1CED483ABC__INCLUDED_)
#define AFX_FACTORIAL_H__AFA204FD_7A7C_4651_BC8B_CD1CED483ABC__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "resource.h"       // main symbols

/////////////////////////////////////////////////////////////////////////////
// CFactorial

class CFactorial : 
	public IDispatchImpl<IFactorial, &IID_IFactorial, &LIBID_RCWTESTLib>, 
	public ISupportErrorInfo,
	public CComObjectRoot,
	public CComCoClass<CFactorial,&CLSID_Factorial>
{
public:
	CFactorial() {}
BEGIN_COM_MAP(CFactorial)
	COM_INTERFACE_ENTRY(IDispatch)
	COM_INTERFACE_ENTRY(IFactorial)
	COM_INTERFACE_ENTRY(ISupportErrorInfo)
END_COM_MAP()
//DECLARE_NOT_AGGREGATABLE(CFactorial) 
// Remove the comment from the line above if you don't want your object to 
// support aggregation. 

DECLARE_REGISTRY_RESOURCEID(IDR_Factorial)
// ISupportsErrorInfo
	STDMETHOD(InterfaceSupportsErrorInfo)(REFIID riid);

// IFactorial
public:
	STDMETHOD(GetFactorial)(/*[in]*/ int iValue, /*[out, retval]*/ unsigned int* uFactorial);
};

#endif // !defined(AFX_FACTORIAL_H__AFA204FD_7A7C_4651_BC8B_CD1CED483ABC__INCLUDED_)
