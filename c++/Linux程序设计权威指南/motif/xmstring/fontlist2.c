
	/* File: fontlist.c */
	#include <locale.h>
	#include <Xm/Xm.h>
	#include <Xm/PushB.h>


	int main(int argc, char *argv[])
	{
		int n;
		Arg args[10];
		XtAppContext app;
		Widget toplevel, button;
		XmString label, label1, label2;
		XFontSet fontset1, fontset2;
		XmFontListEntry fontlist_entry1, fontlist_entry2;
		XmFontList fontlist;
		char **missing_charset_list;
		int  missing_charset_count;
		char *def_string;

		setlocale(LC_ALL, "");

		//建立顶级窗口
		toplevel = XtVaAppInitialize(&app, "FontList", NULL, 0,
			&argc, argv, NULL, NULL);

		//建立fontset
		fontset1 = XCreateFontSet(XtDisplay(toplevel), 
			"8x16,-*-song-medium-r-normal--16-*",
			&missing_charset_list, &missing_charset_count,
			&def_string);
		fontset2 = XCreateFontSet(XtDisplay(toplevel), 
			"12x24,-*-song-medium-r-normal--24-*",
			&missing_charset_list, &missing_charset_count,
			&def_string);

		fontlist_entry1 = XmFontListEntryCreate("small", 
			XmFONT_IS_FONTSET, (XtPointer)fontset1);
		fontlist_entry2 = XmFontListEntryCreate("large", 
			XmFONT_IS_FONTSET, (XtPointer)fontset2);

		fontlist = XmFontListAppendEntry(NULL, fontlist_entry1);
		fontlist = XmFontListAppendEntry(fontlist, fontlist_entry2);

		//建立按钮上标签的字符串
		label1 = XmStringCreate("复合字符串Testing", "small");
		label2 = XmStringCreate("大字体Testing", "large");
		label = XmStringConcat(label1, label2);
		//label = XmStringCreateSimple("复合字符串Testing");

		n = 0;
		XtSetArg(args[n], XmNlabelString, label); 	n++;
		XtSetArg(args[n], XmNfontList, fontlist); 	n++;
		
		button = XmCreatePushButton(toplevel, "pushbutton", args, n);
		XtManageChild(button);

		XmStringFree(label);
		XmStringFree(label1);
		XmStringFree(label2);
		XmFontListEntryFree(&fontlist_entry1);
		XmFontListEntryFree(&fontlist_entry2);
		XmFontListFree(fontlist);

		//显示窗口
		XtRealizeWidget(toplevel);

		//进入事件循环
		XtAppMainLoop(app);

	}
