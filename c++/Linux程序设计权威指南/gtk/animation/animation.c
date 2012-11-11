
	/* File: animation.c */
	//Note: this program need gdk-pixbuf extension
	//code from mzdic

	#include <gtk/gtk.h>
	#include <gdk-pixbuf/gdk-pixbuf.h>

	static int draw_frame(GtkWidget *w);
	static void initialize_animation(GtkWidget *w);
	static int width, height;
	static GList *current_frame_list;
	static GdkPixbufAnimation *ani;
	static guint animation_id = 0;
	static char * animation_filename;
	static GdkPixmap *pixmap, *clean_pixmap;

	static void start_animation(GtkWidget *w)
	{
		if (ani == NULL) initialize_animation(w);
		draw_frame(w);
	}

	static void stop_animation(GtkWidget *w)
	{
		if (animation_id) {
			gtk_timeout_remove(animation_id);
			animation_id = 0;
		}
	}

	static void destroy_animation(GtkWidget *w)
	{
		if (animation_id) {
			gtk_timeout_remove(animation_id);
			animation_id = 0;
		}
		gdk_pixbuf_animation_unref(ani);
		gdk_pixmap_unref(pixmap); 
		gdk_pixmap_unref(clean_pixmap);
		ani = NULL;
		g_free(animation_filename);
	}

	static int draw_frame(GtkWidget *w)
	{
		int offset_x, offset_y;
		int margin_x, margin_y;
		GdkPixbufFrame *frame;
		GdkPixbuf *pix;

		if (!GTK_WIDGET_REALIZED(w)) return FALSE;

		frame = current_frame_list->data;
		pix = gdk_pixbuf_frame_get_pixbuf(frame);

		offset_x = gdk_pixbuf_frame_get_x_offset(frame);
		offset_y = gdk_pixbuf_frame_get_y_offset(frame);
		margin_x = (w->allocation.width - width)/2;
		margin_y = (w->allocation.height - height)/2;
		gdk_window_copy_area(pixmap, w->style->black_gc,
		    0, 0, clean_pixmap, 0, 0, width, height);

		gdk_pixbuf_render_to_drawable_alpha(pix, pixmap,
			0, 0, offset_x, offset_y,
			gdk_pixbuf_get_width(pix), gdk_pixbuf_get_height(pix),
			GDK_PIXBUF_ALPHA_BILEVEL, 128,
			GDK_RGB_DITHER_NONE, 0, 0);
		gdk_window_copy_area(w->window, w->style->black_gc, 
			margin_x, margin_y, pixmap, 0, 0, width, height);
		animation_id = gtk_timeout_add(
			10*gdk_pixbuf_frame_get_delay_time(frame),
			(GtkFunction)draw_frame, w);
		current_frame_list = current_frame_list->next;
		if (current_frame_list == NULL) 
			current_frame_list = 
				gdk_pixbuf_animation_get_frames(ani);
		return FALSE;
	}

	static void initialize_animation(GtkWidget *w)
	{
		if (ani) return;

		ani = gdk_pixbuf_animation_new_from_file(animation_filename);
		if (ani == NULL) return;

		width = gdk_pixbuf_animation_get_width(ani);
		height = gdk_pixbuf_animation_get_height(ani);
		current_frame_list = gdk_pixbuf_animation_get_frames(ani);
		pixmap = gdk_pixmap_new(w->window, width, height, -1);
		clean_pixmap = gdk_pixmap_new(w->window, width, height, -1);
		if (w->style->bg_pixmap[GTK_STATE_NORMAL]) {
			GdkPixmap *bg = w->style->bg_pixmap[GTK_STATE_NORMAL];
			GdkGC *gc;
			GdkGCValues values;
			values.tile = bg;
			values.fill = GDK_TILED;
			gc = gdk_gc_new_with_values(w->window, &values, 
				GDK_GC_FILL|GDK_GC_TILE);
			gdk_gc_set_ts_origin(gc, 
				(w->allocation.width - width)/2, 
				(w->allocation.height-height)/2);
			gdk_draw_rectangle(clean_pixmap,gc, TRUE, 
				0, 0, width, height);
			gdk_gc_destroy(gc);
		} else {
			gdk_draw_rectangle(clean_pixmap, 
				w->style->bg_gc[GTK_STATE_NORMAL], 
				TRUE, 0, 0, width, height);
		}
	}

	GtkWidget *animation_new(char *filename)
	{
		GtkWidget *w;
		animation_filename = g_strdup(filename);

		w = gtk_drawing_area_new();
		gtk_signal_connect(GTK_OBJECT(w), "realize",
			GTK_SIGNAL_FUNC(start_animation), NULL);
		gtk_signal_connect(GTK_OBJECT(w), "show",
			GTK_SIGNAL_FUNC(draw_frame), NULL);
		gtk_signal_connect(GTK_OBJECT(w), "hide",
			GTK_SIGNAL_FUNC(stop_animation), NULL);
		gtk_signal_connect(GTK_OBJECT(w), "destroy",
			GTK_SIGNAL_FUNC(destroy_animation), NULL);
		return w;
	}

	int main(int argc, char *argv[])
	{
		GtkWidget *window;
		GtkWidget *penguin;

		//初始化
		gtk_set_locale();
		gtk_init(&argc, &argv);
		//gdk_rgb_set_verbose(TRUE);
		gdk_rgb_init();
		gtk_widget_set_default_colormap(gdk_rgb_get_cmap());
		gtk_widget_set_default_visual(gdk_rgb_get_visual());

		//建立窗口
		window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
		gtk_window_set_default_size(GTK_WINDOW(window), 200, 200);
		gtk_window_set_title(GTK_WINDOW(window), "动画");
		gtk_signal_connect(GTK_OBJECT(window), "destroy",
			GTK_SIGNAL_FUNC(gtk_main_quit), NULL);

		if(argc > 1) penguin = animation_new(argv[1]);
		else penguin = animation_new("./penguin.gif");

		gtk_container_add(GTK_CONTAINER(window), penguin);

		gtk_widget_show_all(window);
		gtk_main();
		return 0;
	}

