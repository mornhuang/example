
	/* File: notebook.c */
	#include <gtk/gtk.h>

	gint delete( GtkWidget *widget, GtkWidget *event, gpointer   data )
	{
		gtk_main_quit();
		return(FALSE);
	}

	int main( int argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *button;
		GtkWidget *table;
		GtkWidget *notebook;
		GtkWidget *frame;
		GtkWidget *label;
		GtkWidget *checkbutton;
		int i;
		static char *notelabel[] = { "显示器", "鼠标", "键盘"};
    
		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);
    
		//建立根窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_signal_connect (GTK_OBJECT (window), "delete_event",
			GTK_SIGNAL_FUNC (delete), NULL); 
		gtk_container_set_border_width (GTK_CONTAINER (window), 10);

		//建立笔记本
		notebook = gtk_notebook_new ();
		gtk_notebook_set_tab_pos (GTK_NOTEBOOK (notebook), GTK_POS_TOP);
		//gtk_widget_set_usize(notebook, 640, 480);
		gtk_container_add (GTK_CONTAINER (window), notebook);
		gtk_widget_show(notebook);
    
		//加入五个页面
		for (i=0; i < 3; i++) {
			char buf[80];
			//建立notebook页面中的框架容器
			sprintf(buf, "框架 - %s", notelabel[i]);
			frame = gtk_frame_new(buf);
			gtk_container_set_border_width(GTK_CONTAINER(frame),10);
			gtk_widget_set_usize (frame, 100, 75);
			gtk_widget_show (frame);

			//建立框架中的标签
			sprintf(buf, "标签 - %s", notelabel[i]);
			label = gtk_label_new (buf);
			gtk_container_add (GTK_CONTAINER (frame), label);
			gtk_widget_show (label);
	
			//建立笔记本的标签
			label = gtk_label_new (notelabel[i]);
			//加入该页
			gtk_notebook_append_page (GTK_NOTEBOOK (notebook), 
				frame, label);
		}
      
		gtk_notebook_set_page (GTK_NOTEBOOK(notebook), 2);

		gtk_widget_show(window);
    
		gtk_main ();
    
		return(0);
	}
