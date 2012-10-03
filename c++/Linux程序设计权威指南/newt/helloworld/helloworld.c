
	#include <stdlib.h>
	#include <newt.h>

	int main(int argc, char *argv[]) 
	{
		//定义组件
		newtComponent form, button;

		//初始化
		newtInit();

		//清屏幕
		newtCls();

		//在窗口底部写帮助信息
		newtPushHelpLine("Press button to exit...");

		//建立窗口
		newtOpenWindow(10, 5, 40, 6, "Hello World!");

		//-------------------------------------------
		//建立按钮
		button = newtButton(8, 1, "你好, newt程序员们!");

		//建立Form
		form = newtForm(NULL, NULL, 0);

		//把按钮加入Form
		newtFormAddComponents(form, button, NULL);

		//--------------------------------------------

		//进入循环
		newtRunForm(form);

		//关闭Form
		newtFormDestroy(form);

		//复原
		newtFinished();
	}
