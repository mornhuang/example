// ft2string.c
//      本程序利用 FreeType 2 函数库把一串文本用 TrueType 字库显示在
//      一个 X 窗口里。
//

// 标准头文件
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <locale.h>

// X11 头文件
#include <X11/Xlib.h>
#include <X11/Xutil.h>

// FreeType 2 头文件
#include <freetype/freetype.h>

// X 窗口的宽度和高度
#define WINDOW_WIDTH 600
#define WINDOW_HEIGHT 400

// 最多只显示 256 个字符
#define MAX_GLYPHS 256

Display *display;
Window win;			// 窗口ID
GC gc;				// 图形环境
XVisualInfo vis;		// Visual 信息
XColor rgb[256];

// 定义将用于 X 窗口显示的字形图象结构
typedef struct
{
  XImage *img;			// 字形的 X 图象
  int left;			// 字形图象的左边轴距
  int top;			// 字形图象的顶边轴距
  int advance_x;		// 字形的水平步幅
  int advance_y;		// 字形的垂直步幅
}
Glyph_Image;

Glyph_Image gi[MAX_GLYPHS];	// 最多 MAX_GLYPHS 个字形 X 图象

FT_Library library;		// FreeType 库
FT_Face face;			// 字体对象
FT_Error error;			// FreeType 错误代码

char *prog;			// 程序名
unsigned char *text = "欢迎进入 Linux 的奇妙世界！";
					// 将显示的文本，缺省文本如上
wchar_t unicode_text[MAX_GLYPHS];	// 文本的 Unicode 编码
int num_glyphs;			// 字形数目
int pixel_size = 50;		// 显示字体象素大小，缺省为 50
int resolution = 72;		// 分辨率，缺省为 72
int grayscale = 0;		// 是否使用灰度显示，缺省为不使用灰度显示
char *font_filename = NULL;	// 字库文件名，用户必须提供

void parse_args ();		// 处理命令行参数
void text_to_unicode ();	// 把文本转换为 Unicode 编码
void init_display ();		// 初始化 X 窗口
void render_glyph ();		// 生成字形图象的位图（单色图或灰度图）
void render_image ();		// 生成字形的 X 图象（XImage）
void draw_image ();		// 在 X 窗口里画出文本图象
void event_loop ();		// X 事件循环
void usage ();			// 使用说明
void fatal_error (const char *msg, ...);	// FreeType 函数返回致命错误


int
main (int argc, char **argv)
{
  int i;

  // 处理命令行参数，获取下列信息
  //    字库文件名
  //    显示字体象素大小
  //    分辨率
  //    是否使用灰度显示
  //    将显示的文本
  parse_args (argc, argv);

  // 初始化一个新的 FreeType 库对象 library
  error = FT_Init_FreeType (&library);
  if (error)
    fatal_error ("couldn't initialize FreeType library.");

  // 为字库文件 font_filename 中的第一种字体创建一个新的字体对象 face
  error = FT_New_Face (library, font_filename, 0, &face);
  if (error == FT_Err_Unknown_File_Format)
    fatal_error ("unsupported font format in file '%s'.", font_filename);
  else if (error)
    fatal_error ("unable to open input file '%s'.", font_filename);

  // 选择字体对象 face 中的 Unicode 字符码表为缺省码表
  error = FT_Select_Charmap (face, ft_encoding_unicode);
  if (error)
    fatal_error ("couldn't select Unicode charmap.");

  // 设置字体 face 的字符宽度和高度都为 pixel_size 个象素
  // 水平和垂直分辨率都为 resolution dpi
  error = FT_Set_Char_Size (face,
			    pixel_size * 64, pixel_size * 64,
			    resolution, resolution);
  if (error)
    fatal_error ("couldn't set character size: pixel size %d, resolution %d.",
		 pixel_size, resolution);

  // 把将显示的文本转换为 Unicode 编码
  text_to_unicode ();

  // 初始化 X 窗口
  init_display ();

  // 对以 Unicode 编码的文本中所有字符进行逐一处理
  for (i = 0; i < num_glyphs; i++)
    {
      // 生成第 i 个 Unicode 字符的字形图象的位图（单色图或灰度图）
      render_glyph (unicode_text[i], grayscale);

      // 生成第 i 个字形的 X 图象（XImage），并保存下列信息：
      //        字形位图的左边轴距
      //        字形位图的顶边轴距
      //        字形的水平步幅
      //        字形的垂直步幅
      render_image (i, face->glyph->bitmap.buffer,
		    face->glyph->bitmap.rows, face->glyph->bitmap.pitch,
		    face->glyph->bitmap_left, face->glyph->bitmap_top,
		    face->glyph->advance.x / 64, face->glyph->advance.y / 64,
		    grayscale);
    }

  // 在 X 窗口中，从位置 x = 0，y = pixel_size * resolution / 72
  // 开始画出文本的所有字符的图象
  draw_image (0, pixel_size * resolution / 72);

  // X 事件循环
  event_loop ();

  // 销毁字体对象 face 及它的所有子对象
  FT_Done_Face (face);

  // 销毁 FreeType 库对象 library 及它的所有后代
  FT_Done_FreeType (library);

  return 0;
}


