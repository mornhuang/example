	/* File: helloworld.c */

	#include <gtk/gtk.h>

	/* This is a callback function. The data arguments are ignored
 	* in this example. More on callbacks below. */
	void hello( GtkWidget *widget, gpointer   data )
	{
		g_print ("Hello World\n");
	}

	gint delete_event( GtkWidget *widget,
                    GdkEvent  *event,
                    gpointer   data )
	{
		//打印信息
		g_print ("delete event occurred\n");

		//如果返回FALSE, GTK将发出"destroy"信号; 如果返回TRUE,
		//则不让该窗口关闭
		return(TRUE);
	}

	void destroy( GtkWidget *widget, gpointer   data )
	{
		gtk_main_quit();
	}

	int main( int   argc, char *argv[] )
	{
		//GtkWidget 是 Widget的类型
		GtkWidget *window;
		GtkWidget *button;
     
		//gtk 初始化
		gtk_init(&argc, &argv);
     
		//建立新窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
     
		//当使用窗口管理器关闭窗口时, 将调用 delete_event() 函数
		//本例中所传递的参数是 NULL
		gtk_signal_connect (GTK_OBJECT (window), "delete_event",
			GTK_SIGNAL_FUNC (delete_event), NULL);
     
		//把 "destroy" 事件和信号处理器联系起来
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC (destroy), NULL);
     
		//设置窗口的边界宽度
		gtk_container_set_border_width (GTK_CONTAINER (window), 10);
     
		//建立一个标签是"Hello World"的按钮
		button = gtk_button_new_with_label ("Hello World");
     
		//当按钮被单击时, 即接收到"clicked"信号, 将调用 hello()函数
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (hello), NULL);

		//当按钮被单击时, 调用 gtk_widget_destroy(window)关闭窗口.
		//这里将引发 "destroy" 信号
		gtk_signal_connect_object (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (gtk_widget_destroy),
			GTK_OBJECT (window));
     
		//把按钮加入顶级窗口中
		gtk_container_add (GTK_CONTAINER (window), button);

		//显示按钮
		gtk_widget_show (button);
     
		//显示顶级窗口
		gtk_widget_show (window);

		//进入事件循环
		gtk_main ();
     
		return(0);
	}

