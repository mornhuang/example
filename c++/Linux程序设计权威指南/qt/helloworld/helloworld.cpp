	/* File: helloworld.c */

	#include <qapplication.h>
	#include <qpushbutton.h>

	int main( int argc, char **argv )
	{
		//定义Application
		QApplication a( argc, argv ); 

		//建立按钮
		QPushButton hello( "Hello world!", 0 );

		//设置按钮尺寸
		hello.resize( 100, 30 ); 

		//在Application中加入按钮
		a.setMainWidget( &hello );

		//显示按钮
		hello.show();

		//进入循环
		return a.exec();
	}
