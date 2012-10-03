
	/* File: slot.cpp */

	#include <qapplication.h>
	#include <qpushbutton.h>
	#include <qfont.h>


	int main( int argc, char **argv )
	{
		QApplication a( argc, argv );

		//建立按钮
		QPushButton button( "Quit", 0 );

		//设置按钮尺寸
		button.resize( 75, 30 );

		//设置按钮字体
		button.setFont( QFont( "Times", 18, QFont::Bold ) );

		//建立回调, 如果按钮按下, 调用button()
		QObject::connect( &button, SIGNAL(clicked()), &a, SLOT(quit()) );

		//把按钮设置为程序主窗口
		a.setMainWidget( &button );

		//显示按钮
		button.show();

		//进入循环
		return a.exec();
	}

