
	/* File: colorsel.c */

	#include <gtk/gtk.h>

	static void color_selection_ok (GtkWidget *w,
                    GtkColorSelectionDialog *cs)
	{
		GtkColorSelection *colorsel;
		gdouble color[4];

		colorsel=GTK_COLOR_SELECTION(cs->colorsel);

		gtk_color_selection_get_color(colorsel, color);
		g_print ("RED:%g, GREEN:%g, BLUE:%g, ALPHA:%g\n",
			color[0], color[1], color[2], color[3]);
		gtk_color_selection_set_color(colorsel, color);
	}

	static void color_selection_changed (GtkWidget *w,
		GtkColorSelectionDialog *cs)
	{
		GtkColorSelection *colorsel;
		gdouble color[4];

		colorsel=GTK_COLOR_SELECTION(cs->colorsel);
		gtk_color_selection_get_color(colorsel,color);
	}

	void color_selection_create (void)
	{
		GtkWidget *window;

		window = gtk_color_selection_dialog_new("color selection");
		gtk_color_selection_set_opacity(
			GTK_COLOR_SELECTION (GTK_COLOR_SELECTION_DIALOG 
				(window)->colorsel), TRUE);

		gtk_color_selection_set_update_policy(GTK_COLOR_SELECTION 
			(GTK_COLOR_SELECTION_DIALOG (window)->colorsel),
			GTK_UPDATE_CONTINUOUS);

		gtk_window_set_position(GTK_WINDOW (window), GTK_WIN_POS_MOUSE);

		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC(gtk_main_quit), NULL);
	  
		gtk_signal_connect(GTK_OBJECT (GTK_COLOR_SELECTION_DIALOG 
			(window)->colorsel), "color_changed",
			GTK_SIGNAL_FUNC(color_selection_changed), window);

		gtk_signal_connect(GTK_OBJECT (GTK_COLOR_SELECTION_DIALOG
			(window)->ok_button), "clicked",
			GTK_SIGNAL_FUNC(color_selection_ok), window);
		gtk_signal_connect_object(GTK_OBJECT 
			(GTK_COLOR_SELECTION_DIALOG (window)->ok_button),
			"clicked", GTK_SIGNAL_FUNC(gtk_widget_destroy),
			GTK_OBJECT (window));
		gtk_signal_connect_object(GTK_OBJECT 
			(GTK_COLOR_SELECTION_DIALOG (window)->cancel_button),
			"clicked", GTK_SIGNAL_FUNC(gtk_widget_destroy),
			GTK_OBJECT (window));

		gtk_widget_show_all (window);
		gtk_main ();

	}

	gint main (gint argc, gchar * argv[])
	{
		GtkWidget *window;
		GtkWidget *button;

		//初始化
		gtk_set_locale ();
		gtk_init (&argc, &argv);

		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC (gtk_main_quit), NULL);
		gtk_container_set_border_width (GTK_CONTAINER (window), 10);

		//建立按钮
		button = gtk_button_new_with_label ("选择颜色...");
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (color_selection_create), NULL);
		gtk_container_add (GTK_CONTAINER (window), button);

		gtk_widget_show_all (window);

		gtk_main ();
		return 0;
	}
