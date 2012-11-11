/* this ALWAYS GENERATED file contains the proxy stub code */


/* File created by MIDL compiler version 3.01.75 */
/* at Fri Jun 27 14:23:49 1997
 */
/* Compiler settings for MeowMoniker.idl:
    Oicf (OptLev=i2), W1, Zp8, env=Win32, ms_ext, c_ext
    error checks: none
*/
//@@MIDL_FILE_HEADING(  )

#define USE_STUBLESS_PROXY

#include "rpcproxy.h"
#include "MeowMoniker.h"

#define TYPE_FORMAT_STRING_SIZE   75                                
#define PROC_FORMAT_STRING_SIZE   61                                

typedef struct _MIDL_TYPE_FORMAT_STRING
    {
    short          Pad;
    unsigned char  Format[ TYPE_FORMAT_STRING_SIZE ];
    } MIDL_TYPE_FORMAT_STRING;

typedef struct _MIDL_PROC_FORMAT_STRING
    {
    short          Pad;
    unsigned char  Format[ PROC_FORMAT_STRING_SIZE ];
    } MIDL_PROC_FORMAT_STRING;


extern const MIDL_TYPE_FORMAT_STRING __MIDL_TypeFormatString;
extern const MIDL_PROC_FORMAT_STRING __MIDL_ProcFormatString;


/* Standard interface: __MIDL_itf_MeowMoniker_0000, ver. 0.0,
   GUID={0x00000000,0x0000,0x0000,{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00}} */


/* Object interface: IUnknown, ver. 0.0,
   GUID={0x00000000,0x0000,0x0000,{0xC0,0x00,0x00,0x00,0x00,0x00,0x00,0x46}} */


/* Object interface: IDispatch, ver. 0.0,
   GUID={0x00020400,0x0000,0x0000,{0xC0,0x00,0x00,0x00,0x00,0x00,0x00,0x46}} */


/* Object interface: DIFeline, ver. 0.0,
   GUID={0xCB18CB8E,0xC7CC,0x11D0,{0x9A,0x44,0x00,0x00,0x86,0x00,0xA1,0x05}} */


extern const MIDL_STUB_DESC Object_StubDesc;


extern const MIDL_SERVER_INFO DIFeline_ServerInfo;

#pragma code_seg(".orpc")
extern const USER_MARSHAL_ROUTINE_QUADRUPLE UserMarshalRoutines[1];

static const MIDL_STUB_DESC Object_StubDesc = 
    {
    0,
    NdrOleAllocate,
    NdrOleFree,
    0,
    0,
    0,
    0,
    0,
    __MIDL_TypeFormatString.Format,
    0, /* -error bounds_check flag */
    0x20000, /* Ndr library version */
    0,
    0x301004b, /* MIDL Version 3.1.75 */
    0,
    UserMarshalRoutines,
    0,  /* Reserved1 */
    0,  /* Reserved2 */
    0,  /* Reserved3 */
    0,  /* Reserved4 */
    0   /* Reserved5 */
    };

static const unsigned short DIFeline_FormatStringOffsetTable[] = 
    {
    (unsigned short) -1,
    (unsigned short) -1,
    (unsigned short) -1,
    (unsigned short) -1,
    0,
    30
    };

static const MIDL_SERVER_INFO DIFeline_ServerInfo = 
    {
    &Object_StubDesc,
    0,
    __MIDL_ProcFormatString.Format,
    &DIFeline_FormatStringOffsetTable[-3],
    0,
    0,
    0,
    0
    };

static const MIDL_STUBLESS_PROXY_INFO DIFeline_ProxyInfo =
    {
    &Object_StubDesc,
    __MIDL_ProcFormatString.Format,
    &DIFeline_FormatStringOffsetTable[-3],
    0,
    0,
    0
    };

CINTERFACE_PROXY_VTABLE(9) _DIFelineProxyVtbl = 
{
    &DIFeline_ProxyInfo,
    &IID_DIFeline,
    IUnknown_QueryInterface_Proxy,
    IUnknown_AddRef_Proxy,
    IUnknown_Release_Proxy ,
    0 /* (void *)-1 /* IDispatch::GetTypeInfoCount */ ,
    0 /* (void *)-1 /* IDispatch::GetTypeInfo */ ,
    0 /* (void *)-1 /* IDispatch::GetIDsOfNames */ ,
    0 /* IDispatch_Invoke_Proxy */ ,
    (void *)-1 /* DIFeline::GetDisplayName */ ,
    (void *)-1 /* DIFeline::ParseDisplayName */
};


