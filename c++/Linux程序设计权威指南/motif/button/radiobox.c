
	/* File: radiobox.c */
	//runit XENVIRONMENT=RadioBox.ad ./radiobox
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/RowColumn.h>
	#include <Xm/ToggleB.h>

	char *lists[] = {
		"键盘", "鼠标", "显示器"
	};

	void toggle(Widget w, int n,
		XmToggleButtonCallbackStruct *cbs)
	{
		printf("Button %s is %s\n", XtName(w), cbs->set ? "on" : "off"); 
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, radiobox, buttons[3];
		XmString label;

		//
		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "RadioBox", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立RadioBox
		radiobox = XmCreateRadioBox(toplevel, "radiobox", NULL, 0);
		XtManageChild(radiobox);

		for(i=0; i<XtNumber(lists); i++){
			buttons[i] = XmCreateToggleButton(radiobox, 
				lists[i], NULL, 0);
			XtManageChild(buttons[i]);
			XtAddCallback(buttons[i], XmNvalueChangedCallback, 
				(XtCallbackProc)toggle, (XtPointer)i);
		}

		//设置初始值
		XmToggleButtonSetState(buttons[1], True, False);
		

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
