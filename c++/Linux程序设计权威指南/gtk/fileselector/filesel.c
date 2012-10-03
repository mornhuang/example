
	/* File: filesel.c */
	#include <gtk/gtk.h>

	//选中了文件, 打印文件名
	void file_ok_sel( GtkWidget *w, GtkFileSelection *fs )
	{
		g_print ("%s\n", 
		gtk_file_selection_get_filename (GTK_FILE_SELECTION (fs)));
	}

	void destroy( GtkWidget *widget, gpointer   data )
	{
		gtk_main_quit ();
	}

	int main( int   argc, char *argv[] )
	{
		GtkWidget *filew;
    
		gtk_set_locale();
		gtk_init (&argc, &argv);
    
		//建立文件选择器
		filew = gtk_file_selection_new ("File selection");
		gtk_signal_connect (GTK_OBJECT (filew), "destroy",
			(GtkSignalFunc) destroy, &filew);
		//对文件选择器中的"确认"按钮设置回调
		gtk_signal_connect (
			GTK_OBJECT(GTK_FILE_SELECTION (filew)->ok_button),
			"clicked", (GtkSignalFunc) file_ok_sel, filew );
    
		//对文件选择器中的"取消"按钮设置回调
		gtk_signal_connect_object (
			GTK_OBJECT (GTK_FILE_SELECTION(filew)->cancel_button),
			"clicked", (GtkSignalFunc) gtk_widget_destroy,
			GTK_OBJECT (filew));
    
		//设置缺省的文件名
		gtk_file_selection_set_filename (GTK_FILE_SELECTION(filew), 
				     "/usr/X11R6/bin/xmag");
		gtk_widget_show(filew);
		gtk_main ();
		return 0;
	}
