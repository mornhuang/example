
	/* File: imlib.c */
	#include <gdk_imlib.h>
	#include <gdk/gdk.h>
	#include <gdk/gdkx.h>


	int main(int argc, char **argv)
	{
		int i, j;
		int sw, sh;
		GdkWindowAttr         attr;
		GdkWindow             *win;
		GdkPixmap             *p = NULL,*m;
		GdkImlibImage         *im;
		int display_w, display_h;
		gint w,h;
		unsigned char modify[256];

		if (argc<=1) {
			printf("Usage:\n %s image_file\n",argv[0]);
			exit(1);
		}

		//初始化
		gtk_set_locale();
		gtk_init(&argc,&argv);
		gdk_imlib_init();

		//使用Visual和Colormap
		gtk_widget_push_visual(gdk_imlib_get_visual());
		gtk_widget_push_colormap(gdk_imlib_get_colormap());
    
		//载入图片
		im = gdk_imlib_load_image(argv[1]);
		//获得图片的尺寸
		w = im->rgb_width;
		h = im->rgb_height;

		//建立无边界窗口
		attr.override_redirect = TRUE;
		attr.window_type=GDK_WINDOW_TEMP;
		attr.wclass=GDK_INPUT_OUTPUT;
		attr.event_mask=GDK_STRUCTURE_MASK;
		attr.width=w;
		attr.height=h;
		win = gdk_window_new(NULL,&attr,0);
		gdk_window_show(win);


		display_w = DisplayWidth(gdk_display, gdk_screen);
		display_h = DisplayHeight(gdk_display, gdk_screen);

		//enlarge
		for(i=1; i<=20; i++){
			sw = (int) ((float)w * i / 20);
			sh = (int) ((float)h * i / 20);
			gdk_imlib_render(im, sw, sh);
			if(p) gdk_imlib_free_pixmap(p);
			p=gdk_imlib_move_image(im);
			gdk_window_set_back_pixmap(win,p,0);
			gdk_window_move_resize(win,
				(display_w - sw)/2, (display_h - sh)/2, sw, sh);
			gdk_flush();
			usleep(50000);
		}
		//fade out
	        for ( i=30-1;i>=1;i--){
                	for (j=0;j<256;j++)
                        	modify[j] = (unsigned char) ( j*i/30 );
                	gdk_imlib_set_image_red_curve(im, modify);
                	gdk_imlib_set_image_green_curve(im, modify);
                	gdk_imlib_set_image_blue_curve(im, modify);
                	gdk_imlib_changed_image(im);
                	//gdk_imlib_render(im, pix_width, pix_height);
                	gdk_imlib_paste_image(im, win, 0, 0, sw, sh);
                	gdk_flush();
                	usleep(50000);
        	}

		gdk_window_destroy(win);
        	if(p) gdk_imlib_free_pixmap(p);

		return 0;
	}
