
	/* File: extended.c */
	//runit XENVIRONMENT=Extended.ad ./extended
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/List.h>
	#include <Xm/PushB.h>
	#include <Xm/Separator.h>
	#include <Xm/RowColumn.h>

	char *months[] = {
		"January(一月)", "Febrary(二月)", "March(三月)", "April(四月)",
		"May(五月)", "June(六月)", "July(七月)","August(八月)", 
		"September(九月)", "October(十月)", "November(十一月)", 
		"December(十二月)"
	};

	void callback(Widget w, XtPointer client_data,
		XmListCallbackStruct *cbs)
	{
		int i;
		char *string;
		printf("\nreason: %d\n", cbs->reason);
		if(cbs->reason == XmCR_EXTENDED_SELECT){
			printf("items selected:%d\n", cbs->selected_item_count);
			for(i=0; i<cbs->selected_item_count; i++){
				XmStringGetLtoR(cbs->selected_items[i],
					"", &string);
				printf("item %d:%s\n", 
					cbs->selected_item_positions[i],string);
				XtFree(string);
			}
		} else {
			XmStringGetLtoR(cbs->item, "", &string);
			printf("item %d:%s\n", cbs->item_position, string);
			XtFree(string);
		}
	}

	void checkit(Widget w,  Widget list,
		XmAnyCallbackStruct *cbs)
	{
		int i, count;
		XmStringTable selection;
		char *string;

		XtVaGetValues(list,
			XmNselectedItemCount,	&count,
			XmNselectedItems,	&selection,
			NULL);
		printf("\nselection result:\n");
		for(i = 0; i < count; i++){
			XmStringGetLtoR(selection[i], "", &string);
			printf("item %s selected.\n", string);
			XtFree(string);
		}
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, rowcol, list, sep, button;
		XmString str_months[12];

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Extended", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立rowcolumn 布局
		n = 0;
		XtSetArg(args[n], XmNpacking, XmPACK_TIGHT);   n++;
		rowcol = XmCreateRowColumn(toplevel, "rowcol", args, n);
		XtManageChild(rowcol);

		//建立列表上的复合字符串
		for(i=0; i<12; i++)
			str_months[i] = XmStringCreateSimple(months[i]);

		//建立列表
		n = 0;
		XtSetArg(args[n], XmNitems, str_months); 	n++;
		XtSetArg(args[n], XmNitemCount, 12);	 	n++;
		XtSetArg(args[n], XmNvisibleItemCount, 8); 	n++;
		XtSetArg(args[n], XmNscrollBarDisplayPolicy, XmSTATIC);n++;
		XtSetArg(args[n], XmNlistSizePolicy, XmCONSTANT);n++;
		XtSetArg(args[n], XmNselectionPolicy, XmEXTENDED_SELECT);n++;
		list = XmCreateScrolledList(rowcol, "list", args, n);
		XtManageChild(list);
		//建立回调
		XtAddCallback(list, XmNextendedSelectionCallback, 
			(XtCallbackProc)callback, NULL);
		XtAddCallback(list, XmNdefaultActionCallback,
			(XtCallbackProc)callback, NULL);


		for(i=0; i<12; i++)
			XmStringFree(str_months[i]);

		//建立分隔符
		sep = XmCreateSeparator(rowcol, "separator", NULL, 0);
		XtManageChild(sep);

		//建立一个普通按钮
		button = XmCreatePushButton(rowcol, "打印列表选择", NULL, 0);
		XtManageChild(button);
		XtAddCallback(button, XmNactivateCallback,
			(XtCallbackProc)checkit, list);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
