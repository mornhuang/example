	/* File: radiobuttons.c */

	#include <gtk/gtk.h>

	GtkWidget *button[3];

	void callback(GtkWidget *w, gpointer data)
	{
		gint i;
		for(i=0; i<3; i++){
			if(GTK_TOGGLE_BUTTON(button[i])->active)
				g_print("button %d pressed\n", i);
		}
		gtk_exit(0);
	}

	gint main (gint argc, gchar *argv[])
	{
		GtkWidget *window;		//顶级窗口
		GtkWidget *vbox;		
		GtkWidget *separator;
		GtkWidget *button_quit;
		GSList *group;

		//初始化
		gtk_set_locale ();
		gtk_init(&argc, &argv);

		window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
		gtk_signal_connect(GTK_OBJECT(window), "destroy",
			GTK_SIGNAL_FUNC(gtk_main_quit),
			NULL);
		gtk_window_set_title(GTK_WINDOW(window), "radio buttons");
		gtk_container_border_width(GTK_CONTAINER(window), 10);

		//建立垂直排放的容器
		vbox = gtk_vbox_new(FALSE, 10);
		gtk_container_add(GTK_CONTAINER(window), vbox);
		gtk_widget_show(vbox);

		//建立 RadioButton 组, 调用函数是
		//gtk_radio_button_new_with_label(GSList *group, 
		//                            const gchar *label);
		//建立第一个按钮时把 group 设置为 NULL
		button[0] = gtk_radio_button_new_with_label(NULL, "新浪网");
		gtk_box_pack_start(GTK_BOX(vbox), button[0], TRUE, TRUE, 0);
		gtk_widget_show(button[0]);

		//建立第二个按钮时需指定按钮所在的组, 这时需要事先
		//获得第一个按钮所在的组
		group = gtk_radio_button_group(GTK_RADIO_BUTTON(button[0]));
		button[1] = gtk_radio_button_new_with_label(group, "网易");
		//把该按钮设为缺省的选择按钮
		gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(button[1]),TRUE);
		gtk_box_pack_start(GTK_BOX(vbox), button[1], TRUE, TRUE, 0);
		gtk_widget_show(button[1]);

		//建立第三个按钮
		group = gtk_radio_button_group(GTK_RADIO_BUTTON(button[1]));
		button[2] = gtk_radio_button_new_with_label(group, "搜狐");
		gtk_box_pack_start(GTK_BOX(vbox), button[2], TRUE, TRUE, 0);
		gtk_widget_show(button[2]);

		separator = gtk_hseparator_new ();
		gtk_box_pack_start(GTK_BOX(vbox), separator, FALSE, TRUE, 0);
		gtk_widget_show (separator);

		//建立按钮, 以便退出
		button_quit = gtk_button_new_with_label("退出");
		gtk_signal_connect_object(GTK_OBJECT(button_quit), "clicked",
			 GTK_SIGNAL_FUNC(callback), NULL);
		gtk_box_pack_start(GTK_BOX(vbox), button_quit, TRUE, TRUE, 0);

		//把按钮设为缺省聚焦状态
		GTK_WIDGET_SET_FLAGS(button_quit, GTK_CAN_DEFAULT);
		gtk_widget_grab_default (button_quit);
		gtk_widget_show(button_quit);

		gtk_widget_show (window);
		gtk_main ();

		return (0);
	}
