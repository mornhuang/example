
	/* File: text.c */

	#include <stdio.h>
	#include <gtk/gtk.h>

	//切换可编辑属性
	void text_toggle_editable (GtkWidget *checkbutton, GtkWidget *text)
	{
		gtk_text_set_editable(GTK_TEXT(text),
			GTK_TOGGLE_BUTTON(checkbutton)->active);
	}

	//切换换行属性
	void text_toggle_word_wrap (GtkWidget *checkbutton, GtkWidget *text)
	{
		gtk_text_set_word_wrap(GTK_TEXT(text),
			GTK_TOGGLE_BUTTON(checkbutton)->active);
	}

	void close_application( GtkWidget *widget, gpointer   data )
	{
		gtk_main_quit();
	}

	int main( int argc, char *argv[] )
	{
		GtkWidget *window;
		GtkWidget *box1;
		GtkWidget *box2;
		GtkWidget *hbox;
		GtkWidget *button;
		GtkWidget *check;
		GtkWidget *separator;
		GtkWidget *table;
		GtkWidget *vscrollbar;
		GtkWidget *text;
		GdkColormap *cmap;
		GdkColor color;
		GdkFont *fixed_font;

		FILE *infile;

		//初始化
		gtk_set_locale();
		gtk_init (&argc, &argv);
 
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_widget_set_usize (window, 600, 500);
		gtk_window_set_policy (GTK_WINDOW(window), TRUE, TRUE, FALSE);  
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC(close_application), NULL);
		gtk_window_set_title (GTK_WINDOW (window), "Text Widget");
		gtk_container_set_border_width (GTK_CONTAINER (window), 0);
 
		box1 = gtk_vbox_new (FALSE, 0);
		gtk_container_add (GTK_CONTAINER (window), box1);
		gtk_widget_show (box1);
  
		box2 = gtk_vbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);
		gtk_box_pack_start (GTK_BOX (box1), box2, TRUE, TRUE, 0);
		gtk_widget_show (box2);
  
 		//建立表格容器 
		table = gtk_table_new (2, 2, FALSE);
		gtk_table_set_row_spacing (GTK_TABLE (table), 0, 2);
		gtk_table_set_col_spacing (GTK_TABLE (table), 0, 2);
		gtk_box_pack_start (GTK_BOX (box2), table, TRUE, TRUE, 0);
		gtk_widget_show (table);
  
		//建立文本区
		text = gtk_text_new (NULL, NULL);
		gtk_text_set_editable (GTK_TEXT (text), TRUE);
		gtk_table_attach (GTK_TABLE (table), text, 0, 1, 0, 1,
		    GTK_EXPAND | GTK_SHRINK | GTK_FILL,
		    GTK_EXPAND | GTK_SHRINK | GTK_FILL, 0, 0);
		gtk_widget_show (text);

		//对文本区附加滚动条
		vscrollbar = gtk_vscrollbar_new (GTK_TEXT (text)->vadj);
		gtk_table_attach (GTK_TABLE (table), vscrollbar, 1, 2, 0, 1,
		    GTK_FILL, GTK_EXPAND | GTK_SHRINK | GTK_FILL, 0, 0);
		gtk_widget_show (vscrollbar);

		//分配颜色
		cmap = gdk_colormap_get_system();
		color.red = 0xffff;
		color.green = 0;
		color.blue = 0;
		if (!gdk_color_alloc(cmap, &color)) {
			g_error("couldn't allocate color");
		}

		//载入字体集
		fixed_font = gdk_fontset_load (
		"12x24,-*-song-medium-r-normal--24-*-*-*-*-*-gb2312.1980-0");

		gtk_widget_realize (text);
		//冻结文本区
		gtk_text_freeze (GTK_TEXT (text));
  
		gtk_text_insert (GTK_TEXT (text), NULL, 
			&text->style->black, NULL, "支持", -1);
		gtk_text_insert (GTK_TEXT (text), NULL, 
			&color, NULL, "彩色的", -1);
		gtk_text_insert (GTK_TEXT (text), NULL, 
			&text->style->black, NULL, "文本和", -1);
		gtk_text_insert (GTK_TEXT (text), fixed_font, 
			&text->style->black, NULL, "字体(Font).\n\n", -1);
  
		//载入文件
		infile = fopen("text.c", "r");
  
		if (infile) {
			char buffer[1024];
			int nchars;
    
			while (1) {
				nchars = fread(buffer, 1, 1024, infile);
				gtk_text_insert (GTK_TEXT (text), NULL, 
					NULL, NULL, buffer, nchars);
	
				if (nchars < 1024) break;
			}
    
			fclose (infile);
		}
		//文本区解冻
		gtk_text_thaw (GTK_TEXT (text));
  
		hbox = gtk_hbutton_box_new ();
		gtk_box_pack_start (GTK_BOX (box2), hbox, FALSE, FALSE, 0);
		gtk_widget_show (hbox);

		check = gtk_check_button_new_with_label("可编辑");
		gtk_box_pack_start (GTK_BOX (hbox), check, FALSE, FALSE, 0);
		gtk_signal_connect (GTK_OBJECT(check), "toggled",
		      GTK_SIGNAL_FUNC(text_toggle_editable), text);
		gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(check), TRUE);
		gtk_widget_show (check);
		check = gtk_check_button_new_with_label("换行");
		gtk_box_pack_start (GTK_BOX (hbox), check, FALSE, TRUE, 0);
		gtk_signal_connect (GTK_OBJECT(check), "toggled",
		      GTK_SIGNAL_FUNC(text_toggle_word_wrap), text);
		gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(check), FALSE);
		gtk_widget_show (check);

		separator = gtk_hseparator_new ();
		gtk_box_pack_start (GTK_BOX (box1), separator, FALSE, TRUE, 0);
		gtk_widget_show (separator);

		box2 = gtk_vbox_new (FALSE, 10);
		gtk_container_set_border_width (GTK_CONTAINER (box2), 10);
		gtk_box_pack_start (GTK_BOX (box1), box2, FALSE, TRUE, 0);
		gtk_widget_show (box2);
  
		button = gtk_button_new_with_label ("关闭");
		gtk_signal_connect (GTK_OBJECT (button), "clicked",
		      GTK_SIGNAL_FUNC(close_application),
		      NULL);
		gtk_box_pack_start (GTK_BOX (box2), button, TRUE, TRUE, 0);
		GTK_WIDGET_SET_FLAGS (button, GTK_CAN_DEFAULT);
		gtk_widget_grab_default (button);
		gtk_widget_show (button);
		
		gtk_widget_show (window);

		gtk_main ();
  
		return(0);       
	}
