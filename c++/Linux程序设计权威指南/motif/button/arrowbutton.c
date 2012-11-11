
	/* File: arrowbutton.c */
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/ArrowB.h>

	void callback(Widget w, int n,
		XmAnyCallbackStruct *cbs)
	{
		printf("arrow button activated.\n");
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, arrow;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "ArrowButton", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立多选按钮的布局组件
		n = 0;
		XtSetArg(args[n], XmNarrowDirection, XmARROW_RIGHT);	n++;
		XtSetArg(args[n], XmNwidth, 100);		n++;
		XtSetArg(args[n], XmNheight, 100);		n++;
		arrow = XmCreateArrowButton(toplevel, "arrow", args, n);
		XtManageChild(arrow);

		//设置回调
		XtAddCallback(arrow, XmNactivateCallback, 
			(XtCallbackProc)callback, NULL);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
