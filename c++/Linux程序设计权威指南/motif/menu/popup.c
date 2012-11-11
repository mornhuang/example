
	/* File: popup.c */
	//Runit: XENVIRONMENT=Popup.ad ./popup
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/RowColumn.h>
	#include <Xm/PushB.h>
	#include <Xm/Separator.h>
	#include <Xm/DrawingA.h>

	void callback(Widget w, char *command, 
		XmAnyCallbackStruct *cbs)
	{
		printf("%s\n", command);
	}

	void popup(Widget w, Widget menu, 
		XmDrawingAreaCallbackStruct *cbs)
	{
		if(cbs->event->xany.type != ButtonPress ||
		   cbs->event->xbutton.button != Button3)
			return;

		XmMenuPosition(menu, (XButtonPressedEvent *)cbs->event);
		XtManageChild(menu);
	}


	Widget make_menu_item(Widget menu, char *label, char mnemonic, 
		char *command)
	{
		int n;
		Arg args[10];
		XmString str;
		Widget button;

		str = XmStringCreateSimple(label);
		n = 0;
		XtSetArg(args[n], XmNlabelString, str);		n++;
		XtSetArg(args[n], XmNmnemonic, mnemonic);	n++;
		button = XmCreatePushButton(menu, label, args, n);
		XtManageChild(button);
		XtAddCallback(button, XmNactivateCallback,
			(XtCallbackProc) callback, command);

		return button;
	}

	void make_menu_item_separator(Widget menu)
	{
		Widget sep;
		sep = XmCreateSeparator(menu, "sep", NULL, 0);
		XtManageChild(sep);
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, drawarea, w;
		Widget filemenu;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Popup", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立绘图区域
		n = 0;
		XtSetArg(args[n], XmNwidth, 320);	n++;
		XtSetArg(args[n], XmNheight, 320);	n++;
		drawarea = XmCreateDrawingArea(toplevel, "drawarea", args, n);
		XtManageChild(drawarea);
		
		//建立文件菜单
		filemenu = XmCreatePopupMenu(drawarea, "文件", NULL, 0);
		make_menu_item(filemenu, "新建(N)", 'N', "Create New File");
		make_menu_item(filemenu, "打开(O)", 'O', "Open File");
		make_menu_item(filemenu, "保存(S)", 'S', "Save File");
		make_menu_item_separator(filemenu);
		make_menu_item(filemenu, "退出(Q)", 'Q', "Quit Application");

		XtAddCallback(drawarea, XmNinputCallback,
			(XtCallbackProc) popup, filemenu);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
