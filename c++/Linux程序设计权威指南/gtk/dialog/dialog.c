	/* File: dialog.c */

	#include <gtk/gtk.h>
	#include <signal.h>

	void dialog_yes (GtkWidget *widget, gint *flag)
	{
		*flag = FALSE;
	}

	dialog_no (GtkWidget *widget, gint *flag)
	{
		*flag = TRUE;
	}

	gint delete_event (GtkWidget *widget, 
		GdkEvent *event, GtkWidget *parent)
	{
		static GtkWidget *dialog_window = NULL;
		GtkWidget *label;
		GtkWidget *button;
		gint x, y;

		if (dialog_window == NULL){
			gint flag = TRUE;
			dialog_window = gtk_dialog_new ();

			gtk_signal_connect (GTK_OBJECT (dialog_window), 
				"delete_event", 
				GTK_SIGNAL_FUNC (gtk_false), NULL);
			gtk_signal_connect (GTK_OBJECT (dialog_window),
				"destroy",
				GTK_SIGNAL_FUNC (gtk_main_quit), NULL);

			gtk_window_set_title (GTK_WINDOW (dialog_window),
				"Exit Hello World");
			gtk_container_set_border_width (
				GTK_CONTAINER (dialog_window), 0);
			gtk_widget_set_usize (dialog_window, 250, 110);


			label = gtk_label_new ("真的要退出程序?");
			gtk_box_pack_start (GTK_BOX 
				(GTK_DIALOG(dialog_window)->vbox), 
				label, TRUE, TRUE, 0);
			gtk_widget_show (label);


			button = gtk_button_new_with_label ("确认");
			gtk_signal_connect (GTK_OBJECT (button), "clicked",
				GTK_SIGNAL_FUNC (dialog_yes), &flag);
			gtk_signal_connect_object (GTK_OBJECT (button), "clicked",
				GTK_SIGNAL_FUNC (gtk_widget_destroy),
				GTK_OBJECT (dialog_window));
			GTK_WIDGET_SET_FLAGS (button, GTK_CAN_DEFAULT);
			gtk_box_pack_start (GTK_BOX 
				(GTK_DIALOG (dialog_window)->action_area), 
				button, TRUE, TRUE, 0);
			gtk_widget_grab_default (button);
			gtk_widget_show (button);
	

			button = gtk_button_new_with_label ("取消");
			gtk_signal_connect (GTK_OBJECT (button), "clicked",
				GTK_SIGNAL_FUNC (dialog_no), &flag);
			gtk_signal_connect_object (GTK_OBJECT (button), "clicked",
				GTK_SIGNAL_FUNC (gtk_widget_destroy),
				GTK_OBJECT (dialog_window));
			GTK_WIDGET_SET_FLAGS (button, GTK_CAN_DEFAULT);
			gtk_box_pack_start (GTK_BOX 
				(GTK_DIALOG (dialog_window)->action_area),
				button, TRUE, TRUE, 0);
			gtk_widget_show (button);
	
			//设置窗口锁定状态
	  		gtk_window_set_modal (GTK_WINDOW(dialog_window), TRUE);
			//设置为临时窗口状态
	  		gtk_window_set_transient_for(GTK_WINDOW (dialog_window),
				GTK_WINDOW (parent));

			//取得根窗口的位置
			gdk_window_get_root_origin (parent->window, &x, &y);
			//设置对话框窗口的位置
			gtk_widget_set_uposition (dialog_window, x+40, y+40);
	
	  		gtk_widget_show (dialog_window);
	  		gtk_main ();

	  		dialog_window = NULL;
  
	  		return flag;
		}
		return TRUE;
	}

	void hello (GtkWidget * widget, gpointer data)
	{
		g_print ("Hello World\n");
	}

	gint main (gint argc, gchar * argv[])
	{
		GtkWidget *window;
		GtkWidget *button;

		//初始化
		gtk_set_locale ();
		gtk_init (&argc, &argv);
		//禁止 Ctrl-C
		(void)signal (SIGINT, SIG_IGN);

		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_signal_connect (GTK_OBJECT (window), "delete_event",
			GTK_SIGNAL_FUNC (delete_event), window);
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC (gtk_main_quit), NULL);
		gtk_container_border_width (GTK_CONTAINER (window), 10);

		button = gtk_button_new_with_label ("从窗口管理器关闭窗口");
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (hello), NULL);

		gtk_container_add (GTK_CONTAINER (window), button);
		gtk_widget_show (button);

		gtk_widget_show (window);
  		gtk_main ();
  		g_print ("Good Bye!\n");
  		return 0;
	}
