/* this ALWAYS GENERATED file contains the proxy stub code */


/* File created by MIDL compiler version 3.01.75 */
/* at Tue Oct 21 21:50:50 1997
 */
/* Compiler settings for .\HostHook.idl:
    Oicf (OptLev=i2), W1, Zp8, env=Win32, ms_ext, c_ext
    error checks: none
*/
//@@MIDL_FILE_HEADING(  )

#define USE_STUBLESS_PROXY

#include "rpcproxy.h"
#include "HostHook.h"

#define TYPE_FORMAT_STRING_SIZE   93                                
#define PROC_FORMAT_STRING_SIZE   241                               

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


/* Object interface: ICallInfo, ver. 0.0,
   GUID={0xF23ADD50,0x4992,0x11d1,{0x99,0x1C,0x00,0x60,0x97,0x58,0x5A,0x3C}} */


extern const MIDL_STUB_DESC Object_StubDesc;


extern const MIDL_SERVER_INFO ICallInfo_ServerInfo;

#pragma code_seg(".orpc")
static const unsigned short ICallInfo_FormatStringOffsetTable[] = 
    {
    0,
    24,
    48,
    72,
    96,
    120,
    144,
    168,
    192
    };

static const MIDL_SERVER_INFO ICallInfo_ServerInfo = 
    {
    &Object_StubDesc,
    0,
    __MIDL_ProcFormatString.Format,
    &ICallInfo_FormatStringOffsetTable[-3],
    0,
    0,
    0,
    0
    };

static const MIDL_STUBLESS_PROXY_INFO ICallInfo_ProxyInfo =
    {
    &Object_StubDesc,
    __MIDL_ProcFormatString.Format,
    &ICallInfo_FormatStringOffsetTable[-3],
    0,
    0,
    0
    };

CINTERFACE_PROXY_VTABLE(12) _ICallInfoProxyVtbl = 
{
    &ICallInfo_ProxyInfo,
    &IID_ICallInfo,
    IUnknown_QueryInterface_Proxy,
    IUnknown_AddRef_Proxy,
    IUnknown_Release_Proxy ,
    (void *)-1 /* ICallInfo::get_OriginalProcessID */ ,
    (void *)-1 /* ICallInfo::get_OriginalThreadID */ ,
    (void *)-1 /* ICallInfo::get_OriginalHostID */ ,
    (void *)-1 /* ICallInfo::get_OriginalHostName */ ,
    (void *)-1 /* ICallInfo::get_DirectProcessID */ ,
    (void *)-1 /* ICallInfo::get_DirectThreadID */ ,
    (void *)-1 /* ICallInfo::get_DirectHostID */ ,
    (void *)-1 /* ICallInfo::get_DirectHostName */ ,
    (void *)-1 /* ICallInfo::get_PseudoCausalityID */
};

const CInterfaceStubVtbl _ICallInfoStubVtbl =
{
    &IID_ICallInfo,
    &ICallInfo_ServerInfo,
    12,
    0, /* pure interpreted */
    CStdStubBuffer_METHODS
};


/* Object interface: IClientCallInfo, ver. 0.0,
   GUID={0xF23ADD54,0x4992,0x11d1,{0x99,0x1C,0x00,0x60,0x97,0x58,0x5A,0x3C}} */


extern const MIDL_STUB_DESC Object_StubDesc;


extern const MIDL_SERVER_INFO IClientCallInfo_ServerInfo;

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

static const unsigned short IClientCallInfo_FormatStringOffsetTable[] = 
    {
    0,
    24,
    48,
    216
    };

static const MIDL_SERVER_INFO IClientCallInfo_ServerInfo = 
    {
    &Object_StubDesc,
    0,
    __MIDL_ProcFormatString.Format,
    &IClientCallInfo_FormatStringOffsetTable[-3],
    0,
    0,
    0,
    0
    };

static const MIDL_STUBLESS_PROXY_INFO IClientCallInfo_ProxyInfo =
    {
    &Object_StubDesc,
    __MIDL_ProcFormatString.Format,
    &IClientCallInfo_FormatStringOffsetTable[-3],
    0,
    0,
    0
    };

