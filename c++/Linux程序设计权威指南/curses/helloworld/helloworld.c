
	/* File: helloworld.c */

	#include <curses.h>

	int main(int argc, char *argv[])
	{

		//初始化
		initscr();

		//画边框
		box(stdscr, ACS_VLINE, ACS_HLINE);

		//在第11行, 第30列写字符串
		mvaddstr(11, 30, "Hello World!");

		//刷新屏幕
		refresh();

		//等待按键
		getch();

		//结束
		endwin();

		return 0;
	}
