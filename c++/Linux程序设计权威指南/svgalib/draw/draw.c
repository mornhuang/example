
	/* File: draw.c */

	#include <vga.h>

	int main(int argc, char *argv[])
	{
		
		int i;
		int oldmode;
		int mode = G320x200x256;
		int width, height, colors;

		//获得当前的模式
		oldmode = vga_getcurrentmode();

		//初始化
		vga_init();

		//判断是否支持该模式
		if(vga_hasmode(mode)) 
			vga_setmode(mode);
		else {
			printf("No such mode\n");
			exit(1);
		}
		//取得信息
		width = vga_getxdim();
		height = vga_getydim();
		colors = vga_getcolors();

		//绘图
		for(i=0; i<colors; i++){
			vga_setcolor(i);
			vga_drawline(0, i, width-1, i);
		}
			

		vga_setcolor(3);
		for(i=0; i<50; i++) vga_drawpixel(i*4, 20);

		vga_setcolor(4);
		vga_drawline(100, 100, 300, 200);

		vga_setcolor(5);
		vga_drawline(0, 0, width-1, 0);
		vga_drawline(0, height-1, width-1, height-1);
		vga_drawline(0, 0, 0, height-1);
		vga_drawline(width-1, 0, width-1, height-1);

		//等待按键
		while(!vga_getkey());

		//恢复原来的模式 
		vga_setmode(oldmode);

		return 0;
	}

