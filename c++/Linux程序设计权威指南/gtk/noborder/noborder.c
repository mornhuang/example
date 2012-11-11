
	/* File: noborder.c */

	#include <gtk/gtk.h>
	#include <gdk/gdkx.h>
	#include "penguin.xpm"

	static GdkWindow *root_win = NULL;
	typedef struct _cursoroffset {gint x,y;} CursorOffset;

	//退出
	gint close_application( GtkWidget *widget, 
		GdkEvent  *event, gpointer   data )
	{
		gtk_main_quit();
		return(FALSE);
	}



	static void shape_pressed (GtkWidget *widget, GdkEventButton *event)
	{
		CursorOffset *p;

		//忽略双击和三击按键
		if (event->type != GDK_BUTTON_PRESS) return;

		p = gtk_object_get_user_data (GTK_OBJECT(widget));
		p->x = (int) event->x;
		p->y = (int) event->y;

		gtk_grab_add (widget);
		gdk_pointer_grab (widget->window, TRUE,
			GDK_BUTTON_RELEASE_MASK |
			GDK_BUTTON_MOTION_MASK |
			GDK_POINTER_MOTION_HINT_MASK,
			NULL, NULL, 0);
	}

	static void shape_released (GtkWidget *widget)
	{
		gtk_grab_remove (widget);
		gdk_pointer_ungrab (0);
	}

	static void shape_motion (GtkWidget *widget, GdkEventMotion *event)
	{
		gint xp, yp;
		CursorOffset * p;
		GdkModifierType mask;

		p = gtk_object_get_user_data (GTK_OBJECT (widget));

		//取得绝对位置
		gdk_window_get_pointer (root_win, &xp, &yp, &mask);
		gtk_widget_set_uposition (widget, xp  - p->x, yp  - p->y);
	}



	int main (int argc, char *argv[] )
	{
		GtkWidget *window, *pixmap, *fixed;
		GdkPixmap *gdk_pixmap;
		GdkBitmap *mask;
		GtkStyle *style;
		GdkGC *gc;
		CursorOffset* icon_pos;

    
		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);

		//根窗口
		root_win = gdk_window_foreign_new (GDK_ROOT_WINDOW ());


		//建立无边界窗口
		window = gtk_window_new( GTK_WINDOW_POPUP );
		gtk_widget_set_events (window,
			gtk_widget_get_events (window) |
			GDK_BUTTON_MOTION_MASK |
			GDK_POINTER_MOTION_HINT_MASK |
			GDK_BUTTON_PRESS_MASK);
		gtk_signal_connect (GTK_OBJECT (window), "delete_event",
			GTK_SIGNAL_FUNC (close_application), NULL);
		gtk_signal_connect (GTK_OBJECT (window), "button_press_event",
			GTK_SIGNAL_FUNC (shape_pressed),NULL);
		gtk_signal_connect (GTK_OBJECT (window), "button_release_event",
			GTK_SIGNAL_FUNC (shape_released),NULL);
		gtk_signal_connect (GTK_OBJECT (window), "motion_notify_event",
			GTK_SIGNAL_FUNC (shape_motion),NULL);


		gtk_widget_show (window);

		//建立图像
		style = gtk_widget_get_default_style();
		gc = style->black_gc;
		gdk_pixmap = gdk_pixmap_create_from_xpm_d( window->window, 
			&mask, &style->bg[GTK_STATE_NORMAL], 
			xpenguin_color_xpm );
		pixmap = gtk_pixmap_new( gdk_pixmap, mask );
		gtk_widget_show( pixmap );

		//建立一个定点定位容器
		fixed = gtk_fixed_new();
		//容器的尺寸和图像一样
		gtk_widget_set_usize( fixed, 202, 240 );
		gtk_fixed_put( GTK_FIXED(fixed), pixmap, 0, 0 );
		gtk_container_add( GTK_CONTAINER(window), fixed );
		gtk_widget_show( fixed );

		//对窗口设置掩码
		gtk_widget_shape_combine_mask( window, mask, 0, 0 );

		//设置数据
		icon_pos = g_new (CursorOffset, 1);
		gtk_object_set_user_data(GTK_OBJECT(window), icon_pos);

		//设置窗口的位置
		gtk_widget_set_uposition( window, 200, 400 );
		gtk_widget_show( window );
		gtk_main ();
          
		return(0);
	}
