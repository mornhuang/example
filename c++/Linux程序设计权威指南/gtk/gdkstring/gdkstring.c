
	/* File gdkstring.c */
	
	#include <gtk/gtk.h>
	#include <stdio.h>

	typedef struct _Data Data;

	struct {
		GdkColor color;
		gchar *name;
	} text_colors[] = {
		{{0, 0x0000, 0x0000, 0x0000}, "黑"},
		{{0, 0xffff, 0xffff, 0xffff}, "白"},
		{{0, 0xffff, 0x0000, 0x0000}, "红"},
		{{0, 0x0000, 0xffff, 0x0000}, "绿"},
		{{0, 0x0000, 0x0000, 0xffff}, "蓝"},
	};

	#define NTEXT_COLORS  (sizeof (text_colors)/sizeof(text_colors[0]))

	struct _Data {
		GdkGC   *gc[NTEXT_COLORS];
		GdkFont *font1;
		GdkFont *font2;
		GdkFont *font3;
	};
  
	gint draw_callback(GtkWidget *widget, GdkEvent *event, Data *data)
	{
		int i;
		for (i = 0; i < NTEXT_COLORS; i++){
			char text[20];
			int len;
	  		sprintf (text, "颜色(Color)是%s色", text_colors[i].name);
	  		len = strlen (text);

			gdk_draw_text (widget->window, data->font1,
				data->gc[i], 12, 25*(i+1), text, len);
			gdk_draw_text (widget->window, data->font2,
				data->gc[i], 140, 25*(i+1), text, len);
			gdk_draw_text (widget->window, data->font3,
				data->gc[i], 300, 25*(i+1), "中文字符串", 10);
		}

		gdk_draw_rectangle(widget->window, data->gc[0], FALSE,
			5, 5, 520, 130);

		return TRUE;
	}

	int main (int argc, char *argv[])
	{
		gint i;
		GtkWidget *window;
		GtkWidget *darea;
		Data data;
		GdkColormap *cmap;

		//初始化
		gtk_set_locale ();
		gtk_init (&argc, &argv);

		//建立根窗口
		window = gtk_window_new (GTK_WINDOW_TOPLEVEL);
		gtk_signal_connect (GTK_OBJECT (window), "destroy",
			GTK_SIGNAL_FUNC(gtk_main_quit), NULL);
		gtk_window_set_title (GTK_WINDOW (window), "GDKString");
		gtk_container_set_border_width (GTK_CONTAINER (window), 0);
		gtk_widget_realize (window);


		//建立绘图窗口
		darea = gtk_drawing_area_new ();
		gtk_widget_set_usize (darea, 550, 150);
		//曝光时调用函数重绘
		gtk_widget_set_events (darea, GDK_EXPOSURE_MASK);
		gtk_signal_connect (GTK_OBJECT (darea),
			"expose_event", GTK_SIGNAL_FUNC (draw_callback),
			&data);
		gtk_container_add (GTK_CONTAINER (window), darea);

		//取得颜色表
		cmap = gdk_colormap_get_system ();
		for (i = 0; i < NTEXT_COLORS; i++){
			//获得GC
			data.gc[i]  = gdk_gc_new (window->window);
			//分配颜色
			if (!gdk_color_alloc(cmap, &text_colors[i].color))
				g_error("couldn't allocate colour");
			//设置GC的前景色
			gdk_gc_set_foreground (data.gc[i], 
				&text_colors[i].color);
		}

		//设置字体集
		data.font1 = gdk_fontset_load (
			"7x14,-*-song-medium-r-normal--14-*-*-*-*-*-gbk-0");
		data.font2 = gdk_fontset_load (
			"8x16,-*-song-medium-r-normal--16-*-*-*-*-*-gbk-0");
		data.font3 = gdk_fontset_load (
			"12x24,-*-song-medium-r-normal--24-*-*-*-*-*-gbk-0");

		gtk_widget_show_all (window);
		gtk_main ();
		return 0;
	}
