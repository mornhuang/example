
	/* File: menu.c */

	#include <stdio.h>
	#include <gtk/gtk.h>
	
	static gint button_press( GtkWidget *widget, GdkEvent *event )
	{
		if (event->type == GDK_BUTTON_PRESS) {
			GdkEventButton *bevent = (GdkEventButton *) event; 
			gtk_menu_popup (GTK_MENU (widget), NULL, NULL, 
				NULL, NULL, bevent->button, bevent->time);
			return TRUE;
		}

		return FALSE;
	}


	static void menuitem_response( gchar *string )
	{
		printf ("%s\n", string);
	}

	int main( int   argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *menu, *menuh;
		GtkWidget *menu_bar;
		GtkWidget *root_menu, *help_menu;
		GtkWidget *menu_items;
		GtkWidget *vbox;
		GtkWidget *button;
		char buf[128];
		int i;

		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);

		//建立根窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_widget_set_usize (GTK_WIDGET (window), 320, 200);
		gtk_window_set_title (GTK_WINDOW (window), "GTK Menu Test");
		gtk_signal_connect (GTK_OBJECT (window), "delete_event",
			(GtkSignalFunc) gtk_main_quit, NULL);

		//建立"文件"菜单, 不要使用 gtk_widget_show()
		menu = gtk_menu_new ();

		//建立菜单项
		menu_items = gtk_menu_item_new_with_label("打开");
		//加入到菜单中
		gtk_menu_append (GTK_MENU(menu), menu_items);
		gtk_signal_connect_object (GTK_OBJECT (menu_items), "activate",
			GTK_SIGNAL_FUNC (menuitem_response), 
			(gpointer) g_strdup ("打开"));
		gtk_widget_show (menu_items);
		//建立菜单项
		menu_items = gtk_menu_item_new_with_label("保存");
		//加入到菜单中
		gtk_menu_append (GTK_MENU(menu), menu_items);
		gtk_signal_connect_object (GTK_OBJECT (menu_items), "activate",
			GTK_SIGNAL_FUNC (menuitem_response), 
			(gpointer) g_strdup ("保存"));
		gtk_widget_show (menu_items);

		//建立分隔线
		menu_items = gtk_menu_item_new();
		gtk_menu_append (GTK_MENU(menu), menu_items);
		gtk_widget_show(menu_items);

		//建立菜单项
		menu_items = gtk_menu_item_new_with_label("退出");
		//加入到菜单中
		gtk_menu_append (GTK_MENU(menu), menu_items);
		gtk_signal_connect_object (GTK_OBJECT (menu_items), "activate",
			GTK_SIGNAL_FUNC (menuitem_response), 
			(gpointer) g_strdup ("退出"));
		gtk_widget_show (menu_items);

		//建立文件菜单
		root_menu = gtk_menu_item_new_with_label("文件");
		gtk_widget_show (root_menu);
		//把它和菜单联系起来
		gtk_menu_item_set_submenu (GTK_MENU_ITEM (root_menu), menu);

		//建立"帮助"菜单, 不要使用 gtk_widget_show()
		menuh = gtk_menu_new ();

		//建立帮助菜单
		help_menu = gtk_menu_item_new_with_label("帮助");
		gtk_widget_show (help_menu);
		//把它和菜单联系起来
		gtk_menu_item_set_submenu (GTK_MENU_ITEM (help_menu), menuh);
                //建立菜单项
                menu_items = gtk_menu_item_new_with_label("关于...");
                //加入到菜单中
                gtk_menu_append (GTK_MENU(menuh), menu_items);
                gtk_signal_connect_object (GTK_OBJECT (menu_items), "activate",
                        GTK_SIGNAL_FUNC (menuitem_response),
                        (gpointer) g_strdup ("关于"));
                gtk_widget_show (menu_items);
		gtk_menu_item_set_submenu (GTK_MENU_ITEM (help_menu), menuh);



		vbox = gtk_vbox_new (FALSE, 0);
		gtk_container_add (GTK_CONTAINER (window), vbox);
		gtk_widget_show (vbox);

		//建立菜单条
		menu_bar = gtk_menu_bar_new ();
		gtk_box_pack_start (GTK_BOX (vbox), menu_bar, FALSE, FALSE, 2);
		gtk_widget_show (menu_bar);
		//把菜单加入到菜单条中
		gtk_menu_bar_append (GTK_MENU_BAR (menu_bar), root_menu);
		gtk_menu_item_right_justify(GTK_MENU_ITEM(help_menu));
			
		gtk_menu_bar_append (GTK_MENU_BAR (menu_bar), help_menu);

		//建立按钮, 用来弹出菜单
		button = gtk_button_new_with_label ("按这里弹出菜单");
		gtk_signal_connect_object (GTK_OBJECT (button), "event",
			GTK_SIGNAL_FUNC (button_press), GTK_OBJECT (menu));
		gtk_box_pack_end (GTK_BOX (vbox), button, TRUE, TRUE, 2);
		gtk_widget_show (button);

		gtk_widget_show (window);

		gtk_main ();
		return(0);
	}

