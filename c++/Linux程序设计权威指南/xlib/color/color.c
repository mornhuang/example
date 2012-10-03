
	/* File: color.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>

	void setcolor(Display *display, Colormap cmap, GC gc, char *name)
	{
		XColor color;

		//传递颜色表
		if(!XParseColor(display, cmap, name, &color)){
			printf("Cannot parse color name:%s\n", name);
			exit(1);
		}
		//分配颜色
		if(!XAllocColor(display, cmap, &color)){
			printf("Cannot allocate color\n");
			exit(1);
		}
		//设置GC的前景色
		XSetForeground(display, gc, color.pixel);
	}


	void draw(Display *display, Colormap cmap, Window win, GC gc)
	{
		char *str1 = "Hello World!";
		
		setcolor(display, cmap, gc, "red");
		XDrawLine(display, win, gc, 20, 100, 300, 100);
		XDrawString(display, win, gc, 20, 100, str1, strlen(str1));

		setcolor(display, cmap, gc, "#0000FF");
		XFillRectangle(display, win, gc, 20, 200, 200, 150);
		
	}

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
		//width = display_width/2;
		//height = display_height/2;
		width = 600;
		height = 400;

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

		//进入事件循环
		while (1)  {

			//取得队列中的事件
			XNextEvent(display, &report);
			switch  (report.type) {

			//曝光事件, 窗口应重绘
			case Expose:
				//取得最后一个曝光事件
				if (report.xexpose.count != 0) break;
				draw(display, default_cmap, win, gc);
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
				XCloseDisplay(display);
				exit(1);
			default:
				
				break;
			}
		}
	}

