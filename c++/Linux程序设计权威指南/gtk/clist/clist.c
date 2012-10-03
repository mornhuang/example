	/* File: clist.c */

	#include <gtk/gtk.h>
	#include "gtk.xpm"

	int selected_row = 0;

	//增加列表
	void button_add_clicked( gpointer data )
	{
		int indx;
		int row;
		GtkCList *clist = GTK_CLIST (data);

		//图像
		GdkBitmap *mask;
		GdkPixmap *pixmap;

		//增加四行两列的列表
		gchar *card[4][3] = { 
			{ "赵凯",    "68001234",	"北京" },
			{ "李小三",  "022-6234567",	"山西" },
			{ "王老五",  "13601111111",	"江苏" },
			{ "严新",    "66054321",	"天津" } };

		//建立图像
		pixmap = gdk_pixmap_create_from_xpm_d(clist->clist_window,
				&mask, &GTK_WIDGET (data)->style->white,
				gtk_mini_xpm);


		//冻结列表
		gtk_clist_freeze(clist);

		//增加行
		for ( indx=0 ; indx < 4 ; indx++ ){
			row = gtk_clist_append(clist, card[indx]); 
			gtk_clist_set_pixtext (clist, row, 2, 
				card[indx][2], 5, pixmap, mask);
		}

		//选中(0,0)单元
		gtk_clist_select_row(clist, 0, 0);
		selected_row = 0;

		//解冻
		gtk_clist_thaw(clist);
		return;
	}

	//清除列表
	void button_clear_clicked( gpointer data )
	{
		gtk_clist_clear( (GtkCList *) data);
		return;
	}

	//删除行
	void button_del_clicked( gpointer data )
	{
		gtk_clist_remove((GtkCList *) data, selected_row);
		if(selected_row > 0) selected_row --;
		gtk_clist_select_row((GtkCList *)data, selected_row, 0);
	}

	//显示/隐藏标题
	void button_hide_show_clicked( gpointer data )
	{
		static short int flag = 0;
		if (flag == 0){
			gtk_clist_column_titles_hide((GtkCList *) data);
         		flag++;
		} else {
			gtk_clist_column_titles_show((GtkCList *) data);
			flag--;
		} 
		return;
	}

	//选择某行
	void selection_made( GtkWidget      *clist,
                      gint            row,
                      gint            column,
                      GdkEventButton *event,
                      gpointer        data )
	{
		gchar *text;
		guint8 spacing;
                GdkBitmap *mask;
                GdkPixmap *pixmap;

		//取得字符串
		if(column == 2){
			//第二列含有图像和字符
			gtk_clist_get_pixtext(GTK_CLIST(clist), row, column,
				&text, &spacing, &pixmap, &mask);
		} else {	
			gtk_clist_get_text(GTK_CLIST(clist), row, column,&text);
		}
		selected_row = row;

		g_print("(%d,%d):%s\n", row, column, text);
		return;
	}

	int main( int argc, gchar *argv[] )
	{
		GtkWidget *window;
		GtkWidget *vbox, *hbox;
		GtkWidget *clist;
		GtkWidget *scrolled_window;	//clist的滚动窗口
		GtkWidget *button_add, *button_clear,
			  *button_del, *button_hide_show;    
		//标题
		gchar *titles[3] = { "姓名", "电话号码", "住址" };

		//初始化
		gtk_set_locale();
		gtk_init(&argc, &argv);
     
		//顶级窗口
		window=gtk_window_new(GTK_WINDOW_TOPLEVEL);
		//设置窗口的尺寸
		gtk_widget_set_usize(GTK_WIDGET(window), 400, 250);
		//窗口标题
		gtk_window_set_title(GTK_WINDOW(window), "CList演示");
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
		//选择某行时的回调函数
		gtk_signal_connect(GTK_OBJECT(clist), "select_row",
                        GTK_SIGNAL_FUNC(selection_made),
                        NULL);
		//设置阴影类型
		gtk_clist_set_shadow_type (GTK_CLIST(clist), GTK_SHADOW_OUT);
		//设置列的宽度(列数是从0开始的)
		gtk_clist_set_column_width (GTK_CLIST(clist), 0, 150);
		gtk_clist_set_column_width (GTK_CLIST(clist), 1, 100);

		//设置第一列的最小和最大宽度
		gtk_clist_set_column_max_width (GTK_CLIST (clist), 0, 200);
		gtk_clist_set_column_min_width (GTK_CLIST (clist), 0, 50);

		//设置选择的模式
		//gtk_clist_set_selection_mode (GTK_CLIST (clist), GTK_SELECTION_EXTENDED);
		//设置第0列对齐方式
		gtk_clist_set_column_justification (GTK_CLIST (clist), 0,
			GTK_JUSTIFY_CENTER);

		//设置行的高度
		gtk_clist_set_row_height (GTK_CLIST (clist), 24);

		//把CList加到滚动窗口中
		gtk_container_add(GTK_CONTAINER(scrolled_window), clist);
		gtk_widget_show(clist);

		//水平容器容纳按钮
		hbox = gtk_hbox_new(FALSE, 0);
		gtk_box_pack_start(GTK_BOX(vbox), hbox, FALSE, TRUE, 0);
		gtk_widget_show(hbox);

		//建立按钮
		button_add = gtk_button_new_with_label("增加列表");
		button_clear = gtk_button_new_with_label("清除列表");
		button_del = gtk_button_new_with_label("删除选中行");
		button_hide_show = gtk_button_new_with_label("显示/隐藏标题");
		gtk_box_pack_start(GTK_BOX(hbox), button_add, TRUE, TRUE, 0);
		gtk_box_pack_start(GTK_BOX(hbox), button_clear, TRUE, TRUE, 0);
		gtk_box_pack_start(GTK_BOX(hbox), button_del, TRUE, TRUE, 0);
		gtk_box_pack_start(GTK_BOX(hbox), button_hide_show,TRUE,TRUE,0);

		//设置回调函数
		gtk_signal_connect_object(GTK_OBJECT(button_add), "clicked",
			GTK_SIGNAL_FUNC(button_add_clicked), (gpointer) clist);
		gtk_signal_connect_object(GTK_OBJECT(button_clear), "clicked",
			GTK_SIGNAL_FUNC(button_clear_clicked), (gpointer)clist);
		gtk_signal_connect_object(GTK_OBJECT(button_del), "clicked",
			GTK_SIGNAL_FUNC(button_del_clicked), (gpointer)clist);
		gtk_signal_connect_object(GTK_OBJECT(button_hide_show),"clicked",
			GTK_SIGNAL_FUNC(button_hide_show_clicked), 
			(gpointer) clist);
		gtk_widget_show(button_add);
		gtk_widget_show(button_clear);
		gtk_widget_show(button_del);
		gtk_widget_show(button_hide_show);

		gtk_widget_show(window);
		gtk_main(); 
		return(0);
	}
