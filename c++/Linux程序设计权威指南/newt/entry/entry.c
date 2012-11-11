
	#include <stdio.h>
	#include <stdlib.h>
	#include <newt.h>
	#include <string.h>

	int main(int argc, char *argv[]) 
	{
		//定义组件
		newtComponent form, label, entry, button_ok, button_cancel;
		newtComponent co;
		char *entryvalue;

		//初始化
		newtInit();

		//清屏幕
		newtCls();

		//建立窗口
		newtCenteredWindow(50, 10, "Entry Sample");

		//-------------------------------------------
		//建立按钮
		label  = newtLabel(6, 1, "请输入住址:");
		entry  = newtEntry(20, 1, "北京市", 28,
			NULL, NEWT_FLAG_SCROLL | NEWT_FLAG_RETURNEXIT);

		button_ok = newtButton(8, 6, "确认");
		button_cancel = newtButton(30, 6, "取消");

		//建立Form
		form = newtForm(NULL, NULL, 0);

		//把按钮加入Form
		newtFormAddComponents(form, label, entry, 
			button_ok, button_cancel, NULL);

		//--------------------------------------------

		//进入循环
		co = newtRunForm(form);

		//获得输入条的值
		if(co == entry || co == button_ok)
			entryvalue = strdup(newtEntryGetValue(entry));
		else
			entryvalue = strdup("Input cancelled.");
 
		//关闭Form
		newtFormDestroy(form);

		//复原
		newtFinished();

		//打印结果
		printf("%s\n", entryvalue);
		free(entryvalue);
	}
