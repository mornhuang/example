
	/* File: xbmlabel.c */
	#include <Xm/Xm.h>
	#include <Xm/Label.h>

	int main(int argc, char *argv[])
	{
		int n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, xbmlabel;
		Pixmap pixmap;

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Label", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立按钮上的图像
		pixmap = XmGetPixmap(XtScreen(toplevel), "opencjk.xbm", 
			BlackPixelOfScreen(XtScreen(toplevel)),
			WhitePixelOfScreen(XtScreen(toplevel)));
		if(pixmap == XmUNSPECIFIED_PIXMAP){
			printf("Pixmap Error loading\n");
			exit(1);
		}
		n = 0;
		XtSetArg(args[n], XmNlabelType, XmPIXMAP);	n++;
		XtSetArg(args[n], XmNlabelPixmap, pixmap);	n++;
		xbmlabel = XmCreateLabel(toplevel, "label", args, n);
		XtManageChild(xbmlabel);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
