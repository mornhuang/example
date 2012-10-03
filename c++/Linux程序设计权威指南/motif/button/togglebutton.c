
	/* File: togglebutton.c */
	#include <Xm/Xm.h>
	#include <Xm/ToggleB.h>


	void callback(Widget w, XtPointer client_data,
		XmAnyCallbackStruct *cbs)
	{
		Boolean status;

		status = XmToggleButtonGetState(w);
		/*
		XtVaGetValues(w,
			XmNset, 	&status,
			NULL);
		*/

		if(status) printf("Toggle is on\n");
		else printf("Toggle is off\n"); 
	}

	int main(int argc, char *argv[])
	{
		int n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, button;
		XmString label;
		void callback();

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Demo", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立按钮上标签的字符串
		label = XmStringCreateSimple("ToggleButton Test");
		n = 0;
		XtSetArg(args[n], XmNlabelString, label);	n++;
		button = XmCreateToggleButton(toplevel, "Demo", args, n);
		XtManageChild(button);

		XmStringFree(label);

		//把按钮缺省设为开的状态
		XmToggleButtonSetState(button, True, False);
		/*
		XtVaSetValues(button,
			XmNset,		True,
			NULL);
		*/

		//设置回调函数
		XtAddCallback(button,
			XmNvalueChangedCallback,callback,
			NULL);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
