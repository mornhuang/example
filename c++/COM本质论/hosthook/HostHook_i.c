/* this file contains the actual definitions of */
/* the IIDs and CLSIDs */

/* link this file in with the server and any clients */


/* File created by MIDL compiler version 3.01.75 */
/* at Tue Oct 21 21:50:50 1997
 */
/* Compiler settings for .\HostHook.idl:
    Oicf (OptLev=i2), W1, Zp8, env=Win32, ms_ext, c_ext
    error checks: none
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

const IID IID_ICallInfo = {0xF23ADD50,0x4992,0x11d1,{0x99,0x1C,0x00,0x60,0x97,0x58,0x5A,0x3C}};


const IID IID_IClientCallInfo = {0xF23ADD54,0x4992,0x11d1,{0x99,0x1C,0x00,0x60,0x97,0x58,0x5A,0x3C}};


const IID LIBID_CallInfoLib = {0xF23ADD51,0x4992,0x11d1,{0x99,0x1C,0x00,0x60,0x97,0x58,0x5A,0x3C}};


const CLSID CLSID_CallInfo = {0xF23ADD52,0x4992,0x11d1,{0x99,0x1C,0x00,0x60,0x97,0x58,0x5A,0x3C}};


#ifdef __cplusplus
}
#endif

