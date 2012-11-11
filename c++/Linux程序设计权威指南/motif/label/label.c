
	/* File: label.c */
	#include <Xm/Xm.h>
	#include <Xm/Label.h>

	int main(int argc, char *argv[])
	{
		int n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, label;
		XmString str;

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Label", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立按钮上标签的字符串
		str = XmStringCreateLtoR("A Simple\n Label",
			XmFONTLIST_DEFAULT_TAG);
		n = 0;
		XtSetArg(args[n], XmNlabelString, str);		n++;
		label = XmCreateLabel(toplevel, "pushbutton", args, n);
		XtManageChild(label);

		XmStringFree(str);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
