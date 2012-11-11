
	/* File: capture.c */

	#include <unistd.h>
	#include <stdio.h>
	#include <fcntl.h>
	#include <linux/fb.h>		//包含FB信息的结构
	#include <sys/mman.h>

	int main(int argc, char *argv[])
	{
		//FB设备的文件描述符
		int fd;
		int x, y;
		int c;
        	int lp;
		unsigned char ch;
		FILE *fp = fopen("screendump.xpm", "w");

		//结构
		struct fb_fix_screeninfo finfo;
		struct fb_var_screeninfo vinfo;

		//打开设备
		fd = open("/dev/fb0", O_RDWR);
		if (!fd) {
			printf("Cannot open framebuffer device.\n");
			exit(1);
		}

		//取得固定信息
		if (ioctl(fd, FBIOGET_FSCREENINFO, &finfo)) {
			printf("Error reading fixed information.\n");
			exit(1);
		}
		if (ioctl(fd, FBIOGET_VSCREENINFO, &vinfo)) {
			printf("Error reading variable information.\n");
			exit(1);
		}

		//VGA16 模式
		if(vinfo.bits_per_pixel == 4){
			vga16_init(fd, &finfo, &vinfo);
			fprintf(fp, "/* XPM */\n");
			fprintf(fp, "static char *xxxx[] = {\n");
			fprintf(fp, "/* width height num_colors chars_per_pixel */\n");
			fprintf(fp, "\"    640    480       16            1\",\n");
			fprintf(fp, "/* colors */\n");
			fprintf(fp, "\"0	c #000000\",\n");
			fprintf(fp, "\"1	c #0000aa\",\n");
			fprintf(fp, "\"2	c #00aa00\",\n");
			fprintf(fp, "\"3	c #00aaaa\",\n");
			fprintf(fp, "\"4	c #aa0000\",\n");
			fprintf(fp, "\"5	c #aa00aa\",\n");
			fprintf(fp, "\"6	c #aaaa00\",\n");
			fprintf(fp, "\"7	c #aaaaaa\",\n");
			fprintf(fp, "\"8	c #555555\",\n");
			fprintf(fp, "\"9	c #5555ff\",\n");
			fprintf(fp, "\"a	c #55ff55\",\n");
			fprintf(fp, "\"b	c #55ffff\",\n");
			fprintf(fp, "\"c	c #ff5555\",\n");
			fprintf(fp, "\"d	c #ff55ff\",\n");
			fprintf(fp, "\"e	c #ffff55\",\n");
			fprintf(fp, "\"f	c #ffffff\",\n");
			for(y = 0; y < 480; y ++){
				fprintf(fp, "\"");
				for(x = 0; x < 640; x ++){
					c = vga16_read_pixel(x, y);
					fprintf(fp, "%x", c);
				}
				fprintf(fp, "\",\n");
			}
			fprintf(fp, "};\n");
		} else if(vinfo.bits_per_pixel == 8){	//256 色模式
	        	unsigned short g_red[256], g_green[256], g_blue[256];
		        struct fb_cmap cmap={ 0, 256, g_red, g_green, g_blue };

			other_init(fd, &finfo, &vinfo);

                        if (ioctl(fd,FBIOGETCMAP,&cmap) < 0) {
                                perror("ioctl FBIOGETCMAP");
                                exit(1);
                        }


			fprintf(fp, "/* XPM */\n");
			fprintf(fp, "static char *xxxx[] = {\n");
			fprintf(fp, "/* width height num_colors chars_per_pixel */\n");
			fprintf(fp, "\"    %d    %d       256            2\",\n",
				vinfo.xres, vinfo.yres);
			fprintf(fp, "/* colors */\n");
        		for(lp=0;lp<256;lp++){
                		fprintf(fp, "\"%02x	c #%02x%02x%02x\",\n", lp, 
					(unsigned char)(*(cmap.red+lp)), 
					(unsigned char)(*(cmap.green+lp)), 
					(unsigned char)(*(cmap.blue+lp)));
			}
			for(y = 0; y < vinfo.yres; y ++){
				fprintf(fp, "\"");
				for(x = 0; x < vinfo.xres; x ++){
					ch = other_read_pixel(x, y);
					fprintf(fp, "%02x", ch);
				}
				fprintf(fp, "\",\n");
			}
			fprintf(fp, "};\n");
		}
		fclose(fp);

		close(fd);
		return 0;
	}


