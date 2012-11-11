
	/* File: window.c */

	#include <curses.h>

	int main(int argc, char *argv[])
	{
		WINDOW *win1, *win2, *subwin;

		//初始化
		initscr();


		//建立窗口
		win1 = newwin(15, 50, 1, 1);
		box(win1, ACS_VLINE, ACS_HLINE);
		mvwprintw(win1, 2,1, "WINDOW 1");
		mvwprintw(win1, 4,1, "Press any key switching to window 2");


		win2 = newwin(15, 40, 4, 20);
		box(win2, ACS_VLINE, ACS_HLINE);
		mvwprintw(win2, 2,1, "WINDOW 2");

		//建立子窗口
		subwin = derwin(win2, 5, 25, 4, 5);
		box(subwin, ACS_VLINE, ACS_HLINE);
		mvwprintw(subwin, 2,1, "Sub Window of Window2");

		//刷新屏幕
		refresh();
		wrefresh(win1);
		wrefresh(win2);
		touchwin(win1);
		wrefresh(win1);
		getch();

		touchwin(win2);
		mvwprintw(win2, 12,1, "Press any key to exit...");
		wrefresh(win2);

		//等待按键
		getch();

		//结束
		delwin(win1);
		delwin(subwin);
		delwin(win2);
		endwin();

		return 0;
	}
