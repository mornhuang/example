
	/* File: color.c */

	#include <curses.h>

	int main(int argc, char *argv[])
	{
		int i;

		//初始化
		initscr();

		//颜色初始化
		if(!has_colors() || start_color() == ERR){
			endwin();
			printf("Terminal does not support color.\n");
			exit(1);
		}

		init_pair(1, COLOR_GREEN, COLOR_BLACK);
		init_pair(2, COLOR_RED, COLOR_BLACK);
		init_pair(3, COLOR_CYAN, COLOR_BLACK);
		init_pair(4, COLOR_WHITE, COLOR_BLACK);
		init_pair(5, COLOR_MAGENTA, COLOR_BLACK);
		init_pair(6, COLOR_BLUE, COLOR_BLACK);
		init_pair(7, COLOR_YELLOW, COLOR_BLACK);

		//写字符串
		for(i = 1; i <= 7; i++) {
			attron(COLOR_PAIR(i));
			printw("color pair %d in normal mode\n", i);
		}
		for(i = 1; i <= 7; i++) {
			attron(COLOR_PAIR(i) | A_BLINK | A_UNDERLINE);
			printw("color pair %d in normal mode\n", i);
		}


		//刷新屏幕
		refresh();

		//等待按键
		getch();

		//结束
		endwin();

		return 0;
	}
