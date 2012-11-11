/* this ALWAYS GENERATED file contains the definitions for the interfaces */


/* File created by MIDL compiler version 3.01.76 */
/* at Sat Sep 06 22:09:23 1997
 */
/* Compiler settings for ..\include\COMChat.idl:
    Os (OptLev=s), W1, Zp8, env=Win32, ms_ext, c_ext
    error checks: none
*/
//@@MIDL_FILE_HEADING(  )
#include "rpc.h"
#include "rpcndr.h"
#ifndef COM_NO_WINDOWS_H
#include "windows.h"
#include "ole2.h"
#endif /*COM_NO_WINDOWS_H*/

#ifndef __COMChat_h__
#define __COMChat_h__

#ifdef __cplusplus
extern "C"{
#endif 

/* Forward Declarations */ 

#ifndef __IChatSession_FWD_DEFINED__
#define __IChatSession_FWD_DEFINED__
typedef interface IChatSession IChatSession;
#endif 	/* __IChatSession_FWD_DEFINED__ */


#ifndef __IChatSessionEvents_FWD_DEFINED__
#define __IChatSessionEvents_FWD_DEFINED__
typedef interface IChatSessionEvents IChatSessionEvents;
#endif 	/* __IChatSessionEvents_FWD_DEFINED__ */


#ifndef __IChatSessionManager_FWD_DEFINED__
#define __IChatSessionManager_FWD_DEFINED__
typedef interface IChatSessionManager IChatSessionManager;
#endif 	/* __IChatSessionManager_FWD_DEFINED__ */


/* header files for imported files */
#include "objidl.h"

void __RPC_FAR * __RPC_USER MIDL_user_allocate(size_t);
void __RPC_USER MIDL_user_free( void __RPC_FAR * ); 

/****************************************
 * Generated header for interface: __MIDL_itf_COMChat_0000
 * at Sat Sep 06 22:09:23 1997
 * using MIDL 3.01.76
 ****************************************/
/* [local] */ 





extern RPC_IF_HANDLE __MIDL_itf_COMChat_0000_v0_0_c_ifspec;
extern RPC_IF_HANDLE __MIDL_itf_COMChat_0000_v0_0_s_ifspec;

#ifndef __IChatSession_INTERFACE_DEFINED__
#define __IChatSession_INTERFACE_DEFINED__

/****************************************
 * Generated header for interface: IChatSession
 * at Sat Sep 06 22:09:23 1997
 * using MIDL 3.01.76
 ****************************************/
/* [object][uuid] */ 



EXTERN_C const IID IID_IChatSession;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    interface DECLSPEC_UUID("5223A050-2441-11d1-AF4F-0060976AA886")
    IChatSession : public IUnknown
    {
    public:
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_SessionName( 
            /* [string][out] */ OLECHAR __RPC_FAR *__RPC_FAR *ppwsz) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Say( 
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszStatement) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetStatements( 
            /* [out] */ IEnumString __RPC_FAR *__RPC_FAR *ppes) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Advise( 
            /* [in] */ IChatSessionEvents __RPC_FAR *pEventSink,
            /* [out] */ DWORD __RPC_FAR *pdwReg) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE Unadvise( 
            /* [in] */ DWORD dwReg) = 0;
        
    };
    
#else 	/* C style interface */

    typedef struct IChatSessionVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *QueryInterface )( 
            IChatSession __RPC_FAR * This,
            /* [in] */ REFIID riid,
            /* [iid_is][out] */ void __RPC_FAR *__RPC_FAR *ppvObject);
        
        ULONG ( STDMETHODCALLTYPE __RPC_FAR *AddRef )( 
            IChatSession __RPC_FAR * This);
        
        ULONG ( STDMETHODCALLTYPE __RPC_FAR *Release )( 
            IChatSession __RPC_FAR * This);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE __RPC_FAR *get_SessionName )( 
            IChatSession __RPC_FAR * This,
            /* [string][out] */ OLECHAR __RPC_FAR *__RPC_FAR *ppwsz);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *Say )( 
            IChatSession __RPC_FAR * This,
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszStatement);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *GetStatements )( 
            IChatSession __RPC_FAR * This,
            /* [out] */ IEnumString __RPC_FAR *__RPC_FAR *ppes);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *Advise )( 
            IChatSession __RPC_FAR * This,
            /* [in] */ IChatSessionEvents __RPC_FAR *pEventSink,
            /* [out] */ DWORD __RPC_FAR *pdwReg);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *Unadvise )( 
            IChatSession __RPC_FAR * This,
            /* [in] */ DWORD dwReg);
        
        END_INTERFACE
    } IChatSessionVtbl;

    interface IChatSession
    {
        CONST_VTBL struct IChatSessionVtbl __RPC_FAR *lpVtbl;
    };

    

