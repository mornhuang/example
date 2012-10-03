
	#include <vga.h>

	int main(int argc, char *argv[])
	{
		int i;
		int oldmode;
		int mode = G320x200x256;
		vga_modeinfo *info;
		int *pal;

		//获得当前的模式
		oldmode = vga_getcurrentmode();

		//初始化
		vga_init();

		//判断是否支持该模式
		if(vga_hasmode(mode)) 
			vga_setmode(mode);
		else {
			printf("No such mode\n");
			exit(1);
		}

		//获得当前的模式
		mode = vga_getcurrentmode();
		info = vga_getmodeinfo(mode);

		printf("Current mode:%s\n", vga_getmodename(mode));
		printf("Info - width           :%d\n", info->width);  
		printf("Info - height          :%d\n", info->height);  
		printf("Info - colors          :%d\n", info->colors);  
		printf("Info - bytesperpixel   :%d\n", info->bytesperpixel);  
		printf("Info - blit            :%s\n", 
			(info->haveblit == 1 ? "Yes":"No"));  
		printf("Info - Chip type       :%d\n", info->chiptype);  
		printf("Info - video memory(KB):%d\n", info->memory);  

		//打印颜色表
		pal = (int *)malloc(info->colors * 3);
		vga_getpalvec(0, info->colors, pal);

		for(i=0; i<info->colors; i++){
			printf("Color %3d : 0x%02x 0x%02x 0x%02x\n",
				i, pal[i*3], pal[i*3+1], pal[i*3+2]);
		}

		return 0;
	}
