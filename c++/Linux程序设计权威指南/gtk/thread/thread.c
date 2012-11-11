	/* File: thread.c */

	#include <pthread.h>
	#include <gtk/gtk.h>

	GtkWidget *clist;
	//增加四行两列的列表
	gchar *card[4][3] = { 
		{ "赵凯",    "68001234",	"" },
		{ "李小三",  "022-6234567",	"" },
		{ "王老五",  "13601111111",	"" },
		{ "严新",    "66054321",	"" } };
	
	pthread_t thread1, thread2;
	pthread_mutex_t mutex;

	void thread_add_item(void *ptr)
	{
		int idx;
		while(1){
			idx = (int)random()%4;
			sleep((int)random()%2 + 2);
			pthread_mutex_lock(&mutex);
			card[idx][2] = (char *)ptr;
			gtk_clist_append(GTK_CLIST(clist), card[idx]); 
			pthread_mutex_unlock(&mutex);
		}
	}


	int main( int argc, gchar *argv[] )
	{
		int i, row;
		GtkWidget *window;
		GtkWidget *vbox;
		GtkWidget *scrolled_window;	//clist的滚动窗口
		//标题
		gchar *titles[3] = { "姓名", "电话号码", "住址" };

		//初始化
		gtk_set_locale();
		g_thread_init(NULL);
		gtk_init(&argc, &argv);
     
		//顶级窗口
		window=gtk_window_new(GTK_WINDOW_TOPLEVEL);
		//设置窗口的尺寸
		gtk_widget_set_usize(GTK_WIDGET(window), 400, 250);
		//窗口标题
		gtk_window_set_title(GTK_WINDOW(window), "GtkCList演示");
		gtk_signal_connect(GTK_OBJECT(window), "destroy",
                        GTK_SIGNAL_FUNC(gtk_main_quit), NULL);

		//容器容纳滚动窗口和按钮
		vbox=gtk_vbox_new(FALSE, 5);
		gtk_container_set_border_width(GTK_CONTAINER(vbox), 5);
		gtk_container_add(GTK_CONTAINER(window), vbox);
		gtk_widget_show(vbox); 

		//滚动窗口
		scrolled_window = gtk_scrolled_window_new (NULL, NULL);
		//滚动窗口的滚动规则, 本例中水平方向永远含滚动条,
		//垂直方向的自动条是自动的
		gtk_scrolled_window_set_policy(
			GTK_SCROLLED_WINDOW (scrolled_window),
			GTK_POLICY_AUTOMATIC, GTK_POLICY_ALWAYS);
		//把滚动窗口放到容器中
		gtk_box_pack_start(GTK_BOX(vbox), scrolled_window,TRUE,TRUE,0);
		gtk_widget_show (scrolled_window);

		//建立两列的带标题的CList
		clist = gtk_clist_new_with_titles( 3, titles);
		//设置阴影类型
		gtk_clist_set_shadow_type (GTK_CLIST(clist), GTK_SHADOW_OUT);
		//设置列的宽度(列数是从0开始的)
		gtk_clist_set_column_width (GTK_CLIST(clist), 0, 60);
		gtk_clist_set_column_width (GTK_CLIST(clist), 1, 100);

		//添加行
		for (i=0 ; i < 4 ; i++ ){
			row = gtk_clist_append(GTK_CLIST(clist), card[i]); 
		}

		//把CList加到滚动窗口中
		gtk_container_add(GTK_CONTAINER(scrolled_window), clist);
		gtk_widget_show(clist);
		gtk_widget_show(window);



		//建立线程
		pthread_mutex_init(&mutex, NULL);
		pthread_create(&thread1, NULL, 
			(void *)thread_add_item, (void *)"线程1");
		pthread_create(&thread2, NULL, 
			(void *)thread_add_item, (void *)"线程2");

		gdk_threads_enter();
		gtk_main(); 
		gdk_threads_leave();

		pthread_mutex_destroy(&mutex);
		return(0);
	}
