
	/* File: handwriting.c */
	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include <X11/Xos.h>
	#include <X11/Xatom.h>
	#include <X11/keysym.h>
	#include <X11/cursorfont.h>

	//点
	typedef struct Point {
		int x;
		int y;
	}Point;

	//曲线
	typedef struct Line {
		int x;
		int y;
		struct Line  *next;
	}Line;

	//一个汉字由许多条线构成
	typedef struct Lines {
		Line *line;
		struct Lines *next;
	}Lines;

	// declare and initilize
	Lines *hanzi_lines = NULL;
	Lines *lines_tail = NULL;
	Line  *hanzi_line = NULL;
	Line  *line_tail = NULL;


	void add_point(int x, int y)
	{
		Line *tmp;
		tmp = (Line *)malloc(sizeof(Line));
		tmp->x = x;
		tmp->y = y;
		tmp->next = NULL;

		line_tail->next = tmp;
		line_tail = line_tail->next;
	}

	void add_line()
	{
		Lines *tmp;
		tmp = (Lines *)malloc(sizeof(Lines));
		tmp->line = hanzi_line;
		tmp->next = NULL;

		lines_tail->next = tmp;
		lines_tail = lines_tail->next;
	}


	void free_line(Line *line)
	{
		Line *p = line;
		Line *p1 = p->next;

		while(p->next != NULL){
			free(p);
			p = p1;
			p1=p->next;
		}
		free(p);
	}


	void free_lines()
	{       
		Lines *p = hanzi_lines;
		Lines *p1;
        
		if(!p) return;
		if(p->next == NULL) {
			free(p);
			return;
		}
		p1 = p->next->next;
		while(p->next != NULL){
			free_line(p->next->line);
			free(p->next);
			p = p->next;
			p1 = p->next;
		}
		free(hanzi_lines);
	}

	void init_line()
	{
		hanzi_line = (Line *)malloc(sizeof(Line));
		hanzi_line->next = NULL;
		line_tail = hanzi_line;
	}


	void init_lines()
	{
		hanzi_lines = (Lines *)malloc(sizeof(Lines));
		hanzi_lines->next = NULL;
		lines_tail = hanzi_lines;
	}


	void drawline(Display *dpy, Window w, GC gc, int x1, int y1)
	{

		while(1){
			Window rootw, curwin;
			int x2, y2, winx, winy;
			unsigned int mask;
			XEvent ev;

			XNextEvent(dpy, &ev);
			switch(ev.type){
			case ButtonRelease:
				XQueryPointer(dpy, w, &rootw, &curwin,
                                        &x2, &y2, &winx, &winy, &mask);
				//新的曲线的开始
				add_line();
				return;
			case MotionNotify:
				XQueryPointer(dpy, w, &rootw, &curwin,
                                        &x2, &y2, &winx, &winy, &mask);
				//在当前曲线上加点
				add_point(x2, y2);
				//画线
				XDrawLine(dpy, w, gc, x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
				break;
			default:
				break;
			}
		}
	}


	void draw_line(Display *dpy, Window win, GC gc, Line *line)
	{

	        int x1, y1, x2, y2;

        	Line *p = line;

        	x1 = p->next->x;
        	y1 = p->next->y;

        	while(p->next != NULL){
        	        x2 = p->next->x;
                	y2 = p->next->y;
                	XDrawLine (dpy, win, gc, x1, y1, x2, y2);
                	x1 = x2;
                	y1 = y2;
                	p = p->next;
        	}
	}

	void erase_lines(Display *dpy, Window win, GC gc)
	{
        	//使用异或的方法重新画屏幕, 即擦除所有的线
        	Lines *p = hanzi_lines;

        	while(p->next != NULL){
                	draw_line(dpy, win, gc, p->next->line);
                	p = p->next;
        	}

	}

	void wait_release(Display *dpy)
	{
		XEvent ev;

		while(1){
			XNextEvent(dpy, &ev);
			if(ev.type == ButtonRelease &&
				ev.xbutton.button == Button3)
				return;
		}
	}


	void draw_root()
	{
		Window curwin, rootw, defaultw;
		GC drawgc;
		Cursor hand_cursor;
		int x1, y1, x2, y2, winx, winy;
		unsigned int mask;	
		XEvent ev;
		int screen_num;
		KeySym sym;
		Display *dpy = XOpenDisplay(NULL);

		/* style for line */
		unsigned int line_width = 8;
		int line_style = LineSolid;
		int cap_style  = CapRound;
		int join_style = JoinRound;


		screen_num = DefaultScreen(dpy);
		defaultw = DefaultRootWindow(dpy);

		hand_cursor = XCreateFontCursor(dpy, XC_hand2);
		//XDefineCursor(dpy, defaultw, hand_cursor);

		drawgc = XCreateGC(dpy, defaultw, 0, NULL);
		XSetSubwindowMode(dpy, drawgc, IncludeInferiors);
		XSetForeground(dpy, drawgc,
           		WhitePixel(dpy, screen_num) 
			^ BlackPixel(dpy, screen_num));
		XSetLineAttributes(dpy, drawgc, line_width, line_style,
                        cap_style, join_style);
        	XSetFunction(dpy, drawgc, GXxor);

		XBell(dpy, 0);

		// init lines 
		free_lines();
		init_lines();

		//捕获按钮
		XGrabButton(dpy, AnyButton, 0, defaultw, False, 
			ButtonPressMask | ButtonReleaseMask | ButtonMotionMask,
			GrabModeAsync, GrabModeAsync, None, hand_cursor);

		// recycle
		while(1){
			XNextEvent(dpy, &ev);
			switch(ev.type){
			case ButtonPress:
				XQueryPointer(dpy, defaultw,
                                        &rootw, &curwin,
                                        &x1, &y1,       // root x, root y
                                        &winx, &winy,
                                        &mask);
				if(ev.xbutton.button == Button2) {
					XUngrabButton(dpy,AnyButton,0,defaultw);
					return;
				} else if(ev.xbutton.button == Button3) {
					XUngrabButton(dpy,AnyButton,0,defaultw);
					erase_lines(dpy, defaultw, drawgc);
					free_lines();
					init_lines();
					wait_release(dpy);
					return;
				}
				init_line();
				add_point(x1, y1);
				XDrawLine(dpy,defaultw, drawgc, x1, y1, x1, y1);
				drawline(dpy, defaultw, drawgc, x1, y1);
				break;
			default:
				break;
			}
		}
	}


	//
	int main(int argc, char **argv)
	{
		printf("Left Button down to draw lines all over the screen.\n");
		printf("Right Button to finish the drawing.\n");
		printf("^C to exit.\n");
		while(1) draw_root();
		return 0;
	}