CINTERFACE_PROXY_VTABLE(7) _IClientCallInfoProxyVtbl = 
{
    &IClientCallInfo_ProxyInfo,
    &IID_IClientCallInfo,
    IUnknown_QueryInterface_Proxy,
    IUnknown_AddRef_Proxy,
    IUnknown_Release_Proxy ,
    (void *)-1 /* IClientCallInfo::get_TargetProcessID */ ,
    (void *)-1 /* IClientCallInfo::get_TargetThreadID */ ,
    (void *)-1 /* IClientCallInfo::get_TargetHostID */ ,
    (void *)-1 /* IClientCallInfo::get_TargetHostName */
};

const CInterfaceStubVtbl _IClientCallInfoStubVtbl =
{
    &IID_IClientCallInfo,
    &IClientCallInfo_ServerInfo,
    7,
    0, /* pure interpreted */
    CStdStubBuffer_METHODS
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

	/* Procedure get_TargetProcessID */


	/* Procedure get_OriginalProcessID */

			0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/*  2 */	NdrFcShort( 0x3 ),	/* 3 */
#ifndef _ALPHA_
/*  4 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/*  6 */	NdrFcShort( 0x0 ),	/* 0 */
/*  8 */	NdrFcShort( 0x10 ),	/* 16 */
/* 10 */	0x4,		/* 4 */
			0x2,		/* 2 */

	/* Parameter pVal */


	/* Parameter pVal */

/* 12 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 14 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 16 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */


	/* Return value */

/* 18 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 20 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 22 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_TargetThreadID */


	/* Procedure get_OriginalThreadID */

/* 24 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 26 */	NdrFcShort( 0x4 ),	/* 4 */
#ifndef _ALPHA_
/* 28 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 30 */	NdrFcShort( 0x0 ),	/* 0 */
/* 32 */	NdrFcShort( 0x10 ),	/* 16 */
/* 34 */	0x4,		/* 4 */
			0x2,		/* 2 */

	/* Parameter pVal */


	/* Parameter pVal */

/* 36 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 38 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 40 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */


	/* Return value */

/* 42 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 44 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 46 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_TargetHostID */


	/* Procedure get_OriginalHostID */

/* 48 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 50 */	NdrFcShort( 0x5 ),	/* 5 */
#ifndef _ALPHA_
/* 52 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 54 */	NdrFcShort( 0x0 ),	/* 0 */
/* 56 */	NdrFcShort( 0x10 ),	/* 16 */
/* 58 */	0x4,		/* 4 */
			0x2,		/* 2 */

	/* Parameter pVal */


	/* Parameter pVal */

/* 60 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 62 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 64 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */


	/* Return value */

/* 66 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 68 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 70 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_OriginalHostName */

/* 72 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 74 */	NdrFcShort( 0x6 ),	/* 6 */
#ifndef _ALPHA_
/* 76 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 78 */	NdrFcShort( 0x0 ),	/* 0 */
/* 80 */	NdrFcShort( 0x8 ),	/* 8 */
/* 82 */	0x5,		/* 5 */
			0x2,		/* 2 */

	/* Parameter pVal */

/* 84 */	NdrFcShort( 0x2113 ),	/* 8467 */
#ifndef _ALPHA_
/* 86 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 88 */	NdrFcShort( 0x20 ),	/* Type Offset=32 */

	/* Return value */

/* 90 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 92 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 94 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_DirectProcessID */

/* 96 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 98 */	NdrFcShort( 0x7 ),	/* 7 */
#ifndef _ALPHA_
/* 100 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 102 */	NdrFcShort( 0x0 ),	/* 0 */
/* 104 */	NdrFcShort( 0x10 ),	/* 16 */
/* 106 */	0x4,		/* 4 */
			0x2,		/* 2 */

	/* Parameter pVal */

/* 108 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 110 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 112 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */

/* 114 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 116 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 118 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_DirectThreadID */

/* 120 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 122 */	NdrFcShort( 0x8 ),	/* 8 */
#ifndef _ALPHA_
/* 124 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 126 */	NdrFcShort( 0x0 ),	/* 0 */
/* 128 */	NdrFcShort( 0x10 ),	/* 16 */
/* 130 */	0x4,		/* 4 */
			0x2,		/* 2 */

	/* Parameter pVal */

/* 132 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 134 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 136 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */

/* 138 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 140 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 142 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_DirectHostID */

/* 144 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 146 */	NdrFcShort( 0x9 ),	/* 9 */
#ifndef _ALPHA_
/* 148 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 150 */	NdrFcShort( 0x0 ),	/* 0 */
/* 152 */	NdrFcShort( 0x10 ),	/* 16 */
/* 154 */	0x4,		/* 4 */
			0x2,		/* 2 */

	/* Parameter pVal */

/* 156 */	NdrFcShort( 0x2150 ),	/* 8528 */
#ifndef _ALPHA_
/* 158 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 160 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Return value */

/* 162 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 164 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 166 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_DirectHostName */

/* 168 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 170 */	NdrFcShort( 0xa ),	/* 10 */
#ifndef _ALPHA_
/* 172 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 174 */	NdrFcShort( 0x0 ),	/* 0 */
/* 176 */	NdrFcShort( 0x8 ),	/* 8 */
/* 178 */	0x5,		/* 5 */
			0x2,		/* 2 */

	/* Parameter pVal */

/* 180 */	NdrFcShort( 0x2113 ),	/* 8467 */
#ifndef _ALPHA_
/* 182 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 184 */	NdrFcShort( 0x2e ),	/* Type Offset=46 */

	/* Return value */

/* 186 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 188 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 190 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_PseudoCausalityID */

/* 192 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 194 */	NdrFcShort( 0xb ),	/* 11 */
#ifndef _ALPHA_
/* 196 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 198 */	NdrFcShort( 0x0 ),	/* 0 */
/* 200 */	NdrFcShort( 0x28 ),	/* 40 */
/* 202 */	0x4,		/* 4 */
			0x2,		/* 2 */

	/* Parameter pguid */

/* 204 */	NdrFcShort( 0x4112 ),	/* 16658 */
#ifndef _ALPHA_
/* 206 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 208 */	NdrFcShort( 0x42 ),	/* Type Offset=66 */

	/* Return value */

/* 210 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 212 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 214 */	0x8,		/* FC_LONG */
			0x0,		/* 0 */

	/* Procedure get_TargetHostName */

/* 216 */	0x33,		/* FC_AUTO_HANDLE */
			0x64,		/* 100 */
/* 218 */	NdrFcShort( 0x6 ),	/* 6 */
#ifndef _ALPHA_
/* 220 */	NdrFcShort( 0xc ),	/* x86, MIPS, PPC Stack size/offset = 12 */
#else
			NdrFcShort( 0x18 ),	/* Alpha Stack size/offset = 24 */
#endif
/* 222 */	NdrFcShort( 0x0 ),	/* 0 */
/* 224 */	NdrFcShort( 0x8 ),	/* 8 */
/* 226 */	0x5,		/* 5 */
			0x2,		/* 2 */

	/* Parameter pVal */

/* 228 */	NdrFcShort( 0x2113 ),	/* 8467 */
#ifndef _ALPHA_
/* 230 */	NdrFcShort( 0x4 ),	/* x86, MIPS, PPC Stack size/offset = 4 */
#else
			NdrFcShort( 0x8 ),	/* Alpha Stack size/offset = 8 */
#endif
/* 232 */	NdrFcShort( 0x52 ),	/* Type Offset=82 */

	/* Return value */

/* 234 */	NdrFcShort( 0x70 ),	/* 112 */
#ifndef _ALPHA_
/* 236 */	NdrFcShort( 0x8 ),	/* x86, MIPS, PPC Stack size/offset = 8 */
#else
			NdrFcShort( 0x10 ),	/* Alpha Stack size/offset = 16 */
#endif
/* 238 */	0x8,		/* FC_LONG */
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
/*  4 */	
			0x11, 0x4,	/* FC_RP [alloced_on_stack] */
/*  6 */	NdrFcShort( 0x1a ),	/* Offset= 26 (32) */
/*  8 */	
			0x13, 0x0,	/* FC_OP */
/* 10 */	NdrFcShort( 0xc ),	/* Offset= 12 (22) */
/* 12 */	
			0x1b,		/* FC_CARRAY */
			0x1,		/* 1 */
/* 14 */	NdrFcShort( 0x2 ),	/* 2 */
/* 16 */	0x9,		/* 9 */
			0x0,		/*  */
/* 18 */	NdrFcShort( 0xfffffffc ),	/* -4 */
/* 20 */	0x6,		/* FC_SHORT */
			0x5b,		/* FC_END */
/* 22 */	
			0x17,		/* FC_CSTRUCT */
			0x3,		/* 3 */
/* 24 */	NdrFcShort( 0x8 ),	/* 8 */
/* 26 */	NdrFcShort( 0xfffffff2 ),	/* Offset= -14 (12) */
/* 28 */	0x8,		/* FC_LONG */
			0x8,		/* FC_LONG */
/* 30 */	0x5c,		/* FC_PAD */
			0x5b,		/* FC_END */
/* 32 */	0xb4,		/* FC_USER_MARSHAL */
			0x83,		/* 131 */
/* 34 */	NdrFcShort( 0x0 ),	/* 0 */
/* 36 */	NdrFcShort( 0x4 ),	/* 4 */
/* 38 */	NdrFcShort( 0x0 ),	/* 0 */
/* 40 */	NdrFcShort( 0xffffffe0 ),	/* Offset= -32 (8) */
/* 42 */	
			0x11, 0x4,	/* FC_RP [alloced_on_stack] */
/* 44 */	NdrFcShort( 0x2 ),	/* Offset= 2 (46) */
/* 46 */	0xb4,		/* FC_USER_MARSHAL */
			0x83,		/* 131 */
/* 48 */	NdrFcShort( 0x0 ),	/* 0 */
/* 50 */	NdrFcShort( 0x4 ),	/* 4 */
/* 52 */	NdrFcShort( 0x0 ),	/* 0 */
/* 54 */	NdrFcShort( 0xffffffd2 ),	/* Offset= -46 (8) */
/* 56 */	
			0x11, 0x4,	/* FC_RP [alloced_on_stack] */
/* 58 */	NdrFcShort( 0x8 ),	/* Offset= 8 (66) */
/* 60 */	
			0x1d,		/* FC_SMFARRAY */
			0x0,		/* 0 */
/* 62 */	NdrFcShort( 0x8 ),	/* 8 */
/* 64 */	0x2,		/* FC_CHAR */
			0x5b,		/* FC_END */
/* 66 */	
			0x15,		/* FC_STRUCT */
			0x3,		/* 3 */
/* 68 */	NdrFcShort( 0x10 ),	/* 16 */
/* 70 */	0x8,		/* FC_LONG */
			0x6,		/* FC_SHORT */
/* 72 */	0x6,		/* FC_SHORT */
			0x4c,		/* FC_EMBEDDED_COMPLEX */
/* 74 */	0x0,		/* 0 */
			NdrFcShort( 0xfffffff1 ),	/* Offset= -15 (60) */
			0x5b,		/* FC_END */
/* 78 */	
			0x11, 0x4,	/* FC_RP [alloced_on_stack] */
/* 80 */	NdrFcShort( 0x2 ),	/* Offset= 2 (82) */
/* 82 */	0xb4,		/* FC_USER_MARSHAL */
			0x83,		/* 131 */
/* 84 */	NdrFcShort( 0x0 ),	/* 0 */
/* 86 */	NdrFcShort( 0x4 ),	/* 4 */
/* 88 */	NdrFcShort( 0x0 ),	/* 0 */
/* 90 */	NdrFcShort( 0xffffffae ),	/* Offset= -82 (8) */

			0x0
        }
    };

