
	/* File: cursor.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>
	#include <X11/cursorfont.h>

	#include <stdio.h>

	void draw(Display *display, Window win, GC gc)
	{

		XDrawLine(display, win, gc, 0, 0, 200, 200);
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
		XEvent report;			//X 事件
		GC gc;				//GC
		unsigned long valuemask = 0;
		XGCValues values;
		char *display_name = NULL;
		Colormap default_cmap;		//缺省颜色表
		Cursor cursor;			//光标
		Pixmap bitmap;			//光标的pixmap
		unsigned int bitmap_width, bitmap_height;
		int hotspot_x, hotspot_y;	//光标的热点位置
		int rc;				//返回值
		XColor cursor_fg, cursor_bg;	//光标的颜色

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

		//读入位图
		rc = XReadBitmapFile(display, win,
                             "icon.xbm",		//位图文件名
                             &bitmap_width, &bitmap_height,//返回位图尺寸
                             &bitmap,			//位图图像
                             &hotspot_x, &hotspot_y);	//热点
		switch (rc) {
        		case BitmapOpenFailed:
				printf("Cannot open file icon.xbm\n");
				exit(1);
			case BitmapFileInvalid:
				printf("bitmap file is not valid.\n");
				exit(1);
			case BitmapNoMemory:
				printf("No enough memory.\n");
			exit(1);
		}

		//分配颜色
		rc = XAllocNamedColor(display, default_cmap, 
			"red", &cursor_fg, &cursor_fg);
		if (rc == 0) { 
			printf("Canot allocate color.\n");
			exit(1);
		}
		rc = XAllocNamedColor(display, default_cmap,
                            "white", &cursor_bg, &cursor_bg);
		if (rc == 0) {
			printf("Canot allocate color\n");
			exit(1);
		}

		//建立光标
		cursor = XCreatePixmapCursor(display, 
			bitmap, 	//光标形状
			bitmap,		//掩码形状
			&cursor_fg, 	//前景色
			&cursor_bg,	//背景色
			5, 4);		//热点位置, 靠近左上方



                //让 window 使用光标
                XDefineCursor(display, win, cursor);

		XSync(display, False);

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
				draw(display, win, gc);
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

