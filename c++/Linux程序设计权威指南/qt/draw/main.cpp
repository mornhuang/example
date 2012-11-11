

#include "draw.h"
#include <qapplication.h>
#include <qtextcodec.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	DrawDemo drawdemo;

	//设置尺寸
	drawdemo.resize( 640, 480 );

	//设置窗口标题
	drawdemo.setCaption( QObject::tr("绘图演示"));

	//设置为主窗口
	a.setMainWidget( &drawdemo );

	//显示
	drawdemo.show();

	//进入循环
	return a.exec();
}
