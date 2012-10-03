
	/* File: i18n.c */
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/PushB.h>

	void callback(Widget w, XtPointer client_data,
		XmPushButtonCallbackStruct *cbs)
	{
		printf("Hello World!\n");
	}

	int main(int argc, char *argv[])
	{
		int n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, button;
		//XmString label;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "I18N", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立按钮上标签的字符串
		//label = XmStringCreateSimple("Hello World!");
		n = 0;
		button = XmCreatePushButton(toplevel, "pushbutton", args, n);
		XtManageChild(button);
		//XmStringFree(label);

		//设置回调函数
		XtAddCallback(button, 
			XmNactivateCallback,	(XtCallbackProc)callback,
			NULL);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
