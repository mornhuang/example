
	/* File: combobox.c */

	#include <stdio.h>
	#include <gtk/gtk.h>
	#include <gtk/gtkcombo.h>

	GtkWidget *status;
	gint context_id;

	void cb_entry(GtkWidget *item, gpointer data)
	{
		char buf[80];
		sprintf(buf, "您选择了: %s", 
			gtk_entry_get_text(GTK_ENTRY(data)));
		gtk_statusbar_push( GTK_STATUSBAR(status), context_id, buf);
	}

	int main( int   argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *combo;
		GList *list = NULL;
		GtkWidget *vbox, *hbox;
		GtkWidget *label;

		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);

		//建立根窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_widget_set_usize (GTK_WIDGET (window), 320, 200);
		gtk_window_set_title (GTK_WINDOW (window), "ComboBox Test");
		gtk_signal_connect (GTK_OBJECT (window), "delete_event",
			(GtkSignalFunc) gtk_main_quit, NULL);

		//建立垂直容器
		vbox = gtk_vbox_new (FALSE, 0);
		gtk_container_add (GTK_CONTAINER (window), vbox);
		gtk_widget_show (vbox);

		hbox = gtk_hbox_new(FALSE, 0);
		gtk_box_pack_start(GTK_BOX(vbox), hbox, FALSE, FALSE, 5);
		gtk_widget_show(hbox);
		//标签
		label = gtk_label_new("请选择项目:");
		gtk_box_pack_start(GTK_BOX(hbox), label, FALSE, FALSE, 5);
		gtk_widget_show(label);
		//建立菜单列表
		list = g_list_append(list, "显示器");
		list = g_list_append(list, "鼠标");
		list = g_list_append(list, "键盘");
		//ComboBox
		combo = gtk_combo_new();
		//设置菜单
		gtk_combo_set_popdown_strings(GTK_COMBO(combo), list);
                gtk_signal_connect(GTK_OBJECT(GTK_COMBO(combo)->entry),
                        "changed", GTK_SIGNAL_FUNC(cb_entry), 
			GTK_COMBO(combo)->entry);
		//释放内存
		g_list_free(list);

		gtk_box_pack_start (GTK_BOX (hbox), combo, FALSE, FALSE, 0);
		gtk_widget_show (combo);

		//建立状态条
		status = gtk_statusbar_new();
		//加入到最下方
		gtk_box_pack_end(GTK_BOX (vbox), status, FALSE, FALSE, 0);
		gtk_widget_show (status);

		//建立状态条的 ID
		context_id = gtk_statusbar_get_context_id(
			GTK_STATUSBAR(status), "Statusbar example");

		gtk_widget_show (window);

		gtk_main ();
		return(0);
	}

