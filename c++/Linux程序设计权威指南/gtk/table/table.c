
	/* File: table.c */

	#include <gtk/gtk.h>

	void callback( GtkWidget *widget, gpointer   data )
	{
		g_print ("Hello again - %s was pressed\n", (char *) data);
	}

	gint delete_event( GtkWidget *widget, GdkEvent *event, gpointer data )
	{
		gtk_main_quit ();
		return(FALSE);
	}

	int main( int   argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *button;
		GtkWidget *table;

		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);

		//建立根窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_window_set_title (GTK_WINDOW (window), "Table");
		gtk_signal_connect (GTK_OBJECT (window), "delete_event",
			GTK_SIGNAL_FUNC (delete_event), NULL);
		gtk_container_set_border_width (GTK_CONTAINER (window), 20);

		//建立2x2表格容器
		table = gtk_table_new (2, 2, TRUE);
		gtk_table_set_row_spacings(GTK_TABLE(table), 5);
		gtk_table_set_col_spacings(GTK_TABLE(table), 5);
		gtk_container_add (GTK_CONTAINER (window), table);

		//按钮
		button = gtk_button_new_with_label ("这是第一个按钮");
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (callback), (gpointer) "button 1");
		//加入到表格中
		gtk_table_attach_defaults(GTK_TABLE(table), button, 0, 1, 0, 1);
		gtk_widget_show (button);

		button = gtk_button_new_with_label ("这是第二个按钮");
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (callback), (gpointer) "button 2");
		gtk_table_attach_defaults(GTK_TABLE(table), button, 1, 2, 0, 1);
		gtk_widget_show (button);

		button = gtk_button_new_with_label ("退出");
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (delete_event), NULL);
		//退出按钮占据两列
		gtk_table_attach_defaults(GTK_TABLE(table), button, 0, 2, 1, 2);
		gtk_widget_show (button);

		gtk_widget_show (table);
		gtk_widget_show (window);

		gtk_main ();
		
		return 0;
	}
