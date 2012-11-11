/* this file contains the actual definitions of */
/* the IIDs and CLSIDs */

/* link this file in with the server and any clients */


/* File created by MIDL compiler version 3.01.76 */
/* at Sat Sep 06 22:09:23 1997
 */
/* Compiler settings for ..\include\COMChat.idl:
    Os (OptLev=s), W1, Zp8, env=Win32, ms_ext, c_ext
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

const IID IID_IChatSession = {0x5223A050,0x2441,0x11d1,{0xAF,0x4F,0x00,0x60,0x97,0x6A,0xA8,0x86}};


const IID IID_IChatSessionEvents = {0x5223A051,0x2441,0x11d1,{0xAF,0x4F,0x00,0x60,0x97,0x6A,0xA8,0x86}};


const IID IID_IChatSessionManager = {0x5223A052,0x2441,0x11d1,{0xAF,0x4F,0x00,0x60,0x97,0x6A,0xA8,0x86}};


#ifdef __cplusplus
}
#endif