#ifdef COBJMACROS


#define IChatSession_QueryInterface(This,riid,ppvObject)	\
    (This)->lpVtbl -> QueryInterface(This,riid,ppvObject)

#define IChatSession_AddRef(This)	\
    (This)->lpVtbl -> AddRef(This)

#define IChatSession_Release(This)	\
    (This)->lpVtbl -> Release(This)


#define IChatSession_get_SessionName(This,ppwsz)	\
    (This)->lpVtbl -> get_SessionName(This,ppwsz)

#define IChatSession_Say(This,pwszStatement)	\
    (This)->lpVtbl -> Say(This,pwszStatement)

#define IChatSession_GetStatements(This,ppes)	\
    (This)->lpVtbl -> GetStatements(This,ppes)

#define IChatSession_Advise(This,pEventSink,pdwReg)	\
    (This)->lpVtbl -> Advise(This,pEventSink,pdwReg)

#define IChatSession_Unadvise(This,dwReg)	\
    (This)->lpVtbl -> Unadvise(This,dwReg)

#endif /* COBJMACROS */


#endif 	/* C style interface */



/* [propget] */ HRESULT STDMETHODCALLTYPE IChatSession_get_SessionName_Proxy( 
    IChatSession __RPC_FAR * This,
    /* [string][out] */ OLECHAR __RPC_FAR *__RPC_FAR *ppwsz);


void __RPC_STUB IChatSession_get_SessionName_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);


HRESULT STDMETHODCALLTYPE IChatSession_Say_Proxy( 
    IChatSession __RPC_FAR * This,
    /* [string][in] */ const OLECHAR __RPC_FAR *pwszStatement);


void __RPC_STUB IChatSession_Say_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);


HRESULT STDMETHODCALLTYPE IChatSession_GetStatements_Proxy( 
    IChatSession __RPC_FAR * This,
    /* [out] */ IEnumString __RPC_FAR *__RPC_FAR *ppes);


void __RPC_STUB IChatSession_GetStatements_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);


HRESULT STDMETHODCALLTYPE IChatSession_Advise_Proxy( 
    IChatSession __RPC_FAR * This,
    /* [in] */ IChatSessionEvents __RPC_FAR *pEventSink,
    /* [out] */ DWORD __RPC_FAR *pdwReg);


void __RPC_STUB IChatSession_Advise_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);


HRESULT STDMETHODCALLTYPE IChatSession_Unadvise_Proxy( 
    IChatSession __RPC_FAR * This,
    /* [in] */ DWORD dwReg);


void __RPC_STUB IChatSession_Unadvise_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);



#endif 	/* __IChatSession_INTERFACE_DEFINED__ */


#ifndef __IChatSessionEvents_INTERFACE_DEFINED__
#define __IChatSessionEvents_INTERFACE_DEFINED__

/****************************************
 * Generated header for interface: IChatSessionEvents
 * at Sat Sep 06 22:09:23 1997
 * using MIDL 3.01.76
 ****************************************/
/* [object][uuid] */ 



EXTERN_C const IID IID_IChatSessionEvents;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    interface DECLSPEC_UUID("5223A051-2441-11d1-AF4F-0060976AA886")
    IChatSessionEvents : public IUnknown
    {
    public:
        virtual HRESULT STDMETHODCALLTYPE OnNewUser( 
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE OnUserLeft( 
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE OnNewStatement( 
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser,
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszStmnt) = 0;
        
    };
    
#else 	/* C style interface */

    typedef struct IChatSessionEventsVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *QueryInterface )( 
            IChatSessionEvents __RPC_FAR * This,
            /* [in] */ REFIID riid,
            /* [iid_is][out] */ void __RPC_FAR *__RPC_FAR *ppvObject);
        
        ULONG ( STDMETHODCALLTYPE __RPC_FAR *AddRef )( 
            IChatSessionEvents __RPC_FAR * This);
        
        ULONG ( STDMETHODCALLTYPE __RPC_FAR *Release )( 
            IChatSessionEvents __RPC_FAR * This);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *OnNewUser )( 
            IChatSessionEvents __RPC_FAR * This,
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *OnUserLeft )( 
            IChatSessionEvents __RPC_FAR * This,
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *OnNewStatement )( 
            IChatSessionEvents __RPC_FAR * This,
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser,
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszStmnt);
        
        END_INTERFACE
    } IChatSessionEventsVtbl;

    interface IChatSessionEvents
    {
        CONST_VTBL struct IChatSessionEventsVtbl __RPC_FAR *lpVtbl;
    };

    

