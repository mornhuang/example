/* this file contains the actual definitions of */
/* the IIDs and CLSIDs */

/* link this file in with the server and any clients */


/* File created by MIDL compiler version 3.01.75 */
/* at Fri Jun 27 14:23:49 1997
 */
/* Compiler settings for MeowMoniker.idl:
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

const IID IID_DIFeline = {0xCB18CB8E,0xC7CC,0x11D0,{0x9A,0x44,0x00,0x00,0x86,0x00,0xA1,0x05}};


const IID LIBID_MEOWMONIKERLib = {0xE900C000,0xBD9D,0x11D0,{0x9A,0x44,0x00,0x00,0x86,0x00,0xA1,0x05}};


const CLSID CLSID_CoMeowMoniker = {0xE900C00E,0xBD9D,0x11D0,{0x9A,0x44,0x00,0x00,0x86,0x00,0xA1,0x05}};


const CLSID CLSID_CoFeline = {0xCB18CB8F,0xC7CC,0x11D0,{0x9A,0x44,0x00,0x00,0x86,0x00,0xA1,0x05}};


#ifdef __cplusplus
}
#endif

