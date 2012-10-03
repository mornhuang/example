
	/* File: advanced.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>

	//globals for simplicity
	int screen_num;
	Colormap cmap;
	GC gc;
	GC dimgc, lightgc, panelgc;

	typedef struct _button {
		Window  win;
		int x, y, w, h;
		char *label;
	} button;


	button buttons[4] = {
		{0, 0, 0, 0, 0, "black"},
		{0, 0, 0, 0, 0, "red"},
		{0, 0, 0, 0, 0, "green"},
		{0, 0, 0, 0, 0, "blue"}
	};

	void create_button(int n, Display *display, Window parent,
		int x, int y, int w, int h)
	{

		buttons[n].x = x;
		buttons[n].y = y;
		buttons[n].w = w;
		buttons[n].h = h;
		buttons[n].win = XCreateSimpleWindow(display, 	//display
			parent, 			//父窗口
			x, y, w, h, 		//位置和大小
			0, 			//边界宽度
			BlackPixel(display,DefaultScreen(display)), //前景色
			WhitePixel(display,DefaultScreen(display)));//背景色
		//选择窗口感兴趣的事件掩码
		XSelectInput(display, buttons[n].win, 
			ExposureMask | 
			ButtonPressMask |		//按下
			ButtonReleaseMask |		//抬起
			ButtonMotionMask | 		//移动
			KeyPressMask |
			StructureNotifyMask);
		XMapWindow(display, buttons[n].win);

        }

	void wait_button_release(Display *display)
	{
        	XEvent ev;
        	XGrabServer(display);
        	while(1){
                	XNextEvent(display, &ev);
                	switch  (ev.type) {
                	        case ButtonRelease:
                                	XUngrabServer(display);
                                	return;
                        	default:
                                	break;
                	}
        	}
	}

	void process_button(int n, Display *display)
	{
		XColor color;
		int w = buttons[n].w;
		int h = buttons[n].h;

		//border
		XDrawLine(display, buttons[n].win, dimgc, 0, 0, w, 0);
		XDrawLine(display, buttons[n].win, dimgc, 0, 1, w, 1);
		XDrawLine(display, buttons[n].win, dimgc, 0, 1, 0, h-1);
		XDrawLine(display, buttons[n].win, dimgc, 1, 1, 1, h-2);
		XDrawLine(display, buttons[n].win, lightgc, 0, h-1, w-1, h-1);
		XDrawLine(display, buttons[n].win, lightgc, 1, h-2, w-2, h-2);
		XDrawLine(display, buttons[n].win, lightgc, w-1, 0, w-1, h-1);
		XDrawLine(display, buttons[n].win, lightgc, w-2, 1, w-2, h-1);

		wait_button_release(display);

		//border
		XDrawLine(display, buttons[n].win, lightgc, 0, 0, w, 0);
		XDrawLine(display, buttons[n].win, lightgc, 0, 1, w, 1);
		XDrawLine(display, buttons[n].win, lightgc, 0, 1, 0, h-1);
		XDrawLine(display, buttons[n].win, lightgc, 1, 1, 1, h-2);
		XDrawLine(display, buttons[n].win, dimgc, 0, h-1, w-1, h-1);
		XDrawLine(display, buttons[n].win, dimgc, 1, h-2, w-2, h-2);
		XDrawLine(display, buttons[n].win, dimgc, w-1, 0, w-1, h-1);
		XDrawLine(display, buttons[n].win, dimgc, w-2, 1, w-2, h-1);

		//set color
		if(!XParseColor(display, cmap, buttons[n].label, &color)){
                        printf("Cannot parse color name\n");
                        exit(1);
                }
                if(!XAllocColor(display, cmap, &color)){
                        printf("Cannot allocate color\n");
                        exit(1);
                }
                XSetForeground(display, gc, color.pixel);
	}

	void button_flush(int n, Display *display)
	{
		int w = buttons[n].w;
		int h = buttons[n].h;

		//fill
		XFillRectangle(display, buttons[n].win, panelgc, 0, 0, w, h);
		//border
		XDrawLine(display, buttons[n].win, lightgc, 0, 0, w, 0);
		XDrawLine(display, buttons[n].win, lightgc, 0, 1, w, 1);
		XDrawLine(display, buttons[n].win, lightgc, 0, 1, 0, h-1);
		XDrawLine(display, buttons[n].win, lightgc, 1, 1, 1, h-2);
		XDrawLine(display, buttons[n].win, dimgc, 0, h-1, w-1, h-1);
		XDrawLine(display, buttons[n].win, dimgc, 1, h-2, w-2, h-2);
		XDrawLine(display, buttons[n].win, dimgc, w-1, 0, w-1, h-1);
		XDrawLine(display, buttons[n].win, dimgc, w-2, 1, w-2, h-1);
		//label
		XDrawString(display, buttons[n].win, gc, 10, 16,
			buttons[n].label, strlen(buttons[n].label));
	}

	GC create_gc(Display *display, Window win, char *rgb)
	{
		XColor color;
		GC mygc;
		unsigned long valuemask = 0;
		XGCValues values;

		mygc = XCreateGC(display, win, valuemask, &values);

		if(!XParseColor(display, cmap, rgb, &color)){
                        printf("Cannot parse color name:%s\n", rgb);
                        exit(1);
                }
                if(!XAllocColor(display, cmap, &color)){
                        printf("Cannot allocate color\n");
                        exit(1);
                }
                XSetForeground(display, mygc, color.pixel);

		return mygc;
	}

	int main(int argc, char **argv)
	{
		Display *display;
		Window win;			//窗口ID
		Window win_draw;
		unsigned int width, height;	//窗口尺寸
		unsigned int border_width = 4;	//边界空白
		unsigned int display_width, display_height;//屏幕尺寸
		int i;
		int count;
		XEvent report;
		unsigned long valuemask = 0;
		XGCValues values;
		char *getenv();
		XColor color;

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
		cmap = DefaultColormap(display, screen_num);
	
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

		panelgc = create_gc(display, win, "#C0C0C0");
		dimgc = create_gc(display, win, "#666666");
		lightgc = create_gc(display, win, "#F5F5F5");

		//建立绘图窗口
		win_draw = XCreateSimpleWindow(display, //display
			win, 				//父窗口
			10, 10, width - 140,height - 40,//位置和大小
			border_width, 			//边界宽度
			BlackPixel(display,screen_num), //前景色
			WhitePixel(display,screen_num));//背景色
		//选择窗口感兴趣的事件掩码
		XSelectInput(display, win_draw, 
			ExposureMask | 
			ButtonPressMask |		//按下
			ButtonReleaseMask |		//抬起
			ButtonMotionMask | 		//移动
			KeyPressMask |
			StructureNotifyMask);

		//建立按钮窗口
		for(i=0; i<4; i++)
			create_button(i, display, win,
				width - 100, i * 30 + 30, 80, 20);


		//显示窗口
		XMapWindow(display, win);
		XMapWindow(display, win_draw);

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
				for(i=0;i<4;i++) button_flush(i, display);
				break;

			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				width = report.xconfigure.width;
				height = report.xconfigure.height;
				break;

			//鼠标左键开始绘图.
			case ButtonPress:
				if(report.xbutton.button == Button1 &&
				   report.xbutton.window == win_draw){
					x1 = report.xbutton.x;
					y1 = report.xbutton.y;
					XDrawPoint(display, win_draw, gc,x1,y1);
				} else if(report.xbutton.button == Button1 &&
                                    report.xbutton.window == buttons[0].win){
					process_button(0, display);
				} else if(report.xbutton.button == Button1 &&
                                    report.xbutton.window == buttons[1].win){
					process_button(1, display);
				} else if(report.xbutton.button == Button1 &&
                                    report.xbutton.window == buttons[2].win){
					process_button(2, display);
				} else if(report.xbutton.button == Button1 &&
                                    report.xbutton.window == buttons[3].win){
					process_button(3, display);
				} else if(report.xbutton.button == Button3){
					XFreeGC(display, gc);
					exit(1);
				}
				break;
			case ButtonRelease:
				if(report.xbutton.button == Button1 &&
				   report.xbutton.window == win_draw){
					x2 = report.xbutton.x;
					y2 = report.xbutton.y;
					XDrawLine(display, win_draw, gc, 
						x1, y1, x2, y2);
				}
				break;
			case MotionNotify:
				if(report.xmotion.state & Button1Mask &&
				   report.xmotion.window == win_draw){
					x2 = report.xmotion.x;
					y2 = report.xmotion.y;
					XDrawLine(display, win_draw, gc,
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

