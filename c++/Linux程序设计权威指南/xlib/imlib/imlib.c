
	/* File: imlib.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/extensions/shape.h>
	#include <Imlib.h>

	int main(int argc, char **argv)
	{
		Display *display;
		Window win;
		XSetWindowAttributes attr;

		//Imlib 的数据
		ImlibData *id;
		ImlibImage *im;
		Pixmap p,m;
		int w,h;
     
		display = XOpenDisplay(NULL);

		//初始化
		id = Imlib_init(display);

		//载入图像
		im = Imlib_load_image(id, "penguin.xpm");

		//取得图像的尺寸
		w = im->rgb_width;
		h = im->rgb_height;

		//建立窗口
		win = XCreateWindow(display,DefaultRootWindow(display),
			0,0,w,h,0,id->x.depth, InputOutput,
			id->x.visual,0,&attr);

		//选择事件
		XSelectInput(display,win,StructureNotifyMask);

		//在缓冲区内画图像
		Imlib_render(id, im, w, h);

		//提取图像和图像掩码
		p = Imlib_move_image(id, im);

		//如果图像是非透明的掩码应该是0
		m = Imlib_move_mask(id, im);

		//设置窗口的背景图像
		XSetWindowBackgroundPixmap(display, win, p);

		//如果有图像掩码, 使用掩码
		if (m) XShapeCombineMask(display, win,
			ShapeBounding, 0, 0, m, ShapeSet);

		//显示窗口
		XMapWindow(display,win);

		//同步X Server
		XSync(display,False);

		while(1){
			XEvent ev;
      
			XNextEvent(display,&ev);

			if (ev.type==ConfigureNotify){
				w = ev.xconfigure.width;
				h = ev.xconfigure.height;

				Imlib_render(id, im, w, h);
				Imlib_free_pixmap(id, p);
				p = Imlib_move_image(id, im);
				m = Imlib_move_mask(id, im);
				XSetWindowBackgroundPixmap(display, win, p);
				if (m) XShapeCombineMask(display, win,
					ShapeBounding, 0, 0, m, ShapeSet);
				XClearWindow(display,win);
				XSync(display,False);
			}
		}
	}
