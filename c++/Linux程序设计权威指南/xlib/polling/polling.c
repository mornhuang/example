
	/* File: polling.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>

	int main(int argc, char **argv)
	{
		int i;
		Display *display;
		int screen_num;
		Window win;			//窗口ID
		unsigned int width, height;	//窗口尺寸
		unsigned int border_width = 4;	//边界空白
		XEvent report;
		long mask = ExposureMask | KeyPressMask 
			| KeyReleaseMask | ButtonPressMask 
			| ButtonReleaseMask | EnterWindowMask
			| LeaveWindowMask | VisibilityChangeMask
			| StructureNotifyMask;

		// 和X 服务器连接
		if ( (display=XOpenDisplay(NULL)) == NULL )
		{
			printf("Cannot connect to X server\n");
			exit(-1);
		}

		screen_num = DefaultScreen(display);
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
		XSelectInput(display, win,  mask);

		//显示窗口
		XMapWindow(display, win);

		//进入事件循环
		while (1)  {

			//检查队列中的事件, 不等待
			if(!XCheckWindowEvent(display, win, mask, &report)){
				printf(".");
				fflush(stdout);
				usleep(100000);
				continue;
			}
			printf("\n");
			fflush(stdout);

			switch  (report.type) {

			//曝光事件, 窗口应重绘
			case Expose: printf("Expose event\n"); break;


			//按钮事件
			case ButtonPress: 
				printf("ButtonPress event\n");break;
			case ButtonRelease: 
				printf("ButtonRelease event\n"); break;

			//鼠标指针事件
			case EnterNotify:
				printf("EnterNotify event\n"); break;
			case LeaveNotify:
				printf("LeaveNotify event\n"); break;

			//键盘按键事件
			case KeyPress:
				printf("KeyPress event\n"); break;
			case KeyRelease:
				printf("KeyRelease event\n"); break;


			//窗口的尺寸改变, 位置移动, 窗口堆栈发生变化, 
			//或边界宽度发生变化
			case CirculateNotify:
				printf("CirculateNotify event\n"); break;
			case ConfigureNotify:
				printf("ConfigureNotify event\n"); break;

			//选择(Selection)事件, 客户程序间通讯
			case ClientMessage:
				printf("ClientMessage event\n"); break;

			default: break;
			}
		}
	}

