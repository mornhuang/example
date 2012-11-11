
	/* File: passwd.c */
	//runit XENVIRONMENT=Passwd.ad ./passwd
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/TextF.h>
	#include <Xm/Label.h>
	#include <Xm/RowColumn.h>

	void checkit(Widget w, XtPointer client_data,
		XmTextVerifyCallbackStruct *cbs)
	{
		static char *passwd;
		char *str;
		int len;

		if(cbs->reason == XmCR_ACTIVATE){
			printf("Your passwd is:%s\n", passwd);
			return;
		}

		//退格键
		if(cbs->text->ptr == NULL){
			cbs->endPos = strlen(passwd);
			passwd[cbs->startPos] = '\0';
			return;
		} else if(cbs->text->length > 1) { //粘贴
			cbs->doit = False;	//不允许
			return;
		}

		str = XtMalloc(cbs->endPos + 2);
		if(passwd){
			strcpy(str, passwd);
			XtFree(passwd);
		} else {
			str[0] = '\0';
		}

		passwd = str;
		strncat(passwd, cbs->text->ptr, cbs->text->length);
		passwd[cbs->endPos + cbs->text->length] = '\0';
		for(len = 0; len<cbs->text->length; len++)
			cbs->text->ptr[len] = '*';
	}

	int main(int argc, char *argv[])
	{
		int i, n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, rowcol, label, textf;
		XmString str_months[12];

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "Passwd", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立rowcolumn 布局
		n = 0;
		XtSetArg(args[n], XmNpacking, XmPACK_TIGHT);   		n++;
		XtSetArg(args[n], XmNorientation, XmHORIZONTAL);   	n++;
		rowcol = XmCreateRowColumn(toplevel, "rowcol", args, n);
		XtManageChild(rowcol);

		//建立标签
		label = XmCreateLabel(rowcol, "请输入口令:", NULL, 0);
		XtManageChild(label);

		//建立输入条
		textf = XmCreateTextField(rowcol, "textf", NULL, 0);
		XtManageChild(textf);
		XtAddCallback(textf, XmNmodifyVerifyCallback, 
			(XtCallbackProc)checkit, NULL);
		XtAddCallback(textf, XmNactivateCallback, 
			(XtCallbackProc)checkit, NULL);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