static const PRPC_STUB_FUNCTION DIFeline_table[] =
{
    STUB_FORWARDING_FUNCTION,
    STUB_FORWARDING_FUNCTION,
    STUB_FORWARDING_FUNCTION,
    STUB_FORWARDING_FUNCTION,
    NdrStubCall2,
    NdrStubCall2
};

CInterfaceStubVtbl _DIFelineStubVtbl =
{
    &IID_DIFeline,
    &DIFeline_ServerInfo,
    9,
    &DIFeline_table[-3],
    CStdStubBuffer_DELEGATING_METHODS
};

#pragma data_seg(".rdata")

static const USER_MARSHAL_ROUTINE_QUADRUPLE UserMarshalRoutines[1] = 
        {
            
            {
            BSTR_UserSize
            ,BSTR_UserMarshal
            ,BSTR_UserUnmarshal
            ,BSTR_UserFree
            }

        };


#if !defined(__RPC_WIN32__)
#error  Invalid build platform for this stub.
#endif

#if !(TARGET_IS_NT40_OR_LATER)
#error You need a Windows NT 4.0 or later to run this stub because it uses these features:
#error   -Oif or -Oicf, [wire_marshal] or [user_marshal] attribute.
#error However, your C/C++ compilation flags indicate you intend to run this app on earlier systems.
#error This app will die there with the RPC_X_WRONG_STUB_VERSION error.
#endif


