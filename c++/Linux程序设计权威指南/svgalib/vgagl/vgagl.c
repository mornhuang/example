
	/* File: vgagl.c */
	#include <stdlib.h>
	#include <stdio.h>
	#include <vga.h>
	#include <vgagl.h>

	#define CAPTURE

	int VGAMODE;
	GraphicsContext *backscreen;
	GraphicsContext *physicalscreen;

	void draw(void)
	{
		int i, j, b, y=0;
		int color = 64;

		//改变颜色表
		for (i = 0; i < 64; i++) {
			b = 63 - i;
			gl_setpalettecolor(color, 0, 0, b);
			for (j = 0; j < 3; j++){
				//画线
				gl_hline(0, y, WIDTH-1, color);
				y++;
			}
			color++;
		}

		//画图元
		gl_fillbox(30, 30, 30, 20, 2);
		gl_circle(100, 80, 30, 3);

		//设置字体和字体颜色
		gl_setfont(8, 8, gl_font8x8);
		gl_setwritemode(FONT_COMPRESSED + WRITEMODE_OVERWRITE);
		gl_setfontcolors(0, vga_white());

		//写字符串
		gl_write(20, 180, "Press any key to exit...");

	}


	int main(int argc, char *argv[])
	{
		//初始化
		vga_init();
		VGAMODE = vga_getdefaultmode();
		if (VGAMODE == -1) VGAMODE = G320x200x256;
		if (!vga_hasmode(VGAMODE)) {
			printf("Mode not available.\n");
			exit(-1);
		}

		//设置模式
		vga_setmode(VGAMODE);

		//设置GraphicsContext
		gl_setcontextvga(VGAMODE);
		physicalscreen = gl_allocatecontext();
		gl_getcontext(physicalscreen);

		//清除屏幕
		gl_clearscreen(0);

		//画图
		draw();

		//抓取屏幕
		#ifdef CAPTURE
		capture();
		#endif

		//复原
		getchar();
		vga_setmode(TEXT);
		return 0;
	}
