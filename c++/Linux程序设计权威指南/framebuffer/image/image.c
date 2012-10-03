
	/* File: cmapinfo.c */

/*
	#include <unistd.h>
	#include <stdio.h>
	#include <fcntl.h>
	#include <linux/fb.h>		//包含FB信息的结构
	#include <sys/mman.h>
*/

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
                
#include <linux/kd.h>
#include <linux/vt.h>
#include <linux/fb.h>
#include <asm/page.h>



	#include "palette.h"
	#include "image.h"
	
	//定义颜色表
	static unsigned short g_red[256], g_green[256], g_blue[256];
	struct fb_cmap  g_cmap = { 0, 256, g_red, g_green, g_blue };
	struct fb_cmap  *cmap;


	int main(int argc, char *argv[])
	{
		int lp;
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

		//取得信息
		if (ioctl(fd, FBIOGET_FSCREENINFO, &finfo)) {
			printf("Error reading fixed information.\n");
			exit(1);
		}
		if (ioctl(fd, FBIOGET_VSCREENINFO, &vinfo)) {
			printf("Error reading variable information.\n");
			exit(1);
		}

		//取得颜色表信息
		if(vinfo.bits_per_pixel == 8 ||
			finfo.visual == FB_VISUAL_DIRECTCOLOR) {
			cmap = &g_cmap;
                	if (ioctl(fd,FBIOGETCMAP,&g_cmap) < 0) {
                        	perror("ioctl FBIOGETCMAP");
                        	exit(1);
               		}
        	} else {
                	cmap = (struct fb_cmap *)
				malloc(sizeof(struct fb_cmap));
               		if(!cmap){
                        	printf("cmap malloc error\n");
                        	exit(1);
                	}
		}

		//打印颜色表信息
                /*
		printf("start           :[%08x]\n",cmap->start);
        	printf("len             :[%08x]\n",cmap->len);
        	for(lp=0;lp<cmap->len;lp++){
                	printf("%2x : %04x %04x %04x\n", lp, 
			*(cmap->red+lp), *(cmap->green+lp), *(cmap->blue+lp));              
        	}
		*/
        	//设置颜色表
        	cmap->start = 32;
        	cmap->len = 224;
        	for(lp=0;lp<224;lp++){
                	if(cmap->red) *(cmap->red+lp)=
                        	(image_red[lp]<<CHAR_BIT)   + image_red[lp];
                	if(cmap->green) *(cmap->green+lp)=
                        	(image_green[lp]<<CHAR_BIT) + image_green[lp];
                	if(cmap->blue) *(cmap->blue+lp)=
                        	(image_blue[lp]<<CHAR_BIT)  + image_blue[lp];
        	}

        	if(ioctl(fd, FBIOPUTCMAP, cmap)){
                	perror("ioctl FBIOPUTCMAP error\n");
                	exit(1);
        	}

		//画图像
		other_init(fd, &finfo, &vinfo);
		other_clear();

		other_set_image(20, 20, IMAGE_WIDTH, IMAGE_HEIGHT, image_grh);


		close(fd);
		return 0;
	}
        
