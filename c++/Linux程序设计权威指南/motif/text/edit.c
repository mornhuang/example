
	/* File: edit.c */
	//runit XENVIRONMENT=Edit.ad ./edit
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/TextF.h>
	#include <Xm/Text.h>
	#include <Xm/PushB.h>
	#include <Xm/Separator.h>
	#include <Xm/RowColumn.h>

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, rowcol, textf, text;
		XmString str_months[12];

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Edit", NULL, 0,
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

		//建立文本区
		n = 0;
		XtSetArg(args[n], XmNrows, 10);				n++;
		XtSetArg(args[n], XmNcolumns, 40);			n++;
		XtSetArg(args[n], XmNeditMode, XmMULTI_LINE_EDIT);	n++;
		XtSetArg(args[n], XmNwordWrap, True);			n++;
		XtSetArg(args[n], XmNvalue, "输入区域\n请测试");	n++;
		text = XmCreateScrolledText(rowcol, "text", args, n);
		XtManageChild(text);
		
		//显示窗口
		XtRealizeWidget(toplevel);

		//edit
		XmTextSetInsertionPosition(text, 3);
		XmTextInsert(text, 5, "测试插入");
		XmTextSetSelection(text, 5, 8, CurrentTime);
		XmTextCopy(text, CurrentTime);
		XmTextSetInsertionPosition(text, 1);
		XmTextPaste(text);
		

		//进入事件循环
		XtAppMainLoop(app);

	}
