
	/* File: form.c */
	#include <curses.h>
	#include <form.h>
	#include <stdlib.h>
	#include <ctype.h>

	int main(int argc, char *argvp[])
	{
		int ch, i = 0;
		int rows, cols;
		WINDOW *win, *subwin;
		FORM *form;
		FIELD *fields[5];

		//初始化
		initscr();
		cbreak();
		//nonl();
		noecho();
		keypad(stdscr, TRUE);

		//建立单元
		fields[0] = new_field(1, 12, 1, 1, 0, 0);
		set_field_buffer(fields[0], 0, "User Name: ");
		set_field_opts(fields[0], field_opts(fields[0]) & ~O_ACTIVE);

		fields[1] = new_field(1, 20, 1, 14, 0, 0);
		set_field_back(fields[1], A_UNDERLINE);
		set_field_userptr(fields[1], NULL);

		fields[2] = new_field(1, 12, 2, 1, 0, 0);
		set_field_buffer(fields[2], 0, "Password : ");
		set_field_opts(fields[2], field_opts(fields[2]) & ~O_ACTIVE);
    
		fields[3] = new_field(1, 20, 2, 14, 0, 0);
		set_field_back(fields[3], A_UNDERLINE);
		set_field_opts(fields[3], field_opts(fields[3]) & ~O_PUBLIC);
		set_field_userptr(fields[3], NULL);

		fields[4] = NULL;

		//建立表格
		form = new_form(fields);

		scale_form(form, &rows, &cols);
		win = newwin(rows+3, cols+4, 3, 20);
		subwin = derwin(win, rows, cols, 1, 2);
		set_form_sub(form, subwin);
		box(win, 0, 0);
		keypad(win, TRUE);

		post_form(form);
		refresh();
		wrefresh(win);
		wrefresh(subwin);

		//设置覆盖模式
		form_driver(form, REQ_OVL_MODE);

		for(;;){
			ch = wgetch(win);
			if(ch == '\n' 
				&& current_field(form) == fields[3]){
				form_driver(form, REQ_END_FIELD);
				break;
			}
			else if(ch == KEY_UP || ch == KEY_PPAGE)
				form_driver(form, REQ_PREV_FIELD);
			else if(ch == '\n' || ch == KEY_DOWN || ch == KEY_NPAGE)
				form_driver(form, REQ_NEXT_FIELD);
			else if(isprint(ch))
				form_driver(form, ch);
			else
				form_driver(form, E_UNKNOWN_COMMAND);
		}

		//输出结果
		mvprintw(18, 1, "User Name:%s", field_buffer(fields[1], 0));
		mvprintw(19, 1, "Password :%s", field_buffer(fields[3], 0));
		mvaddstr(21, 1, "Press any key to quit...");

		refresh();
		wrefresh(win);
		wrefresh(subwin);
		getch();

		//撤销表格
		unpost_form(form);

		//释放内存
		free_form(form);
		for(i = 0; i < 5; i++) free_field(fields[i]);

		//复位
		keypad(stdscr, FALSE);
		nocbreak();
		endwin();
		return 0;
	}
