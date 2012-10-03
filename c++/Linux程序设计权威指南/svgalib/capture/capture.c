
#include <stdio.h>
#include <vga.h>

int capture()
{
#ifndef CAPTURE
	int i;
	int mode;
	vga_modeinfo *info;
	int *pal;
	FILE *fp;
	int x, y;
	int c;
	unsigned char ch;
	fp = fopen("screendump.xpm", "w");
	if(!fp) return 0;


	//获得当前的模式
	mode = vga_getcurrentmode();
	info = vga_getmodeinfo(mode);

	pal = (int *)malloc(info->colors * 3);
	vga_getpalvec(0, info->colors, pal);

	fprintf(fp, "/* XPM */\n");
	fprintf(fp, "static char *xxxx[] = {\n");
	fprintf(fp, "/* width height num_colors chars_per_pixel */\n");
	fprintf(fp, "\"    %d    %d      %d            2\",\n",
		info->width, info->height, info->colors);
	fprintf(fp, "/* colors */\n");

	//put colormap
	for(i=0; i<info->colors; i++){
		fprintf(fp, "\"%02x	c #%02x%02x%02x\",\n",
			i, pal[i*3], pal[i*3+1], pal[i*3+2]);
	}
	for(y = 0; y < info->height; y ++){
		fprintf(fp, "\"");
		for(x = 0; x < info->width; x ++){
			ch = vga_getpixel(x, y);
			fprintf(fp, "%02x", ch);
		}
		fprintf(fp, "\",\n");
	}
	fprintf(fp, "};\n");

	fclose(fp);

	return 0;
#endif
}
