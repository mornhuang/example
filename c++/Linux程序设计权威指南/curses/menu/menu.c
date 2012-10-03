

	/* File: menu.c */
	#include <curses.h>
	#include <menu.h>
	#include <stdlib.h>
	#include <ctype.h>


	static const char *months[] = {
		"January", "Febrary", "March", "April", "May", "June", "July",
		"August", "September", "October", "November", "December", NULL
	};

	#define N_ITEMS sizeof(months)/sizeof(months[0])

	int main(int argc, char *argv[])
	{
		int i;
		int ch;
		int mrows, mcols;
		WINDOW  *win, *subwin;

		ITEM *items[N_ITEMS];
		MENU *mymenu;

		//初始化
		if(initscr() == NULL) {
			perror("initcurs");
			exit(EXIT_FAILURE);
		}
		cbreak();
		noecho();
		keypad(stdscr, TRUE);

		//建立菜单项
		for(i=0; i<N_ITEMS; i++){
			items[i] = new_item(months[i], "");
		}

		//建立菜单
		mymenu = new_menu(items);

		//设置为5行单列的菜单
		set_menu_format(mymenu, 5, 1);
		set_menu_mark(mymenu, "*");

		//获得菜单的行数很列数
		scale_menu(mymenu, &mrows, &mcols);

		//建立窗口和子窗口
		win = newwin(mrows + 2, mcols + 2, 3, 30);
		keypad(win, TRUE);
		box(win, 0, 0);
		subwin = derwin(win, 0, 0, 1, 1);

		//设置菜单的窗口
		set_menu_sub(mymenu, subwin);

		//在子窗口上放置菜单
		post_menu(mymenu);

		refresh();
		wrefresh(win);

		//获得输入
		while(toupper(ch = wgetch(win)) != '\n') {
			if(ch == KEY_DOWN)
				menu_driver(mymenu, REQ_DOWN_ITEM);
			else if(ch == KEY_RIGHT)
				menu_driver(mymenu, REQ_RIGHT_ITEM);
			else if(ch == KEY_UP)
				menu_driver(mymenu, REQ_UP_ITEM);
			else if(ch == KEY_LEFT)
				menu_driver(mymenu, REQ_LEFT_ITEM);
		}

		mvprintw(LINES - 2, 0, "You chose: %s\n", 
			item_name(current_item(mymenu)));

		refresh();
		unpost_menu(mymenu);
		getch();

		//释放内存
		free_menu(mymenu);
		for(i=0; i<N_ITEMS; i++)free_item(items[i]);
		endwin();
		exit(0);
	}
