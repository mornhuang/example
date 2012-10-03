
	/* File: spinbox.c */
	//Runit: XENVIRONMENT=SpinBox.ad ./spinbox
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/SpinB.h>
	#include <Xm/TextF.h>

        char *months[] = {
                "January(一月)", "Febrary(二月)", "March(三月)", "April(四月)",
                "May(五月)", "June(六月)", "July(七月)","August(八月)",
                "September(九月)", "October(十月)", "November(十一月)",
                "December(十二月)"
        };

	void ValueChanged(Widget w, XtPointer client_data,
		XmSpinBoxCallbackStruct *cbs)
	{
		char *str;
		printf("Value Changed: ");
  
		switch (cbs->reason) {
			case XmCR_OK:
				printf ("reason = XmCR_OK\n\n"); break;
			case XmCR_SPIN_NEXT:
				printf ("reason = XmCR_SPIN_NEXT\n"); break; 
			case XmCR_SPIN_PRIOR:
				printf ("reason = XmCR_SPIN_PRIOR\n"); break;
			case XmCR_SPIN_FIRST:
				printf ("reason = XmCR_SPIN_FIRST\n"); break;
			case XmCR_SPIN_LAST:
				printf ("reason = XmCR_SPIN_LAST\n"); break; 
			default: break;
		}
		XmStringGetLtoR(cbs->value, "", &str);
		printf("position: %d\n", cbs->position);
		printf("value: %s\n", str);
		XFree(str);
	}

	void ModifyVerify(Widget w, XtPointer client_data,
		XmSpinBoxCallbackStruct *cbs)
	{
		printf ("Modify Verify invoked: ");
		switch ( cbs->reason ) {
			case XmCR_OK: 
				printf ("reason = XmCR_OK\n"); break;
			case XmCR_SPIN_NEXT:
				printf ("reason = XmCR_SPIN_NEXT\n"); break;
			case XmCR_SPIN_PRIOR:
				printf ("reason = XmCR_SPIN_PRIOR\n"); break;
			case XmCR_SPIN_FIRST:
				printf ("reason = XmCR_SPIN_FIRST\n"); break; 
			case XmCR_SPIN_LAST:
				printf ("reason = XmCR_SPIN_LAST\n"); break; 
			default: break;
		}
		printf("position: %d\n", cbs->position);
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, spinbox, textf;
		XmString str_months[12];

		setlocale(LC_ALL, "");

		XtSetLanguageProc(NULL, NULL, NULL);

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "SpinBox", NULL, 0,
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