// 处理命令行参数
void
parse_args (int argc, char **argv)
{
  int i;

  prog = argv[0];
  if (argc == 1)
    usage ();

  for (i = 1; i < argc; i++)
    {
      switch (argv[i][0])
	{
	case '-':
	  switch (argv[i][1])
	    {
	    case 'p':
	      if (i + 1 < argc && argv[i + 1][0] != '-')
		pixel_size = strtol (argv[++i], (char **) NULL, 10);
	      break;
	    case 'r':
	      if (i + 1 < argc && argv[i + 1][0] != '-')
		resolution = strtol (argv[++i], (char **) NULL, 10);
	      break;
	    case 'f':
	      if (i + 1 < argc && argv[i + 1][0] != '-')
		font_filename = argv[++i];
	      break;
	    case 'g':
	      grayscale = 1;
	      break;
	    default:
	      break;
	    }
	  break;
	default:
	  text = argv[i];
	  break;
	}
    }

  if (font_filename == NULL)
    {
      fprintf (stderr,
	       "ERROR: You must provide a TrueType font file name!\n");
      exit (1);
    }

  if (pixel_size == 0 || pixel_size == LONG_MAX)
    {
      fprintf (stderr, "ERROR: Illegal pixel size: %d.\n", pixel_size);
      exit (1);
    }

  if (resolution == 0 || resolution == LONG_MAX)
    {
      fprintf (stderr, "ERROR: Illegal resolution: %d.\n", resolution);
      exit (1);
    }

  // 打印程序运行参数
  printf ("font_filename: %s\n", font_filename);
  printf ("pixel_size: %d\n", pixel_size);
  printf ("resolution: %d\n", resolution);
  printf ("grayscale: %d\n", grayscale);
  printf ("text: %s\n", text);
}


// 利用 locale 和 mbstowcs() 函数把文本转换为 Unicode 编码
void
text_to_unicode ()
{
  char *old_locale, *saved_locale;
  int length;
  int i;

  // 获取当前 locale 的 LC_CTYPE 类的设定
  old_locale = setlocale (LC_CTYPE, NULL);

  // 保存当前 locale 的 LC_CTYPE 类的设定
  saved_locale = strdup (old_locale);

  // 设置 LC_CTYPE 为一种简体中文 locale
  setlocale (LC_CTYPE, "zh_CN");

  length = strlen (text);
  if (length > 256)
    length = 256;

  // 把输入文本转换为 Unicode 编码
  // 返回转换后的 Unicode 字符总数，即将要处理的字形总数，最多只取 256 个
  num_glyphs = mbstowcs (unicode_text, text, length);

  // 打印字形总数
  printf ("number of glyphs: %d\n", num_glyphs);

  // 打印所有字符的 Unicode 编码
  printf ("unicode text:");
  for (i = 0; i < num_glyphs; i++)
    {
      printf (" %x", (unsigned int) unicode_text[i]);
    }
  printf ("\n");

  // 恢复对 locale 类 LC_CTYPE 的设置
  setlocale (LC_CTYPE, saved_locale);

  free (saved_locale);
}


