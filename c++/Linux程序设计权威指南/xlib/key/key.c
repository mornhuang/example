
	/* File: key.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>
	#include <X11/keysym.h>

	#include <stdio.h>

	char *getenv();

	void draw(Display *display, Window win, GC gc, int w, int h,
		int count, int code, unsigned int state, char *str, char *sym)
	{
		char buf[128];
		XClearArea(display, win, 0, 0, w, h, False);

		sprintf(buf, "Keycode: %d", code);
		XDrawImageString(display, win, gc, 20, 100, buf, strlen(buf));

		sprintf(buf, "XLookupString return  count: %d", count);
		XDrawImageString(display, win, gc, 20, 140, buf, strlen(buf));

		sprintf(buf, "XLookupString return keysym: %s", sym);
		XDrawImageString(display, win, gc, 20, 180, buf, strlen(buf));

		//state
		sprintf(buf, "Modifiers: Control: %s Shift: %s Alt: %s",
			(state & ControlMask) ? "*" : " ",
			(state & ShiftMask) ? "*" : " ",
			(state & Mod1Mask) ? "*" : " ");
		XDrawImageString(display, win, gc, 20, 220, buf, strlen(buf));


		if(count > 0){
			sprintf(buf, "XLookupString return buffer: %s", str);
			XDrawImageString(display, win, gc, 20, 260, 
				buf, strlen(buf));
		}

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
		XEvent report;
		GC gc;
		unsigned long valuemask = 0;
		XGCValues values;

		//处理按键事件的变量
		char buffer[1024] = "";
		KeySym keysym;			//按键符号
		int count;			//返回的buffer中内容的长度
		XComposeStatus compose;		//Compose状态

		// 和X 服务器连接
		if ( (display=XOpenDisplay(getenv("DISPLAY"))) == NULL ){
			printf("Cannot connect to X server\n");
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
			ExposureMask | KeyPressMask | 
			ButtonPressMask | StructureNotifyMask);

		//建立GC
		//gc = XCreateGC(display, win, valuemask, &values);
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

			//键盘事件
			case KeyPress:
				//查找 keysym, 返回键码ASCII值
				count = XLookupString(&report.xkey,buffer, 1024,
					&keysym, &compose);
				//显示信息
				draw(display, win, gc, width, height,
					count, report.xkey.keycode, 
					report.xkey.state,
					buffer, XKeysymToString(keysym));
				//一些感兴趣的键
				if(keysym == XK_Return)
					printf("KeySym:XK_Return\n");
				else if(keysym == XK_Up)
					printf("KeySym:XK_Up\n");
				else if(keysym == XK_Down)
					printf("KeySym:XK_Down\n");
				else if(keysym == XK_Left)
					printf("KeySym:XK_Left\n");
				else if(keysym == XK_Right)
					printf("KeySym:XK_Right\n");
				else if(keysym >= XK_KP_0 &&
					keysym <= XK_KP_9)
					printf("Keypad: 0-9\n");
				else if(keysym >= XK_F1 &&
					keysym <= XK_F12)
					printf("Function keys\n"); 
				else if(keysym == XK_BackSpace)
					printf("KeySym: XK_BackSpace\n");
				else if(keysym == XK_Delete)
					printf("KeySym: XK_Delete\n");
				else if(keysym >= XK_space &&
					keysym <= XK_asciitilde)
					printf("Normal keys\n");
				else {
					XBell(display, 100);
					printf("I am not interested in it\n");
				}

				break;


			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				width = report.xconfigure.width;
				height = report.xconfigure.height;
				break;

			//鼠标点击或有按键, 释放资源则退出
			case ButtonPress:
				XFreeGC(display, gc);
				exit(1);
			default:
				
				break;
			}
		}
	}

