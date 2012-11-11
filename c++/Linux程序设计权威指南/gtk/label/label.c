
	/* File: label.c */

	#include <gtk/gtk.h>

	int main( int   argc, char *argv[] )
	{
		static GtkWidget *window = NULL;
		GtkWidget *vbox;
		GtkWidget *hbox;
		GtkWidget *frame;
		GtkWidget *label;

		//初始化
		gtk_set_locale();
		gtk_init(&argc, &argv);

		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
                       GTK_SIGNAL_FUNC(gtk_main_quit), NULL);
		gtk_window_set_title (GTK_WINDOW (window), "Label");

		//建立垂直方向的容器
		vbox = gtk_vbox_new (FALSE, 5);
		gtk_container_add (GTK_CONTAINER (window), vbox);
		//设置容器的边界空间
		gtk_container_set_border_width (GTK_CONTAINER (window), 5);
 
		//建立一个 Frame 容器
		frame = gtk_frame_new("正常标签");
		label = gtk_label_new ("这是一个正常标签");
		//在容器中加入标签
		gtk_container_add (GTK_CONTAINER (frame), label);
		gtk_box_pack_start (GTK_BOX (vbox), frame, FALSE, FALSE, 0);
   
		//建立多行标签
		frame = gtk_frame_new("多行标签");
		label = gtk_label_new("这是一个多行标签.\n第二行\n" \
                          "第三行");
		gtk_container_add (GTK_CONTAINER (frame), label);
		gtk_box_pack_start (GTK_BOX (vbox), frame, FALSE, FALSE, 0);
   
		//左对齐标签
		frame = gtk_frame_new ("左对齐标签");
		label = gtk_label_new ("此标签是左对齐的\n" \
                          "多行标签.\n第三行");
		gtk_label_set_justify (GTK_LABEL (label), GTK_JUSTIFY_LEFT);
		gtk_container_add (GTK_CONTAINER (frame), label);
		gtk_box_pack_start (GTK_BOX (vbox), frame, FALSE, FALSE, 0);
   
		//右对齐标签
		frame = gtk_frame_new ("右对齐标签");
		label = gtk_label_new ("此标签是右对齐的\n多行标签.\n" \
                          "第三行");
		gtk_label_set_justify (GTK_LABEL (label), GTK_JUSTIFY_RIGHT);
		gtk_container_add (GTK_CONTAINER (frame), label);
		gtk_box_pack_start (GTK_BOX (vbox), frame, FALSE, FALSE, 0);

		//建立自动折行标签
		frame = gtk_frame_new ("自动折行标签");
		label = gtk_label_new (
			"This is an example of a line-wrapped label.  It " \
                        "should not be taking up the entire             " /* big space to test spacing */\
                        "width allocated to it, but automatically " \
                        "wraps the words to fit.  " \
                        "The time has come, for all good men, to come to " \
                        "the aid of their party.  " \
                        "The sixth sheik's six sheep's sick.\n" \
                        "     It supports multiple paragraphs correctly, " \
                        "and  correctly   adds "\
                        "many          extra  spaces. ");
		//设置为自动折行
		gtk_label_set_line_wrap (GTK_LABEL (label), TRUE);
		gtk_container_add (GTK_CONTAINER (frame), label);
		gtk_box_pack_start (GTK_BOX (vbox), frame, FALSE, FALSE, 0);
   
		//显示所有的窗口
		gtk_widget_show_all (window);

		gtk_main ();
  
		return(0);
	}
