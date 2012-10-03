
	/* File: pad.c */

	#include <curses.h>

	int main(int argc, char *argv[])
	{
		int i, j;
		int w, h;
		WINDOW *pad;

		//初始化
		initscr();

		//获得屏幕尺寸
		getmaxyx(stdscr, h,  w);

		//画背景
		for(i=0; i<h; i++)
		for(j=0; j<w; j++){
			mvaddch(i, j, ACS_CKBOARD);
		}
		refresh();

		//建立窗口
		pad = newpad(80, 128);
		for(i=0; i<80; i++){
			char line[128];
			sprintf(line, "This line in pad is numbered %d\n", i);
			mvwprintw(pad, i, 0, line);
		}


		//刷新屏幕
		refresh();
		prefresh(pad, 0, 1, 5, 10, 20, 45);
		
		for(i=0; i<50; i++){
			prefresh(pad, i+1, 1, 5, 10, 20, 45);
			usleep(30000);
		}

		//等待按键
		getch();

		//结束
		delwin(pad);
		endwin();

		return 0;
	}
