
	/* File: i18n-output.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>
	#include <locale.h>



	int main(int argc, char **argv)
	{
		char *string = "这是中文和English的混和字符串!";
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

		//字体集
		XFontSet fontset;
		char **missing_charsets;
		int num_missing_charsets;
		char *default_string;
                wchar_t wcstr[80];		//宽字符串
		int num;			//转换出的宽字符串长度.


		//设置locale	
		if((setlocale(LC_ALL, "")) == NULL){
			printf("cannot set locale\n");
			exit(1);
		}

		//判断X是否支持locale
		if(!XSupportsLocale()){
			printf("X does not support current locale\n");
			exit(1);
		}

		//设置locale修饰
		if(XSetLocaleModifiers(NULL)){
			printf("Cannot set locale modifiers\n");
			exit(1);
		}
	
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
		gc = XCreateGC(display, win, valuemask, &values);

		//建立字体集
		fontset = XCreateFontSet(display, 
			"8x16,-*-song-medium-r-normal--16-*-*-*-*-*-gb2312.1980-0",
			&missing_charsets, &num_missing_charsets,
			&default_string);

		if(num_missing_charsets > 0){
			int i;
			printf("Following charsets are missing:\n");
			for(i=0; i<num_missing_charsets; i++){
				printf("Missing %d: %s\n", 
					i, missing_charsets[i]);
			}
			printf("\nDefault string:%s\n", default_string);
			XFreeStringList(missing_charsets);
		}

                num = mbstowcs(wcstr, string, strlen(string));

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
				XmbDrawString(display, win, fontset, gc, 
					50, 100,
					string, strlen(string));
				XwcDrawString(display, win, fontset, gc,
					50, 140,
					(wchar_t *)wcstr, num);
	
				break;

			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				width = report.xconfigure.width;
				height = report.xconfigure.height;
				break;

			//鼠标点击或有按键, 释放资源则退出
			case ButtonPress:
			case KeyPress:
				XFreeFontSet(display, fontset);
				XFreeGC(display, gc);
				exit(1);
			default:
				
				break;
			}
		}
	}
