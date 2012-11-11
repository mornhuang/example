
	/* File: scroll.c */
	//Runit: XENVIRONMENT=Scroll.ad ./scroll
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/ScrolledW.h>
	#include <Xm/DrawingA.h>

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, scroll, drawarea;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Scroll", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立SpinBox
		n = 0;
		XtSetArg(args[n], XmNwidth, 400 ); 			n++;
		XtSetArg(args[n], XmNheight, 100 ); 			n++;
		//XtSetArg(args[n], XmNarrowLayout, XmARROWS_FLAT_END);	n++;
		XtSetArg(args[n], XmNrepeatDelay, 0); 			n++;
		//XtSetArg(args[n], XmNarrowOrientation, XmHORIZONTAL);	n++;
		spinbox  = XmCreateSpinBox(toplevel, "SpinBox", args, n);
		XtManageChild(spinbox);

		XtAddCallback (spinbox, XmNvalueChangedCallback, 
			(XtCallbackProc)ValueChanged, (XtPointer)NULL);
		XtAddCallback (spinbox, XmNmodifyVerifyCallback, 
			(XtCallbackProc)ModifyVerify, (XtPointer)NULL);


		//SpinBox的输入条
/*
		n = 0;
		XtSetArg(args[n], XmNspinBoxChildType, XmNUMERIC); 	n++;
		XtSetArg(args[n], XmNminimumValue, 1); 			n++;
		XtSetArg(args[n], XmNmaximumValue, 10); 		n++;
		//XtSetArg(args[n], XmNincrementValue, 3); 		n++;
		XtSetArg(args[n], XmNposition, 2); 			n++;
		XtSetArg(args[n], XmNpositionType, XmPOSITION_INDEX); 	n++;
*/
		for(i=0; i<12; i++)
			str_months[i] = XmStringCreateSimple(months[i]);
		n = 0;
		XtSetArg (args[n], XmNspinBoxChildType, XmSTRING); 	n++;
		XtSetArg (args[n], XmNposition, 3); 			n++;
		XtSetArg (args[n], XmNnumValues, 12);	 		n++;
		XtSetArg (args[n], XmNvalues, str_months); 		n++;

		textf  = XmCreateTextField(spinbox, "textf", args, n);
		XtManageChild(textf);


		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}

