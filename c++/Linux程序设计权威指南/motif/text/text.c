
	/* File: text.c */
	//runit XENVIRONMENT=Text.ad ./text
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/TextF.h>
	#include <Xm/Text.h>
	#include <Xm/PushB.h>
	#include <Xm/Separator.h>
	#include <Xm/RowColumn.h>

	Widget textf, text;

	void checkit(Widget w,  XtPointer client_data ,
		XmAnyCallbackStruct *cbs)
	{
		char *string;
		string = XmTextFieldGetString(textf);
		printf("TextField:%s\n", string);
		XtFree(string);
		
		string = XmTextGetString(text);
		printf("Text:%s\n", string);
		XtFree(string);
		
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, rowcol, sep, button;
		XmString str_months[12];

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Text", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立rowcolumn 布局
		n = 0;
		XtSetArg(args[n], XmNpacking, XmPACK_TIGHT);   n++;
		rowcol = XmCreateRowColumn(toplevel, "rowcol", args, n);
		XtManageChild(rowcol);

		//建立输入条
		n = 0;
		XtSetArg(args[n], XmNvalue, "输入条");	n++;
		textf = XmCreateTextField(rowcol, "textf", args, n);
		XtManageChild(textf);
		//建立回调
		XtAddCallback(textf, XmNactivateCallback, 
			(XtCallbackProc)checkit, NULL);

		//建立输入区
		n = 0;
		XtSetArg(args[n], XmNrows, 10);				n++;
		XtSetArg(args[n], XmNcolumns, 40);			n++;
		XtSetArg(args[n], XmNeditMode, XmMULTI_LINE_EDIT);	n++;
		XtSetArg(args[n], XmNvalue, "输入区域\n请测试");	n++;
		text = XmCreateScrolledText(rowcol, "text", args, n);
		//text = XmCreateText(rowcol, "text", args, n);
		XtManageChild(text);
		
		//建立分隔符
		sep = XmCreateSeparator(rowcol, "separator", NULL, 0);
		XtManageChild(sep);

		//建立一个普通按钮
		button = XmCreatePushButton(rowcol, 
			"打印输入条和文本区内容", NULL, 0);
		XtManageChild(button);
		XtAddCallback(button, XmNactivateCallback,
			(XtCallbackProc)checkit, NULL);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