static const MIDL_PROC_FORMAT_STRING __MIDL_ProcFormatString =
    {
        0,
        {

	/* Procedure GetDisplayName */

			0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/*  2 */	NdrFcShort( 0x7 ),	/* 7 */
#ifndef _ALPHA_
/*  4 */	NdrFcShort( 0x10 ),	/* x86, MIPS, PPC Stack size/offset = 16 */
#else
			NdrFcShort( 0x20 ),	/* Alpha Stack size/offset = 32 */
#endif
/*  6 */	NdrFcShort( 0x0 ),	/* 0 */
/*  8 */	NdrFcShort( 0x8 ),	/* 8 */
/* 10 */	0x7,		/* 7 */
			0x3,		/* 3 */

	/* Parameter pdisp */

/* 12 */	NdrFcShort( 0xb ),	/* 11 */
#ifndef _ALPHA_
/* 14 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 16 */	NdrFcShort( 0x0 ),	/* Type Offset=0 */

	/* Parameter pbstrName */

/* 18 */	NdrFcShort( 0x2113 ),	/* 8467 */
#ifndef _ALPHA_
/* 20 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 22 */	NdrFcShort( 0x2e ),	/* Type Offset=46 */

	/* Return value */

/* 24 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 26 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 28 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure ParseDisplayName */

/* 30 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 32 */	NdrFcShort( 0x8 ),	/* 8 */
#ifndef _ALPHA_
/* 34 */	NdrFcShort( 0x10 ),	/* x86, MIPS, PPC Stack size/offset = 16 */
#else
			NdrFcShort( 0x20 ),	/* Alpha Stack size/offset = 32 */
#endif
/* 36 */	NdrFcShort( 0x0 ),	/* 0 */
/* 38 */	NdrFcShort( 0x8 ),	/* 8 */
/* 40 */	0x7,		/* 7 */
			0x3,		/* 3 */

	/* Parameter bstrName */

/* 42 */	NdrFcShort( 0x8b ),	/* 139 */
#ifndef _ALPHA_
/* 44 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 46 */	NdrFcShort( 0x3c ),	/* Type Offset=60 */

	/* Parameter ppdisp */

/* 48 */	NdrFcShort( 0x13 ),	/* 19 */
#ifndef _ALPHA_
/* 50 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 52 */	NdrFcShort( 0x46 ),	/* Type Offset=70 */

	/* Return value */

/* 54 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 56 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 58 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

			0x0
        }
    };

static const MIDL_TYPE_FORMAT_STRING __MIDL_TypeFormatString =
    {
        0,
        {
			
			0x2f,		/* FC_IP */
			0x5a,		/* FC_CONSTANT_IID */
/*  2 */	NdrFcLong( 0x20400 ),	/* 132096 */
/*  6 */	NdrFcShort( 0x0 ),	/* 0 */
/*  8 */	NdrFcShort( 0x0 ),	/* 0 */
/* 10 */	0xc0,		/* 192 */
			0x0,		/* 0 */
/* 12 */	0x0,		/* 0 */
			0x0,		/* 0 */
/* 14 */	0x0,		/* 0 */
			0x0,		/* 0 */
/* 16 */	0x0,		/* 0 */
			0x46,		/* 70 */
/* 18 */	
			0x11, 0x4,	/* FC_RP [alloced_on_stack] */
/* 20 */	NdrFcShort( 0x1a ),	/* Offset= 26 (46) */
/* 22 */	
			0x13, 0x0,	/* FC_OP */
/* 24 */	NdrFcShort( 0xc ),	/* Offset= 12 (36) */
/* 26 */	
			0x1b,		/* FC_CARRAY */
			0x1,		/* 1 */
/* 28 */	NdrFcShort( 0x2 ),	/* 2 */
/* 30 */	0x9,		/* 9 */
			0x0,		/*  */
/* 32 */	NdrFcShort( 0xfffffffc ),	/* -4 */
/* 34 */	0x6,		/* FC_SHORT */
			0x5b,		/* FC_END */
/* 36 */	
			0x17,		/* FC_CSTRUCT */
			0x3,		/* 3 */
/* 38 */	NdrFcShort( 0x8 ),	/* 8 */
/* 40 */	NdrFcShort( 0xfffffff2 ),	/* Offset= -14 (26) */
/* 42 */	0x8,		/* FC_LONG */
			0x8,		/* FC_LONG */
/* 44 */	0x5c,		/* FC_PAD */
			0x5b,		/* FC_END */
/* 46 */	0xb4,		/* FC_USER_MARSHAL */
			0x83,		/* 131 */
/* 48 */	NdrFcShort( 0x0 ),	/* 0 */
/* 50 */	NdrFcShort( 0x4 ),	/* 4 */
/* 52 */	NdrFcShort( 0x0 ),	/* 0 */
/* 54 */	NdrFcShort( 0xffffffe0 ),	/* Offset= -32 (22) */
/* 56 */	
			0x12, 0x0,	/* FC_UP */
/* 58 */	NdrFcShort( 0xffffffea ),	/* Offset= -22 (36) */
/* 60 */	0xb4,		/* FC_USER_MARSHAL */
			0x83,		/* 131 */
/* 62 */	NdrFcShort( 0x0 ),	/* 0 */
/* 64 */	NdrFcShort( 0x4 ),	/* 4 */
/* 66 */	NdrFcShort( 0x0 ),	/* 0 */
/* 68 */	NdrFcShort( 0xfffffff4 ),	/* Offset= -12 (56) */
/* 70 */	
			0x11, 0x10,	/* FC_RP */
/* 72 */	NdrFcShort( 0xffffffb8 ),	/* Offset= -72 (0) */

			0x0
        }
    };

const CInterfaceProxyVtbl * _MeowMoniker_ProxyVtblList[] = 
{
    ( CInterfaceProxyVtbl *) &_DIFelineProxyVtbl,
    0
};

const CInterfaceStubVtbl * _MeowMoniker_StubVtblList[] = 
{
    ( CInterfaceStubVtbl *) &_DIFelineStubVtbl,
    0
};

PCInterfaceName const _MeowMoniker_InterfaceNamesList[] = 
{
    "DIFeline",
    0
};

const IID *  _MeowMoniker_BaseIIDList[] = 
{
    &IID_IDispatch,
    0
};


#define _MeowMoniker_CHECK_IID(n)	IID_GENERIC_CHECK_IID( _MeowMoniker, pIID, n)

int __stdcall _MeowMoniker_IID_Lookup( const IID * pIID, int * pIndex )
{
    
    if(!_MeowMoniker_CHECK_IID(0))
        {
        *pIndex = 0;
        return 1;
        }

    return 0;
}

const ExtendedProxyFileInfo MeowMoniker_ProxyFileInfo = 
{
    (PCInterfaceProxyVtblList *) & _MeowMoniker_ProxyVtblList,
    (PCInterfaceStubVtblList *) & _MeowMoniker_StubVtblList,
    (const PCInterfaceName * ) & _MeowMoniker_InterfaceNamesList,
    (const IID ** ) & _MeowMoniker_BaseIIDList,
    & _MeowMoniker_IID_Lookup, 
    1,
    2
};
