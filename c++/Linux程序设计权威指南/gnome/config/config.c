
	/* File: config.c */



	/*
	Config File Contents:

	[Global]
	数量=20
	价格=8900
	品牌=Compaq
	硬件=显示器 鼠标 键盘\\ 
	*/

	#include <gnome.h>

	#define PACKAGE "TestGnome"
	#define VERSION "1.0"

	void save_settings()
	{
		const char *values[] = {"显示器", "鼠标", "键盘 "};

		gnome_config_push_prefix ("/"PACKAGE"/Global/");

		gnome_config_set_int ("数量", 20);
		gnome_config_set_int ("价格", 8900);
		gnome_config_set_string ("品牌", "Compaq");
		gnome_config_set_vector("硬件", 3, values);
	
		gnome_config_pop_prefix ();
		gnome_config_sync ();
	}

	void get_settings()
	{
		int i;
		int amount, price;
		char *brand;
		int len;
		char **vec;
	 
                printf("Get Config from file:%s\n",
			gnome_config_get_real_path(PACKAGE));


		gnome_config_push_prefix ("/"PACKAGE"/Global/");
	 
		amount = gnome_config_get_int ("数量");
		price = gnome_config_get_int ("价格");
		brand = gnome_config_get_string("品牌");
	
		printf("数量:%d 价格:%d 品牌:%s\n", amount, price, brand);

		gnome_config_get_vector("硬件", &len, &vec);
		for(i=0; i<len; i++) 
			printf("item:%s\n", vec[i]);

		gnome_config_pop_prefix ();
		gnome_config_sync ();
	
	}



	int main(int argc, char **argv)
	{
                //初始化
                gnome_init(PACKAGE, VERSION, argc, argv);

		save_settings();
		get_settings();

		return 0;
	}
