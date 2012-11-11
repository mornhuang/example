
	/* File: fontlist1.c */
	//runit: XENVIRONMENT=FontList1.ad ./fontlist1
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/PushB.h>


	int main(int argc, char *argv[])
	{
		int n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, button;
		XmString label, label1, label2;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "FontList1", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立按钮上标签的字符串
		label1 = XmStringCreate("复合字符串Testing", "small");
		label2 = XmStringCreate("大字体Testing", "large");
		label = XmStringConcat(label1, label2);

		n = 0;
		XtSetArg(args[n], XmNlabelString, label); 	n++;
		button = XmCreatePushButton(toplevel, "pushbutton", args, n);
		XtManageChild(button);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
