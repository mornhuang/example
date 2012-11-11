

#include "skin.h"
#include <iostream.h>
#include <qwidget.h>
#include <qapplication.h>
#include <qtextcodec.h>

int main( int argc, char **argv )
{
	if(argc < 2){
		cout << "Usage: ./skin gamepad" << endl;
		exit(1);
	}

	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal, TRUE));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	SkinDemo skindemo;

	//设置为主窗口
	a.setMainWidget( &skindemo );

	//显示
	skindemo.show();

	//进入循环
	return a.exec();
}
