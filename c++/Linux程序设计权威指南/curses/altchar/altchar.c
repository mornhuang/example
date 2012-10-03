
	/* File: altchar.c */

	#include <curses.h>

	#define BOTH(name) #name, name

	int show_char(int n, const char *name, chtype code)
	{
		const int height = 16;
		int row = 4 + (n % height);
		int col = (n / height) * COLS / 2;
		mvprintw(row, col, "%*s : ", COLS/4, name);
		addch(code);
		return n + 1;
	}


	int main(int argc, char *argv[])
	{
		int n = 0;
		//初始化
		initscr();

		n = show_char(n, BOTH(ACS_ULCORNER));
		n = show_char(n, BOTH(ACS_LLCORNER));
		n = show_char(n, BOTH(ACS_URCORNER));
		n = show_char(n, BOTH(ACS_LRCORNER));
		n = show_char(n, BOTH(ACS_RTEE));
		n = show_char(n, BOTH(ACS_LTEE));
		n = show_char(n, BOTH(ACS_BTEE));
		n = show_char(n, BOTH(ACS_TTEE));
		n = show_char(n, BOTH(ACS_HLINE));
		n = show_char(n, BOTH(ACS_VLINE));
		n = show_char(n, BOTH(ACS_PLUS));
		n = show_char(n, BOTH(ACS_S1));
		n = show_char(n, BOTH(ACS_S9));
		n = show_char(n, BOTH(ACS_DIAMOND));
		n = show_char(n, BOTH(ACS_CKBOARD));
		n = show_char(n, BOTH(ACS_DEGREE));
		n = show_char(n, BOTH(ACS_PLMINUS));
		n = show_char(n, BOTH(ACS_BULLET));
		n = show_char(n, BOTH(ACS_LARROW));
		n = show_char(n, BOTH(ACS_RARROW));
		n = show_char(n, BOTH(ACS_DARROW));
		n = show_char(n, BOTH(ACS_UARROW));
		n = show_char(n, BOTH(ACS_BOARD));
		n = show_char(n, BOTH(ACS_LANTERN));
		n = show_char(n, BOTH(ACS_BLOCK));
		n = show_char(n, BOTH(ACS_S3));
		n = show_char(n, BOTH(ACS_S7));
		n = show_char(n, BOTH(ACS_LEQUAL));
		n = show_char(n, BOTH(ACS_GEQUAL));
		n = show_char(n, BOTH(ACS_PI));
		n = show_char(n, BOTH(ACS_NEQUAL));
		n = show_char(n, BOTH(ACS_STERLING));
		
		refresh();

		//等待按键
		getch();

		endwin();

		return 0;
	}
