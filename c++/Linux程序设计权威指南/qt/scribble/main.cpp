
#include "scribble.h"
#include <qapplication.h>
#include <qtextcodec.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	ScribbleDemo scribbledemo;

	//设置窗口标题
	scribbledemo.setCaption(QObject::tr("交互绘图演示"));

	//设置为主窗口
	a.setMainWidget( &scribbledemo );

	//显示
	scribbledemo.show();

	//进入循环
	return a.exec();
}
