/* this file contains the actual definitions of */
/* the IIDs and CLSIDs */

/* link this file in with the server and any clients */


/* File created by MIDL compiler version 5.01.0164 */
/* at Mon Feb 11 17:27:16 2002
 */
/* Compiler settings for C:\Program Files\Microsoft Visual Studio\MyProjects\RCWTEST\RCWTEST.idl:
    Oicf (OptLev=i2), W1, Zp8, env=Win32, ms_ext, c_ext
    error checks: allocation ref bounds_check enum stub_data 
*/
//@@MIDL_FILE_HEADING(  )
#ifdef __cplusplus
extern "C"{
#endif 


#ifndef __IID_DEFINED__
#define __IID_DEFINED__

typedef struct _IID
{
    unsigned long x;
    unsigned short s1;
    unsigned short s2;
    unsigned char  c[8];
} IID;

#endif // __IID_DEFINED__

#ifndef CLSID_DEFINED
#define CLSID_DEFINED
typedef IID CLSID;
#endif // CLSID_DEFINED

const IID IID_IFactorial = {0x617C8372,0x022A,0x4605,{0x8E,0x0A,0x2E,0x5F,0x53,0x33,0x31,0xBE}};


const IID LIBID_RCWTESTLib = {0x649CA963,0xEA19,0x4DA3,{0xB0,0xCF,0xBA,0x16,0xE8,0xE2,0x22,0xEE}};


const CLSID CLSID_Factorial = {0x8D3176FD,0x0C97,0x4481,{0x8F,0x66,0xEB,0x78,0x45,0x74,0x17,0xBB}};


#ifdef __cplusplus
}
#endif

