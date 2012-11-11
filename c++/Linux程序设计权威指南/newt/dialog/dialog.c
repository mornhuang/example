
	/* File: dialog.c */

	#include <stdlib.h>
	#include <newt.h>



	int main(int argc, char *argv[])
	{
		int rc;
		char * menuContents[] = { "One", "Two", "Three", 
			"Four", "Five", NULL };
		int textWidth;
		char * entries[10];
		struct newtWinEntry autoEntries[] = {
			{ "An entry", entries + 0, 0 },
			{ "Another entry", entries + 1, 0 },
			{ "Third entry", entries + 2, 0 },
			{ "Fourth entry", entries + 3, 0 },
			{ NULL, NULL, 0 } };
		memset(entries, 0, sizeof(entries));

		//初始化
		newtInit();
		newtCls();


		//信息框
		newtWinMessage("Simple", "确认", 
			"This is a simple message window");
		newtWinChoice("Simple", "确认", "取消", 
			"This is a simple choice window");
		rc = newtWinMenu("Test Menu", 
			"This is a sample invovation of the "
			"newtWinMenu() call.It may or may not have a scrollbar,"
			"depending on the need for one.", 50, 5, 5, 3,
			menuContents, &textWidth, "确认", "取消", NULL);

		rc = newtWinEntries("Text newtWinEntries()", 
			"This is a sample invovation of "
			"newtWinEntries() call. It lets you get a lot of input "
			"quite easily.", 50, 5, 5, 20, autoEntries, "Ok",
			"Cancel", NULL);

		newtFinished();
	}
