
	/* File: pointer.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>

	int main(int argc, char **argv)
	{
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
		char *getenv();

		//draw
		int x1, y1, x2, y2;
	
		// 和X 服务器连接
		if ( (display=XOpenDisplay(getenv("DISPLAY"))) == NULL ){
			printf("Cannot connect to X server %s");
			exit(-1);
		}

		//获得缺省的 screen_num
		screen_num = DefaultScreen(display);

		//获得屏幕的宽度和高度
		display_width = DisplayWidth(display, screen_num);
		display_height = DisplayHeight(display, screen_num);
	
		//指定所建立窗口的宽度和高度
		width = display_width/2;
		height = display_height/2;
	
		//建立窗口
		win = XCreateSimpleWindow(display, 	//display
			RootWindow(display,screen_num), //父窗口
			0, 0, width, height, 		//位置和大小
			border_width, 			//边界宽度
			BlackPixel(display,screen_num), //前景色
			WhitePixel(display,screen_num));//背景色
	
		//选择窗口感兴趣的事件掩码
		XSelectInput(display, win, 
			ExposureMask | 
			ButtonPressMask |		//按下
			ButtonReleaseMask |		//抬起
			ButtonMotionMask | 		//移动
			KeyPressMask |
			StructureNotifyMask);

		//建立GC
		gc = XCreateGC(display, win, valuemask, &values);
               	XSetLineAttributes(display, gc, 3, 
			LineSolid, CapRound, JoinRound);

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
				XClearArea(display, win, 0, 0, 
					width, height, False);
				break;

			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				width = report.xconfigure.width;
				height = report.xconfigure.height;
				break;

			//鼠标左键开始绘图.
			case ButtonPress:
				if(report.xbutton.button == Button1){
					x1 = report.xbutton.x;
					y1 = report.xbutton.y;
					XDrawPoint(display, win, gc, x1, y1);
				} else if(report.xbutton.button == Button3){
					XFreeGC(display, gc);
					exit(1);
				}
				break;
			case ButtonRelease:
				if(report.xbutton.button == Button1){
					x2 = report.xbutton.x;
					y2 = report.xbutton.y;
					XDrawLine(display, win, gc, 
						x1, y1, x2, y2);
				}
				break;
			case MotionNotify:
				if(report.xmotion.state & Button1Mask){
					x2 = report.xmotion.x;
					y2 = report.xmotion.y;
					XDrawLine(display, win, gc,
						x1, y1, x2, y2);
					x1 = x2;
					y1 = y2;
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

