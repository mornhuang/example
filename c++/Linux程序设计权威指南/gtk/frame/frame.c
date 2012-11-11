
	/* File: frame.c */
	#include <gtk/gtk.h>

	int main( int   argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *frame;
		GtkWidget *button;
		gint i;

		//初始化
		gtk_set_locale();
		gtk_init(&argc, &argv);
     
		//建立根窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_window_set_title(GTK_WINDOW(window), "Frame Example");
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC (gtk_main_quit), NULL);
		gtk_widget_set_usize(window, 300, 300);
		gtk_container_set_border_width (GTK_CONTAINER (window), 10);


		//建立框架容器
		frame = gtk_frame_new(NULL);
		gtk_container_add(GTK_CONTAINER(window), frame);

		//设置框架标签
		gtk_frame_set_label( GTK_FRAME(frame), "GTK Frame Widget" );

		//设置标签右对齐
		gtk_frame_set_label_align( GTK_FRAME(frame), 1.0, 0.0);

		//设置框架阴影类型
		gtk_frame_set_shadow_type(GTK_FRAME(frame), 
			GTK_SHADOW_ETCHED_OUT);

		gtk_widget_show(frame);
		gtk_widget_show (window);
		gtk_main ();

		return(0);
	}