// 生成 Unicode 编码为 charcode 的字符的字形图象和位图（单色图或灰度图）
void
render_glyph (unsigned long charcode, int gray)
{
  int glyph_index;		// 字体中的字形索引号

  // 把 Unicode 编码 charcode 转换为字体 face 中对应的字形索引号
  glyph_index = FT_Get_Char_Index (face, charcode);

  if (glyph_index != 0)
    {
      int flags;

      // 定义使用缺省模式载入字形，即，内嵌位图优先于轮廓，
      // 矢量总是被缩放并符合格点
      flags = FT_LOAD_DEFAULT;

      // 在使用灰度显示时，不载入可缩放字体的内嵌点阵字体位图，
      // 而载入轮廓线字形图象
      if (gray)
	flags |= FT_LOAD_NO_BITMAP;

      // 从字体 face 中按 flags 设定的模式载入字形索引号为 glyph_index
      // 的字形图象
      error = FT_Load_Glyph (face, glyph_index, flags);
      if (error)
	fatal_error ("couldn't load glyph.");

      // 把字形图象 face->glyph 转换为灰度位图或单色位图
      if (gray)
	error = FT_Render_Glyph (face->glyph, ft_render_mode_normal);
      else
	error = FT_Render_Glyph (face->glyph, ft_render_mode_mono);
      if (error)
	fatal_error ("couldn't render glyph.");

    }
}

// 生成 Unicode 编码文本中第 num 个字符位图 bit_data 的 X 图象（XImage），
// 并保存下列信息：
//      字形位图的左边轴距 left
//      字形位图的顶边轴距 top
//      字形的水平步幅 advance_x
//      字形的垂直步幅 advance_y
void
render_image (int num, unsigned char *bit_data,
	      int rows, int cols,
	      int left, int top, int advance_x, int advance_y, int gray)
{
  int i, j;
  int img_width;		// 位图宽度
  int img_size;			// 位图尺寸
  unsigned char *bitmap;	// 用于 XImage 中的位图

  if (gray)
    img_width = cols;
  else
    img_width = ((cols + 1) & -2) * 8;

  // 给位图 bitmap 分配内存空间
  img_size = cols * rows * 2;
  bitmap = (unsigned char *) malloc (img_size);

  // 把位图 bitmap 初始化为全零
  memset (bitmap, 0, img_size);

  // 保存位图及字形相关数据
  gi[num].left = left;
  gi[num].top = top;
  gi[num].advance_x = advance_x;
  gi[num].advance_y = advance_y;

  // 创建 XImage
  gi[num].img = XCreateImage (display,
			      vis.visual,
			      gray ? vis.depth : 1,
			      gray ? ZPixmap : XYBitmap,
			      0, bitmap, img_width, rows, 8, 0);

  // 在单色位图数据中，最左侧的象素用最高标志位（Most Significant Bit）表示
  if (!gray)
    {
      gi[num].img->byte_order = MSBFirst;
      gi[num].img->bitmap_bit_order = MSBFirst;
    }

  // 根据字形位图给 XImage 赋值
  for (j = 0; j < rows; j++)
    for (i = 0; i < cols; i++)
      {
	unsigned char c = bit_data[i + j * cols];
	if (gray)
	  gi[num].img->f.put_pixel (gi[num].img, i, j, rgb[c].pixel);
	else
	  bitmap[i + j * img_width / 8] = c;
      }
}


// 在 X 窗口中，从位置 x = start_x，y = start_y 开始画出文本的所有字符的图象
void
draw_image (int start_x, int start_y)
{
  int i;
  for (i = 0; i < num_glyphs; i++)
    {
      // 如果将要到达窗口右边界，“另起一行”开始书写
      if (start_x + gi[i].img->width >= WINDOW_WIDTH)
	{
	  start_x = 0;
	  start_y += pixel_size * resolution / 72;
	}

      // 画出当前的字形图象，根据字形左边轴距和顶边轴距调整字形图象的位置
      XPutImage (display, win, gc, gi[i].img, 0, 0,
		 start_x + gi[i].left, start_y - gi[i].top,
		 gi[i].img->width, gi[i].img->height);

      // 根据当前字形的步幅调整画下一个字形图象的起始位置
      start_x += gi[i].advance_x;
      start_y += gi[i].advance_y;
    }
}


