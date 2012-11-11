
	/* File: eventbox.c */

	#include <gtk/gtk.h>

	void callback_enter(GtkWidget *w, gpointer data)
	{
		g_print("pointer entered.\n");
	}

	void callback_leave(GtkWidget *w, gpointer data)
	{
		g_print("pointer left.\n");
	}

	int main( int argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *event_box;
		GtkWidget *label;
		GtkTooltips *tips;
     

		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);
     
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_window_set_title (GTK_WINDOW (window), "Event Box");
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
                         GTK_SIGNAL_FUNC (gtk_exit), NULL);
		gtk_container_set_border_width (GTK_CONTAINER (window), 10);
     
		//建立事件盒容器
		event_box = gtk_event_box_new ();
		gtk_container_add (GTK_CONTAINER(window), event_box);
		gtk_widget_show (event_box);


		//建立标签
		label = gtk_label_new ("按此标签退出!");
		gtk_container_add (GTK_CONTAINER (event_box), label);
		gtk_widget_show (label);

		//设置事件盒感兴趣的事件:按键和进入离开事件
		gtk_widget_set_events (event_box, 
			GDK_BUTTON_PRESS_MASK|
			GDK_ENTER_NOTIFY_MASK|
			GDK_LEAVE_NOTIFY_MASK );
		gtk_signal_connect (GTK_OBJECT(event_box), "button_press_event",
                    GTK_SIGNAL_FUNC (gtk_exit), NULL);
		gtk_signal_connect (GTK_OBJECT(event_box), "enter-notify-event",
                    GTK_SIGNAL_FUNC (callback_enter), NULL);
		gtk_signal_connect (GTK_OBJECT(event_box), "leave-notify-event",
                    GTK_SIGNAL_FUNC (callback_leave), NULL);

     
		//设置事件盒对应的鼠标形状
		gtk_widget_realize (event_box);
		gdk_window_set_cursor (event_box->window, 
			gdk_cursor_new (GDK_HAND1));

		tips = gtk_tooltips_new();
		gtk_tooltips_set_tip(tips, event_box, "本标签用了事件盒!", NULL);
	
		gtk_widget_show (window);
		gtk_main ();
		return(0);
	}
