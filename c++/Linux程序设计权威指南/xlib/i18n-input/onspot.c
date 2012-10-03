
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


	//回调函数
	int     P_StartCB(XIC, XPointer, XPointer);
	int     P_DoneCB(XIC, XPointer, XPointer);
	int     P_DrawCB(XIC, XPointer, XIMPreeditDrawCallbackStruct*);
	int     P_CaretCB(XIC, XPointer, XIMPreeditCaretCallbackStruct*);
	
	int     S_StartCB(XIC, XPointer, XPointer);
	int     S_DoneCB(XIC, XPointer, XPointer);
	int     S_DrawCB(XIC, XPointer, XIMStatusDrawCallbackStruct*);


	Display *display;
	int screen_num;
	Window win;			//窗口ID
	GC gc, rgc;
	XFontSet        fontset;
	int             dec;
	int             row = 0, col = 0;
	char            status_tab[30];
	char            preedit_tab[128];
	char            Feedback[128];
	void            DrawPreeditString(char *, char *);

	int main(int argc, char **argv)
	{
		unsigned int width, height;	//窗口尺寸
		unsigned int border_width = 4;	//边界空白
		unsigned int display_width, display_height;//屏幕尺寸
		XEvent report;
		unsigned long valuemask = 0;
		XGCValues values;
		char *display_name = NULL;
		Atom          protocols;

		//edit
		int           len = 127;
		unsigned char string[128], s_tab[ROW][127];
		KeySym        keysym;
		int           i, count = 0;
		Status        status;
		//字体集
		char **missing_charsets;
		int num_missing_charsets;
		char *default_string;
		XFontSetExtents *fs_ext;

		//XIM
		XIM im;
		XIC ic;
		XVaNestedList preedit_attr, status_attr;

		//回调函数
		XIMCallback   P_StartCB_Str;
		XIMCallback   P_DoneCB_Str;
		XIMCallback   P_DrawCB_Str;
		XIMCallback   P_CaretCB_Str;
		XIMCallback   S_StartCB_Str;
		XIMCallback   S_DoneCB_Str;
		XIMCallback   S_DrawCB_Str;

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

		//建立GC和翻转色GC
		gc = DefaultGC(display, screen_num);
		values.foreground = WhitePixel(display, screen_num);
		values.background = BlackPixel(display, screen_num);
		rgc = XCreateGC(display, win,
			(GCForeground | GCBackground), &values);

		protocols = XInternAtom(display, "WM_DELETE_WINDOW", True);
		XSetWMProtocols(display, win, &protocols, 1);

		//显示窗口
		XMapWindow(display, win);

		//联接输入服务器
		if((im = XOpenIM(display, NULL, NULL, NULL)) == NULL){
			printf("Error : XOpenIM !\n");
			exit(1);
		}

		//设置输入服务器的回调
		P_StartCB_Str.client_data = NULL;
		P_StartCB_Str.callback = (XIMProc)P_StartCB;
		P_DoneCB_Str.client_data = NULL;
		P_DoneCB_Str.callback = (XIMProc)P_DoneCB;
		P_DrawCB_Str.client_data = NULL;
		P_DrawCB_Str.callback = (XIMProc)P_DrawCB;
		P_CaretCB_Str.client_data = NULL;
		P_CaretCB_Str.callback = (XIMProc)P_StartCB;
		preedit_attr = XVaCreateNestedList(0,
			XNPreeditStartCallback, &P_StartCB_Str,
			XNPreeditDoneCallback, &P_DoneCB_Str,
			XNPreeditDrawCallback, &P_DrawCB_Str,
			XNPreeditCaretCallback, &P_CaretCB_Str,
			NULL);
		S_StartCB_Str.client_data = NULL;
		S_StartCB_Str.callback = (XIMProc)S_StartCB;
		S_DoneCB_Str.client_data = NULL;
		S_DoneCB_Str.callback = (XIMProc)S_DoneCB;
		S_DrawCB_Str.client_data = NULL;
		S_DrawCB_Str.callback = (XIMProc)S_DrawCB;
		status_attr = XVaCreateNestedList(0,
			XNStatusStartCallback, &S_StartCB_Str,
			XNStatusDoneCallback, &S_DoneCB_Str,
			XNStatusDrawCallback, &S_DrawCB_Str,
			NULL);
		//建立IC
		if((ic = XCreateIC(im, 
			XNInputStyle, XIMPreeditCallbacks | XIMStatusCallbacks,
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
/*
				if (report.xexpose.count != 0) break;
				for ( i=0; i < ROW; i++)
					XmbDrawString(display, win, fontset,gc, 
					0, F_SIZE * (i +1), 
					s_tab[i], strlen(s_tab[i]));
				DrawPreeditString(preedit_tab, Feedback);
				XmbDrawString(display, win, fontset, gc, 
					20, W_HEIGHT, status_tab, 
					strlen(status_tab));
*/
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
					preedit_tab[0] = 0;
					Feedback[0] = 0;
					XClearArea(display, win, 
						0, F_SIZE * row + dec,
						W_WIDTH, F_SIZE, False);
				} else if (status = XLookupChars 
					|| status == XLookupBoth){
					XmbDrawString(display, win, fontset, gc,
						F_SIZE / 2 * col, F_SIZE * (row + 1),
						string, count);
					for (i = 0; i < count; i++, col++)
						s_tab[row][col] = 0;
					s_tab[row][col] = 0;
					preedit_tab[0] = 0;
					Feedback[0] = 0;
				}
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


	int P_StartCB(XIC ic, XPointer client_data, XPointer P_S)
	{
		printf(" PreeditStartCallback\n");
		return(127);
	}

	int P_DoneCB(XIC ic, XPointer client_data, XPointer P_DN)
	{
		printf(" PreeditDoneCallback\n");
		preedit_tab[0] = 0;
		Feedback[0] = 0;
	}

	int P_DrawCB(XIC ic, XPointer client_data,
	      XIMPreeditDrawCallbackStruct *P_DR)
	{
		int i, j, lcol;
		char preedit_string[128], emp_string[1];
 
		lcol = col;
		emp_string[0] = ' ' ;
  
		for (i = 0; i <(P_DR->chg_length*2); i++, lcol++)
			XmbDrawImageString(display, win, fontset, gc,
				F_SIZE / 2 * (lcol + 2 * P_DR->chg_first),
				F_SIZE * (row + 1), emp_string, 1);

		if (P_DR->text) {
			if (P_DR->text->encoding_is_wchar)
				wctomb(preedit_string, 
					P_DR->text->string.wide_char);
			else
				strcpy(preedit_string, 
					P_DR->text->string.multi_byte);
			for (i = P_DR->chg_first * 2, j = 0; 
				preedit_string[j] != 0; i++, j++)
				preedit_tab[j] = preedit_string[j];
			preedit_tab[i] = 0;
			for (i = P_DR->chg_first, j = 0;
				(unsigned int)i < P_DR->chg_first + 
				P_DR->text->length; i++, j++)
				Feedback[i] = P_DR->text->feedback[j];
			DrawPreeditString(preedit_tab, Feedback);
		}
}

	int P_CaretCB(XIC ic, XPointer client_data,
	      XIMPreeditCaretCallbackStruct *P_C)
	{
		printf(" PreeditCaretCalback\n");
	}

	int S_StartCB(XIC ic, XPointer client_data, XPointer S_S)
	{
		printf(" StatusStartCallback\n");
	}

	int S_DoneCB(XIC ic, XPointer client_data, XPointer S_S)
	{
		printf(" StartusDoneCallback\n");
	}

	int S_DrawCB(XIC ic, XPointer client_data,
	      XIMStatusDrawCallbackStruct *S_DR)
	{
		if (S_DR->type != XIMBitmapType) {
			if (S_DR->data.text->encoding_is_wchar)
				wctomb(status_tab, 
					S_DR->data.text->string.wide_char);
			else
				strcpy(status_tab, 
					S_DR->data.text->string.multi_byte);
			XClearArea(display, win, 0, 
				W_HEIGHT - F_SIZE + dec,W_WIDTH, F_SIZE, False);
			XmbDrawImageString(display, win, fontset, gc,
				20, W_HEIGHT, status_tab, strlen(status_tab));
		}
	}

	void DrawPreeditString(char *p, char *fp)
	{
		int i;
		int lcol = col;

		dec = 2;
		for (i = 0; i < strlen(fp); i++) {

			if (fp[i] == XIMReverse)
				XmbDrawImageString(display, win, fontset, rgc,
					F_SIZE / 2 * lcol, F_SIZE * (row + 1),
					p, mblen(p, 3));
			else {
				XmbDrawImageString(display, win, fontset, rgc,
					F_SIZE / 2 * lcol, F_SIZE * (row + 1),
					p, mblen(p, 3));
				XDrawLine(display, win, gc,
					F_SIZE / 2 * lcol, F_SIZE * (row + 1),
					F_SIZE / 2 * (lcol + mblen(p, 3)) 
					- (dec - 1), F_SIZE * (row + 1) + 1);
			}
			lcol += mblen(p, 3);
			p += mblen(p, 3);
		}
	}
