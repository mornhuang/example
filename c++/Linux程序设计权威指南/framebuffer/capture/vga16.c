
/* File: vga16.c */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <endian.h>
#include <unistd.h>
#include <fcntl.h>
#include <termios.h>
#include <signal.h>
#include <errno.h>
#include <signal.h>
#include <limits.h>
#include <sys/types.h>
#include <sys/ioctl.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <sys/io.h>
#include <linux/fb.h>

#include <asm/io.h>
#include <asm/system.h>

//globals

static unsigned char *fb_mem;
static int fb_line_length;
static int fb_smem_length;
static int fb_smem_offset;

#define GRAPHICS_ADDR_REG 0x3ce         /* Graphics address register. */
#define GRAPHICS_DATA_REG 0x3cf         /* Graphics data register. */

#define SET_RESET_INDEX 0               /* Set/Reset Register index. */
#define ENABLE_SET_RESET_INDEX 1        /* Enable Set/Reset Register index. */
#define DATA_ROTATE_INDEX 3             /* Data Rotate Register index. */
#define GRAPHICS_MODE_INDEX 5           /* Graphics Mode Register index. */
#define BIT_MASK_INDEX 8                /* Bit Mask Register index. */


#define __OUT1_U(s,x) \
static void out##s(unsigned x value, unsigned short port) {

