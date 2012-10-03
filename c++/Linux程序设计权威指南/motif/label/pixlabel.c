
	/* File: pixlabel.c */
	#include <Xm/Xm.h>
	#include <Xm/Label.h>
	#include <X11/xpm.h>

	int main(int argc, char *argv[])
	{
		int n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, pixlabel;
		Pixmap pixmap;
		int status;

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Label", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立按钮上的图像
		status = XpmReadFileToPixmap(XtDisplay(toplevel), 
			XRootWindowOfScreen(XtScreen(toplevel)),
			"opencjk.xpm", &pixmap, NULL, NULL);
		if(status != XpmSuccess){
			printf("XpmError: %s\n", XpmGetErrorString(status));
			exit(1);
		}
		n = 0;
		XtSetArg(args[n], XmNlabelType, XmPIXMAP);	n++;
		XtSetArg(args[n], XmNlabelPixmap, pixmap);	n++;
		pixlabel = XmCreateLabel(toplevel, "label", args, n);
		XtManageChild(pixlabel);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
