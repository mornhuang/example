
	/* File: draw.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>


	void draw(Display *display, Window win, GC gc, int width, int height)
	{
		int i, x, y;
                unsigned int line_width = 0;
                int line_style = LineSolid;
                int cap_style  = CapRound;
                int join_style = JoinRound;
		XPoint points[5] = {
			{400, 200},
			{600, 200},
			{450, 300},
			{500, 150},
			{550, 300}};
			

		//画点
		for(i = 0; i< width; i+=3)
			XDrawPoint(display, win, gc, i, 10);

		//画线
		y = 30;
		for(i = 0; i< 10; i+=2){
                	XSetLineAttributes(display, gc, i, line_style,
                        	cap_style, join_style);
			XDrawLine(display, win, gc, 10, y, width-10, y);
			y += 20;
		}

               	XSetLineAttributes(display, gc, 2, line_style,
                        	cap_style, join_style);
		//画矩形
		XDrawRectangle(display, win, gc, 10, y, 50, 30);
		
		//画弧线
		//参考框架
		XDrawRectangle(display, win, gc, 100, y, 200, 200);
		//0度到90度的圆弧
		XDrawArc(display, win, gc, 100, y, 200, 200, 0, 90*64);
		//填充90度到120度的圆弧
		XFillArc(display, win, gc, 100, y, 200, 200, 90*64, 120*64);

		//填充多边形
		XFillPolygon(display, win, gc, points, 5, 
			Complex, EvenOddRule);


	}

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
	
				//写字符串
				draw(display, win, gc, width, height);
	
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

