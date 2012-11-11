	/* File: gdkdrawing.c */
	/* example from gtk+ package */
	#include <gtk/gtk.h>

	static GdkPixmap *pixmap = NULL;

	static gint configure_event( GtkWidget *widget,GdkEventConfigure *event)
	{
		if (pixmap) gdk_pixmap_unref(pixmap);

		pixmap = gdk_pixmap_new(widget->window,
			  widget->allocation.width,
			  widget->allocation.height,
			  -1);
		gdk_draw_rectangle (pixmap,
		      widget->style->white_gc,
		      TRUE,
		      0, 0,
		      widget->allocation.width,
		      widget->allocation.height);

		return TRUE;
	}

	static gint expose_event( GtkWidget *widget, GdkEventExpose *event )
	{
		gdk_draw_pixmap(widget->window,
		  widget->style->fg_gc[GTK_WIDGET_STATE (widget)],
		  pixmap,
		  event->area.x, event->area.y,
		  event->area.x, event->area.y,
		  event->area.width, event->area.height);

		return FALSE;
	}

	static void draw_brush( GtkWidget *widget, gdouble x, gdouble y)
	{
		GdkRectangle update_rect;

		update_rect.x = x - 5;
		update_rect.y = y - 5;
		update_rect.width = 10;
		update_rect.height = 10;
		gdk_draw_rectangle (pixmap,
		      widget->style->black_gc,
		      TRUE,
		      update_rect.x, update_rect.y,
		      update_rect.width, update_rect.height);
		gtk_widget_draw (widget, &update_rect);
	}

	static gint button_press_event( GtkWidget *widget,GdkEventButton *event)
	{
		if (event->button == 1 && pixmap != NULL)
			draw_brush (widget, event->x, event->y);

		return TRUE;
	}

	static gint motion_notify_event( GtkWidget *widget,
		GdkEventMotion *event )
	{
		int x, y;
		GdkModifierType state;

		if (event->is_hint)
			gdk_window_get_pointer (event->window, &x, &y, &state);
		else {
			x = event->x;
			y = event->y;
			state = event->state;
		}
    
		if (state & GDK_BUTTON1_MASK && pixmap != NULL)
			draw_brush (widget, x, y);
  
		return TRUE;
	}

	void quit ()
	{
		gtk_exit (0);
	}

	int main( int   argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *drawing_area;
		GtkWidget *vbox;
		GtkWidget *button;

		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);

		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_widget_set_name (window, "Drawing Area");

		vbox = gtk_vbox_new (FALSE, 0);
		gtk_container_add (GTK_CONTAINER (window), vbox);
		gtk_widget_show (vbox);

		gtk_signal_connect (GTK_OBJECT (window), "destroy",
		      GTK_SIGNAL_FUNC (quit), NULL);

		//建立绘图区
		drawing_area = gtk_drawing_area_new ();
		gtk_drawing_area_size(GTK_DRAWING_AREA(drawing_area), 200, 200);
		gtk_box_pack_start(GTK_BOX(vbox), drawing_area, TRUE, TRUE, 0);
		gtk_widget_show (drawing_area);

		//建立信号触发
		gtk_signal_connect (GTK_OBJECT (drawing_area), "expose_event",
		      (GtkSignalFunc) expose_event, NULL);
		gtk_signal_connect (GTK_OBJECT(drawing_area),"configure_event",
		      (GtkSignalFunc) configure_event, NULL);
		gtk_signal_connect (GTK_OBJECT (drawing_area), 
			"motion_notify_event",
			(GtkSignalFunc) motion_notify_event, NULL);
		gtk_signal_connect (GTK_OBJECT (drawing_area), 
			"button_press_event",
			(GtkSignalFunc) button_press_event, NULL);

		//设置事件掩码
		gtk_widget_set_events (drawing_area, GDK_EXPOSURE_MASK
			 | GDK_LEAVE_NOTIFY_MASK
			 | GDK_BUTTON_PRESS_MASK
			 | GDK_POINTER_MOTION_MASK
			 | GDK_POINTER_MOTION_HINT_MASK);

		button = gtk_button_new_with_label ("退出");
		gtk_box_pack_start (GTK_BOX (vbox), button, FALSE, FALSE, 0);

		gtk_signal_connect_object (GTK_OBJECT (button), "clicked",
			     GTK_SIGNAL_FUNC (gtk_widget_destroy),
			     GTK_OBJECT (window));
		gtk_widget_show (button);

		gtk_widget_show (window);

		gtk_main ();

		return 0;
	}