#ifdef COBJMACROS


#define IChatSessionEvents_QueryInterface(This,riid,ppvObject)	\
    (This)->lpVtbl -> QueryInterface(This,riid,ppvObject)

#define IChatSessionEvents_AddRef(This)	\
    (This)->lpVtbl -> AddRef(This)

#define IChatSessionEvents_Release(This)	\
    (This)->lpVtbl -> Release(This)


#define IChatSessionEvents_OnNewUser(This,pwszUser)	\
    (This)->lpVtbl -> OnNewUser(This,pwszUser)

#define IChatSessionEvents_OnUserLeft(This,pwszUser)	\
    (This)->lpVtbl -> OnUserLeft(This,pwszUser)

#define IChatSessionEvents_OnNewStatement(This,pwszUser,pwszStmnt)	\
    (This)->lpVtbl -> OnNewStatement(This,pwszUser,pwszStmnt)

#endif /* COBJMACROS */


#endif 	/* C style interface */



HRESULT STDMETHODCALLTYPE IChatSessionEvents_OnNewUser_Proxy( 
    IChatSessionEvents __RPC_FAR * This,
    /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser);


void __RPC_STUB IChatSessionEvents_OnNewUser_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);


HRESULT STDMETHODCALLTYPE IChatSessionEvents_OnUserLeft_Proxy( 
    IChatSessionEvents __RPC_FAR * This,
    /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser);


void __RPC_STUB IChatSessionEvents_OnUserLeft_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);


HRESULT STDMETHODCALLTYPE IChatSessionEvents_OnNewStatement_Proxy( 
    IChatSessionEvents __RPC_FAR * This,
    /* [string][in] */ const OLECHAR __RPC_FAR *pwszUser,
    /* [string][in] */ const OLECHAR __RPC_FAR *pwszStmnt);


void __RPC_STUB IChatSessionEvents_OnNewStatement_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);



#endif 	/* __IChatSessionEvents_INTERFACE_DEFINED__ */


#ifndef __IChatSessionManager_INTERFACE_DEFINED__
#define __IChatSessionManager_INTERFACE_DEFINED__

/****************************************
 * Generated header for interface: IChatSessionManager
 * at Sat Sep 06 22:09:23 1997
 * using MIDL 3.01.76
 ****************************************/
/* [object][uuid] */ 



EXTERN_C const IID IID_IChatSessionManager;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    interface DECLSPEC_UUID("5223A052-2441-11d1-AF4F-0060976AA886")
    IChatSessionManager : public IUnknown
    {
    public:
        virtual HRESULT STDMETHODCALLTYPE GetSessionNames( 
            /* [out] */ IEnumString __RPC_FAR *__RPC_FAR *ppes) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE FindSession( 
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszSessionName,
            /* [in] */ BOOL bDontCreate,
            /* [in] */ BOOL bAllowAnonymousAccess,
            /* [out] */ IChatSession __RPC_FAR *__RPC_FAR *ppcs) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE DeleteSession( 
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszSessionName) = 0;
        
    };
    
