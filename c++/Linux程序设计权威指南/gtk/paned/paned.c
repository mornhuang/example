	/* File: paned.c */

	#include <gtk/gtk.h>
   
	GtkWidget *create_list( void )
	{

		GtkWidget *scrolled_window;
		GtkWidget *list;
		GtkWidget *list_item;
		int i;
		char buffer[80];
   
		//建立滚动窗口
		scrolled_window = gtk_scrolled_window_new (NULL, NULL);
		gtk_scrolled_window_set_policy (
			GTK_SCROLLED_WINDOW (scrolled_window),
			GTK_POLICY_AUTOMATIC, GTK_POLICY_AUTOMATIC);
 
		//建立列表
		list = gtk_list_new ();
		gtk_scrolled_window_add_with_viewport (
			GTK_SCROLLED_WINDOW (scrolled_window), list);
		gtk_widget_show (list);
 		//在列表中加入项目 
		for (i=0; i<10; i++) {
			sprintf(buffer,"这是第 %d 条信息",i);
    			list_item = gtk_list_item_new_with_label (buffer);
    			gtk_container_add (GTK_CONTAINER(list), list_item);
    			gtk_widget_show (list_item);

    		}
   
    		return scrolled_window;
	}
   
	//建立文本
	void realize_text( GtkWidget *text, gpointer data )
	{
		gtk_text_freeze (GTK_TEXT (text));
    
		gtk_text_insert (GTK_TEXT (text), NULL, 
			&text->style->black, NULL,
    			"From: xxx@china.com\n"
    			"To: yyy@china.com\n"
    			"Subject: 测试\n"
    			"\n"
    			"本邮件只是测试邮件, 请忽略.\n"
    			" -Ciao\n", -1);
   
		gtk_text_thaw (GTK_TEXT (text));
	}
   
	GtkWidget *create_text( void )
	{
    		GtkWidget *table;
    		GtkWidget *text;
    		GtkWidget *hscrollbar;
    		GtkWidget *vscrollbar;
   
		//建立表格组件
    		table = gtk_table_new (2, 2, FALSE);
   		//建立文本区
    		text = gtk_text_new (NULL, NULL);
    		gtk_table_attach (GTK_TABLE (table), text, 0, 1, 0, 1,
		      GTK_FILL | GTK_EXPAND,
		      GTK_FILL | GTK_EXPAND | GTK_SHRINK, 0, 0);
    		gtk_widget_show (text);
   
		//建立滚动条
    		hscrollbar = gtk_hscrollbar_new (GTK_TEXT (text)->hadj);
    		gtk_table_attach (GTK_TABLE (table), hscrollbar, 0, 1, 1, 2,
		      GTK_EXPAND | GTK_FILL, GTK_FILL, 0, 0);
    		gtk_widget_show (hscrollbar);
   
    		vscrollbar = gtk_vscrollbar_new (GTK_TEXT (text)->vadj);
    		gtk_table_attach (GTK_TABLE (table), vscrollbar, 1, 2, 0, 1,
		      GTK_FILL, GTK_EXPAND | GTK_FILL | GTK_SHRINK, 0, 0);
    		gtk_widget_show (vscrollbar);
   
    		gtk_signal_connect (GTK_OBJECT (text), "realize",
			GTK_SIGNAL_FUNC (realize_text), NULL);
   
    		return table;
	}
   
	int main( int   argc, char *argv[] )
	{
    		GtkWidget *window;
    		GtkWidget *vpaned;
    		GtkWidget *list;
    		GtkWidget *text;

		//初始化
		gtk_set_locale();
    		gtk_init (&argc, &argv);
   
    		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
    		gtk_window_set_title (GTK_WINDOW (window), "Paned Windows");
    		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC (gtk_main_quit), NULL);
    		gtk_container_set_border_width (GTK_CONTAINER (window), 10);
    		gtk_widget_set_usize (GTK_WIDGET(window), 450, 400);

  		//建立垂直方向的方格窗口 
    		vpaned = gtk_vpaned_new ();
    		gtk_container_add (GTK_CONTAINER(window), vpaned);
    		gtk_paned_set_handle_size (GTK_PANED(vpaned), 10);
    		gtk_paned_set_gutter_size (GTK_PANED(vpaned), 15);
		//gtk_paned_set_position(GTK_PANED(vpaned), 10);
    		gtk_widget_show (vpaned);
   
		//建立列表
    		list = create_list ();
		//在上面的方格中加入列表
    		gtk_paned_add1 (GTK_PANED(vpaned), list);
    		gtk_widget_show (list);
   		//建立文本区
    		text = create_text ();
    		//在下面的方格中加入文本区
		gtk_paned_add2 (GTK_PANED(vpaned), text);
    		gtk_widget_show (text);
    		gtk_widget_show (window);
    		gtk_main ();
    		return 0;
	}
