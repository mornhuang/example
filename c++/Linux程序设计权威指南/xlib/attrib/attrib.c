
	/* File: attrib.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>



	int main(int argc, char **argv)
	{
		static char *string = "Hello World!";
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
	
		//指定所建立窗口的宽度和高度
		width = display_width/3;
		height = display_height/4;
	
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
		gc = DefaultGC(display, screen_num);

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
				break;

			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				break;

			//鼠标点击显示窗口的某些属性
			case ButtonPress:
				{
				XWindowAttributes xa;
				Window root;
				int x, y;
				unsigned int w, h, border, depth;
					
				XGetWindowAttributes(display,win,&xa);
				printf("geometry:%d %d %d %d\n",
					xa.x,xa.y, xa.width, xa.height);
				printf("override redirect:%d\n", 
					xa.override_redirect);
				XGetGeometry(display,win,&root, &x, &y,
					&w, &h, &border, &depth);
				printf("attrib: x:%d y:%d w:%d h:%d "
					"border:%d depth: %d\n",
					x, y, w, h, border, depth);
				}
				break;
			case KeyPress:
				XFreeGC(display, gc);
				exit(1);
			default:
				
				break;
			}
		}
	}
