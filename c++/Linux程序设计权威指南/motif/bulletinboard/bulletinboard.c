
	/* File: bulletinboard.c */
	//Runit: XENVIRONMENT=BulletinBoard.ad ./bulletinboard
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/BulletinB.h>
	#include <Xm/PushB.h>

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, bb, button1, button2;
		XmString str_months[12];

		setlocale(LC_ALL, "");

		XtSetLanguageProc(NULL, NULL, NULL);

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "BulletinBoard", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立公告板
		n = 0;
		XtSetArg(args[n], XmNwidth, 400 ); 			n++;
		XtSetArg(args[n], XmNheight, 400 ); 			n++;
		XtSetArg(args[n], XmNshadowType, XmSHADOW_ETCHED_IN);	n++;
		XtSetArg(args[n], XmNshadowThickness, 2);		n++;
		bb = XmCreateBulletinBoard(toplevel, "bb", args, n);
		XtManageChild(bb);

		//建立按钮
		n = 0;
		XtSetArg(args[n], XmNx, 20); 				n++;
		XtSetArg(args[n], XmNy, 50); 				n++;
		XtSetArg(args[n], XmNwidth, 100 ); 			n++;
		XtSetArg(args[n], XmNheight, 30 ); 			n++;
		button1 = XmCreatePushButton(bb, "Button1", args, n);
		XtManageChild(button1);
		n = 0;
		XtSetArg(args[n], XmNx, 100); 				n++;
		XtSetArg(args[n], XmNy, 150); 				n++;
		XtSetArg(args[n], XmNwidth, 200 ); 			n++;
		XtSetArg(args[n], XmNheight, 50 ); 			n++;
		button2 = XmCreatePushButton(bb, "Button2", args, n);
		XtManageChild(button2);
		

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}

