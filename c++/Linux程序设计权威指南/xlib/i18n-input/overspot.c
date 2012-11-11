
	/* File: overspot.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/keysym.h>

	#include <stdio.h>
	#include <locale.h>

	#define F_SIZE		16
	#define COL 		30
	#define ROW		10
	#define W_WIDTH         (F_SIZE * COL)
	#define W_HEIGHT        (F_SIZE * ROW)


	int main(int argc, char **argv)
	{
		Display *display;
		int screen_num;
		Window win;			//窗口ID
		unsigned int width, height;	//窗口尺寸
		unsigned int border_width = 4;	//边界空白
		unsigned int display_width, display_height;//屏幕尺寸
		XEvent report;
		GC gc;
		unsigned long valuemask = 0;
		XGCValues values;
		char *display_name = NULL;
		Atom          protocols;

		//edit
		int           len = 127;
		unsigned char string[128], s_tab[ROW][127];
		KeySym        keysym;
		int           row = 0, col = 0;
		int           i, count = 0;
		Status        status;
		//字体集
		XFontSet fontset;
		char **missing_charsets;
		int num_missing_charsets;
		char *default_string;
		XFontSetExtents *fs_ext;
		int dec;    

		//XIM
		XIM im;
		XIC ic;
		//overspot
		XRectangle    spot, s_rect;
		XVaNestedList preedit_attr, status_attr;

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
		//字体的Extents
		fs_ext = XExtentsOfFontSet(fontset);
		dec = fs_ext->max_logical_extent.height 
			+ fs_ext->max_logical_extent.y;

		width  = W_WIDTH;
		height = W_HEIGHT + dec;

	
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

		protocols = XInternAtom(display, "WM_DELETE_WINDOW", True);
		XSetWMProtocols(display, win, &protocols, 1);

		//显示窗口
		XMapWindow(display, win);

		//联接输入服务器
		if((im = XOpenIM(display, NULL, NULL, NULL)) == NULL){
			printf("Error : XOpenIM !\n");
			exit(1);
		}

		//设置输入服务器的位置
		spot.x = F_SIZE / 2 * col;
		spot.y = F_SIZE * (row + 1) - dec; 
		preedit_attr = XVaCreateNestedList(0,
			XNSpotLocation, &spot,
			XNFontSet, fontset, 
			NULL);
		s_rect.x = 0;
		s_rect.y = F_SIZE * ROW + dec + 2;
		s_rect.width = W_WIDTH;
		s_rect.height = F_SIZE;
		status_attr = XVaCreateNestedList(0,
			XNArea, &s_rect,
			XNFontSet, fontset,
			NULL);
		//建立IC
		if((ic = XCreateIC(im, 
			XNInputStyle, XIMPreeditPosition | XIMStatusNothing,
			XNClientWindow, win, 
			XNPreeditAttributes, preedit_attr,
			XNStatusAttributes, status_attr, NULL)) == NULL){
			printf("Error : XCreateIC() ! \n");
			XCloseIM(im);
			exit(0);
		}
		//释放内存
		XFree(preedit_attr);
		XFree(status_attr);


		//写屏缓冲区初始化
		for(i = 0; i < ROW; i++)s_tab[i][0] = 0;

		//进入事件循环
		while (1)  {

			//取得队列中的事件
			XNextEvent(display, &report);

			//过滤事件
			if(XFilterEvent(&report, None) == True) continue;
			switch  (report.type) {

			//聚焦发声变化
			case FocusIn:
				XSetICFocus(ic); 
				break;
			case FocusOut:
				XUnsetICFocus(ic);
				break;

			//曝光事件, 窗口应重绘
			case Expose:
				//取得最后一个曝光事件
				if (report.xexpose.count != 0) break;
				for ( i=0; i < ROW; i++)
					XmbDrawString(display, win, fontset,gc, 
					0, F_SIZE * (i +1), 
					s_tab[i], strlen(s_tab[i]));
				break;

			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				width = report.xconfigure.width;
				height = report.xconfigure.height;
				break;

			//鼠标点击或有按键, 释放资源则退出
			case KeyPress:
				count = XmbLookupString(ic, 
					(XKeyPressedEvent *) &report,
					string, len, &keysym, &status);
				string[count] = 0;
				if (status == XLookupBoth&&keysym == XK_Return){
					row = (++row) % ROW;
					col = 0;
					s_tab[row][0] = 0;
					XClearArea(display, win, 
						0, F_SIZE * row + dec,
						W_WIDTH, F_SIZE, False);
				} else if (status = XLookupChars 
					|| status == XLookupBoth){
					XmbDrawString(display, win, fontset, gc,
					F_SIZE / 2 * col, F_SIZE * (row + 1),
					string, count);
					for (i = 0; i < count && col < len && 
						string[i]; i++, col++)
						s_tab[row][col] = string[i];
						s_tab[row][col] = 0;
				}
				//更新输入服务器位置
				spot.x = F_SIZE / 2 * col;
				spot.y = F_SIZE * (row + 1);
				preedit_attr = XVaCreateNestedList(0,
					XNSpotLocation, &spot,
					NULL);
				XSetICValues(ic, 
					XNPreeditAttributes, preedit_attr, 
					NULL);
				XFree(preedit_attr);
				break;
			case ClientMessage:
				if (report.xclient.data.l[0] == protocols) {
					XDestroyIC(ic);
					XCloseIM(im);
					XDestroyWindow(display, win);
					XCloseDisplay(display);
					exit(0);
				}
				break;
			default:
				break;
			}
		}
	}
