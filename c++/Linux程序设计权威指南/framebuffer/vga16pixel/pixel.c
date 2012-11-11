
	/* File: pixel.c */

	#include "color.h"

	#include <unistd.h>
	#include <stdio.h>
	#include <fcntl.h>
	#include <linux/fb.h>		//包含FB信息的结构
	#include <sys/mman.h>

	int main(int argc, char *argv[])
	{
		int i;

		//FB设备的文件描述符
		int fd;

		//结构
		struct fb_fix_screeninfo finfo;
		struct fb_var_screeninfo vinfo;

		//打开设备
		fd = open("/dev/fb0", O_RDWR);
		if (!fd) {
			printf("Cannot open framebuffer device.\n");
			exit(1);
		}
		printf("The framebuffer device was opened successfully.\n");

		//取得固定信息
		if (ioctl(fd, FBIOGET_FSCREENINFO, &finfo)) {
			printf("Error reading fixed information.\n");
			exit(1);
		}
		//取得可变信息
		if (ioctl(fd, FBIOGET_VSCREENINFO, &vinfo)) {
			printf("Error reading variable information.\n");
			exit(1);
		}

		//VGA16 初始化
		vga16_init(fd, &finfo, &vinfo);
		
		//清屏幕
		vga16_clear();

		//画线
		for(i=0; i<200; i++)
			vga16_set_pixel(i, i, COLOR_HI_GREEN);
		
		

		close(fd);
		return 0;
	}
        
