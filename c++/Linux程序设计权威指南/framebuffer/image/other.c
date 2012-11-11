#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <termios.h>
#include <signal.h>
#include <errno.h>
#include <signal.h>
#include <limits.h>
#include <sys/ioctl.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <sys/stat.h>

#include <linux/fb.h>

#include <asm/page.h>

static unsigned char *fb_mem;
static unsigned char *fb_smem_addr;
static int fb_smem_len;
static int fb_mem_offset;
static int fb_bits_per_pixel;
static int fb_line_length;

void other_set_pixel(int x, int y, unsigned long color)
{
    unsigned char bit;
    unsigned char mask;
    unsigned char *dest;
    unsigned char *p;
    switch (fb_bits_per_pixel)
    {
        case 1:
        {
           unsigned char cl = ((unsigned char) 1) << (8 - 1);
           dest = fb_mem + fb_line_length * y + (x >> 3);
           bit = cl >> (x & 7);
           mask = ~(cl >> (x & 7));
           *dest = (*dest & mask) | bit;
           break;
        }
        case 2:
        {
           unsigned char cl = ((unsigned char) (color & 0x3)) << (8 - 2);
           dest = fb_mem + fb_line_length * y + (x >> 2);
           mask = ~(0xC0 >> ((x & 3) << 1));
           bit = cl >> ((x & 3) << 1);
           *dest = (*dest & mask) | bit;
           break;
        }
        case 8:
        {
           dest = fb_mem + fb_line_length * y + x;
           *dest = (unsigned char) (color & 0xff);
           break;
        }
        case 16:
        {
           int cl = (int) color;
           dest = fb_mem + fb_line_length * y + (x << 1);
           p = (unsigned char *) &cl;
           *dest = *p;
           *(dest+1) = *(p+1);
           break;
        }
        case 32:
        {
           dest = fb_mem + fb_line_length * y + (x << 2);
           p = (unsigned char *) &color;
           *dest = *p;
           *(dest+1) = *(p+1);
           *(dest+2) = *(p+2);
           *(dest+3) = *(p+3);
           break;
        }
    }
}

void other_rectangle(int x1, int y1, int x2, int y2, int color)
{
	int i, j;
	for(i = x1; i < x2; i++)
	for(j = y1; j < y2; j++)
		other_set_pixel(i, j, color);
}

int other_init(int fd, 
	struct fb_fix_screeninfo *finfo, struct fb_var_screeninfo *vinfo)
{

	fb_smem_addr = finfo->smem_start;
	fb_smem_len  = finfo->smem_len;
	fb_line_length = finfo->line_length;
	fb_bits_per_pixel = vinfo->bits_per_pixel;

    	fb_mem_offset = (unsigned long)(fb_smem_addr)&(~PAGE_MASK);
    	fb_mem = mmap(NULL, fb_smem_len + fb_mem_offset,
		PROT_WRITE,MAP_SHARED,fd,0);

    	if (-1L == (long)fb_mem) {
		perror("mmap");
		exit(1);
    	}
	return 0;
}

int other_clear()
{
	memset(fb_mem + fb_mem_offset, 0, fb_smem_len);
	return 0;
}

unsigned char other_read_pixel(int x, int y)
{
        return (unsigned char)(fb_mem)[x + y * fb_line_length];
}

void other_set_image(int x0, int y0, int w, int h, unsigned char *image)
{
        int x, y;
        unsigned char *p;

        for(x=x0 ; x<x0+w ; x++){
                for(y=y0 ; y<y0+h ; y++){
                        p = image + (y-y0)*w + (x-x0);
                        if(*p != 0x20) 
			other_set_pixel(x, y, (unsigned long)(*p));
                }
        }
}

