
	/* File: menu.c */
	//Runit: XENVIRONMENT=Menu.ad ./menu
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/MainW.h>
	#include <Xm/RowColumn.h>
	#include <Xm/CascadeB.h>
	#include <Xm/PushB.h>
	#include <Xm/Separator.h>

	void callback(Widget w, char *command, 
		XmAnyCallbackStruct *cbs)
	{
		printf("%s\n", command);
	}

	Widget make_menu(Widget menubar, char *label, char mnemonic,
		char *acce, char *acce_text)
	{
		int n;
		Arg args[10];
		XmString str, text;
		Widget menu, button;
	
		menu = XmCreatePulldownMenu(menubar, label, NULL, 0);
		str = XmStringCreateSimple(label);
		text = XmStringCreateSimple(acce_text);
		n = 0;
		XtSetArg(args[n], XmNlabelString, str);		n++;
		XtSetArg(args[n], XmNmnemonic, mnemonic);	n++;
		XtSetArg(args[n], XmNsubMenuId, menu);		n++;
		XtSetArg(args[n], XmNaccelerator, acce);	n++;
		XtSetArg(args[n], XmNacceleratorText, text);	n++;
		button = XmCreateCascadeButton(menubar, label, args, n);
		XtManageChild(button);
		XmStringFree(str);
		XmStringFree(text);
		return menu;
	}

	Widget make_help_menu(Widget menubar, char *label, char mnemonic,
		char *acce, char *acce_text)
	{
		int n;
		Arg args[10];
		XmString str, text;
		Widget menu, button;
	
		menu = XmCreatePulldownMenu(menubar, label, NULL, 0);
		str = XmStringCreateSimple(label);
		text = XmStringCreateSimple(acce_text);
		n = 0;
		XtSetArg(args[n], XmNlabelString, str);		n++;
		XtSetArg(args[n], XmNmnemonic, mnemonic);	n++;
		XtSetArg(args[n], XmNsubMenuId, menu);		n++;
		XtSetArg(args[n], XmNaccelerator, acce);	n++;
		XtSetArg(args[n], XmNacceleratorText, text);	n++;
		button = XmCreateCascadeButton(menubar, label, args, n);
		XtManageChild(button);
		XmStringFree(str);
		XmStringFree(text);

		//设置帮助菜单
		n = 0;
		XtSetArg(args[n], XmNmenuHelpWidget, button);	n++;
		XtSetValues(menubar, args, n);
		return menu;
	}

	Widget make_menu_item(Widget menu, char *label, char mnemonic, 
		char *acce, char *acce_text, char *command)
	{
		int n;
		Arg args[10];
		XmString str, text;
		Widget button;

		str = XmStringCreateSimple(label);
		text = XmStringCreateSimple(acce_text);
		n = 0;
		XtSetArg(args[n], XmNlabelString, str);		n++;
		XtSetArg(args[n], XmNmnemonic, mnemonic);	n++;
		XtSetArg(args[n], XmNaccelerator, acce);	n++;
		XtSetArg(args[n], XmNacceleratorText, text);	n++;
		button = XmCreatePushButton(menu, label, args, n);
		XtManageChild(button);
		XtAddCallback(button, XmNactivateCallback,
			(XtCallbackProc) callback, command);
		XmStringFree(str);
		XmStringFree(text);
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
		Widget toplevel, mainw, menubar, w;
		Widget filemenu, editmenu, helpmenu;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Menu", NULL, 0,
			&argc, argv, NULL, NULL);
		n = 0;
		XtSetArg(args[n], XmNwidth, 320);	n++;
		XtSetArg(args[n], XmNheight, 320);	n++;
		XtSetValues(toplevel, args, n);

		//建立主窗口
		mainw = XmCreateMainWindow(toplevel, "mainw", NULL, 0);
		XtManageChild(mainw);
		
		//建立菜单条
		menubar = XmCreateMenuBar(mainw, "menubar", NULL, 0);
		XtManageChild(menubar);

		//建立文件菜单
		filemenu = make_menu(menubar, "文件(F)", 'F', "Meta<key>F", "Alt-F");
		make_menu_item(filemenu, "新建(N)", 'N', "Meta<key>N", "Meta+N", "Create New File");
		make_menu_item(filemenu, "打开(O)", 'O', "Meta<key>O", "Meta+O", "Open File");
		make_menu_item(filemenu, "保存(S)", 'S', "Meta<key>S", "Meta+S", "Save File");
		make_menu_item_separator(filemenu);
		make_menu_item(filemenu, "退出(Q)", 'Q', "Meta<key>Q", "Meta+Q", "Quit Application");

		//建立编辑菜单
		editmenu = make_menu(menubar, "编辑(E)", 'E', "Meta<key>E", "Alt-E");
		make_menu_item(editmenu, "取消(U)", 'U', "Meta<key>U", "Meta+U", "Undo last operation");
		make_menu_item(editmenu, "重复(R)", 'R', "Meta<key>R", "Meta+R", "Redo last operation");
		make_menu_item_separator(editmenu);
		//建立子级联菜单
		w = make_menu(editmenu, "编辑操作(o)", 'o', "Meta<key>o", "Alt-o");
		make_menu_item(w, "拷贝(o)", 'o', "Meta<key>o", "Meta+o", "Copy to clipboard");
		make_menu_item(w, "剪切(u)", 'u', "Meta<key>u", "Meta+u", "Cut to clipboard");
		make_menu_item(w, "粘贴(P)", 'P',"Meta<key>P", "Meta+P", "Paste from clipboard");

		//建立帮助菜单
		helpmenu = make_help_menu(menubar, "帮助(H)", 'H', "Meta<key>H", "Alt-H");
		make_menu_item(helpmenu, "帮助(h)", 'h', "Meta<key>h", "Meta+h", "Help Contents");
		make_menu_item(helpmenu, "关于(A)...", 'A',"Meta<key>A", "Meta+A",  "About this application");

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
