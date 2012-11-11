
	/* File: stock.c */

	#include <gnome.h>

	#define VERSION "1.0"
	#define PACKAGE "stock"


	static void about_cb (GtkWidget *widget, gpointer data)
	{
		GtkWidget *about;
		gchar *authors[] = {
			"于明俭",
			NULL
		}; 
		about = gnome_about_new("Gnome 编程风格", 
			VERSION,
			"(C) 2000 the OpenCJK Organization",
			(const char **)authors,
			"Simple demonstration for Gnome programming",
			NULL);
		gtk_widget_show (about);
	}


	static void item_activated (GtkWidget *widget, gpointer data)
	{
		printf ("%s activated\n", (char *) data);
	}

	static GnomeUIInfo helper_file_menu[] = {
		GNOMEUIINFO_MENU_NEW_ITEM("新建",NULL,item_activated,NULL),
		GNOMEUIINFO_MENU_OPEN_ITEM(item_activated, "File/Open"),
		GNOMEUIINFO_MENU_SAVE_ITEM(item_activated, "File/Save"),
		GNOMEUIINFO_MENU_SAVE_AS_ITEM(item_activated, "File/SaveAs"),
		GNOMEUIINFO_MENU_REVERT_ITEM(item_activated, "File/Revert"),
		GNOMEUIINFO_MENU_PRINT_ITEM(item_activated, "File/Print"),
		GNOMEUIINFO_SEPARATOR, 
		GNOMEUIINFO_MENU_CLOSE_ITEM(item_activated, "File/Close"),
		GNOMEUIINFO_MENU_EXIT_ITEM(item_activated, "File/Exit"),
		GNOMEUIINFO_END
	};

	static GnomeUIInfo helper_edit_menu[] = {
		GNOMEUIINFO_MENU_UNDO_ITEM(item_activated, "Edit/Undo"),
		GNOMEUIINFO_MENU_REDO_ITEM(item_activated, "Edit/Redo"),
		GNOMEUIINFO_SEPARATOR, 
		GNOMEUIINFO_MENU_CUT_ITEM(item_activated, "Edit/Cut"),
		GNOMEUIINFO_MENU_COPY_ITEM(item_activated, "Edit/Copy"),
		GNOMEUIINFO_MENU_PASTE_ITEM(item_activated, "Edit/Paste"),
		GNOMEUIINFO_SEPARATOR, 
		GNOMEUIINFO_MENU_SELECT_ALL_ITEM(item_activated, "Edit/Select"),
		GNOMEUIINFO_MENU_CLEAR_ITEM(item_activated, "Edit/Clear"),
		GNOMEUIINFO_MENU_FIND_ITEM(item_activated, "Edit/Find"),
		GNOMEUIINFO_MENU_FIND_AGAIN_ITEM(item_activated, "Edit/Find Again"),
		GNOMEUIINFO_MENU_REPLACE_ITEM(item_activated, "Edit/Replace"),
		GNOMEUIINFO_SEPARATOR, 
		GNOMEUIINFO_MENU_PROPERTIES_ITEM(item_activated, "Edit/Properties"),
		GNOMEUIINFO_END
	};

	static GnomeUIInfo helper_settings_menu[] = {
		GNOMEUIINFO_MENU_PREFERENCES_ITEM(item_activated,"Settings/Preferences"),
		GNOMEUIINFO_END
	};

	static GnomeUIInfo helper_windows_menu[] = {
		GNOMEUIINFO_MENU_NEW_WINDOW_ITEM(item_activated, "Windows/New window"),
		GNOMEUIINFO_MENU_CLOSE_WINDOW_ITEM(item_activated, "Windows/Close window"),
		GNOMEUIINFO_END
	};

	static GnomeUIInfo helper_gameop_menu[] = {
		GNOMEUIINFO_MENU_UNDO_MOVE_ITEM(item_activated, "Games/Undo move"),
		GNOMEUIINFO_MENU_REDO_MOVE_ITEM(item_activated, "Games/Redo move"),
		GNOMEUIINFO_END
	};

	static GnomeUIInfo helper_games_menu[] = {
		GNOMEUIINFO_SUBTREE("游戏操作", helper_gameop_menu),
		GNOMEUIINFO_SEPARATOR, 
		GNOMEUIINFO_MENU_NEW_GAME_ITEM(item_activated, "Games/New game"),
		GNOMEUIINFO_MENU_PAUSE_GAME_ITEM(item_activated, "Games/Pause game"),
		GNOMEUIINFO_MENU_RESTART_GAME_ITEM(item_activated, "Games/Restart game"),
		GNOMEUIINFO_MENU_HINT_ITEM(item_activated, "Games/Hint"),
		GNOMEUIINFO_MENU_SCORES_ITEM(item_activated, "Games/Scores"),
		GNOMEUIINFO_SEPARATOR, 
		GNOMEUIINFO_MENU_END_GAME_ITEM(item_activated, "Games/End game"),
		GNOMEUIINFO_END
	};

	static GnomeUIInfo helper_help_menu[] = {
		GNOMEUIINFO_MENU_ABOUT_ITEM(about_cb, "Help/About..."),
		GNOMEUIINFO_END
	};


	static GnomeUIInfo helper_main_menu[] = {
		GNOMEUIINFO_MENU_FILE_TREE (helper_file_menu),
		GNOMEUIINFO_MENU_EDIT_TREE (helper_edit_menu),
		GNOMEUIINFO_MENU_SETTINGS_TREE (helper_settings_menu),
		GNOMEUIINFO_MENU_WINDOWS_TREE (helper_windows_menu),
		GNOMEUIINFO_MENU_GAME_TREE (helper_games_menu),
		GNOMEUIINFO_MENU_HELP_TREE (helper_help_menu),
		GNOMEUIINFO_END
	};




	static GnomeUIInfo helper_toolbar[] = {
		{ GNOME_APP_UI_ITEM, "新建", "Create a new file", 
			item_activated, "toolbar/new", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_PIXMAP_NEW, 
			0, 0, NULL },
		{ GNOME_APP_UI_ITEM, "打开", "Open an existing file", 
			item_activated, "toolbar/open", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_PIXMAP_OPEN, 
			0, 0, NULL },
		{ GNOME_APP_UI_ITEM, "保存", "Save the current file", 
			item_activated, "toolbar/save", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_PIXMAP_SAVE, 
			0, 0, NULL },
		{ GNOME_APP_UI_ITEM, "打印", "Print the current file", 
			item_activated, "toolbar/print", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_PIXMAP_PRINT, 
			0, 0, NULL },
		GNOMEUIINFO_END
	};

	
	void quit_cb (GtkWidget *widget, void *data)
	{
		gtk_main_quit ();
		return;
	}

           
	int main(int argc, char *argv[])
	{ 
		GtkWidget *app;
		GnomeAppBar *bar;
		GtkWidget *frame;

		//初始化
		gnome_init("Style", "1.0", argc, argv);

		//建立主窗口
		app = gnome_app_new ("style", "GNOME Style Programming");
		gtk_signal_connect (GTK_OBJECT (app), "delete_event",
			GTK_SIGNAL_FUNC (quit_cb), NULL);

		//建立菜单
		gnome_app_create_menus (GNOME_APP (app), helper_main_menu);
		//建立按钮
		gnome_app_create_toolbar (GNOME_APP (app), helper_toolbar);
		//建立状态条
		bar = GNOME_APPBAR(
			gnome_appbar_new(FALSE, TRUE, GNOME_PREFERENCES_USER));
		gnome_app_set_statusbar(GNOME_APP(app), GTK_WIDGET(bar));

		//在状态条上显示菜单提示 
		gnome_app_install_appbar_menu_hints(GNOME_APPBAR(bar), 
			helper_main_menu);


		//---- 主要工作区域 ---------------------------

		frame = gtk_frame_new ("主要工作区域");
		gtk_widget_set_usize(frame, 640, 480);
		gtk_widget_show (frame);


		//-------------------------------

		//加入组件
		gnome_app_set_contents ( GNOME_APP (app), frame);

		//显示组件
		gtk_widget_show (app);

		gtk_main ();
		return 0;
	}
