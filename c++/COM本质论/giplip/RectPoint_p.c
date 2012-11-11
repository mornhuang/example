/* this ALWAYS GENERATED file contains the proxy stub code */


/* File created by MIDL compiler version 3.01.76 */
/* at Sun Jun 01 23:10:55 1997
 */
/* Compiler settings for RectPoint.idl:
    Oicf (OptLev=i2), W1, Zp8, env=Win32, ms_ext, c_ext
    error checks: none
*/
//@@MIDL_FILE_HEADING(  )

#define USE_STUBLESS_PROXY

#include "rpcproxy.h"
#include "RectPoint.h"

#define TYPE_FORMAT_STRING_SIZE   5                                 
#define PROC_FORMAT_STRING_SIZE   127                               

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


/* Object interface: IUnknown, ver. 0.0,
   GUID={0x00000000,0x0000,0x0000,{0xC0,0x00,0x00,0x00,0x00,0x00,0x00,0x46}} */


/* Object interface: IPoint, ver. 0.0,
   GUID={0xCCA7F35D,0xDAF3,0x11D0,{0x8C,0x53,0x00,0x80,0xC7,0x39,0x25,0xBA}} */


extern const MIDL_STUB_DESC Object_StubDesc;


extern const MIDL_SERVER_INFO IPoint_ServerInfo;

#pragma code_seg(".orpc")
static const unsigned short IPoint_FormatStringOffsetTable[] = 
    {
    0,
    30
    };

static const MIDL_SERVER_INFO IPoint_ServerInfo = 
    {
    &Object_StubDesc,
    0,
    __MIDL_ProcFormatString.Format,
    &IPoint_FormatStringOffsetTable[-3],
    0,
    0,
    0,
    0
    };

static const MIDL_STUBLESS_PROXY_INFO IPoint_ProxyInfo =
    {
    &Object_StubDesc,
    __MIDL_ProcFormatString.Format,
    &IPoint_FormatStringOffsetTable[-3],
    0,
    0,
    0
    };

CINTERFACE_PROXY_VTABLE(5) _IPointProxyVtbl = 
{
    &IPoint_ProxyInfo,
    &IID_IPoint,
    IUnknown_QueryInterface_Proxy,
    IUnknown_AddRef_Proxy,
    IUnknown_Release_Proxy ,
    (void *)-1 /* IPoint::GetCoords */ ,
    (void *)-1 /* IPoint::SetCoords */
};

const CInterfaceStubVtbl _IPointStubVtbl =
{
    &IID_IPoint,
    &IPoint_ServerInfo,
    5,
    0, /* pure interpreted */
    CStdStubBuffer_METHODS
};


/* Object interface: IRect, ver. 0.0,
   GUID={0xCCA7F35F,0xDAF3,0x11D0,{0x8C,0x53,0x00,0x80,0xC7,0x39,0x25,0xBA}} */


extern const MIDL_STUB_DESC Object_StubDesc;


extern const MIDL_SERVER_INFO IRect_ServerInfo;

#pragma code_seg(".orpc")

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
    0x301004c, /* MIDL Version 3.1.76 */
    0,
    0,
    0,  /* Reserved1 */
    0,  /* Reserved2 */
    0,  /* Reserved3 */
    0,  /* Reserved4 */
    0   /* Reserved5 */
    };

static const unsigned short IRect_FormatStringOffsetTable[] = 
    {
    60,
    102
    };

static const MIDL_SERVER_INFO IRect_ServerInfo = 
    {
    &Object_StubDesc,
    0,
    __MIDL_ProcFormatString.Format,
    &IRect_FormatStringOffsetTable[-3],
    0,
    0,
    0,
    0
    };

static const MIDL_STUBLESS_PROXY_INFO IRect_ProxyInfo =
    {
    &Object_StubDesc,
    __MIDL_ProcFormatString.Format,
    &IRect_FormatStringOffsetTable[-3],
    0,
    0,
    0
    };

CINTERFACE_PROXY_VTABLE(5) _IRectProxyVtbl = 
{
    &IRect_ProxyInfo,
    &IID_IRect,
    IUnknown_QueryInterface_Proxy,
    IUnknown_AddRef_Proxy,
    IUnknown_Release_Proxy ,
    (void *)-1 /* IRect::SetCoords */ ,
    (void *)-1 /* IRect::get_Volume */
};

const CInterfaceStubVtbl _IRectStubVtbl =
{
    &IID_IRect,
    &IRect_ServerInfo,
    5,
    0, /* pure interpreted */
    CStdStubBuffer_METHODS
};

#pragma data_seg(".rdata")

#if !defined(__RPC_WIN32__)
#error  Invalid build platform for this stub.
#endif

#if !(TARGET_IS_NT40_OR_LATER)
#error You need a Windows NT 4.0 or later to run this stub because it uses these features:
#error   -Oif or -Oicf.
#error However, your C/C++ compilation flags indicate you intend to run this app on earlier systems.
#error This app will die there with the RPC_X_WRONG_STUB_VERSION error.
#endif


