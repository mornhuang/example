	/* File progressbar.c */

	#include <gtk/gtk.h>

	typedef struct _ProgressData {
		GtkWidget *window;
		GtkWidget *pbar;
		int timer;
	} ProgressData;

	//超时函数
	gint progress_timeout( gpointer data )
	{
		gfloat new_val;
		GtkAdjustment *adj;

		//取得进度条数值
		new_val = gtk_progress_get_value( GTK_PROGRESS(data) ) + 1;

		//如果达到最大值, 再从头开始
		adj = GTK_PROGRESS (data)->adjustment;
		if (new_val > adj->upper) new_val = adj->lower;

		//设置进度值
		gtk_progress_set_value (GTK_PROGRESS (data), new_val);

		//返回TRUE, 可以继续调用该函数
		return(TRUE);
	} 

	//切换文本显示的函数
	void toggle_show_text( GtkWidget *widget, ProgressData *pdata )
	{
		gtk_progress_set_show_text (GTK_PROGRESS (pdata->pbar),
			GTK_TOGGLE_BUTTON (widget)->active);
	}

	//设置激活模式的函数
	void toggle_activity_mode( GtkWidget *widget, ProgressData *pdata )
	{
		gtk_progress_set_activity_mode (GTK_PROGRESS (pdata->pbar),
			GTK_TOGGLE_BUTTON (widget)->active);
	}

	//设置连续模式的函数
	void set_continuous_mode( GtkWidget *widget,ProgressData *pdata )
	{
		gtk_progress_bar_set_bar_style (GTK_PROGRESS_BAR (pdata->pbar),
			GTK_PROGRESS_CONTINUOUS);
	}

	//设置不连续模式的函数
	void set_discrete_mode( GtkWidget *widget, ProgressData *pdata )
	{
		gtk_progress_bar_set_bar_style (GTK_PROGRESS_BAR (pdata->pbar),
			GTK_PROGRESS_DISCRETE);
	}
 
	//退出时需要清除内存和定时器
	void destroy_progress( GtkWidget *widget, ProgressData *pdata)
	{
		gtk_timeout_remove (pdata->timer);
		pdata->timer = 0;
		pdata->window = NULL;
		g_free(pdata);
		gtk_main_quit();
	}

	int main(int  argc, char *argv[])
	{
		ProgressData *pdata;
		GtkWidget *align;
		GtkWidget *separator;
		GtkWidget *table;
		GtkAdjustment *adj;
		GtkWidget *button;
		GtkWidget *check;
		GtkWidget *vbox;

		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);
		pdata = g_malloc( sizeof(ProgressData) );
  
		//建立根窗口
		pdata->window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		//设置窗口属性
		gtk_window_set_policy (GTK_WINDOW (pdata->window), 
			FALSE, FALSE, TRUE);
		gtk_signal_connect (GTK_OBJECT (pdata->window), "destroy",
			GTK_SIGNAL_FUNC (destroy_progress), pdata);
		gtk_window_set_title(GTK_WINDOW(pdata->window),"ProgressBar");
		gtk_container_set_border_width(GTK_CONTAINER(pdata->window),0);

		//垂直方向的容器
		vbox = gtk_vbox_new (FALSE, 5);
		gtk_container_set_border_width (GTK_CONTAINER (vbox), 10);
		gtk_container_add (GTK_CONTAINER (pdata->window), vbox);
		gtk_widget_show(vbox);
  
		//中间对齐
		align = gtk_alignment_new (0.5, 0.5, 0, 0);
		gtk_box_pack_start (GTK_BOX (vbox), align, FALSE, FALSE, 5);
		gtk_widget_show(align);

		//建立调节器
		adj = (GtkAdjustment *)gtk_adjustment_new (0, 1, 150, 0, 0, 0);
		//建立使用该调节器的进度条
		pdata->pbar = gtk_progress_bar_new_with_adjustment (adj);
		//设置进度条的显示文本格式
		gtk_progress_set_format_string (GTK_PROGRESS (pdata->pbar),
			"%v from [%l-%u] (=%p%%)");
		gtk_container_add (GTK_CONTAINER (align), pdata->pbar);
		gtk_widget_show(pdata->pbar);

		//建立定时器, 来更新进度条
		pdata->timer = gtk_timeout_add (100, 
			progress_timeout, pdata->pbar);


		separator = gtk_hseparator_new ();
		gtk_box_pack_start (GTK_BOX (vbox), separator, FALSE, FALSE, 0);
		gtk_widget_show(separator);

		//两行三列的表格容器
		table = gtk_table_new (2, 3, FALSE);
		gtk_box_pack_start (GTK_BOX (vbox), table, FALSE, TRUE, 0);
		gtk_widget_show(table);

		//显示文本的check button
		check = gtk_check_button_new_with_label ("显示文本");
		gtk_table_attach (GTK_TABLE (table), check, 0, 1, 0, 1,
			GTK_EXPAND | GTK_FILL, GTK_EXPAND | GTK_FILL, 5, 5);
		gtk_signal_connect (GTK_OBJECT (check), "clicked",
			GTK_SIGNAL_FUNC (toggle_show_text), pdata);
		gtk_widget_show(check);

		//显示激活状态check button
		check = gtk_check_button_new_with_label ("激活状态");
		gtk_table_attach (GTK_TABLE (table), check, 0, 1, 1, 2,
			GTK_EXPAND | GTK_FILL, GTK_EXPAND | GTK_FILL, 5, 5);
		gtk_signal_connect (GTK_OBJECT (check), "clicked",
			GTK_SIGNAL_FUNC (toggle_activity_mode), pdata);
		gtk_widget_show(check);

		separator = gtk_vseparator_new ();
		gtk_table_attach (GTK_TABLE (table), separator, 1, 2, 0, 2,
			GTK_EXPAND | GTK_FILL, GTK_EXPAND | GTK_FILL, 5, 5);
		gtk_widget_show(separator);

		//设置连续模式的 radio button
		button = gtk_radio_button_new_with_label (NULL, "连续模式");
		gtk_table_attach (GTK_TABLE (table), button, 2, 3, 0, 1,
			GTK_EXPAND | GTK_FILL, GTK_EXPAND | GTK_FILL, 5, 5);
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (set_continuous_mode), pdata);
		gtk_widget_show (button);

		//设置不连续模式的 radio button
		button = gtk_radio_button_new_with_label(
			gtk_radio_button_group (GTK_RADIO_BUTTON (button)),
			"不连续模式");
		gtk_table_attach (GTK_TABLE (table), button, 2, 3, 1, 2,
			GTK_EXPAND | GTK_FILL, GTK_EXPAND | GTK_FILL, 5, 5);
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC (set_discrete_mode), pdata);
		gtk_widget_show (button);

		separator = gtk_hseparator_new ();
		gtk_box_pack_start (GTK_BOX (vbox), separator, FALSE, FALSE, 0);
		gtk_widget_show(separator);

		//退出按钮
		button = gtk_button_new_with_label ("close");
		gtk_signal_connect_object (GTK_OBJECT (button), "clicked",
			(GtkSignalFunc) gtk_widget_destroy,
			GTK_OBJECT (pdata->window));
		gtk_box_pack_start (GTK_BOX (vbox), button, FALSE, FALSE, 0);

		//设置为可以缺省聚焦
		GTK_WIDGET_SET_FLAGS (button, GTK_CAN_DEFAULT);

		gtk_widget_grab_default (button);
		gtk_widget_show(button);

		gtk_widget_show (pdata->window);

		gtk_main ();
    
		return(0);
	}