#define __OUT2_U(s,s1,s2) \
__asm__ __volatile__ ("out" #s " %" s1 "0,%" s2 "1"

#define __OUT_U(s,s1,x) \
__OUT1_U(s,x) __OUT2_U(s,s1,"w") : : "a" (value), "Nd" (port)); } \
__OUT1_U(s##_p,x) __OUT2_U(s,s1,"w") __FULL_SLOW_DOWN_IO : : "a" (value), "Nd" (port));} \

#define __IN1_U(s) \
static RETURN_TYPE in##s(unsigned short port) { RETURN_TYPE _v;

#define __IN2_U(s,s1,s2) \
__asm__ __volatile__ ("in" #s " %" s2 "1,%" s1 "0"

#define __IN_U(s,s1,i...) \
__IN1_U(s) __IN2_U(s,s1,"w") : "=a" (_v) : "Nd" (port) ,##i ); return _v; } \
__IN1_U(s##_p) __IN2_U(s,s1,"w") __FULL_SLOW_DOWN_IO : "=a" (_v) : "Nd" (port) ,##i ); return _v; } \

#define __INS_U(s) \
static void ins##s(unsigned short port, void * addr, unsigned long count) \
{ __asm__ __volatile__ ("cld ; rep ; ins" #s \
: "=D" (addr), "=c" (count) : "d" (port),"0" (addr),"1" (count)); }

#define __OUTS_U(s) \
static void outs##s(unsigned short port, const void * addr, unsigned long count) \
{ __asm__ __volatile__ ("cld ; rep ; outs" #s \
: "=S" (addr), "=c" (count) : "d" (port),"0" (addr),"1" (count)); }

__OUT_U(b,"b", char)
__OUT_U(w,"w", short)
__OUT_U(l,, int)


/* The VGA's weird architecture often requires that we read a byte and
   write a byte to the same location.  It doesn't matter *what* byte
   we write, however.  This is because all the action goes on behind
   the scenes in the VGA's 32-bit latch register, and reading and writing
   video memory just invokes latch behavior.

   To avoid race conditions (is this necessary?), reading and writing
   the memory byte should be done with a single instruction.  One
   suitable instruction is the x86 bitwise OR.  The following
   read-modify-write routine should optimize to one such bitwise
   OR. */
static inline void rmw(volatile char *p)
{
        *p |= 1;
}

/* Set the Graphics Mode Register.  Bits 0-1 are write mode, bit 3 is
   read mode. */
static inline void setmode(int mode)
{
        outb(GRAPHICS_MODE_INDEX, GRAPHICS_ADDR_REG);
        outb(mode, GRAPHICS_DATA_REG);
}

/* Select the Bit Mask Register. */
static inline void selectmask(void)
{
        outb(BIT_MASK_INDEX, GRAPHICS_ADDR_REG);
}

/* Set the value of the Bit Mask Register.  It must already have been
   selected with selectmask(). */
static inline void setmask(int mask)
{
        outb(mask, GRAPHICS_DATA_REG);
}

/* Set the Data Rotate Register.  Bits 0-2 are rotate count, bits 3-4
   are logical operation (0=NOP, 1=AND, 2=OR, 3=XOR). */
static inline void setop(int op)
{
        outb(DATA_ROTATE_INDEX, GRAPHICS_ADDR_REG);
        outb(op, GRAPHICS_DATA_REG);
}

/* Set the Enable Set/Reset Register.  The code here always uses value
   0xf for this register.  */
static inline void setsr(int sr)
{
        outb(ENABLE_SET_RESET_INDEX, GRAPHICS_ADDR_REG);
        outb(sr, GRAPHICS_DATA_REG);
}

/* Set the Set/Reset Register. */
static inline void setcolor(int color)
{
        outb(SET_RESET_INDEX, GRAPHICS_ADDR_REG);
        outb(color, GRAPHICS_DATA_REG);
}

/* Set the value in the Graphics Address Register. */
static inline void setindex(int index)
{
        outb(index, GRAPHICS_ADDR_REG);
}


int  vga16_init(int fd,  
	struct fb_fix_screeninfo *finfo, struct fb_var_screeninfo *vinfo)
{


	static size_t map_amt;

	if(ioperm(0x3c0, 0x20, 1) == -1){
		printf("Cannot init vga16 mode.\n");
		exit(1);
	}

	//assign globals
	fb_line_length = finfo->line_length;
	fb_smem_length = finfo->smem_len;
	fb_smem_offset = 0;

	map_amt = 0x10000;
	map_amt = (map_amt + getpagesize() -1 )/ getpagesize() * getpagesize();
	fb_mem = mmap(NULL, map_amt, PROT_READ | PROT_WRITE, 
		MAP_SHARED, fd, 0);

	if(!fb_mem || fb_mem == (unsigned char *)-1){
		printf("Error init...\n");
		exit(1);
	}
	return 0;
}

void vga16_clear()
{
	memset(fb_mem, 0, fb_smem_length);
}

void vga16_set_pixel(int x, int y, unsigned long c)
{
	volatile char *where;
	setmode(0);
	setop(0);
	setcolor(c);
	selectmask();
	setmask(0x80 >> (x % 8));
	where = fb_mem + y * 80 + x/8;
	rmw(where);
}

void vga16_rectangle(int x1, int y1, int x2, int y2, int color)
{
	int i, j;
	for(i=x1; i<x2; i++)
	for(j=x1; j<x2; j++)
		vga16_set_pixel(i, j, color);
}

void vga16_putc(unsigned char *cdat, int yy, int xx, int fg, int bg)
{
        int y;
        char *where = fb_mem + xx/8 + yy * fb_line_length;

        setmode(2);
        setop(0);
        setsr(0xf);
        setcolor(fg);
        selectmask();

        setmask(0xff);
        *where = bg;
        rmb();
        *(volatile char*)where; /* fill latches */
        setmode(3);
        wmb();
        *(volatile char*)where; /* fill latches */
        setmode(3);
        wmb();
        for (y = 0; y < 16; y++, where += fb_line_length)
                *where = cdat[y];
        wmb();
}

static inline void set_read_plane (int plane)
{
        outb (4, 0x3ce);
        outb (plane, 0x3cf);
}

int vga16_read_pixel(int x,int y)
{
        volatile char * src;
        int             plane;
        int             c = 0;

        src = fb_mem + x / 8 + y * fb_line_length;
        for(plane=0; plane<4; ++plane) {
                set_read_plane(plane);
                if(*src & (0x80 >> (x % 8)))
                        c |= 1 << plane;
        }
        return c;
}
