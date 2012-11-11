

	/* File: toolbar.c */
	#include <gtk/gtk.h>
	#include "gtk.xpm"

	gint delete_event (GtkWidget *widget, GdkEvent *event, gpointer data)
	{
		gtk_main_quit ();
		return(FALSE);
	}

	void cb_button (GtkWidget *widget, gpointer data)
	{
		g_print("button pressed.\n");
	}

	int main (int argc, char *argv[])
	{
		GtkWidget* dialog;
		GtkWidget* handlebox;
		GtkWidget* close_button, *backward_button, 
			   *forward_button, *refresh_button;
		GtkWidget* tooltips_button;
		GtkWidget* entry;

		GtkWidget * toolbar;
		GdkPixmap * icon;
		GdkBitmap * mask;
		GtkWidget * iconw;

		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);
   
		//建立对话框
		dialog = gtk_dialog_new ();
		gtk_window_set_title (GTK_WINDOW(dialog) , "GTKToolbar");
		gtk_widget_set_usize(GTK_WIDGET ( dialog ) , 600 , 300 );
		GTK_WINDOW ( dialog ) ->allow_shrink = TRUE;

		//关闭窗口行为
		gtk_signal_connect ( GTK_OBJECT ( dialog ), "delete_event",
			GTK_SIGNAL_FUNC(delete_event), NULL);

		//实现对话框, 以备生成图像
		gtk_widget_realize ( dialog );

		//handle box 是更高级的容器, 它可以使按钮和窗口分离
		handlebox = gtk_handle_box_new ();
		gtk_box_pack_start ( GTK_BOX ( GTK_DIALOG(dialog)->vbox ),
			handlebox, FALSE, FALSE, 5 );

		//建立横向的, 含有文本和图像按钮的按钮条
		toolbar = gtk_toolbar_new ( GTK_ORIENTATION_HORIZONTAL,
			GTK_TOOLBAR_BOTH );
		gtk_container_set_border_width(GTK_CONTAINER ( toolbar ) , 5 );
		gtk_toolbar_set_space_size ( GTK_TOOLBAR ( toolbar ), 5);
		gtk_toolbar_set_button_relief( GTK_TOOLBAR ( toolbar ),
			GTK_RELIEF_NONE);
		gtk_container_add ( GTK_CONTAINER ( handlebox ) , toolbar );

		//建立图像
		icon = gdk_pixmap_create_from_xpm_d(dialog->window, &mask,
			&dialog->style->white, gtk_xpm);
		iconw = gtk_pixmap_new (icon, mask);
		//建立按钮
		close_button = gtk_toolbar_append_item(GTK_TOOLBAR (toolbar),
			"关闭", "关闭程序", "Private", iconw,
			GTK_SIGNAL_FUNC (delete_event), NULL );
		gtk_toolbar_append_space(GTK_TOOLBAR ( toolbar ) );
		gtk_widget_set_usize(GTK_WIDGET(close_button) , 60 , 60 );

		//建立按钮
		iconw = gtk_pixmap_new ( icon, mask );
		backward_button=gtk_toolbar_append_item(GTK_TOOLBAR(toolbar),
			"前进", "向前翻页", "Private", iconw,
			GTK_SIGNAL_FUNC (cb_button), backward_button);
		gtk_toolbar_append_space ( GTK_TOOLBAR ( toolbar ) );

		//建立按钮
		iconw = gtk_pixmap_new ( icon, mask );
		forward_button=gtk_toolbar_append_item(GTK_TOOLBAR(toolbar),
			"刷新", "刷新当前页", "Private", iconw,
			GTK_SIGNAL_FUNC (cb_button), forward_button);
		gtk_toolbar_append_space ( GTK_TOOLBAR ( toolbar ) );

		//建立输入条
		entry = gtk_entry_new ();
		gtk_toolbar_append_widget(GTK_TOOLBAR (toolbar), entry, 
			"输入条 for fun", "Private" );
		gtk_widget_show ( entry );


		gtk_widget_show ( toolbar );
		gtk_widget_show (handlebox);
		gtk_widget_show ( dialog );

		gtk_main ();
		return 0;
	}


