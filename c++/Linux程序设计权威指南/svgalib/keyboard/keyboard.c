	/* File: keyboard.c */

	#include <unistd.h>
	#include <stdlib.h>
	#include <stdio.h>
	#include <signal.h>
	#include <vga.h>
	#include <vgakeyboard.h>

	int main(int argc, char *argv[])
	{
		//x, y 当前位置, w, h 屏幕尺寸
		int x, y, w, h;

		printf("Arrow keys to draw, 'q' or ESC key to quit.\n");
		printf("press any key to start...\n");
		while(!vga_getkey());

		//初始化
		vga_init();

		vga_setmode(G320x200x256);

		//键盘初始化
		if (keyboard_init()) {
			printf("Could not initialize keyboard.\n");
			exit(1);
		}

		//小键盘起作用
		keyboard_translatekeys(TRANSLATE_CURSORKEYS|TRANSLATE_DIAGONAL);

		//取得屏幕尺寸,初始化位置
		w = vga_getxdim();
		h = vga_getydim();
		x = w / 2;
		y = h / 2;
    		//设置颜色 
		vga_setcolor(3);

		for (;;) { 

			//画点
			vga_drawpixel(x, y);

			usleep(10000);

			keyboard_update();

			//监测箭头键是否按下, 移动画点
			if (keyboard_keypressed(SCANCODE_CURSORLEFT)) x--;
			if (keyboard_keypressed(SCANCODE_CURSORRIGHT)) x++;
			if (keyboard_keypressed(SCANCODE_CURSORUP)) y--;
			if (keyboard_keypressed(SCANCODE_CURSORDOWN)) y++;

			//检查是否超出边界
			if (x < 0) x = 0;
			if (x >= w) x = w - 1;
			if (y < 1) y = 1;
			if (y >= h) y = h - 1;

			//Q 和 ESC 键退出
			if (keyboard_keypressed(SCANCODE_Q) ||
				keyboard_keypressed(SCANCODE_ESCAPE)) break;
		}

		//复原
		keyboard_close();
		vga_setmode(TEXT);
		exit(0);
	}

