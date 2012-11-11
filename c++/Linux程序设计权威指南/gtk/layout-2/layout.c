	/* File: layout.c */

	#include <gtk/gtk.h>

	void destroy( GtkWidget *widget, gpointer   data )
	{
		gtk_main_quit();
	}

	void callback_ok(GtkWidget *widget, gpointer   data )
	{
		printf("Ok pressed\n");
		printf("Entry:%s\n", gtk_entry_get_text(GTK_ENTRY(data)));
		gtk_main_quit();
	}

	void callback_cancel(GtkWidget *widget, gpointer   data )
	{
		printf("Cancel pressed\n");
		gtk_main_quit();
	}

	int main( int   argc, char *argv[] )
	{
		//GtkWidget 是 Widget的类型
		GtkWidget *window;
		GtkWidget *vbox, *hbox1, *hbox2, *hbox3;
		GtkWidget *label1, *label2;
		GtkWidget *entry1, *entry2;
		GtkWidget *sep;
		GtkWidget *button_ok, *button_cancel;
     
		//设置Locale
		gtk_set_locale();

		//gtk 初始化
		gtk_init(&argc, &argv);
     
		//建立新窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
     
		//把 "destroy" 事件和信号处理器联系起来
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC(destroy), NULL);
     
		//设置窗口的边界宽度
		gtk_container_set_border_width (GTK_CONTAINER (window), 10);
     
		//建立一个垂直容器, 可以垂直安排组件和水平的容器
		vbox = gtk_vbox_new(FALSE, 10);
		gtk_container_add(GTK_CONTAINER(window), vbox);
		gtk_widget_show(vbox);

		//建立第一个水平容器, 容纳一个标签和输入条
		hbox1 = gtk_hbox_new(FALSE, 5);
		gtk_box_pack_start(GTK_BOX(vbox), hbox1, FALSE, FALSE, 0);
		gtk_widget_show(hbox1);
		//标签和输入条
		label1 = gtk_label_new("姓　　名:");
		gtk_box_pack_start(GTK_BOX(hbox1), label1, FALSE, FALSE, 0);
		gtk_widget_show(label1);
		entry1 = gtk_entry_new();
		gtk_box_pack_start(GTK_BOX(hbox1), entry1, TRUE, TRUE, 0);
		gtk_widget_show(entry1);
     

		//建立第二个水平容器, 容纳一个标签和输入条
		hbox2 = gtk_hbox_new(FALSE, 5);
		gtk_box_pack_start(GTK_BOX(vbox), hbox2, FALSE, FALSE, 0);
		gtk_widget_show(hbox2);
		//标签和输入条
		label2 = gtk_label_new("电话号码:");
		gtk_box_pack_start(GTK_BOX(hbox2), label2, FALSE, FALSE, 0);
		gtk_widget_show(label2);
		entry2 = gtk_entry_new();
		gtk_box_pack_start(GTK_BOX(hbox2), entry2, TRUE, TRUE, 0);
		gtk_widget_show(entry2);

		//加入分隔组件
		sep = gtk_hseparator_new();
		gtk_box_pack_start(GTK_BOX(vbox), sep, FALSE, FALSE, 0);
		gtk_widget_show(sep);

		//加入第三个水平容器, 容纳两个按钮
		hbox3 = gtk_hbox_new(FALSE, 5);
		gtk_box_pack_start(GTK_BOX(vbox), hbox3, FALSE, FALSE, 0);
		gtk_widget_show(hbox3);
		//按钮
		button_ok = gtk_button_new_with_label("确认");
		gtk_box_pack_start(GTK_BOX(hbox3), button_ok, TRUE, FALSE, 0);
		gtk_signal_connect(GTK_OBJECT(button_ok), "clicked",
			GTK_SIGNAL_FUNC(callback_ok), entry1);
		gtk_widget_show(button_ok);
		button_cancel = gtk_button_new_with_label("取消");
		gtk_box_pack_start(GTK_BOX(hbox3), button_cancel,TRUE,FALSE, 0);
		gtk_signal_connect(GTK_OBJECT(button_cancel), "clicked",
			GTK_SIGNAL_FUNC(callback_cancel), NULL);
		gtk_widget_show(button_cancel);

		//显示顶级窗口
		gtk_widget_show (window);

		//进入事件循环
		gtk_main ();
     
		return(0);
	}


