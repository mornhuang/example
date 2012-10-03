/*
  file: GAMEDEFS.H

  purpose:
    basic defines and other useful stuff
*/

#ifndef _GAMEDEFS_H_
#define _GAMEDEFS_H_

/* BYTE & WORD already defined in xlib.h */

typedef int             BOOL;
typedef unsigned int    DWORD;

#define FARPTR(x)       (MK_FP(FP_SEG(x),FP_OFF(x)))

#define FALSE           0
#define TRUE            (!FALSE)

#define PIC		0x20
#define NONSPECIFIC_EOI	0x20

#define UP              0
#define RIGHT           1
#define DOWN            2
#define LEFT            3

#define CENTER          4
#define BUTTON_1        5
#define BUTTON_2        6
#define NONE            7
#define QUIT            8

#endif
