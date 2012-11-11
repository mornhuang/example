
	/* File: list.c */
	//runit XENVIRONMENT=List.ad ./list
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/List.h>

	char *months[] = {
		"January(一月)", "Febrary(二月)", "March(三月)", "April(四月)",
		"May(五月)", "June(六月)", "July(七月)","August(八月)", 
		"September(九月)", "October(十月)", "November(十一月)", 
		"December(十二月)"
	};

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, list;
		XmString str_months[12];

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "List", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立列表上的复合字符串
		for(i=0; i<12; i++)
			str_months[i] = XmStringCreateSimple(months[i]);

		//建立列表
		n = 0;
		XtSetArg(args[n], XmNitems, str_months); 	n++;
		XtSetArg(args[n], XmNitemCount, 12);	 	n++;
		XtSetArg(args[n], XmNvisibleItemCount, 8); 	n++;
		//XtSetArg(args[n], XmNscrollBarDisplayPolicy, XmSTATIC);n++;
		//XtSetArg(args[n], XmNlistSizePolicy, XmCONSTANT);n++;
		XtSetArg(args[n], XmNselectionPolicy, XmEXTENDED_SELECT);n++;
		list = XmCreateScrolledList(toplevel, "list", args, n);
		XtManageChild(list);

		for(i=0; i<12; i++)
			XmStringFree(str_months[i]);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
