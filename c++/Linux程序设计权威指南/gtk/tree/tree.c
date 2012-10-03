
	/* File: tree.c */

	#include <gtk/gtk.h>

	static void cb_itemsignal( GtkWidget *item, gchar *signame )
	{
		gchar *name;
		GtkLabel *label;

		label = GTK_LABEL (GTK_BIN (item)->child);
		gtk_label_get (label, &name);
		g_print ("%s called for item %s, level %d\n", 
			signame, name, GTK_TREE (item->parent)->level);
	}


	static void cb_unselect_child( GtkWidget *root_tree,
		GtkWidget *child, GtkWidget *subtree )
	{
		g_print ("unselect_child called\n");
	}

	static void cb_select_child (GtkWidget *root_tree,
		GtkWidget *child, GtkWidget *subtree)
	{
		g_print ("select_child called\n");
	}

	static void cb_selection_changed( GtkWidget *tree )
	{
		GList *i;

		i = GTK_TREE_SELECTION(tree);
		while (i){
			gchar *name;
			GtkLabel *label;
			GtkWidget *item;

			item = GTK_WIDGET (i->data);
			label = GTK_LABEL (GTK_BIN (item)->child);
			gtk_label_get (label, &name);
			g_print ("selected: %s on level %d\n", name, 
				GTK_TREE(item->parent)->level);
			i = i->next;
		}
	}


	int main( int argc, gchar *argv[] )
	{
		int i;
		GtkWidget *window;
		GtkWidget *vbox, *hbox;
		GtkWidget *root_tree, *tree;
		GtkWidget *root_item;
		GtkWidget *subtree, *item, *subitem;
		GtkWidget *scrolled_window;	//clist的滚动窗口

		static char *hardware[3] = {
			"键盘",
			"鼠标",
			"硬盘"};

		static char *software[2] = {
			"GIMP",
			"MTV"};

		//初始化
		gtk_set_locale();
		gtk_init(&argc, &argv);
     
		//顶级窗口
		window=gtk_window_new(GTK_WINDOW_TOPLEVEL);
		//设置窗口的尺寸
		gtk_widget_set_usize(GTK_WIDGET(window), 400, 250);
		//窗口标题
		gtk_window_set_title(GTK_WINDOW(window), "GtkTree 演示");
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

		//建立树根
		root_tree = gtk_tree_new();
		gtk_signal_connect (GTK_OBJECT(root_tree), "select_child",
			GTK_SIGNAL_FUNC(cb_select_child), root_tree);
		gtk_signal_connect (GTK_OBJECT(root_tree), "unselect_child",
			GTK_SIGNAL_FUNC(cb_unselect_child), root_tree);
		gtk_signal_connect (GTK_OBJECT(root_tree), "selection_changed",
			GTK_SIGNAL_FUNC(cb_selection_changed), root_tree);
		//把Tree加到滚动窗口中
		gtk_scrolled_window_add_with_viewport(
			GTK_SCROLLED_WINDOW(scrolled_window), root_tree);
		gtk_widget_show(root_tree);
		//树的label
		root_item = gtk_tree_item_new_with_label("计算机");
		gtk_signal_connect (GTK_OBJECT(root_item), "select",
			GTK_SIGNAL_FUNC(cb_itemsignal), "select");
		gtk_signal_connect (GTK_OBJECT(root_item), "deselect",
			GTK_SIGNAL_FUNC(cb_itemsignal), "deselect");
		gtk_signal_connect (GTK_OBJECT(root_item), "toggle",
			GTK_SIGNAL_FUNC(cb_itemsignal), "toggle");
		gtk_signal_connect (GTK_OBJECT(root_item), "expand",
			GTK_SIGNAL_FUNC(cb_itemsignal), "expand");
		gtk_signal_connect (GTK_OBJECT(root_item), "collapse",
			GTK_SIGNAL_FUNC(cb_itemsignal), "collapse");
		gtk_tree_append(GTK_TREE(root_tree), root_item);
		gtk_widget_show(root_item);

		//子树
		tree = gtk_tree_new();
		gtk_signal_connect (GTK_OBJECT(tree), "select_child",
			GTK_SIGNAL_FUNC(cb_select_child), tree);
		gtk_signal_connect (GTK_OBJECT(tree), "unselect_child",
			GTK_SIGNAL_FUNC(cb_unselect_child), tree);
		gtk_signal_connect (GTK_OBJECT(tree), "selection_changed",
			GTK_SIGNAL_FUNC(cb_selection_changed), tree);
		gtk_widget_show (tree);
		gtk_tree_item_set_subtree(GTK_TREE_ITEM(root_item), tree);

		//加入两个树枝
		item = gtk_tree_item_new_with_label("硬件");
		gtk_signal_connect (GTK_OBJECT(item), "select",
			GTK_SIGNAL_FUNC(cb_itemsignal), "select");
		gtk_signal_connect (GTK_OBJECT(item), "deselect",
			GTK_SIGNAL_FUNC(cb_itemsignal), "deselect");
		gtk_signal_connect (GTK_OBJECT(item), "toggle",
			GTK_SIGNAL_FUNC(cb_itemsignal), "toggle");
		gtk_signal_connect (GTK_OBJECT(item), "expand",
			GTK_SIGNAL_FUNC(cb_itemsignal), "expand");
		gtk_signal_connect (GTK_OBJECT(item), "collapse",
			GTK_SIGNAL_FUNC(cb_itemsignal), "collapse");
		//加入到parent中
		gtk_tree_append(GTK_TREE(tree), item);
		gtk_widget_show (item);
		//树枝
		subtree = gtk_tree_new();
		gtk_signal_connect (GTK_OBJECT(subtree), "select_child",
			GTK_SIGNAL_FUNC(cb_select_child), subtree);
		gtk_signal_connect (GTK_OBJECT(subtree), "unselect_child",
			GTK_SIGNAL_FUNC(cb_unselect_child), subtree);
		gtk_tree_set_selection_mode (GTK_TREE(subtree),
			GTK_SELECTION_SINGLE);
		gtk_tree_set_view_mode (GTK_TREE(subtree), GTK_TREE_VIEW_ITEM);
		gtk_tree_item_set_subtree (GTK_TREE_ITEM(item), subtree);
		//树叶
		for(i = 0; i < 3; i++){
			subitem = gtk_tree_item_new_with_label(hardware[i]);
			gtk_signal_connect (GTK_OBJECT(subitem), "select",
				GTK_SIGNAL_FUNC(cb_itemsignal), "select");
			gtk_signal_connect (GTK_OBJECT(subitem), "deselect",
				GTK_SIGNAL_FUNC(cb_itemsignal), "deselect");
			gtk_signal_connect (GTK_OBJECT(subitem), "toggle",
				GTK_SIGNAL_FUNC(cb_itemsignal), "toggle");
			gtk_signal_connect (GTK_OBJECT(subitem), "expand",
				GTK_SIGNAL_FUNC(cb_itemsignal), "expand");
			gtk_signal_connect (GTK_OBJECT(subitem), "collapse",
				GTK_SIGNAL_FUNC(cb_itemsignal), "collapse");
			gtk_tree_append (GTK_TREE(subtree), subitem);
			gtk_widget_show (subitem);
		}

		//另外一个树枝
		item = gtk_tree_item_new_with_label("软件");
		gtk_signal_connect (GTK_OBJECT(item), "select",
			GTK_SIGNAL_FUNC(cb_itemsignal), "select");
		gtk_signal_connect (GTK_OBJECT(item), "deselect",
			GTK_SIGNAL_FUNC(cb_itemsignal), "deselect");
		gtk_signal_connect (GTK_OBJECT(item), "toggle",
			GTK_SIGNAL_FUNC(cb_itemsignal), "toggle");
		gtk_signal_connect (GTK_OBJECT(item), "expand",
			GTK_SIGNAL_FUNC(cb_itemsignal), "expand");
		gtk_signal_connect (GTK_OBJECT(item), "collapse",
			GTK_SIGNAL_FUNC(cb_itemsignal), "collapse");
		gtk_tree_append(GTK_TREE(tree), item);
		gtk_widget_show (item);
		subtree = gtk_tree_new();
		gtk_signal_connect (GTK_OBJECT(subtree), "select_child",
			GTK_SIGNAL_FUNC(cb_select_child), subtree);
		gtk_signal_connect (GTK_OBJECT(subtree), "unselect_child",
			GTK_SIGNAL_FUNC(cb_unselect_child), subtree);
		gtk_tree_set_selection_mode (GTK_TREE(subtree),
			GTK_SELECTION_SINGLE);
		gtk_tree_set_view_mode (GTK_TREE(subtree), GTK_TREE_VIEW_ITEM);
		gtk_tree_item_set_subtree (GTK_TREE_ITEM(item), subtree);
		//树叶
		for(i = 0; i < 2; i++){
			subitem = gtk_tree_item_new_with_label(software[i]);
			gtk_signal_connect (GTK_OBJECT(subitem), "select",
				GTK_SIGNAL_FUNC(cb_itemsignal), "select");
			gtk_signal_connect (GTK_OBJECT(subitem), "deselect",
				GTK_SIGNAL_FUNC(cb_itemsignal), "deselect");
			gtk_signal_connect (GTK_OBJECT(subitem), "toggle",
				GTK_SIGNAL_FUNC(cb_itemsignal), "toggle");
			gtk_signal_connect (GTK_OBJECT(subitem), "expand",
				GTK_SIGNAL_FUNC(cb_itemsignal), "expand");
			gtk_signal_connect (GTK_OBJECT(subitem), "collapse",
				GTK_SIGNAL_FUNC(cb_itemsignal), "collapse");
			gtk_tree_append (GTK_TREE(subtree), subitem);
			gtk_widget_show (subitem);
		}


		gtk_widget_show(window);
		gtk_main(); 
		return(0);
	}
