
	/* File: mouse.c */
	#include <stdlib.h>
	#include <stdio.h>
	#include <vga.h>
	#include <vgamouse.h>

	int main(int argc, char *argv[])
	{
		int leftpressed, middlepressed;
		int x, y, w, h, rx, ox, oy, button;

		//初始化
		vga_init();
		vga_setmode(G320x200x256);
		w = vga_getxdim();
		h = vga_getydim();
		ox = x = w / 2;
		oy = y = h / 2;
		vga_setcolor(5);
		vga_drawline(ox, oy, x, y);

		//鼠标初始化
		vga_setmousesupport(1);
		mouse_init("/dev/mouse", MOUSE_IMPS2, MOUSE_DEFAULTSAMPLERATE);
		mouse_setxrange(0, w - 1);
		mouse_setyrange(0, h - 1);
		mouse_setwrap(MOUSE_NOWRAP);
		leftpressed = middlepressed = 0;
		mouse_setposition(x, y);

		//移动鼠标画图
		for (;;) {

			//读鼠标事件, 不等待
			mouse_update();

			//获取鼠标位置
			mouse_getposition_6d(&x, &y, NULL, &rx, NULL, NULL);

			//画线
			vga_drawline(ox, oy, x, y);
			ox = x;
			oy = y;

			//获取鼠标按钮状态
			button = mouse_getbutton();
			if (button & MOUSE_LEFTBUTTON) {
	    			if (!leftpressed) {
					leftpressed = 1;
					vga_setcolor(3);
	    			}
			} else {
            			leftpressed = 0;
				vga_setcolor(5);
			}
        		if (button & MOUSE_MIDDLEBUTTON) {
	    			if (!middlepressed) {
                			middlepressed = 1;
            			}
        		} else {
            			middlepressed = 0;
			}

			//右键退出
			if (button & MOUSE_RIGHTBUTTON) break;
    		}

		//复原
		mouse_close();
		vga_setmode(TEXT);

		return 0;
	}
