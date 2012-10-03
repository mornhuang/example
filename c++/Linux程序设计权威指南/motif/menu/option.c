
	/* File: option.c */
	//Runit: XENVIRONMENT=Option.ad ./option
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/RowColumn.h>
	#include <Xm/PushB.h>
	#include <Xm/DrawingA.h>

	void callback(Widget w, char *command, 
		XmAnyCallbackStruct *cbs)
	{
		printf("%s\n", command);
	}

	Widget make_menu_item(Widget menu, char *label, char *command)
	{
		Widget button;

		button = XmCreatePushButton(menu, label, NULL, 0);
		XtManageChild(button);
		XtAddCallback(button, XmNactivateCallback,
			(XtCallbackProc) callback, command);
		return button;
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel;
		Widget mymenu, optmenu;
		WidgetList entries;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Option", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立选项菜单
		mymenu = XmCreatePulldownMenu(toplevel, "文件", NULL, 0);
		make_menu_item(mymenu, "硬盘", "Hard Disk");
		make_menu_item(mymenu, "鼠标", "Mouse");
		make_menu_item(mymenu, "键盘", "Keyboard");
		make_menu_item(mymenu, "显示器", "Monitor");

		n = 0;
		XtSetArg(args[n], XmNsubMenuId, mymenu);	n++;
		optmenu = XmCreateOptionMenu(toplevel, "计算机: ", args, n);
		XtManageChild(optmenu);

		//设置为第三项
		XtVaGetValues(mymenu,
			XmNchildren,	&entries,
			NULL);
		XtVaSetValues(mymenu,
			XmNmenuHistory, entries[2],
			NULL);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
