	/* File: layout.c */

	#include <gtk/gtk.h>

	void destroy( GtkWidget *widget, gpointer   data )
	{
		gtk_main_quit();
	}

	int main( int   argc, char *argv[] )
	{
		//GtkWidget 是 Widget的类型
		GtkWidget *window;
		GtkWidget *hbox;
		GtkWidget *label, *entry;
     
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
     
		//建立一个容器, 可以水平地安排组件
		hbox = gtk_hbox_new(FALSE, 0);
		//把按钮加入顶级窗口中
		gtk_container_add (GTK_CONTAINER (window), hbox);
		gtk_widget_show(hbox);

		//建立一个含中文标签, 并加入到hbox中
		label = gtk_label_new("请输入:");
		gtk_box_pack_start(GTK_BOX(hbox), label, FALSE, FALSE, 0);
		gtk_widget_show(label);
     
		//建立一个输入条, 并加入到hbox中
		entry = gtk_entry_new();
		gtk_box_pack_start(GTK_BOX(hbox), entry, TRUE, TRUE, 0);
		gtk_widget_show(entry);

		//显示顶级窗口
		gtk_widget_show (window);

		//进入事件循环
		gtk_main ();
     
		return(0);
	}

