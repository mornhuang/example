
	/* File: font.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>


	void draw(Display *display,  Window win, GC gc, 
		XFontStruct *fs, XFontStruct *fs16)
	{
		char *str1 = "Hello World!";
		char *str2 = "你好，大家好！";

		
		//参考线
		XDrawLine(display, win, gc, 20, 100, 300, 100);
		//画字符串
		XSetFont(display, gc, fs->fid);
		XDrawString(display, win, gc, 20, 100, str1, strlen(str1));
		//计算宽度
		printf("str1 width:%d\n", XTextWidth(fs, str1, strlen(str1)));
		
		//画中文字符串
		XSetFont(display, gc, fs16->fid);
		XDrawString16(display, win, gc, 200, 100, (XChar2b*)str2, 
			strlen(str2)/2);
		//计算宽度
		printf("str2 width:%d\n", XTextWidth16(fs16, 
			(XChar2b *)str2, strlen(str2)));
		
		
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
		char **fonts;
		int font_count;
		XFontStruct *fs, *fs16;
	
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

		//列出所有字体
		printf("All fonts on this server:\n");
		fonts = XListFonts(display, "*", 10000, &font_count);
		for(i=0; i<font_count; i++)
			printf("%s\n", fonts[i]);
		XFreeFontNames(fonts);

		//列出所有G
		printf("All gb2312 fonts on this server:\n");
		fonts = XListFonts(display, "*gb2312*", 10000, &font_count);
		for(i=0; i<font_count; i++)
			printf("%s\n", fonts[i]);
		XFreeFontNames(fonts);

		//载入8x16字体
		if((fs = XLoadQueryFont(display, "9x15")) == NULL){
			printf("Cannot load font 9x15\n");
			exit(1);
		}
		//载入中文16点阵字体
		if((fs16 = XLoadQueryFont(display, "ccs16_1")) == NULL){
			printf("Cannot load Chinese font\n");
			exit(1);
		}

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
				draw(display, win, gc, fs, fs16);
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

