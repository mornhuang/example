
	/* File: frame.c */
	//Runit: XENVIRONMENT=Frame.ad ./frame
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/Frame.h>
	#include <Xm/RowColumn.h>
	#include <Xm/Label.h>
	#include <Xm/TextF.h>

	char *items[] = {
		"姓名:",
		"性别:",
		"出生年月:",
		"电话号码:",
		"家庭住址:",
		"邮政编码:"
	};

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, frame, rowcolumn, label, textf;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Frame", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立Frame组件
		n = 0;
		XtSetArg(args[n], XmNshadowType, XmSHADOW_ETCHED_IN);	n++;
		XtSetArg(args[n], XmNshadowThickness, 4);		n++;
		frame = XmCreateFrame(toplevel, "frame", args, n);
		XtManageChild(frame);
		
		//建立RowColumn组件
		n = 0;
		XtSetArg(args[n], XmNnumColumns, 6); 			n++;
		XtSetArg(args[n], XmNpacking, XmPACK_COLUMN);		n++;
		XtSetArg(args[n], XmNorientation, XmHORIZONTAL);	n++;
		XtSetArg(args[n], XmNadjustLast, False);		n++;
		XtSetArg(args[n], XmNisAligned, True);			n++;
		XtSetArg(args[n], XmNentryAlignment, XmALIGNMENT_END);	n++;
		rowcolumn  = XmCreateRowColumn(frame, "rowcolumn", args, n);
		XtManageChild(rowcolumn);


		for(i=0; i<XtNumber(items); i++){
			label = XmCreateLabel(rowcolumn, items[i], NULL, 0);
			XtManageChild(label);

			textf = XmCreateTextField(rowcolumn, "textf", NULL, 0);
			XtManageChild(textf);
		}
			
		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}

