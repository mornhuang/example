
	/* File: buttons.c */

	#include <stdio.h>
	#include <stdlib.h>
	#include <newt.h>
	#include <string.h>

	int main(int argc, char *argv[]) 
	{
		int i;
		//定义组件
		newtComponent form;
		newtComponent text, list;
		newtComponent button_ok, button_cancel;
		newtComponent co;
		char *selection;

		char *message =	"请选择你认为最好的操作系统, "
				"如果你选择了Windows,  说明你"
				"你不了解Linux.";
		char *str_list[7] = {
			"Linux",
			"Windows",
			"Macintosh",
			"Solaris",
			"HP-UX",
			"BeOS",
			"OS/2"
		};


		//初始化
		newtInit();
		newtCls();

		//建立窗口
		newtCenteredWindow(50, 18, "List Sample");

		//-------------------------------------------
		//建立Form
		form = newtForm(NULL, NULL, 0);

		text = newtTextboxReflowed(10, 1, message, 30, 5, 5, 0);
		newtFormAddComponent(form, text);

		//建立列表
		list = newtListbox(8, 5, 8, 
	 	   NEWT_FLAG_SCROLL | NEWT_FLAG_RETURNEXIT |NEWT_FLAG_BORDER);
		newtListboxSetWidth(list, 30);
		for(i=0; i<7; i++)
			newtListboxAppendEntry(list, 
                                str_list[i], str_list[i]);
		newtFormAddComponent(form, list);

		//列表

		button_ok = newtButton(20, 14, "确认");
		newtFormAddComponent(form, button_ok);

		//--------------------------------------------
		//进入循环
		newtRunForm(form);
		selection = strdup((char *)newtListboxGetCurrent(list));
		//关闭Form
		newtFormDestroy(form);
		newtPopWindow();

		newtWinMessage("您的选择", "Ok", selection);

		//复原
		newtFinished();

	}