static const MIDL_PROC_FORMAT_STRING __MIDL_ProcFormatString =
    {
        0,
        {

	/* Procedure GetCoords */

			0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/*  2 */	NdrFcShort( 0x3 ),	/* 3 */
#ifndef _ALPHA_
/*  4 */	NdrFcShort( 0x10 ),	/* x86, MIPS, PPC Stack size/offset = 16 */
#else
			NdrFcShort( 0x20 ),	/* Alpha Stack size/offset = 32 */
#endif
/*  6 */	NdrFcShort( 0x0 ),	/* 0 */
/*  8 */	NdrFcShort( 0x18 ),	/* 24 */
/* 10 */	0x4,		/* 4 */
			0x3,		/* 3 */

	/* Parameter px */

/* 12 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 14 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 16 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Parameter py */

/* 18 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 20 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 22 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */

/* 24 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 26 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 28 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure SetCoords */

/* 30 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 32 */	NdrFcShort( 0x4 ),	/* 4 */
#ifndef _ALPHA_
/* 34 */	NdrFcShort( 0x10 ),	/* x86, MIPS, PPC Stack size/offset = 16 */
#else
			NdrFcShort( 0x20 ),	/* Alpha Stack size/offset = 32 */
#endif
/* 36 */	NdrFcShort( 0x10 ),	/* 16 */
/* 38 */	NdrFcShort( 0x8 ),	/* 8 */
/* 40 */	0x4,		/* 4 */
			0x3,		/* 3 */

	/* Parameter x */

/* 42 */	NdrFcShort( 0x48 ),	/* 72 */
#ifndef _ALPHA_
/* 44 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 46 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Parameter y */

/* 48 */	NdrFcShort( 0x48 ),	/* 72 */
#ifndef _ALPHA_
/* 50 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 52 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */

/* 54 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 56 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 58 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure SetCoords */

/* 60 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 62 */	NdrFcShort( 0x3 ),	/* 3 */
#ifndef _ALPHA_
/* 64 */	NdrFcShort( 0x18 ),	/* x86, MIPS, PPC Stack size/offset = 24 */
#else
			NdrFcShort( 0x30 ),	/* Alpha Stack size/offset = 48 */
#endif
/* 66 */	NdrFcShort( 0x20 ),	/* 32 */
/* 68 */	NdrFcShort( 0x8 ),	/* 8 */
/* 70 */	0x4,		/* 4 */
			0x5,		/* 5 */

	/* Parameter left */

/* 72 */	NdrFcShort( 0x48 ),	/* 72 */
#ifndef _ALPHA_
/* 74 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 76 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Parameter top */

/* 78 */	NdrFcShort( 0x48 ),	/* 72 */
#ifndef _ALPHA_
/* 80 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 82 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Parameter right */

/* 84 */	NdrFcShort( 0x48 ),	/* 72 */
#ifndef _ALPHA_
/* 86 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 88 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Parameter bottom */

/* 90 */	NdrFcShort( 0x48 ),	/* 72 */
#ifndef _ALPHA_
/* 92 */	NdrFcShort( 0x10 ),	/* x86, MIPS, PPC Stack size/offset = 16 */
#else
			NdrFcShort( 0x20 ),	/* Alpha Stack size/offset = 32 */
#endif
/* 94 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */

/* 96 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 98 */	NdrFcShort( 0x14 ),	/* x86, MIPS, PPC Stack size/offset = 20 */
#else
			NdrFcShort( 0x28 ),	/* Alpha Stack size/offset = 40 */
#endif
/* 100 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_Volume */

/* 102 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 104 */	NdrFcShort( 0x4 ),	/* 4 */
#ifndef _ALPHA_
/* 106 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 108 */	NdrFcShort( 0x0 ),	/* 0 */
/* 110 */	NdrFcShort( 0x10 ),	/* 16 */
/* 112 */	0x4,		/* 4 */
			0x2,		/* 2 */

	/* Parameter pVal */

/* 114 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 116 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 118 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */

/* 120 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 122 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 124 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

			0x0
        }
    };

static const MIDL_TYPE_FORMAT_STRING __MIDL_TypeFormatString =
    {
        0,
        {
			0x11, 0xc,	/* FC_RP [alloced_on_stack] [simple_pointer] */
/*  2 */	0x8,		/* FC_LONG */
			0x5c,		/* FC_PAD */

			0x0
        }
    };

const CInterfaceProxyVtbl * _RectPoint_ProxyVtblList[] = 
{
    ( CInterfaceProxyVtbl *) &_IPointProxyVtbl,
    ( CInterfaceProxyVtbl *) &_IRectProxyVtbl,
    0
};

const CInterfaceStubVtbl * _RectPoint_StubVtblList[] = 
{
    ( CInterfaceStubVtbl *) &_IPointStubVtbl,
    ( CInterfaceStubVtbl *) &_IRectStubVtbl,
    0
};

PCInterfaceName const _RectPoint_InterfaceNamesList[] = 
{
    "IPoint",
    "IRect",
    0
};


#define _RectPoint_CHECK_IID(n)	IID_GENERIC_CHECK_IID( _RectPoint, pIID, n)

int __stdcall _RectPoint_IID_Lookup( const IID * pIID, int * pIndex )
{
    IID_BS_LOOKUP_SETUP

    IID_BS_LOOKUP_INITIAL_TEST( _RectPoint, 2, 1 )
    IID_BS_LOOKUP_RETURN_RESULT( _RectPoint, 2, *pIndex )
    
}

const ExtendedProxyFileInfo RectPoint_ProxyFileInfo = 
{
    (PCInterfaceProxyVtblList *) & _RectPoint_ProxyVtblList,
    (PCInterfaceStubVtblList *) & _RectPoint_StubVtblList,
    (const PCInterfaceName * ) & _RectPoint_InterfaceNamesList,
    0, // no delegation
    & _RectPoint_IID_Lookup, 
    2,
    2
};
