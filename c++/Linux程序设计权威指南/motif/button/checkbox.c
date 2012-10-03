
	/* File: checkbox.c */
	// Runit: XENVIRONMENT=CheckBox.ad ./checkbox
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/RowColumn.h>
	#include <Xm/ToggleB.h>
	#include <Xm/PushB.h>
	#include <Xm/Separator.h>

	Widget buttons[6];

	char *lists[] = {
		"键盘",	"鼠标", "显示器",
		"内存", "硬盘", "电源"
	};

	void checkit(Widget w, int n,
		XmAnyCallbackStruct *cbs)
	{
		int i;
		Boolean status;
		printf("Final results:\n");
		for(i=0; i<6; i++){
			status = XmToggleButtonGetState(buttons[i]);
			printf("button %s is %s\n",
				XtName(buttons[i]), status ? "on" : "off");
		}
	}
	void toggle(Widget w, int n,
		XmToggleButtonCallbackStruct *cbs)
	{
		printf("button %d is %s\n", n, cbs->set ? "on" : "off");
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, rowcol, checkbox, w;
		XmString label;
		void callback();

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "CheckBox", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立布局组件
		rowcol = XmCreateRowColumn(toplevel, "rowcol", NULL, 0);
		XtManageChild(rowcol);

		//建立多选按钮的布局组件
		n = 0;
		XtSetArg(args[n], XmNpacking, XmPACK_COLUMN);	n++;
		XtSetArg(args[n], XmNnumColumns, 2);		n++;
		checkbox = XmCreateRowColumn(rowcol, "rowcol", args, n);
		XtManageChild(checkbox);

		//建立按钮
		for(i=0; i<XtNumber(lists); i++){
			buttons[i] = XmCreateToggleButton(checkbox, lists[i],
				NULL, 0);
			XtManageChild(buttons[i]);
			XtAddCallback(buttons[i], XmNvalueChangedCallback, 
				(XtCallbackProc)toggle, (XtPointer)i);
		}

		//设置按钮的初始状态
		XmToggleButtonSetState(buttons[2], True, False);
		XmToggleButtonSetState(buttons[4], True, False);

		//建立分隔符
		w = XmCreateSeparator(rowcol, "separator", NULL, 0);
		XtManageChild(w);

		//建立一个普通按钮
		w = XmCreatePushButton(rowcol, "检查按钮设置", NULL, 0);
		XtManageChild(w);
		XtAddCallback(w, XmNactivateCallback, 
			(XtCallbackProc)checkit, NULL);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
