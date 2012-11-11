
	#include <vga.h>

	int main(int argc, char *argv[])
	{
		
		int oldmode;
		int mode = G320x200x256;

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

		mode = vga_getcurrentmode();

		//恢复原来的模式 
		vga_setmode(oldmode);

		//打印当前模式名
		printf("Current mode:%s\n", vga_getmodename(mode));

		return 0;
	}