const CInterfaceProxyVtbl * _HostHook_ProxyVtblList[] = 
{
    ( CInterfaceProxyVtbl *) &_ICallInfoProxyVtbl,
    ( CInterfaceProxyVtbl *) &_IClientCallInfoProxyVtbl,
    0
};

const CInterfaceStubVtbl * _HostHook_StubVtblList[] = 
{
    ( CInterfaceStubVtbl *) &_ICallInfoStubVtbl,
    ( CInterfaceStubVtbl *) &_IClientCallInfoStubVtbl,
    0
};

PCInterfaceName const _HostHook_InterfaceNamesList[] = 
{
    "ICallInfo",
    "IClientCallInfo",
    0
};


#define _HostHook_CHECK_IID(n)	IID_GENERIC_CHECK_IID( _HostHook, pIID, n)

int __stdcall _HostHook_IID_Lookup( const IID * pIID, int * pIndex )
{
    IID_BS_LOOKUP_SETUP

    IID_BS_LOOKUP_INITIAL_TEST( _HostHook, 2, 1 )
    IID_BS_LOOKUP_RETURN_RESULT( _HostHook, 2, *pIndex )
    
}

const ExtendedProxyFileInfo HostHook_ProxyFileInfo = 
{
    (PCInterfaceProxyVtblList *) & _HostHook_ProxyVtblList,
    (PCInterfaceStubVtblList *) & _HostHook_StubVtblList,
    (const PCInterfaceName * ) & _HostHook_InterfaceNamesList,
    0, // no delegation
    & _HostHook_IID_Lookup, 
    2,
    2
};
