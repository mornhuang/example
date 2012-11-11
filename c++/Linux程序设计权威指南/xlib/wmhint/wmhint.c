
	/* File: wmhint.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>
	#include "icon.xbm"

	int main(int argc, char **argv)
	{
		int i;
		Display *display;
		int screen_num;
		Window win;			//窗口ID
		unsigned int width, height;	//窗口尺寸
		unsigned int border_width = 4;	//边界空白
		unsigned int display_width, display_height;//屏幕尺寸
		int count;
		XEvent report;
		GC gc;
		unsigned long valuemask = 0;
		XGCValues values;
		char *display_name = NULL;
		Colormap default_cmap;
		XColor color;
		int rc;			//函数返回值

		//wm
		//窗口名属性
		XTextProperty window_name_property;
		//图标名属性
		XTextProperty icon_name_property;
		//尺寸暗示结构指针
		XSizeHints* win_size_hints;
		//窗口暗示结构指针
		XWMHints* win_hints;
		//图标所使用的图像(xbm)
		Pixmap icon_pixmap;

		//窗口名和图标名
		char* window_name = "Hello World!";
		char* icon_name = "Another World!";


		printf("This program has been iconified.\n");

		// 和X 服务器连接
		if ( (display=XOpenDisplay(display_name)) == NULL )
		{
			printf("Cannot connect to X server %s\n", 
					XDisplayName(display_name));
			exit(-1);
		}

		//获得缺省的 screen_num
		screen_num = DefaultScreen(display);

		//获得屏幕的宽度和高度
		display_width = DisplayWidth(display, screen_num);
		display_height = DisplayHeight(display, screen_num);

		//获取缺省的颜色表
		default_cmap = DefaultColormap(display, screen_num);
	
		//指定所建立窗口的宽度和高度
		width = 400;
		height = 300;

		//建立窗口
		win = XCreateSimpleWindow(display, 	//display
			RootWindow(display,screen_num), //父窗口
			0, 0, width, height, 		//位置和大小
			border_width, 			//边界宽度
			BlackPixel(display,screen_num), //前景色
			WhitePixel(display,screen_num));//背景色
	
		//选择窗口感兴趣的事件掩码
		XSelectInput(display, win, 
			ExposureMask | KeyPressMask | 
			ButtonPressMask | StructureNotifyMask);

		//建立GC
		gc = XCreateGC(display, win, valuemask, &values);


		//显示窗口
		XMapWindow(display, win);

		//把窗口名转换为X属性(Property)
		rc = XStringListToTextProperty(&window_name, 
			1, &window_name_property);

		if (rc == 0) {
			printf("XStringListToTextProperty - out of memory\n");
			exit(1);
		}

		//把图标名转换为X属性(Property)
		rc = XStringListToTextProperty(&icon_name, 
			1, &icon_name_property);
		if (rc == 0) {
			printf("XStringListToTextProperty - out of memory\n");
			exit(1);
		}

		//设置窗口名和图标名
		XSetWMName(display, win, &window_name_property);
		XSetWMIconName(display, win, &icon_name_property);

		//为窗口尺寸暗示分配结构
		win_size_hints = XAllocSizeHints();
		if (!win_size_hints) {
			printf("XAllocSizeHints - out of memory\n");
			exit(1);
		}
		//限制窗口的最小尺寸
		win_size_hints->flags = PSize | PMinSize | PMaxSize;
		win_size_hints->min_width = 320;
		win_size_hints->min_height = 200;
		win_size_hints->max_width = 640;
		win_size_hints->max_height = 480;
		win_size_hints->base_width = width;
		win_size_hints->base_height = height;

		//告诉窗口管理器
		XSetWMNormalHints(display, win, win_size_hints);
		XFree(win_size_hints);

		//载入位图
		icon_pixmap = XCreateBitmapFromData(display, 
			win, icon_bits, icon_width, icon_height);
		if (!icon_pixmap) {
			printf("XCreateBitmapFromData - error creating\n");
			exit(1);
		}

		//为窗口管理器暗示分配内存
		win_hints = XAllocWMHints();
		if (!win_hints) {
			printf("XAllocWMHints - out of memory\n");
			exit(1);
		}

		//设置图标的图像, 状态和位置
		win_hints->flags = IconPixmapHint|StateHint|IconPositionHint;
		win_hints->icon_pixmap = icon_pixmap;
		win_hints->initial_state = IconicState;
		win_hints->icon_x = 0;
		win_hints->icon_y = 0;

		//高速窗口管理器
		XSetWMHints(display, win, win_hints);
		XFree(win_hints);


		//进入事件循环
		while (1)  {

			//取得队列中的事件
			XNextEvent(display, &report);
			switch  (report.type) {

			//曝光事件, 窗口应重绘
			case Expose:
				//取得最后一个曝光事件
				if (report.xexpose.count != 0) break;
				break;

			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				width = report.xconfigure.width;
				height = report.xconfigure.height;
				break;

			//鼠标点击或有按键, 释放资源则退出
			case ButtonPress:
			case KeyPress:
				XFreeGC(display, gc);
				exit(1);
			default:
				
				break;
			}
		}
	}