// 初始化 X 窗口
void
init_display ()
{
  int i;
  int screen_num;
  unsigned int win_width, win_height;	// 窗口尺寸
  unsigned int border_width = 4;	// 边界空白
  char *display_name = NULL;
  Colormap default_cmap;	// 颜色表
  XVisualInfo *vlist;		// Visual 信息
  int depth;			// 窗口颜色深度
  int match;			// 所符合的 Visual 结构

  // 与 X 服务器连接
  if ((display = XOpenDisplay (display_name)) == NULL)
    {
      printf ("Cannot connect to X server %s\n", XDisplayName (display_name));
      exit (-1);
    }

  // 获得缺省的 screen_num
  screen_num = DefaultScreen (display);

  // 建立图形环境
  gc = DefaultGC (display, screen_num);

  // 获得颜色深度
  depth = DefaultDepthOfScreen (DefaultScreenOfDisplay (display));

  // 获取缺省的颜色表
  default_cmap = DefaultColormap (display, screen_num);

  // 获得Visual信息
  vis.screen = screen_num;
  vlist = XGetVisualInfo (display, VisualScreenMask,	// 掩码
			  &vis,	// 返回visual
			  &match);	// 所符合的Visual结构
  if (!vlist)
    {
      printf ("No matched visuals\n");
      exit (1);
    }
  vis = vlist[0];
  XFree (vlist);

  // 设置灰度级颜色表
  for (i = 0; i < 256; i++)
    {
      rgb[i].red = rgb[i].green = rgb[i].blue = 65535 - (i * 65535) / 256;
      rgb[i].flags = DoRed | DoGreen | DoBlue;
      XAllocColor (display, default_cmap, &rgb[i]);
    }

  win_width = WINDOW_WIDTH;	// 窗口宽度
  win_height = WINDOW_HEIGHT;	// 窗口高度

  // 建立窗口
  win = XCreateSimpleWindow (display, RootWindow (display, screen_num),	//父窗口
			     0, 0, win_width, win_height,	//位置和大小
			     border_width,	//边界宽度
			     BlackPixel (display, screen_num),	//前景色
			     WhitePixel (display, screen_num));	//背景色

  // 选择窗口感兴趣的事件掩码
  XSelectInput (display, win, ExposureMask | KeyPressMask | ButtonPressMask);

  // 显示窗口
  XMapWindow (display, win);
}


// X 事件循环
void
event_loop ()
{
  int i;
  int is_exit = 0;		// 退出标志

  XEvent report;

  // 进入事件循环
  while (!is_exit)
    {
      // 取得队列中的事件
      XNextEvent (display, &report);

      switch (report.type)
	{

	  // 曝光事件, 窗口应重绘
	case Expose:
	  // 取得最后一个曝光事件
	  if (report.xexpose.count != 0)
	    break;

	  // 重画图像
	  draw_image (0, pixel_size * resolution / 72);
	  break;

	  // 鼠标点击或有按键, 释放资源后返回主程序
	case ButtonPress:
	case KeyPress:
	  for (i = 0; i < num_glyphs; i++)
	    XDestroyImage (gi[i].img);
	  XFreeGC (display, gc);
	  is_exit = 1;

	default:
	  break;
	}
    }
}


// FreeType 函数返回致命错误
void
fatal_error (const char *msg, ...)
{
  va_list ap;

  va_start (ap, msg);
  fprintf (stderr, "%s: error code 0x%04lx: ", prog, (long unsigned) error);
  vfprintf (stderr, msg, ap);
  fprintf (stderr, "\n");
  va_end (ap);

  exit (-1);
}


// 使用说明
void
usage ()
{
  fprintf (stderr, "\n");
  fprintf (stderr, "Usage:\n");
  fprintf (stderr, "\n");
  fprintf (stderr,
	   "%s -f font_filename [-p pixel_size] [-r resolution] [-g] [text_to_display]\n",
	   prog);
  fprintf (stderr, "\n");
  fprintf (stderr, "\t-g\tUse grayscale to render text.\n");
  fprintf (stderr, "\n");
  fprintf (stderr, "\tDefault pixel size is 50.\n");
  fprintf (stderr, "\tDefault resolution is 72.\n");
  fprintf (stderr, "\tDefault is not using grayscale.\n");
  fprintf (stderr, "\tDefault text string is \"%s\"\n", text);
  fprintf (stderr, "\n");
  exit (1);
}

// 这是 ft2string.c 的最后一行。This is the end of ft2string.c.
