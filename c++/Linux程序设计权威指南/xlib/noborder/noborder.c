
	/* File: noborder.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>
	#include <X11/keysym.h>

	#include <stdio.h>


	void draw(Display *display, Window win, GC gc)
	{
		char *msg1 = "Buttons to move window";
		char *msg2 = "Arrow keys to resize window";
		char *msg3 = "Escape key to exit";

		XDrawString(display, win, gc, 20, 60, msg1, strlen(msg1));
		XDrawString(display, win, gc, 20, 80, msg2, strlen(msg2));
		XDrawString(display, win, gc, 20, 140, msg3, strlen(msg3));
	}

	int main(int argc, char **argv)
	{
		static char *string = "Hello World!";
		Display *display;
		int screen_num;
		Window win;			//窗口ID
		unsigned int width, height;	//窗口尺寸
		unsigned int border_width = 0;	//边界空白
		unsigned int display_width, display_height;//屏幕尺寸
		int count;
		XEvent report;
		GC gc;
		unsigned long valuemask = 0;
		XGCValues values;
		char *display_name = NULL;
		//attribute vars
	        XSizeHints size_hints;
		XSetWindowAttributes attrib;
		unsigned long attribmask;
		//moving window
		Window root, child;
		unsigned int mask;
		int root_x, root_y, win_x, win_y, orig_x, orig_y;
	
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
			100, 100, width, height, 		//位置和大小
			border_width, 			//边界宽度
			BlackPixel(display,screen_num), //前景色
			WhitePixel(display,screen_num));//背景色

		//设置属性
		attrib.override_redirect = True;
		attribmask = CWOverrideRedirect;
		XChangeWindowAttributes(display, win, attribmask, &attrib);


	
		//选择窗口感兴趣的事件掩码
		XSelectInput(display, win, 
			ExposureMask | KeyPressMask | 
			ButtonMotionMask | ButtonPressMask | ButtonReleaseMask
			| StructureNotifyMask);

		//建立GC
		gc = DefaultGC(display, screen_num);

		//显示窗口
		XMapWindow(display, win);

		//设置为键盘聚焦窗口
		XSetInputFocus(display, win, False, CurrentTime);

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
				break;

			//取得位置
			case ButtonPress:
				//把窗口浮动到最上方
				XRaiseWindow(display, win);
				//设置键盘聚焦
				XSetInputFocus(display, win, False,CurrentTime);
				//取得指针相对于根窗口的位置和窗口的位置
				XQueryPointer(display,win,&root,&child,
					&root_x, &root_y, &win_x, &win_y,
					&mask);
				orig_x = root_x - win_x;
				orig_y = root_y - win_y;
				break;
			case MotionNotify:
				{
				int rx, ry, winx, winy;
				//取得指针相对于根窗口的位置和窗口的位置
				XQueryPointer(display,win,&root,&child,
                                        &rx, &ry, &winx, &winy, &mask);
				//移动窗口
				XMoveWindow(display, win, 
					rx - root_x + orig_x,
					ry - root_y + orig_y);
				}
				break;
			case ButtonRelease:
				break;
			case KeyPress:
				{
				char buffer[1024] = "";
				KeySym keysym;     //按键符号
				int count;         //返回的buffer中内容的长度
				XComposeStatus compose; //Compose状态
                                XWindowAttributes xa;
                                unsigned int w, h;

                                XGetWindowAttributes(display,win,&xa);
				w = xa.width; h = xa.height;

				//尖头移动窗口
				count = XLookupString(&report.xkey,buffer, 1024,
                                        &keysym, &compose);
				if(keysym == XK_Up){
					if(h > 10) h -= 2;
					//改变窗口大小
					XResizeWindow(display, win, w, h);
				} else if(keysym == XK_Down) {
					h += 2;
					XResizeWindow(display, win, w, h);
				} else if(keysym == XK_Left) {
					if(w > 10) w -= 2;
					XResizeWindow(display, win, w, h);
				} else if(keysym == XK_Right) {
					w += 2;
					XResizeWindow(display, win, w, h);
				} else if(keysym == XK_Escape) {
					exit(0);
				}
				}
				break;
			default:
				
				break;
			}
		}
	}
