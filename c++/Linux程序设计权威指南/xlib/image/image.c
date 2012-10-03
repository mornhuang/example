
	/* File: image.c */

	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>

	#include <stdio.h>

	#define COLORS  8
	#define IMAGE_WIDTH   320
	#define IMAGE_HEIGHT   200
	#define MAXDEPTH 200

	typedef struct {
		double real, imag;
	} complex;

	static void complexInit(complex *z, double r, double i)
	{
		z->real = r; z->imag = i;
		return;
	}

	static void complexAdd(complex *z, complex u)
	{
		z->real += u.real;
		z->imag += u.imag;
	}
	static void complexSub(complex *z, complex u)
	{
		z->real -= u.real;
		z->imag -= u.imag;
	}
	static void complexMult(complex *z, complex u)
	{
		double tmp;
		tmp = z->real*u.real - z->imag*u.imag;
		z->imag = z->real*u.imag + z->imag*u.real;
		z->real = tmp;
	}

	static double complexReal(complex *z)
	{
		return z->real;
	}

	static double complexImag(complex *z)
	{
		return z->imag;
	}

	static void complexPrint(complex *z)
	{
		printf("%.2g+%.2gi", complexReal(z), complexImag(z));
	}

	static double complexAbsSquare(complex *z)
	{
		return z->real*z->real+ z->imag*z->imag;
	}


	static int julia(complex z, complex c)
	{
		int n;
		/* f(z) := z*z - c */

		for(n=0; n<MAXDEPTH; n++){
			complexMult(&z, z);
			complexSub(&z, c);
			/* if(complexAbs(&z)>2.0) */
			if(complexAbsSquare(&z)>4.0)
				return n;
		}

		return 0;
	}


	void draw(Display *display, Window win, GC gc,
		XImage *img, XColor rgb[], int colors)
	{
		complex initial, point;
		int i, j;
		int w = IMAGE_WIDTH, h = IMAGE_HEIGHT;
		complexInit(&initial, 0.0, 0.0);
		printf("Rendering [%3d,%3d] fractal, please wait.\n", w, h);

		for(j=0; j<h; j++)
		for(i=0; i<w; i++) {
			int c;
			complexInit(&point, 2.5*(i-w/3)/w, 2.1*(j-h/2)/h);
			c = julia(initial, point);
			if(i%25==0) {
				printf("[%4d,%4d] = %4d\r", i, j, c);
			}
			img->f.put_pixel(img, i, j, rgb[c%colors].pixel);
		}
	}

	void reverse_image(XImage *img)
	{
		unsigned long pixelvalue1, pixelvalue2;
		int y;
		int left_x, right_x;

		for (left_x=0 ; left_x<IMAGE_WIDTH/2 ; left_x++){
			for (y=0 ; y<IMAGE_HEIGHT ; y++){
                		pixelvalue1 = XGetPixel(img, left_x, y);
				right_x = IMAGE_WIDTH - left_x;
				if (left_x != right_x){
					pixelvalue2 = XGetPixel(img,right_x, y);
					XPutPixel(img, left_x, y, pixelvalue2);
				}
				XPutPixel(img, right_x, y, pixelvalue1);
			}
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
		int count;
		XEvent report;
		char *display_name = NULL;
		GC gc;
		//颜色表
		Colormap default_cmap;
		XColor rgb[COLORS];
		//图像
		XImage *img, *cimg = NULL;	//图像和所拷贝的图像
		XVisualInfo vis, *vlist;	//Visual 信息
		Visual *visual;			//Visual
		int depth;
		int match;
		int temp;
		int w, h;
		unsigned char *bit_data, *bitp, *datap;
		unsigned char *tmpdata;
		int bytes_per_line;		//每行字节数

		// 和X 服务器连接
		if ( (display=XOpenDisplay(display_name)) == NULL )
		{
			printf("Cannot connect to X server %s\n", 
					XDisplayName(display_name));
			exit(-1);
		}

		//获得缺省的 screen_num
		screen_num = DefaultScreen(display);
		//建立GC
		gc = DefaultGC(display, screen_num);
		//获得颜色深度
		depth = DefaultDepthOfScreen(DefaultScreenOfDisplay(display));
		//获得屏幕的宽度和高度
		display_width = DisplayWidth(display, screen_num);
		display_height = DisplayHeight(display, screen_num);

		//获取缺省的颜色表
		default_cmap = DefaultColormap(display, screen_num);

		//获得Visual信息
		vis.screen = screen_num;
		vlist = XGetVisualInfo(display, 
			VisualScreenMask, 		//掩码
			&vis, 				//返回visual
			&match);			//所符合的Visual结构
		if(!vlist){
			printf("No matched visuals\n");
			exit(1);
		}
		vis = vlist[0];
		XFree(vlist);

		//判断颜色表大小是否合适
		if(vis.colormap_size<COLORS){
			printf("Colormap is too small.\n");
			exit(1);
		}

		//设置灰度级颜色表
		for(i=0; i<COLORS; i++){
			rgb[i].red = 65535 * (1.0-1.0*i/COLORS);
			rgb[i].green = 65535 * (1.0-1.0*i/COLORS);;
			rgb[i].blue = 65535 * (1.0*i/COLORS);;
			rgb[i].flags = DoRed|DoGreen|DoBlue;
		}
		//奇数class, 重定义颜色
		if(vis.class%2==1){
			unsigned long color[COLORS];
			//分配颜色单元
			if(XAllocColorCells(display, default_cmap, 1, NULL, 0,
				color, COLORS)==0){
				printf("Cannot allocate enough colors cells.\n");
				exit(1);
			}
			//修改颜色单元
			for(i=0; i<COLORS; i++) rgb[i].pixel = color[i];
			XStoreColors(display, default_cmap, rgb, COLORS);
		} else if(vis.class == TrueColor){
			//分配颜色
			for(i=0; i<COLORS; i++)
				XAllocColor(display, default_cmap, rgb + i);
		} else {
			printf("No content with visual class %d.\n",
				vis.class);
			exit(1);
		}

		switch(depth){
		case 6:
		case 8:
			img = XCreateImage(display,
				vis.visual,
				depth,
				ZPixmap,
				0,
				(char *)malloc(IMAGE_WIDTH*IMAGE_HEIGHT),
				IMAGE_WIDTH, IMAGE_HEIGHT,
				8,
				IMAGE_WIDTH);
			break;
		case 16:
                	bit_data = (unsigned char *)malloc(IMAGE_WIDTH * IMAGE_HEIGHT * 2);
			img = XCreateImage(display, 
				vis.visual, 
				vis.depth, 
				ZPixmap, 
				0, 
				bit_data, 
				IMAGE_WIDTH, IMAGE_HEIGHT,
				16, 
				0);
			break;
		case 24:
			bit_data = (unsigned char *)malloc(IMAGE_WIDTH * IMAGE_HEIGHT * 4);
			img = XCreateImage(display,
				vis.visual,
				depth,
				ZPixmap,
				0,
				(char *)bit_data,
				IMAGE_WIDTH, IMAGE_HEIGHT, 
				32, 
				0);
			break;
		default:
			exit(1);
		}
		//指定所建立窗口的宽度和高度
		//width = display_width/2;
		//height = display_height/2;
		width = 600;
		height = 400;

		//数据生成
		draw(display, win, gc, img, rgb, COLORS);



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


		//显示窗口
		XMapWindow(display, win);

		//把图像放到窗口上
		XPutImage(display, win, gc, img, 0, 0, 0, 0,
			IMAGE_WIDTH,IMAGE_HEIGHT);

		//取得图像
		cimg = XGetImage(display, win, 0, 0, 
			IMAGE_WIDTH, IMAGE_HEIGHT, AllPlanes, ZPixmap);

		reverse_image(cimg);


		//进入事件循环
		while (1)  {

			//取得队列中的事件
			XNextEvent(display, &report);
			switch  (report.type) {

			//曝光事件, 窗口应重绘
			case Expose:
				//取得最后一个曝光事件
				if (report.xexpose.count != 0) break;

				//画图像
				XPutImage(display, win, gc, img, 
					0, 0, 0, 0, IMAGE_WIDTH,IMAGE_HEIGHT);

				//拷贝图像
				XPutImage(display, win, gc, cimg, 
					0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, 
					IMAGE_WIDTH, IMAGE_HEIGHT);

				break;

			//窗口尺寸改变, 重新取得窗口的宽度和高度
			case ConfigureNotify:
				width = report.xconfigure.width;
				height = report.xconfigure.height;
				break;

			//鼠标点击或有按键, 释放资源则退出
			case ButtonPress:
			case KeyPress:
				XDestroyImage(img);
				XDestroyImage(cimg);
				XFreeGC(display, gc);
				//XCloseDisplay(display);
				exit(1);
			default:
				
				break;
			}
		}
	}

