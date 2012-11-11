
	/* File: shape.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>
	#include <X11/keysym.h>
	#include <X11/extensions/shape.h>
	#include <X11/cursorfont.h>
	#include <X11/xpm.h>


	#include <stdio.h>

	int main(int argc, char **argv)
	{
		int i;
		Display *display;
		int screen_num;
		Window win;			//窗口ID
		unsigned int width, height;	//窗口尺寸
		unsigned int border_width = 4;	//边界空白
		unsigned int display_width, display_height,display_depth;
		int count;
		XEvent report;
		GC gc;
		unsigned long valuemask = 0;
		XGCValues values;
		XSetWindowAttributes setwinattr;
		char *display_name = NULL;
		Colormap default_cmap;
		Visual *default_visual;
		XpmAttributes xpmattributes;	//xpm
		XVisualInfo *visual_info, vinfo_template;
		int nmatches;
		XColor color;
		Cursor cursor;
		Window root, child;
		int root_x, root_y, win_x, win_y, orig_x, orig_y;
		unsigned int mask_return;

		//pixmap
		Pixmap background_pixmap, shape_pixmap;
		char *pixmap, *mask;
		unsigned char *mask_bits;
		int xhot, yhot;

		// 和X 服务器连接
		if ( (display=XOpenDisplay(display_name)) == NULL )
		{
			printf("Cannot connect to X server %s\n", 
					XDisplayName(display_name));
			exit(-1);
		}

		//获得缺省的 screen_num
		screen_num = DefaultScreen(display);

		//获得信息
		display_width = DisplayWidth(display, screen_num);
		display_height = DisplayHeight(display, screen_num);
		display_depth = DefaultDepth(display, screen_num);

		//建立图像, 获得高度和宽度, 以建立窗口
		XReadBitmapFileData("penguin.xbm", &width, &height,
			&mask_bits, &xhot, &yhot);

	
		//建立窗口
		win = XCreateSimpleWindow(display, 	//display
			RootWindow(display,screen_num), //父窗口
			0, 0, width, height, 		//位置和大小
			border_width, 			//边界宽度
			BlackPixel(display,screen_num), //前景色
			WhitePixel(display,screen_num));//背景色

		//
		default_visual = DefaultVisual(display, screen_num);
		default_cmap = DefaultColormap(display, screen_num);
		//获得Visual类型
		vinfo_template.visualid = XVisualIDFromVisual(default_visual);
		visual_info = XGetVisualInfo(display, VisualIDMask,
                               &vinfo_template, &nmatches);

		xpmattributes.visual = default_visual;
		xpmattributes.colormap = default_cmap;
		xpmattributes.depth = display_depth;
		xpmattributes.valuemask = XpmVisual | XpmColormap | XpmDepth;
		XpmReadFileToPixmap(display, win, "penguin.xpm",
			&background_pixmap, &shape_pixmap,
			&xpmattributes);
		XFree(visual_info);

		setwinattr.background_pixmap = background_pixmap;
		setwinattr.override_redirect = True;
		cursor = XCreateFontCursor(display, XC_heart);
		setwinattr.cursor = cursor;
		valuemask = CWBackPixmap | CWOverrideRedirect | CWCursor;
		XChangeWindowAttributes(display, win, valuemask, &setwinattr);

		//读入pixmap作为图像背景
		shape_pixmap = XCreateBitmapFromData(display, win, mask_bits,
			width, height);
		XShapeCombineMask(display, win, ShapeBounding, 
			0, 0, shape_pixmap, ShapeSet);
	
		//选择窗口感兴趣的事件掩码
		XSelectInput(display, win, 
			ExposureMask | KeyPressMask | ButtonPressMask |
			ButtonReleaseMask | StructureNotifyMask | 
			ButtonMotionMask | PointerMotionHintMask | 
			EnterWindowMask | LeaveWindowMask);

		//建立GC
		//gc = XCreateGC(display, win, valuemask, &values);


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

			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				width = report.xconfigure.width;
				height = report.xconfigure.height;
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
					&mask_return);
				orig_x = root_x - win_x;
				orig_y = root_y - win_y;
				break;
			case MotionNotify:
				{
				int rx, ry, winx, winy;
				//取得指针相对于根窗口的位置和窗口的位置
				XQueryPointer(display,win,&root,&child,
                                        &rx, &ry, &winx, &winy, &mask_return);
				//移动窗口
				XMoveWindow(display, win, 
					rx - root_x + orig_x,
					ry - root_y + orig_y);
				}
				break;
			case ButtonRelease:
				break;
			case KeyPress:
				exit(0);
				break;
			default:
				break;
			}
		}
	}
