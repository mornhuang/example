/* this file contains the actual definitions of */
/* the IIDs and CLSIDs */

/* link this file in with the server and any clients */


/* File created by MIDL compiler version 3.01.76 */
/* at Sun Jun 01 23:10:55 1997
 */
/* Compiler settings for RectPoint.idl:
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

const IID IID_IPoint = {0xCCA7F35D,0xDAF3,0x11D0,{0x8C,0x53,0x00,0x80,0xC7,0x39,0x25,0xBA}};


const IID IID_IRect = {0xCCA7F35F,0xDAF3,0x11D0,{0x8C,0x53,0x00,0x80,0xC7,0x39,0x25,0xBA}};


const IID LIBID_RECTPOINTLib = {0xCCA7F350,0xDAF3,0x11D0,{0x8C,0x53,0x00,0x80,0xC7,0x39,0x25,0xBA}};


const CLSID CLSID_Point = {0xCCA7F35E,0xDAF3,0x11D0,{0x8C,0x53,0x00,0x80,0xC7,0x39,0x25,0xBA}};


const CLSID CLSID_Rect = {0xCCA7F360,0xDAF3,0x11D0,{0x8C,0x53,0x00,0x80,0xC7,0x39,0x25,0xBA}};


#ifdef __cplusplus
}
#endif

