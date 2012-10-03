
	/* File: fixed.c */

	#include <gtk/gtk.h>

	//组件位置
	gint x=50;
	gint y=50;

	void move_button( GtkWidget *widget, GtkWidget *fixed )
	{
		x = (x+30)%300;
		y = (y+50)%300;
		gtk_fixed_move( GTK_FIXED(fixed), widget, x, y);
	} 

	int main( int   argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *fixed;
		GtkWidget *button;
		gint i;

		//初始化
		gtk_set_locale();
		gtk_init(&argc, &argv);
     
		//建立根窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_window_set_title(GTK_WINDOW(window), "Fixed Container");
		gtk_widget_set_usize(window, 500, 400);
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC (gtk_main_quit), NULL);
		gtk_container_set_border_width (GTK_CONTAINER (window), 10);

		//建立定位布局容器
		fixed = gtk_fixed_new();
		gtk_container_add(GTK_CONTAINER(window), fixed);
		gtk_widget_show(fixed);
   
		for (i = 1 ; i <= 3 ; i++) {
			char buf[80];
			sprintf(buf, "按钮 %d", i);
			//建立按钮
			button = gtk_button_new_with_label(buf);
			//建立按钮回调
			gtk_signal_connect (GTK_OBJECT (button), "clicked",
				GTK_SIGNAL_FUNC (move_button), fixed);
			//把按钮放到容器中
			gtk_fixed_put (GTK_FIXED (fixed), button, i*50, i*50);
			gtk_widget_show (button);
		}

		gtk_widget_show (window);
		gtk_main ();
		return(0);
	}
