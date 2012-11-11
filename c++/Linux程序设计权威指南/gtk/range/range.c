
	/* File: range.c */

	#include <gtk/gtk.h>

	//标尺组件
	GtkWidget *hscale, *vscale;

	void cb_pos_menu_select( GtkWidget *item, GtkPositionType  pos )
	{
		//设置标尺上的数值的位置
		gtk_scale_set_value_pos (GTK_SCALE (hscale), pos);
		gtk_scale_set_value_pos (GTK_SCALE (vscale), pos);
	}

	void cb_update_menu_select( GtkWidget *item, GtkUpdateType  policy )
	{
		//设置标尺组件的更新规则
		gtk_range_set_update_policy (GTK_RANGE (hscale), policy);
		gtk_range_set_update_policy (GTK_RANGE (vscale), policy);
	}

	void cb_digits_scale( GtkAdjustment *adj )
	{
		//设置标尺显示数值的小数点后位数
		gtk_scale_set_digits (GTK_SCALE (hscale), (gint) adj->value);
		gtk_scale_set_digits (GTK_SCALE (vscale), (gint) adj->value);
	}

	void cb_page_size( GtkAdjustment *get, GtkAdjustment *set )
	{
		//设置页长度
		set->page_size = get->value;
		set->page_increment = get->value;

		//设置组件属性改变的触发函数
		gtk_signal_emit_by_name (GTK_OBJECT (set), "changed");
	}

	void cb_draw_value( GtkToggleButton *button )
	{
		//显示/不显示组件的数值
		gtk_scale_set_draw_value (GTK_SCALE (hscale), button->active);
		gtk_scale_set_draw_value (GTK_SCALE (vscale), button->active);  
	}

	//建立选项菜单
	GtkWidget *make_menu_item( gchar *name,
		GtkSignalFunc  callback, gpointer data )
	{
		GtkWidget *item;
  
		item = gtk_menu_item_new_with_label (name);
		gtk_signal_connect (GTK_OBJECT (item), "activate",
			callback, data);
		gtk_widget_show (item);

		return(item);
	}

	//设置缺省值
	void scale_set_default_values( GtkScale *scale )
	{
		gtk_range_set_update_policy (GTK_RANGE (scale),
			GTK_UPDATE_CONTINUOUS);
		gtk_scale_set_digits (scale, 1);
		gtk_scale_set_value_pos (scale, GTK_POS_TOP);
		gtk_scale_set_draw_value (scale, TRUE);
	}

	int main( int   argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *box1, *box2, *box3;
		GtkWidget *button;
		GtkWidget *scrollbar;
		GtkWidget *separator;
		GtkWidget *opt, *menu, *item;
		GtkWidget *label;
		GtkWidget *scale;
		GtkObject *adj1, *adj2;

		//初始化
		gtk_set_locale();
		gtk_init(&argc, &argv);
		//建立顶级窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC(gtk_main_quit), NULL);
		gtk_window_set_title (GTK_WINDOW (window), "range controls");

		box1 = gtk_vbox_new (FALSE, 0);
		gtk_container_add (GTK_CONTAINER (window), box1);
		gtk_widget_show (box1);

		box2 = gtk_hbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);
		gtk_box_pack_start (GTK_BOX (box1), box2, TRUE, TRUE, 0);
		gtk_widget_show (box2);

		//建立调节物体
		adj1 = gtk_adjustment_new (0.0, 0.0, 101.0, 0.1, 1.0, 1.0);

		//建立垂直标尺 
		vscale = gtk_vscale_new (GTK_ADJUSTMENT (adj1));
		scale_set_default_values (GTK_SCALE (vscale));
		gtk_box_pack_start (GTK_BOX (box2), vscale, TRUE, TRUE, 0);
		gtk_widget_show (vscale);

		box3 = gtk_vbox_new (FALSE, 10);
		gtk_box_pack_start (GTK_BOX (box2), box3, TRUE, TRUE, 0);
		gtk_widget_show (box3);

		//建立水平标尺
		hscale = gtk_hscale_new (GTK_ADJUSTMENT (adj1));
		gtk_widget_set_usize (GTK_WIDGET (hscale), 200, 30);
		scale_set_default_values (GTK_SCALE (hscale));
		gtk_box_pack_start (GTK_BOX (box3), hscale, TRUE, TRUE, 0);
		gtk_widget_show (hscale);

		//建立滚动条, 使用了同一 adjustment
		scrollbar = gtk_hscrollbar_new (GTK_ADJUSTMENT (adj1));
		gtk_range_set_update_policy (GTK_RANGE (scrollbar), 
			GTK_UPDATE_CONTINUOUS);
		gtk_box_pack_start (GTK_BOX (box3), scrollbar, TRUE, TRUE, 0);
		gtk_widget_show (scrollbar);

		box2 = gtk_hbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);
		gtk_box_pack_start (GTK_BOX (box1), box2, TRUE, TRUE, 0);
		gtk_widget_show (box2);

		button = gtk_check_button_new_with_label(
			"在标尺组件上显示数值");
		gtk_toggle_button_set_active (GTK_TOGGLE_BUTTON (button), TRUE);
		gtk_signal_connect (GTK_OBJECT (button), "toggled",
			GTK_SIGNAL_FUNC(cb_draw_value), NULL);
		gtk_box_pack_start (GTK_BOX (box2), button, TRUE, TRUE, 0);
		gtk_widget_show (button);
  
		box2 = gtk_hbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);

		//选项菜单, 控制标尺的显示数值位置
		label = gtk_label_new ("标尺数值显示位置:");
		gtk_box_pack_start (GTK_BOX (box2), label, FALSE, FALSE, 0);
		gtk_widget_show (label);
 		 
		opt = gtk_option_menu_new();
		menu = gtk_menu_new();

		item = make_menu_item ("顶部",
			GTK_SIGNAL_FUNC(cb_pos_menu_select),
			GINT_TO_POINTER (GTK_POS_TOP));
		gtk_menu_append (GTK_MENU (menu), item);
  
		item = make_menu_item ("底部", 
			GTK_SIGNAL_FUNC (cb_pos_menu_select), 
			GINT_TO_POINTER (GTK_POS_BOTTOM));
		gtk_menu_append (GTK_MENU (menu), item);
  
		item = make_menu_item ("左边", 
			GTK_SIGNAL_FUNC (cb_pos_menu_select),
			GINT_TO_POINTER (GTK_POS_LEFT));
		gtk_menu_append (GTK_MENU (menu), item);
  
		item = make_menu_item ("右边", 
			GTK_SIGNAL_FUNC (cb_pos_menu_select),
			GINT_TO_POINTER (GTK_POS_RIGHT));
		gtk_menu_append (GTK_MENU (menu), item);
  
		gtk_option_menu_set_menu (GTK_OPTION_MENU (opt), menu);
		gtk_box_pack_start (GTK_BOX (box2), opt, TRUE, TRUE, 0);
		gtk_widget_show (opt);

		gtk_box_pack_start (GTK_BOX (box1), box2, TRUE, TRUE, 0);
		gtk_widget_show (box2);

		box2 = gtk_hbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);

		//选项菜单, 用于控制标尺的更新规则
		label = gtk_label_new ("标尺更新规则:");
		gtk_box_pack_start (GTK_BOX (box2), label, FALSE, FALSE, 0);
		gtk_widget_show (label);
  
		opt = gtk_option_menu_new();
		menu = gtk_menu_new();
  
		item = make_menu_item ("连续的",
			GTK_SIGNAL_FUNC (cb_update_menu_select),
			GINT_TO_POINTER (GTK_UPDATE_CONTINUOUS));
		gtk_menu_append (GTK_MENU (menu), item);
  
		item = make_menu_item ("不连续的",
			GTK_SIGNAL_FUNC (cb_update_menu_select),
			GINT_TO_POINTER (GTK_UPDATE_DISCONTINUOUS));
		gtk_menu_append (GTK_MENU (menu), item);
  
		item = make_menu_item ("延迟的",
			GTK_SIGNAL_FUNC (cb_update_menu_select),
			GINT_TO_POINTER (GTK_UPDATE_DELAYED));
		gtk_menu_append (GTK_MENU (menu), item);
  
		gtk_option_menu_set_menu (GTK_OPTION_MENU (opt), menu);
		gtk_box_pack_start (GTK_BOX (box2), opt, TRUE, TRUE, 0);
		gtk_widget_show (opt);
  
		gtk_box_pack_start (GTK_BOX (box1), box2, TRUE, TRUE, 0);
		gtk_widget_show (box2);

		box2 = gtk_hbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);
  
		//水平标尺用来调节样本标尺显示数值的小数位
		label = gtk_label_new ("标尺小数位:");
		gtk_box_pack_start (GTK_BOX (box2), label, FALSE, FALSE, 0);
		gtk_widget_show (label);

		adj2 = gtk_adjustment_new (1.0, 0.0, 5.0, 1.0, 1.0, 0.0);
		gtk_signal_connect (GTK_OBJECT (adj2), "value_changed",
			GTK_SIGNAL_FUNC (cb_digits_scale), NULL);
		scale = gtk_hscale_new (GTK_ADJUSTMENT (adj2));
		gtk_scale_set_digits (GTK_SCALE (scale), 0);
		gtk_box_pack_start (GTK_BOX (box2), scale, TRUE, TRUE, 0);
		gtk_widget_show (scale);

		gtk_box_pack_start (GTK_BOX (box1), box2, TRUE, TRUE, 0);
		gtk_widget_show (box2);
  
		box2 = gtk_hbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);
 
		//水平标尺, 用于控制样本标尺的页长度
		label = gtk_label_new ("滚动条页长度:");
		gtk_box_pack_start (GTK_BOX (box2), label, FALSE, FALSE, 0);
		gtk_widget_show (label);

		adj2 = gtk_adjustment_new (1.0, 1.0, 101.0, 1.0, 1.0, 0.0);
		gtk_signal_connect (GTK_OBJECT (adj2), "value_changed",
                        GTK_SIGNAL_FUNC (cb_page_size), adj1);
		scale = gtk_hscale_new (GTK_ADJUSTMENT (adj2));
		gtk_scale_set_digits (GTK_SCALE (scale), 0);
		gtk_box_pack_start (GTK_BOX (box2), scale, TRUE, TRUE, 0);
		gtk_widget_show (scale);

		gtk_box_pack_start (GTK_BOX (box1), box2, TRUE, TRUE, 0);
		gtk_widget_show (box2);

		separator = gtk_hseparator_new ();
		gtk_box_pack_start (GTK_BOX (box1), separator, FALSE, TRUE, 0);
		gtk_widget_show (separator);

		box2 = gtk_vbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);
		gtk_box_pack_start (GTK_BOX (box1), box2, FALSE, TRUE, 0);
		gtk_widget_show (box2);

		button = gtk_button_new_with_label ("退出");
		gtk_signal_connect_object (GTK_OBJECT (button), "clicked",
			GTK_SIGNAL_FUNC(gtk_main_quit), NULL);
		gtk_box_pack_start (GTK_BOX (box2), button, TRUE, TRUE, 0);
		GTK_WIDGET_SET_FLAGS (button, GTK_CAN_DEFAULT);
		gtk_widget_grab_default (button);
		gtk_widget_show (button);
		
		gtk_widget_show (window);
		gtk_main();
		return(0);
	}