#else 	/* C style interface */

    typedef struct IChatSessionManagerVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *QueryInterface )( 
            IChatSessionManager __RPC_FAR * This,
            /* [in] */ REFIID riid,
            /* [iid_is][out] */ void __RPC_FAR *__RPC_FAR *ppvObject);
        
        ULONG ( STDMETHODCALLTYPE __RPC_FAR *AddRef )( 
            IChatSessionManager __RPC_FAR * This);
        
        ULONG ( STDMETHODCALLTYPE __RPC_FAR *Release )( 
            IChatSessionManager __RPC_FAR * This);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *GetSessionNames )( 
            IChatSessionManager __RPC_FAR * This,
            /* [out] */ IEnumString __RPC_FAR *__RPC_FAR *ppes);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *FindSession )( 
            IChatSessionManager __RPC_FAR * This,
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszSessionName,
            /* [in] */ BOOL bDontCreate,
            /* [in] */ BOOL bAllowAnonymousAccess,
            /* [out] */ IChatSession __RPC_FAR *__RPC_FAR *ppcs);
        
        HRESULT ( STDMETHODCALLTYPE __RPC_FAR *DeleteSession )( 
            IChatSessionManager __RPC_FAR * This,
            /* [string][in] */ const OLECHAR __RPC_FAR *pwszSessionName);
        
        END_INTERFACE
    } IChatSessionManagerVtbl;

    interface IChatSessionManager
    {
        CONST_VTBL struct IChatSessionManagerVtbl __RPC_FAR *lpVtbl;
    };

    

#ifdef COBJMACROS


#define IChatSessionManager_QueryInterface(This,riid,ppvObject)	\
    (This)->lpVtbl -> QueryInterface(This,riid,ppvObject)

#define IChatSessionManager_AddRef(This)	\
    (This)->lpVtbl -> AddRef(This)

#define IChatSessionManager_Release(This)	\
    (This)->lpVtbl -> Release(This)


#define IChatSessionManager_GetSessionNames(This,ppes)	\
    (This)->lpVtbl -> GetSessionNames(This,ppes)

#define IChatSessionManager_FindSession(This,pwszSessionName,bDontCreate,bAllowAnonymousAccess,ppcs)	\
    (This)->lpVtbl -> FindSession(This,pwszSessionName,bDontCreate,bAllowAnonymousAccess,ppcs)

#define IChatSessionManager_DeleteSession(This,pwszSessionName)	\
    (This)->lpVtbl -> DeleteSession(This,pwszSessionName)

#endif /* COBJMACROS */


#endif 	/* C style interface */



HRESULT STDMETHODCALLTYPE IChatSessionManager_GetSessionNames_Proxy( 
    IChatSessionManager __RPC_FAR * This,
    /* [out] */ IEnumString __RPC_FAR *__RPC_FAR *ppes);


void __RPC_STUB IChatSessionManager_GetSessionNames_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);


HRESULT STDMETHODCALLTYPE IChatSessionManager_FindSession_Proxy( 
    IChatSessionManager __RPC_FAR * This,
    /* [string][in] */ const OLECHAR __RPC_FAR *pwszSessionName,
    /* [in] */ BOOL bDontCreate,
    /* [in] */ BOOL bAllowAnonymousAccess,
    /* [out] */ IChatSession __RPC_FAR *__RPC_FAR *ppcs);


void __RPC_STUB IChatSessionManager_FindSession_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);


HRESULT STDMETHODCALLTYPE IChatSessionManager_DeleteSession_Proxy( 
    IChatSessionManager __RPC_FAR * This,
    /* [string][in] */ const OLECHAR __RPC_FAR *pwszSessionName);


void __RPC_STUB IChatSessionManager_DeleteSession_Stub(
    IRpcStubBuffer *This,
    IRpcChannelBuffer *_pRpcChannelBuffer,
    PRPC_MESSAGE _pRpcMessage,
    DWORD *_pdwStubPhase);



#endif 	/* __IChatSessionManager_INTERFACE_DEFINED__ */


/****************************************
 * Generated header for interface: __MIDL_itf_COMChat_0061
 * at Sat Sep 06 22:09:23 1997
 * using MIDL 3.01.76
 ****************************************/
/* [local] */ 


DEFINE_GUID(CLSID_ChatSession, 0x5223a053, 0x2441, 0x11d1, 0xaf, 0x4f, 0x0, 0x60, 0x97, 0x6a, 0xa8, 0x86);


extern RPC_IF_HANDLE __MIDL_itf_COMChat_0061_v0_0_c_ifspec;
extern RPC_IF_HANDLE __MIDL_itf_COMChat_0061_v0_0_s_ifspec;

/* Additional Prototypes for ALL interfaces */

/* end of Additional Prototypes */

#ifdef __cplusplus
}
#endif

#endif
