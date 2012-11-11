/* this ALWAYS GENERATED file contains the definitions for the interfaces */


/* File created by MIDL compiler version 5.01.0164 */
/* at Mon Feb 11 17:27:16 2002
 */
/* Compiler settings for C:\Program Files\Microsoft Visual Studio\MyProjects\RCWTEST\RCWTEST.idl:
    Oicf (OptLev=i2), W1, Zp8, env=Win32, ms_ext, c_ext
    error checks: allocation ref bounds_check enum stub_data 
*/
//@@MIDL_FILE_HEADING(  )


/* verify that the <rpcndr.h> version is high enough to compile this file*/
#ifndef __REQUIRED_RPCNDR_H_VERSION__
#define __REQUIRED_RPCNDR_H_VERSION__ 440
#endif

#include "rpc.h"
#include "rpcndr.h"

#ifndef __RPCNDR_H_VERSION__
#error this stub requires an updated version of <rpcndr.h>
#endif // __RPCNDR_H_VERSION__

#ifndef COM_NO_WINDOWS_H
#include "windows.h"
#include "ole2.h"
#endif /*COM_NO_WINDOWS_H*/

#ifndef __RCWTEST_h__
#define __RCWTEST_h__

#ifdef __cplusplus
extern "C"{
#endif 

/* Forward Declarations */ 

#ifndef __IFactorial_FWD_DEFINED__
#define __IFactorial_FWD_DEFINED__
typedef interface IFactorial IFactorial;
#endif 	/* __IFactorial_FWD_DEFINED__ */


#ifndef __Factorial_FWD_DEFINED__
#define __Factorial_FWD_DEFINED__

#ifdef __cplusplus
typedef class Factorial Factorial;
#else
typedef struct Factorial Factorial;
#endif /* __cplusplus */

#endif 	/* __Factorial_FWD_DEFINED__ */


/* header files for imported files */
#include "oaidl.h"
#include "ocidl.h"

void __RPC_FAR * __RPC_USER MIDL_user_allocate(size_t);
void __RPC_USER MIDL_user_free( void __RPC_FAR * ); 

#ifndef __IFactorial_INTERFACE_DEFINED__
#define __IFactorial_INTERFACE_DEFINED__

/* interface IFactorial */
/* [unique][helpstring][dual][uuid][object] */ 


EXTERN_C const IID IID_IFactorial;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("617C8372-022A-4605-8E0A-2E5F533331BE")
    IFactorial : public IDispatch
    {
    public:
        virtual /* [helpstring][id] */ HRESULT STDMETHODCALLTYPE GetFactorial( 
            /* [in] */ int iValue,
            /* [retval][out] */ unsigned int __RPC_FAR *uFactorial) = 0;
        
    };
    
#else 	/* C style interface */

    typedef struct IFactorialVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *QueryInterface )( 
            IFactorial __RPC_FAR * This,
            /* [in] */ REFIID riid,
            /* [iid_is][out] */ void __RPC_FAR *__RPC_FAR *ppvObject);
        
        ULONG ( STDMETHODCALLTYPE __RPC_FAR *AddRef )( 
            IFactorial __RPC_FAR * This);
        
        ULONG ( STDMETHODCALLTYPE __RPC_FAR *Release )( 
            IFactorial __RPC_FAR * This);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *GetTypeInfoCount )( 
            IFactorial __RPC_FAR * This,
            /* [out] */ UINT __RPC_FAR *pctinfo);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *GetTypeInfo )( 
            IFactorial __RPC_FAR * This,
            /* [in] */ UINT iTInfo,
            /* [in] */ LCID lcid,
            /* [out] */ ITypeInfo __RPC_FAR *__RPC_FAR *ppTInfo);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *GetIDsOfNames )( 
            IFactorial __RPC_FAR * This,
            /* [in] */ REFIID riid,
            /* [size_is][in] */ LPOLESTR __RPC_FAR *rgszNames,
            /* [in] */ UINT cNames,
            /* [in] */ LCID lcid,
            /* [size_is][out] */ DISPID __RPC_FAR *rgDispId);
        
        /* [local] */ HRESULT ( STDMETHODCALLTYPE __RPC_FAR *Invoke )( 
            IFactorial __RPC_FAR * This,
            /* [in] */ DISPID dispIdMember,
            /* [in] */ REFIID riid,
            /* [in] */ LCID lcid,
            /* [in] */ WORD wFlags,
            /* [out][in] */ DISPPARAMS __RPC_FAR *pDispParams,
            /* [out] */ VARIANT __RPC_FAR *pVarResult,
            /* [out] */ EXCEPINFO __RPC_FAR *pExcepInfo,
            /* [out] */ UINT __RPC_FAR *puArgErr);
        
        /* [helpstring][id] */ HRESULT ( STDMETHODCALLTYPE __RPC_FAR *GetFactorial )( 
            IFactorial __RPC_FAR * This,
            /* [in] */ int iValue,
            /* [retval][out] */ unsigned int __RPC_FAR *uFactorial);
        
        END_INTERFACE
    } IFactorialVtbl;

    interface IFactorial
    {
        CONST_VTBL struct IFactorialVtbl __RPC_FAR *lpVtbl;
    };

    

#ifdef COBJMACROS


#define IFactorial_QueryInterface(This,riid,ppvObject)	\
    (This)->lpVtbl -> QueryInterface(This,riid,ppvObject)

#define IFactorial_AddRef(This)	\
    (This)->lpVtbl -> AddRef(This)

#define IFactorial_Release(This)	\
    (This)->lpVtbl -> Release(This)


#define IFactorial_GetTypeInfoCount(This,pctinfo)	\
    (This)->lpVtbl -> GetTypeInfoCount(This,pctinfo)

#define IFactorial_GetTypeInfo(This,iTInfo,lcid,ppTInfo)	\
    (This)->lpVtbl -> GetTypeInfo(This,iTInfo,lcid,ppTInfo)

#define IFactorial_GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId)	\
    (This)->lpVtbl -> GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId)

#define IFactorial_Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr)	\
    (This)->lpVtbl -> Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr)


#define IFactorial_GetFactorial(This,iValue,uFactorial)	\
    (This)->lpVtbl -> GetFactorial(This,iValue,uFactorial)

#endif /* COBJMACROS */


#endif 	/* C style interface */



/* [helpstring][id] */ HRESULT STDMETHODCALLTYPE IFactorial_GetFactorial_Proxy( 
    IFactorial __RPC_FAR * This,
    /* [in] */ int iValue,
    /* [retval][out] */ unsigned int __RPC_FAR *uFactorial);


void __RPC_STUB IFactorial_GetFactorial_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);



#endif 	/* __IFactorial_INTERFACE_DEFINED__ */



#ifndef __RCWTESTLib_LIBRARY_DEFINED__
#define __RCWTESTLib_LIBRARY_DEFINED__

/* library RCWTESTLib */
/* [helpstring][version][uuid] */ 


EXTERN_C const IID LIBID_RCWTESTLib;

EXTERN_C const CLSID CLSID_Factorial;

#ifdef __cplusplus

class DECLSPEC_UUID("8D3176FD-0C97-4481-8F66-EB78457417BB")
Factorial;
#endif
#endif /* __RCWTESTLib_LIBRARY_DEFINED__ */

/* Additional Prototypes for ALL interfaces */

/* end of Additional Prototypes */

#ifdef __cplusplus
}
#endif

#endif
