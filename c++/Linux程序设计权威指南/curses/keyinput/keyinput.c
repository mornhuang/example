

	/* File: keyinput.c */
	#include <curses.h>
	#include <stdlib.h>
	#include <ctype.h>

	int main(int argc, char *argv[])
	{
		WINDOW  *win;
		int ch;
		int w = 40, h = 10;
		int curx = 10, cury = 5;
 
		//初始化
		if(initscr() == NULL) {
			perror("initcurs");
			exit(EXIT_FAILURE);
		}

		//设置模式
		cbreak();
		noecho();
		keypad(stdscr, TRUE);


		//建立窗口
		win = newwin(h, w, 3, 20);
		box(win, 0, 0);
		keypad(win, TRUE);
		wmove(win, cury, curx);
		mvaddstr(16, 1, "Press arrow keys to move the cursor within the window.\n");
		mvaddstr(17, 1, "Press 'q' to quit.\n");
		refresh();
		wrefresh(win);

		//获得输入
		for(;;){
			ch = wgetch(win);
			if(ch == 'q') break;
			if(ch == KEY_UP) {if(cury > 1)cury --;}
			else if(ch == KEY_DOWN) {if(cury < h-2) cury ++;}
			else if(ch == KEY_LEFT) {if(curx > 1) curx --;}
			else if(ch == KEY_RIGHT){if(curx < w-2) curx ++;}

			wmove(win, cury, curx);

			mvprintw(20, 1, "Input Key: %s\n", keyname(ch));
			refresh();
		}

		delwin(win);
		endwin();
		exit(0);
	}
