
	/* File: style.c */

	#include <gnome.h>

	#define VERSION "1.0"
	#define PACKAGE "style"


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
		{ GNOME_APP_UI_ITEM, "新建(_N)", "Create a new file", 
			item_activated, "file/new", NULL, 
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_MENU_NEW, 
			'n', GDK_CONTROL_MASK, NULL },
		{ GNOME_APP_UI_ITEM, "打开(_O)...", "Open an existing file", 
			item_activated, "file/open", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_MENU_OPEN, 
			'o', GDK_CONTROL_MASK, NULL },
		GNOMEUIINFO_SEPARATOR, 
		{ GNOME_APP_UI_ITEM, "关闭(_C)", "Close the current file", 
			item_activated, "file/close", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_MENU_CLOSE, 
			0, 0, NULL },
		GNOMEUIINFO_END
	};

	static GnomeUIInfo helper_edit_menu[] = {
		{ GNOME_APP_UI_ITEM, "撤销(_U)", "Undo the last operation", 
			item_activated, "edit/undo", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_MENU_UNDO, 
			'z', GDK_CONTROL_MASK, NULL }, 
		{ GNOME_APP_UI_ITEM, "剪切(_t)", "Cut the selection", 
			item_activated, "edit/cut", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_MENU_CUT, 
			'x', GDK_CONTROL_MASK, NULL },
		{ GNOME_APP_UI_ITEM, "拷贝(_C)", "Copy the selection",
			item_activated, "edit/copy", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_MENU_COPY, 
			'c', GDK_CONTROL_MASK, NULL },
		{ GNOME_APP_UI_ITEM, "粘贴(_P)", "Paste the contents", 
			item_activated, "edit/paste", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_MENU_PASTE, 
			'v', GDK_CONTROL_MASK, NULL },
		GNOMEUIINFO_END
	};

	static GnomeUIInfo helper_help_menu[] = {
		{ GNOME_APP_UI_ITEM, "关于(_A)...", 
			"Displays information about the program", about_cb, 
			"help/about", NULL,
			GNOME_APP_PIXMAP_STOCK, GNOME_STOCK_MENU_ABOUT, 
			0, 0, NULL },
		GNOMEUIINFO_END
	};



	static GnomeUIInfo helper_main_menu[] = {
		{ GNOME_APP_UI_SUBTREE, "文件(_F)", "File operations", 
			helper_file_menu, NULL, NULL,
			GNOME_APP_PIXMAP_NONE, NULL, 0, 0, NULL },
		{ GNOME_APP_UI_SUBTREE, "编辑(_E)", "Editing commands", 
			helper_edit_menu, NULL, NULL,
			GNOME_APP_PIXMAP_NONE, NULL, 0, 0, NULL },
		{ GNOME_APP_UI_SUBTREE, "帮助(_H)", "Help on the program", 
			helper_help_menu, NULL, NULL,
			GNOME_APP_PIXMAP_NONE, NULL, 0, 0, NULL },
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
		gtk_widget_set_usize(frame, 400, 200);
		gtk_widget_show (frame);


		//-------------------------------

		//加入组件
		gnome_app_set_contents ( GNOME_APP (app), frame);

		//显示组件
		gtk_widget_show (app);

		gtk_main ();
		return 0;
	}
