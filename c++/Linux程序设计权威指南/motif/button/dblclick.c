
	/* File: dblclick.c */
	#include <Xm/Xm.h>
	#include <Xm/PushB.h>

	XtAppContext app;
	int timeout;

	void clicks(int click)
	{
		if(click) printf("double click.\n");
		else printf("single click.\n");
	}

	void callback(Widget w, XtPointer client_data,
		XmPushButtonCallbackStruct *cbs)
	{
		static XtIntervalId id;
		if(cbs->click_count == 1){
			id = XtAppAddTimeOut(app, timeout, 
				(XtTimerCallbackProc)clicks, False);
		} else if(cbs->click_count == 2){
			XtRemoveTimeOut(id);
			clicks(True);
		}
	}

	int main(int argc, char *argv[])
	{
		int n;
		Arg args[10];
		Widget toplevel, button;
		XmString label;
		void callback();

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Demo", NULL, 0,
			&argc, argv, NULL, NULL);

		//获得双击时间
		timeout = XtGetMultiClickTime(XtDisplay(toplevel));
		printf("double click time:%d ms\n", timeout);

		//建立按钮上标签的字符串
		label = XmStringCreateSimple("Double Click Test");
		n = 0;
		XtSetArg(args[n], XmNlabelString, label);	n++;
		button = XmCreatePushButton(toplevel, "pushbutton", args, n);
		XtManageChild(button);
		XmStringFree(label);

		//设置回调函数
		XtAddCallback(button, 
			XmNactivateCallback,	callback,
			(XtPointer)timeout);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
