
	/* File: drawarea.c */
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/DrawingA.h>

	Widget 	drawarea;
	GC	gcXor, gcCopy;
	int	x1, y1, x2, y2;

	void prepare()
	{
		int n;
		Arg args[10];
		XGCValues gcv;
		Colormap cmap;
		XColor col, unused;

		cmap = DefaultColormapOfScreen(XtScreen(drawarea));

		//设置前景和背景
		if(!XAllocNamedColor(XtDisplay(drawarea), cmap, "red", 
			&col, &unused)){
			printf("Cannot allocate color.\n");
			return;
		}

		//建立GC
		gcv.foreground = col.pixel;
		gcCopy = XtGetGC(drawarea, GCForeground, &gcv);
		XSetLineAttributes(XtDisplay(drawarea), gcCopy, 3,
			LineSolid, CapRound, JoinRound);

		gcv.foreground = col.pixel;
		gcv.function = GXxor;
		gcXor = XtGetGC(drawarea, GCForeground | GCFunction, &gcv);
		XSetLineAttributes(XtDisplay(drawarea), gcXor, 3,
			LineSolid, CapRound, JoinRound);
        }

	void button_pressed(Widget w, XtPointer client_data, XEvent *event)
	{
		x1 = x2 = event->xbutton.x;
		y1 = y2 = event->xbutton.y;
		XDrawLine(XtDisplay(w), XtWindow(w), gcXor, x1, y1, x2, y2);
	}

	void button_released(Widget w, XtPointer client_data, XEvent *event)
	{
		x2 = event->xbutton.x;
		y2 = event->xbutton.y;
		XDrawLine(XtDisplay(w), XtWindow(w), gcCopy, x1, y1, x2, y2);
	}

	void button_motion(Widget w, XtPointer client_data, XEvent *event)
	{
		//擦掉原来的线
		XDrawLine(XtDisplay(w), XtWindow(w), gcXor, x1, y1, x2, y2);
		x2 = event->xbutton.x;
		y2 = event->xbutton.y;
		XDrawLine(XtDisplay(w), XtWindow(w), gcXor, x1, y1, x2, y2);
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel;
		XmString str_months[12];

		setlocale(LC_ALL, "");

		XtSetLanguageProc(NULL, NULL, NULL);

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "DrawArea", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立drawarea
		n = 0;
		XtSetArg(args[n], XmNwidth, 500 ); 			n++;
		XtSetArg(args[n], XmNheight, 500 ); 			n++;
		drawarea  = XmCreateDrawingArea(toplevel, "drawarea", args, n);
		XtManageChild(drawarea);

		//建立绘图所用的GC
		prepare();

		XtAddEventHandler(drawarea, ButtonPressMask, FALSE,
			(XtEventHandler)button_pressed, NULL);
		XtAddEventHandler(drawarea, ButtonReleaseMask, FALSE,
			(XtEventHandler)button_released, NULL);
		XtAddEventHandler(drawarea, ButtonMotionMask, FALSE,
			(XtEventHandler)button_motion, NULL);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}

