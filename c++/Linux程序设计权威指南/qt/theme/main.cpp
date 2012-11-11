
#include "theme.h"
#include <qapplication.h>
#include <qtextcodec.h>
#include <qmenubar.h>
#include <qpopupmenu.h>

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	a.setFont(QFont("Times", 16, QFont::Normal));
	a.setDefaultCodec( QTextCodec::codecForName("GBK") );

	//定义类
	ThemeDemo themedemo;

	//设置窗口标题
	themedemo.setCaption(QObject::tr("主题(Themes)演示"));

	//设置为主窗口
	a.setMainWidget( &themedemo );

	//显示
	themedemo.show();

	//进入循环
	return a.exec();
}
