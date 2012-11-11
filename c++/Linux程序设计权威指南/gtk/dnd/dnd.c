
	/* File: dnd.c */

	#include <gtk/gtk.h>
	#include "xpm.h"

	GdkPixmap *trashcan_open;
	GdkPixmap *trashcan_open_mask;
	GdkPixmap *trashcan_closed;
	GdkPixmap *trashcan_closed_mask;

	gboolean have_drag;

	enum {
  		TARGET_HTML,
  		TARGET_PLAIN,
  		TARGET_URL,
  		TARGET_ROOTWIN,
	};


	static GtkTargetEntry drag_types[] = {
	{"text/plain",             GTK_TARGET_SAME_APP, TARGET_PLAIN},
	{"text/html",              GTK_TARGET_SAME_APP, TARGET_HTML},
  	{"application/x-rootwin-drop", GTK_TARGET_SAME_APP, TARGET_ROOTWIN},
	};
	static GtkTargetEntry drop_types[] = {
  	{ "text/plain", GTK_TARGET_SAME_APP, TARGET_PLAIN },
  	{ "text/url",   GTK_TARGET_SAME_APP, TARGET_URL },
	};

	void target_drag_leave (GtkWidget      *widget,
				   GdkDragContext *context,
				   guint           time)
	{
		g_print ("leave\n");
  		have_drag = FALSE;
  		gtk_pixmap_set (GTK_PIXMAP (widget), 
			trashcan_closed, trashcan_closed_mask);
	}


	gboolean target_drag_motion (GtkWidget      *widget,
					GdkDragContext *context,
					gint            x,
					gint            y,
					guint           time)
	{
  		GtkWidget *source_widget;

  		if (!have_drag) {
	  		have_drag = TRUE;
	  		gtk_pixmap_set (GTK_PIXMAP (widget), 
				trashcan_open, trashcan_open_mask);
		}

  		source_widget = gtk_drag_get_source_widget (context);
  		g_print ("motion, source %s\n", source_widget ?
		   gtk_type_name (GTK_OBJECT (source_widget)->klass->type) :
		   "unknown");

  		gdk_drag_status (context, context->suggested_action, time);
  		return TRUE;
	}

	gboolean target_drag_drop (GtkWidget      *widget,
				  GdkDragContext *context,
				  gint            x,
				  gint            y,
				  guint           time)
	{
  		g_print ("drop\n");
  		have_drag = FALSE;

  		gtk_pixmap_set (GTK_PIXMAP (widget), 
			trashcan_closed, trashcan_closed_mask);

		if (context->targets) {
	  		gtk_drag_get_data (widget, context,
				GPOINTER_TO_INT (context->targets->data), time);
	  		return TRUE;
		}

  		return FALSE;
	}

	void target_drag_data_received (GtkWidget        *widget,
		   GdkDragContext   *context,
		   gint              x,
		   gint              y,
		   GtkSelectionData *data,
		   guint             info,
		   guint             time,
		   gpointer          user_data)
	{
  		if ((data->length >= 0) && (data->format == 8)){
	  		g_print ("Received \"%s\"\n", data->data);
	  		gtk_drag_finish (context, TRUE, FALSE, time);
	  		return;
		}

  		gtk_drag_finish (context, FALSE, FALSE, time);
	}

	void source_drag_data_get (GtkWidget        *widget,
		  GdkDragContext   *context,
		  GtkSelectionData *selection_data,
		  guint             info,
		  guint             time,
		  gpointer          data)
	{
		guchar *str_data = "I'm data from dnd";
		switch (info) {
			case TARGET_ROOTWIN:
	  			g_print ("I was dropped on the rootwin\n");
	  			break;
			case TARGET_PLAIN:
				gtk_selection_data_set (selection_data,
					selection_data->target, 8, 
					str_data, strlen (str_data));
	  			break;
		}
	}

	void source_drag_data_delete (GtkWidget * widget,
			 GdkDragContext * context,
			 gpointer data)
	{
		g_print ("Delete the data!\n");
	}

	gint main (gint argc, gchar **argv)
	{
  		GtkWidget *window;
  		GtkWidget *table;
		GtkWidget *label;
		GtkWidget *button;
		GtkWidget *pixmap;
	  	GdkPixmap *drag_icon;
	  	GdkPixmap *drag_mask;

  		gtk_set_locale ();
  		gtk_init (&argc, &argv);

		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_window_set_title (GTK_WINDOW(window), "Drag and Drop");
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC (gtk_main_quit), NULL);
		table = gtk_table_new (2, 2, FALSE);
		gtk_container_add (GTK_CONTAINER (window), table);

		label = gtk_label_new ("放到这里");
		gtk_signal_connect (GTK_OBJECT (label), "drag_data_received",
			GTK_SIGNAL_FUNC (target_drag_data_received), NULL);

		gtk_drag_dest_set (label, GTK_DEST_DEFAULT_ALL, drop_types,
			sizeof(drop_types) / sizeof(drop_types[0]),
			GDK_ACTION_COPY | GDK_ACTION_MOVE);
		gtk_table_attach (GTK_TABLE (table), label, 0, 1, 0, 1,
			GTK_EXPAND | GTK_FILL, GTK_EXPAND | GTK_FILL, 0, 0);

		trashcan_open = gdk_pixmap_colormap_create_from_xpm_d (NULL,
			gtk_widget_get_colormap (window), 
			&trashcan_open_mask, NULL, trashcan_open_xpm);
		trashcan_closed = gdk_pixmap_colormap_create_from_xpm_d (NULL,
			gtk_widget_get_colormap (window),
			&trashcan_closed_mask, NULL, trashcan_closed_xpm);
		pixmap = gtk_pixmap_new (trashcan_closed, trashcan_closed_mask);
		gtk_signal_connect (GTK_OBJECT (pixmap), "drag_leave",
			GTK_SIGNAL_FUNC (target_drag_leave), NULL);
		gtk_signal_connect (GTK_OBJECT (pixmap), "drag_motion",
			GTK_SIGNAL_FUNC (target_drag_motion), NULL);
		gtk_signal_connect (GTK_OBJECT (pixmap), "drag_drop",
			GTK_SIGNAL_FUNC (target_drag_drop), NULL);
		gtk_signal_connect (GTK_OBJECT (pixmap), "drag_data_received",
			GTK_SIGNAL_FUNC (target_drag_data_received), NULL);
		gtk_drag_dest_set (pixmap,
		   GTK_DEST_DEFAULT_MOTION |
		   GTK_DEST_DEFAULT_HIGHLIGHT |
		   GTK_DEST_DEFAULT_DROP,
		   drop_types, sizeof(drop_types) / sizeof(drop_types[0]),
		   GDK_ACTION_COPY);
		gtk_table_attach_defaults (GTK_TABLE (table), 
			pixmap, 1, 2, 0, 1);

		button = gtk_button_new_with_label ("从这里拖出\n");
	
		gtk_signal_connect (GTK_OBJECT (button), "drag_data_get",
			GTK_SIGNAL_FUNC (source_drag_data_get), NULL);
		gtk_signal_connect (GTK_OBJECT (button), "drag_data_delete",
			GTK_SIGNAL_FUNC (source_drag_data_delete), NULL);
		gtk_drag_source_set (button,
			GDK_BUTTON1_MASK | GDK_BUTTON3_MASK, drag_types,
			sizeof(drag_types) / sizeof(drag_types[0]),
			GDK_ACTION_COPY);
		gtk_table_attach_defaults (GTK_TABLE (table), 
			button, 0, 1, 1, 2);


	  	drag_icon = gdk_pixmap_colormap_create_from_xpm_d (NULL,
			gtk_widget_get_colormap (window), 
			&drag_mask, NULL, drag_icon_xpm);
		gtk_drag_source_set_icon (button,
			gtk_widget_get_colormap (window),
			drag_icon, drag_mask);
	  	gdk_pixmap_unref (drag_icon);
	  	gdk_pixmap_unref (drag_mask);
  		gtk_widget_show_all (window);
  		gtk_main ();
  		return 0;
	}
